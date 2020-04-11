package testes;

import controles.PedidoControle;

public class VisaoCarregarPedido {
    public static void main(String[] args) {
        PedidoControle.listarPedidos().forEach(mapa -> System.out.println(mapa.get("Cliente")));
    }
}