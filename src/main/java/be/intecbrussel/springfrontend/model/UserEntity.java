package be.intecbrussel.springfrontend.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank
    @EqualsAndHashCode.Include
    private String username;

    @NotNull
    @Length(min = 8)
    private String password;

    @Email
    @ToString.Include
    @EqualsAndHashCode.Include
    private String email;

    @NotBlank
    private String role;

    private Boolean deleted;


}
