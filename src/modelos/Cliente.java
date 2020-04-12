package modelos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Cliente {
    private int id;
    private String nome;
    private String cpf;
    private String ultimaCompra;

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

    public String getUltimaCompra() {
        return ultimaCompra;
    }

    public void setUltimaCompra(Date ultimaCompra) {
        DateFormat formatoData = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.ultimaCompra = formatoData.format(ultimaCompra);
    }

    public void setUltimaCompra(String ultimaCompra) {
        this.ultimaCompra = ultimaCompra;
    }

    public Map<String,String> toMap() {
        Map<String, String> clienteMap = new HashMap<>();
        
        clienteMap.put("id", Integer.toString(this.id));
        clienteMap.put("nome", this.nome);
        clienteMap.put("cpf", this.cpf);
        clienteMap.put("ultimacompra", this.ultimaCompra);

        return clienteMap;
    }

	@Override
	public String toString() {
		return "Cliente [cpf=" + cpf + ", id=" + id + ", nome=" + nome + ", ultimaCompra=" + ultimaCompra + "]";
	}
    
}