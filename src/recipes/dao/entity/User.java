package recipes.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    @Column
    private Long id;
    @Column
    @JsonIgnore
    private String username;
    @Column
    @Email
    @Pattern(regexp = ".+@.+\\..+", message="Please provide a valid email address")
    private String email;
    @Column
    @Size(min = 8)
    @NotBlank
    private String password;
    @Column
    @JsonIgnore
    private String role;
}
