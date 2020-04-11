package dao.fachadas;

import java.sql.Connection;
import java.util.Collection;
import java.util.Date;

import conexao.FabricaConexaoTransacional;
import dao.ClienteDao;
import dao.PedidoDao;
import dao.PedidoProdutoDao;
import dao.ProdutoDao;
import dao.ProdutoMovimentoDao;
import dao.VendedorComissaoDao;
import dao.VendedorDao;
import modelos.Cliente;
import modelos.Pedido;
import modelos.PedidoProduto;
import modelos.Produto;
import modelos.ProdutoMovimento;
import modelos.Vendedor;
import modelos.VendedorComissao;

public class PedidoFachada {
    public static void salvarPedido (Pedido pedido, Collection<Produto> produtos, Connection conexao){
        try {
            PedidoDao pedidoDao = new PedidoDao(conexao);
            pedido.setId(pedidoDao.inserir(pedido).get());

            ProdutoDao produtoDao = new ProdutoDao(conexao);

            ProdutoMovimentoDao produtoMovimentoDao = new ProdutoMovimentoDao(conexao);

            PedidoProdutoDao pedidoProdutoDao = new PedidoProdutoDao(conexao);
            produtos.forEach(p -> {
                produtoDao.update(p);
        
                ProdutoMovimento produtoMovimento = new ProdutoMovimento();
                produtoMovimento.setIdProduto(p.getId());
                produtoMovimento.setQuantidade(p.getQuantidade());
                produtoMovimento.setData(new Date());
                produtoMovimento.setDescricao(ProdutoMovimento.Operacao.S.getDescricao());
                produtoMovimento.setTipo(ProdutoMovimento.Operacao.S);
                produtoMovimentoDao.inserir(produtoMovimento);

                PedidoProduto pedidoProduto = new PedidoProduto();
                pedidoProduto.setIdPedido(pedido.getId());
                pedidoProduto.setIdProduto(p.getId());
                pedidoProduto.setQuantidade(p.getQuantidade());
                pedidoProduto.setValorUni(p.getPreco());
                pedidoProduto.calcValorTotal();
                pedidoProdutoDao.inserir(pedidoProduto);
            });

            ClienteDao clienteDao = new ClienteDao(conexao);
            Cliente cliente = clienteDao.select(pedido.getIdCliente()).get();
            cliente.setUltimaCompra(new Date());
            clienteDao.update(cliente);

            VendedorDao vendedorDao = new VendedorDao(conexao);
            Vendedor vendedor = vendedorDao.select(pedido.getIdVendedor()).get();
            
            VendedorComissao vendedorComissao = new VendedorComissao();;
            vendedorComissao.setIdPedido(pedido.getId());
            vendedorComissao.setIdVendedor(vendedor.getId());
            vendedorComissao.setPercentual(vendedor.getPercentualComissao());
            vendedorComissao.setValor(
                vendedor.getPercentualComissao() * pedidoProdutoDao.selectAll()
                                                .stream()
                                                .filter(pd -> pd.getIdPedido() == pedido.getId())
                                                .mapToDouble(PedidoProduto::getValorTotal)
                                                .sum()
            );

            VendedorComissaoDao vendedorComissaoDao = new VendedorComissaoDao(conexao);
            vendedorComissaoDao.inserir(vendedorComissao);
            
            FabricaConexaoTransacional.commitTransacao(conexao);
        } catch (Exception e) {
            FabricaConexaoTransacional.rollbackTransacao(conexao);
            FabricaConexaoTransacional.closeConnection(conexao);
            throw new RuntimeException("Não foi possível efetuar a operacao de "
                                        + "salvar"
                                        + "\nError: " + e.getMessage());
        }
    }
}