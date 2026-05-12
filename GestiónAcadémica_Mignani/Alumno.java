import java.util.Date;

public class Alumno extends Persona { //Herencia
	private int legajo;
	
	//Constructor
	public Alumno(int dni, String nombre, String apellido, String email, Date fechaNacimiento, int legajo) {
        super(dni, nombre, apellido, email, fechaNacimiento);
        this.legajo = legajo;
    }

    // Getter
    public int getLegajo() {
        return legajo;
    }

    // Setter
    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    // Polimorfismo Dinámico
    @Override
    public void mostrarRol() {
        System.out.println("Soy el alumno: " + getNombreCompleto() + " | Legajo: " + legajo);
    }
}
