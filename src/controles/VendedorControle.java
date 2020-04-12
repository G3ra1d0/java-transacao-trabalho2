package controles;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

import dao.VendedorDao;
import modelos.Vendedor;

public class VendedorControle {
    public static Collection<Map<String, String>> listarVendedores() {
        VendedorDao vendedorDao = new VendedorDao();
        return vendedorDao.selectAll().stream()
                                    .map(Vendedor::toMap)
                                    .collect(Collectors.toCollection(LinkedList::new));
    }
}