package net.bpogroup.horario.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Christianl3aron on 30/08/2015.
 */
@Controller
public class IndexController {

    @RequestMapping(value = {"/","inicio"})
    public ModelAndView index(){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }
}