/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.agendamedica.dao;

import br.ufscar.dc.agendamedica.beans.Paciente;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author luanbatista
 */
@RequestScoped
public class PacienteDAO implements Serializable {
    private final static String CRIAR_PACIENTE_SQL = "insert into paciente"
            + " (cpf, nome, senha, telefone, sexo, datanasc)"
            + " values (?,?,?,?,?,?)";

    private final static String BUSCAR_PACIENTE_SQL = "select"
            + " cpf, nome, senha, telefone, sexo, datanasc"
            + " from paciente"
            + " where cpf=?";
    
    private final static String LOGIN_PACIENTE_SQL = "select"
            + " cpf, nome, senha, telefone, sexo, datanasc"
            + " from paciente"
            + " where cpf=?"
            + " and senha=?";
    
    @Resource(name = "jdbc/AgendaLocal")
    DataSource dataSource;
    
    public Paciente gravarPaciente(Paciente p) throws SQLException, NamingException {
        
        java.sql.Date data = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            data = new java.sql.Date(format.parse(p.getDatanasc()).getTime());
        } catch(Exception e) {
            e.getStackTrace();
            return null;
        }
        
        
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(CRIAR_PACIENTE_SQL);) {
            
            ps.setString(1, p.getCpf());
            ps.setString(2, p.getNome());
            ps.setString(3, p.getSenha());
            ps.setString(4, p.getTelefone());
            ps.setInt(5, p.getSexo());
            ps.setDate(6, data);
            ps.execute();

        }
        return p;
    }

    public Paciente buscarPaciente(String cpf) throws SQLException, NamingException {
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(BUSCAR_PACIENTE_SQL)) {
            ps.setString(1, cpf);

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                Paciente p = new Paciente();
                p.setCpf(rs.getString("cpf"));
                p.setNome(rs.getString("nome"));
                p.setSenha(rs.getString("senha"));
                p.setTelefone(rs.getString("telefone"));
                p.setSexo(rs.getInt("sexo"));
                p.setDatanasc(rs.getString("datanasc"));
                return p;
            }
        }
    }
    
    public Paciente loginPaciente(String cpf, String senha) throws SQLException, NamingException {
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(LOGIN_PACIENTE_SQL)) {
            ps.setString(1, cpf.trim());
            ps.setString(2, senha.trim());

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                Paciente p = new Paciente();
                p.setCpf(rs.getString("cpf"));
                p.setNome(rs.getString("nome"));
                p.setSenha(rs.getString("senha"));
                p.setTelefone(rs.getString("telefone"));
                p.setSexo(rs.getInt("sexo"));
                p.setDatanasc(rs.getString("datanasc"));
                return p;
            } catch (Exception e) {
                //e.printStackTrace();
                return null;
            }
        }catch(Exception e) {
            //e.printStackTrace();
            return null;
        }
    }
}
