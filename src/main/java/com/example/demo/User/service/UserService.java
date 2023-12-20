package com.example.demo.User.service;

import com.example.demo.User.entity.User;
import com.example.demo.User.entity.UserRoleEnum;
import com.example.demo.User.jwt.JwtUtil;
import com.example.demo.User.repository.UserRepository;
import com.example.demo.User.requestdto.LoginRequestDTO;
import com.example.demo.User.requestdto.SignUpRequestDTO;
import com.example.demo.User.responsedto.ResponseDTO;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserService {
    //final 을 쓴이유UserRepository 필드가 한 번 설정되면 변경되지 않으며, 이로써 불변성, 스레드 안전성, 가독성, 유지보수성 등을 강화하는데 사용됩니다.
    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    private final String ADMIN_TOKEN = "123456789";

    public ResponseDTO signup(SignUpRequestDTO signUpRequestDTO) {
        String username = signUpRequestDTO.getUsername();
        String password = signUpRequestDTO.getPassword();
        String confirmpassword = signUpRequestDTO.getConfirmpassword();

        // 중복된 닉네임인지 확인
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            return  ResponseDTO.builder()
                    .message("중복된 사용자가 존재 합니다.")
                    .build();

        }

        if(!password.equals(confirmpassword)){
            return ResponseDTO.builder()
                    .message("비밀번호와 비밀번호 확인이 일치하지 않습니다.")
                    .build();
        }

        if (password.contains(username)) {
            return ResponseDTO.builder()
                    .message("비밀번호에 아이디를 포함할수 없습니다.")
                    .build();
        }

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (signUpRequestDTO.isAdmin()) {
            if (!ADMIN_TOKEN.equals(signUpRequestDTO.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        // 회원가입 처리
        User user = User.builder()
                .username(username)
                .password(password)
                .role(role)
                .build();

        userRepository.save(user);


        return  ResponseDTO.builder()
                .message("회원가입 성공")
                .build();
    }


    public ResponseDTO login(LoginRequestDTO loginRequestDTO,  HttpServletResponse res){
        String username = loginRequestDTO.getUsername();
        String password = loginRequestDTO.getPassword();

        // 사용자가 존재하는지 확인
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            return ResponseDTO.builder()
                    .message("사용자를 찾을 수 없습니다.")
                    .build();
        }

        User user = userOptional.get();
        // 비밀번호 일치 여부 확인
        if (!user.getPassword().equals(password)) {
            return ResponseDTO.builder()
                    .message("비밀번호가 일치하지 않습니다.")
                    .build();
        }

        // JWT 생성 및 쿠키에 저장 후 Response 객체에 추가
        String token = jwtUtil.createToken(user.getUsername(), user.getRole());
        jwtUtil.addJwtToCookie(token, res);


        return ResponseDTO.builder().
                message("로그인 성공").
                build();
    }
}
