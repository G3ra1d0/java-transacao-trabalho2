package testes;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import dao.PedidoProdutoDao;
import modelos.PedidoProduto;

public class TestePedidoProdutoDao {
    public static void main(String[] args) {
        PedidoProduto PedidoProduto = new PedidoProduto();

        PedidoProdutoDao PedidoProdutoDao = new PedidoProdutoDao();

        //INSERCAO
        /*PedidoProduto.setIdPedido(1);
        PedidoProduto.setIdProduto(1);
        PedidoProduto.setQuantidade(10.0);
        PedidoProduto.setValorUni(1.0);
        PedidoProduto.calcValorTotal();

        PedidoProdutoDao.inserir(PedidoProduto);*/

        //UPDATE
        /*Optional<PedidoProduto> PedidoProdutoOpt = PedidoProdutoDao.select(1);
        
        if(PedidoProdutoOpt.isPresent()) {
            PedidoProduto = PedidoProdutoOpt.get();
            PedidoProduto.setValorUni(2.0);
            PedidoProduto.calcValorTotal();
            PedidoProdutoDao.update(PedidoProduto);
        }*/

        //DELETE
        Collection<PedidoProduto> PedidoProdutos = PedidoProdutoDao.selectAll();
        PedidoProdutos.forEach(
            pp -> PedidoProdutoDao.delete(pp)
        );

    }
}