package mgUI;

import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class mgInterfaz extends JFrame{
    private JTable dataTable;
    private JLabel userLabel;

    public mgInterfaz() {
        // Configura la ventana
        setTitle("Consulta de Datos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Crea un panel para los componentes
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Crea una etiqueta para mostrar el usuario logeado
        userLabel = new JLabel("Usuario: " + mgLogin.mgUsuarioLogeado.toUpperCase());
        panel.add(userLabel, BorderLayout.NORTH);

        // Crea una tabla para mostrar los datos
        dataTable = new JTable();
        panel.add(new JScrollPane(dataTable), BorderLayout.CENTER);

        // Agrega el panel a la ventana
        add(panel);
    }

    public void updateUserData(String username) {
        userLabel.setText("Usuario: " + username);
    }

    public void updateTableData(ResultSet resultSet) {
        // Convierte el ResultSet en un modelo de tabla
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Coordenada");
        model.addColumn("CoordenadaTipo");
        model.addColumn("Arsenal");
        model.addColumn("Dia");
        model.addColumn("Hora");

        try {
            while (resultSet.next()) {
                String coordenada = resultSet.getString("Coordenada");
                String coordenadaTipo = resultSet.getString("CoordenadaTipo");
                String arsenal = resultSet.getString("TipoArsenal");
                String dia = resultSet.getString("Dia");
                String hora = resultSet.getString("Hora");

                model.addRow(new String[]{coordenada, coordenadaTipo, arsenal, dia, hora});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        dataTable.setModel(model);
    }

    
}
