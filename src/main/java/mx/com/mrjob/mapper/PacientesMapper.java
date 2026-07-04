package mx.com.mrjob.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.com.mrjob.dto.PacientesDto;

//Clase encargada de convertir los registros obtenidos
//de la base de datos en objetos PacientesDto.

	public class PacientesMapper<T> implements RowMapper<PacientesDto>{
		
		// Este método se ejecuta por cada registro obtenido
		// de la consulta SQL.
	    public PacientesDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		
	    	// Se crea un objeto PacientesDto
	    	PacientesDto objeto = new PacientesDto();

	    	// Se asignan los valores de la base de datos al DTO
		objeto.setIdPaciente(rs.getInt("ID_PACIENTE"));
		objeto.setNombre(rs.getString("NOMBRE"));
		objeto.setEdad(rs.getInt("EDAD"));
		objeto.setTelefono(rs.getLong("TELEFONO"));
		objeto.setPeso(rs.getDouble("PESO"));
		objeto.setTipoSangre(rs.getString("TIPO_SANGRE"));
		objeto.setFechaCreacion(rs.getTimestamp("FECHA_CREACION").toLocalDateTime());
        objeto.setFechaModificacion(rs.getTimestamp("FECHA_MODIFICACION") == null
        ? null
		    : rs.getTimestamp("FECHA_MODIFICACION").toLocalDateTime());

		// Se devuelve el objeto lleno con la información
		return objeto;
	}
}