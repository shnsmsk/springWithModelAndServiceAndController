package be.intecbrussel.springfrontend.service;

import be.intecbrussel.springfrontend.model.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    // public Target map(Source source) ...
    public UserEntity mapCreateUserRequestToUserEntity(CreateUserRequest createUserRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(createUserRequest.getUsername());
        userEntity.setPassword(createUserRequest.getPassword());
        userEntity.setEmail(createUserRequest.getEmail());
        return userEntity;
    }

    // public Target map(Source source) ...
    public CreateUserRequest mapUserEntityToCreateUserRequest(UserEntity userEntity) {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUsername(userEntity.getUsername());
        createUserRequest.setPassword(userEntity.getPassword());
        createUserRequest.setEmail(userEntity.getEmail());
        return createUserRequest;
    }

    public UserEntity fromChangePasswordRequestToUserEntity(ChangePasswordRequest changePasswordRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(changePasswordRequest.getUsername());
        userEntity.setPassword(changePasswordRequest.getNewPassword());
        return userEntity;
    }

    public ChangePasswordRequest fromUserEntityToChangePasswordRequest(UserEntity userEntity) {
        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest();
        changePasswordRequest.setUsername(userEntity.getUsername());
        changePasswordRequest.setNewPassword(userEntity.getPassword());
        return changePasswordRequest;
    }

    public UserEntity fromSearchUserRequestToUserEntity(SearchUserRequest searchUserRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(searchUserRequest.getEmail());
        userEntity.setUsername(searchUserRequest.getUsername());
        return userEntity;
    }

    public SearchUserRequest fromUserEntityToSearchUserRequest(UserEntity userEntity) {
        SearchUserRequest searchUserRequest = new SearchUserRequest();
        searchUserRequest.setEmail(userEntity.getEmail());
        searchUserRequest.setUsername(userEntity.getUsername());
        return searchUserRequest;
    }

    public UserEntity mapUserResponseToUserEntity(UserResponse userResponse) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userResponse.getId());
        userEntity.setUsername(userResponse.getUsername());
        userEntity.setEmail(userResponse.getEmail());
        return userEntity;
    }

    public UserResponse mapUserEntityToUserResponse(UserEntity userEntity) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(userEntity.getId());
        userResponse.setUsername(userEntity.getUsername());
        userResponse.setEmail(userEntity.getEmail());
        return userResponse;
    }

}
