package com.il.sod.mapper;

import com.il.sod.db.model.entities.*;
import com.il.sod.rest.dto.db.*;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.converter.ConverterFactory;

public enum ServiceMapper {

	INSTANCE;
	private final MapperFacade mapperFacade;

	private ServiceMapper() {
		
		ConverterFactory converterFactory = BaseMapper.MAPPER_FACTORY.getConverterFactory();
		converterFactory.registerConverter("serviceSetConverter", new ServiceSet2IntConverter());
		converterFactory.registerConverter("serviceTypeSpecSetConverter", new ServiceTypeSpecSetConverter());
		converterFactory.registerConverter("serviceTypeTaskSetConverter", new ServiceTypeTaskSetConverter());
		converterFactory.registerConverter("serviceSpecSetConverter", new ServiceSpecSetConverter());
		converterFactory.registerConverter("serviceTaskSetConverter", new ServiceTaskSetConverter());
		converterFactory.registerConverter("orderTypeSetConverter", new OrderTypeSetConverter());
		converterFactory.registerConverter("serviceTypeSetConverter", new ServiceTypeSetConverter());
		converterFactory.registerConverter("specConverter", new SpecConverter());
		converterFactory.registerConverter("assetTaskServiceSetConverter", new AssetTaskServiceSetConverter());
		converterFactory.registerConverter("productTypeSetConverter", new ProductTypeSetConverter());
		converterFactory.registerConverter("employeeTaskServiceSetConverter", new EmployeeTaskServiceSetConverter());
		converterFactory.registerConverter("serviceProductDTOSetConverter", new ServiceProductDTOSetConverter());

		BaseMapper.MAPPER_FACTORY.classMap(ServiceDTO.class, Service.class)
			.fieldMap("serviceSpecs", "serviceSpecs").converter("serviceSpecSetConverter").mapNulls(true).mapNullsInReverse(true).add()
			.fieldMap("serviceTasks", "serviceTasks").converter("serviceTaskSetConverter").mapNulls(true).mapNullsInReverse(true).add()
			.fieldMap("serviceProducts", "serviceProducts").converter("serviceProductDTOSetConverter").mapNulls(true).mapNullsInReverse(true).add()
			.field("idServiceCategory", "serviceType.serviceCategory.idServiceCategory")
			.field("idServiceType", "serviceType.idServiceType")
			.field("idOrder", "order.idOrder")
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(ServiceTypeDTO.class, ServiceType.class)
			.fieldMap("services", "services").converter("serviceSetConverter").mapNulls(true).mapNullsInReverse(true).add()
			.fieldMap("serviceTypeSpecs", "serviceTypeSpecs").converter("serviceTypeSpecSetConverter").mapNulls(true).mapNullsInReverse(true).add()
			.fieldMap("serviceTypeTasks", "serviceTypeTasks").converter("serviceTypeTaskSetConverter").mapNulls(true).mapNullsInReverse(true).add()
			.fieldMap("productTypes", "productTypes").converter("productTypeSetConverter").mapNulls(true).mapNullsInReverse(true).add()
			.field("serviceTypeCategoryName", "serviceCategory.name")
			.field("idServiceCategory", "serviceCategory.idServiceCategory")
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(ServiceTypeSpecDTO.class, ServiceTypeSpec.class)
			.fieldMap("spec", "spec").converter("specConverter").mapNulls(true).mapNullsInReverse(true).add()
			.field("idServiceType", "serviceType.idServiceType")
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(ServiceTypeTaskDTO.class, ServiceTypeTask.class)
			.field("idServiceType", "serviceType.idServiceType")
			.field("serviceTypeName", "serviceType.name")
			.field("taskTypeName", "task.taskType.name")
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(ServiceCategoryDTO.class, ServiceCategory.class)
			.fieldMap("serviceTypes", "serviceTypes").converter("serviceTypeSetConverter").mapNulls(true).mapNullsInReverse(true).add()
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(ServiceTaskDTO.class, ServiceTask.class)
			.fieldMap("assetTaskServices", "assetTaskServices").converter("assetTaskServiceSetConverter").mapNulls(true).mapNullsInReverse(true).add()
			.fieldMap("employeeTaskServices", "employeeTaskServices").converter("employeeTaskServiceSetConverter").mapNulls(true).mapNullsInReverse(true).add()
			.field("idService", "service.idService")
			.field("idTask", "task.idTask")
			.field("taskName", "task.name")
			.byDefault().register();
		
		BaseMapper.MAPPER_FACTORY.classMap(AssetTaskServiceDTO.class, AssetTaskService.class)
			.field("idAsset", "asset.idAsset")
			.field("idServiceTask", "serviceTask.idServiceTask")
			.byDefault().register();

		BaseMapper.MAPPER_FACTORY.classMap(EmployeeTaskServiceDTO.class, EmployeeTaskService.class)
			.field("idServiceTask", "serviceTask.idServiceTask")
			.field("idEmployee", "employee.idEmployee")
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(ServiceCommentDTO.class, ServiceComment.class)
			.field("idService", "service.idService")
			.byDefault()
			.register();

		mapperFacade = BaseMapper.MAPPER_FACTORY.getMapperFacade();
	}

	public Service map(ServiceDTO dto) {
		return this.mapperFacade.map(dto, Service.class);
	}

	public ServiceDTO map(Service entity) {
		return this.mapperFacade.map(entity, ServiceDTO.class);
	}
	
	public ServiceType map(ServiceTypeDTO dto) {
		return this.mapperFacade.map(dto, ServiceType.class);
	}
	
	public ServiceTypeDTO map(ServiceType entity) {
		return this.mapperFacade.map(entity, ServiceTypeDTO.class);
	}
	
	public ServiceTypeSpec map(ServiceTypeSpecDTO dto) {
		return this.mapperFacade.map(dto, ServiceTypeSpec.class);
	}
	
	public ServiceTypeSpecDTO map(ServiceTypeSpec entity) {
		return this.mapperFacade.map(entity, ServiceTypeSpecDTO.class);
	}
	
	public ServiceTypeTask map(ServiceTypeTaskDTO dto) {
		return this.mapperFacade.map(dto, ServiceTypeTask.class);
	}
	
	public ServiceTypeTaskDTO map(ServiceTypeTask entity) {
		return this.mapperFacade.map(entity, ServiceTypeTaskDTO.class);
	}
	
	public ServiceCategory map(ServiceCategoryDTO dto) {
		return this.mapperFacade.map(dto, ServiceCategory.class);
	}
	
	public ServiceCategoryDTO map(ServiceCategory entity) {
		return this.mapperFacade.map(entity, ServiceCategoryDTO.class);
	}
	
	public ServiceTask map(ServiceTaskDTO dto) {
		return this.mapperFacade.map(dto, ServiceTask.class);
	}

	public ServiceTaskDTO map(ServiceTask entity) {
		return this.mapperFacade.map(entity, ServiceTaskDTO.class);
	}
	
	public AssetTaskServiceDTO map(AssetTaskService entity) {
		return this.mapperFacade.map(entity, AssetTaskServiceDTO.class);
	}

	public AssetTaskService map(AssetTaskServiceDTO dto) {
		return this.mapperFacade.map(dto, AssetTaskService.class);
	}

	public EmployeeTaskServiceDTO map(EmployeeTaskService entity) {
		return this.mapperFacade.map(entity, EmployeeTaskServiceDTO.class);
	}

	public EmployeeTaskService map(EmployeeTaskServiceDTO dto) {
		return this.mapperFacade.map(dto, EmployeeTaskService.class);
	}
	
	public ServiceCommentDTO map(ServiceComment entity) {
		return this.mapperFacade.map(entity, ServiceCommentDTO.class);
	}
	
	public ServiceComment map(ServiceCommentDTO dto) {
		return this.mapperFacade.map(dto, ServiceComment.class);
	}

}

