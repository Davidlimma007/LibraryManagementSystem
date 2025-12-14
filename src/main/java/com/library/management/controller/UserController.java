package com.library.management.controller;


import com.library.management.model.User;
import com.library.management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //Create
    // Endpoint para criar um novo usuário (POST)
    @PostMapping // Mapeia requisições POST para este método
    public User createUser(@RequestBody User user){
        // @RequestBody indica que o corpo da requisição será mapeado para o objeto User
        return userService.saveUser(user); // Chama o serviço para salvar um usuário
    }

    //Read All
    // Endpoint para obter todos os usuários (GET)
    @GetMapping // Mapeia para Get /api/v1/users
    public List<User> getAllUsers(){
        return userService.findAllUsers(); // Chama o serviço para obter todos os usuários
    }

    //Read by ID
    // Endpoint para obter um usuário por ID (GET)
    @GetMapping("/{id}") // Mapeia requisições GET para /api/v1/users/{id}
    public User getUserById(@PathVariable Long id) {
        // @PathVariable indica que o ID será extraído da URL
        return userService.findUserById(id); // Chama o serviço para encontrar um usuário pelo ID
    }

    //Update
    // Endpoint para atualizar um usuário existente (PUT)
    @PutMapping("/{id}") // Mapeia requisições PUT para /api/v1/users/{id}
    public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        // @PathVariable para o ID e @RequestBody para os detalhes do usuário
        return userService.updateUser(id, userDetails); // Chama o serviço para atualizar o usuário
    }

    // Delete
    // Endpoint para deletar um usuário por ID (DELETE)
    @DeleteMapping("/{id}") // Mapeia requisições DELETE para /api/v1/users/{id}
    public void deleteUser(@PathVariable Long id) {
        // @PathVariable indica que o ID será extraído da URL
        userService.deleteUser(id); // Chama o serviço para deletar o usuário
    }

}
