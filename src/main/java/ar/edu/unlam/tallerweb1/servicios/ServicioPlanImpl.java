package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Cobertura;
import ar.edu.unlam.tallerweb1.modelo.Paciente;
import ar.edu.unlam.tallerweb1.modelo.Plan;
import ar.edu.unlam.tallerweb1.modelo.PlanPaciente;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPaciente;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPlan;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPlanPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service("servicioPlan")
@Transactional
public class ServicioPlanImpl implements ServicioPlan{
    RepositorioPlan repositorioPlan;
    RepositorioPlanPaciente repositorioPlanPaciente;
    RepositorioPaciente repositorioPaciente;

    @Autowired
    public ServicioPlanImpl(RepositorioPlan repositorioPlan, RepositorioPlanPaciente repositorioPlanPaciente, RepositorioPaciente repositorioPaciente){
        this.repositorioPlan=repositorioPlan;
        this.repositorioPlanPaciente = repositorioPlanPaciente;
        this.repositorioPaciente = repositorioPaciente;

    }

    @Override
    public Cobertura obtenerCoberturaPorPlan(Long id) {
        Plan plan = repositorioPlan.obtenerPlanPorId(id);
        return plan.getCobertura();
    }

    @Override
    public HashSet<Cobertura> obetenerCoberturasPaciente(Long idPaciente) {
        HashSet<Cobertura> coberturas = new HashSet<Cobertura>();
        Paciente paciente = repositorioPaciente.obtenerPacientePorId(idPaciente);
        List<PlanPaciente> planPacientes = repositorioPlanPaciente.obtenerPlanesPorPaciente(paciente);

        for (PlanPaciente plan : planPacientes){
            coberturas.add(obtenerCoberturaPorPlan(plan.getIdPlan().getId()));
        }
        return coberturas;
    }
}
