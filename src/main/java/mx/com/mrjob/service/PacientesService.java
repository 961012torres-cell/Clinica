package mx.com.mrjob.service;

import mx.com.mrjob.dto.PacientesDto;
import mx.com.mrjob.dto.ResponseDto;

public interface PacientesService {

	ResponseDto getPacientes();

	ResponseDto getPacientesById(Integer idPaciente);

	ResponseDto insertPaciente(PacientesDto paciente);

	ResponseDto updatePaciente(PacientesDto paciente);

	ResponseDto deletePaciente(PacientesDto paciente);
}

