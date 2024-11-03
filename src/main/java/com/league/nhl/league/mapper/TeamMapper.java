package com.league.nhl.league.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.league.nhl.league.dto.TeamDto;
import com.league.nhl.league.entity.Team;

@Mapper
public interface TeamMapper {
	TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);

	TeamDto toDTO(Team team);

	Team toEntity(TeamDto dto);

	
}
