package com.example.demo.user.Controller;

import com.example.demo.user.jwt.JwtUtil;
import com.example.demo.user.requestdto.LoginRequestDTO;
import com.example.demo.user.requestdto.SignUpRequestDTO;
import com.example.demo.user.responsedto.UserResponseDTO;
import com.example.demo.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/signup")//@RequestBody 어노테이션을 사용하는 메소드 파라미터는 HTTP 요청의 본문을 자바 객체로 변환합니다. 이때 변환은 주로 JSON 또는 XML 형식으로 전달된 데이터를 해당 자바 객체로 변환하는 작업을 의미합니다.
    public UserResponseDTO signup(@Validated @RequestBody SignUpRequestDTO signUpRequestDTO){
        UserResponseDTO userResponseDTO =userService.signup(signUpRequestDTO);

        return userResponseDTO;
    }

    @PostMapping("/login")
    public UserResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO,
                                 HttpServletResponse res){
        UserResponseDTO userResponseDTO =userService.login(loginRequestDTO, res);

        return userResponseDTO;
    }

}
