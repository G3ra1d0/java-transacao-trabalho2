package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import conexao.FabricaConexaoSingleton;
import conexao.FabricaConexaoTransacional;
import dao.fachadas.PedidoFachada;
import modelos.Pedido;
import modelos.Produto;

public class PedidoDao {
    private Connection connection;
    private int nivelIsolamento = Connection.TRANSACTION_REPEATABLE_READ; //valor default do nível do
                                                                          //isolamento

    public Connection getConnection() {
        return connection;
    }

    //sobrecarga de construtor
    public PedidoDao() {
        this.connection = FabricaConexaoSingleton.getConnection();
    }

    public PedidoDao(int nivelIsolamento) {
        this.nivelIsolamento = nivelIsolamento;
        FabricaConexaoTransacional fabricaConexaoTransacional = new FabricaConexaoTransacional();
        this.connection = fabricaConexaoTransacional.getConnection(this.nivelIsolamento);
    }

    //sobrecarga de método para trabalhar com uma conexao transacional existente
    public PedidoDao(Connection conexao) {
        this.connection = conexao;
    }

    public Optional<Integer> inserir(Pedido Pedido) {
        Integer id = null;

        String sql = "insert into pedido( data_ped, obs_ped, codcliente_ped, codvendedor_ped )"
                     + " values (?, ?, ?, ?)";

        try {
            PreparedStatement pst = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, Pedido.getData());
            pst.setString(2, Pedido.getObservacao());
            pst.setInt(3, Pedido.getIdCliente());
            pst.setInt(4, Pedido.getIdVendedor());

            pst.execute();

            ResultSet rs = pst.getGeneratedKeys();

            if(rs.next()){
                id = rs.getInt(1);
            }	

        } catch (SQLException e) {
            System.err.println("Erro ao salvar o objeto: " + e.getMessage());
        }
        return Optional.ofNullable(id);
    }

    public void update(Pedido Pedido) {
        String sql = "update pedido set data_ped = ?, obs_ped = ?, codcliente_ped = ?, codvendedor_ped = ?"
                     + " where codigo_ped = ?";

        try {
            PreparedStatement pst = this.connection.prepareStatement(sql);

            pst.setString(1, Pedido.getData());
            pst.setString(2, Pedido.getObservacao());
            pst.setInt(3, Pedido.getIdCliente());
            pst.setInt(4, Pedido.getIdVendedor());
            pst.setInt(5, Pedido.getId());

            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o objeto: " + e.getMessage());
        }
    }

    public void delete(Pedido Pedido) {
        String sql = "delete from pedido where codigo_ped = ?";

        try {
            PreparedStatement pst = this.connection.prepareStatement(sql);

            pst.setInt(1, Pedido.getId());

            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar o objeto: " + e.getMessage());
        }
    }

    public Optional<Pedido> select(int id) {
        Pedido Pedido = new Pedido();
    
        String sql = "select * from pedido where codigo_ped = ?";

        try {
            PreparedStatement pst = this.connection.prepareStatement(sql);
            
            pst.setInt(1, id);
            
            ResultSet Resultado = pst.executeQuery();

            if(Resultado.next()){
                Pedido.setId(Resultado.getInt("codigo_ped"));
                Pedido.setData(Resultado.getString("data_ped"));
                Pedido.setObservacao(Resultado.getString("obs_ped"));
                Pedido.setIdCliente(Resultado.getInt("codcliente_ped"));
                Pedido.setIdVendedor(Resultado.getInt("codvendedor_ped"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar o objeto " + id  + " : " + e.getMessage());
        }finally {
            //FabricaConexaoSingleton.closeConnection();
        }
        return Optional.ofNullable(Pedido);
    }

    public Collection<Pedido> selectAll() {
        Collection<Pedido> Pedidos = new LinkedList<>();
        
        String sql = "select * from pedido";

        try {
            PreparedStatement pst = this.connection.prepareStatement(sql);
            
            ResultSet Resultado = pst.executeQuery();

            if(Resultado.next()) {
                do {
                    Pedido Pedido = new Pedido();

                    Pedido.setId(Resultado.getInt("codigo_ped"));
                    Pedido.setData(Resultado.getString("data_ped"));
                    Pedido.setObservacao(Resultado.getString("obs_ped"));
                    Pedido.setIdCliente(Resultado.getInt("codcliente_ped"));
                    Pedido.setIdVendedor(Resultado.getInt("codvendedor_ped"));

                    Pedidos.add(Pedido);
                
                }while(Resultado.next());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar os objetos : " + e.getMessage());
        }
        return Pedidos;
    }
    /* 
        Atenção:
        Os métodos abaixo utilizam uma nova conexão, isolada da conexão principal da classe
        (this.connection).
        Por que disso? Os métodos abaixo utilizam uma conexão Transacional, então iremos
        evitar possíveis conflitos com instâncias da classe que estejam operando no formato Singleton.
    */
    public void salvarPedido (Pedido pedido, Collection<Produto> produtos){
        try {
            FabricaConexaoTransacional fabricaConexaoTransacional = new FabricaConexaoTransacional();

            Connection conexao = fabricaConexaoTransacional.getConnection(this.nivelIsolamento);

            PedidoFachada.salvarPedido(pedido, produtos, conexao);
        } catch (Exception e) {
            System.err.println("Erro ao utilizar o método salvar : " + e.getMessage());
        }
    }
}