package com.treinamento.hepta.rest;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.treinamento.hepta.entity.Funcionario;
import com.treinamento.hepta.persistence.FuncionarioDAO;

@Path("/funcionarios")
public class FuncionarioService {

	Connection conn = null;

	private FuncionarioDAO dao;

	public FuncionarioService() {
		dao = new FuncionarioDAO();
	}

	@Path("/salvar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	public Response FuncionarioCreate(Funcionario Funcionario) {
		try {
			dao.save(Funcionario);
			return Response.status(Status.CREATED).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Path("/listar")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response FuncionarioRead() {
		List<Funcionario> funcionarios = new ArrayList<>();
		try {
			funcionarios = dao.getFuncionarios();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Falha ao listar os funcionarios").build();
		}

		GenericEntity<List<Funcionario>> entity = new GenericEntity<List<Funcionario>>(funcionarios) {
		};
		return Response.status(Status.OK).entity(entity).build();
	}
	@Path("/listar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response SetorFind(@PathParam("id") Integer  id_funcionario) {
        List<Funcionario> funcionarios = new ArrayList<>();
        try {
            funcionarios = dao.getFind(id_funcionario);
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Falha ao listar setor pela id").build();
        }

        GenericEntity<List<Funcionario>> entity = new GenericEntity<List<Funcionario>>(funcionarios) {
        };
        return Response.status(Status.OK).entity(entity).build();
    }
	
	 @Path("/buscar/{nome}")
	    @Produces(MediaType.APPLICATION_JSON)
	    @GET
	    public Response SetorFindLike(@PathParam("nome")String nome) {
	        List<Funcionario> funcionarios = new ArrayList<>();
	        try {
	            funcionarios = dao.getLike(nome);
	        } catch (Exception e) {
	            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Falha ao buscar os funcionarios").build();
	        }

	        GenericEntity<List<Funcionario>> entity = new GenericEntity<List<Funcionario>>(funcionarios) {
	        };
	        return Response.status(Status.OK).entity(entity).build();
	    }

	@Path("/editar/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@PUT
	public Response FuncionarioUpdate(@PathParam("id") Integer id, Funcionario funcionario) {
		try {
			dao.update(funcionario);
			return Response.status(Status.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao tentar atualizar um funcionario")
					.build();
		}
	}

	@Path("/excluir/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@DELETE
	public Response FuncionarioDelete(@PathParam("id") Integer id) {
		try {
			dao.delete(id);
			return Response.status(Status.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao tentar remover um funcionario")
					.build();
		}
	}

}
