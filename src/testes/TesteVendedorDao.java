package testes;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import dao.VendedorDao;
import modelos.Vendedor;

public class TesteVendedorDao {
    public static void main(String[] args) {
        Vendedor Vendedor = new Vendedor();

        VendedorDao VendedorDao = new VendedorDao();

        //INSERCAO
        /*Vendedor.setNome("Saulo");
        Vendedor.setPercentualComissao(0.5);

        VendedorDao.inserir(Vendedor);*/

        //UPDATE
        /*Optional<Vendedor> VendedorOpt = VendedorDao.select(1);
        if(VendedorOpt.isPresent()) {
            Vendedor = VendedorOpt.get();
            Vendedor.setNome("Jacinto");
            VendedorDao.update(Vendedor);
        }*/

        //DELETE
        /*Collection<Vendedor> Vendedors = VendedorDao.selectAll();
        Vendedors.forEach(
            v -> VendedorDao.delete(v)
        );*/

    }
}