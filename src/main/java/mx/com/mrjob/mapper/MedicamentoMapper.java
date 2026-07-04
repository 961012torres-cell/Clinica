package mx.com.mrjob.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.com.mrjob.dto.MedicamentoDto;

public class MedicamentoMapper<T> implements RowMapper<MedicamentoDto> {

    @Override
    public MedicamentoDto mapRow(ResultSet rs, int rowNum) throws SQLException {

        MedicamentoDto objeto = new MedicamentoDto();

        objeto.setIdMedicamento(rs.getInt("ID_MEDICAMENTO"));
        objeto.setNombreMedicamento(rs.getString("NOMBRE_MEDICAMENTO"));
        objeto.setPrecio(rs.getDouble("PRECIO"));
        objeto.setFechaCreacion(rs.getTimestamp("FECHA_CREACION") == null
                ? null : rs.getTimestamp("FECHA_CREACION").toLocalDateTime());
        objeto.setFechaModificacion(rs.getTimestamp("FECHA_MODIFICACION") == null
                ? null : rs.getTimestamp("FECHA_MODIFICACION").toLocalDateTime());

        return objeto;
    }
}