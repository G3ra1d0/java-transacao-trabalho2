package modelos;

import java.util.HashMap;
import java.util.Map;

public class Vendedor {
    private int id;
    private String nome;
    private Double percentualComissao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPercentualComissao() {
        return percentualComissao;
    }

    public void setPercentualComissao(Double percentualComissao) {
        this.percentualComissao = percentualComissao;
    }

    public Map<String,String> toMap() {
        Map<String, String> vendedorMap = new HashMap<>();
        
        vendedorMap.put("id", Integer.toString(this.id));
        vendedorMap.put("nome", this.nome);
        vendedorMap.put("percentual", Double.toString(this.percentualComissao));

        return vendedorMap;
    }

    @Override
    public String toString() {
        return "Vendedor [id=" + id + ", nome=" + nome + ", percentualComissao=" + percentualComissao + "]";
    }
    
}