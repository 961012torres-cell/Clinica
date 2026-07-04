package mx.com.mrjob.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DoctorDto {

	private int idDoctor;
	private String nombreDoctor;
	private String especialidad;
	private long telefono;
	private int edad;
	private String sexo;
	private String correo;
	private String horario;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime fechaCreacion;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime fechaModificacion;
    public DoctorDto() {
        super();
    }

    public DoctorDto(int idDoctor, String nombreDoctor, String especialidad,
            long telefono, int edad, String sexo, String correo,
            String horario, LocalDateTime fechaCreacion, LocalDateTime fechaModificacion) {
    	
        super();
        this.idDoctor = idDoctor;
        this.nombreDoctor = nombreDoctor;
        this.especialidad = especialidad;
        this.telefono = telefono;
        this.edad = edad;
        this.sexo = sexo;
        this.correo = correo;
        this.horario = horario;
     
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
    }

    public int getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(int idDoctor) {
        this.idDoctor = idDoctor;
    }

    public String getNombreDoctor() {
        return nombreDoctor;
    }

    public void setNombreDoctor(String nombreDoctor) {
        this.nombreDoctor = nombreDoctor;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
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
        return "DoctorDto [idDoctor=" + idDoctor  + ", nombreDoctor=" + nombreDoctor  + ", especialidad=" + especialidad+ ", telefono=" + telefono + ", edad=" + edad + ", sexo=" + sexo + ", correo=" + correo + ", horario=" + horario
        	+ ", fechaCreacion=" + fechaCreacion + ", fechaModificacion=" + fechaModificacion + "]";
    }
}