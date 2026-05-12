import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        ArrayList<Persona> integrantesSistemas = new ArrayList<>(); // Usamos una lista de la clase padre (Persona) para almacenar objetos de clases hijas. (Esto demuestra la regla "Es un": Un Alumno ES UNA Persona.)
        
        Profesor profMatias = new Profesor(20111222, "Matias", "Galvan", "maty@gmail.com", new Date());
        Alumno aluVanina = new Alumno(40111222, "Vanina", "Tantucci", "vani@gmail.com", new Date(), 1001);
        Alumno aluMaximo = new Alumno(42333444, "Maximo", "Tantucci", "maxi@gmail.com", new Date(), 1002);
        Alumno aluAgustina = new Alumno(45666777, "Agustina", "Mignani", "agus@gmail.com", new Date(), 1003);

        integrantesSistemas.add(profMatias);
        integrantesSistemas.add(aluVanina);
        integrantesSistemas.add(aluMaximo);
        integrantesSistemas.add(aluAgustina);

        System.out.println(">>> 1. PRUEBA DE ENLACE DINÁMICO (Polimorfismo de Ejecución) <<<");
        for (Persona p : integrantesSistemas) { // Recorremos la lista de Personas. Aunque la variable 'p' es de tipo Persona, Java ejecutará el método 'mostrarRol' específico de Alumno o Profesor.
            p.mostrarRol(); 
        }

        System.out.println("\n>>> 2. PRUEBA DE SOBRECARGA (Polimorfismo Estático) <<<");
        Curso cursoPOO = new Curso(101, "Programación Orientada a Objetos", (Profesor)integrantesSistemas.get(0), 2);// Crea el curso usando el profesor que está en nuestra lista (posicion 0)

        System.out.println("Intentando inscripción tradicional...");
        cursoPOO.inscribirAlumno(aluVanina); // Versión 1: Pasamos el objeto Alumno completo

        System.out.println("\nIntentando inscripción rápida (Sobrecarga)...");
        cursoPOO.inscribirAlumno(40222333, "Rodrigo", "Lascano"); // Versión 2:Pasamos solo datos básicos (DNI, Nombre, Apellido). El compilador sabe que debe usar el segundo método por los parámetros.

        System.out.println("\n>>> 3. PRUEBA DE LÍMITES Y REGLAS DE NEGOCIO <<<");
        cursoPOO.inscribirAlumno(aluAgustina); // Esta debería fallar por cupo (puse máximo 2 y ya se anotaron Vanina y Rodrigo)
        
        cursoPOO.inscribirAlumno(aluVanina); // Intentar anotar a alguien que ya está (Vanina)

        System.out.println("\n>>> 4. ESTADO FINAL DEL CURSO <<<");
        cursoPOO.listarInscritos();

        System.out.println("\n>>> 5. ELIMINANDO INSCRIPCIÓN ID 1 <<<");
        cursoPOO.eliminarInscripcion(1); // Prueba de eliminación (Desacoplamiento)
        
        cursoPOO.listarInscritos(); // Verificamos que ahora hay un lugar libre
        
        System.out.println("\n>>> EXPOSICIÓN FINALIZADA CON ÉXITO <<<");
    }
}
