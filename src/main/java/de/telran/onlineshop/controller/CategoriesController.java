package de.telran.onlineshop.controller;

import de.telran.onlineshop.dto.CategoryDto;
import de.telran.onlineshop.service.CategoriesService;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
@Slf4j
public class CategoriesController implements CategoriesControllerInterface{
    //@Autowired - иньекция через value (не рекомендуемая из-за Reflection)
    private CategoriesService categoryService;

    //@Autowired - иньекция через конструктор (рекомендуемая !!!), авто аннотирование с версии 3.0
    public CategoriesController(CategoriesService categoryService) {
        this.categoryService = categoryService;
    }

    // @Autowired - иньекция через сеттер (обязательно использовать аннотацию)
    public void setCategoryService(CategoriesService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping  //select
    public List<CategoryDto> getAllCategories() {
        long start = System.currentTimeMillis();
        List<CategoryDto> result = categoryService.getAllCategories();
        log.info("getAllCategories -  " + (System.currentTimeMillis() - start));  //простой вариант подключения логирования
        return result;
    }

    @GetMapping(value = "/find/{id}")
    public CategoryDto getCategoryById(@PathVariable @Valid Long id) { ///categories/find/3
        return categoryService.getCategoryById(id);
    }

    // Экранирование кириллицы для url - https://planetcalc.ru/683/
    @GetMapping(value = "/get")
    public CategoryDto getCategoryByName(@RequestParam String name) { ///categories/get?name=Other,k=2
        return categoryService.getCategoryByName(name);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping //Jackson
    public boolean createCategories(@RequestBody @Valid CategoryDto newCategoryDto) { //insert
        return categoryService.createCategories(newCategoryDto);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping
    public CategoryDto updateCategories(@RequestBody @Valid CategoryDto updCategoryDto) { //update
        return categoryService.updateCategories(updCategoryDto);
    }


    @DeleteMapping(value = "/{id}")
    public void deleteCategories(@PathVariable Long id) { //delete
        categoryService.deleteCategories(id);
    }

}