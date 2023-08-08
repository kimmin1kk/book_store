package com.example.book_store.common;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.security.Principal;

@Aspect
@Component
@RequiredArgsConstructor
public class ControllerAspect {
    @Before(value = "execution(* com.example.book_store.*.controller.*Controller.*(..)) && args(model, principal, ..)", argNames = "model, principal")
    public void initializeDetails(Object model, Object principal) {
        Model m = (Model) model;
        Principal p = (Principal) principal;
        if (p != null) {
            m.addAttribute("username", p.getName());
        }
    }
}
