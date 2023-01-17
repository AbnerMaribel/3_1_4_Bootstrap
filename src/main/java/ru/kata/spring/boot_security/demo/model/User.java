package ru.kata.spring.boot_security.demo.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Component
@Table(name = "users")
public class User implements UserDetails {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   @Column(name = "email")
   @NotEmpty(message = "Required field")
   @Email(message = "invalid e-mail")
   private String username;

   @Column(name = "name")
   private String name;

   @Column(name = "last_name")
   private String lastName;

   @Column(name = "age")
   private int age;

   @Column(name = "password")
   private String password;

   @ManyToMany(cascade = CascadeType.PERSIST)
   @JoinTable(name = "users_roles",
           joinColumns = @JoinColumn(name = "user_id"),
           inverseJoinColumns = @JoinColumn(name = "role_id"))
   private Set<Role> roles = new HashSet<>();

   public User() {
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return getRoles();
   }

   public String getPassword() {
      return password;
   }

   public String getUsername() {
      return username;
   }
   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }


   public void setUsername(String login) {
      this.username = login;
   }

   public int getAge() {
      return age;
   }

   public void setAge(int age) {
      this.age = age;
   }

   public Set<Role> getRoles() {
      return roles;
   }

   public void setRoles(Set<Role> roles) {
      this.roles = roles;
   }



   public String getRolesToString() {
      String s = getRoles().toString().replaceAll("^\\[|\\]$", "");
      return s.replace("ROLE_", "");
   }


   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      User user = (User) o;
      return Objects.equals(id, user.id) &&
              Objects.equals(age, user.age) &&
              Objects.equals(name, user.name) &&
              Objects.equals(lastName, user.lastName) &&
              Objects.equals(username, user.username) &&
              Objects.equals(password, user.password) &&
              Objects.equals(roles, user.roles);
   }

   @Override
   public int hashCode() {
      return getClass().hashCode();
   }
}