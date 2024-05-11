package com.doemaisvida.una.doemaisvida.resources;


import com.doemaisvida.una.doemaisvida.entitys.User;
import com.doemaisvida.una.doemaisvida.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    //public ResponseEntity<List<UserDTO>>

	@PostMapping
	public ResponseEntity<Void> createUser(@RequestBody User user ){
		userService.createUser(user);
       return ResponseEntity.noContent().build();
	}
}