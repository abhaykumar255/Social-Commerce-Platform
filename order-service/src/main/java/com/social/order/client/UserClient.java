package com.social.order.client;

import com.social.common.dto.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "user-service", path = "/api/v1/users")
public interface UserClient {

    @GetMapping("/{id}")
    ApiResponse<UserDTO> getUserById(@PathVariable UUID id);

    // UserDTO inner class for Feign response
    class UserDTO {
        public UUID id;
        public String email;
        public String firstName;
        public String lastName;
        public String phone;
    }
}
