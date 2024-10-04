package br.com.destaxa.v8.coletor.shipay.application.mapper;


import br.com.destaxa.v8.coletor.shipay.domain.customer.model.Customer;
import br.com.destaxa.v8.coletor.shipay.infrastructure.integration.rest.auth.ShipayPdvsysoutLoginRequestDTO;
import org.mapstruct.Mapper;
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
