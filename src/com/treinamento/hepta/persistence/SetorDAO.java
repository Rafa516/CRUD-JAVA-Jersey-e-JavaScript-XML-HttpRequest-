package com.treinamento.hepta.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.treinamento.hepta.connection.ConnectionDB;

import com.treinamento.hepta.entity.Setor;

public class SetorDAO {

    Connection conn = null;
    PreparedStatement pstm = null;

    // CREATE
    public void save(Setor setor) throws SQLException {

        String sql = "INSERT INTO tb_setores(nome_setor)" + "VALUES (?)";

        try {
            conn = ConnectionDB.ConnectionToMySQL();
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, setor.getNome_setor());

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
    public List<Setor> getSetores() throws SQLException {
        String sql = "SELECT * FROM tb_setores";
        List<Setor> setores = new ArrayList<Setor>();
        ResultSet rset = null;

        try {
            conn = ConnectionDB.ConnectionToMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            rset = pstm.executeQuery();

            while (rset.next()) {
                Setor setor = new Setor();
                setor.setId_setor(rset.getInt("id_setor"));
                setor.setNome_setor(rset.getString("nome_setor"));
                setores.add(setor);

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
        return setores;

    }

    // READ - VISUALIZAR PELA ID
    public List<Setor> getFind(int id_setor) throws SQLException {
        String sql = "SELECT * FROM tb_setores WHERE id_setor = ?";
        List<Setor> setores = new ArrayList<Setor>();
        ResultSet rset = null;

        try {
            conn = ConnectionDB.ConnectionToMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setInt(1, id_setor);
            rset = pstm.executeQuery();

            while (rset.next()) {
                Setor setor = new Setor();
                setor.setId_setor(rset.getInt("id_setor"));
                setor.setNome_setor(rset.getString("nome_setor"));
                setores.add(setor);

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
        return setores;

    }

    // FIND - BUSCAR %%
    public List<Setor> getLike(String nome_setor) throws SQLException {
        String sql = "SELECT * from tb_setores WHERE nome_setor LIKE '%" + nome_setor + "%'";
        List<Setor> setores = new ArrayList<Setor>();
        ResultSet rset = null;

        try {
            conn = ConnectionDB.ConnectionToMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {
                Setor setor = new Setor();
                setor.setId_setor(rset.getInt("id_setor"));
                setor.setNome_setor(rset.getString("nome_setor"));
                setores.add(setor);

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

        return setores;

    }

    // UPDATE - ATUALIZAR
    public void update(Setor setor) {

        String sql = "UPDATE tb_setores SET  nome_setor = ?  WHERE id_setor = ?";

        try {
            conn = ConnectionDB.ConnectionToMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1, setor.getNome_setor());
            pstm.setInt(2, setor.getId_setor());
            pstm.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    // DELETE - DELETAR
    public void delete(int id_setor) throws SQLException {

        String sql = "DELETE FROM tb_setores WHERE id_setor = ?";
        try {
            conn = ConnectionDB.ConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id_setor);
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
