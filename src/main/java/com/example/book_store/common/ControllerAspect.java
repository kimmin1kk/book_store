package com.example.book_store.common;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.security.Principal;

@Aspect
@Component
@RequiredArgsConstructor
public class ControllerAspect {

    @Pointcut("execution(* com.example.book_store.*.controller.*Controller.*(..))")
    public void controllerExcution() {
    }



    @Pointcut("args(model, principal, ..)")
    public void argsWithModelAndPrincipal(Model model, Principal principal) {
    }


    @Before(value = "controllerExcution() && argsWithModelAndPrincipal(model, principal)", argNames = "model, principal")
    public void applySomethingBeforeController(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
    }
}
