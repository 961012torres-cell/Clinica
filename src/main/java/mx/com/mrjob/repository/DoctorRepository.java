package mx.com.mrjob.repository;
import java.util.List;

import mx.com.mrjob.dto.DoctorDto;

public interface DoctorRepository {

    List<DoctorDto> getDoctores();

    DoctorDto getDoctorById(Integer idDoctor);

    Integer insertDoctor(DoctorDto doctor);

    Integer updateDoctor(DoctorDto doctor);

    Integer deleteDoctor(DoctorDto doctor);

}