package mx.com.mrjob.repository;

import java.util.List;

import mx.com.mrjob.dto.PacientesDto;

public interface PacientesRepository {

	List<PacientesDto> getPacientes();

	PacientesDto getPacientesById(Integer idPaciente);

	Integer insertPaciente(PacientesDto paciente);

	Integer updatePaciente(PacientesDto paciente);

	Integer deletePaciente(PacientesDto paciente);

}
