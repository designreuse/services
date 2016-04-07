package com.il.sod.mapper;

import java.util.Set;
import java.util.Set;
import java.util.stream.Collectors;

import com.il.sod.db.model.entities.Address;
import com.il.sod.db.model.entities.AssetTaskOrder;
import com.il.sod.db.model.entities.Client;
import com.il.sod.db.model.entities.ClientPaymentInfo;
import com.il.sod.db.model.entities.EmployeeTaskOrder;
import com.il.sod.db.model.entities.Order;
import com.il.sod.db.model.entities.OrderPickNDeliver;
import com.il.sod.db.model.entities.OrderTask;
import com.il.sod.db.model.entities.OrderType;
import com.il.sod.db.model.entities.OrderTypeTask;
import com.il.sod.db.model.entities.PaymentInfo;
import com.il.sod.db.model.entities.PhoneNumber;
import com.il.sod.db.model.entities.Service;
import com.il.sod.db.model.entities.ServiceSpec;
import com.il.sod.db.model.entities.ServiceTask;
import com.il.sod.db.model.entities.ServiceType;
import com.il.sod.db.model.entities.ServiceTypeSpec;
import com.il.sod.db.model.entities.ServiceTypeTask;
import com.il.sod.db.model.entities.Spec;
import com.il.sod.rest.dto.db.AddressDTO;
import com.il.sod.rest.dto.db.AssetTaskOrderDTO;
import com.il.sod.rest.dto.db.ClientDTO;
import com.il.sod.rest.dto.db.ClientPaymentInfoDTO;
import com.il.sod.rest.dto.db.EmployeeTaskOrderDTO;
import com.il.sod.rest.dto.db.OrderPickNDeliverDTO;
import com.il.sod.rest.dto.db.OrderTaskDTO;
import com.il.sod.rest.dto.db.OrderTypeTaskDTO;
import com.il.sod.rest.dto.db.PaymentInfoDTO;
import com.il.sod.rest.dto.db.PhoneNumberDTO;
import com.il.sod.rest.dto.db.ServiceTypeDTO;
import com.il.sod.rest.dto.db.ServiceTypeSpecDTO;
import com.il.sod.rest.dto.db.SpecDTO;

import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

public class BidirectionalConverters {}

//*******************
// Orders Mappers
//*******************
class OrderSetConverter extends BidirectionalConverter<Set<Order>, Set<Integer>> {
	@Override
	public Set<Order> convertFrom(Set<Integer> source, Type<Set<Order>> destT) {
		return source.stream().map(p -> (new Order()).setId(p)).collect(Collectors.toSet());
	}

	@Override
	public Set<Integer> convertTo(Set<Order> source, Type<Set<Integer>> destT) {
		return source.stream().map(p -> p.getId()).collect(Collectors.toSet());
	}
}

class OrderTaskSetConverter extends BidirectionalConverter<Set<OrderTask>, Set<OrderTaskDTO>> {
	@Override
	public Set<OrderTask> convertFrom(Set<OrderTaskDTO> source, Type<Set<OrderTask>> arg1) {
		return source.stream().map(item -> OrderMapper.INSTANCE.map(item)).collect(Collectors.toSet());
	}

	@Override
	public Set<OrderTaskDTO> convertTo(Set<OrderTask> source, Type<Set<OrderTaskDTO>> arg1) {
		return source.stream().map(item -> OrderMapper.INSTANCE.map(item)).collect(Collectors.toSet());
	}
}

class OrderTypeTaskSetConverter extends BidirectionalConverter<Set<OrderTypeTask>, Set<OrderTypeTaskDTO>> {
	@Override
	public Set<OrderTypeTask> convertFrom(Set<OrderTypeTaskDTO> source, Type<Set<OrderTypeTask>> arg1) {
		return source.stream().map(item -> OrderMapper.INSTANCE.map(item)).collect(Collectors.toSet());
	}

	@Override
	public Set<OrderTypeTaskDTO> convertTo(Set<OrderTypeTask> source, Type<Set<OrderTypeTaskDTO>> arg1) {
		return source.stream().map(item -> OrderMapper.INSTANCE.map(item)).collect(Collectors.toSet());
	}
}

class ClientConverter extends BidirectionalConverter<Client, ClientDTO> {
	@Override
	public Client convertFrom(ClientDTO source, Type<Client> arg1) {
		return ClientMapper.INSTANCE.map(source);
	}

	@Override
	public ClientDTO convertTo(Client source, Type<ClientDTO> arg1) {
		return ClientMapper.INSTANCE.map(source);
	}
}

class AssetTaskOrderSetConverter extends BidirectionalConverter<Set<AssetTaskOrder>, Set<AssetTaskOrderDTO>> {
	@Override
	public Set<AssetTaskOrder> convertFrom(Set<AssetTaskOrderDTO> source, Type<Set<AssetTaskOrder>> arg1) {
		return source.stream().map(item -> OrderMapper.INSTANCE.map(item)).collect(Collectors.toSet());
	}

	@Override
	public Set<AssetTaskOrderDTO> convertTo(Set<AssetTaskOrder> source, Type<Set<AssetTaskOrderDTO>> arg1) {
		return source.stream().map(item -> OrderMapper.INSTANCE.map(item)).collect(Collectors.toSet());
	}
}

class EmployeeTaskOrderSetConverter
		extends BidirectionalConverter<Set<EmployeeTaskOrder>, Set<EmployeeTaskOrderDTO>> {
	@Override
	public Set<EmployeeTaskOrder> convertFrom(Set<EmployeeTaskOrderDTO> source, Type<Set<EmployeeTaskOrder>> arg1) {
		return source.stream().map(item -> OrderMapper.INSTANCE.map(item)).collect(Collectors.toSet());
	}

	@Override
	public Set<EmployeeTaskOrderDTO> convertTo(Set<EmployeeTaskOrder> source, Type<Set<EmployeeTaskOrderDTO>> arg1) {
		return source.stream().map(item -> OrderMapper.INSTANCE.map(item)).collect(Collectors.toSet());
	}
}

class OrderPickNDeliverSetConverter
		extends BidirectionalConverter<Set<OrderPickNDeliver>, Set<OrderPickNDeliverDTO>> {
	@Override
	public Set<OrderPickNDeliver> convertFrom(Set<OrderPickNDeliverDTO> source, Type<Set<OrderPickNDeliver>> arg1) {
		return source.stream().map(item -> OrderMapper.INSTANCE.map(item)).collect(Collectors.toSet());
	}

	@Override
	public Set<OrderPickNDeliverDTO> convertTo(Set<OrderPickNDeliver> source, Type<Set<OrderPickNDeliverDTO>> arg1) {
		return source.stream().map(item -> OrderMapper.INSTANCE.map(item)).collect(Collectors.toSet());
	}
}

class PaymentInfoSetConverter extends BidirectionalConverter<Set<PaymentInfo>, Set<PaymentInfoDTO>> {
	@Override
	public Set<PaymentInfo> convertFrom(Set<PaymentInfoDTO> source, Type<Set<PaymentInfo>> arg1) {
		return source.stream().map(item -> OrderMapper.INSTANCE.map(item)).collect(Collectors.toSet());
	}

	@Override
	public Set<PaymentInfoDTO> convertTo(Set<PaymentInfo> source, Type<Set<PaymentInfoDTO>> arg1) {
		return source.stream().map(item -> OrderMapper.INSTANCE.map(item)).collect(Collectors.toSet());
	}
}

//*******************
// Service Mapper
//*******************
class ServiceSetConverter extends BidirectionalConverter<Set<Service>, Set<Integer>> {
	@Override
	public Set<Service> convertFrom(Set<Integer> source, Type<Set<Service>> arg1) {
		return source.stream().map(p -> (new Service()).setId(p)).collect(Collectors.toSet());
	}

	@Override
	public Set<Integer> convertTo(Set<Service> source, Type<Set<Integer>> arg1) {
		return source.stream().map(p -> p.getId()).collect(Collectors.toSet());
	}
}

class ServiceTypeSpecSetConverter extends BidirectionalConverter<Set<ServiceTypeSpec>, Set<ServiceTypeSpecDTO>> {
	@Override
	public Set<ServiceTypeSpec> convertFrom(Set<ServiceTypeSpecDTO> source, Type<Set<ServiceTypeSpec>> arg1) {
		return source.stream().map(item -> ServiceMapper.INSTANCE.map(item)).collect(Collectors.toSet());
	}
	@Override
	public Set<ServiceTypeSpecDTO> convertTo(Set<ServiceTypeSpec> source, Type<Set<ServiceTypeSpecDTO>> arg1) {
		return source.stream().map(item -> ServiceMapper.INSTANCE.map(item)).collect(Collectors.toSet());
	}
}

class ServiceTypeTaskSetConverter extends BidirectionalConverter<Set<ServiceTypeTask>, Set<Integer>> {
	@Override
	public Set<ServiceTypeTask> convertFrom(Set<Integer> source, Type<Set<ServiceTypeTask>> arg1) {
		return source.stream().map(p -> (new ServiceTypeTask()).setId(p)).collect(Collectors.toSet());
	}
	
	@Override
	public Set<Integer> convertTo(Set<ServiceTypeTask> source, Type<Set<Integer>> arg1) {
		return source.stream().map(p -> p.getId()).collect(Collectors.toSet());
	}
}

class ServiceSpecSetConverter extends BidirectionalConverter<Set<ServiceSpec>, Set<Integer>> {
	@Override
	public Set<ServiceSpec> convertFrom(Set<Integer> source, Type<Set<ServiceSpec>> arg1) {
		return source.stream().map(p -> (new ServiceSpec()).setId(p)).collect(Collectors.toSet());
	}
	
	@Override
	public Set<Integer> convertTo(Set<ServiceSpec> source, Type<Set<Integer>> arg1) {
		return source.stream().map(p -> p.getId()).collect(Collectors.toSet());
	}
}

class ServiceTaskSetConverter extends BidirectionalConverter<Set<ServiceTask>, Set<Integer>> {
	@Override
	public Set<ServiceTask> convertFrom(Set<Integer> source, Type<Set<ServiceTask>> arg1) {
		return source.stream().map(p -> (new ServiceTask()).setId(p)).collect(Collectors.toSet());
	}
	
	@Override
	public Set<Integer> convertTo(Set<ServiceTask> source, Type<Set<Integer>> arg1) {
		return source.stream().map(p -> p.getId()).collect(Collectors.toSet());
	}
}

class OrderTypeSetConverter extends BidirectionalConverter<Set<OrderType>, Set<Integer>> {
	@Override
	public Set<OrderType> convertFrom(Set<Integer> source, Type<Set<OrderType>> arg1) {
		return source.stream().map(p -> (new OrderType()).setId(p)).collect(Collectors.toSet());
	}

	@Override
	public Set<Integer> convertTo(Set<OrderType> source, Type<Set<Integer>> arg1) {
		return source.stream().map(p -> p.getId()).collect(Collectors.toSet());
	}
}

class ServiceTypeSetConverter extends BidirectionalConverter<Set<ServiceType>, Set<ServiceTypeDTO>> {
	@Override
	public Set<ServiceType> convertFrom(Set<ServiceTypeDTO> source, Type<Set<ServiceType>> arg1) {
		return source.stream().map(item -> ServiceMapper.INSTANCE.map(item)).collect(Collectors.toSet());
	}

	@Override
	public Set<ServiceTypeDTO> convertTo(Set<ServiceType> source, Type<Set<ServiceTypeDTO>> arg1) {
		return source.stream().map(item -> ServiceMapper.INSTANCE.map(item)).collect(Collectors.toSet());
	}
}

class SpecConverter extends BidirectionalConverter<Spec, SpecDTO> {
	@Override
	public Spec convertFrom(SpecDTO source, Type<Spec> arg1) {
		return SpecsMapper.INSTANCE.map(source);
	}

	@Override
	public SpecDTO convertTo(Spec source, Type<SpecDTO> arg1) {
		return SpecsMapper.INSTANCE.map(source);
	}
}

class ServiceSet2IntConverter extends BidirectionalConverter<Set<Service>, Set<Integer>> {

	@Override
	public Set<Service> convertFrom(Set<Integer> source, Type<Set<Service>> arg1) {
		return source.stream().map(p -> (new Service()).setId(p)).collect(Collectors.toSet());
	}

	@Override
	public Set<Integer> convertTo(Set<Service> source, Type<Set<Integer>> arg1) {
		return source.stream().map(p -> p.getId()).collect(Collectors.toSet());
	}
}

// *******************
// ****** Clients mapper
// *******************
class AddressDTOConverter extends BidirectionalConverter<Set<Address>, Set<AddressDTO>> {
	@Override
	public Set<Address> convertFrom(Set<AddressDTO> source, Type<Set<Address>> arg1) {
		return source.stream().map(item -> ClientMapper.INSTANCE.map(item)).collect(Collectors.toSet());
	}

	@Override
	public Set<AddressDTO> convertTo(Set<Address> source, Type<Set<AddressDTO>> arg1) {
		return source.stream().map(item -> ClientMapper.INSTANCE.map(item)).collect(Collectors.toSet());
	}
}

class PhoneNumberDTOConverter extends BidirectionalConverter<Set<PhoneNumber>, Set<PhoneNumberDTO>> {
	@Override
	public Set<PhoneNumber> convertFrom(Set<PhoneNumberDTO> source, Type<Set<PhoneNumber>> arg1) {
		return source.stream().map(item -> ClientMapper.INSTANCE.map(item)).collect(Collectors.toSet());
	}

	@Override
	public Set<PhoneNumberDTO> convertTo(Set<PhoneNumber> source, Type<Set<PhoneNumberDTO>> arg1) {
		return source.stream().map(item -> ClientMapper.INSTANCE.map(item)).collect(Collectors.toSet());
	}
}

class ClientPaymentInfoConverter extends BidirectionalConverter<Set<ClientPaymentInfo>, Set<ClientPaymentInfoDTO>> {
	@Override
	public Set<ClientPaymentInfo> convertFrom(Set<ClientPaymentInfoDTO> source, Type<Set<ClientPaymentInfo>> arg1) {
		return source.stream().map(item -> ClientMapper.INSTANCE.map(item)).collect(Collectors.toSet());
	}

	@Override
	public Set<ClientPaymentInfoDTO> convertTo(Set<ClientPaymentInfo> source, Type<Set<ClientPaymentInfoDTO>> arg1) {
		return source.stream().map(item -> ClientMapper.INSTANCE.map(item)).collect(Collectors.toSet());
	}
}