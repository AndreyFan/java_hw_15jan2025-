package de.telran.onlineshop.controller;

import de.telran.onlineshop.dto.ProductDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Products", description = "Контроллер для работы с товарами",
        externalDocs = @ExternalDocumentation(
                description = "Ссылка на общую документацию по товарам",
                url = "https://example.com/docs/products-controller"
        )
)
public interface ProductsControllerInterface {
    @Operation(
            summary = "Все товары",
            description = "Позволяет получить все товары, имеющиеся в магазине"
    )
    public List<ProductDto> getAllProducts();

    @Operation(
            summary = "Информация о товаре по его ID",
            description = "Позволяет получить информацию о товаре по его ID "
    )
    public ProductDto getProductByID(
            @Parameter(description = "идентификатор товара", required = true, example = "5")
            Long id);

    @Operation(
            summary = "Информация о товаре по его названию",
            description = "Позволяет получить информацию о товаре по его названию "
    )
    public ProductDto getProductByName(
            @Parameter(description = "название товара", required = true, example = "Radio")
            String name);

    @Operation(
            summary = "Создание нового товара",
            description = "Позволяет создать новый товар в конце списка "
    )
    public boolean createProduct(
            @Parameter(description = "вводим DTO нового товара, ID оставляем null", required = true)
            ProductDto newProductDto);

    @Operation(
            summary = "Изменение информации о товаре",
            description = "Позволяет изменить информацию о товаре "
    )
    public ProductDto updateProduct(@RequestBody @Valid ProductDto updProductDto);

    @Hidden // скрываем данную функцию для Swagger
    public void deleteProduct(@PathVariable Long id);

}
