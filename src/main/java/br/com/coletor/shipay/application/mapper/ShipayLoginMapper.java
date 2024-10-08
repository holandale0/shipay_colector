package br.com.coletor.shipay.application.mapper;


import br.com.coletor.shipay.infrastructure.integration.rest.auth.ShipayPdvsysoutLoginRequestDTO;
import br.com.coletor.shipay.domain.customer.model.Customer;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

//@Mapper
public interface ShipayLoginMapper {

  ShipayLoginMapper INTANCE = Mappers.getMapper(ShipayLoginMapper.class);

 // @Mapping(target = "accessKey", source = "accessKey")
  //@Mapping(target = "posProductId", source = "secretKey")
  @Mapping(target = "clientId", source = "clientId")
  ShipayPdvsysoutLoginRequestDTO toRequest(Customer customer);

}
