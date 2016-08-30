package br.com.hcs.spring.aspectj.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import br.com.hcs.spring.aspectj.Pessoa;

public class DAOPessoas {
    public void persistir(Pessoa[] pessoas, File arquivo) throws IOException {
        if (pessoas == null || pessoas.length == 0) {
            throw new IllegalArgumentException("Lista de pessoas nula ou vazia.");
        }
        
        if (arquivo == null) {
            throw new IllegalArgumentException("Arquivo nulo passado como parâmetro.");
        }
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(pessoas);
        }
    }
    
    public Pessoa[] ler(File arquivo) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (Pessoa[]) ois.readObject();
        } catch (ClassNotFoundException cnfe) {
            throw new IOException("Erro ao ler o arquivo. ClassNotFoundException", cnfe);
        }
    }
}
