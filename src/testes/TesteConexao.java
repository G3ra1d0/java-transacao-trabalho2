package testes;

import java.sql.Connection;

import conexao.FabricaConexaoSingleton;
import conexao.FabricaConexaoTransacional;

public class TesteConexao {
    public static void main(String[] args) {
        Connection conexaoSingleton = FabricaConexaoSingleton.getConnection();

        FabricaConexaoTransacional fabricaConexaoTransacional = new FabricaConexaoTransacional();
        Connection conexaoTransacional = fabricaConexaoTransacional.getConnection(Connection.TRANSACTION_READ_COMMITTED);
    }
}