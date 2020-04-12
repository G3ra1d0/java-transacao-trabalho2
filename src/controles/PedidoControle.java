package controles;

import java.util.Collection;
import java.util.LinkedList;

import dao.PedidoDao;
import dao.ProdutoDao;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import modelos.Pedido;
import modelos.Produto;

public class PedidoControle {
    public static void salvarPedido(Map<String,String> pedidoMap, Collection<Map<String,String>> produtosMap) {
        PedidoDao pedidoDao = new PedidoDao();
        
        Pedido pedido = new Pedido();
        pedido.setData(new Date());
        pedido.setObservacao(pedidoMap.get("observacao"));
        pedido.setIdCliente(Integer.parseInt(pedidoMap.get("cliente")));
        pedido.setIdVendedor(Integer.parseInt(pedidoMap.get("vendedor")));
        
        ProdutoDao produtoDao = new ProdutoDao();
        
        Collection<Produto> produtos = new LinkedList<>();
        
        produtosMap.forEach(p -> {
            Produto produto = new Produto();
            produto = produtoDao.select(Integer.parseInt(p.get("id"))).get();
            produto.setQuantidade(Double.parseDouble(p.get("saldo")));
            
            produtos.add(produto);
        });
        
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