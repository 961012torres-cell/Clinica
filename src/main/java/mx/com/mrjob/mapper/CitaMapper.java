package mx.com.mrjob.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.com.mrjob.dto.CitaDto;


public class CitaMapper<T> implements RowMapper<CitaDto>{
		
	   public CitaDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		
	    	
		  CitaDto objeto = new CitaDto();

		  objeto.setIdCita(rs.getInt("ID_CITA"));
		  objeto.setIdPaciente(rs.getInt("ID_PACIENTE"));
		  objeto.setIdDoctor(rs.getInt("ID_DOCTOR"));
		  objeto.setHoraCita(rs.getString("HORA_CITA"));
		  objeto.setIdConsultorio(rs.getInt("ID_CONSULTORIO"));
		   objeto.setFechaCreacion(rs.getTimestamp("FECHA_CREACION") == null
		   ? null : rs.getTimestamp("FECHA_CREACION").toLocalDateTime());
           objeto.setFechaModificacion(rs.getTimestamp("FECHA_MODIFICACION") == null
		   ? null : rs.getTimestamp("FECHA_MODIFICACION").toLocalDateTime());
		   
		   return objeto;
}
}