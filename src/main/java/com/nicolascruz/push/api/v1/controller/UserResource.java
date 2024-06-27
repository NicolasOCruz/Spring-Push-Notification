package com.nicolascruz.push.api.v1.controller;

import com.nicolascruz.push.api.v1.assembler.UserAssembler;
import com.nicolascruz.push.api.v1.model.records.UserRecord;
import com.nicolascruz.push.domain.model.User;
import com.nicolascruz.push.domain.services.UserService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Log4j2
@CrossOrigin
public class UserResource {

    private final UserAssembler assembler;
    private final UserService userService;

    public UserResource(UserAssembler assembler, UserService userService) {
        this.assembler = assembler;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid UserRecord userRecord) {
        User user = assembler.toDomainObject(userRecord);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }
}
