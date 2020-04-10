package testes;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import dao.ProdutoMovimentoDao;
import modelos.ProdutoMovimento;

public class TesteProdutoMovimentoDao {
    public static void main(String[] args) {
        ProdutoMovimento produtoMovimento = new ProdutoMovimento();

        ProdutoMovimentoDao ProdutoMovimentoDao = new ProdutoMovimentoDao();

        //INSERCAO
        /*produtoMovimento.setData(new Date());
        produtoMovimento.setDescricao("Movimentacao de entrada");
        produtoMovimento.setTipo(ProdutoMovimento.Operacao.E);
        produtoMovimento.setIdProduto(3);
        produtoMovimento.setQuantidade(30);

        ProdutoMovimentoDao.inserir(produtoMovimento);*/

        //UPDATE
        /*Optional<ProdutoMovimento> ProdutoMovimentoOpt = ProdutoMovimentoDao.select(6);
        
        if(ProdutoMovimentoOpt.isPresent()) {
            produtoMovimento = ProdutoMovimentoOpt.get();
            produtoMovimento.setTipo(ProdutoMovimento.Operacao.S);
            ProdutoMovimentoDao.update(produtoMovimento);
        }*/

        //DELETE
        Collection<ProdutoMovimento> ProdutoMovimentos = ProdutoMovimentoDao.selectAll();
        ProdutoMovimentos.forEach(
            pm -> ProdutoMovimentoDao.delete(pm)
        );

    }
}