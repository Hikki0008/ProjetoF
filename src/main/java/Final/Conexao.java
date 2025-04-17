package Final;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    private static final String USUARIO = "";
    private static final String SENHA = "";
    private static final String URL = "jdbc:mysql://localhost:3306/MercedesDB?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection abrir() throws Exception {
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}

