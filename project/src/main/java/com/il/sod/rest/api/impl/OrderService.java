package com.il.sod.rest.api.impl;

import com.il.sod.converter.services.OrderConverterService;
import com.il.sod.db.dao.impl.OrdersDAO;
import com.il.sod.db.model.entities.Order;
import com.il.sod.db.model.repositories.OrderRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.OrderMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.OrderDTO;
import com.il.sod.rest.dto.specifics.OrderTasksInfoDTO;
import com.il.sod.rest.dto.specifics.ServiceTasksInfoDTO;
import com.il.sod.rest.dto.specifics.UIOrderDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RolesAllowed("ADMIN")
@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/orders", tags = { "orders" })
public class OrderService extends AbstractServiceMutations {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	OrdersDAO ordersDAO;

	@Autowired
	OrderConverterService orderConverterService;

	@POST
	@ApiOperation(value = "Create Order Type", response = OrderDTO.class)
	public Response saveOrder(OrderDTO dto) throws SODAPIException {
		try {
			Order entity = OrderMapper.INSTANCE.map(dto);
			this.saveEntity(orderRepository, entity);
			dto = OrderMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@PUT
	@ApiOperation(value = "Update Order Type", response = OrderDTO.class)
	public Response updateOrder(OrderDTO dto) throws SODAPIException {
		return updateEntity(dto);
	}

	private Response updateEntity(OrderDTO dto) throws SODAPIException {
		try {
			Order entity = OrderMapper.INSTANCE.map(dto);
			this.updateEntity(orderRepository, entity);
			dto = OrderMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.OK);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@DELETE
	@ApiOperation(value = "Create Order Type", response = OrderDTO.class)
	public Response deleteOrder(OrderDTO dto) throws SODAPIException {
		try {
			Order entity = OrderMapper.INSTANCE.map(dto);
			this.deleteEntity(orderRepository, entity.getIdOrder());
			return castEntityAsResponse(
					new GeneralResponseMessage(true, "Entity deleted"),
					Response.Status.OK);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@GET
	@ApiOperation(value = "Get Order Type list", response = OrderDTO.class, responseContainer = "List")
	public Response getOrderList() throws SODAPIException {
		List<Order> rentityList = this.getEntityList(orderRepository);
		List<OrderDTO> list = rentityList.stream().map((i) -> {
			OrderDTO dto = orderConverterService.convert(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

	@GET
	@Path("/byStatus/{status}")
	@ApiOperation(value = "Get Order by status ", response = OrderDTO.class, responseContainer = "List")
	public Response getOrdersByStatus(@PathParam("status") int status) throws SODAPIException {
		List<OrderDTO> dto = ordersDAO.findByStatus(status).stream().map( o -> orderConverterService.convert(o)).collect(Collectors.toList());
		return castEntityAsResponse(dto, Response.Status.OK);
	}

	@GET
	@Path("/byId/{orderId}")
	@ApiOperation(value = "Get Order by id", response = OrderDTO.class)
	public Response getOrderById(@PathParam("orderId") String orderId) throws SODAPIException {
		OrderDTO dto = OrderMapper.INSTANCE.map(this.getEntity(orderRepository, Integer.valueOf(orderId)));
		return castEntityAsResponse(dto, Response.Status.OK);
	}

	@GET
	@Path("/tasks/{orderId}")
	@ApiOperation(value = "Get Task list for order", response = OrderTasksInfoDTO.class)
	public Response getOrderTaskInfo(@PathParam("orderId") String orderId) throws SODAPIException {
		OrderDTO dto = OrderMapper.INSTANCE.map(this.getEntity(orderRepository, Integer.valueOf(orderId)));
		OrderTasksInfoDTO result = new OrderTasksInfoDTO();
		result.setOrderTasks(dto.getOrderTasks());
		Set<ServiceTasksInfoDTO> services = dto.getServices().stream().map(i->{
			ServiceTasksInfoDTO r = new ServiceTasksInfoDTO();
			r.setIdService(i.getIdService());
			r.setServiceTasks(i.getServiceTasks());
			return r;}).collect(Collectors.toSet());
		result.setServices(services);
		return castEntityAsResponse(result, Response.Status.OK);
	}

	@Deprecated
	@GET
	@Path("/forEdit/{orderId}")
	@ApiOperation(value = "Get Order in Edit object mode.", response = OrderTasksInfoDTO.class)
	public Response getOrder4Edit(@PathParam("orderId") String orderId) throws SODAPIException {
		UIOrderDTO result = orderConverterService.convert2UI(this.getEntity(orderRepository, Integer.valueOf(orderId)));
		return castEntityAsResponse(result, Response.Status.OK);
	}

}
