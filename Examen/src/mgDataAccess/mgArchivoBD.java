// package mgDataAccess;

// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.sql.Connection;
// import java.sql.PreparedStatement;


// // import DataAccess.SQLiteDataHelper;
// // import Framework.AppException;

// public class mgArchivoBD {
    
//         public static String mgRutaArchivo = "Archivo/mgArchivoDeCoordenadas.txt"; 

         
//          public static void loadCSV(String csvFilePath) throws AppException {
//         String line;
//         String csvSplitBy = ";"; // Cambia esto si tus datos CSV usan un separador diferente

//         try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
//              Connection connection = SQLiteDataHelper.openConnection()) {

//             String insertQuery = "INSERT INTO horarios (nro, codigo, materia, paralelo, aula, creditos, lunes, martes, miercoles, jueves, viernes, sabado, carrera) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


//             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

//             while ((line = br.readLine()) != null) {
//                 String[] data = line.split(csvSplitBy);

//                 // Configura los valores para la consulta preparada
//                 preparedStatement.setString(1, data[0]);
//                 preparedStatement.setString(2, data[1]);
//                 preparedStatement.setString(3, data[2]);
//                 preparedStatement.setString(4, data[3]);
//                 preparedStatement.setString(5, data[4]);
//                 preparedStatement.setString(6, data[5]);
//                 preparedStatement.setString(7, data[6]);
//                 preparedStatement.setString(8, data[7]);
//                 preparedStatement.setString(9, data[8]);
//                 preparedStatement.setString(10, data[9]);
//                 preparedStatement.setString(11, data[10]);
//                 preparedStatement.setString(12, data[11]);
//                 preparedStatement.setString(13, data[12]);


//                 preparedStatement.executeUpdate();
//             }

//             System.out.println("Datos CSV importados con éxito.");

//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }

// }
