package br.com.treinaweb.ediaristas.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.treinaweb.ediaristas.web.dtos.FlashMessage;
import br.com.treinaweb.ediaristas.web.dtos.UsuarioCadastroForm;
import br.com.treinaweb.ediaristas.web.services.WebUsuarioService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("admin/usuarios")
public class UsuarioController {

    @Autowired
    private WebUsuarioService service;
    
    @GetMapping
    public ModelAndView buscarTodos(){
        var modelAndView = new ModelAndView("admin/usuario/lista");

        modelAndView.addObject("usuarios", service.buscarTodos());

        return modelAndView;
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar(){
        var modelAndView = new ModelAndView("admin/usuario/cadastro-form");

        modelAndView.addObject("cadastroForm", new UsuarioCadastroForm());
        
        return modelAndView;
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@Valid @ModelAttribute("cadastroForm") UsuarioCadastroForm cadastroForm, BindingResult result, RedirectAttributes attrs ){
        if(result.hasErrors()){
            return "admin/ususario/cadastro-form";
        }
        service.cadastrar(cadastroForm);

        attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Usuario cadastrar com sucessos!"));
        return "redirect:/admin/usuarios";
    }


    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id){
        var modelAndView = new ModelAndView("admin/usuario/edicao-form");

        modelAndView.addObject("edicaoForm", service.buscarPorId(id));
        
        return modelAndView;
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes attrs){
        service.excluirPorId(id);
        attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Usuario excluido com sucesso!"));
        return "redirect:/admin/usuarios";
    }

}
