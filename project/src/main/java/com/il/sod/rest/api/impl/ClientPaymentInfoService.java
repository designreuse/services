package com.il.sod.rest.api.impl;

import com.il.sod.db.dao.IClientPaymentInfoDAO;
import com.il.sod.db.model.entities.Client;
import com.il.sod.db.model.entities.ClientPaymentInfo;
import com.il.sod.db.model.repositories.ClientPaymentInfoRepository;
import com.il.sod.db.model.repositories.ClientRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.PaymentMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.ClientPaymentInfoDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
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
@Path("/clients/client-payment-info")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/clients/client-payment-info", tags = { "payment" })
public class ClientPaymentInfoService extends AbstractServiceMutations {

	@Autowired
	ClientPaymentInfoRepository clientPaymentInfoRepository;

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	IClientPaymentInfoDAO clientPaymentInfoDAO;

	@POST
	@ApiOperation(value = "Create Payment Info", response = ClientPaymentInfoDTO.class)
	public Response saveClientPaymentInfo(ClientPaymentInfoDTO dto) throws SODAPIException {
		try {
			ClientPaymentInfo entity = PaymentMapper.INSTANCE.map(dto);
			this.saveEntity(clientPaymentInfoRepository, entity);
			dto = PaymentMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@PUT
	@ApiOperation(value = "Update Payment Info", response = ClientPaymentInfoDTO.class)
	public Response updateClientPaymentInfo(ClientPaymentInfoDTO dto) throws SODAPIException {
		return updateEntity(dto);
	}

	private Response updateEntity(ClientPaymentInfoDTO dto) throws SODAPIException {
		try {
			ClientPaymentInfo entity = PaymentMapper.INSTANCE.map(dto);
			this.updateEntity(clientPaymentInfoRepository, entity);
			dto = PaymentMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.OK);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@DELETE
	@ApiOperation(value = "Create Payment Info", response = ClientPaymentInfoDTO.class)
	public Response deleteItemPaymentInfo(ClientPaymentInfoDTO dto) throws SODAPIException {
		try {
			ClientPaymentInfo entity = PaymentMapper.INSTANCE.map(dto);
			this.deleteEntity(clientPaymentInfoRepository, entity.getIdClientPaymentInfo());
			return castEntityAsResponse(new GeneralResponseMessage(true, "Entity deleted"),
					Response.Status.OK);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}


	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Delete ClientPaymentInfo", response = GeneralResponseMessage.class)
	public Response deleteEntity(@PathParam("id") String id) throws SODAPIException {
		ClientPaymentInfo entity = clientPaymentInfoRepository.findOne(Integer.valueOf(id));
		if (entity == null){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "ClientPaymentInfo not found");
		}
		Client cEntity = entity.getClient();
		cEntity.removeClientPaymentInfo(entity);
		this.saveEntity(clientRepository, cEntity);
		return castEntityAsResponse(new GeneralResponseMessage(true, "Entity deleted"),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get Payment Info list", response = ClientPaymentInfoDTO.class, responseContainer = "List")
	public Response getClientPaymentInfoList(@QueryParam("idClient") String idClient) throws SODAPIException {
		List<ClientPaymentInfo> entityList = null;
		if (!StringUtils.isEmpty(idClient)){
			if (!NumberUtils.isDigits(idClient)){
				throw new SODAPIException(Response.Status.BAD_REQUEST, "Not a valid id " + idClient);
			}
			entityList = clientPaymentInfoDAO.findByIdClient(Integer.valueOf(idClient));
		}else{
			entityList = this.getEntityList(clientPaymentInfoRepository);
		}

		List<ClientPaymentInfoDTO> list = entityList.stream().map(PaymentMapper.INSTANCE::map).collect(Collectors.toList());
		return castEntityAsResponse(list);

	}

	@GET
	@Path("/byId/{idClientPaymentInfo}")
	@ApiOperation(value = "Get Payment Info list", response = ClientPaymentInfoDTO.class)
	public Response getPaymentInfoById(@PathParam("idClientPaymentInfo") String idClientPaymentInfo) throws SODAPIException {
		if (!NumberUtils.isDigits(idClientPaymentInfo)){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Not a valid id " + idClientPaymentInfo);
		}
		ClientPaymentInfo pi = this.getEntity(clientPaymentInfoRepository, Integer.valueOf(idClientPaymentInfo));
		ClientPaymentInfoDTO piDto = PaymentMapper.INSTANCE.map(pi);
		return castEntityAsResponse(piDto);
	}

}
