package modelos;

import java.util.Date;

public class ProdutoMovimento {
    private int id;
    public enum Operacao {
        E("Entrada"),
        S("Saída");
     
        private String descricao;
     
        Operacao(String descricao) {
            this.descricao = descricao;
        }
     
        public String getDescricao() {
            return descricao;
        }
    }
    private Operacao tipo;
    private Date data;
    private String descricao;
    private int quantidade;
    private int idProduto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipo(Operacao tipo) {
        this.tipo = tipo;
    }

    public Date getData() {
        return data;
    }

    public Operacao getTipo() {
        return tipo;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    @Override
    public String toString() {
        return "ProdutoMovimento [data=" + data + ", descricao=" + descricao + ", id=" + id + ", idProduto=" + idProduto
                + ", quantidade=" + quantidade + ", tipo=" + tipo + "]";
    }
    
}