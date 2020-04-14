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
import modelos.VendedorComissao;

public class VendedorComissaoDao {
    private Connection connection;
    private int nivelIsolamento = Connection.TRANSACTION_REPEATABLE_READ; //valor default do nível do
                                                                          //isolamento

    public Connection getConnection() {
        return connection;
    }

    //sobrecarga de construtor
    public VendedorComissaoDao() {
        this.connection = FabricaConexaoSingleton.getConnection();
    }

    public VendedorComissaoDao(int nivelIsolamento) {
        this.nivelIsolamento = nivelIsolamento;
        FabricaConexaoTransacional fabricaConexaoTransacional = new FabricaConexaoTransacional();
        this.connection = fabricaConexaoTransacional.getConnection(this.nivelIsolamento);
    }

    //sobrecarga de método para trabalhar com uma conexao transacional existente
    public VendedorComissaoDao(Connection conexao) {
        this.connection = conexao;
    }

    public Optional<Integer> inserir(VendedorComissao VendedorComissao) {
        Integer id = null;

        String sql = "insert into vendedor_comissao( porcentualcomissao_vendcom, valorcomissao_vendcom, codvend_vendcom, codpedido_vendcom )"
                     + " values (?, ?, ?, ?)";

        try {
            PreparedStatement pst = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pst.setDouble(1, VendedorComissao.getPercentual());
            pst.setDouble(2, VendedorComissao.getValor());
            pst.setInt(3, VendedorComissao.getIdVendedor());
            pst.setInt(4, VendedorComissao.getIdPedido());

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

    public void update(VendedorComissao VendedorComissao) {
        String sql = "update vendedor_comissao set porcentualcomissao_vendcom = ?, valorcomissao_vendcom = ?, codvend_vendcom = ?, codpedido_vendcom = ?"
                     + " where id_vendcom = ?";

        try {
            PreparedStatement pst = this.connection.prepareStatement(sql);

            pst.setDouble(1, VendedorComissao.getPercentual());
            pst.setDouble(2, VendedorComissao.getValor());
            pst.setInt(3, VendedorComissao.getIdVendedor());
            pst.setInt(4, VendedorComissao.getIdPedido());
            pst.setInt(5, VendedorComissao.getId());

            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o objeto: " + e.getMessage());
        }
    }

    public void delete(VendedorComissao VendedorComissao) {
        String sql = "delete from vendedor_comissao where id_vendcom = ?";

        try {
            PreparedStatement pst = this.connection.prepareStatement(sql);

            pst.setInt(1, VendedorComissao.getId());

            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar o objeto: " + e.getMessage());
        }
    }

    public Optional<VendedorComissao> select(int id) {
        VendedorComissao VendedorComissao = new VendedorComissao();
    
        String sql = "select * from vendedor_comissao where id_vendcom = ?";

        try {
            PreparedStatement pst = this.connection.prepareStatement(sql);
            
            pst.setInt(1, id);
            
            ResultSet Resultado = pst.executeQuery();

            if(Resultado.next()){
                VendedorComissao.setId(Resultado.getInt("id_vendcom"));
                VendedorComissao.setPercentual(Resultado.getDouble("porcentualcomissao_vendcom"));
                VendedorComissao.setValor(Resultado.getDouble("valorcomissao_vendcom"));
                VendedorComissao.setIdVendedor(Resultado.getInt("codvend_vendcom"));
                VendedorComissao.setIdPedido(Resultado.getInt("codpedido_vendcom"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar o objeto " + id  + " : " + e.getMessage());
        }finally {
            //FabricaConexaoSingleton.closeConnection();
        }
        return Optional.ofNullable(VendedorComissao);
    }

    public Collection<VendedorComissao> selectAll() {
        Collection<VendedorComissao> VendedoresComissao = new LinkedList<>();
        
        String sql = "select * from vendedor_comissao";

        try {
            PreparedStatement pst = this.connection.prepareStatement(sql);
            
            ResultSet Resultado = pst.executeQuery();

            if(Resultado.next()) {
                do {
                    VendedorComissao VendedorComissao = new VendedorComissao();
                    
                    VendedorComissao.setId(Resultado.getInt("id_vendcom"));
                    VendedorComissao.setPercentual(Resultado.getDouble("porcentualcomissao_vendcom"));
                    VendedorComissao.setValor(Resultado.getDouble("valorcomissao_vendcom"));
                    VendedorComissao.setIdVendedor(Resultado.getInt("codvend_vendcom"));
                    VendedorComissao.setIdPedido(Resultado.getInt("codpedido_vendcom"));

                    VendedoresComissao.add(VendedorComissao);
                
                }while(Resultado.next());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar os objetos : " + e.getMessage());
        }
        return VendedoresComissao;
    }
}