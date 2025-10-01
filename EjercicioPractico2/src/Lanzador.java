import java.io.*;

public class Lanzador {

    private static final String SUFIJO_RESULTADO = ".res";
    private static final String SUFIJO_ERRORES = ".err";
    private static final String RESULTADOS_GLOBALES = "Resultado_global.txt";

    public static void main(String[] args) throws IOException{
        // 👉 Aquí metemos la ruta fija de tu proyecto compilado
        String classpath = "..\\..\\out\\production\\EjercicioPractico2";

        String[] ficheros = {
                "informatica.txt",
                "gerencia.txt",
                "contabilidad.txt",
                "comercio.txt",
                "rrhh.txt"
        };

        String[] ficherosResultado = new String[ficheros.length];
        Process[] procesos = new Process[ficheros.length];

        for (int i = 0; i < ficheros.length; i++) {
            String fichResultado = ficheros[i] + SUFIJO_RESULTADO;
            String fichErrores = ficheros[i] + SUFIJO_ERRORES;

            ficherosResultado[i] = fichResultado;

            ProcessBuilder pb = new ProcessBuilder(
                    "java", "-cp", classpath,
                    "ProcesadorContabilidad",
                    "..\\..\\EjercicioPractico2\\datos\\"+ficheros[i], "C:\\Users\\JORGE\\Desktop\\Programación Multiproceso\\EjercicioPractico2\\resultados\\"+fichResultado
            );

            pb.redirectError(new File("C:\\Users\\JORGE\\Desktop\\Programación Multiproceso\\EjercicioPractico2\\resultados\\" + fichErrores));
            //pb.redirectOutput(new File("C:\\Users\\JORGE\\Desktop\\Programación Multiproceso\\EjercicioPractico2\\resultados\\" + fichResultado));

            procesos[i] = pb.start();
        }

        for (Process proceso : procesos) {
            try {
                proceso.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long total = UtilidadesFicheros.getSuma(ficherosResultado);

        PrintWriter pw = new PrintWriter(new FileWriter(RESULTADOS_GLOBALES));
        pw.println(total);
        pw.close();

        System.out.println("✅ Procesamiento finalizado. Total: " + total);
    }
}
