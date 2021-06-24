package ar.edu.unlam.tallerweb1.servicios;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCentroMedico;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPlanPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service("servicioCentroMedico")
@Transactional
public class ServicioCentroMedicoImpl implements ServicioCentroMedico {
    private RepositorioCentroMedico repositorioCentroMedico;
    private RepositorioPlanPaciente repositorioPlanPaciente;

    @Autowired
    public ServicioCentroMedicoImpl(RepositorioCentroMedico repositorioCentroMedico, RepositorioPlanPaciente repositorioPlanPaciente) { this.repositorioCentroMedico = repositorioCentroMedico;
        this.repositorioPlanPaciente = repositorioPlanPaciente;
    }

    @Override
    public List<CentroMedico> obtenerCentrosMedicos() {
        return repositorioCentroMedico.obtenerCentrosMedicos();
    }

    @Override
    public HashSet<CentroMedico> obtenerCentrosMedicosPorPaciente(Paciente paciente) {
        List<PlanPaciente> planes = repositorioPlanPaciente.obtenerPlanesPorPaciente(paciente);
            HashSet<CentroMedico> centrosMedicos = new HashSet<CentroMedico>();
            for (PlanPaciente planPaciente : planes){
                List<PlanCentroMedico> planesCentros = repositorioCentroMedico
                        .obtenerPlanesCentrosMedicosPorPlan(planPaciente.getIdPlan());

                for(PlanCentroMedico planCentroMedico : planesCentros){
                    centrosMedicos.add(planCentroMedico.getIdCentroMedico());
                }
            }

        return centrosMedicos;
    }

    @Override
    public CentroMedico obtenerCentroMedicoPorId(Long id) throws Exception {
        if (repositorioCentroMedico.obtenerCentroMedicoPorId(id) != null) {
            return repositorioCentroMedico.obtenerCentroMedicoPorId(id);
        }
        throw new Exception("Hubo un error al buscar los datos");
    }

    @Override
    public HashSet<CentroMedico> centrosMedicosQuePoseenRequerimientosMedicos(HashSet<CentroMedico> centrosMedicos, RequerimientosMedicos requerimientosMedicos) {
        HashSet<CentroMedico> centrosHabilitados = new HashSet<>();
        RequerimientosMedicos requerimientosMedicos1;
        for (CentroMedico centroMedico : centrosMedicos){
            requerimientosMedicos1 = centroMedico.getRequerimientosMedicos();
            if(requerimientosMedicos.getTomografo()){
                if(!requerimientosMedicos1.getTomografo()){
                    continue;
                }
            }
            if(requerimientosMedicos.getCardiologoSeGuardia()){
                if(!requerimientosMedicos1.getCardiologoSeGuardia()){
                    continue;
                }
            }
            if(requerimientosMedicos.getCirujanoDeGuardia()){
                if(!requerimientosMedicos1.getCirujanoDeGuardia()){
                    continue;
                }
            }
            if(requerimientosMedicos.getTraumatologoDeguardia()){
                if(!requerimientosMedicos1.getTraumatologoDeguardia()){
                    continue;
                }
            }
            centrosHabilitados.add(centroMedico);
        }
        return null;
    }
}
