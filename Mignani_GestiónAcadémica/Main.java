import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
		// Colección polimórfica basada en la superclase (Regla "Es un": Alumno/Profesor ES UNA Persona)
        ArrayList<Persona> integrantesSistemas = new ArrayList<>(); 
        
        Profesor profMatias = new Profesor(20111222, "Matias", "Galvan", "maty@gmail.com", new Date());
        Alumno alumnoVanina = new Alumno(40111222, "Vanina", "Tantucci", "vani@gmail.com", new Date(), 1001);
        Alumno alumnoMaximo = new Alumno(42333444, "Maximo", "Tantucci", "maxi@gmail.com", new Date(), 1002);
        Alumno alumnoAgustina = new Alumno(45666777, "Agustina", "Mignani", "agus@gmail.com", new Date(), 1003);

        integrantesSistemas.add(profMatias);
        integrantesSistemas.add(alumnoVanina);
        integrantesSistemas.add(alumnoMaximo);
        integrantesSistemas.add(alumnoAgustina);

        System.out.println(">>> 1. PRUEBA DE ENLACE DINÁMICO (Polimorfismo de Ejecución) <<<");
        for (Persona persona : integrantesSistemas) { 
            persona.mostrarRol(); 
        }

        System.out.println("\n>>> 2. PRUEBA DE SOBRECARGA (Polimorfismo Estático) <<<");
        Curso cursoPOO = new Curso(101, "Programación Orientada a Objetos", (Profesor)integrantesSistemas.get(0), 2);// Crea el curso usando el profesor que está en nuestra lista (posicion 0)

        System.out.println("Intentando inscripción tradicional...");
        cursoPOO.inscribirAlumno(alumnoVanina); // Versión 1: Pasamos el objeto Alumno completo

        System.out.println("\nIntentando inscripción rápida (Sobrecarga)...");
        cursoPOO.inscribirAlumno(40222333, "Rodrigo", "Lascano"); // Versión 2:Pasamos solo datos básicos (DNI, Nombre, Apellido). El compilador sabe que debe usar el segundo método por los parámetros.

        System.out.println("\n>>> 3. PRUEBA DE LÍMITES Y REGLAS DE NEGOCIO <<<");
        cursoPOO.inscribirAlumno(alumnoAgustina); // Esta debería fallar por cupo (puse máximo 2 y ya se anotaron Vanina y Rodrigo)
        
        cursoPOO.inscribirAlumno(alumnoVanina); // Intentar anotar a alguien que ya está (Vanina)

        System.out.println("\n>>> 4. ESTADO FINAL DEL CURSO <<<");
        cursoPOO.listarInscritos();

        System.out.println("\n>>> 4.B PRUEBA DE POLIMORFISMO POR INTERFAZ <<<");
        // Una lista que unifica objetos completamente distintos (Persona e Inscripción) bajo el tipo de la interfaz
        ArrayList<Reportable> elementosAImprimir = new ArrayList<>();
        elementosAImprimir.add(profMatias);
        elementosAImprimir.add(alumnoVanina);
        // Obtenemos la inscripción nro 1 directamente del curso para la prueba
        if(!cursoPOO.getListaInscripciones().isEmpty()){
            elementosAImprimir.add(cursoPOO.getListaInscripciones().get(0));
        }

        for (Reportable reporte : elementosAImprimir) {
            // El comportamiento cambia según la instancia real del objeto en tiempo de ejecución
            System.out.println(reporte.obtenerReporteLimpio());
        }

        System.out.println("\n>>> 5. ELIMINANDO INSCRIPCIÓN ID 1 <<<");
        cursoPOO.eliminarInscripcion(1); 
        cursoPOO.listarInscritos(); 

        System.out.println("\n>>> EXPOSICIÓN FINALIZADA CON ÉXITO <<<");
    }
}
