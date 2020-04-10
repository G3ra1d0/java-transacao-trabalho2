package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import conexao.FabricaConexaoSingleton;
import conexao.FabricaConexaoTransacional;
import modelos.Vendedor;

public class VendedorDao {
    private Connection connection;
    private int nivelIsolamento = Connection.TRANSACTION_REPEATABLE_READ; //valor default do nível do
                                                                          //isolamento

    public Connection getConnection() {
        return connection;
    }

    //sobrecarga de construtor
    public VendedorDao() {
        this.connection = FabricaConexaoSingleton.getConnection();
    }

    public VendedorDao(int nivelIsolamento) {
        this.nivelIsolamento = nivelIsolamento;
        FabricaConexaoTransacional fabricaConexaoTransacional = new FabricaConexaoTransacional();
        this.connection = fabricaConexaoTransacional.getConnection(this.nivelIsolamento);
    }

    //sobrecarga de método para trabalhar com uma conexao transacional existente
    public VendedorDao(Connection conexao) {
        this.connection = conexao;
    }

    public void inserir(Vendedor Vendedor) {
        String sql = "insert into vendedor( nome_vend, porcentualcomissao_vend )"
                     + " values (?, ?)";

        try {
            PreparedStatement pst = this.connection.prepareStatement(sql);

            pst.setString(1, Vendedor.getNome());
            pst.setDouble(2, Vendedor.getPercentualComissao());

            pst.execute();

        } catch (SQLException e) {
            System.err.println("Erro ao salvar o objeto: " + e.getMessage());
        }
    }

    public void update(Vendedor Vendedor) {
        String sql = "update vendedor set nome_vend = ?, porcentualcomissao_vend = ?"
                     + " where codigo_vend = ?";

        try {
            PreparedStatement pst = this.connection.prepareStatement(sql);

            pst.setString(1, Vendedor.getNome());
            pst.setDouble(2, Vendedor.getPercentualComissao());
            pst.setInt(3, Vendedor.getId());

            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o objeto: " + e.getMessage());
        }
    }

    public void delete(Vendedor Vendedor) {
        String sql = "delete from vendedor where codigo_vend = ?";

        try {
            PreparedStatement pst = this.connection.prepareStatement(sql);

            pst.setInt(1, Vendedor.getId());

            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar o objeto: " + e.getMessage());
        }
    }

    public Optional<Vendedor> select(int id) {
        Vendedor Vendedor = new Vendedor();
    
        String sql = "select * from vendedor where codigo_vend = ?";

        try {
            PreparedStatement pst = this.connection.prepareStatement(sql);
            
            pst.setInt(1, id);
            
            ResultSet Resultado = pst.executeQuery();

            if(Resultado.next()){
                Vendedor.setId(Resultado.getInt("codigo_vend"));
                Vendedor.setNome(Resultado.getString("nome_vend"));
                Vendedor.setPercentualComissao(Resultado.getDouble("porcentualcomissao_vend"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar o objeto " + id  + " : " + e.getMessage());
        }finally {
            //FabricaConexaoSingleton.closeConnection();
        }
        return Optional.ofNullable(Vendedor);
    }

    public Collection<Vendedor> selectAll() {
        Collection<Vendedor> Vendedores = new LinkedList<>();
        
        String sql = "select * from vendedor";

        try {
            PreparedStatement pst = this.connection.prepareStatement(sql);
            
            ResultSet Resultado = pst.executeQuery();

            if(Resultado.next()) {
                do {
                    Vendedor Vendedor = new Vendedor();
                    
                    Vendedor.setId(Resultado.getInt("codigo_vend"));
                    Vendedor.setNome(Resultado.getString("nome_vend"));
                    Vendedor.setPercentualComissao(Resultado.getDouble("porcentualcomissao_vend"));

                    Vendedores.add(Vendedor);
                
                }while(Resultado.next());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar os objetos : " + e.getMessage());
        }
        return Vendedores;
    }
}