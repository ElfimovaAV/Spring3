package ru.geekbrain.example3sem3hometask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrain.example3sem3hometask.domain.User;
import ru.geekbrain.example3sem3hometask.services.RegistrationService;
import ru.geekbrain.example3sem3hometask.services.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private RegistrationService service;
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> userList() { return service.getDataProcessingService().getRepository().getUsers(); }

    @PostMapping("/body")
    public String userAddFromBody(@RequestBody User user)
    {
        service.getDataProcessingService().getRepository().getUsers().add(user);
        return "User added from body!";
    }
    /**
     * Перехват команды на регистрацию пользователя
     * @param user
     * @return зарегистрированного пользователя
     */
    @PostMapping("/registration")
    public User addByRegistration(@RequestBody User user){
        return service.processRegistration(user);
    }

    /**
     *Перехват команды на создание пользователя из параметров HTTP запроса
     * @param name
     * @param age
     * @param email
     * @return добавленного пользователя
     */
    @RequestMapping("/param")
    public User userAddFromParams(@RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "age", required = false) int age,
                                    @RequestParam(value = "email", required = false) String email){

        User user = userService.createUser(name, age, email);
        service.getDataProcessingService().getRepository().getUsers().add(user);
        return user;
    }
}
