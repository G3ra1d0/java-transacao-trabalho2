package testes;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import dao.ProdutoDao;
import modelos.Produto;

public class TesteProdutoDao {
    public static void main(String[] args) {
        Produto Produto = new Produto();

        ProdutoDao ProdutoDao = new ProdutoDao();

        //INSERCAO
        Produto.setDescricao("Feijao");
        Produto.setSaldo(1.0);
        Produto.setUnidade("Kg");
        Produto.setPreco(1.0);

        ProdutoDao.inserir(Produto);

        //UPDATE
        /*Optional<Produto> ProdutoOpt = ProdutoDao.select(3);
        
        if(ProdutoOpt.isPresent()) {
            Produto = ProdutoOpt.get();
            Produto.setSaldo(10.5);
            ProdutoDao.update(Produto);
        }*/

        //DELETE
        /*Collection<Produto> Produtos = ProdutoDao.selectAll();
        Produtos.forEach(
            p -> ProdutoDao.delete(p)
        );*/

    }
}