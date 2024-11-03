package com.league.nhl.league.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.league.nhl.league.dto.TeamTableDto;
import com.league.nhl.league.entity.SeasonData;

@Mapper
public interface SeasonDataMapper {

	SeasonDataMapper INSTANCE = Mappers.getMapper(SeasonDataMapper.class);

	TeamTableDto toTeamTableDto(SeasonData seasonData);
}
