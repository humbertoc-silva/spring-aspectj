package br.com.hcs.spring.aspectj.aspect;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AdviceAroundProfilador {
    private List<Long> tempos = new ArrayList<>();
    
    public List<Long> getTempos() {
        return tempos;
    }
    
    @Around("execution(* br.com.hcs.spring.aspectj.dao.DAOPessoas.*(..))")
    public Object profilar(ProceedingJoinPoint joinPoint) throws Throwable {
        Long momentoInicial = System.currentTimeMillis();
        
        Object resultado = joinPoint.proceed();
        
        Long tempo = System.currentTimeMillis() - momentoInicial;
        tempos.add(tempo);
        System.out.println("Tempo para executar: " + tempo + "ms");
        
        return resultado;
    }
}
