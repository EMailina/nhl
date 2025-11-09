package com.league.nhl.league.mapper;

import com.league.nhl.league.dto.MatchDto;
import com.league.nhl.league.dto.MatchViewDto;
import com.league.nhl.league.entity.Match;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-09T15:49:17+0100",
    comments = "version: 1.3.1.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250628-1110, environment: Java 21.0.7 (Eclipse Adoptium)"
)
public class MatchMapperImpl implements MatchMapper {

    @Override
    public MatchDto toDto(Match match) {
        if ( match == null ) {
            return null;
        }

        MatchDto matchDto = new MatchDto();

        matchDto.setSeasonId( match.getSeasonId() );
        matchDto.setId( match.getId() );
        matchDto.setHomeTeamId( match.getHomeTeamId() );
        matchDto.setAwayTeamId( match.getAwayTeamId() );
        matchDto.setHomeTeamScore( match.getHomeTeamScore() );
        matchDto.setAwayTeamScore( match.getAwayTeamScore() );
        matchDto.setOvertime( match.isOvertime() );
        matchDto.setSimulated( match.isSimulated() );

        return matchDto;
    }

    @Override
    public MatchViewDto toMatchViewDto(Match match) {
        if ( match == null ) {
            return null;
        }

        MatchViewDto matchViewDto = new MatchViewDto();

        matchViewDto.setSeasonId( match.getSeasonId() );
        matchViewDto.setId( match.getId() );
        matchViewDto.setHomeTeamId( match.getHomeTeamId() );
        matchViewDto.setAwayTeamId( match.getAwayTeamId() );
        matchViewDto.setHomeTeamScore( match.getHomeTeamScore() );
        matchViewDto.setAwayTeamScore( match.getAwayTeamScore() );
        matchViewDto.setOvertime( match.isOvertime() );
        matchViewDto.setSimulated( match.isSimulated() );
        matchViewDto.setCreatedAt( match.getCreatedAt() );

        return matchViewDto;
    }

    @Override
    public Match toEntity(MatchDto matchDTO) {
        if ( matchDTO == null ) {
            return null;
        }

        Match match = new Match();

        match.setId( matchDTO.getId() );
        match.setHomeTeamId( matchDTO.getHomeTeamId() );
        match.setAwayTeamId( matchDTO.getAwayTeamId() );
        match.setHomeTeamScore( matchDTO.getHomeTeamScore() );
        match.setAwayTeamScore( matchDTO.getAwayTeamScore() );
        match.setOvertime( matchDTO.isOvertime() );
        match.setSimulated( matchDTO.isSimulated() );
        match.setSeasonId( matchDTO.getSeasonId() );

        return match;
    }

    @Override
    public List<MatchViewDto> toDtos(List<Match> matches) {
        if ( matches == null ) {
            return null;
        }

        List<MatchViewDto> list = new ArrayList<MatchViewDto>( matches.size() );
        for ( Match match : matches ) {
            list.add( toMatchViewDto( match ) );
        }

        return list;
    }
}
