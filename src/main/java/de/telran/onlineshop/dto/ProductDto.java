package de.telran.onlineshop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Schema(description = "Сущность товар")
public class ProductDto {
    @Schema(description = "Уникальный идентификатор товара", example = "6")
    private Long productID;

    @NotNull
    @NotEmpty(message = "invalid name: name is empty")
    @Size(min = 2, max = 30, message = "name mast be 2-30 char ")
    @Schema(description = "Уникальное название товара", example = "Radio")
    private String name;

    @NotNull
    @NotEmpty(message = "invalid name: name is empty")
    @Size(min = 2, max = 30, message = "name mast be 2-30 char ")
    @Schema(description = "Описание товара", example = "FM radio receiver")
    private String description;

    @NotBlank
    @Schema(description = "Стоимость товара ", example = "30.0")
    private double price;

    @NotBlank(message = "invalid category ")
    @Positive
    @Schema(description = "Категория товара ", example = "3")
    private CategoryDto categoryDto;

    @Size(min = 2, max = 30, message = "name mast be 2-30 char ")
    @Schema(description = "Ссылка на изображение товара ", example = "https://example.com/radio.jpg")
    private String imageURL;

    @NotBlank
    @Schema(description = "Скидка на товар в процентах ", example = "3.5")
    private double discountPrice;

    @PastOrPresent
    @Schema(description = "Время создания товарной позиции в формате Timestamp ", example = "")
    private Timestamp createdAt;

    @PastOrPresent
    @Schema(description = "Время изменения описания товарной позиции в формате Timestamp", example = "")
    private Timestamp updatedAt;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto productDto = (ProductDto) o;
        return productID == productDto.productID && Double.compare(price, productDto.price) == 0 && Double.compare(discountPrice, productDto.discountPrice) == 0 && Objects.equals(name, productDto.name) && Objects.equals(description, productDto.description) && Objects.equals(imageURL, productDto.imageURL) && Objects.equals(createdAt, productDto.createdAt) && Objects.equals(updatedAt, productDto.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productID, name, description, price, imageURL, discountPrice, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", imageURL='" + imageURL + '\'' +
                ", discountPrice=" + discountPrice +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

}