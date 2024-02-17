package com.ndrianja.mongotest.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Tag(name = "User", description = "Endpoints for user management")
public class UserController {

    private final UserService userService;
    @PostMapping("/create")
    public ResponseEntity<UserAddResponse> addUser(@RequestBody UserAddRequest user) {

        return ResponseEntity.ok(userService.addUser(user));
    }

    @GetMapping()
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    //@PostMapping("/create")
    //    public UserResponse addUser(@RequestBody SignupRequest signupRequest) {
    //        log.info("Request : {}", signupRequest);
    //        boolean find = userService.saveUser(signupRequest.toSignupModel());
    //        String resMessage = find ? "User Created" : "User Already exists";
    //        return UserResponse.builder().resMessage(resMessage).build();
    //    }


    //Ao amin'ny service ireto
    //public boolean saveUser(SignupModel signupModel) {
    //        if(!StringUtils.equals(signupModel.getPassword(), signupModel.getConfirmPassword())) {
    //            return Boolean.FALSE;
    //        }
    //
    //        UserModel byEmail = userRepository.findByEmail(signupModel.getEmail());
    //        if(byEmail == null) {
    //            userRepository.save(signupModel);
    //            return Boolean.TRUE;
    //        }
    //        return Boolean.FALSE;
    //    }
    //
    //
    //    public boolean login(UserModel toLoginModel) {
    //        UserModel userModel = userRepository.findByEmail(toLoginModel.getEmail());
    //        if(userModel == null) {
    //            return Boolean.FALSE;
    //        }
    //        if(StringUtils.equals(userModel.getPassword(), toLoginModel.getPassword())) {
    //            return Boolean.TRUE;
    //        }
    //        return Boolean.FALSE;
    //    }
    //
    //    public boolean updatePassword(ForgotModel toForgotModel) {
    //        UserModel userModel = userRepository.findByEmail(toForgotModel.getEmail());
    //        if(userModel == null ||
    //                !StringUtils.equals(toForgotModel.getPassword(), toForgotModel.getConfirmPassword())) {
    //            return  Boolean.FALSE;
    //        }
    //        userRepository.forgotPassword(toForgotModel.getEmail(), toForgotModel.getPassword(), toForgotModel.getConfirmPassword());
    //        return Boolean.TRUE;
    //    }
    //}
}
