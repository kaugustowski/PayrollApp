package pl.wizyg.payroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.wizyg.payroll.DTO.UserDTO;

public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Override
//    public User registerNewUserAccount(UserDTO userDto) throws EmailExistsException {
//        if (emailExist(accountDto.getEmail())) {
//            throw new EmailExistsException(
//                    "There is an account with that email adress:" + accountDto.getEmail());
//        }
//        User user = new User();
//        user.setFirstName(userDto.getFirstName());
//        user.setLastName(userDto.getLastName());
//
//        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
//
//        user.setEmail(userDto.getEmail());
//        user.setRole(new Role(Integer.valueOf(1), user));
//        return userRepository.save(user);
//    }
}
