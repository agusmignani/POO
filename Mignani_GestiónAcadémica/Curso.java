import java.util.ArrayList;
import java.util.Date;

public class Curso {
    private int idCurso;
    private String nombre;
    private Profesor profesorResponsable; // Relación de asociación
    private int cupoMax;
    private ArrayList<Inscripcion> listaInscripciones;
    private int contadorInscripciones = 1; // Autogenerar IDs de inscripción

    public Curso(int idCurso, String nombre, Profesor profesorResponsable, int cupoMax) {
        if (cupoMax <= 0) {
            throw new IllegalArgumentException("El cupo máximo del curso debe ser mayor a cero. Valor recibido: " + cupoMax);
        }
        if (profesorResponsable == null) {
            throw new IllegalArgumentException("El curso debe tener asignado un profesor responsable obligatorio.");
        }
        this.idCurso = idCurso;
        this.nombre = nombre;
        this.profesorResponsable = profesorResponsable;
        this.cupoMax = cupoMax;
        this.listaInscripciones = new ArrayList<>(); // Se crea la instancia del ArrayList para poder agregar elementos después
    }

	private int generarId() {
		return contadorInscripciones++;
	}
    
    // Polimorfismo estático (Sobrecarga). Versión 1: Inscribe recibiendo un objeto Alumno completo.
    public void inscribirAlumno(Alumno alumno) throws CupoExcedidoException {
        if (estaInscripto(alumno.getDni())) {
            System.out.println("ERROR: El alumno " + alumno.getNombre() + " ya está inscripto.");
            return;
        }
        procesarInscripcion(alumno);
    }

	// Polimorfismo estático (Sobrecarga). Versión 2: Sobrecarga. Permite inscribir usando solo datos básicos (Útil para inscripciones rápidas o manuales.).
    public void inscribirAlumno(int dni, String nombre, String apellido) throws CupoExcedidoException {
        System.out.println("SISTEMA: Iniciando inscripción rápida para DNI: " + dni);
        
        if (estaInscripto(dni)) {
            System.out.println("ERROR: El DNI " + dni + " ya figura en la lista.");
            return; 
        }
        
        Alumno alumnoTemporal = new Alumno(dni, nombre, apellido, "alumnotemporal@mail.com", new Date(), 0); 
        procesarInscripcion(alumnoTemporal);
    }

    private boolean estaInscripto(int dni) {
        return listaInscripciones.stream() //Stream API para recorrer la lista eficientemente
                .anyMatch(inscripcion -> inscripcion.getAlumno().getDni() == dni);
    }

    private void procesarInscripcion(Alumno alumno) throws CupoExcedidoException { // Método centralizador de lógica (Principio DRY - No repetir código)
        if (listaInscripciones.size() < cupoMax) {
            Inscripcion nueva = new Inscripcion( // Crea la instancia de la relación (Asociación) entre Alumno y Curso
                generarId(),
                alumno, 
                this, 
                new Date()
            );
            listaInscripciones.add(nueva);
            System.out.println("ÉXITO: " + alumno.getNombre() + " ha sido inscripto en " + this.nombre);
        } else {
            throw new CupoExcedidoException("No se pudo inscribir a " + alumno.getNombre() + ". Cupo agotado en " + this.nombre);
        }
    }

    public void eliminarInscripcion(int idBusqueda) {
        // removeIf recorre la lista y borra el objeto que cumpla la condición
        boolean seElimino = listaInscripciones.removeIf(inscripcion -> inscripcion.getIdInscripcion() == idBusqueda);
        
        if (seElimino) {
            System.out.println("SISTEMA: La inscripción con ID " + idBusqueda + " ha sido removida.");
        } else {
            System.out.println("SISTEMA: No se encontró ninguna inscripción con el ID " + idBusqueda);
        }
    }

    public void listarInscritos() {
        System.out.println("\n-------------------------------------------");
        System.out.println("LISTADO DE ALUMNOS - CURSO: " + this.nombre.toUpperCase());
        System.out.println("Profesor a cargo: " + profesorResponsable.getNombreCompleto());
        System.out.println("-------------------------------------------");

        if (listaInscripciones.isEmpty()) {
            System.out.println("Aún no hay alumnos inscriptos en este curso.");
        } else {
            for (Inscripcion inscripcion : listaInscripciones) {
                // Navega desde Inscripción hasta los datos heredados en Alumno
                System.out.println("ID Insc: " + inscripcion.getIdInscripcion() + 
								   " | Legajo: " + inscripcion.getAlumno().getLegajo() +
                                   " | DNI: " + inscripcion.getAlumno().getDni() + 
                                   " | Nombre: " + inscripcion.getAlumno().getNombreCompleto());
            }
        }
        System.out.println("Cupo actual: " + listaInscripciones.size() + "/" + cupoMax);
    }

    public int getIdCurso() { return idCurso; }
    public void setIdCurso(int idCurso) { this.idCurso = idCurso; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public int getCupoMax() { return cupoMax; }
    public void setCupoMax(int cupoMax) {
        if (cupoMax <= 0) {
            throw new IllegalArgumentException("El cupo máximo debe ser mayor a cero.");
        }
        this.cupoMax = cupoMax;
    }

    public Profesor getProfesorResponsable() { return profesorResponsable; }
    public void setProfesorResponsable(Profesor profesor) { this.profesorResponsable = profesor; }
	
	//Getter para que Main pueda acceder a las inscripciones y testear la interfaz
    public ArrayList<Inscripcion> getListaInscripciones() {
        return listaInscripciones;
    }
}
