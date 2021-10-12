package recipes.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import recipes.dao.RecipeRepo;
import recipes.dao.entity.Recipe;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

@Service
public class RecipeService {

    private RecipeRepo recipeRepo;

    @Autowired
    public RecipeService(RecipeRepo recipeRepo) {
        this.recipeRepo = recipeRepo;
    }

    public Recipe save(Recipe recipe) {
    return recipeRepo.save(recipe);
}

    public Optional<Recipe> getById(@RequestParam Long id) {
        return recipeRepo.findById(id);

    }

    public void deleteById(@RequestParam Long id) {
        recipeRepo.deleteById(id);
    }

    public Long register(Recipe recipe) {
        return recipe.getId();
    }

    public Recipe updateRecipe(Recipe recipe, Long id) {
        Recipe updatedRecipe = getById(id).get();
        updatedRecipe.setName(recipe.getName());
        updatedRecipe.setCategory(recipe.getCategory());
        updatedRecipe.setDescription(recipe.getDescription());
        updatedRecipe.setIngredients(recipe.getIngredients());
        updatedRecipe.setDirections(recipe.getDirections());
        updatedRecipe.setDate(LocalDateTime.now());

        return recipeRepo.save(updatedRecipe); }

    public Collection<Recipe> getRecipeByCategory(String category) {
        return recipeRepo.findByCategoryIgnoreCaseOrderByDateDesc(category);
    }

    public Collection<Recipe> getRecipeByName(String name) {
        return recipeRepo.findByNameContainingIgnoreCaseOrderByDateDesc(name);
    }
}
