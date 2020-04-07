package modelos;

import java.util.Date;

public class Cliente {
    private int id;
    private String nome;
    private String cpf;
    private Date ultimaCompra;

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getUltimaCompra() {
        return ultimaCompra;
    }

    public void setUltimaCompra(Date ultimaCompra) {
        this.ultimaCompra = ultimaCompra;
    }

	@Override
	public String toString() {
		return "Cliente [cpf=" + cpf + ", id=" + id + ", nome=" + nome + ", ultimaCompra=" + ultimaCompra + "]";
	}
    
}