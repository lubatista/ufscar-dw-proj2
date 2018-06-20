/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.agendamedica.views;

import br.ufscar.dc.agendamedica.beans.Consulta;
import br.ufscar.dc.agendamedica.beans.Medico;
import br.ufscar.dc.agendamedica.beans.Paciente;
import br.ufscar.dc.agendamedica.beans.Usuario;
import br.ufscar.dc.agendamedica.dao.ConsultaDAO;
import br.ufscar.dc.agendamedica.dao.MedicoDAO;
import br.ufscar.dc.agendamedica.dao.PacienteDAO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

/**
 *
 * @author luanbatista
 */
@Named
@SessionScoped
public class NovaConsulta implements Serializable{
    
    @Inject
    Acesso acesso;
    
    @Inject
    ConsultaDAO consultaDao;
    
    @Inject
    MedicoDAO medicoDao;
    
    @Inject
    PacienteDAO pacienteDao;

    Consulta dadosConsulta;
    Usuario usuario;

    public NovaConsulta() {
        System.out.println("Criou nova consulta");
        zerar();
    }
    
    private void zerar() {
        dadosConsulta = new Consulta();
        dadosConsulta.setMedico(new Medico());
        dadosConsulta.setPaciente(new Paciente());
    }

    public Consulta getDadosConsulta() {
        return dadosConsulta;
    }
    
    public void setDadosConsulta(Consulta dadosConsulta) {
        this.dadosConsulta = dadosConsulta;
    }
    
    public String popula() {
        try {
            Medico medico = medicoDao.buscarMedico(dadosConsulta.getMedico().getCrm());
            
            dadosConsulta.setMedico(medico);
            
            if(acesso.dadosUsuario.getDocumento() == null)
                acesso.dadosUsuario.setDocumento("41543102832");
                
            Paciente paciente = pacienteDao.buscarPaciente(acesso.dadosUsuario.getDocumento());
            
            dadosConsulta.setPaciente(paciente);
                 
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(NovaConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "confirmaConsulta";
    }  
    
    public String salvar() {
        try {
            Medico medico = medicoDao.buscarMedico(dadosConsulta.getMedico().getCrm());
            
            dadosConsulta.setMedico(medico);
            
            if(acesso.dadosUsuario.getDocumento() == null)
                acesso.dadosUsuario.setDocumento("41543102832");
                
            Paciente paciente = pacienteDao.buscarPaciente(acesso.dadosUsuario.getDocumento());
            
            dadosConsulta.setPaciente(paciente);

            consultaDao.gravarConsulta(dadosConsulta);
                 
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(NovaConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "index";
    }  
    
    public String recomecar() {
        try {
            Medico medico = medicoDao.buscarMedico(dadosConsulta.getMedico().getCrm());
            
            dadosConsulta.setMedico(medico);
            
            if(acesso.dadosUsuario.getDocumento() == null)
                acesso.dadosUsuario.setDocumento("41543102832");
                
            Paciente paciente = pacienteDao.buscarPaciente(acesso.dadosUsuario.getDocumento());
            
            dadosConsulta.setPaciente(paciente);
            
                 
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(NovaConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "consultaForm";
    }  
}
