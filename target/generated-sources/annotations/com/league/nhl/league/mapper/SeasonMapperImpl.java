package com.league.nhl.league.mapper;

import com.league.nhl.league.dto.SeasonDto;
import com.league.nhl.league.entity.Season;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-03T15:07:48+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
public class SeasonMapperImpl implements SeasonMapper {

    @Override
    public Season toEntity(SeasonDto seasonDto) {
        if ( seasonDto == null ) {
            return null;
        }

        Season season = new Season();

        season.setId( seasonDto.getId() );
        season.setName( seasonDto.getName() );
        season.setGameType( seasonDto.getGameType() );
        season.setYearOrigin( seasonDto.getYearOrigin() );
        season.setYearPlayed( seasonDto.getYearPlayed() );

        return season;
    }

    @Override
    public SeasonDto toDto(Season season) {
        if ( season == null ) {
            return null;
        }

        SeasonDto seasonDto = new SeasonDto();

        seasonDto.setId( season.getId() );
        seasonDto.setName( season.getName() );
        seasonDto.setGameType( season.getGameType() );
        seasonDto.setYearPlayed( season.getYearPlayed() );
        seasonDto.setYearOrigin( season.getYearOrigin() );

        return seasonDto;
    }
}
