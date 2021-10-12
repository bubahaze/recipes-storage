package recipes.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import recipes.dao.entity.Recipe;

import java.util.Collection;

@Repository
public interface RecipeRepo extends CrudRepository<Recipe, Long> {


    Collection<Recipe> findByCategoryIgnoreCaseOrderByDateDesc(String category);

    Collection<Recipe> findByNameContainingIgnoreCaseOrderByDateDesc(String name);


}
