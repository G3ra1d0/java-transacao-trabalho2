package testes;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import dao.ClienteDao;
import modelos.Cliente;

public class TesteClienteDao {
    public static void main(String[] args) {
        Cliente cliente = new Cliente();

        ClienteDao clienteDao = new ClienteDao();

        //INSERCAO
        /*cliente.setCpf("0808080");
        cliente.setNome("Mateus Cirino");

        clienteDao.inserir(cliente);*/

        //UPDATE
        /*Optional<Cliente> clienteOpt = clienteDao.select(1);
        if(clienteOpt.isPresent()) {
            cliente = clienteOpt.get();
            cliente.setUltimaCompra(new Date());
            clienteDao.update(cliente);
        }*/

        //DELETE
        /*Collection<Cliente> clientes = clienteDao.selectAll();
        clientes.forEach(
            c -> clienteDao.delete(c)
        );*/

    }
}