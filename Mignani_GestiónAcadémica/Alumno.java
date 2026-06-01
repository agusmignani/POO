import java.util.Date;

public class Alumno extends Persona { //Herencia
	private int legajo;
	
	public Alumno(int dni, String nombre, String apellido, String email, Date fechaNacimiento, int legajo) {
        super(dni, nombre, apellido, email, fechaNacimiento);
        this.legajo = legajo;
    }

    public int getLegajo() { return legajo; }
    public void setLegajo(int legajo) { this.legajo = legajo; }

    // Polimorfismo Dinámico (Sobrescritura)
    @Override
    public void mostrarRol() {
        System.out.println("Soy el alumno: " + getNombreCompleto() + " | Legajo: " + legajo);
    }
    
    // Implementación obligatoria de la interfaz Reportable (Polimorfismo Dinámico)
    @Override
    public String obtenerReporteLimpio() {
        return "[ALUMNO] Legajo: " + legajo + " - " + getNombreCompleto();
    }
}
