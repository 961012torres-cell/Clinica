package mx.com.mrjob.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CitaDto {

    private int idCita;
    private int idPaciente;
    private String horaCita;
    private int idConsultorio;
    private int idDoctor;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime fechaCreacion;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime fechaModificacion;

    public CitaDto() {
        super();
    }

    public CitaDto(int idCita, int idPaciente, String horaCita,
            int idConsultorio, int idDoctor, LocalDateTime fechaCreacion,
            LocalDateTime fechaModificacion) {

        super();
        this.idCita = idCita;
        this.idPaciente = idPaciente;
        this.horaCita = horaCita;
        this.idConsultorio = idConsultorio;
        this.idDoctor = idDoctor;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(String horaCita) {
        this.horaCita = horaCita;
    }

    public int getIdConsultorio() {
        return idConsultorio;
    }

    public void setIdConsultorio(int idConsultorio) {
        this.idConsultorio = idConsultorio;
    }
    public int getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(int idDoctor) {
        this.idDoctor = idDoctor;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDateTime fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    @Override
    public String toString() {
        return "CitaDto [idCita=" + idCita + ", idPaciente=" + idPaciente
                + ", horaCita=" + horaCita + ", idConsultorio=" + idConsultorio
                + ", idDoctor=" + idDoctor
                + ", fechaCreacion=" + fechaCreacion
                + ", fechaModificacion=" + fechaModificacion + "]";
    }
}