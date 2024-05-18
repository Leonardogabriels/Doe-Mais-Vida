package com.doemaisvida.una.doemaisvida.resources;


import com.doemaisvida.una.doemaisvida.Dto.UserDto;
import com.doemaisvida.una.doemaisvida.entitys.User;
import com.doemaisvida.una.doemaisvida.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody User User ){
		User user = userService.login(User.getEmail(), User.getPassword());
		return ResponseEntity.ok().body(user);
	}

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user ){
		userService.createUser(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(user);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user ){
		User newUser = userService.update(id,user);
		return ResponseEntity.ok().body(newUser);
	}
}