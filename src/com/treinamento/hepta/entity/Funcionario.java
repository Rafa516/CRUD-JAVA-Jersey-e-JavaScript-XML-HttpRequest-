package com.treinamento.hepta.entity;

import java.sql.Date;

public class Funcionario {

	private int id_funcionario;
	private int id_setor;
	private String setor;
	private String nome;
	private Date aniversario;
	private String foto;

	public int getId_funcionario() {
		return id_funcionario;
	}

	public void setId_funcionario(int id_funcionario) {
		this.id_funcionario = id_funcionario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

    public String getSetor() {
        return setor;
    }

    public void setSetor(String string) {
        this.setor = string;
    }

    public int getId_setor() {
        return id_setor;
    }

    public void setId_setor(int id_setor) {
        this.id_setor = id_setor;
    }

    public Date getAniversario() {
        return aniversario;
    }

    public void setAniversario(Date aniversario) {
        this.aniversario = aniversario;
    }

  

 
	

}
