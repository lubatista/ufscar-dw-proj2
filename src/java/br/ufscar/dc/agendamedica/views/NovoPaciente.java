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
public class NovoPaciente implements Serializable{
    
    @Inject
    Acesso acesso;

    @Inject
    PacienteDAO pacienteDao;

    Paciente dadosPaciente;
    
    public NovoPaciente() {
        System.out.println("Criou nova consulta NovoPaciente");
        zerar();
    }
    
    private void zerar() {
        dadosPaciente = new Paciente();
    }

    public Paciente getDadosPaciente() {
        return dadosPaciente;
    }
    
    public void setDadosPaciente(Paciente dadosPaciente) {
        this.dadosPaciente = dadosPaciente;
    }
    
    public String salvar() {
        try {
            pacienteDao.gravarPaciente(dadosPaciente);
                 
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(NovoPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "index";
    }  
    
}
