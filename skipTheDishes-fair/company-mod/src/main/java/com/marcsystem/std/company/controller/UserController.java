package com.marcsystem.std.company.controller;

import com.marcsystem.std.company.model.AppUser;
import com.marcsystem.std.company.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static java.util.Objects.isNull;

@RestController
public class UserController extends BaseController<AppUser, Long, UserRepository> {

    @GetMapping("/users")
    public List<AppUser> getAllUsers() {
        return getAll();
    }

    @GetMapping("/users/{userName}")
    public AppUser findByUserName(@PathVariable String userName){
        return getRepository().findByUserName(userName);
    }

    @PostMapping("/users")
    public ResponseEntity<AppUser> createNew(@RequestBody AppUser user) {
        AppUser saved = save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(saved.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("users/{id}")
    public ResponseEntity<AppUser> updateUser(@RequestBody AppUser user, @PathVariable Long id) {
        AppUser old = getOne(id);
        if (isNull(old)) {
            return ResponseEntity.notFound().build();
        }

        user.setId(id);
        save(user);

        return ResponseEntity.noContent().build();
    }
}
