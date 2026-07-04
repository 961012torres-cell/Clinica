package mx.com.mrjob.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import mx.com.mrjob.dto.PacientesDto;
import mx.com.mrjob.mapper.PacientesMapper;

@Repository
public class PacientesRepositoryImpl implements PacientesRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// Consulta todo
	@Override
	public List<PacientesDto> getPacientes() {

	    return jdbcTemplate.query(
	            "SELECT * FROM INFORMACION_PACIENTES WHERE ESTADO = 'ACTIVO'",
	            new PacientesMapper<PacientesDto>());
	}
	// Consulta ID
	@Override
	public PacientesDto getPacientesById(Integer idPaciente) {

	    return jdbcTemplate.queryForObject(
	            "SELECT * FROM INFORMACION_PACIENTES WHERE ID_PACIENTE = ? AND ESTADO = 'ACTIVO'",
	            new Object[] { idPaciente },
	            new PacientesMapper<PacientesDto>());
	}

	@Override
	public Integer insertPaciente(PacientesDto paciente) {

		return jdbcTemplate.update(
				"INSERT INTO INFORMACION_PACIENTES (ID_PACIENTE, NOMBRE, EDAD, TELEFONO, PESO, TIPO_SANGRE) "
						+ "VALUES (SEQ_PACIENTES.NEXTVAL, ?, ?, ?, ?, ?)",
				new Object[] {
						paciente.getNombre(),
						paciente.getEdad(),
						paciente.getTelefono(),
						paciente.getPeso(),
						paciente.getTipoSangre()
				});
	}

	// Actualiza 
	@Override
	public Integer updatePaciente(PacientesDto paciente) {

		return jdbcTemplate.update(
				"UPDATE INFORMACION_PACIENTES SET NOMBRE = ?, EDAD = ?, TELEFONO = ?, PESO = ?, TIPO_SANGRE = ?,FECHA_MODIFICACION = SYSDATE WHERE ID_PACIENTE = ?",
				new Object[] {
						paciente.getNombre(),
						paciente.getEdad(),
						paciente.getTelefono(),
						paciente.getPeso(),
						paciente.getTipoSangre(),
						paciente.getIdPaciente()
				});
	}

	@Override
	public Integer deletePaciente(PacientesDto paciente) {

	    return jdbcTemplate.update(
	            "UPDATE INFORMACION_PACIENTES "
	          + "SET ESTADO = 'INACTIVO', FECHA_MODIFICACION = SYSDATE "
	          + "WHERE ID_PACIENTE = ? "
	          + "AND ESTADO = 'ACTIVO'",
	            new Object[] {
	                    paciente.getIdPaciente()
	            });
	}
}