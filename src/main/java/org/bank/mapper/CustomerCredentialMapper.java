package org.bank.mapper;

import org.bank.dto.CustomerCredentialDto;
import org.bank.entity.CustomerCredential;
import org.mapstruct.AnnotateWith;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
@AnnotateWith(GeneratedMapper.class)
public abstract class CustomerCredentialMapper extends BaseMapper<CustomerCredentialDto, CustomerCredential> {
}
