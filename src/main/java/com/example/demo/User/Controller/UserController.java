package com.example.demo.User.Controller;

import com.example.demo.User.jwt.JwtUtil;
import com.example.demo.User.requestdto.LoginRequestDTO;
import com.example.demo.User.requestdto.SignUpRequestDTO;
import com.example.demo.User.responsedto.ResponseDTO;
import com.example.demo.User.service.UserService;
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

    private final JwtUtil jwtUtil;
    @PostMapping("/signup")//@RequestBody 어노테이션을 사용하는 메소드 파라미터는 HTTP 요청의 본문을 자바 객체로 변환합니다. 이때 변환은 주로 JSON 또는 XML 형식으로 전달된 데이터를 해당 자바 객체로 변환하는 작업을 의미합니다.
    public ResponseDTO signup(@Validated @RequestBody SignUpRequestDTO signUpRequestDTO){
        ResponseDTO responseDTO=userService.signup(signUpRequestDTO);

        return responseDTO;
    }

    @PostMapping("/login")
    public ResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO,
                                          HttpServletResponse res){
        ResponseDTO responseDTO=userService.login(loginRequestDTO, res);

        return responseDTO;
    }

}
