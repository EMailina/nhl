package com.league.nhl.team.resources.mapper;

import com.league.nhl.team.resources.dto.ResourceDTO;
import com.league.nhl.team.resources.entity.Resource;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-03T16:03:30+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
public class ResourceMapperImpl implements ResourceMapper {

    @Override
    public ResourceDTO resourceToResourceDTO(Resource resource) {
        if ( resource == null ) {
            return null;
        }

        ResourceDTO resourceDTO = new ResourceDTO();

        resourceDTO.setId( resource.getId() );
        resourceDTO.setTeamId( resource.getTeamId() );
        resourceDTO.setName( resource.getName() );
        resourceDTO.setBase64Image( resource.getBase64Image() );
        resourceDTO.setMimeType( resource.getMimeType() );

        return resourceDTO;
    }

    @Override
    public Resource resourceDTOToResource(ResourceDTO resourceDTO) {
        if ( resourceDTO == null ) {
            return null;
        }

        Resource resource = new Resource();

        resource.setId( resourceDTO.getId() );
        resource.setTeamId( resourceDTO.getTeamId() );
        resource.setName( resourceDTO.getName() );
        resource.setBase64Image( resourceDTO.getBase64Image() );
        resource.setMimeType( resourceDTO.getMimeType() );

        return resource;
    }
}
