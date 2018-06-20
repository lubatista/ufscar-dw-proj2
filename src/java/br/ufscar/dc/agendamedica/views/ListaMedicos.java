/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.agendamedica.views;

import br.ufscar.dc.agendamedica.beans.Medico;
import br.ufscar.dc.agendamedica.dao.MedicoDAO;
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
public class ListaMedicos implements Serializable {

    List<Medico> listaMedicos;
    @Inject
    MedicoDAO medicoDAO;

    private List<Medico> medicosFiltrados;
        
    public List<Medico> getListaMedicos() {
        return listaMedicos;
    }

    public void setListaMedicos(List<Medico> listaMedicos) {
        this.listaMedicos = listaMedicos;
    }

    public String verTodosMedicos() throws SQLException, NamingException {
        listaMedicos = medicoDAO.listarTodosMedicos();
        return "listaMedicos";
    }

    public List<Medico> getMedicosFiltrados() {
        return medicosFiltrados;
    }

    public void setMedicosFiltrados(List<Medico> medicosFiltrados) {
        this.medicosFiltrados = medicosFiltrados;
    }

    /* public String verTodosMedicosEspecialidade(String especialidade) throws SQLException, NamingException {

        listaMedicos = medicoDAO.listarMedicosEspecialidade(especialidade);
        return "listaMedicos";
    }*/
}
