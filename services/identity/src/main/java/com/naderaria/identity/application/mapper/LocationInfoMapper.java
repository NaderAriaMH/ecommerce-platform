package com.naderaria.identity.application.mapper;

import com.naderaria.identity.api.dto.location_info.request.ReqLocationInfoDto;
import com.naderaria.identity.api.dto.location_info.response.ResLocationInfoDto;
import com.naderaria.identity.infratructure.domin.LocationInfo;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface LocationInfoMapper {

    LocationInfo toLocationInfo(ReqLocationInfoDto reqLocationInfoDto);

    ResLocationInfoDto toResLocationInfo(LocationInfo locationInfo);

}
