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
import modelos.ProdutoMovimento;

public class ProdutoMovimentoDao {
    private Connection connection;
    private int nivelIsolamento = Connection.TRANSACTION_REPEATABLE_READ; //valor default do nível do
                                                                          //isolamento

    public Connection getConnection() {
        return connection;
    }

    //sobrecarga de construtor
    public ProdutoMovimentoDao() {
        this.connection = FabricaConexaoSingleton.getConnection();
    }

    public ProdutoMovimentoDao(int nivelIsolamento) {
        this.nivelIsolamento = nivelIsolamento;
        FabricaConexaoTransacional fabricaConexaoTransacional = new FabricaConexaoTransacional();
        this.connection = fabricaConexaoTransacional.getConnection(this.nivelIsolamento);
    }

    //sobrecarga de método para trabalhar com uma conexao transacional existente
    public ProdutoMovimentoDao(Connection conexao) {
        this.connection = conexao;
    }

    public void inserir(ProdutoMovimento produtoMovimento) {
        String sql = "insert into produto_movimento( tipo_prodmov, data_prodmov, descricao_prodmov, codproduto_prodmov, quantidade_prodmov )"
                     + " values (?, ?, ?, ?, ?)";

        try {
            PreparedStatement pst = this.connection.prepareStatement(sql);

            pst.setString(1, produtoMovimento.getTipo().toString());
            pst.setString(2, produtoMovimento.getData());
            pst.setString(3, produtoMovimento.getDescricao());
            pst.setInt(4, produtoMovimento.getIdProduto());
            pst.setDouble(5, produtoMovimento.getQuantidade());

            pst.execute();

        } catch (SQLException e) {
            System.err.println("Erro ao salvar o objeto: " + e.getMessage());
        }
    }

    public void update(ProdutoMovimento produtoMovimento) {
        String sql = "update produto_movimento set tipo_prodmov = ?, data_prodmov = ?, descricao_prodmov = ?, codproduto_prodmov = ?, quantidade_prodmov = ?"
                     + " where id_prodmov = ?";

        try {
            PreparedStatement pst = this.connection.prepareStatement(sql);

            pst.setString(1, produtoMovimento.getTipo().toString());
            pst.setString(2, produtoMovimento.getData());
            pst.setString(3, produtoMovimento.getDescricao());
            pst.setInt(4, produtoMovimento.getIdProduto());
            pst.setDouble(5, produtoMovimento.getQuantidade());
            pst.setInt(6, produtoMovimento.getId());

            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o objeto: " + e.getMessage());
        }
    }

    public void delete(ProdutoMovimento produtoMovimento) {
        String sql = "delete from produto_movimento where id_prodmov = ?";

        try {
            PreparedStatement pst = this.connection.prepareStatement(sql);

            pst.setInt(1, produtoMovimento.getId());

            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar o objeto: " + e.getMessage());
        }
    }

    public Optional<ProdutoMovimento> select(int id) {
        ProdutoMovimento produtoMovimento = new ProdutoMovimento();
    
        String sql = "select * from produto_movimento where id_prodmov = ?";

        try {
            PreparedStatement pst = this.connection.prepareStatement(sql);
            
            pst.setInt(1, id);
            
            ResultSet Resultado = pst.executeQuery();

            if(Resultado.next()){
                produtoMovimento.setId(Resultado.getInt("id_prodmov"));
                produtoMovimento.setTipo(ProdutoMovimento.Operacao.valueOf(Resultado.getString("tipo_prodmov")));
                produtoMovimento.setData(Resultado.getString("data_prodmov"));
                produtoMovimento.setDescricao(Resultado.getString("descricao_prodmov"));
                produtoMovimento.setIdProduto(Resultado.getInt("codproduto_prodmov"));
                produtoMovimento.setQuantidade(Resultado.getDouble("quantidade_prodmov"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar o objeto " + id  + " : " + e.getMessage());
        }finally {
            //FabricaConexaoSingleton.closeConnection();
        }
        return Optional.ofNullable(produtoMovimento);
    }

    public Collection<ProdutoMovimento> selectAll() {
        Collection<ProdutoMovimento> ProdutoMovimentos = new LinkedList<>();
        
        String sql = "select * from produto_movimento";

        try {
            PreparedStatement pst = this.connection.prepareStatement(sql);
            
            ResultSet Resultado = pst.executeQuery();

            if(Resultado.next()) {
                do {
                    ProdutoMovimento produtoMovimento = new ProdutoMovimento();
                    
                    produtoMovimento.setId(Resultado.getInt("id_prodmov"));
                    produtoMovimento.setTipo(ProdutoMovimento.Operacao.valueOf(Resultado.getString("tipo_prodmov")));
                    produtoMovimento.setData(Resultado.getString("data_prodmov"));
                    produtoMovimento.setDescricao(Resultado.getString("descricao_prodmov"));
                    produtoMovimento.setIdProduto(Resultado.getInt("codproduto_prodmov"));
                    produtoMovimento.setQuantidade(Resultado.getDouble("quantidade_prodmov"));

                    ProdutoMovimentos.add(produtoMovimento);
                
                }while(Resultado.next());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar os objetos : " + e.getMessage());
        }
        return ProdutoMovimentos;
    }
}