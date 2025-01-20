package de.telran.onlineshop.controller;

import de.telran.onlineshop.dto.CategoryDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Categories", description = "Контроллер для работы с категориями товаров",
        externalDocs = @ExternalDocumentation(
                description = "Ссылка на общую документацию по категориям",
                url = "https://example.com/docs/categories-controller"
        )
)
public interface CategoriesControllerInterface {
    @Operation(
            summary = "Все категории",
            description = "Позволяет получить все категории товаров, имеющиеся в магазине"
    )
    List<CategoryDto> getAllCategories();


    @Operation(
            summary = "Поиск по Id",
            description = "Позволяет найти информация по идентификатору Id категории товара"
    )
    CategoryDto getCategoryById(
            @Parameter(description = "Идентификатор категории", required = true, example = "1")
            Long id);
    @Operation(
            summary = "Поиск категории по названию",
            description = "Позволяет найти номер категории товара по её названию"
    )
    CategoryDto getCategoryByName(
            @Parameter(description = "название категории", required = true, example = "Продукты")
            String name);

    @Operation(
            summary = "создание новой категории",
            description = "Позволяет создать новую категорию товаров, добавление новой в конец списка"
    )
    boolean createCategories(@RequestBody @Valid CategoryDto newCategoryDto);

    @Operation(
            summary = "изменение названия существующей категории",
            description = "Позволяет переименовать  категорию товаров"
    )

    CategoryDto updateCategories(
            @Parameter(description = "нужно ввести номер категории и новое название", required = true, example = "изменённая категория")
            CategoryDto updCategoryDto);


    @Hidden // скрываем для Open Api
    void deleteCategories(@PathVariable Long id);

}
