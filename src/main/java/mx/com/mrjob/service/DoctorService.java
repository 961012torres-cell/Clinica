package mx.com.mrjob.service;

import mx.com.mrjob.dto.DoctorDto;
import mx.com.mrjob.dto.ResponseDto;

public interface DoctorService {

    ResponseDto getDoctores();

    ResponseDto getDoctorById(Integer idDoctor);

    ResponseDto insertDoctor(DoctorDto doctor);

    ResponseDto updateDoctor(DoctorDto doctor);

    ResponseDto deleteDoctor(DoctorDto doctor);

}

