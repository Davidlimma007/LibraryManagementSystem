package com.library.management.service;

import com.library.management.exception.PersonNotFoundException;
import com.library.management.model.Employee;
import com.library.management.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    //Create
    // Método para salvar um funcionário
    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee); // Salva um funcionário no banco de dados
    }

    //Read
    // Método para encontrar um funcionário por ID
    public Employee findEmployeeById(Long id){
        return employeeRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id)); // Lança uma exceção se o funcionário não for encontrado
    }

    //Read
    // Método para retornar todos os funcionários
    public List<Employee> findAllEmployees(){
        return employeeRepository.findAll(); // Retorna todos os funcionários do banco de dados
    }


    //Update
    // Método para atualizar um funcionário existente
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = findEmployeeById(id); // Encontra o funcionário pelo ID

        // Atualiza os detalhes do funcionário
        employee.setName(employeeDetails.getName());
        employee.setDocument(employeeDetails.getDocument());
        employee.setBirthDate(employeeDetails.getBirthDate());
        employee.setGender(employeeDetails.getGender());
        employee.setPosition(employeeDetails.getPosition());

        return employeeRepository.save(employee); // Salva as alterações no banco de dados
    }

    //Delete
    // Método para deletar um funcionário por ID
    public void deleteEmployee(Long id){
        Employee employee = findEmployeeById(id); // Encontra o funcionário pelo ID
        employeeRepository.delete(employee); // Deleta o funcionário do banco de dados
    }
}
