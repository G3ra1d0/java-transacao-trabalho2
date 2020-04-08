package modelos;

public class VendedorComissao {
    private int id;
    private Double percentual;
    private Double valor;
    private int idVendedor;
    private int idPedido;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getPercentual() {
        return percentual;
    }

    public void setPercentual(Double percentual) {
        this.percentual = percentual;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    @Override
    public String toString() {
        return "VendedorComissao [id=" + id + ", idPedido=" + idPedido + ", idVendedor=" + idVendedor + ", percentual="
                + percentual + ", valor=" + valor + "]";
    }
    
}