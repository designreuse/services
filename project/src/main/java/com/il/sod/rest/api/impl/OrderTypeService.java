package com.il.sod.rest.api.impl;

import com.il.sod.db.model.entities.OrderType;
import com.il.sod.db.model.repositories.OrderTypeRepository;
import com.il.sod.db.model.repositories.ServiceTypeRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.OrderMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.OrderTypeDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RolesAllowed("ADMIN")
@Path("/orders/order-type")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/orders/order-type", tags = { "orders" })
public class OrderTypeService extends AbstractServiceMutations {

	@Autowired
	OrderTypeRepository orderTypeRepository;

	@Autowired
	ServiceTypeRepository serviceTypeRepository;

	@POST
	@ApiOperation(value = "Create Order Type", response = OrderTypeDTO.class)
	public Response saveOrderType(OrderTypeDTO dto) throws SODAPIException {
		try {
			OrderType entity = OrderMapper.INSTANCE.map(dto);
			this.saveEntity(orderTypeRepository, entity);
			dto = OrderMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@PUT
	@ApiOperation(value = "Update Order Type", response = OrderTypeDTO.class)
	public Response updateOrderType(OrderTypeDTO dto) throws SODAPIException {
		return updateEntity(dto);
	}

	private Response updateEntity(OrderTypeDTO dto) throws SODAPIException {
		try {
			OrderType entity = OrderMapper.INSTANCE.map(dto);
			this.updateEntity(orderTypeRepository, entity);
			dto = OrderMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.OK);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Delete Task", response = GeneralResponseMessage.class)
	public Response deleteItem(@PathParam("id") String id) throws SODAPIException {
		OrderType entity = orderTypeRepository.findOne(Integer.valueOf(id));
		if (entity == null){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Item not found");
		}
		this.deleteEntity(orderTypeRepository, entity.getId());
		return castEntityAsResponse(new GeneralResponseMessage(true, "Entity deleted"),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get Order Type list", response = OrderTypeDTO.class, responseContainer = "List")
	public Response getOrderTypeList() throws SODAPIException {
		List<OrderType> rentityList = this.getEntityList(orderTypeRepository);
		List<OrderTypeDTO> list = rentityList.stream().map((i) -> {
			OrderTypeDTO dto = OrderMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}
}
