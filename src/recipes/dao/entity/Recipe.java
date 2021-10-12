package recipes.dao.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name="recipe")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @JsonIgnore
    private Long id;
    @Column
    @NotBlank
    private String name;
    @Column
    @NotBlank
    private String category;
    @Column
    @CreationTimestamp
    private LocalDateTime date;
    @Column
    @NotBlank
    private String description;
    @Column
    @Size(min=1, message="At least one ingredient needed")
    @NotEmpty
    private String[] ingredients;
    @Column
    @Size(min=1, message="At least one direction needed")
    @NotEmpty
    private String[] directions;
    @Column
    @JsonIgnore
    private String author;

}
