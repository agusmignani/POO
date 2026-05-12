public class Calificacion{
	private int idCalificacion;
	private Inscripcion inscripcion;
	private double nota;
	
	// Constructor
    public Calificacion(int idCalificacion, Inscripcion inscripcion, double nota) {
        this.idCalificacion = idCalificacion;
        this.inscripcion = inscripcion;
        this.nota = nota;
    }
    
    //Getter: Consultar id
    public int getIdCalificacion(){
			return idCalificacion;
	}
	
	//Setter: Modificar id
	public void setIdCalificacion(int id){
			this.idCalificacion = id;
	}
	
	//Getter: Consultar inscripcion
	public Inscripcion getInscripcion(){
		return inscripcion;
	}
	
	//Setter: Modificar inscripcion
	public void setInscripcion(Inscripcion inscripcion){
			this.inscripcion = inscripcion;
	}
	
	//Getter: Consultar nota
	public double getNota(){
			return nota;
	}
	
    //Setter: Actualizar nota
    public void setNota(double nota){
		if(nota >= 0 && nota <= 10){
			this.nota = nota;
		} else {
			System.out.println("ERROR: Nota inválida (debe ser entre 0 y 10)");
		}
    }
    
    //Método
   public void mostrarCalificacionesDNI(){
    System.out.println("El alumno " + inscripcion.getAlumno().getNombre() + 
                       " con DNI " + inscripcion.getAlumno().getDni() + 
                       " tiene una nota de: " + nota);
	}
}
