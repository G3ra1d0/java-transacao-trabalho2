package modelos;

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

    @Override
    public String toString() {
        return "Vendedor [id=" + id + ", nome=" + nome + ", percentualComissao=" + percentualComissao + "]";
    }
    
}