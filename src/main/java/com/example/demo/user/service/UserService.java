package com.example.demo.user.service;

import com.example.demo.user.entity.User;
import com.example.demo.user.entity.UserRoleEnum;
import com.example.demo.user.jwt.JwtUtil;
import com.example.demo.user.repository.UserRepository;
import com.example.demo.user.requestdto.LoginRequestDTO;
import com.example.demo.user.requestdto.SignUpRequestDTO;
import com.example.demo.user.responsedto.UserResponseDTO;
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

    public UserResponseDTO signup(SignUpRequestDTO signUpRequestDTO) {
        String username = signUpRequestDTO.getUsername();
        String password = signUpRequestDTO.getPassword();
        String confirmpassword = signUpRequestDTO.getConfirmpassword();

        // 중복된 닉네임인지 확인
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            return  UserResponseDTO.builder()
                    .message("중복된 사용자가 존재 합니다.")
                    .build();

        }

        if(!password.equals(confirmpassword)){
            return UserResponseDTO.builder()
                    .message("비밀번호와 비밀번호 확인이 일치하지 않습니다.")
                    .build();
        }

        if (password.contains(username)) {
            return UserResponseDTO.builder()
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


        return  UserResponseDTO.builder()
                .message("회원가입 성공")
                .build();
    }


    public UserResponseDTO login(LoginRequestDTO loginRequestDTO, HttpServletResponse res){
        String username = loginRequestDTO.getUsername();
        String password = loginRequestDTO.getPassword();

        // 사용자가 존재하는지 확인
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            return UserResponseDTO.builder()
                    .message("사용자를 찾을 수 없습니다.")
                    .build();
        }

        User user = userOptional.get();
        // 비밀번호 일치 여부 확인
        if (!user.getPassword().equals(password)) {
            return UserResponseDTO.builder()
                    .message("비밀번호가 일치하지 않습니다.")
                    .build();
        }

        res.setHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(loginRequestDTO.getUsername()));


        return UserResponseDTO.builder().
                message("로그인 성공").
                build();
    }
}
