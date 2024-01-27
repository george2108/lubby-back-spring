package com.example.lubby.auth.services;

import com.example.lubby.auth.dto.LoginRequestDTO;
import com.example.lubby.auth.dto.LoginResponseDTO;
import com.example.lubby.auth.dto.RegisterResponseDTO;
import com.example.lubby.common.dto.HttpException;
import com.example.lubby.security.jwt.JWTUtils;
import com.example.lubby.users.dto.CreateUserDTO;
import com.example.lubby.users.UserEntity;
import com.example.lubby.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class AuthService implements IAuthService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    JWTUtils jwtUtils;

    @Override
    public ResponseEntity<LoginResponseDTO> login(LoginRequestDTO data) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword())
            );
            System.out.println(authentication.getPrincipal());

            String email = authentication.getName();

            Optional<UserEntity> user = userRepository.findByEmail(email);

            if(user.isEmpty()){
                HttpException notFound = HttpException.builder()
                        .message("Usuario no encontrado")
                        .status(HttpStatus.NOT_FOUND)
                        .build();
                return new ResponseEntity(notFound, HttpStatus.NOT_FOUND);
            }

            String token = jwtUtils.generateAccessToken(user.get().getEmail());
            UserEntity userEntity = UserEntity.builder()
                    .id(user.get().getId())
                    .email(user.get().getEmail())
                    .active(user.get().getActive())
                    .firstName(user.get().getFirstName())
                    .lastName(user.get().getLastName())
                    .createdAt(user.get().getCreatedAt())
                    .updatedAt(user.get().getUpdatedAt())
                    .build();
            LoginResponseDTO responseDTO = LoginResponseDTO.builder()
                    .accessToken(token)
                    .user(userEntity)
                    .build();

            System.out.println(token);

            return new ResponseEntity<LoginResponseDTO>(responseDTO, HttpStatus.OK);
        }catch (BadCredentialsException e){
            HttpException error = HttpException.builder()
                    .message("Las credenciales son invalidas")
                    .status(HttpStatus.UNAUTHORIZED)
                    .statusCode(HttpStatus.UNAUTHORIZED.value())
                    .build();
            return new ResponseEntity(error, HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            HttpException error = HttpException.builder()
                    .message(e.getMessage())
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .build();
            return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<RegisterResponseDTO> register(CreateUserDTO userDTO) {
        Optional<UserEntity> userExists = userRepository.findByEmail(userDTO.getEmail());

        if(userExists.isPresent()){
            HashMap<String, String> response = new HashMap<>();
            response.put("message", "Usuario duplicado");
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }

        UserEntity userEntity = UserEntity.builder()
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .active(Boolean.TRUE)
                .build();

        UserEntity userCreated = userRepository.save(userEntity);

        String token = jwtUtils.generateAccessToken(userCreated.getEmail());
        UserEntity userResponse = UserEntity.builder()
                .id(userCreated.getId())
                .email(userCreated.getEmail())
                .firstName(userCreated.getFirstName())
                .lastName(userCreated.getLastName())
                .createdAt(userCreated.getCreatedAt())
                .updatedAt(userCreated.getUpdatedAt())
                .active(userCreated.getActive())
                .build();

        System.out.println(userCreated);
        RegisterResponseDTO responseDTO = RegisterResponseDTO.builder()
                .accessToken(token)
                .user(userResponse)
                .build();

        return new ResponseEntity<RegisterResponseDTO>(responseDTO, HttpStatus.CREATED);
    }
}
