package mx.com.mrjob.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.mrjob.dto.MedicamentoDto;
import mx.com.mrjob.dto.ResponseDto;
import mx.com.mrjob.repository.MedicamentoRepository;

@Service
public class MedicamentoServiceImpl implements MedicamentoService {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Override
    public ResponseDto getMedicamentos() {

        ResponseDto response = new ResponseDto();

        try {
            List<MedicamentoDto> medicamentos = medicamentoRepository.getMedicamentos();

            if (medicamentos != null && !medicamentos.isEmpty()) {
                response.setCode(1);
                response.setMessage("Registros de medicamentos obtenidos");
                response.setContent(medicamentos);
            } else {
                response.setCode(-1);
                response.setMessage("No se obtuvieron medicamentos");
            }

        } catch (Exception exception) {
            response.setCode(-100);
            response.setMessage("Error al consultar los medicamentos");
        }

        return response;
    }

    @Override
    public ResponseDto getMedicamentoById(Integer idMedicamento) {

        ResponseDto response = new ResponseDto();

        try {
            MedicamentoDto medicamento = medicamentoRepository.getMedicamentoById(idMedicamento);

            if (medicamento != null) {
                response.setCode(1);
                response.setMessage("Medicamento obtenido correctamente");
                response.setContent(medicamento);
            } else {
                response.setCode(-1);
                response.setMessage("No se encontró el medicamento");
            }

        } catch (Exception exception) {
            response.setCode(-100);
            response.setMessage("Error al consultar el medicamento");
            response.setContent("Verifique el Id enviado e intente nuevamente");
        }

        return response;
    }

    @Override
    public ResponseDto insertMedicamento(MedicamentoDto medicamento) {

        ResponseDto response = new ResponseDto();

        String errores = validaciones(medicamento);

        if (!errores.isEmpty()) {
            response.setCode(-10);
            response.setMessage("Errores de validación");
            response.setContent(errores);
            return response;
        }

        try {
            Integer insertResponse = medicamentoRepository.insertMedicamento(medicamento);

            if (insertResponse == 1) {
                response.setCode(1);
                response.setMessage("Se insertó correctamente, Medicamento guardado");
                return response;
            }

            response.setCode(-1);
            response.setMessage("No se insertaron registros, Verifique la información enviada");
            return response;

        } catch (Exception exception) {
            response.setCode(-100);
            response.setMessage("Error al insertar medicamento. " + exception.getMessage());
            return response;
        }
    }

    @Override
    public ResponseDto updateMedicamento(MedicamentoDto medicamento) {

        ResponseDto response = new ResponseDto();

        StringBuilder errores = new StringBuilder();

        errores.append(validaciones(medicamento));

        if (medicamento != null) {
            errores.append(validarIdMedicamento(medicamento.getIdMedicamento()));
        }

        if (!errores.toString().isEmpty()) {
            response.setCode(-10);
            response.setMessage("Errores de validación");
            response.setContent(errores.toString());
            return response;
        }

        try {
            Integer updateResponse = medicamentoRepository.updateMedicamento(medicamento);

            if (updateResponse == 1) {
                response.setCode(1);
                response.setMessage("Se actualizó correctamente");
                response.setContent(medicamento);
                return response;
            }

            response.setCode(-1);
            response.setMessage("No se actualizaron registros");
            response.setContent("Verifique la información enviada");
            return response;

        } catch (Exception exception) {
            response.setCode(-100);
            response.setMessage("Error al actualizar medicamento");
            response.setContent(exception.getMessage());
            return response;
        }
    }

    @Override
    public ResponseDto deleteMedicamento(MedicamentoDto medicamento) {

        ResponseDto response = new ResponseDto();

        StringBuilder errores = new StringBuilder();

        if (medicamento == null) {
            errores.append("No se recibieron datos del medicamento. ");
        } else {
            errores.append(validarIdMedicamento(medicamento.getIdMedicamento()));
        }

        if (!errores.toString().isEmpty()) {
            response.setCode(-10);
            response.setMessage("Errores de validación");
            response.setContent(errores.toString());
            return response;
        }

        try {
            Integer deleteResponse = medicamentoRepository.deleteMedicamento(medicamento);

            if (deleteResponse == 1) {
                response.setCode(1);
                response.setMessage("El medicamento con ID " + medicamento.getIdMedicamento()
                        + " fue cambiado a estado INACTIVO.");
                return response;
            }

            response.setCode(-1);
            response.setMessage("No se encontró un medicamento ACTIVO con el ID "
                    + medicamento.getIdMedicamento() + ".");
            return response;

        } catch (Exception exception) {
            response.setCode(-100);
            response.setMessage("Error al eliminar medicamento");
            response.setContent(exception.getMessage());
            return response;
        }
    }

    private String validaciones(MedicamentoDto medicamento) {

        try {
            StringBuilder errores = new StringBuilder();

            if (medicamento == null) {
                return "No se recibieron datos del medicamento. ";
            }

            errores.append(validarNombreMedicamento(medicamento.getNombreMedicamento()));
            errores.append(validarPrecio(medicamento.getPrecio()));

            return errores.toString();

        } catch (Exception exception) {
            return "Ocurrió un error inesperado durante las validaciones.";
        }
    }

    private String validarNombreMedicamento(String nombreMedicamento) {

        if (nombreMedicamento == null || nombreMedicamento.trim().isEmpty()) {
            return "El nombre del medicamento es obligatorio. ";
        }

        if (nombreMedicamento.length() > 100) {
            return "El nombre del medicamento no puede tener más de 100 caracteres. ";
        }

        return "";
    }

    private String validarPrecio(double precio) {

        if (precio <= 0) {
            return "El precio del medicamento debe ser mayor a 0. ";
        }

        return "";
    }

    private String validarIdMedicamento(int idMedicamento) {

        if (idMedicamento <= 0) {
            return "El ID del medicamento es inválido. ";
        }

        return "";
    }
}