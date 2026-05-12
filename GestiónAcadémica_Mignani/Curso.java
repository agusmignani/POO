import java.util.ArrayList;
import java.util.Date;

public class Curso {
    private int idCurso;
    private String nombre;
    private Profesor profesorResponsable; // Relación de asociación
    private int cupoMax;
    private ArrayList<Inscripcion> listaInscripciones;
    private int contadorInscripciones = 1; // Para autogenerar IDs de inscripción

    // Constructor
    public Curso(int idCurso, String nombre, Profesor profesorResponsable, int cupoMax) {
        this.idCurso = idCurso;
        this.nombre = nombre;
        this.profesorResponsable = profesorResponsable;
        this.cupoMax = cupoMax;
        this.listaInscripciones = new ArrayList<>(); // Se crea la instancia del ArrayList para poder agregar elementos después
    }

	private int generarId() {
		return contadorInscripciones++;
	}
    
    public void inscribirAlumno(Alumno alumno) { //Versión 1: Inscribe recibiendo un objeto Alumno completo.
        // Valida si ya está inscripto antes de procesar
        if (estaInscripto(alumno.getDni())) {
            System.out.println("ERROR: El alumno " + alumno.getNombre() + " ya está inscripto.");
            return;
        }
        procesarInscripcion(alumno);
    }

    public void inscribirAlumno(int dni, String nombre, String apellido) { // Versión 2: Sobrecarga. Permite inscribir usando solo datos básicos (Útil para inscripciones rápidas o manuales.).
        System.out.println("SISTEMA: Iniciando inscripción rápida para DNI: " + dni);
        
        Alumno alumnoTemporal = new Alumno(dni, nombre, apellido, "pendiente@mail.com", new Date(), 0); // Crea un objeto temporal para la inscripción (Simula búsqueda o creación rápida). El legajo 0 indica que es una inscripción provisoria.
        
        if (estaInscripto(dni)) {
            System.out.println("ERROR: El DNI " + dni + " ya figura en la lista.");
            return;
        }
        procesarInscripcion(alumnoTemporal);
    }

    private boolean estaInscripto(int dni) {
        return listaInscripciones.stream() //Stream API para recorrer la lista eficientemente
                .anyMatch(ins -> ins.getAlumno().getDni() == dni); // Verifica si algún objeto de la lista coincide con el DNI buscado
    }

    private void procesarInscripcion(Alumno alumno) { // Método centralizador de lógica (Principio DRY - No repetir código)
        if (listaInscripciones.size() < cupoMax) {
            Inscripcion nueva = new Inscripcion( // Crea la instancia de la relación (Asociación) entre Alumno y Curso
                contadorInscripciones++, 
                alumno, 
                this, 
                new Date()
            );
            listaInscripciones.add(nueva);
            System.out.println("ÉXITO: " + alumno.getNombre() + " ha sido inscripto en " + this.nombre);
        } else {
            System.out.println("ERROR: Cupo agotado en " + this.nombre + ". No se pudo inscribir a " + alumno.getNombre());
        }
    }

    //Método para BORRAR un elemento.
		//Busca en la colección por el ID de inscripción y lo elimina si coincide.
    public void eliminarInscripcion(int idBusqueda) {
        // removeIf recorre la lista y borra el objeto que cumpla la condición
        boolean seElimino = listaInscripciones.removeIf(ins -> ins.getIdInscripcion() == idBusqueda);
        
        if (seElimino) {
            System.out.println("SISTEMA: La inscripción con ID " + idBusqueda + " ha sido removida.");
        } else {
            System.out.println("SISTEMA: No se encontró ninguna inscripción con el ID " + idBusqueda);
        }
    }

    //Método para MOSTRAR todos los elementos.
		//Recorre la lista y utiliza los métodos del objeto Alumno para imprimir los datos.
    public void listarInscritos() {
        System.out.println("\n-------------------------------------------");
        System.out.println("LISTADO DE ALUMNOS - CURSO: " + this.nombre.toUpperCase());
        System.out.println("Profesor a cargo: " + profesorResponsable.getNombreCompleto());
        System.out.println("-------------------------------------------");

        if (listaInscripciones.isEmpty()) {
            System.out.println("Aún no hay alumnos inscriptos en este curso.");
        } else {
            for (Inscripcion i : listaInscripciones) {
                // Navega desde Inscripción hasta los datos heredados en Alumno
                System.out.println("ID Insc: " + i.getIdInscripcion() + 
								   " | Legajo: " + i.getAlumno().getLegajo() +
                                   " | DNI: " + i.getAlumno().getDni() + 
                                   " | Nombre: " + i.getAlumno().getNombreCompleto());
            }
        }
        System.out.println("Cupo actual: " + listaInscripciones.size() + "/" + cupoMax);
    }

    public int getIdCurso() { 
		return idCurso; 
	}
	
    public void setIdCurso(int idCurso) { 
		this.idCurso = idCurso; 
	}

    public String getNombre() { 
		return nombre; 
	}
	
    public void setNombre(String nombre) { 
		this.nombre = nombre; 
	}

    public Profesor getProfesorResponsable() { 
		return profesorResponsable; 
	}
	
    public void setProfesorResponsable(Profesor profesor) { 
		this.profesorResponsable = profesor; 
	}
}
