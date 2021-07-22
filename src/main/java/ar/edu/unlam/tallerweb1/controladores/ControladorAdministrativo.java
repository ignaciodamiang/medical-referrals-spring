package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.servicios.ServicioCentroMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ControladorAdministrativo {

    ServicioCentroMedico servicioCentroMedico;

    @Autowired
    public ControladorAdministrativo(ServicioCentroMedico servicioCentroMedico){
        this.servicioCentroMedico=servicioCentroMedico;
    }

    @RequestMapping(path = "graficasAdministrativo", method = RequestMethod.GET)
    public ModelAndView graficasAdministrativo(HttpServletRequest request){
        if(!request.getSession().getAttribute("ROL").equals("Administrativo")){
            return new ModelAndView("redirect:/router");
        }
        ModelMap map = new ModelMap();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaMax = sdf.format(new Date());


        return null;
    }
}
