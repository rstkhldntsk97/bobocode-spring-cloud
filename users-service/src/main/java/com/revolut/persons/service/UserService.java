package com.revolut.persons.service;

import com.revolut.persons.dto.UserDto;
import com.revolut.persons.entity.UserEntity;
import com.revolut.persons.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final ModelMapper mapper;
    private final PasswordEncoder encryptor;
//    private final NoteClient noteClient;

//    @Transactional
//    public User createUser(User user) {
//        if (!StringUtils.isEmpty(user.getFirstName()) && !StringUtils.isEmpty(user.getLastName())) {
//            return repository.save(user);
//        }
//        throw new IllegalArgumentException("User's info is not complete");
//    }

    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
        UserEntity userEntity = mapper.map(userDto, UserEntity.class);
        userEntity.setEncryptedPassword(encryptor.encode(userDto.getPassword()));
        UserEntity persistUser = repository.save(userEntity);
        return mapper.map(persistUser, UserDto.class);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = repository.findByEmail(email);
        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }
        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
    }

    public UserDto getUserDetailsByEmail(String email) {
        UserEntity userEntity = repository.findByEmail(email);
        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }
        return mapper.map(userEntity, UserDto.class);
    }

//    @Transactional
//    public User updateUser(Long id, User user) {
//        User persistUser = repository.findById(id).orElseThrow();
//        if (!StringUtils.isEmpty(persistUser.getFirstName())) {
//            persistUser.setFirstName(user.getFirstName());
//        }
//        if (!StringUtils.isEmpty(persistUser.getLastName())) {
//            persistUser.setLastName(user.getLastName());
//        }
//        return persistUser;
//    }
//
//    @Transactional
//    public String deleteUser(Long id) {
//        User user = repository.findById(id).orElseThrow(IllegalArgumentException::new);
//        repository.delete(user);
//        return "User with id " + user.getId() + " was deleted";
//    }
//
//    @Transactional
//    public User updateUserV2(Long id, User user) {
//        User persistUser = repository.findById(id).orElseThrow();
//        if (!StringUtils.isEmpty(user.getFirstName())) {
//            persistUser.setFirstName(user.getFirstName());
//        }
//        if (!StringUtils.isEmpty(persistUser.getFirstName())) {
//            persistUser.setLastName(user.getLastName());
//        }
//        return persistUser;
//    }

    //    public UserDto getPersonNotesById(Long personId) {
//        User user = userRepository.findById(personId).orElseThrow();
//        List<NoteDto> userNotes = noteClient.getNoteByUserId(personId);
//        return new UserDto(user.getFirstName(), user.getLastName(), userNotes);
//    }
}
