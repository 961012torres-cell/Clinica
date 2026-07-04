package mx.com.mrjob.service;

import mx.com.mrjob.dto.CitaDto;
import mx.com.mrjob.dto.ResponseDto;

public interface CitaService {

    ResponseDto getCitas();

    ResponseDto getCitaById(Integer idCita);

    ResponseDto insertCita(CitaDto cita);

    ResponseDto updateCita(CitaDto cita);

    ResponseDto deleteCita(CitaDto cita);
}
