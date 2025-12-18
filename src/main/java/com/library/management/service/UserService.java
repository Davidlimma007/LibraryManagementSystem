package com.library.management.service;

import com.library.management.exception.PersonNotFoundException;
import com.library.management.model.User;
import com.library.management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //Create
    // Método para salvar um usuário
    public User saveUser(User user){
        return userRepository.save(user); // Salva um usuário no banco de dados
    }

    //Read
    // Método para encontrar um usuário por ID
    public User findUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id)); // Lança uma exceção se o usuário não for encontrado
    }

    //Read
    // Método para retornar todos os usuários
    public List<User> findAllUsers(){
        return userRepository.findAll(); // Retorna todos os usuários do banco de dados
    }

    //Update
    // Método para atualizar um usuário existente
    public User updateUser(Long id, User userDetails) {
        User user = findUserById(id); // Encontra o usuário pelo ID

        // Atualiza os detalhes do usuário
        user.setName(userDetails.getName());
        user.setDocument(userDetails.getDocument());
        user.setBirthDate(userDetails.getBirthDate());
        user.setGender(userDetails.getGender());

        return userRepository.save(user); // Salva as alterações no banco de dados
    }

    //Delete
    // Método para deletar um usuário por ID
    public void deleteUser(Long id){
        User user = findUserById(id); // Encontra o usuário pelo ID
        userRepository.delete(user); // Deleta o usuário do banco de dados
    }
}
