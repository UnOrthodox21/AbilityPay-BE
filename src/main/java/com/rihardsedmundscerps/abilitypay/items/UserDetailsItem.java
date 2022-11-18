package com.rihardsedmundscerps.abilitypay.items;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Objects;

@JsonDeserialize(builder = UserDetailsItem.Builder.class)
public class UserDetailsItem {

    private long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private long phone;
    private String password;
    private String roles;
    private boolean enabled;
    private GrantedAuthority authorities;
    private String image;
    private String jwt;

    private UserDetailsItem(UserDetailsItem.Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.email = builder.email;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.address = builder.address;
        this.phone = builder.phone;
        this.password = builder.password;
        this.roles = builder.roles;
        this.enabled = builder.enabled;
        this.authorities = builder.authorities;
        this.image = builder.image;
        this.jwt = builder.jwt;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public long getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public String getRoles() {
        return roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return (Collection<? extends GrantedAuthority>) authorities;
    }

    public String getImage() {
        return image;
    }

    public String getJwt() {
        return jwt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetailsItem that = (UserDetailsItem) o;
        return id == that.id && phone == that.phone && enabled == that.enabled && Objects.equals(username, that.username) && Objects.equals(email, that.email) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(address, that.address) && Objects.equals(password, that.password) && Objects.equals(roles, that.roles) && Objects.equals(authorities, that.authorities) && Objects.equals(image, that.image) && Objects.equals(jwt, that.jwt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, firstName, lastName, address, phone, password, roles, enabled, authorities, image, jwt);
    }

    @Override
    public String toString() {
        return "UserDetailsItem{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", phone=" + phone +
                ", password='" + password + '\'' +
                ", roles='" + roles + '\'' +
                ", enabled=" + enabled +
                ", authorities=" + authorities +
                ", image='" + image + '\'' +
                ", jwt='" + jwt + '\'' +
                '}';
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static final class Builder {

        private long id;
        private String username;
        private String email;
        private String firstName;
        private String lastName;
        private String address;
        private long phone;
        private String password;
        private String roles;
        private boolean enabled;
        private GrantedAuthority authorities;
        private String image;
        private String jwt;

        public UserDetailsItem.Builder id(long id) {
            this.id = id;
            return this;
        }

        public UserDetailsItem.Builder username(String username) {
            this.username = username;
            return this;
        }

        public UserDetailsItem.Builder email(String email) {
            this.email = email;
            return this;
        }

        public UserDetailsItem.Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserDetailsItem.Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserDetailsItem.Builder address(String address) {
            this.address = address;
            return this;
        }

        public UserDetailsItem.Builder phone(long phone) {
            this.phone = phone;
            return this;
        }

        public UserDetailsItem.Builder password(String password) {
            this.password = password;
            return this;
        }

        public UserDetailsItem.Builder roles(String roles) {
            this.roles = roles;
            return this;
        }

        public UserDetailsItem.Builder enabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public UserDetailsItem.Builder authorities(Collection<? extends GrantedAuthority> authorities) {
            this.authorities = (GrantedAuthority) authorities;
            return this;
        }

        public UserDetailsItem.Builder image(String image) {
            this.image = image;
            return this;
        }

        public UserDetailsItem.Builder jwt(String jwt) {
            this.jwt = jwt;
            return this;
        }

        public UserDetailsItem build() {
            return new UserDetailsItem(this);
        }

    }
}
