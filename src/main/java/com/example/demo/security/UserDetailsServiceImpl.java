package com.example.demo.security;


import com.example.demo.user.entity.User;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl {
    private final UserRepository userRepository;

    public UserDetailsImpl getUserDetails(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("해당 유저를 찾을 수 없습니다." + username));
        return new UserDetailsImpl(user);
    }
    public UserDetailsImpl getUserById(Long userId){
        User user = userRepository.findById(userId)
                    .orElseThrow(() ->
                            new UsernameNotFoundException("해당 유저를 찾을 수 없습니다." + userId));
        return new UserDetailsImpl(user);
        }
}
