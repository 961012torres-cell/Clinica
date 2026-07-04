package mx.com.mrjob.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import mx.com.mrjob.dto.DoctorDto;
import mx.com.mrjob.mapper.DoctorMapper;

@Repository
public class DoctorRepositoryImpl implements DoctorRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;


	@Override
	public List<DoctorDto> getDoctores() {

	    return jdbcTemplate.query(
	            "SELECT * FROM INFORMACION_DOCTOR WHERE ESTADO = 'ACTIVO'",
	            new DoctorMapper<DoctorDto>());
	}
	
	@Override
	public DoctorDto getDoctorById(Integer idDoctor) {

	    return jdbcTemplate.queryForObject(
	            "SELECT * FROM INFORMACION_DOCTOR WHERE ID_DOCTOR = ? AND ESTADO = 'ACTIVO'",
	            new Object[] { idDoctor },
	            new DoctorMapper<DoctorDto>());
	}

	 @Override
	    public Integer insertDoctor(DoctorDto doctor) {

	        return jdbcTemplate.update(
	     "INSERT INTO INFORMACION_DOCTOR "
	              + "(ID_DOCTOR, NOMBRE_DOCTOR, ESPECIALIDAD, TELEFONO, EDAD, SEXO, CORREO, HORARIO, ESTADO) "
	              + "VALUES (SEQ_DOCTOR.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, 'ACTIVO')",
	                new Object[] {

	                        doctor.getNombreDoctor(),
	                        doctor.getEspecialidad(),
	                        doctor.getTelefono(),
	                        doctor.getEdad(),
	                        doctor.getSexo(),
	                        doctor.getCorreo(),
	                        doctor.getHorario()
	                });
	    }


	 @Override
	    public Integer updateDoctor(DoctorDto doctor) {

	        return jdbcTemplate.update(
       "UPDATE INFORMACION_DOCTOR " + "SET NOMBRE_DOCTOR = ?, " + "ESPECIALIDAD = ?, " + "TELEFONO = ?, "
	   + "EDAD = ?, " + "SEXO = ?, " + "CORREO = ?, " + "HORARIO = ?, "+ "FECHA_MODIFICACION = SYSDATE " + "WHERE ID_DOCTOR = ? " + "AND ESTADO = 'ACTIVO'",
	                new Object[] {
	                        doctor.getNombreDoctor(),
	                        doctor.getEspecialidad(),
	                        doctor.getTelefono(),
	                        doctor.getEdad(),
	                        doctor.getSexo(),
	                        doctor.getCorreo(),
	                        doctor.getHorario(),
	                        doctor.getIdDoctor()
	                });
	    }

	    @Override
	    public Integer deleteDoctor(DoctorDto doctor) {

	        return jdbcTemplate.update(
	                "UPDATE INFORMACION_DOCTOR "
	              + "SET ESTADO = 'INACTIVO', "
	              + "FECHA_MODIFICACION = SYSDATE "
	              + "WHERE ID_DOCTOR = ? "
	              + "AND ESTADO = 'ACTIVO'",
	                new Object[] {
	                        doctor.getIdDoctor()
	                });
	    }
	}