package mx.com.mrjob.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.mrjob.dto.PacientesDto;
import mx.com.mrjob.dto.ResponseDto;
import mx.com.mrjob.repository.PacientesRepository;

@Service
public class PacientesServiceImpl implements PacientesService {

    @Autowired
    private PacientesRepository pacientesRepository;

    @Override
    public ResponseDto getPacientes() {

        ResponseDto response = new ResponseDto();

        try {
            List<PacientesDto> pacientes = pacientesRepository.getPacientes();

            if (pacientes != null && !pacientes.isEmpty()) {
                response.setCode(1);
                response.setMessage("Registros de pacientes obtenidos");
                response.setContent(pacientes);
            } else {
                response.setCode(-1);
                response.setMessage("No se obtuvieron pacientes");
            }

        } catch (NullPointerException exception) {
            response.setCode(-10);
            response.setMessage("Alg˙n dato viene nulo");
        } catch (Exception exception) {
            response.setCode(-100);
            response.setMessage("Error al consultar los pacientes");
        }

        return response;
    }

    @Override
    public ResponseDto getPacientesById(Integer idPaciente) {

        ResponseDto response = new ResponseDto();

        try {
            PacientesDto paciente = pacientesRepository.getPacientesById(idPaciente);

            if (paciente != null) {
                response.setCode(1);
                response.setMessage("Paciente obtenido correctamente");
                response.setContent(paciente);
            } else {
                response.setCode(-1);
                response.setMessage("No se encontrÛ el paciente");
            }

        } catch (NullPointerException exception) {
            response.setCode(-10);
            response.setMessage("Alg˙n dato viene nulo");
        } catch (Exception exception) {
            response.setCode(-100);
            response.setMessage("Error al consultar el paciente");
            response.setContent("Verifique el Id enviado e intente nuevamente");
        }

        return response;
    }

    @Override
    public ResponseDto insertPaciente(PacientesDto paciente) {

        ResponseDto response = new ResponseDto();

        String errores = validaciones(paciente);

        if (!errores.isEmpty()) {
            response.setCode(-10);
            response.setMessage("Errores de validaciÛn");
            response.setContent(errores);
            return response;
        }

        try {
            Integer insertResponse = pacientesRepository.insertPaciente(paciente);

            if (insertResponse == 1) {
                response.setCode(1);
                response.setMessage("Se insertÛ correctamente, Paciente guardado");
                return response;
            }

            response.setCode(-1);
            response.setMessage("No se insertaron registros, Verifique la informaciÛn enviada");
            return response;

        } catch (Exception exception) {
            response.setCode(-100);
            response.setMessage("Error al insertar paciente. " + exception.getMessage());
            return response;
        }
    }

    @Override
    public ResponseDto updatePaciente(PacientesDto paciente) {

        ResponseDto response = new ResponseDto();

        StringBuilder errores = new StringBuilder();

        errores.append(validaciones(paciente));

        if (paciente != null) {
            errores.append(validarIdPaciente(paciente.getIdPaciente()));
        }

        if (!errores.toString().isEmpty()) {
            response.setCode(-10);
            response.setMessage("Errores de validaciÛn");
            response.setContent(errores.toString());
            return response;
        }

        try {
            Integer updateResponse = pacientesRepository.updatePaciente(paciente);

            if (updateResponse == 1) {
                response.setCode(1);
                response.setMessage("Se actualizÛ correctamente");
                response.setContent(paciente);
                return response;
            }

            response.setCode(-1);
            response.setMessage("No se actualizaron registros");
            response.setContent("Verifique la informaciÛn enviada");
            return response;

        } catch (Exception exception) {
            response.setCode(-100);
            response.setMessage("Error al actualizar paciente");
            response.setContent(exception.getMessage());
            return response;
        }
    }

    @Override
    public ResponseDto deletePaciente(PacientesDto paciente) {

        ResponseDto response = new ResponseDto();

        StringBuilder errores = new StringBuilder();

        if (paciente == null) {
            errores.append("No se recibieron datos del paciente. ");
        } else {
            errores.append(validarIdPaciente(paciente.getIdPaciente()));
        }

        if (!errores.toString().isEmpty()) {
            response.setCode(-10);
            response.setMessage("Errores de validaciÛn");
            response.setContent(errores.toString());
            return response;
        }

        try {

            Integer deleteResponse = pacientesRepository.deletePaciente(paciente);

            if (deleteResponse == 1) {
                response.setCode(1);
                response.setMessage("El paciente con ID " + paciente.getIdPaciente() + " fue cambiado a estado INACTIVO.");
                return response;
            }

            response.setCode(-1);
            response.setMessage("No se encontrÛ un paciente ACTIVO con el ID " + paciente.getIdPaciente() + ".");
            return response;

        } catch (Exception exception) {

            response.setCode(-100);
            response.setMessage("Error al eliminar paciente");
            response.setContent(exception.getMessage());
            return response;
        }
    }

    private String validaciones(PacientesDto paciente) {

        try {

            StringBuilder errores = new StringBuilder();

            if (paciente == null) {
                return "No se recibieron datos del paciente. ";
            }

            errores.append(validarNombre(paciente.getNombre()));
            errores.append(validarLongitudMinimaNombre(paciente.getNombre()));
            errores.append(validarTipoSangre(paciente.getTipoSangre()));
            errores.append(validarEdad(paciente.getEdad()));
            errores.append(validarPeso(paciente.getPeso()));
            errores.append(validarTelefono(paciente.getTelefono()));

            return errores.toString();

        } catch (Exception e) {
            return "OcurriÛ un error inesperado durante las validaciones.";
        }
    }

    private String validarNombre(String nombre) {

        StringBuilder errores = new StringBuilder();

        if (nombre == null || nombre.trim().isEmpty()) {
            errores.append("El nombre es obligatorio. ");
            return errores.toString();
        }

        if (nombre.length() > 100) {
            errores.append("El nombre no puede tener m·s de 100 caracteres. ");
        }

        if (!nombre.matches("[a-zA-Z·ÈÌÛ˙¡…Õ”⁄Ò— ]+")) {
            errores.append("El nombre solo debe contener letras. ");
        }

        return errores.toString();
    }
    
    private String validarLongitudMinimaNombre(String nombre) {

        if (nombre == null || nombre.trim().isEmpty()) {
            return "";
        }

        if (nombre.trim().length() < 3) {
            return "El nombre debe tener al menos 3 caracteres. ";
        }

        return "";
    }

    private String validarTipoSangre(String tipoSangre) {

        StringBuilder errores = new StringBuilder();

        if (tipoSangre == null || tipoSangre.trim().isEmpty()) {
            errores.append("El tipo de sangre es obligatorio. ");
        }

        if (tipoSangre != null && tipoSangre.length() > 20) {
            errores.append("El tipo de sangre no puede tener m·s de 20 caracteres. ");
        }

        return errores.toString();
    }

    private String validarEdad(int edad) {

        if (edad < 1 || edad > 120) {
            return "La edad debe estar entre 1 y 120 aÒos. ";
        }

        return "";
    }

    private String validarPeso(double peso) {

        if (peso <= 0 || peso > 999.99) {
            return "El peso debe ser mayor a 0 y menor o igual a 999.99 kg. ";
        }

        return "";
    }

    private String validarTelefono(long telefono) {

        if (telefono <= 0 || String.valueOf(telefono).length() != 10) {
            return "El telÈfono debe contener exactamente 10 dÌgitos. ";
        }

        return "";
    }

    private String validarIdPaciente(int idPaciente) {

        if (idPaciente <= 0) {
            return "El ID del paciente es inv·lido. ";
        }

        return "";
    }
 }