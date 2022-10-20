package be.intecbrussel.springfrontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringFrontendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringFrontendApplication.class, args);
	}


	// TODO: UserEntity (id, username, password, email, role)

	// TODO: UserDTO (username, password, email, role)

	// TODO: UserMapper (mapToDTO(UserEntity entity), mapToEntity(UserDTO dto))

	// TODO: UserRepository (extends JpaRepository)

	// TODO: UserService (create(....), read(....), update(.....), delete(....))

	// TODO: UserController (create(....), read(....), update(....), delete(....))

	// TODO: UserView


}
