import java.util.Date;

public abstract class Persona implements Reportable {
	private int dni;
    private String nombre; 
    private String apellido;
    private String email;
    private Date fechaNacimiento;

    public Persona(int dni, String nombre, String apellido, String email, Date fechaNacimiento) {
		this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
    }

    // Getters y Setters comunes para no repetirlos en las otras clases
    public int getDni() { return dni; }
    public void setDni(int dni) { this.dni = dni; }
	
    public String getNombre() {	return nombre; }	
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
	
	public Date getFechaNacimiento(){ return fechaNacimiento; }
	public void setFechaNacimiento(Date nuevaFecha){ this.fechaNacimiento = nuevaFecha;	}
	
	// Método abstracto 
    public abstract void mostrarRol();
    
	// Métodos comunes que heredarán Alumno y Profesor
    public String getNombreCompleto() {
        return apellido + ", " + nombre;
    }
}
