package br.com.hcs.spring.aspectj.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AdviceAfterThrowingProfilador {
    @AfterThrowing(pointcut = "execution(* br.com.hcs.spring.aspectj.dao.DAOPessoas.*(..))", throwing = "iae")
    public void informar(IllegalArgumentException iae) {
        enviarEmail(iae);
    }
    
    private void enviarEmail(IllegalArgumentException ioe) {
        System.out.println("IllegalArgumentException capturada, enviando e-mail...");
    }
}
