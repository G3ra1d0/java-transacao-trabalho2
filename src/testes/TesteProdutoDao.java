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
        Produto.setSaldo(2.5);
        Produto.setUnidade("Kg");

        ProdutoDao.inserir(Produto);

        //UPDATE
        /*Optional<Produto> ProdutoOpt = ProdutoDao.select(2);
        
        if(ProdutoOpt.isPresent()) {
            Produto = ProdutoOpt.get();
            Produto.setSaldo(5.5);
            ProdutoDao.update(Produto);
        }*/

        //DELETE
        /*Collection<Produto> Produtos = ProdutoDao.selectAll();
        Produtos.forEach(
            p -> ProdutoDao.delete(p)
        );*/

    }
}