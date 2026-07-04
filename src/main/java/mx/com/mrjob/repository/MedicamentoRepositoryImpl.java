package mx.com.mrjob.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import mx.com.mrjob.dto.MedicamentoDto;
import mx.com.mrjob.mapper.MedicamentoMapper;

@Repository
public class MedicamentoRepositoryImpl implements MedicamentoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

   
  
    @Override
    public List<MedicamentoDto> getMedicamentos() {

        return jdbcTemplate.query(
                "SELECT * FROM MEDICAMENTO WHERE ESTADO = 'ACTIVO'",
                new MedicamentoMapper<MedicamentoDto>());
    }

    @Override
    public MedicamentoDto getMedicamentoById(Integer idMedicamento) {

        return jdbcTemplate.queryForObject(
                "SELECT * FROM MEDICAMENTO WHERE ID_MEDICAMENTO = ? AND ESTADO = 'ACTIVO'",
                new Object[] { idMedicamento },
                new MedicamentoMapper<MedicamentoDto>());
    }

    @Override
    public Integer insertMedicamento(MedicamentoDto medicamento) {

        return jdbcTemplate.update(
                "INSERT INTO MEDICAMENTO "
              + "(ID_MEDICAMENTO, NOMBRE_MEDICAMENTO, PRECIO, FECHA_CREACION) "
              + "VALUES (SEQ_MEDICAMENTO.NEXTVAL, ?, ?, SYSDATE)",
                new Object[] {
                        medicamento.getNombreMedicamento(),
                        medicamento.getPrecio()
                });
    }

    @Override
    public Integer updateMedicamento(MedicamentoDto medicamento) {

        return jdbcTemplate.update(
                "UPDATE MEDICAMENTO "
              + "SET NOMBRE_MEDICAMENTO = ?, "
              + "PRECIO = ?, "
              + "FECHA_MODIFICACION = SYSDATE "
              + "WHERE ID_MEDICAMENTO = ? "
              + "AND ESTADO = 'ACTIVO'",
                new Object[] {
                        medicamento.getNombreMedicamento(),
                        medicamento.getPrecio(),
                        medicamento.getIdMedicamento()
                });
    }
    @Override
    public Integer deleteMedicamento(MedicamentoDto medicamento) {

        return jdbcTemplate.update(
                "UPDATE MEDICAMENTO "
              + "SET ESTADO = 'INACTIVO', "
              + "FECHA_MODIFICACION = SYSDATE "
              + "WHERE ID_MEDICAMENTO = ? "
              + "AND ESTADO = 'ACTIVO'",
                new Object[] {
                        medicamento.getIdMedicamento()
                });
    }
}