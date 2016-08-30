package br.com.hcs.spring.aspectj.teste;

import java.io.File;
import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.hcs.spring.aspectj.Pessoa;
import br.com.hcs.spring.aspectj.aspect.AdviceAroundProfilador;
import br.com.hcs.spring.aspectj.dao.DAOPessoas;

public class App {
    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        
        DAOPessoas dao = (DAOPessoas) context.getBean("daoPessoas");
        
        // Deve ser retornado o proxy, e não o objeto alvo. 
        assert dao.getClass().equals(DAOPessoas.class) == false;
        
        File arquivo = new File("pessoas");
        Pessoa pessoa1 = new Pessoa("Nome1");
        Pessoa pessoa2 = new Pessoa("Nome2");
        Pessoa pessoa3 = new Pessoa("Nome3");
        Pessoa[] pessoas = {pessoa1, pessoa2, pessoa3};
        
        dao.persistir(pessoas, arquivo);
        
        AdviceAroundProfilador aroundProfilador = context.getBean(AdviceAroundProfilador.class);
        
        // Verificando se o tempo de execução foi capturado.
        assert !aroundProfilador.getTempos().isEmpty();
        
        // Testando o advice after throwing.
        try {
            dao.persistir(pessoas, null);
        } catch (IllegalArgumentException iae) {
            assert iae.getMessage().equals("Arquivo nulo passado como parâmetro.");
        }
    }
}
