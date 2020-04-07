/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.transacao.trabalho2;

import Dao.FabricaConexao;
import java.sql.Connection;

/**
 *
 * @author g3ra1d0
 */
public class JavaTransacaoTrabalho2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Connection cnx = FabricaConexao.GeraConexaoCustomizada(1);
        System.out.println("Oi");
    }
    
}
