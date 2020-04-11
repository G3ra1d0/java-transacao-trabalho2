package testes;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import controles.PedidoControle;
import dao.ClienteDao;
import dao.ProdutoDao;
import dao.VendedorDao;
import modelos.Cliente;
import modelos.Pedido;
import modelos.Produto;
import modelos.Vendedor;

public class VisaoInseriPedido {
    public static void main(String[] args) {
        ClienteDao clienteDao = new ClienteDao();
        VendedorDao vendedorDao = new VendedorDao();

        Optional<Cliente> optCliente = clienteDao.select(2);
        Optional<Vendedor> optVendedor = vendedorDao.select(1);

        if (optCliente.isPresent() && optVendedor.isPresent()) {
            Cliente cliente = optCliente.get();

            Vendedor vendedor = optVendedor.get();

            Pedido pedido = new Pedido();
            pedido.setObservacao("Meu segundo teste simulando uma view");
            pedido.setData(new Date());
            pedido.setIdCliente(cliente.getId());
            pedido.setIdVendedor(vendedor.getId());

            ProdutoDao produtoDao = new ProdutoDao();
            Collection<Produto> produtos = produtoDao.selectAll();
            produtos.forEach(p -> p.setQuantidade(0.1));

            PedidoControle.salvarPedido(pedido, produtos);
        }
    }
}