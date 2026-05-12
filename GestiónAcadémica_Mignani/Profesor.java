import java.util.Date;

public class Profesor extends Persona { //Herencia
	
	//Constructor
	public Profesor(int dni, String nombre, String apellido, String email, Date fechaNacimiento) {
        super(dni, nombre, apellido, email, fechaNacimiento);
    }
    
    // Polimorfismo Dinámico
    @Override
    public void mostrarRol() {
        System.out.println("Soy el profesor responsable: " + getNombreCompleto());
    }
}
