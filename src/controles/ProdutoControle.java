package controles;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

import dao.ProdutoDao;
import modelos.Produto;

public class ProdutoControle {
    public static Collection<Map<String, String>> listarProdutos() {
        ProdutoDao produtoDao = new ProdutoDao();
        return produtoDao.selectAll().stream()
                                    .map(Produto::toMap)
                                    .collect(Collectors.toCollection(LinkedList::new));
    }
}