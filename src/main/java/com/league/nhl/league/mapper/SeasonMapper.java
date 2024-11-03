package com.league.nhl.league.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.league.nhl.league.dto.SeasonDto;
import com.league.nhl.league.entity.Season;

@Mapper
public interface SeasonMapper {
	SeasonMapper INSTANCE = Mappers.getMapper(SeasonMapper.class);

	Season toEntity(SeasonDto seasonDto);

	SeasonDto toDto(Season season);
}
