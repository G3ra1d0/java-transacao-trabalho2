package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexaoSingleton {
    //utilizando o padrão singleton, uma unica classe que mantem a conexão para todas as outras classes
    private static Connection conexao = null;

    public static Connection getConnection() {
        if(conexao == null) {
            //utilizando o padrão factory, que encapsula a criacao de um objeto
            try {
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                conexao = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/java-transacao-trabalho2?useTimezone=true&serverTimezone=UTC","mateus",""
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return conexao;
        
    }

    public static void closeConnection() {
        try {
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}