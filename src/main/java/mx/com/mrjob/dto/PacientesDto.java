package mx.com.mrjob.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

//DTO (Data Transfer Object)
  //transporta
 //la información entre las diferentes capas del proyecto
 //(Controller, Service y Repository).
 

public class PacientesDto {
	
	
	private int idPaciente;
    private String nombre;
    private int edad;
    private long telefono;
    private double peso;
    private String tipoSangre;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime fechaCreacion;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime fechaModificacion;

    public PacientesDto(int idPaciente, String nombre, int edad,
                        long telefono, double peso, String tipoSangre,LocalDateTime fechaCreacion, LocalDateTime fechaModificacion){  
        super();
        this.idPaciente = idPaciente;
        this.nombre = nombre;
        this.edad = edad;
        this.telefono = telefono;
        this.peso = peso;
        this.tipoSangre = tipoSangre;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
    
    }

    public PacientesDto() {
        super();
    }

    public int getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public long getTelefono() {
		return telefono;
	}

	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public String getTipoSangre() {
		return tipoSangre;
	}
	
	public void setTipoSangre(String tipoSangre) {
	    this.tipoSangre = tipoSangre;
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
	
	/*
     * Método toString().
     //muestra el contenido del objeto en formato texto.
     */
	
	@Override
	public String toString() {
	    return "PacientesDto [idPaciente=" + idPaciente + ", nombre=" + nombre + ", edad=" + edad  + ", telefono=" + telefono
	  + ", peso=" + peso + ", tipoSangre=" + tipoSangre + ", fechaCreacion=" + fechaCreacion + ", fechaModificacion=" + fechaModificacion + "]";
	}
	
}


