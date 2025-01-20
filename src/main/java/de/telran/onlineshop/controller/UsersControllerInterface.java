package de.telran.onlineshop.controller;

import de.telran.onlineshop.aspect.LogAnnotation;
import de.telran.onlineshop.dto.UserDto;
import de.telran.onlineshop.service.UsersService;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Users", description = "Контроллер для работы с пользователями (админами и покупателями)",
        externalDocs = @ExternalDocumentation(
                description = "Ссылка на общую документацию по работе с пользователями",
                url = "https://example.com/docs/users-controller"
        )
)
public interface UsersControllerInterface {

    @Operation(
            summary = "Все пользователи",
            description = "Позволяет получить информацию о всех пользователях"
    )
    List<UserDto> getAllUsers();

    @Operation(
            summary = "Вывод информации о пользователе по его ID",
            description = "Позволяет получить информацию о пользователе с конкретным ID"
    )
    ResponseEntity<UserDto> getUserById(
            @Parameter(description = "Идентификатор пользователя", required = true, example = "1")
            Long id);


    @Operation(
            summary = "Вывод информации о пользователе по его имени",
            description = "Позволяет получить информацию о пользователе по его имени"
    )
    UserDto getUserByName(
            @Parameter(description = "Имя пользователя", required = true, example = "Peter")
            String name);

    @Operation(
            summary = "Создаем нового пользователя в системе",
            description = "Позволяет создать нового пользователя системы. Добавление в конец списка."
    )
    ResponseEntity<Boolean> createUsers(@RequestBody @Valid UserDto newUserDto);

    @Operation(
            summary = "Изменение информации о пользователе по его ID",
            description = "Позволяет изменить информацию о пользователе с конкретным ID"
    )
    public ResponseEntity<UserDto> updateUsers(@RequestBody @Valid UserDto updUserDto);

    @Operation(
            summary = "Удаление пользователя из системы",
            description = "Позволяет удалить пользователя по его ID. Скрыто в открытом API"
    )
    @Hidden
    ResponseEntity<Void> deleteUsers(@PathVariable Long id);
}