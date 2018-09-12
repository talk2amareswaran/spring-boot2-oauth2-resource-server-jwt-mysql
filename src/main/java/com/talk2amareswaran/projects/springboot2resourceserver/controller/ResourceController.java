package com.talk2amareswaran.projects.springboot2resourceserver.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talk2amareswaran.projects.springboot2resourceserver.model.CustomPrincipal;

@RestController
public class ResourceController {

	@GetMapping("/admins")
	@PreAuthorize("hasAuthority('role_admin')")
	public String context() {
		CustomPrincipal principal = (CustomPrincipal) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		return principal.getUsername() + " " + principal.getEmail();
	}

	@GetMapping("/users")
	@PreAuthorize("hasAnyAuthority('role_admin','role_user')")
	public String secured(CustomPrincipal principal) {
		return principal.getUsername() + " " + principal.getEmail();
	}

	@GetMapping("/common")
	public String general() {
		return "common api success";
	}
	
}
