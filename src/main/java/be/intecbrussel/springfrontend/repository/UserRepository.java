package be.intecbrussel.springfrontend.repository;

import be.intecbrussel.springfrontend.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {


    Optional<UserEntity> findByUsername(@NotBlank String username);

    @Modifying
    @Query("update u UserEntity set u.password= :password where u.username = :username")
    void changePassword(@Param("username") @NotBlank String username, @Param("password") @NotBlank String password);
}
