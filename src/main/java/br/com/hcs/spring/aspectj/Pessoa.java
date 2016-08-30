package br.com.hcs.spring.aspectj;

import java.io.Serializable;

public class Pessoa implements Serializable {
    private static final long serialVersionUID = 4605123174584124550L;
    private String nome;

    public Pessoa() {}
    
    public Pessoa(String nome) {
        this.nome = nome;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
