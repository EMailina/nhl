package com.league.nhl.team.resources.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.league.nhl.team.resources.dto.ResourceDTO;
import com.league.nhl.team.resources.entity.Resource;

@Mapper
public interface ResourceMapper {
	ResourceMapper INSTANCE = Mappers.getMapper(ResourceMapper.class);

	// Mapping from Entity to DTO
	ResourceDTO resourceToResourceDTO(Resource resource);

	// Mapping from DTO to Entity
	Resource resourceDTOToResource(ResourceDTO resourceDTO);
}
