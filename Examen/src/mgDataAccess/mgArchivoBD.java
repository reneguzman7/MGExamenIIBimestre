package mgDataAccess;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

import mgBusinessLogic.mgConvertirArsenal;

/**
 * La clase mgArchivoBD se encarga de cargar datos desde un archivo CSV en una base de datos SQLite.
 */
public class mgArchivoBD extends mgConvertirArsenal {

    // Ruta del archivo CSV a cargar
    public static String mgRutaArchivo = "Examen\\mgArchivo\\MontesdeocaGuzman.csv";

    /**
     * Carga los datos desde un archivo CSV en una base de datos SQLite.
     *
     * @param csvFilePath Ruta del archivo CSV que contiene los datos a cargar.
     */
    public static void mgCargarDatosABaseDeDatos(String csvFilePath) {
        String line;
        String csvSplitBy = ";";
        System.out.println("[+] Leyendo: ");
        System.out.println("\t - Coordenadas... ");
        mgpausa(200);
        System.out.println("\t - Tipo de Coordenadas... ");
        mgpausa(200);
        System.out.println("\t - Arsenal... ");
        mgpausa(200);
        System.out.println("\t - Horarios... ");
        mgpausa(200);
        System.out.println("[+] Guardando: ");
        System.out.println("\t - Coordenadas... ");
        mgpausa(200);
        System.out.println("\t - Tipo de Coordenadas... ");
        mgpausa(200);
        System.out.println("\t - Arsenal... ");
        mgpausa(200);
        System.out.println("\t - Horarios... ");
        mgpausa(200);
    

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
                Connection connection = mgSQLiteDataHelper.openConnection()) {

            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);

                if (data.length >= 8) {
                    String coordenada = data[0];
                    String coordenadaTipo = data[1];
                    String lunes = data[2];
                    String martes = data[3];
                    String miercoles = data[4];
                    String jueves = data[5];
                    String viernes = data[6];
                    String tipoArsenal = data[7];

                    // Convierte las letras a palabras en la columna TipoArsenal
                    tipoArsenal = reemplazarLetrasConPalabras(tipoArsenal);

                    // Inserta en la tabla Coordenada
                    String insertCoordenadaQuery = "INSERT INTO Coordenada (Coordenada) VALUES (?)";
                    PreparedStatement preparedStatementCoordenada = connection.prepareStatement(insertCoordenadaQuery);
                    preparedStatementCoordenada.setString(1, coordenada);
                    preparedStatementCoordenada.executeUpdate();

                    // Inserta en la tabla CoordenadaTipo
                    String insertCoordenadaTipoQuery = "INSERT INTO CoordenadaTipo (CoordenadaTipo) VALUES (?)";
                    PreparedStatement preparedStatementCoordenadaTipo = connection
                            .prepareStatement(insertCoordenadaTipoQuery);
                    preparedStatementCoordenadaTipo.setString(1, coordenadaTipo);
                    preparedStatementCoordenadaTipo.executeUpdate();

                    // Inserta en la tabla Arsenal
                    String insertArsenalQuery = "INSERT INTO Arsenal (TipoArsenal) VALUES (?)";
                    PreparedStatement preparedStatementArsenal = connection.prepareStatement(insertArsenalQuery);
                    preparedStatementArsenal.setString(1, tipoArsenal);
                    preparedStatementArsenal.executeUpdate();

                    // Inserta en la tabla Horarios
                    String insertHorariosQuery = "INSERT INTO Horarios (Lunes, Martes, Miercoles, Jueves, Viernes) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatementHorarios = connection.prepareStatement(insertHorariosQuery);
                    preparedStatementHorarios.setString(1, lunes);
                    preparedStatementHorarios.setString(2, martes);
                    preparedStatementHorarios.setString(3, miercoles);
                    preparedStatementHorarios.setString(4, jueves);
                    preparedStatementHorarios.setString(5, viernes);
                    preparedStatementHorarios.executeUpdate();
                }
            }

            System.out.println("Tablas Creadas y Actualizadas!!!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Pausa la ejecución del programa durante un tiempo determinado en milisegundos.
     *
     * @param milisegundos El tiempo en milisegundos.
     */
    public static void mgpausa(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
