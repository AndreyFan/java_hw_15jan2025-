package de.telran.onlineshop.dto;

import de.telran.onlineshop.entity.ProductsEntity;
import de.telran.onlineshop.entity.UsersEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FavoriteDto {
    @PositiveOrZero
    private long favoriteID;

    @NotEmpty
    private UserDto user;

    @NotEmpty
    private ProductDto product;

}
