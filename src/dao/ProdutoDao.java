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
import modelos.Produto;

public class ProdutoDao {
    private Connection connection;
    private int nivelIsolamento = Connection.TRANSACTION_REPEATABLE_READ; //valor default do nível do
                                                                          //isolamento

    public Connection getConnection() {
        return connection;
    }

    //sobrecarga de construtor
    public ProdutoDao() {
        this.connection = FabricaConexaoSingleton.getConnection();
    }

    public ProdutoDao(int nivelIsolamento) {
        this.nivelIsolamento = nivelIsolamento;
        FabricaConexaoTransacional fabricaConexaoTransacional = new FabricaConexaoTransacional();
        this.connection = fabricaConexaoTransacional.getConnection(this.nivelIsolamento);
    }

    //sobrecarga de método para trabalhar com uma conexao transacional existente
    public ProdutoDao(Connection conexao) {
        this.connection = conexao;
    }

    public void inserir(Produto Produto) {
        String sql = "insert into produto( descricao_prod, saldo_prod, unidade_prod, preco_prod )"
                     + " values (?, ?, ?, ?)";

        try {
            PreparedStatement pst = this.connection.prepareStatement(sql);

            pst.setString(1, Produto.getDescricao());
            pst.setDouble(2, Produto.getSaldo());
            pst.setString(3, Produto.getUnidade());
            pst.setDouble(4, Produto.getPreco());

            pst.execute();

        } catch (SQLException e) {
            System.err.println("Erro ao salvar o objeto: " + e.getMessage());
        }
    }

    public void update(Produto Produto) {
        String sql = "update produto set descricao_prod = ?, saldo_prod = ?, unidade_prod = ?, preco_prod = ?"
                     + " where codigo_prod = ?";

        try {
            PreparedStatement pst = this.connection.prepareStatement(sql);

            pst.setString(1, Produto.getDescricao());
            pst.setDouble(2, Produto.getSaldo());
            pst.setString(3, Produto.getUnidade());
            pst.setDouble(4, Produto.getPreco());
            pst.setInt(5, Produto.getId());

            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o objeto: " + e.getMessage());
        }
    }

    public void delete(Produto Produto) {
        String sql = "delete from produto where codigo_prod = ?";

        try {
            PreparedStatement pst = this.connection.prepareStatement(sql);

            pst.setInt(1, Produto.getId());

            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar o objeto: " + e.getMessage());
        }
    }

    public Optional<Produto> select(int id) {
        Produto Produto = new Produto();
    
        String sql = "select * from produto where codigo_prod = ?";

        try {
            PreparedStatement pst = this.connection.prepareStatement(sql);
            
            pst.setInt(1, id);
            
            ResultSet Resultado = pst.executeQuery();

            if(Resultado.next()){
                Produto.setId(Resultado.getInt("codigo_prod"));
                Produto.setDescricao(Resultado.getString("descricao_prod"));
                Produto.setSaldo(Resultado.getDouble("saldo_prod"));
                Produto.setUnidade(Resultado.getString("unidade_prod"));
                Produto.setPreco(Resultado.getDouble("preco_prod"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar o objeto " + id  + " : " + e.getMessage());
        }finally {
            //FabricaConexaoSingleton.closeConnection();
        }
        return Optional.ofNullable(Produto);
    }

    public Collection<Produto> selectAll() {
        Collection<Produto> Produtos = new LinkedList<>();
        
        String sql = "select * from produto";

        try {
            PreparedStatement pst = this.connection.prepareStatement(sql);
            
            ResultSet Resultado = pst.executeQuery();

            if(Resultado.next()) {
                do {
                    Produto Produto = new Produto();
                    
                    Produto.setId(Resultado.getInt("codigo_prod"));
                    Produto.setDescricao(Resultado.getString("descricao_prod"));
                    Produto.setSaldo(Resultado.getDouble("saldo_prod"));
                    Produto.setUnidade(Resultado.getString("unidade_prod"));
                    Produto.setPreco(Resultado.getDouble("preco_prod"));

                    Produtos.add(Produto);
                
                }while(Resultado.next());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar os objetos : " + e.getMessage());
        }
        return Produtos;
    }
}