package com.league.nhl.team.resources.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.league.nhl.team.resources.dto.ResourceDTO;
import com.league.nhl.team.resources.service.ResourceService;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {

	@Autowired
	private ResourceService service;

	@PostMapping("/create")
	public ResponseEntity<ResourceDTO> createResource(@RequestBody ResourceDTO resourceDTO) {
		ResourceDTO savedResource = service.saveResource(resourceDTO);
		return ResponseEntity.ok(savedResource);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<ResourceDTO> getResource(@PathVariable Long id) {
		return service.getResource(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/test")
	public String gettestResource() {
		return "dfasfasdf";
	}

	@GetMapping("/getByTeamId/{teamId}")
	public ResponseEntity<ResourceDTO> getResourceByTeamId(@PathVariable Long teamId) {
		return service.getResourceByTeamId(teamId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteResource(@PathVariable Long id) {
		service.deleteResource(id);
		return ResponseEntity.noContent().build();
	}
}