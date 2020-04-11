package visoes;

import dao.PedidoDao;


public class VisaoExcluirPedido {
    public static void main(String[] args) {
        PedidoDao pedidoDao = new PedidoDao();
        pedidoDao.selectAll()
                 .forEach(p -> pedidoDao.excluirPedido(p));
    }
}