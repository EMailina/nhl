package com.league.nhl.league.mapper;

import com.league.nhl.league.dto.TeamTableDto;
import com.league.nhl.league.entity.SeasonData;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-08T19:50:48+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
public class SeasonDataMapperImpl implements SeasonDataMapper {

    @Override
    public TeamTableDto toTeamTableDto(SeasonData seasonData) {
        if ( seasonData == null ) {
            return null;
        }

        TeamTableDto teamTableDto = new TeamTableDto();

        teamTableDto.setOwner( seasonData.getOwner() );
        teamTableDto.setTeamId( seasonData.getTeamId() );
        teamTableDto.setPoints( seasonData.getPoints() );
        teamTableDto.setWins( seasonData.getWins() );
        teamTableDto.setLosses( seasonData.getLosses() );
        teamTableDto.setWinsOt( seasonData.getWinsOt() );
        teamTableDto.setLossesOt( seasonData.getLossesOt() );
        teamTableDto.setGoalsScored( seasonData.getGoalsScored() );
        teamTableDto.setGoalsAgainst( seasonData.getGoalsAgainst() );
        teamTableDto.setPointsBeforeRound( seasonData.getPointsBeforeRound() );

        return teamTableDto;
    }
}
