package com.il.sod.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import com.il.sod.db.model.entities.Spec;
import com.il.sod.db.model.entities.SpecsValue;
import com.il.sod.rest.dto.db.SpecDTO;
import com.il.sod.rest.dto.db.SpecsValueDTO;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.metadata.Type;

public enum SpecsMapper {

	INSTANCE;
	private final MapperFacade mapperFacade;

	private SpecsMapper() {
		ConverterFactory converterFactory = BaseMapper.MAPPER_FACTORY.getConverterFactory();
		converterFactory.registerConverter("specsValueConverter", new SpecsValueConverter());
		
		BaseMapper.MAPPER_FACTORY.classMap(SpecDTO.class, Spec.class)
			.fieldMap("specsValues", "specsValues").converter("specsValueConverter").mapNulls(true).mapNullsInReverse(true).add()
			.byDefault()
			.register();

		BaseMapper.MAPPER_FACTORY.classMap(SpecsValueDTO.class, SpecsValue.class)
			.field("idSpecs", "spec.idSpecs")
			.byDefault()
			.register();
		
		mapperFacade = BaseMapper.MAPPER_FACTORY.getMapperFacade();
	}

	public Spec map(SpecDTO dto) {
		return this.mapperFacade.map(dto, Spec.class);
	}

	public SpecDTO map(Spec entity) {
		return this.mapperFacade.map(entity, SpecDTO.class);
	}
	
	public SpecsValue map(SpecsValueDTO dto) {
		return this.mapperFacade.map(dto, SpecsValue.class);
	}
	
	public SpecsValueDTO map(SpecsValue entity) {
		return this.mapperFacade.map(entity, SpecsValueDTO.class);
	}	
}

class SpecsValueConverter extends BidirectionalConverter<Set<SpecsValue>, Set<SpecsValueDTO>> {
	@Override
	public Set<SpecsValue> convertFrom(Set<SpecsValueDTO> source, Type<Set<SpecsValue>> arg1) {
		return source.stream().map(item -> SpecsMapper.INSTANCE.map(item)).collect(Collectors.toSet());
	}

	@Override
	public Set<SpecsValueDTO> convertTo(Set<SpecsValue> source, Type<Set<SpecsValueDTO>> arg1) {
		return source.stream().map(item -> SpecsMapper.INSTANCE.map(item)).collect(Collectors.toSet());
	}
}