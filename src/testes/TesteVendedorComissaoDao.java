package testes;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import dao.VendedorComissaoDao;
import modelos.VendedorComissao;

public class TesteVendedorComissaoDao {
    public static void main(String[] args) {
        VendedorComissao VendedorComissao = new VendedorComissao();

        VendedorComissaoDao VendedorComissaoDao = new VendedorComissaoDao();

        //INSERCAO
        /*VendedorComissao.setPercentual(0.3);
        VendedorComissao.setValor(200.0);
        VendedorComissao.setIdPedido(1);
        VendedorComissao.setIdVendedor(1);

        VendedorComissaoDao.inserir(VendedorComissao);*/

        //UPDATE
        /*Optional<VendedorComissao> VendedorComissaoOpt = VendedorComissaoDao.select(1);
        if(VendedorComissaoOpt.isPresent()) {
            VendedorComissao = VendedorComissaoOpt.get();
            VendedorComissao.setValor(1000.0);
            VendedorComissaoDao.update(VendedorComissao);
        }*/

        //DELETE
        Collection<VendedorComissao> VendedorComissaos = VendedorComissaoDao.selectAll();
        VendedorComissaos.forEach(
            vc -> VendedorComissaoDao.delete(vc)
        );

    }
}