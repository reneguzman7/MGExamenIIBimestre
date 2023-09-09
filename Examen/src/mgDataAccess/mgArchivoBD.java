package mgDataAccess;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

// import DataAccess.SQLiteDataHelper;
// import Framework.AppException;

public class mgArchivoBD {

    public static String mgRutaArchivo = "Examen\\mgArchivo\\MontesdeocaGuzman.csv";

    public static void mgCargarDatosABaseDeDatos(String csvFilePath) {

        String line;
        String csvSplitBy = ";"; 

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
                Connection connection = mgSQLiteDataHelper.openConnection()) {

            String insertQuery = "INSERT INTO MG_DATOS_ATAQUE (Coordenada, CoordenadaTipo, Lunes, Martes, Miercoles, Jueves, Viernes, TipoArsenal) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);

                // Configura los valores para la consulta preparada
                preparedStatement.setString(1, data[0]);
                preparedStatement.setString(2, data[1]);
                preparedStatement.setString(3, data[2]);
                preparedStatement.setString(4, data[3]);
                preparedStatement.setString(5, data[4]);
                preparedStatement.setString(6, data[5]);
                preparedStatement.setString(7, data[6]);
                preparedStatement.setString(8, data[7]);
            
                preparedStatement.executeUpdate();
            }

            System.out.println("Datos CSV importados con exito.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
