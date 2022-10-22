package com.treinamento.hepta.rest;

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

import com.treinamento.hepta.entity.Setor;
import com.treinamento.hepta.persistence.SetorDAO;

@Path("/setores")
public class SetorService {

    private SetorDAO dao;

    public SetorService() {
        dao = new SetorDAO();
    }

    @Path("/salvar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response SetorCreate(Setor setor) {
        try {
            dao.save(setor);
            return Response.status(Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response SetorRead() {
        List<Setor> setores = new ArrayList<>();
        try {
            setores = dao.getSetores();
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Falha ao listar os setores").build();
        }

        GenericEntity<List<Setor>> entity = new GenericEntity<List<Setor>>(setores) {
        };
        return Response.status(Status.OK).entity(entity).build();
    }

    

    @Path("/editar/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PUT
    public Response setorUpdate(@PathParam("id") Integer id, Setor setor) {
        try {
            dao.update(setor);
            return Response.status(Status.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao tentar atualizar um setor")
                    .build();
        }
    }
    
    @Path("/listar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response SetorFind(@PathParam("id") Integer  id_setor) {
        List<Setor> setores = new ArrayList<>();
        try {
            setores = dao.getFind(id_setor);
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Falha ao listar setor pela id").build();
        }

        GenericEntity<List<Setor>> entity = new GenericEntity<List<Setor>>(setores) {
        };
        return Response.status(Status.OK).entity(entity).build();
    }
    
    
    @Path("/buscar/{nome_setor}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response SetorFindLike(@PathParam("nome_setor")String nome_setor) {
        List<Setor> setores = new ArrayList<>();
        try {
            setores = dao.getLike(nome_setor);
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Falha ao listar os setores").build();
        }

        GenericEntity<List<Setor>> entity = new GenericEntity<List<Setor>>(setores) {
        };
        return Response.status(Status.OK).entity(entity).build();
    }
    

    @Path("/excluir/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @DELETE
    public Response SetorDelete(@PathParam("id") Integer id) {
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
