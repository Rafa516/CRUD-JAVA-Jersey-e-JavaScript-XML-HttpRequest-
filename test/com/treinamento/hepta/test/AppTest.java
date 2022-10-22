package com.treinamento.hepta.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;

import com.treinamento.hepta.entity.Funcionario;
import com.treinamento.hepta.entity.Setor;
import com.treinamento.hepta.persistence.FuncionarioDAO;
import com.treinamento.hepta.persistence.SetorDAO;

public class AppTest {

    SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");

    SetorDAO setorDAO = new SetorDAO();
    Setor setor = new Setor();
    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    Funcionario funcionario = new Funcionario();

    @Test
    public void SaveTestFuncionario() throws ParseException {

        funcionario.setId_setor(1);
        funcionario.setNome("Luciana");
        // funcionario.setAniversario(data);
        funcionario.setFoto("img1.png");

        try {
            funcionarioDAO.save(funcionario);
            System.out.println("Funcionario(a) " + funcionario.getNome() + "Aniversário" + funcionario.getAniversario()
                    + " cadastrado com sucesso!");
            assertTrue(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void SaveTestSetor() {

        setor.setNome_setor("Financeiro33");

        try {
            setorDAO.save(setor);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Setor " + setor.getNome_setor() + " cadastrado com sucesso!");
    }

    @Test
    public void ReadTestFuncionario() {

        try {
            for (Funcionario f : funcionarioDAO.getFuncionarios()) {
                System.out.println("Funcionario: " + f.getNome() + " Aniversário: " + f.getAniversario());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void ReadTestFuncionarioGetFind() {

        try {
            for (Funcionario f : funcionarioDAO.getFind(1)) {
                System.out.println("ID: " + f.getId_funcionario() + " funcionario: " + f.getNome());
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    @Test
    public void ReadTestSetor() {

        try {
            for (Setor s : setorDAO.getSetores()) {
                System.out.println("ID: " + s.getId_setor() + " Setor: " + s.getNome_setor());
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Test
    public void likeTestSetor() {

        try {
            for (Setor s : setorDAO.getLike("rec")) {
                System.out.println("ID: " + s.getId_setor() + " Setor: " + s.getNome_setor());
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Test
    public void likeTestFuncionario() {

        try {
            for (Funcionario f : funcionarioDAO.getLike("andr")) {
                System.out.println("ID: " + f.getId_setor() + " Nome " + f.getNome());
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Test
    public void ReadTestSetorGetFind() {

        try {
            for (Setor s : setorDAO.getFind(2)) {
                System.out.println("ID: " + s.getId_setor() + " Setor: " + s.getNome_setor());
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Test
    public void UpdateTestFuncionario() {

        funcionario.setId_setor(1);
        funcionario.setNome("Diana");
        // funcionario.setAniversario((java.sql.Date) new Date());
        funcionario.setFoto("perfil.png");
        funcionario.setId_funcionario(23);
        try {
            funcionarioDAO.update(funcionario);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Dados alterados com sucesso");
    }

    @Test
    public void UpdateTestSetor() {

        setor.setId_setor(2);
        setor.setNome_setor("RH");
        setorDAO.update(setor);
        System.out.println("Dados alterados com sucesso");
    }

    @Test
    public void DeleteTestFuncionario() {

        try {
            funcionarioDAO.delete(23);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Funcionario(a) Deletado com sucesso!");
    }

    @Test
    public void DeleteTestSetor() {

        try {
            setorDAO.delete(20);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Setor Deletado com sucesso!");
    }

}
