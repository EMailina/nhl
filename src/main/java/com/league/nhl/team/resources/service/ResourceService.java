package com.league.nhl.team.resources.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.league.nhl.team.resources.dto.ResourceDTO;
import com.league.nhl.team.resources.entity.Resource;
import com.league.nhl.team.resources.mapper.ResourceMapper;
import com.league.nhl.team.resources.repository.ResourceRepository;

@Service
public class ResourceService {

	@Autowired
	private ResourceRepository repository;

	private final ResourceMapper resourceMapper = ResourceMapper.INSTANCE;

	public ResourceDTO saveResource(ResourceDTO resourceDTO) {
		Resource resource = resourceMapper.resourceDTOToResource(resourceDTO);
		Resource savedResource = repository.save(resource);
		return resourceMapper.resourceToResourceDTO(savedResource);
	}

	public Optional<ResourceDTO> getResource(Long id) {
		return repository.findById(id).map(resourceMapper::resourceToResourceDTO);
	}
	
	public Optional<ResourceDTO> getResourceByTeamId(Long teamId) {
		return repository.findByTeamId(teamId).map(resourceMapper::resourceToResourceDTO);
	}

	public void deleteResource(Long id) {
		repository.deleteById(id);
	}
}
