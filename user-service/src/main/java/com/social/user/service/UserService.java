package com.social.user.service;

import com.social.common.dto.PageResponse;
import com.social.common.exception.ResourceNotFoundException;
import com.social.common.exception.ValidationException;
import com.social.common.security.PasswordEncoder;
import com.social.user.dto.CreateUserRequest;
import com.social.user.dto.UserDTO;
import com.social.user.model.Role;
import com.social.user.model.User;
import com.social.user.repository.PermissionRepository;
import com.social.user.repository.RoleRepository;
import com.social.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static com.social.common.constant.StringConstants.ROLE_USER;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDTO createUser(CreateUserRequest request) {
        log.info("Creating user with email: {}", request.getEmail());

        // Checking if email already exists
        if (userRepository.existsByEmail(request.getEmail()))
            throw new ValidationException("Email Already Exists");

//        // Check if phone already exists
//        if (request.getPhone() != null && userRepository.existsByPhone(request.getPhone()))
//            throw new ValidationException("Phone number already exists");

        // Create user entity
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhone(request.getPhone());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setStatus(User.UserStatus.ACTIVE);

        // Assign default role
        Role userRole = roleRepository.findByName(ROLE_USER)
                .orElseThrow(() -> new ResourceNotFoundException("Default role not found"));
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);

        // Saving User
        User savedUser = userRepository.save(user);
        log.info("User created successfully with ID: {}", savedUser.getId());


        return UserDTO.fromEntity(user);
    }

    @Cacheable(value = "users", key = "#id")
    public UserDTO getUserById(UUID id) {
        log.info("Fetching user with ID: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));

        return UserDTO.fromEntity(user);
    }

    public UserDTO getUserByEmail(String email) {
        log.info("Fetching user with email: {}", email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));

        return UserDTO.fromEntity(user);
    }

    public PageResponse<UserDTO> getAllUsers(int page, int size, String sortBy, String sortDir) {
        log.info("Fetching all users - page: {}, size: {}", page, size);

        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<User> userPage = userRepository.findAll(pageable);

        return PageResponse.of(
                userPage.getContent().stream().map(UserDTO::fromEntity).toList(),
                userPage.getNumber(),
                userPage.getSize(),
                userPage.getTotalElements(),
                userPage.getTotalPages(),
                userPage.isLast()
        );
    }

    public PageResponse<UserDTO> searchUsers(String search, int page, int size) {
        log.info("Searching users with query: {}", search);

        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = userRepository.searchUsers(search, pageable);

        return PageResponse.of(
                userPage.getContent().stream().map(UserDTO::fromEntity).toList(),
                userPage.getNumber(),
                userPage.getSize(),
                userPage.getTotalElements(),
                userPage.getTotalPages(),
                userPage.isLast()
        );
    }

    public long getActiveUserCount() {
        return userRepository.countActiveUsers();
    }

}
