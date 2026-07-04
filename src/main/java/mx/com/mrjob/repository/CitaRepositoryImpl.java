package mx.com.mrjob.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import mx.com.mrjob.dto.CitaDto;
import mx.com.mrjob.mapper.CitaMapper;

@Repository
public class CitaRepositoryImpl implements CitaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<CitaDto> getCitas() {

        return jdbcTemplate.query(
                "SELECT * FROM CITA WHERE ESTADO = 'ACTIVO'",
                new CitaMapper<CitaDto>());
    }

    @Override
    public CitaDto getCitaById(Integer idCita) {

        return jdbcTemplate.queryForObject(
                "SELECT * FROM CITA WHERE ID_CITA = ? AND ESTADO = 'ACTIVO'",
                new Object[] { idCita },
                new CitaMapper<CitaDto>());
    }

    @Override
    public Integer insertCita(CitaDto cita) {

        return jdbcTemplate.update(
        		"INSERT INTO CITA "
        				+ "(ID_CITA, ID_PACIENTE, ID_DOCTOR, HORA_CITA, ID_CONSULTORIO, ESTADO) "
        				+ "VALUES (SEQ_CITA.NEXTVAL, ?, ?, ?, ?, 'ACTIVO')",
        				new Object[] {
        				        cita.getIdPaciente(),
        				        cita.getIdDoctor(),
        				        cita.getHoraCita(),
        				        cita.getIdConsultorio()
        				});
    }
        				
    @Override
    public Integer updateCita(CitaDto cita) {

        return jdbcTemplate.update(
        		"UPDATE CITA "
        				+ "SET ID_PACIENTE = ?, "
        				+ "ID_DOCTOR = ?, "
        				+ "HORA_CITA = ?, "
        				+ "ID_CONSULTORIO = ?, "
        				+ "FECHA_MODIFICACION = SYSDATE "
        				+ "WHERE ID_CITA = ? "
        				+ "AND ESTADO = 'ACTIVO'",
        				new Object[] {
        				        cita.getIdPaciente(),
        				        cita.getIdDoctor(),
        				        cita.getHoraCita(),
        				        cita.getIdConsultorio(),
        				        cita.getIdCita()
        				});
    }

    @Override
    public Integer deleteCita(CitaDto cita) {

        return jdbcTemplate.update(
                "UPDATE CITA "
              + "SET ESTADO = 'INACTIVO', "
              + "FECHA_MODIFICACION = SYSDATE "
              + "WHERE ID_CITA = ? "
              + "AND ESTADO = 'ACTIVO'",
                new Object[] {
                        cita.getIdCita()
                });
    }
   

    @Override
    public Integer existePaciente(Integer idPaciente) {

        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM INFORMACION_PACIENTES WHERE ID_PACIENTE = ?",
                Integer.class,
                idPaciente);
    }
    @Override
    public Integer existeConsultorio(Integer idConsultorio) {

        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM CONSULTORIO WHERE ID_CONSULTORIO = ?",
                Integer.class,
                idConsultorio);
    }
    @Override
    public Integer existeDoctor(Integer idDoctor) {

        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM DOCTOR WHERE ID_DOCTOR = ?",
                Integer.class,
                idDoctor);
    }
}