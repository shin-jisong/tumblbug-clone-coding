
package com.example.tumblbugclone.service;


import com.example.tumblbugclone.Exception.userexception.*;

import com.example.tumblbugclone.dto.UserReceivingDTO;
import com.example.tumblbugclone.dto.UserSendingDTO;
import com.example.tumblbugclone.managedconst.HttpConst;
import com.example.tumblbugclone.model.User;
import com.example.tumblbugclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){this.userRepository = userRepository;}


    public long join(UserReceivingDTO userDTO) throws UserEmailDuplicatedException, UserIdDuplicatedException, UserDTOConvertException {
        User user = convertDTO2User(userDTO);
        try {
            userRepository.checkDuplication(user);
        } catch (UserEmailDuplicatedException | UserIdDuplicatedException e) {
            throw e;
        }
        long savedIndex = userRepository.save(user);

        return savedIndex;
    }

    public long login(String userId, String userPassword) throws UserCantFindException, WrongPasswordException {
        User userById;
        try {
            userById = userRepository.findUserById(userId);
        }catch (EmptyResultDataAccessException e){
            throw new UserCantFindException();
        }

        if(userById.getUserPassword().equals(userPassword) == false){
            throw new WrongPasswordException();
        }

        return userById.getUserIdx();
    }
    /*

        return userRepository.save(user);
    }*/


    public UserSendingDTO findUserByIndex(long userIdx){

        User findUser = userRepository.findUserByIndex(userIdx);

        return convertUser2DTO(findUser);
    }

    public UserSendingDTO findUserById(String userId){
        User findUser = userRepository.findUserById(userId);

        return convertUser2DTO(findUser);
    }

    public void unregiste(UserReceivingDTO userDTO) throws UserDTOConvertException {
        User user = convertDTO2User(userDTO);
        User findUser = userRepository.findUserByIndex(user.getUserIdx());
        user.setActive(false);
        userRepository.modify(user);
    }

    public void modify(UserReceivingDTO userDTO) throws UserCantModifyIdException, UserDTOConvertException {
        User user = convertDTO2User(userDTO);

        User findUser = userRepository.findUserByIndex(user.getUserIdx());

        if(findUser.getUserId().equals(user.getUserId()) == false){
            throw new UserCantModifyIdException();
        }

        userRepository.modify(user);
    }

    public UserSendingDTO convertUser2DTO(User user){
        UserSendingDTO userDTO = new UserSendingDTO();

        userDTO.setUserId(user.getUserId());

        userDTO.setUserName(user.getUserName());

        userDTO.setUserEmail(user.getUserEmail());

        userDTO.setUserIdx(user.getUserIdx());
        userDTO.setUserImg(user.getUserImg());
        userDTO.setActive(user.isActive());
        userDTO.setGreeting(user.getGreeting());
        userDTO.setLastLogin(user.getLastLogin());

        return userDTO;
    }

    public User convertDTO2User(UserReceivingDTO newUserDTO) throws UserDTOConvertException {
        User user = new User();
        user.setUserIdx(newUserDTO.getUserIdx());
        if(newUserDTO.getUserName() == null){
            throw new UserDTOConvertException(HttpConst.USERNAME_IS_NULL);
        }
        user.setUserName(newUserDTO.getUserName());

        if(newUserDTO.getUserId() == null){
            throw new UserDTOConvertException(HttpConst.USERID_IS_NULL);
        }
        user.setUserId(newUserDTO.getUserId());

        if(newUserDTO.getUserPassword() == null){
            throw new UserDTOConvertException(HttpConst.USERPASSWORD_IS_NULL);
        }
        user.setUserPassword(newUserDTO.getUserPassword());

        if(newUserDTO.getUserEmail() == null){
            throw new UserDTOConvertException(HttpConst.USEREMAIL_IS_NULL);
        }
        user.setUserEmail(newUserDTO.getUserEmail());

        user.setUserImg(newUserDTO.getUserImg());
        user.setGreeting(newUserDTO.getGreeting());
        user.setLastLogin(newUserDTO.getLastLogin());
        user.setActive(newUserDTO.isActive());
        return user;
    }

}

