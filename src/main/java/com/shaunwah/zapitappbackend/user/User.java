package com.shaunwah.zapitappbackend.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String firstName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String lastName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date dateOfBirth;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false, unique = true)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String mobilePhone;

    @Column(columnDefinition = "varchar(255) default 'ROLE_USER'", nullable = false)
    private String roles = "ROLE_USER";

    @Column(columnDefinition = "boolean default true", nullable = false)
    private Boolean isActive = true;

    @Column(columnDefinition = "boolean default false", nullable = false)
    private Boolean isHidden = false;

    @CreationTimestamp
    @Column(columnDefinition = "datetime default current_timestamp", nullable = false, updatable = false)
    private Timestamp createdOn;

    @UpdateTimestamp
    private Timestamp updatedOn;

    public static User newUserWithId(Long id) {
        User user = new User();
        user.setId(id);
        return user;
    }
}
