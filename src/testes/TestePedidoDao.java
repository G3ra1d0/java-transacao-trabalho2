package testes;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import dao.PedidoDao;
import modelos.Pedido;

public class TestePedidoDao {
    public static void main(String[] args) {
        Pedido Pedido = new Pedido();

        PedidoDao PedidoDao = new PedidoDao();

        //INSERCAO
        /*Pedido.setData(new Date());
        Pedido.setIdCliente(2);
        Pedido.setIdVendedor(1);
        Pedido.setObservacao("Um pedido mt legal");

        PedidoDao.inserir(Pedido);*/

        //UPDATE
        /*Optional<Pedido> PedidoOpt = PedidoDao.select(1);
        
        if(PedidoOpt.isPresent()) {
            Pedido = PedidoOpt.get();
            Pedido.setObservacao("segunda obs");
            PedidoDao.update(Pedido);
        }*/

        //DELETE
        Collection<Pedido> Pedidos = PedidoDao.selectAll();
        Pedidos.forEach(
            d -> PedidoDao.delete(d)
        );

    }
}