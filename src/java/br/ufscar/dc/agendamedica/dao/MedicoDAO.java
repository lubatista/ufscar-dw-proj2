/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.agendamedica.dao;

import br.ufscar.dc.agendamedica.beans.Medico;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author luanbatista
 */
@RequestScoped
public class MedicoDAO implements Serializable {
    private final static String CRIAR_MEDICO_SQL = "insert into medico"
            + " (crm, nome, senha, especialidade)"
            + " values (?,?,?,?)";

    private final static String BUSCAR_MEDICO_SQL = "select"
            + " crm, nome, senha, especialidade"
            + " from medico"
            + " where crm=?";
    
    private final static String LOGIN_MEDICO_SQL = "select"
            + " crm, nome, senha, especialidade"
            + " from medico"
            + " where crm=?"
            + " and senha=?";
    
    private final static String LISTAR_TODOS_MEDICO_SQL = "select"
            + " crm, nome, senha, especialidade"
            + " from medico";
    
    private final static String LISTAR_ESPECIALIDADE_MEDICO_SQL = "select"
            + " crm, nome, senha, especialidade"
            + " from medico"
            + " where especialidade = ?";
    
    
    @Resource(name = "jdbc/AgendaLocal")
    DataSource dataSource;

    public Medico gravarMedico(Medico m) throws SQLException, NamingException {
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(CRIAR_MEDICO_SQL);) {
            
            ps.setString(1, m.getCrm());
            ps.setString(2, m.getNome());
            ps.setString(3, m.getSenha());
            ps.setString(4, m.getEspecialidade());
            ps.execute();

        }
        return m;
    }

    public Medico buscarMedico(String crm) throws SQLException, NamingException {
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(BUSCAR_MEDICO_SQL)) {
            ps.setString(1, crm.trim());

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                Medico m = new Medico();
                m.setCrm(rs.getString("crm"));
                m.setNome(rs.getString("nome"));
                m.setSenha(rs.getString("senha"));
                m.setEspecialidade(rs.getString("especialidade"));
                return m;
            } catch (Exception e) {
                //e.printStackTrace();
                return null;
            }
        }catch(Exception e) {
            //e.printStackTrace();
            return null;
        }
    }
    
    public Medico loginMedico(String crm, String senha) throws SQLException, NamingException {
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(LOGIN_MEDICO_SQL)) {
            ps.setString(1, crm.trim());
            ps.setString(2, senha.trim());

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                Medico m = new Medico();
                m.setCrm(rs.getString("crm"));
                m.setNome(rs.getString("nome"));
                m.setSenha(rs.getString("senha"));
                m.setEspecialidade(rs.getString("especialidade"));
                return m;
            } catch (Exception e) {
                //e.printStackTrace();
                return null;
            }
        }catch(Exception e) {
            //e.printStackTrace();
            return null;
        }
    }
    
    public List<Medico> listarTodosMedicos() throws SQLException, NamingException {
        List<Medico> ret = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(LISTAR_TODOS_MEDICO_SQL)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Medico m = new Medico();
                    m.setCrm(rs.getString("crm"));
                    m.setNome(rs.getString("nome"));
                    m.setEspecialidade(rs.getString("especialidade"));
                    ret.add(m);
                }
            }
        }
        return ret;
    }
    
    public List<Medico> listarMedicosEspecialidade(String especialidade) throws SQLException, NamingException {
        List<Medico> ret = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(LISTAR_ESPECIALIDADE_MEDICO_SQL)) {

            ps.setString(1, especialidade);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Medico m = new Medico();
                    m.setCrm(rs.getString("crm"));
                    m.setNome(rs.getString("nome"));
                    m.setEspecialidade(rs.getString("especialidade"));
                    ret.add(m);
                }
            }
        }
        return ret;
    }
}
