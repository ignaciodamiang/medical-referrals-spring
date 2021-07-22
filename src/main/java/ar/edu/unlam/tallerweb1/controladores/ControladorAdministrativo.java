package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorAdministrativo {

    @Autowired
    public ControladorAdministrativo(){

    }

    @RequestMapping(path = "graficasAdministrativo", method = RequestMethod.GET)
    public ModelAndView graficasAdministrativo(HttpServletRequest request){
        if(!request.getSession().getAttribute("ROL").equals("Administrativo")){
            return new ModelAndView("redirect:/router");
        }
        return null;
    }
}
