package com.league.nhl.league.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.league.nhl.league.dto.MatchDto;
import com.league.nhl.league.dto.MatchViewDto;
import com.league.nhl.league.entity.Match;

@Mapper
public interface MatchMapper {

	MatchMapper INSTANCE = Mappers.getMapper(MatchMapper.class);

	MatchDto toDto(Match match);

	MatchViewDto toMatchViewDto(Match match);

	Match toEntity(MatchDto matchDTO);

	List<MatchViewDto> toDtos(List<Match> matches);
}