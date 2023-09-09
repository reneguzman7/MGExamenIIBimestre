package mgDataAccess;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

import mgBusinessLogic.mgConvertirArsenal;

public class mgArchivoBD extends mgConvertirArsenal {

    public static String mgRutaArchivo = "Examen\\mgArchivo\\MontesdeocaGuzman.csv";

    public static void mgCargarDatosABaseDeDatos(String csvFilePath) {
        String line;
        String csvSplitBy = ";";
        System.out.println("[+] Leyendo: ");
        System.out.println("\t - Coordenadas... ");
        System.out.println("\t - Tipo de Coordenadas... ");
        System.out.println("\t - Arsenal... ");
        System.out.println("\t - Horarios... ");

        System.out.println("[+] Guardando: ");
        System.out.println("\t - Coordenadas... ");
        System.out.println("\t - Tipo de Coordenadas... ");
        System.out.println("\t - Arsenal... ");
        System.out.println("\t - Horarios... ");

    
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

            System.out.println("Datos CSV importados con exito.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
