import java.io.*;
import java.util.ArrayList;

public class ProcesadorContabilidad {

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.err.println("Uso: java ProcesadorContabilidad <ficheroEntrada> <ficheroSalida>");
            return;
        }

        String nombreFichero = args[0];                // Ej: informatica.txt
        String nombreFicheroResultado = args[1];       // Ej: informatica.txt.res
        ArrayList<String> cantidades;
        long total = 0;

        try {
            // Leemos cantidades
            cantidades = UtilidadesFicheros.getLineasFichero(nombreFichero);
            for (String lineaCantidad : cantidades) {
                if (!lineaCantidad.isEmpty()) {
                    long cantidad = Long.parseLong(lineaCantidad);
                    total += cantidad;
                }
            }

            // Guardamos el total en su fichero .res
            PrintWriter pw = UtilidadesFicheros.getPrintWriter(nombreFicheroResultado);
            pw.println(total);
            pw.close();

        } catch (IOException e) {
            System.err.println("No se pudo procesar el fichero " + nombreFichero);
            e.printStackTrace();
        }
    }
}
