package mx.com.mrjob.repository;
import java.util.List;

import mx.com.mrjob.dto.CitaDto;

public interface CitaRepository {

    List<CitaDto> getCitas();

    CitaDto getCitaById(Integer idCita);

    Integer insertCita(CitaDto cita);

    Integer updateCita(CitaDto cita);

    Integer deleteCita(CitaDto cita);
    
    Integer existePaciente(Integer idPaciente);

    Integer existeConsultorio(Integer idConsultorio);
    
    Integer existeDoctor(Integer idDoctor);
    
}