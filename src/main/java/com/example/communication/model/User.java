package com.example.communication.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "usr")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(unique = true)
  @NotEmpty(message = "Username cannot be empty")
  @Size(min = 3, max = 16, message = "Username should be in range from 3 to 16 symbols")
  private String username;

  @NotEmpty(message = "Password cannot be empty")
  @Size(min = 6, max = 16, message = "Password should be in range from 6 to 16 symbols")
  private String password;

  @Column(unique = true)
  @NotEmpty(message = "Email cannot be empty")
  private String email;

  private boolean active;

  private String profilePic;

  @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
  @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
  @Enumerated(EnumType.STRING)
  private Set<Role> roles;

  @ManyToMany
  @JoinTable(
          name = "user_subscriptions",
          joinColumns = { @JoinColumn(name = "channel_id") },
          inverseJoinColumns = { @JoinColumn(name = "subscriber_id") }
  )
  private Set<User> subscribers = new HashSet<>();

  @ManyToMany
  @JoinTable(
          name = "user_subscriptions",
          joinColumns = { @JoinColumn(name = "subscriber_id") },
          inverseJoinColumns = { @JoinColumn(name = "channel_id") }
  )
  private Set<User> subscriptions = new HashSet<>();

  public User(String username, String password, String email, boolean active) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.active = active;
  }

  public User(
      @Size(min = 6, max = 16) String username,
      @Size(min = 6, max = 16) String password,
      @Email String email, boolean active, Set<Role> roles) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.active = active;
    this.roles = roles;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return getRoles();
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
    return isActive();
  }

  public boolean isAdmin() {
    return roles.contains(Role.ADMIN);
  }

  //Bad
  public boolean isAuthorized() {
    return !roles.isEmpty();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(id, user.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
