package com.nicolascruz.push.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Document
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractEntity<UUID> {

    private String firstName;

    private String lastName;

    private String email;

    private boolean active;

    @JsonIgnore
    private String password;

    private Set<String> roles = new HashSet<>();
}
