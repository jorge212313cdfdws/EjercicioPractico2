import java.io.*;
import java.util.ArrayList;

public class UtilidadesFicheros {

    private static final String RUTA_DATOS = "datos/";
    private static final String RUTA_RESULTADOS = "resultados/";

    // Devuelve todas las líneas de un fichero de datos
    public static ArrayList<String> getLineasFichero(String nombreFichero) throws IOException {
        ArrayList<String> lineas = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(nombreFichero));
        String linea;
        while ((linea = br.readLine()) != null) {
            lineas.add(linea.trim());
        }
        br.close();
        return lineas;
    }

    // Devuelve un PrintWriter para escribir en un fichero de resultados
    public static PrintWriter getPrintWriter(String nombreFicheroResultado) throws IOException {
        return new PrintWriter(new FileWriter(nombreFicheroResultado));
    }

    // Suma los valores de los ficheros .res
    public static long getSuma(String[] listaNombresFichero) {
        long suma = 0;
        for (String nombreFichero : listaNombresFichero) {
            try {
                BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\JORGE\\Desktop\\Programación Multiproceso\\EjercicioPractico2\\resultados\\" +nombreFichero));
                String lineaCantidad = br.readLine();
                if (lineaCantidad != null) {
                    long cantidad = Long.parseLong(lineaCantidad.trim());
                    suma += cantidad;
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Fallo al procesar el fichero " + nombreFichero);
            }
        }
        return suma;
    }
}
