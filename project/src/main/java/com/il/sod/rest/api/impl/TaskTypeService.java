package com.il.sod.rest.api.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.il.sod.db.model.entities.TaskType;
import com.il.sod.db.model.repositories.TaskTypeRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.TaskTypeDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@RolesAllowed("ADMIN")
@Path("/task-type")
@Produces(MediaType.APPLICATION_JSON)
// @Api(value = "/task-type", tags = { "generic" })
public class TaskTypeService extends AbstractServiceMutations {
	@Autowired
	TaskTypeRepository taskTypeRepository;

	@PUT
	@ApiOperation(value = "Create Task Type", response = TaskTypeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveTaskType(TaskTypeDTO dto) throws SODAPIException {
		try {
			TaskType entity = converter.map(dto, TaskType.class);
			this.saveEntity(taskTypeRepository, entity);
			dto = converter.map(entity, TaskTypeDTO.class);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@POST
	@ApiOperation(value = "Update Task Type", response = TaskTypeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateTaskType(TaskTypeDTO dto) throws SODAPIException {
		try {
			TaskType entity = converter.map(dto, TaskType.class);
			this.updateEntity(taskTypeRepository, entity);
			dto = converter.map(entity, TaskTypeDTO.class);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@DELETE
	@ApiOperation(value = "Create Task Type", response = TaskTypeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteTaskType(TaskTypeDTO dto) throws SODAPIException {
		try {
			TaskType entity = converter.map(dto, TaskType.class);
			this.deleteEntity(taskTypeRepository, entity.getIdTaskType());
			return castEntityAsResponse(
					GeneralResponseMessage.getInstance().success().setMessage("Task deleted"),
					Response.Status.OK);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@GET
	@ApiOperation(value = "Get Task Type list", response = TaskTypeDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getTaskTypeList() throws SODAPIException {
		List<TaskType> rentityList = this.getEntityList(taskTypeRepository);
		List<TaskTypeDTO> list = rentityList.stream().map((i) -> {
			TaskTypeDTO dto = converter.map(i, TaskTypeDTO.class);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}