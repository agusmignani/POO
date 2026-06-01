public class Calificacion{
	private int idCalificacion;
	private Inscripcion inscripcion;
	private double nota;
	
    public Calificacion(int idCalificacion, Inscripcion inscripcion, double nota) {
        this.idCalificacion = idCalificacion;
        this.inscripcion = inscripcion;
        this.nota = nota;
    }
    
    public int getIdCalificacion(){	return idCalificacion; }
	public void setIdCalificacion(int id){ this.idCalificacion = id; }
	
	public Inscripcion getInscripcion(){ return inscripcion; }
	public void setInscripcion(Inscripcion inscripcion){ this.inscripcion = inscripcion; }
	
	public double getNota(){ return nota; }
	
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
