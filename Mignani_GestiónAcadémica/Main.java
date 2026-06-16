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
        Curso cursoPOO = new Curso(101, "Programación Orientada a Objetos", (Profesor)integrantesSistemas.get(0), 2);

        try {
            System.out.println("Intentando inscripción tradicional para el alumno: " + alumnoVanina.getNombreCompleto() + "...");
            cursoPOO.inscribirAlumno(alumnoVanina); 
            System.out.println("\nIntentando inscripción rápida (Sobrecarga)...");
            cursoPOO.inscribirAlumno(40222333, "Rodrigo", "Lascano"); 
        } catch (CupoExcedidoException e) {
            System.out.println("ERROR EN LA INSCRIPCIÓN INICIAL: " + e.getMessage());
        }

        System.out.println("\n>>> 3. PRUEBA DE LÍMITES Y REGLAS DE NEGOCIO <<<");
        try {
            System.out.println("Intentando inscribir a " + alumnoAgustina.getNombreCompleto() + " (Debería fallar por cupo)...");
            cursoPOO.inscribirAlumno(alumnoAgustina); 
        } catch (CupoExcedidoException e) {
            System.out.println("Capturado en Catch -> " + e.getMessage()); 
        }

        try {
            System.out.println("\nIntentando asignar una nota inválida (12)...");
            Calificacion califPrueba = new Calificacion(1, cursoPOO.getListaInscripciones().get(0), 5);
            califPrueba.setNota(12);
        } catch (IllegalArgumentException e) {
            System.out.println("Capturado en Catch -> Error de validación: " + e.getMessage());
        }
        
        System.out.println("\n>>> 4. ESTADO FINAL DEL CURSO: " + cursoPOO.getNombre().toUpperCase() + " <<<");
        cursoPOO.listarInscritos();

        System.out.println("\n>>> 4.B PRUEBA DE POLIMORFISMO POR INTERFAZ <<<");
        ArrayList<Reportable> elementosAImprimir = new ArrayList<>();
        
        elementosAImprimir.add(profMatias);
        elementosAImprimir.add(alumnoVanina);
        
        if(!cursoPOO.getListaInscripciones().isEmpty()){
            elementosAImprimir.add(cursoPOO.getListaInscripciones().get(0));
        }

        for (Reportable reporte : elementosAImprimir) {
            System.out.println(reporte.obtenerReporteLimpio());
        }

        System.out.println("\n>>> 5. ELIMINANDO INSCRIPCIÓN ID 1 <<<");
        cursoPOO.eliminarInscripcion(1); 
        cursoPOO.listarInscritos(); 

        System.out.println("\n>>> EXPOSICIÓN FINALIZADA CON ÉXITO <<<");
    }
}
