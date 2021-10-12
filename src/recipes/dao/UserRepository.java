package recipes.dao;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import recipes.dao.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   //extends JpaRepository or CrudRepository as in RecipeRepo ?

   Optional<User> findByUsername(String username);


}
