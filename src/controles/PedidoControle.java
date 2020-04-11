package controles;

import java.util.Collection;

import dao.PedidoDao;
import modelos.Pedido;
import modelos.Produto;

public class PedidoControle {
    public static void salvarPedido(Pedido pedido, Collection<Produto> produtos) {
        PedidoDao pedidoDao = new PedidoDao();
        pedidoDao.salvarPedido(pedido, produtos);
    }

    public static void removerPedido() {

    }
}