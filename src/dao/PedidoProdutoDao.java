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
import modelos.PedidoProduto;

public class PedidoProdutoDao {
    private Connection connection;
    private int nivelIsolamento = Connection.TRANSACTION_REPEATABLE_READ; //valor default do nível do
                                                                          //isolamento

    public Connection getConnection() {
        return connection;
    }

    //sobrecarga de construtor
    public PedidoProdutoDao() {
        this.connection = FabricaConexaoSingleton.getConnection();
    }

    public PedidoProdutoDao(int nivelIsolamento) {
        this.nivelIsolamento = nivelIsolamento;
        FabricaConexaoTransacional fabricaConexaoTransacional = new FabricaConexaoTransacional();
        this.connection = fabricaConexaoTransacional.getConnection(this.nivelIsolamento);
    }

    //sobrecarga de método para trabalhar com uma conexao transacional existente
    public PedidoProdutoDao(Connection conexao) {
        this.connection = conexao;
    }

    public void inserir(PedidoProduto PedidoProduto) {
        String sql = "insert into pedido_produto( quantidade_pedprod, valor_pedprod, valortotal_pedprod, codproduto_pedprod, codpedido_pedprod )"
                     + " values (?, ?, ?, ?, ?)";

        try {
            PreparedStatement pst = this.connection.prepareStatement(sql);

            pst.setDouble(1, PedidoProduto.getQuantidade());
            pst.setDouble(2, PedidoProduto.getValorUni());
            pst.setDouble(3, PedidoProduto.getValorTotal());
            pst.setInt(4, PedidoProduto.getIdProduto());
            pst.setInt(5, PedidoProduto.getIdPedido());

            pst.execute();

        } catch (SQLException e) {
            System.err.println("Erro ao salvar o objeto: " + e.getMessage());
        }
    }

    public void update(PedidoProduto PedidoProduto) {
        String sql = "update pedido_produto set quantidade_pedprod = ?, valor_pedprod = ?, valortotal_pedprod = ?, codproduto_pedprod = ?, codpedido_pedprod = ?"
                     + " where id_pedprod = ?";

        try {
            PreparedStatement pst = this.connection.prepareStatement(sql);

            pst.setDouble(1, PedidoProduto.getQuantidade());
            pst.setDouble(2, PedidoProduto.getValorUni());
            pst.setDouble(3, PedidoProduto.getValorTotal());
            pst.setInt(4, PedidoProduto.getIdProduto());
            pst.setInt(5, PedidoProduto.getIdPedido());
            pst.setInt(6, PedidoProduto.getId());

            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o objeto: " + e.getMessage());
        }
    }

    public void delete(PedidoProduto PedidoProduto) {
        String sql = "delete from pedido_produto where id_pedprod = ?";

        try {
            PreparedStatement pst = this.connection.prepareStatement(sql);

            pst.setInt(1, PedidoProduto.getId());

            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar o objeto: " + e.getMessage());
        }
    }

    public Optional<PedidoProduto> select(int id) {
        PedidoProduto PedidoProduto = new PedidoProduto();
    
        String sql = "select * from pedido_produto where id_pedprod = ?";

        try {
            PreparedStatement pst = this.connection.prepareStatement(sql);
            
            pst.setInt(1, id);
            
            ResultSet Resultado = pst.executeQuery();

            if(Resultado.next()){
                PedidoProduto.setId(Resultado.getInt("id_pedprod"));
                PedidoProduto.setQuantidade(Resultado.getDouble("quantidade_pedprod"));
                PedidoProduto.setValorUni(Resultado.getDouble("valor_pedprod"));
                PedidoProduto.setValorTotal(Resultado.getDouble("valortotal_pedprod"));
                PedidoProduto.setIdProduto(Resultado.getInt("codproduto_pedprod"));
                PedidoProduto.setIdPedido(Resultado.getInt("codpedido_pedprod"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar o objeto " + id  + " : " + e.getMessage());
        }finally {
            //FabricaConexaoSingleton.closeConnection();
        }
        return Optional.ofNullable(PedidoProduto);
    }

    public Collection<PedidoProduto> selectAll() {
        Collection<PedidoProduto> PedidoProdutos = new LinkedList<>();
        
        String sql = "select * from pedido_produto";

        try {
            PreparedStatement pst = this.connection.prepareStatement(sql);
            
            ResultSet Resultado = pst.executeQuery();

            if(Resultado.next()) {
                do {
                    PedidoProduto PedidoProduto = new PedidoProduto();

                    PedidoProduto.setId(Resultado.getInt("id_pedprod"));
                    PedidoProduto.setQuantidade(Resultado.getDouble("quantidade_pedprod"));
                    PedidoProduto.setValorUni(Resultado.getDouble("valor_pedprod"));
                    PedidoProduto.setValorTotal(Resultado.getDouble("valortotal_pedprod"));
                    PedidoProduto.setIdProduto(Resultado.getInt("codproduto_pedprod"));
                    PedidoProduto.setIdPedido(Resultado.getInt("codpedido_pedprod"));

                    PedidoProdutos.add(PedidoProduto);
                
                }while(Resultado.next());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar os objetos : " + e.getMessage());
        }
        return PedidoProdutos;
    }
}