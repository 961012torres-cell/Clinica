package mx.com.mrjob.repository;

import java.util.List;

import mx.com.mrjob.dto.MedicamentoDto;

public interface MedicamentoRepository {

    List<MedicamentoDto> getMedicamentos();

    MedicamentoDto getMedicamentoById(Integer idMedicamento);

    Integer insertMedicamento(MedicamentoDto medicamento);

    Integer updateMedicamento(MedicamentoDto medicamento);

    Integer deleteMedicamento(MedicamentoDto medicamento);
}