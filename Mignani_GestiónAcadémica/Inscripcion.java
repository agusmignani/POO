import java.util.Date;

public class Inscripcion implements Reportable {
    private int idInscripcion;
    private Alumno alumno; // Relación de asociación con la clase Alumno
    private Curso curso;
    private Date fechaInscripcion;

    public Inscripcion(int idInscripcion, Alumno alumno, Curso curso, Date fechaInscripcion) {
        this.idInscripcion = idInscripcion;
        this.alumno = alumno;
        this.curso = curso;
        this.fechaInscripcion = fechaInscripcion;
    }

    public int getIdInscripcion() { return idInscripcion; }
    public void setIdInscripcion(int idInscripcion) { this.idInscripcion = idInscripcion; }
    
    public Alumno getAlumno() { return alumno; }
    public void setAlumno(Alumno alumno) { this.alumno = alumno; }
    
    public Curso getCurso() { return curso; }
	public void setCurso(Curso curso) {	this.curso = curso;	}
    
    public Date getFechaInscripcion() { return fechaInscripcion; }
    public void setFechaInscripcion(Date fechaInscripcion) { this.fechaInscripcion = fechaInscripcion; }

    public void darDeBaja() {
        System.out.println("Cancelando inscripción ID: " + idInscripcion +
                   " | Alumno: " + alumno.getNombreCompleto() +
                   " | Curso: " + curso.getNombre());
    }
    
    @Override
    public String obtenerReporteLimpio() {
        return "[INSCRIPCIÓN #" + idInscripcion + "] " + alumno.getNombreCompleto() + " en " + curso.getNombre();
    }
}
