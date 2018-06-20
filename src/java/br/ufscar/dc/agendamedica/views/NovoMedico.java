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
public class NovoMedico implements Serializable{
    
    @Inject
    Acesso acesso;

    @Inject
    MedicoDAO medicoDao;

    Medico dadosMedico;
    
    public NovoMedico() {
        System.out.println("Criou nova consulta NovoMedico");
        zerar();
    }
    
    private void zerar() {
        dadosMedico = new Medico();
    }

    public Medico getDadosMedico() {
        return dadosMedico;
    }
    
    public void setDadosMedico(Medico dadosMedico) {
        this.dadosMedico = dadosMedico;
    }
    
    public String salvar() {
        try {
            medicoDao.gravarMedico(dadosMedico);
                 
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(NovoMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "index";
    }  
    
}
