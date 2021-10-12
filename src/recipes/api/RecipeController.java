package recipes.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import recipes.dao.entity.Recipe;
import recipes.service.RecipeService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;


@RestController
@Validated
public class RecipeController {

    private RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }



    @PostMapping("/api/recipe/new")
    public Map<String, Long> postRecipe(@Valid @RequestBody Recipe recipe, Principal principal) {

        recipe.setAuthor(principal.getName());
        System.out.println(principal.getName());
        recipeService.save(recipe);
        Long id = recipeService.register(recipe);
        return Map.of("id", id);

    }


    @GetMapping("/api/recipe/{id}")
    public Optional<Recipe> retreiveRecipe(@PathVariable Long id) {

        if (recipeService.getById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
            return recipeService.getById(id);
    }

    @GetMapping(value = "/api/recipe/search", params = "category")
    public Collection<Recipe> searchByCategory(@RequestParam String category) {
        return recipeService.getRecipeByCategory(category);
    }

    @GetMapping(value = "/api/recipe/search", params = "name")
    public Collection<Recipe> searchByName(@RequestParam String name) {
        return recipeService.getRecipeByName(name);
    }


    @DeleteMapping("/api/recipe/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecipe(@PathVariable Long id, Principal principal) {
            String recipeAuthor = recipeService.getById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)).getAuthor();
            if (principal.getName().equals(recipeAuthor)) {
                recipeService.deleteById(id);
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
    }

    @PutMapping("/api/recipe/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Map<String, Long> updateRecipe(@Valid @RequestBody Recipe recipe, @PathVariable Long id, Principal principal) {

        String recipeAuthor = recipeService.getById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)).getAuthor();
        if (principal.getName().equals(recipeAuthor)) {

            recipeService.updateRecipe(recipe, id);
        }  else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        return Map.of("id", id);
    }
}
