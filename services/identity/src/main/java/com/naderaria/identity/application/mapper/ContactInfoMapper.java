package com.naderaria.identity.application.mapper;

import com.naderaria.identity.api.dto.contact_info.request.ReqContactInfoDto;
import com.naderaria.identity.api.dto.contact_info.response.ResContactInfoDto;
import com.naderaria.identity.infratructure.domin.ContactInfo;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ContactInfoMapper {

    ContactInfo toContactInfo(ReqContactInfoDto reqContactInfoDto);

    ResContactInfoDto toResContactInfo(ContactInfo contactInfo);
}
