package be.intecbrussel.springfrontend.service;

import be.intecbrussel.springfrontend.model.ChangePasswordRequest;
import be.intecbrussel.springfrontend.model.CreateUserRequest;
import be.intecbrussel.springfrontend.model.UserEntity;
import be.intecbrussel.springfrontend.model.UserResponse;
import be.intecbrussel.springfrontend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    // Create, read, update and delete methods here

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public void create(CreateUserRequest request) {
        UserEntity entity = userMapper.mapCreateUserRequestToUserEntity(request);
        userRepository.save(entity);
    }


    public boolean changePassword(ChangePasswordRequest request) {
        UserEntity searchableEntity = userMapper.fromChangePasswordRequestToUserEntity(request);
        searchableEntity.setUsername(request.getUsername());
        searchableEntity.setPassword(request.getOldPassword());

        Optional<UserEntity> foundEntity = userRepository.findOne(Example.of(searchableEntity));
        String encodedPassword = passwordEncoder.encode(request.getNewPassword());

        if (foundEntity.isPresent()) {
            UserEntity entity = foundEntity.get();
            entity.setPassword(encodedPassword);

            userRepository.save(entity);
            return true;
        }

        return false;
    }


    public boolean changePassword(String username, String oldPassword, String newPassword) {

        UserEntity searchableEntity = new UserEntity();
        searchableEntity.setUsername(username);
        searchableEntity.setPassword(oldPassword);

        Optional<UserEntity> foundEntity = userRepository.findOne(Example.of(searchableEntity));
        String encodedPassword = passwordEncoder.encode(newPassword);

        if (foundEntity.isPresent()) {
            UserEntity entity = foundEntity.get();
            entity.setPassword(encodedPassword);

            userRepository.save(entity);
            return true;
        }

        return false;

    }


    public Optional<UserResponse> read(Long id) {
        Optional<UserEntity> foundEntity = userRepository.findById(id);

        if (foundEntity.isPresent())
            return Optional.of(
                    userMapper.mapUserEntityToUserResponse(foundEntity.get())
            );

        return Optional.empty();
    }



    public Optional<UserResponse> readByUsername(String username) {
        Optional<UserEntity> foundEntity = userRepository.findByUsername(username);

        if (foundEntity.isPresent())
            return Optional.of(
                    userMapper.mapUserEntityToUserResponse(foundEntity.get())
            );

        return Optional.empty();
    }



    public Optional<UserResponse> readByUsernameAndPassword(String username, String password) {

        UserEntity searchableUser = new UserEntity();
        searchableUser.setUsername(username);
        searchableUser.setPassword(password);
        Optional<UserEntity> foundEntity = userRepository.findOne(Example.of(searchableUser));

        if (foundEntity.isPresent())
            return Optional.of(
                    userMapper.mapUserEntityToUserResponse(foundEntity.get())
            );

        return Optional.empty();

    }



    public Optional<UserResponse> readByEmailAndPassword(String email, String password) {

        UserEntity searchableUser = new UserEntity();
        searchableUser.setEmail(email);
        searchableUser.setPassword(password);
        Optional<UserEntity> foundEntity = userRepository.findOne(Example.of(searchableUser));

        if (foundEntity.isPresent())
            return Optional.of(
                    userMapper.mapUserEntityToUserResponse(foundEntity.get())
            );

        return Optional.empty();

    }


    public List<UserResponse> readAll() {

        List<UserEntity> foundEntities = userRepository.findAll();

        List<UserResponse> responses = new ArrayList<>();

        for (UserEntity foundEntity : foundEntities) {
            UserResponse response = userMapper.mapUserEntityToUserResponse(foundEntity);
            if (response != null) {
                responses.add(response);
            }
        }

        return responses;
    }



    public List<UserResponse> readAllInPages(Integer pageNo, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, 5, Sort.by(sortBy));
        Page<UserEntity> pagedResult = userRepository.findAll(paging);

        if (pagedResult.hasContent()) {
            List<UserEntity> foundEntities = pagedResult.getContent();

            List<UserResponse> responses = new ArrayList<>();

            for (UserEntity foundEntity : foundEntities) {
                UserResponse response = userMapper.mapUserEntityToUserResponse(foundEntity);
                if (response != null) {
                    responses.add(response);
                }
            }

            return responses;
        }

        return new ArrayList<>();

    }


}
