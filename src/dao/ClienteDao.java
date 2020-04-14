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
import modelos.Cliente;

public class ClienteDao {
    private Connection connection;
    private int nivelIsolamento = Connection.TRANSACTION_REPEATABLE_READ; //valor default do nível do
                                                                          //isolamento

    public Connection getConnection() {
        return connection;
    }

    //sobrecarga de construtor
    public ClienteDao() {
        this.connection = FabricaConexaoSingleton.getConnection();
    }

    public ClienteDao(int nivelIsolamento) {
        this.nivelIsolamento = nivelIsolamento;
        FabricaConexaoTransacional fabricaConexaoTransacional = new FabricaConexaoTransacional();
        this.connection = fabricaConexaoTransacional.getConnection(this.nivelIsolamento);
    }

    //sobrecarga de método para trabalhar com uma conexao transacional existente
    public ClienteDao(Connection conexao) {
        this.connection = conexao;
    }

    public Optional<Integer> inserir(Cliente cliente) {
        Integer id = null;

        String sql = "insert into cliente( nome_cli, cpf_cli ) values (?, ?)";

        try {
            PreparedStatement pst = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, cliente.getNome());
            pst.setString(2, cliente.getCpf());

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

    public void update(Cliente cliente) {
        String sql = "update cliente set nome_cli = ?, cpf_cli = ?, ultimacompra_cli = ? "
                     + "where codigo_cli = ?";

        try {
            PreparedStatement pst = this.connection.prepareStatement(sql);

            pst.setString(1, cliente.getNome());
            pst.setString(2, cliente.getCpf());
            pst.setString(3, cliente.getUltimaCompra());
            pst.setInt(4, cliente.getId());

            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o objeto: " + e.getMessage());
        }
    }

    public void delete(Cliente cliente) {
        String sql = "delete from cliente where codigo_cli = ?";

        try {
            PreparedStatement pst = this.connection.prepareStatement(sql);

            pst.setInt(1, cliente.getId());

            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar o objeto: " + e.getMessage());
        }
    }

    public Optional<Cliente> select(int id) {
        Cliente cliente = new Cliente();
    
        String sql = "select * from cliente where codigo_cli = ?";

        try {
            PreparedStatement pst = this.connection.prepareStatement(sql);
            
            pst.setInt(1, id);
            
            ResultSet Resultado = pst.executeQuery();

            if(Resultado.next()){
                cliente.setId(Resultado.getInt("codigo_cli"));
                cliente.setNome(Resultado.getString("nome_cli"));
                cliente.setCpf(Resultado.getString("cpf_cli"));
                cliente.setUltimaCompra(Resultado.getString("ultimacompra_cli"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar o objeto " + id  + " : " + e.getMessage());
        }finally {
            //FabricaConexaoSingleton.closeConnection();
        }
        return Optional.ofNullable(cliente);
    }

    public Collection<Cliente> selectAll() {
        Collection<Cliente> clientes = new LinkedList<>();
        
        String sql = "select * from cliente";

        try {
            PreparedStatement pst = this.connection.prepareStatement(sql);
            
            ResultSet Resultado = pst.executeQuery();

            if(Resultado.next()) {
                do {
                    Cliente cliente = new Cliente();

                    cliente.setId(Resultado.getInt("codigo_cli"));
                    cliente.setNome(Resultado.getString("nome_cli"));
                    cliente.setCpf(Resultado.getString("cpf_cli"));
                    cliente.setUltimaCompra(Resultado.getString("ultimacompra_cli"));

                    clientes.add(cliente);
                
                }while(Resultado.next());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar os objetos : " + e.getMessage());
        }
        return clientes;
    }
}