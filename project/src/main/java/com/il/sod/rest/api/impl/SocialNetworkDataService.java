package com.il.sod.rest.api.impl;

import com.il.sod.db.model.entities.SocialNetworkData;
import com.il.sod.db.model.repositories.SocialNetworkDataRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.SocialNetworkDataDTO;
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
@Path("/social-network-data")
@Produces(MediaType.APPLICATION_JSON)
// @Api(value = "/social-network-data", tags = { "clients" })
public class SocialNetworkDataService extends AbstractServiceMutations {
	@Autowired
	SocialNetworkDataRepository socialNetworkDataRepository;

	@POST
	@ApiOperation(value = "Create Service Type", response = SocialNetworkDataDTO.class)
	public Response saveSocialNetworkData(SocialNetworkDataDTO dto) throws SODAPIException {
		try {
			SocialNetworkData entity = converter.map(dto, SocialNetworkData.class);
			this.saveEntity(socialNetworkDataRepository, entity);
			dto = converter.map(entity, SocialNetworkDataDTO.class);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@PUT
	@ApiOperation(value = "Update Service Type", response = SocialNetworkDataDTO.class)
	public Response updateSocialNetworkData(SocialNetworkDataDTO dto) throws SODAPIException {
		return updateEntity(dto);
	}

	private Response updateEntity(SocialNetworkDataDTO dto) throws SODAPIException {
		try {
			SocialNetworkData entity = converter.map(dto, SocialNetworkData.class);
			this.updateEntity(socialNetworkDataRepository, entity);
			dto = converter.map(entity, SocialNetworkDataDTO.class);
			return castEntityAsResponse(dto, Response.Status.OK);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@DELETE
	@ApiOperation(value = "Create Service Type", response = SocialNetworkDataDTO.class)
	public Response deleteSocialNetworkData(SocialNetworkDataDTO dto) throws SODAPIException {
		try {
			SocialNetworkData entity = converter.map(dto, SocialNetworkData.class);
			this.deleteEntity(socialNetworkDataRepository, entity.getIdSocialNetworkData());
			return castEntityAsResponse(
					new GeneralResponseMessage(true, "Entity deleted"),
					Response.Status.OK);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@GET
	@ApiOperation(value = "Get Service Type list", response = SocialNetworkDataDTO.class, responseContainer = "List")
	public Response getSocialNetworkDataList() throws SODAPIException {
		List<SocialNetworkData> rentityList = this.getEntityList(socialNetworkDataRepository);
		List<SocialNetworkDataDTO> list = rentityList.stream().map((i) -> {
			SocialNetworkDataDTO dto = converter.map(i, SocialNetworkDataDTO.class);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
