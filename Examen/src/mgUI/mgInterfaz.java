package mgUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class mgInterfaz extends JFrame {

    private JTable table;

    public mgInterfaz() {
        setTitle("Formulario Ataques");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear el modelo de tabla con columnas
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Usuario");
        model.addColumn("Tipo_Coordenada");
        model.addColumn("Coordenada");
        model.addColumn("Arsenal");
        model.addColumn("Dia");
        model.addColumn("Hora");

        // Conectar a la base de datos SQLite
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:Examen\\database\\MG-EXAMEN-DB.db");
            String query = "SELECT * FROM TablaJoin"; 
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Agregar filas a la tabla desde los datos de la base de datos
            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getString("Usuario")); 
                row.add(resultSet.getString("Tipo_Coordenada"));
                row.add(resultSet.getString("Coordenada"));
                row.add(resultSet.getString("Arsenal"));
                row.add(resultSet.getString("Dia"));
                row.add(resultSet.getString("Hora"));
                model.addRow(row);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Crear la tabla y agregar el modelo de datos
        table = new JTable(model);

        // Agregar la tabla a un JScrollPane para hacerla desplazable
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane);
    }

}
