package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.RequerimientosMedicos;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioRequerimientosMedicos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("servicioRequerimientosMedicos")
@Transactional
public class ServicioRequerimientosMedicosImpl implements ServicioRequerimientosMedicos{

    private RepositorioRequerimientosMedicos repositorioRequerimientosMedicos;

    @Autowired
    public ServicioRequerimientosMedicosImpl(RepositorioRequerimientosMedicos repositorioRequerimientosMedicos){
        this.repositorioRequerimientosMedicos = repositorioRequerimientosMedicos;
    }

    @Override
    public void guardaRequerimientosMedicos(RequerimientosMedicos requerimientosMedicos) {
        repositorioRequerimientosMedicos.guardaRequerimientosMedicos(requerimientosMedicos);
    }

    @Override
    public void modificarRequerimientosMedicos(RequerimientosMedicos requerimientosMedicos) {
        repositorioRequerimientosMedicos.modificarRequerimientosMedicos(requerimientosMedicos);
    }
}
