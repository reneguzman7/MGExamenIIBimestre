package mgUI;

import java.awt.Container;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class mgInterfaz extends JFrame {

    private DefaultTableModel modeloUsuarios;
    private DefaultTableModel modeloCoordenadas;
    private DefaultTableModel modeloCoordenadasTipo;
    private DefaultTableModel modeloArsenal;

    private JTable tablaUsuarios;
    private JTable tablaCoordenadas;
    private JTable tablaCoordenadasTipo;
    private JTable tablaArsenal;

    public mgInterfaz() {
        setTitle("Interfaz de Tablas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Establece la conexión con la base de datos SQLite
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:tubasededatos.db");

            // Crea los modelos de tabla para cada tabla de la base de datos
            modeloUsuarios = new DefaultTableModel();
            modeloCoordenadas = new DefaultTableModel();
            modeloCoordenadasTipo = new DefaultTableModel();
            modeloArsenal = new DefaultTableModel();

            // Crea las tablas y establece sus modelos de tabla
            tablaUsuarios = new JTable(modeloUsuarios);
            tablaCoordenadas = new JTable(modeloCoordenadas);
            tablaCoordenadasTipo = new JTable(modeloCoordenadasTipo);
            tablaArsenal = new JTable(modeloArsenal);

            // Agrega las tablas a paneles con barras de desplazamiento
            JScrollPane scrollUsuarios = new JScrollPane(tablaUsuarios);
            JScrollPane scrollCoordenadas = new JScrollPane(tablaCoordenadas);
            JScrollPane scrollCoordenadasTipo = new JScrollPane(tablaCoordenadasTipo);
            JScrollPane scrollArsenal = new JScrollPane(tablaArsenal);

            // Agrega los paneles al contenedor
            Container container = getContentPane();
            container.setLayout(new GridLayout(4, 1));
            container.add(scrollUsuarios);
            container.add(scrollCoordenadas);
            container.add(scrollCoordenadasTipo);
            container.add(scrollArsenal);

            // Obtén los datos de las tablas y muestra en las tablas
            obtenerDatos(connection);

            // Cierra la conexión
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

         // Crea los JScrollPane para cada tabla
        JScrollPane scrollUsuarios = new JScrollPane(tablaUsuarios);
        JScrollPane scrollCoordenadas = new JScrollPane(tablaCoordenadas);
        JScrollPane scrollCoordenadasTipo = new JScrollPane(tablaCoordenadasTipo);
        JScrollPane scrollArsenal = new JScrollPane(tablaArsenal);

        // Crea un JSplitPane para combinar las tablas horizontalmente
        JSplitPane splitPaneHorizontal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPaneHorizontal.setLeftComponent(scrollUsuarios);
        splitPaneHorizontal.setRightComponent(scrollCoordenadas);

        // Crea otro JSplitPane para combinar las tablas de CoordenadasTipo y Arsenal verticalmente
        JSplitPane splitPaneVertical = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPaneVertical.setTopComponent(splitPaneHorizontal);
        splitPaneVertical.setBottomComponent(scrollCoordenadasTipo);

        // Crea un JSplitPane final para combinar las tablas de CoordenadasTipo/Arsenal con la tabla de Usuarios
        JSplitPane finalSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        finalSplitPane.setLeftComponent(splitPaneVertical);
        finalSplitPane.setRightComponent(scrollArsenal);

        // Configura la apariencia y otros aspectos de la ventana principal
        setTitle("Vista Conjunta de Tablas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Agrega el JSplitPane final a la ventana principal
        add(finalSplitPane);

        // Asegúrate de configurar todo antes de hacer visible la ventana
        pack();
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setVisible(true);
    }

    private void obtenerDatos(Connection connection) {
        // Obtén los datos de cada tabla de la base de datos y agrégalos a los modelos de tabla
        obtenerDatosUsuarios(connection);
        obtenerDatosCoordenadas(connection);
        obtenerDatosCoordenadasTipo(connection);
        obtenerDatosArsenal(connection);
    }

    private void obtenerDatosUsuarios(Connection connection) {
        try {
            modeloUsuarios.addColumn("Usuario");

            String query = "SELECT Usuario FROM MG_USUARIOS";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String usuario = resultSet.getString("Usuario");
                Vector<String> fila = new Vector<>();
                fila.add(usuario);
                modeloUsuarios.addRow(fila);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void obtenerDatosCoordenadas(Connection connection) {
        try {
            modeloCoordenadas.addColumn("Coordenada");
    
            String query = "SELECT Coordenada FROM Coordenada";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
    
            while (resultSet.next()) {
                String coordenada = resultSet.getString("Coordenada");
                Vector<String> fila = new Vector<>();
                fila.add(coordenada);
                modeloCoordenadas.addRow(fila);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void obtenerDatosCoordenadasTipo(Connection connection) {
        try {
            modeloCoordenadasTipo.addColumn("Tipo Coordenada");
    
            String query = "SELECT CoordenadaTipo FROM CoordenadaTipo";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
    
            while (resultSet.next()) {
                String tipoCoordenada = resultSet.getString("CoordenadaTipo");
                Vector<String> fila = new Vector<>();
                fila.add(tipoCoordenada);
                modeloCoordenadasTipo.addRow(fila);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void obtenerDatosArsenal(Connection connection) {
        try {
            modeloArsenal.addColumn("Tipo Arsenal");
    
            String query = "SELECT TipoArsenal FROM Arsenal";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
    
            while (resultSet.next()) {
                String tipoArsenal = resultSet.getString("TipoArsenal");
                Vector<String> fila = new Vector<>();
                fila.add(tipoArsenal);
                modeloArsenal.addRow(fila);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}

