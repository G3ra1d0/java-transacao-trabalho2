package controles;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

import dao.ClienteDao;
import modelos.Cliente;

public class ClienteControle {
    public static Collection<Map<String, String>> listarClientes() {
        ClienteDao clienteDao = new ClienteDao();
        return clienteDao.selectAll().stream()
                                    .map(Cliente::toMap)
                                    .collect(Collectors.toCollection(LinkedList::new));
    }
}