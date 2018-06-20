/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.agendamedica.views;

import br.ufscar.dc.agendamedica.beans.Consulta;
import br.ufscar.dc.agendamedica.dao.ConsultaDAO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

/**
 *
 * @author duduoliverio
 */
@Named
@RequestScoped
public class ListaConsultas implements Serializable {

    List<Consulta> listaConsulta;
    
    @Inject
    ConsultaDAO consultaDAO;
    
    @Inject
    Acesso acesso;
        
    public List<Consulta> getListaConsulta() {
        return listaConsulta;
    }

    public void setListaConsulta(List<Consulta> listaConsultas) {
        this.listaConsulta = listaConsultas;
    }

    public String verConsultasPaciente() throws SQLException, NamingException {
        
        if(acesso.dadosUsuario.getDocumento() == null)
                acesso.dadosUsuario.setDocumento("41543102832");
        
        listaConsulta = consultaDAO.listarConsultaPaciente(acesso.dadosUsuario.getDocumento());

        return "listaConsulta";
    }
    
    public String verConsultasMedico() throws SQLException, NamingException {
        
        if(acesso.dadosUsuario.getDocumento() == null)
                acesso.dadosUsuario.setDocumento("123456");
        
        System.out.println("Documento Medico: "  + acesso.dadosUsuario.getDocumento());
        
        listaConsulta = consultaDAO.listarConsultaMedico(acesso.dadosUsuario.getDocumento());

        return "listaConsulta";
    }

}
