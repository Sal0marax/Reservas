package gestionreservas;

public class Reserva {
    private String nombre;
    private String rut;
    private String area;
    private String fecha;
    private String horario; // Nuevo atributo

    // Constructor
    public Reserva(String nombre, String rut, String area, String fecha, String horario) {
        this.nombre = nombre;
        this.rut = rut;
        this.area = area;
        this.fecha = fecha;
        this.horario = horario; // Inicializar nuevo atributo
    }

    // Métodos getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", RUT: " + rut + ", Área: " + area + ", Fecha: " + fecha + ", Horario: " + horario;
    }
}
