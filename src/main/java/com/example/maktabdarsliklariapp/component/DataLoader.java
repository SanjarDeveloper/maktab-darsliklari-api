package com.example.maktabdarsliklariapp.component;

import com.example.maktabdarsliklariapp.entity.Role;
import com.example.maktabdarsliklariapp.entity.User;
import com.example.maktabdarsliklariapp.entity.enums.PermissionEnum;
import com.example.maktabdarsliklariapp.entity.enums.RoleEnum;
import com.example.maktabdarsliklariapp.repository.RoleRepository;
import com.example.maktabdarsliklariapp.repository.UsersRepository;
import com.example.maktabdarsliklariapp.security.SecurityConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final UsersRepository userRepository;
    private final RoleRepository roleRepository;
    private final SecurityConfig securityConfig;

    @Value("${spring.sql.init.mode}")
    String mode;

    @Override
    public void run(String... args) throws Exception {
        if (mode.equals("always")) {
            PermissionEnum[] values = PermissionEnum.values();

            Set<Role> roleList = new LinkedHashSet<>();
            Role userRole = roleRepository.save(new Role(RoleEnum.ROLE_USER, new ArrayList<>(Arrays.asList(PermissionEnum.READ_ALL_BOOK, PermissionEnum.READ_ONE_BOOK, PermissionEnum.READ_ALL_SAVED, PermissionEnum.READ_ONE_SAVED, PermissionEnum.CREATE_SAVED, PermissionEnum.DELETE_SAVED))));
            Role adminRole = roleRepository.save(new Role(RoleEnum.ROLE_ADMIN, Arrays.asList(values)));
            roleList.add(userRole);
            roleList.add(adminRole);

            Set<Role> admin = new LinkedHashSet<>();
            admin.add(adminRole);

            Set<Role> user = new LinkedHashSet<>();
            user.add(userRole);

            userRepository.save(new User("Admin", securityConfig.passwordEncoder().encode("123"),admin));
            userRepository.save(new User("User", securityConfig.passwordEncoder().encode("123"),user));
        }
    }
}
