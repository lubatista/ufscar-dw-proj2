/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.agendamedica.views;

import br.ufscar.dc.agendamedica.beans.Medico;
import br.ufscar.dc.agendamedica.beans.Paciente;
import br.ufscar.dc.agendamedica.beans.Usuario;
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
public class Acesso implements Serializable{
    
    @Inject
    PacienteDAO pacienteDao;
    
    @Inject
    MedicoDAO medicoDao;
    
    Usuario dadosUsuario;

    public Acesso() {
        System.out.println("Criou nova sessão");
        dadosUsuario = new Usuario();
    }
    
    public Usuario getDadosUsuario() {
        return dadosUsuario;
    }
    
    public void setDadosUsuario(Usuario dadosUsuario) {
        this.dadosUsuario = dadosUsuario;
    }
    
    public String valida() {
        try {
            
            System.out.println("DADOS: " + dadosUsuario.getDocumento());
            System.out.println("DADOS: " + dadosUsuario.getSenha());
            
            if(dadosUsuario.getDocumento().equals("admin") && dadosUsuario.getSenha().equals("admin")) {
                dadosUsuario.setNome("Admin");
                dadosUsuario.setTipo(0);
                return "index";
            }
            
            Medico medico = medicoDao.loginMedico(dadosUsuario.getDocumento(), dadosUsuario.getSenha());
            
            if(medico != null) {
                dadosUsuario.setDocumento(medico.getCrm());
                dadosUsuario.setNome(medico.getNome());
                dadosUsuario.setTipo(1);
                
                return "index";
            }
            
            Paciente paciente = pacienteDao.loginPaciente(dadosUsuario.getDocumento(), dadosUsuario.getSenha());
            
            if(paciente != null) {
                dadosUsuario.setDocumento(paciente.getCpf());
                dadosUsuario.setNome(paciente.getNome());
                dadosUsuario.setTipo(2);

                System.out.println("DADOS 2: " + dadosUsuario.getDocumento());
                System.out.println("DADOS 2: " + dadosUsuario.getSenha());
                
                return "index";
            }

        } catch (SQLException ex) {
            Logger.getLogger(Acesso.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(Acesso.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "login";
    }  
}
