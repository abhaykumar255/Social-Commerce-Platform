package com.social.user.service;

import com.social.common.dto.PageResponse;
import com.social.common.exception.ResourceNotFoundException;
import com.social.common.exception.ValidationException;
import com.social.common.security.PasswordEncoder;
import com.social.user.dto.ChangePasswordRequest;
import com.social.user.dto.CreateUserRequest;
import com.social.user.dto.UpdateUserRequest;
import com.social.user.dto.UserDTO;
import com.social.user.model.Role;
import com.social.user.model.User;
import com.social.user.repository.PermissionRepository;
import com.social.user.repository.RoleRepository;
import com.social.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    @Transactional
    @CacheEvict(value = "users", key = "#id")
    public UserDTO updateUser(UUID id, UpdateUserRequest request) {
        log.info("Updating user with ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));

        if (request.getFirstName() != null)
            user.setFirstName(request.getFirstName());
        if (request.getLastName() != null)
            user.setLastName(request.getLastName());

        if (request.getPhone() != null) {
            // Checking if a phone already exists for another user
//            userRepository.findByPhone(request.getPhone()).ifPresent(existingUser -> {
//                if (!existingUser.getId().equals(id)) {
//                    throw new ValidationException("Phone number already exists");
//                }
//            });
            user.setPhone(request.getPhone());
        }
        if (request.getAvatarUrl() != null)
            user.setAvatarUrl(request.getAvatarUrl());
        if (request.getDateOfBirth() != null)
            user.setDateOfBirth(request.getDateOfBirth());

        User updatedUser = userRepository.save(user);
        log.info("User updated successfully with ID: {}", updatedUser.getId());

        return UserDTO.fromEntity(updatedUser);
    }

    @Transactional
    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(UUID id) {
        log.info("Deleting user with ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));

        // Soft delete
        user.setDeleted(true);
        user.setStatus(User.UserStatus.DELETED);
        userRepository.save(user);
        userRepository.delete(user);

        log.info("User deleted successfully with ID: {}", id);
    }

    @Transactional
    public void changePassword(UUID id, ChangePasswordRequest request) {
        log.info("Changing password for user with ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));

        // Verifying the current password
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword()))
            throw new ValidationException("Current password is incorrect");

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        log.info("Password changed successfully for user with ID: {}", id);
    }

    @Transactional
    public void updateLastLogin(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
        user.setLastLoginAt(LocalDateTime.now());
        user.setFailedLoginAttempts(0);
        userRepository.save(user);
    }

    @Transactional
    public void incrementFailedLoginAttempts(String email) {
        userRepository.findByEmail(email).ifPresent(user -> {
            user.setFailedLoginAttempts(user.getFailedLoginAttempts() + 1);
            if (user.getFailedLoginAttempts() >= 5) {
                user.setLockedUntil(LocalDateTime.now().plusMinutes(30));
                log.warn("User account locked for 30 minutes due to multiple failed login attempts: {}", email);
            }
            userRepository.save(user);
        });
    }

    @Transactional
    public void resetFailedLoginAttempts(UUID id){
        userRepository.findById(id).ifPresent( user -> {
            user.setFailedLoginAttempts(0);
            user.setLockedUntil(null);
            userRepository.save(user);
            log.debug("Reset failed login attempts for user: {}", id);
        });
    }

    public long getActiveUserCount() {
        return userRepository.countActiveUsers();
    }

}
