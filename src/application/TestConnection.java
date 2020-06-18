package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {
	
    private static final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";
    
//    private static final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";
    private static final String URL_CONNECTION = "jdbc:oracle:thin:@10.52.234.232:1521:P_MSTSAF";
    private static final String DB_USER = "FISCAL";
    private static final String DB_PASS = "FISCALSTMAKRO";

//    private static final String URL_CONNECTION = "jdbc:oracle:thin:@FISCAL:1521:FISCAL";
//    private static final String DB_USER = "fiscal";
//    private static final String DB_PASS = "fiscalstmakro";
    
    //private static final String URL_CONNECTION = "jdbc:oracle:thin:@10.52.234.232:1521:MSTSAF_PRD";
    //private static final String DB_USER = "fiscal";
    //private static final String DB_PASS = "FISCALSTMAKRO";
    
    public static Connection getConnection() {
        try {
         //conexao com o driver jdbc do oracle
         Class.forName(JDBC_DRIVER);
//         JOptionPane.showMessageDialog(null,"Driver Carregado!!!");
        } catch (ClassNotFoundException ex) {
         // TODO Auto-generated catch block
            ex.printStackTrace();
        }

        try {
            return DriverManager.getConnection(URL_CONNECTION, DB_USER, DB_PASS);   
        } catch(SQLException ex) {
            throw new RuntimeException(ex);
        }
    }  
    
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//new DBOracle();
        //Class.forName(JDBC_DRIVER);
		Connection connection = getConnection();
		//Connection connection = DBOracle.getConnection();
		System.out.println("Conexão aberta!");
		connection.close();
		
		
        //OracleDataSource ods = new OracleDataSource();
        //ods.setURL("jdbc:oracle:thin:/@localhost:1521/XE");
        //Connection conn = ods.getConnection();
        //ConexaoDois conexao = new ConexaoDois();
		
		
	}
}