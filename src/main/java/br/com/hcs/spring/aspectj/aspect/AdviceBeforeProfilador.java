package br.com.hcs.spring.aspectj.aspect;

import java.io.File;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AdviceBeforeProfilador {
    @Before("execution(* br.com.hcs.spring.aspectj.dao.DAOPessoas.persistir(..))")
    public void coletarEstatisticas(JoinPoint joinPoint) {
        if (joinPoint.getArgs()[1] != null) {
            File arquivo = (File) joinPoint.getArgs()[1];
            obterEstatisticas(arquivo);
        }
    }
    
    private void obterEstatisticas(File arquivo) {
        System.out.println("Nome do arquivo: " + arquivo.getName());
    }
}
