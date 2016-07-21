package com.il.sod.rest.api.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.il.sod.db.model.entities.Spec;
import com.il.sod.db.model.repositories.SpecRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.SpecsMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.SpecDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@RolesAllowed("ADMIN")
@Path("/spec")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/spec", tags = { "specs" })
public class SpecService extends AbstractServiceMutations {

	@Autowired
	SpecRepository specRepository;

	@POST
	@ApiOperation(value = "Create Spec", response = SpecDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveSpec(SpecDTO dto) throws SODAPIException {
		try {
			Spec entity = SpecsMapper.INSTANCE.map(dto);
			System.out.println("****************");
			System.out.println("spec: " + this.castEntityAsString(entity));
			System.out.println("****************");
			this.saveEntity(specRepository, entity);
			dto = SpecsMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@Deprecated
	@PUT
	@ApiOperation(value = "Update Spec", response = SpecDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateSpec(SpecDTO dto) throws SODAPIException {
		try {
			Spec entity = SpecsMapper.INSTANCE.map(dto);
			this.updateEntity(specRepository, entity);
			dto = SpecsMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@PUT
	@Path("/{id}")
	@ApiOperation(value = "Update Spec", response = SpecDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateSpecById(@PathParam("id") String id, SpecDTO dto) throws SODAPIException {
		try {
			Spec entity = SpecsMapper.INSTANCE.map(dto);
			this.updateEntity(specRepository, entity);
			dto = SpecsMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Delete", response = GeneralResponseMessage.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteItem(@PathParam("id") String id) throws SODAPIException {
		Spec entity = specRepository.findOne(Integer.valueOf(id));
		if (entity == null){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Item not found");
		}
		this.deleteEntity(specRepository, entity.getId());
		return castEntityAsResponse(GeneralResponseMessage.getInstance().success().setMessage("Item deleted"),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get Spec list", response = SpecDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getAllSpecList() throws SODAPIException {
		List<Spec> entityList = this.getEntityList(specRepository); 
		List<SpecDTO> list = entityList.stream().map((i) -> {
			SpecDTO dto = SpecsMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}
	
	@GET
	@Path("/by/notPrimary")
	@ApiOperation(value = "Get Spec list Not Primary", response = SpecDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getSpecList() throws SODAPIException {
		List<Spec> entityList = specRepository.findAllNotPrimary(); 
		List<SpecDTO> list = entityList.stream().map((i) -> {
			SpecDTO dto = SpecsMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}
	
	@GET
	@Path("/by/primary")
	@ApiOperation(value = "Get Spec list Primary", response = SpecDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getSpecListByPrimary() throws SODAPIException {
		List<Spec> entityList = specRepository.findAllPrimary(); 
		List<SpecDTO> list = entityList.stream().map((i) -> {
			SpecDTO dto = SpecsMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
