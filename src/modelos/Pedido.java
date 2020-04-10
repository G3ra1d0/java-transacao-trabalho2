package modelos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Pedido {
    private int id;
    private String data;
    private String observacao;
    private int idCliente;
    private int idVendedor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(Date data) {
        DateFormat formatoData = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.data = formatoData.format(data);
    }

    public void setUltimaCompra(String data) {
        this.data = data;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    @Override
    public String toString() {
        return "Pedido [data=" + data + ", id=" + id + ", idCliente=" + idCliente + ", idVendedor=" + idVendedor
                + ", observacao=" + observacao + "]";
    }
    
}