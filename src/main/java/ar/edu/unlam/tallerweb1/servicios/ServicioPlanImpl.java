package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Cobertura;
import ar.edu.unlam.tallerweb1.modelo.Plan;
import ar.edu.unlam.tallerweb1.modelo.PlanPaciente;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPlan;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPlanPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("servicioPlan")
@Transactional
public class ServicioPlanImpl implements ServicioPlan{
    RepositorioPlan repositorioPlan;
    RepositorioPlanPaciente repositorioPlanPaciente;

    @Autowired
    public ServicioPlanImpl(RepositorioPlan repositorioPlan, RepositorioPlanPaciente repositorioPlanPaciente){
        this.repositorioPlan=repositorioPlan;
        this.repositorioPlanPaciente = repositorioPlanPaciente;

    }

    @Override
    public Cobertura obtenerCoberturaPorPlan(Long id) {
        Plan plan = repositorioPlan.obtenerPlanPorId(id);
        return plan.getCobertura();
    }

    @Override
    public List<Cobertura> obetenerCoberturasPaciente(Long idPaciente) {
        List<Cobertura> coberturas = new ArrayList<Cobertura>();
        List<PlanPaciente> planPacientes = repositorioPlanPaciente.obtenerPlanesPorPaciente(idPaciente);

        for (PlanPaciente plan : planPacientes){
            coberturas.add(obtenerCoberturaPorPlan(plan.getIdPlan().getId()));
        }
        return coberturas;
    }
}
