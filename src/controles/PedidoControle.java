package controles;

import java.util.Collection;
import java.util.LinkedList;

import dao.PedidoDao;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import modelos.Pedido;
import modelos.Produto;

public class PedidoControle {
    public static void salvarPedido(Pedido pedido, Collection<Produto> produtos) {
        PedidoDao pedidoDao = new PedidoDao();
        pedidoDao.salvarPedido(pedido, produtos);
    }

    public static void removerPedido(String codigo) {
        PedidoDao pedidoDao = new PedidoDao();

        Optional<Pedido> optPedido = pedidoDao.select(Integer.parseInt(codigo));

        if(optPedido.isPresent()) {
            pedidoDao.excluirPedido(optPedido.get());
        }else {
            System.err.println("Código de pedido inválido");
        }
    }
    
    public static Collection<Map<String, String>> listarPedidos() {
        PedidoDao pedidoDao = new PedidoDao();
        return pedidoDao.selectAll().stream()
                                    .map(Pedido::toMap)
                                    .collect(Collectors.toCollection(LinkedList::new));
    }

}