package com.example.maktabdarsliklariapp.entity;

import com.example.maktabdarsliklariapp.entity.enums.PermissionEnum;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username, password;
    @ManyToMany(fetch = FetchType.EAGER)
//    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roleList; //user_roleList
    @OneToMany
    @ToString.Exclude
    private List<Book> savedBooks;
    private boolean active = true;

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        //hasAuthority
        Set<GrantedAuthority> authorities = new LinkedHashSet<>();
        for (Role role : this.roleList) {
            for (PermissionEnum permissionEnum : role.getPermissionEnum()) {
                authorities.add(new SimpleGrantedAuthority(permissionEnum.name()));
            }
        }

        //preauthorize hasRole
//        for (Role role : this.roleList) {
//            authorities.add(role.getRoleName());
//        }
        return authorities;

    }

    public User(String username, String password, Set<Role> roleList) {
        this.username = username;
        this.password = password;
        this.roleList = roleList;
    }

}
