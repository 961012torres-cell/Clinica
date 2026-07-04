package mx.com.mrjob.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.com.mrjob.dto.DoctorDto;

//Clase encargada de convertir los registros obtenidos
//de la base de datos en objetos PacientesDto.

public class DoctorMapper<T> implements RowMapper<DoctorDto>{
		
		// Este método se ejecuta por cada registro obtenido
		// de la consulta SQL.
	   public DoctorDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		
	    	// Se crea un objeto PacientesDto
		   DoctorDto objeto = new DoctorDto();

		   objeto.setIdDoctor(rs.getInt("ID_DOCTOR"));
		   objeto.setNombreDoctor(rs.getString("NOMBRE_DOCTOR"));
		   objeto.setEspecialidad(rs.getString("ESPECIALIDAD"));
		   objeto.setTelefono(rs.getLong("TELEFONO"));
		   objeto.setEdad(rs.getInt("EDAD"));
		   objeto.setSexo(rs.getString("SEXO"));
		   objeto.setCorreo(rs.getString("CORREO"));
		   objeto.setHorario(rs.getString("HORARIO"));
		   objeto.setFechaCreacion(rs.getTimestamp("FECHA_CREACION") == null
		   ? null : rs.getTimestamp("FECHA_CREACION").toLocalDateTime());
           objeto.setFechaModificacion(rs.getTimestamp("FECHA_MODIFICACION") == null
		   ? null : rs.getTimestamp("FECHA_MODIFICACION").toLocalDateTime());
		   
		   return objeto;
}
}