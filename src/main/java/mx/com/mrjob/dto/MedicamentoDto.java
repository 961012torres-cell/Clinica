package mx.com.mrjob.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MedicamentoDto {

    private int idMedicamento;
    private String nombreMedicamento;
    private double precio;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime fechaCreacion;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime fechaModificacion;

    public MedicamentoDto() {
        super();
    }

    public MedicamentoDto(int idMedicamento, String nombreMedicamento,
            double precio, LocalDateTime fechaCreacion,
            LocalDateTime fechaModificacion) {

        super();
        this.idMedicamento = idMedicamento;
        this.nombreMedicamento = nombreMedicamento;
        this.precio = precio;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
    }

    public int getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public String getNombreMedicamento() {
        return nombreMedicamento;
    }

    public void setNombreMedicamento(String nombreMedicamento) {
        this.nombreMedicamento = nombreMedicamento;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
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
        return "MedicamentoDto [idMedicamento=" + idMedicamento
                + ", nombreMedicamento=" + nombreMedicamento
                + ", precio=" + precio
                + ", fechaCreacion=" + fechaCreacion
                + ", fechaModificacion=" + fechaModificacion + "]";
    }
}