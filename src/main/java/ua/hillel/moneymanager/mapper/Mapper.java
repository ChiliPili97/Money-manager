package ua.hillel.moneymanager.mapper;

import org.springframework.stereotype.Component;

import java.util.List;

public interface Mapper <ENTITY, DTO> {
     DTO toDto(ENTITY entity);

    List<DTO> toDto(List<ENTITY> entities);

     ENTITY toEntity(DTO dto);
}
