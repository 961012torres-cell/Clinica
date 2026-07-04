package mx.com.mrjob.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.mrjob.dto.DoctorDto;
import mx.com.mrjob.dto.ResponseDto;
import mx.com.mrjob.repository.DoctorRepository;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public ResponseDto getDoctores() {

        ResponseDto response = new ResponseDto();

        try {
            List<DoctorDto> doctores = doctorRepository.getDoctores();

            if (doctores != null && !doctores.isEmpty()) {
                response.setCode(1);
                response.setMessage("Registros de doctores obtenidos");
                response.setContent(doctores);
            } else {
                response.setCode(-1);
                response.setMessage("No se obtuvieron doctores");
            }

        } catch (NullPointerException exception) {
            response.setCode(-10);
            response.setMessage("Algún dato viene nulo");
        } catch (Exception exception) {
            response.setCode(-100);
            response.setMessage("Error al consultar los doctores");
        }

        return response;
    }

    @Override
    public ResponseDto getDoctorById(Integer idDoctor) {

        ResponseDto response = new ResponseDto();

        try {
            DoctorDto doctor = doctorRepository.getDoctorById(idDoctor);

            if (doctor != null) {
                response.setCode(1);
                response.setMessage("Doctor obtenido correctamente");
                response.setContent(doctor);
            } else {
                response.setCode(-1);
                response.setMessage("No se encontró el doctor");
            }

        } catch (NullPointerException exception) {
            response.setCode(-10);
            response.setMessage("Algún dato viene nulo");
        } catch (Exception exception) {
            response.setCode(-100);
            response.setMessage("Error al consultar el doctor, Verifique el Id enviado e intente nuevamente");
        }

        return response;
    }

    @Override
    public ResponseDto insertDoctor(DoctorDto doctor) {

        ResponseDto response = new ResponseDto();

        String errores = validaciones(doctor);

        if (!errores.isEmpty()) {
            response.setCode(-10);
            response.setMessage("Errores de validación");
            response.setContent(errores);
            return response;
        }

        try {

            Integer insertResponse = doctorRepository.insertDoctor(doctor);

            if (insertResponse == 1) {
                response.setCode(1);
                response.setMessage("Se insertó correctamente, Doctor guardado");
                return response;
            }

            response.setCode(-1);
            response.setMessage("No se insertaron registros, Verifique la información enviada");
            return response;

        } catch (Exception exception) {

            response.setCode(-100);
            response.setMessage("Error al insertar doctor. " + exception.getMessage());
            return response;
        }
    }

    @Override
    public ResponseDto updateDoctor(DoctorDto doctor) {

        ResponseDto response = new ResponseDto();

        StringBuilder errores = new StringBuilder();

        errores.append(validaciones(doctor));

        if (doctor != null) {
            errores.append(validarIdDoctor(doctor.getIdDoctor()));
        }

        if (!errores.toString().isEmpty()) {
            response.setCode(-10);
            response.setMessage("Errores de validación");
            response.setContent(errores.toString());
            return response;
        }

        try {

            Integer updateResponse = doctorRepository.updateDoctor(doctor);

            if (updateResponse == 1) {
                response.setCode(1);
                response.setMessage("El doctor con ID " + doctor.getIdDoctor() + " fue actualizado correctamente.");
                return response;
            }

            response.setCode(-1);
            response.setMessage("No se encontró un doctor ACTIVO con el ID " + doctor.getIdDoctor() + ".");
            return response;

        } catch (Exception exception) {

            response.setCode(-100);
            response.setMessage("Error al actualizar doctor");
            response.setContent(exception.getMessage());
            return response;
        }
    }

    @Override
    public ResponseDto deleteDoctor(DoctorDto doctor) {

        ResponseDto response = new ResponseDto();

        StringBuilder errores = new StringBuilder();

        if (doctor == null) {
            errores.append("No se recibieron datos del doctor. ");
        } else {
            errores.append(validarIdDoctor(doctor.getIdDoctor()));
        }

        if (!errores.toString().isEmpty()) {
            response.setCode(-10);
            response.setMessage("Errores de validación");
            response.setContent(errores.toString());
            return response;
        }

        try {

            Integer deleteResponse = doctorRepository.deleteDoctor(doctor);

            if (deleteResponse == 1) {
                response.setCode(1);
                response.setMessage("El doctor con ID " + doctor.getIdDoctor() + " fue cambiado a estado INACTIVO.");
                return response;
            }

            response.setCode(-1);
            response.setMessage("No se encontró un doctor ACTIVO con el ID " + doctor.getIdDoctor() + ".");
            return response;

        } catch (Exception exception) {

            response.setCode(-100);
            response.setMessage("Error al eliminar doctor");
            response.setContent(exception.getMessage());
            return response;
        }
    }

    private String validaciones(DoctorDto doctor) {

        StringBuilder errores = new StringBuilder();
        
        try {
        
        if (doctor == null) {
            return "No se recibieron datos del doctor. ";
        }

        errores.append(validarNombreDoctor(doctor.getNombreDoctor()));
        errores.append(validarEspecialidad(doctor.getEspecialidad()));
        errores.append(validarEdadDoctor(doctor.getEdad()));
        errores.append(validarTelefono(doctor.getTelefono()));
        errores.append(validarCorreo(doctor.getCorreo()));
        errores.append(validarSexo(doctor.getSexo()));
        errores.append(validarHorario(doctor.getHorario()));

        return errores.toString();

    } catch (Exception exception) {
        return "Ocurrió un error durante las validaciones del doctor.";
    }
}

    private String validarNombreDoctor(String nombreDoctor) {

        StringBuilder errores = new StringBuilder();

        if (nombreDoctor == null || nombreDoctor.trim().isEmpty()) {
            errores.append("El nombre del doctor es obligatorio. ");
            return errores.toString();
        }

        if (nombreDoctor.length() > 100) {
            errores.append("El nombre del doctor no puede tener más de 100 caracteres. ");
        }

        if (!nombreDoctor.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            errores.append("El nombre del doctor solo debe contener letras. ");
        }

        return errores.toString();
    }

    private String validarEspecialidad(String especialidad) {

        StringBuilder errores = new StringBuilder();

        if (especialidad == null || especialidad.trim().isEmpty()) {
            errores.append("La especialidad es obligatoria. ");
            return errores.toString();
        }

        if (especialidad.length() > 100) {
            errores.append("La especialidad no puede tener más de 100 caracteres. ");
        }

        return errores.toString();
    }

    private String validarEdadDoctor(int edad) {

        if (edad < 18 || edad > 50) {
            return "La edad del doctor debe estar entre 18 y 50 años. ";
        }

        return "";
    }

    private String validarTelefono(long telefono) {

        if (telefono <= 0 || String.valueOf(telefono).length() != 10) {
            return "El teléfono debe contener exactamente 10 dígitos. ";
        }

        return "";
    }

    private String validarCorreo(String correo) {

        StringBuilder errores = new StringBuilder();

        if (correo == null || correo.trim().isEmpty()) {
            errores.append("El correo es obligatorio. ");
            return errores.toString();
        }

        if (correo.length() > 100) {
            errores.append("El correo no puede tener más de 100 caracteres. ");
        }

        if (!correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            errores.append("El correo no tiene un formato válido. ");
        }

        return errores.toString();
    }

    private String validarSexo(String sexo) {

        StringBuilder errores = new StringBuilder();

        if (sexo == null || sexo.trim().isEmpty()) {
            errores.append("El sexo es obligatorio. ");
            return errores.toString();
        }

        if (sexo.length() > 20) {
            errores.append("El sexo no puede tener más de 20 caracteres. ");
        }

        if (!sexo.equalsIgnoreCase("MASCULINO")
                && !sexo.equalsIgnoreCase("FEMENINO")) {
            errores.append("El sexo solo puede ser MASCULINO o FEMENINO. ");
        }

        return errores.toString();
    }

    private String validarHorario(String horario) {

        StringBuilder errores = new StringBuilder();

        if (horario == null || horario.trim().isEmpty()) {
            errores.append("El horario es obligatorio. ");
            return errores.toString();
        }

        if (horario.length() > 100) {
            errores.append("El horario no puede tener más de 100 caracteres. ");
        }

        if (!horario.matches("^([01]\\d|2[0-3]):[0-5]\\d A ([01]\\d|2[0-3]):[0-5]\\d$")) {
            errores.append("El horario debe tener el formato HH:MM A HH:MM. ");
        }

        return errores.toString();
    }

    private String validarIdDoctor(int idDoctor) {

        if (idDoctor <= 0) {
            return "El ID del doctor es inválido. ";
        }

        return "";
    }
}