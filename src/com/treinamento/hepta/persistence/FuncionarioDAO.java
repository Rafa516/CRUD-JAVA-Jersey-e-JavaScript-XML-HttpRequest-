package com.treinamento.hepta.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import com.treinamento.hepta.entity.Funcionario;
import com.treinamento.hepta.connection.ConnectionDB;

public class FuncionarioDAO {
    
    java.util.Date utilDate = new java.util.Date();

    Connection conn = null;
    PreparedStatement pstm = null;

    // CREATE - SALVAR
    public void save(Funcionario funcionario) throws SQLException {

        String sql = "INSERT INTO tb_funcionarios(id_setor,nome,aniversario,foto)" + "VALUES (?,?,?,?)";

        try {
            conn = ConnectionDB.ConnectionToMySQL();
            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, funcionario.getId_setor());
            pstm.setString(2, funcionario.getNome());
            pstm.setDate(3, new java.sql.Date(funcionario.getAniversario().getTime()));
            pstm.setString(4, funcionario.getFoto());

            pstm.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

    }

    // READ - VISUALIZAR
    public List<Funcionario> getFuncionarios() throws SQLException {
        String sql = "SELECT * FROM tb_funcionarios tf  INNER JOIN tb_setores ts  USING(id_setor)"
                + " WHERE tf.id_setor = ts.id_setor ORDER BY tf.id_funcionario";

        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        ResultSet rset = null;

        try {
            conn = ConnectionDB.ConnectionToMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            rset = pstm.executeQuery();

            while (rset.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId_funcionario(rset.getInt("id_funcionario"));
                funcionario.setId_setor(rset.getInt("id_setor"));
                funcionario.setNome(rset.getString("nome"));
                funcionario.setAniversario(rset.getDate("aniversario"));
                funcionario.setSetor(rset.getString("nome_setor"));
                funcionario.setFoto(rset.getString("foto"));

                funcionarios.add(funcionario);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return funcionarios;

    }

    // READ - VISUALIZAR PELA ID
    public List<Funcionario> getFind(int id_funcionario) throws SQLException {
        String sql = "SELECT * FROM tb_funcionarios tf  INNER JOIN tb_setores ts  USING(id_setor)"
                + " WHERE tf.id_funcionario = ?";
      
        
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        ResultSet rset = null;
    
        try {
            conn = ConnectionDB.ConnectionToMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setInt(1, id_funcionario);
            rset = pstm.executeQuery();

            while (rset.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId_funcionario(rset.getInt("id_funcionario"));
                funcionario.setId_setor(rset.getInt("id_setor"));
                funcionario.setNome(rset.getString("nome"));
                funcionario.setAniversario(rset.getDate("aniversario"));
                funcionario.setSetor(rset.getString("nome_setor"));
                funcionario.setFoto(rset.getString("foto"));
                funcionarios.add(funcionario);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return funcionarios;

    }

    // FIND - BUSCAR %%
    public List<Funcionario> getLike(String nome) throws SQLException {
        String sql = "SELECT * from tb_funcionarios tf INNER JOIN tb_setores  ts USING(id_setor)  WHERE nome LIKE '%"+ nome + "%' " ;
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        ResultSet rset = null;

        try {
            conn = ConnectionDB.ConnectionToMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId_funcionario(rset.getInt("id_funcionario"));
                funcionario.setId_setor(rset.getInt("id_setor"));
                funcionario.setAniversario(rset.getDate("aniversario"));
                funcionario.setSetor(rset.getString("nome_setor"));
                funcionario.setFoto(rset.getString("foto"));
                funcionario.setNome(rset.getString("nome"));
                funcionarios.add(funcionario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return funcionarios;

    }

    // UPDATE - ATUALIZAR
    public void update(Funcionario funcionario) throws SQLException {

        String sql = "UPDATE tb_funcionarios SET id_setor = ? ,nome = ? , aniversario =?, foto =? "
                + " WHERE id_funcionario = ?";

        try {
            conn = ConnectionDB.ConnectionToMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setInt(1, funcionario.getId_setor());
            pstm.setString(2, funcionario.getNome());
            pstm.setDate(3, new java.sql.Date(funcionario.getAniversario().getTime()));
            pstm.setString(4, funcionario.getFoto());
            pstm.setInt(5, funcionario.getId_funcionario());

            pstm.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

    }

    // DELETE - DELETAR
    public void delete(int id_funcionario) throws SQLException {

        String sql = "DELETE FROM tb_funcionarios  WHERE id_funcionario = ?";

        try {
            conn = ConnectionDB.ConnectionToMySQL();
            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, id_funcionario);

            pstm.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

    }

}
