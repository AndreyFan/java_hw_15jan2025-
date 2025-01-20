package de.telran.onlineshop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import de.telran.onlineshop.entity.CartEntity;
import de.telran.onlineshop.entity.FavoritesEntity;
import de.telran.onlineshop.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)  // если 0 , то ВСЕ нулевые  не попадут в выдачу
@Schema(description = "Сущность пользователи")
public class UserDto {
    @Schema(description = "Уникальный идентификатор пользователя", example = "1" /*, accessMode = Schema.AccessMode.READ_ONLY */)
    @Max(1_000_000)
    private Long userID;
    @Schema(description = "Имя пользователя", example = "Peter")
    @Size(min=2, max=30, message = "Invalid name: must be of 2-30 characters")
    @NotBlank(message = " Name must be NotBlank")
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)// если 0 , то поле не попадёт в выдачу
    @Email(message = "Invalid email")
    @NotBlank(message = "email not be blank")
    @Schema(description = "email пользователя", example = "name@gmail.com")
    private String email;

    @NotBlank(message = "PhoneNumber not be blank")
    @Pattern(regexp = "^\\d{12}$", message = "invalid phone number")
    @Schema(description = "телефон пользователя: Внимание ! должен иметь 12 цифр и не иметь других символов", example = "015128512451")
    private String phoneNumber;
    @NotNull
    @Schema(description = "Уникальный пароль пользователя", example = "peterPasswordHash" /*, accessMode = Schema.AccessMode.READ_ONLY */)
    private String passwordHash;

@NotNull
@Schema(description = "Роль пользователя. Имеет только 2 значения: CLIENT / ADMINISTRATOR", example = "CLIENT" )

    private Role role;

//  @JsonProperty(required = false)
//    private Set<FavoriteDto> favorites =new HashSet<>();

    private CartDto cart;
    public UserDto() {
    }

    public UserDto(Long userID, String name, String email, String phoneNumber, String passwordHash, Role role) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.passwordHash = passwordHash;
        this.role = role;
    }

//    public Set<FavoriteDto> getFavorites() {
//        return favorites;
//    }

//    public void setFavorites(Set<FavoriteDto> favorites) {
//        this.favorites = favorites;
//    }

//    public void setCart(CartDto cart) {
//        this.cart = cart;
//    }

//    public CartDto getCart() {
//        return cart;
//    }

    public Long getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return userID == userDto.userID && Objects.equals(name, userDto.name) && Objects.equals(email, userDto.email) && Objects.equals(phoneNumber, userDto.phoneNumber) && Objects.equals(passwordHash, userDto.passwordHash) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, name, email, phoneNumber, passwordHash);
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                '}';
    }
}