package com.library.management.controller;

import com.library.management.model.Employee;
import com.library.management.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    //Create
    // Endpoint para criar um novo funcionário (POST)
    @PostMapping // Mapeia requisições POST para este método
    public Employee createEmployee(@RequestBody Employee employee){
        // @RequestBody indica que o corpo da requisição será mapeado para o objeto Employee
        return employeeService.saveEmployee(employee); // Chama o serviço para salvar um funcionário
    }

    //Read All
    // Endpoint para obter todos os funcionários (GET)
    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.findAllEmployees(); // Chama o serviço para obter todos os funcionários
    }

    //Read by ID
    // Endpoint para obter um funcionário por ID (GET)
    @GetMapping("/{id}") // Mapeia requisições GET para /api/v1/employees/{id}
    public Employee getEmployeeById(@PathVariable Long id) {
        // @PathVariable indica que o ID será extraído da URL
        return employeeService.findEmployeeById(id); // Chama o serviço para encontrar um funcionário
    }

    //Update
    // Endpoint para atualizar um funcionário existente (PUT)
    @PutMapping("/{id}") // Mapeia requisições PUT para /api/v1/employees/{id}
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        // @PathVariable para o ID e @RequestBody para os detalhes
        return employeeService.updateEmployee(id, employeeDetails); // Chama o serviço para atualizar o funcionário
    }

    //Delete
    // Endpoint para deletar um funcionário por ID (DELETE)
    @DeleteMapping("/{id}") // Mapeia requisições DELETE para /api/v1/employees/{id}
    public void deleteEmployee(@PathVariable Long id) {
        // @PathVariable indica que o ID será extraído da URL
        employeeService.deleteEmployee(id); // Chama o serviço para deletar o funcionário
    }
}
