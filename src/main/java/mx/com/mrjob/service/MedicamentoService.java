package mx.com.mrjob.service;

import mx.com.mrjob.dto.MedicamentoDto;
import mx.com.mrjob.dto.ResponseDto;

public interface MedicamentoService {

    ResponseDto getMedicamentos();

    ResponseDto getMedicamentoById(Integer idMedicamento);

    ResponseDto insertMedicamento(MedicamentoDto medicamento);

    ResponseDto updateMedicamento(MedicamentoDto medicamento);

    ResponseDto deleteMedicamento(MedicamentoDto medicamento);

}