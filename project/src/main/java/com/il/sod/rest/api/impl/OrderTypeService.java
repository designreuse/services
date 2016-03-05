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

import com.il.sod.db.model.entities.OrderType;
import com.il.sod.db.model.repositories.OrderTypeRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.OrderTypeDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@RolesAllowed("ADMIN")
@Path("/order-type")
@Produces(MediaType.APPLICATION_JSON)
// @Api(value = "/order-type", tags = { "order" })
public class OrderTypeService extends AbstractServiceMutations {
	@Autowired
	OrderTypeRepository orderTypeRepository;

	@PUT
	@ApiOperation(value = "Create Order Type", response = OrderTypeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveOrderType(OrderTypeDTO dto) throws SODAPIException {
		try {
			OrderType entity = converter.map(dto, OrderType.class);
			this.saveEntity(orderTypeRepository, entity);
			dto = converter.map(entity, OrderTypeDTO.class);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@POST
	@ApiOperation(value = "Update Order Type", response = OrderTypeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateOrderType(OrderTypeDTO dto) throws SODAPIException {
		try {
			OrderType entity = converter.map(dto, OrderType.class);
			this.updateEntity(orderTypeRepository, entity);
			dto = converter.map(entity, OrderTypeDTO.class);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@DELETE
	@ApiOperation(value = "Create Order Type", response = OrderTypeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteOrderType(OrderTypeDTO dto) throws SODAPIException {
		try {
			OrderType entity = converter.map(dto, OrderType.class);
			this.deleteEntity(orderTypeRepository, entity.getIdOrderType());
			return castEntityAsResponse(
					GeneralResponseMessage.getInstance().success().setMessage("Order deleted"),
					Response.Status.OK);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@GET
	@ApiOperation(value = "Get Order Type list", response = OrderTypeDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getOrderTypeList() throws SODAPIException {
		List<OrderType> rentityList = this.getEntityList(orderTypeRepository);
		List<OrderTypeDTO> list = rentityList.stream().map((i) -> {
			OrderTypeDTO dto = converter.map(i, OrderTypeDTO.class);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}