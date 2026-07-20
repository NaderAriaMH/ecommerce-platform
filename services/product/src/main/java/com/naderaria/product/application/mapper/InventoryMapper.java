package com.naderaria.product.application.mapper;

import com.naderaria.product.api.dto.request.ReqInventoryDto;
import com.naderaria.product.infratructure.domain.model.Inventory;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface InventoryMapper {

    Inventory toInventory(ReqInventoryDto reqInventoryDto);

}
