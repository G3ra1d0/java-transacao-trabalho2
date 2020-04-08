package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexaoTransacional {
    public static void rollbackTransacao(Connection conexao) {
        try {
            conexao.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void commitTransacao(Connection conexao) {
        try {
            conexao.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection(int nivelIsolamento) {
        //utilizando o padrão factory, que encapsula a criacao de um objeto
        Connection conexao;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            conexao = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/java-transacao-trabalho2?useTimezone=true&serverTimezone=UTC","mateus",""
            );
            conexao.setAutoCommit(false);
            conexao.setTransactionIsolation(nivelIsolamento);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        return conexao;      
    }

    public static void closeConnection(Connection conexao) {
        try {
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}