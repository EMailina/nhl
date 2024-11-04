package com.league.nhl.league.mapper;

import com.league.nhl.league.dto.TeamDto;
import com.league.nhl.league.entity.Team;
import com.league.nhl.league.enums.Owner;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-04T19:10:50+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
public class TeamMapperImpl implements TeamMapper {

    @Override
    public TeamDto toDTO(Team team) {
        if ( team == null ) {
            return null;
        }

        TeamDto teamDto = new TeamDto();

        teamDto.setId( team.getId() );
        teamDto.setName( team.getName() );
        teamDto.setShortName( team.getShortName() );
        teamDto.setStrength( team.getStrength() );
        if ( team.getOwner() != null ) {
            teamDto.setOwner( team.getOwner().name() );
        }
        teamDto.setDivision( team.getDivision() );
        teamDto.setConference( team.getConference() );
        teamDto.setDefenseStrength( team.getDefenseStrength() );
        teamDto.setGoalieStrength( team.getGoalieStrength() );
        teamDto.setOffenseStrength( team.getOffenseStrength() );

        return teamDto;
    }

    @Override
    public Team toEntity(TeamDto dto) {
        if ( dto == null ) {
            return null;
        }

        Team team = new Team();

        team.setId( dto.getId() );
        team.setName( dto.getName() );
        team.setShortName( dto.getShortName() );
        team.setStrength( dto.getStrength() );
        if ( dto.getOwner() != null ) {
            team.setOwner( Enum.valueOf( Owner.class, dto.getOwner() ) );
        }
        team.setDivision( dto.getDivision() );
        team.setConference( dto.getConference() );
        team.setGoalieStrength( dto.getGoalieStrength() );
        team.setDefenseStrength( dto.getDefenseStrength() );
        team.setOffenseStrength( dto.getOffenseStrength() );

        return team;
    }
}
