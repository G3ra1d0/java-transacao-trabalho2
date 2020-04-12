package modelos;

import java.util.HashMap;
import java.util.Map;

public class Vendedor extends Pessoa {
    private Double percentualComissao;

    public Double getPercentualComissao() {
        return percentualComissao;
    }

    public void setPercentualComissao(Double percentualComissao) {
        this.percentualComissao = percentualComissao;
    }

    public Map<String,String> toMap() {
        Map<String, String> vendedorMap = new HashMap<>();
        
        vendedorMap.put("id", Integer.toString(super.getId()));
        vendedorMap.put("nome", super.getNome());
        vendedorMap.put("percentual", Double.toString(this.percentualComissao));

        return vendedorMap;
    }

    @Override
    public String toString() {
        return "Vendedor [id=" + super.getId() + ", nome=" + super.getNome() + ", percentualComissao=" + percentualComissao + "]";
    }
    
}