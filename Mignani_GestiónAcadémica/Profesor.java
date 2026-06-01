import java.util.Date;

public class Profesor extends Persona { //Herencia
	
	//Constructor
	public Profesor(int dni, String nombre, String apellido, String email, Date fechaNacimiento) {
        super(dni, nombre, apellido, email, fechaNacimiento);
    }
    
    // Polimorfismo Dinámico (Sobrescritura)
    @Override
    public void mostrarRol() {
        System.out.println("Soy el profesor responsable: " + getNombreCompleto());
    }
    
    // Implementación obligatoria de la interfaz Reportable (Polimorfismo Dinámico)
    @Override
    public String obtenerReporteLimpio() {
        return "[PROFESOR] DNI: " + getDni() + " - " + getNombreCompleto() + " (" + getEmail() + ")";
    }
}
