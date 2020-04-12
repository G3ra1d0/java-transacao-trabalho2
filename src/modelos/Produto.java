package modelos;

import java.util.HashMap;
import java.util.Map;

public class Produto {
    private int id;
    private String descricao;
    private Double saldo;
    private String unidade;
    private Double quantidade;
    private Double preco;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
    
    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        if(quantidade > this.saldo) {
            throw new RuntimeException("O saldo de produtos é menor que a quantidade solicitada");
        }
        this.saldo = this.saldo - quantidade;
        this.quantidade = quantidade;
    }

    public Map<String,String> toMap() {
        Map<String, String> produtoMap = new HashMap<>();

        produtoMap.put("id", Integer.toString(this.id));
        produtoMap.put("descricao", this.descricao);
        produtoMap.put("saldo", Double.toString(this.saldo));
        produtoMap.put("unidade", this.unidade);
        produtoMap.put("preco", Double.toString(this.preco));

        return produtoMap;
    }

    @Override
    public String toString() {
        return "Produto [descricao=" + descricao + ", id=" + id + ", saldo=" + saldo + ", unidade=" + unidade + "]";
    }
    
}