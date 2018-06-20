/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.agendamedica.dao;

import br.ufscar.dc.agendamedica.beans.Consulta;
import br.ufscar.dc.agendamedica.beans.Medico;
import br.ufscar.dc.agendamedica.beans.Paciente;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author luanbatista
 */
@RequestScoped
public class ConsultaDAO implements Serializable {
    
    @Inject 
    MedicoDAO medicoDao;
    
    @Inject 
    PacienteDAO pacienteDao;
    
    private final static String CRIAR_CONSULTA_SQL = "insert into consulta"
            + " (crm, cpf, data)"
            + " values (?,?,?)";

    private final static String BUSCAR_CONSULTA_MEDICO_SQL = "select"
            + " c.crm, c.cpf, c.data, m.nome as medico, p.nome as paciente"
            + " from consulta c"
            + " inner join medico m on m.crm = c.crm"
            + " inner join paciente p on p.cpf = c.cpf"
            + " where c.crm=?";
    
    private final static String BUSCAR_CONSULTA_PACIENTE_SQL = "select"
            + " c.crm, c.cpf, c.data, m.nome as medico, p.nome as paciente"
            + " from consulta c"
            + " inner join medico m on m.crm = c.crm"
            + " inner join paciente p on p.cpf = c.cpf"
            + " where c.cpf=?";
    
    private final static String VERIFICA_CONSULTA_PACIENTE_SQL = "select"
            + " COUNT(*) as total"
            + " from consulta"
            + " where data = ?"
            + " and cpf=?";
    
    private final static String VERIFICA_CONSULTA_MEDICO_SQL = "select"
            + " COUNT(*) as total"
            + " from consulta"
            + " where data = ?"
            + " and crm=?";
    
    @Resource(name = "jdbc/AgendaLocal")
    DataSource dataSource;
    
    public Consulta gravarConsulta(Consulta c) throws SQLException, NamingException {
        
        java.sql.Date data = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            data = new java.sql.Date(format.parse(c.getData()).getTime());
        } catch(Exception e) {
            e.getStackTrace();
            return null;
        }
            
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(CRIAR_CONSULTA_SQL);) {
            
            ps.setString(1, c.getMedico().getCrm());
            ps.setString(2, c.getPaciente().getCpf());
            ps.setDate(3, data);
            ps.execute();

        }
        return c;
    }
    
    public Boolean buscarPacienteConsulta(String cpf, String data) throws SQLException, NamingException {
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(VERIFICA_CONSULTA_PACIENTE_SQL)) {
            ps.setString(1, data.trim());
            ps.setString(2, cpf.trim());

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                if(rs.getInt("total") > 0)
                   return true;
                return false;
            } catch (Exception e) {
                e.getStackTrace();
                return null;
            }
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }
    
    public Boolean buscarMedicoConsulta(String crm, String data) throws SQLException, NamingException {
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(VERIFICA_CONSULTA_MEDICO_SQL)) {
            ps.setString(1, data.trim());
            ps.setString(2, crm.trim());

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                if(rs.getInt("total") > 0)
                   return true;
                return false;
            } catch (Exception e) {
                e.getStackTrace();
                return null;
            }
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }
    
    public List<Consulta> listarConsultaPaciente(String cpf) throws SQLException, NamingException {
        List<Consulta> ret = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(BUSCAR_CONSULTA_PACIENTE_SQL)) {

            ps.setString(1, cpf);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Medico medico = medicoDao.buscarMedico(rs.getString("crm"));
                    Paciente paciente = pacienteDao.buscarPaciente(rs.getString("cpf"));
                    
                    Consulta c = new Consulta();
                    c.setData(rs.getString("data"));
                    c.setMedico(medico);
                    c.setPaciente(paciente);
                    ret.add(c);
                }
            }
        }
        return ret;
    }
    
    public List<Consulta> listarConsultaMedico(String crm) throws SQLException, NamingException {
        List<Consulta> ret = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(BUSCAR_CONSULTA_MEDICO_SQL)) {

            ps.setString(1, crm);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Medico medico = medicoDao.buscarMedico(rs.getString("crm"));
                    Paciente paciente = pacienteDao.buscarPaciente(rs.getString("cpf"));
                    
                    Consulta c = new Consulta();
                    c.setData(rs.getString("data"));
                    c.setMedico(medico);
                    c.setPaciente(paciente);
                    ret.add(c);
                }
            }
        }
        return ret;
    }
    
    /*
    public List<NovaConsultaFormBean> buscarConsultaMedico(String crm) throws SQLException, NamingException {
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(BUSCAR_CONSULTA_MEDICO_SQL)) {
            ps.setString(1, crm);
            
            List<NovaConsultaFormBean> ret = new ArrayList<>();
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    NovaConsultaFormBean c = new NovaConsultaFormBean();
                    c.setCrm(rs.getString("crm"));
                    c.setCpf(rs.getString("cpf"));
                    c.setData(rs.getString("data"));
                    c.setMedico(rs.getString("medico"));
                    c.setPaciente(rs.getString("paciente"));
                    ret.add(c);
                }
                return ret;
            }
        }
    }
    
    public List<NovaConsultaFormBean> buscarConsultaPaciente(String cpf) throws SQLException, NamingException {
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(BUSCAR_CONSULTA_PACIENTE_SQL)) {
            ps.setString(1, cpf);
            System.out.println("CPF: " + cpf );
            List<NovaConsultaFormBean> ret = new ArrayList<>();
            
            try (ResultSet rs = ps.executeQuery()) {
                     
                while (rs.next()) {
                    NovaConsultaFormBean c = new NovaConsultaFormBean();
                    c.setCrm(rs.getString("crm"));
                    c.setCpf(rs.getString("cpf"));
                    c.setData(rs.getString("data"));
                    c.setMedico(rs.getString("medico"));
                    c.setPaciente(rs.getString("paciente"));
                    ret.add(c);
                }
                return ret;
            }
        }
    }*/
}
