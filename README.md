# Library Management System

**Descrição**

Este projeto é um sistema de gerenciamento de biblioteca, desenvolvido para fins de estudo e aplicação de boas práticas de engenharia de software, como APIs RESTful, Spring Boot e Docker (para o banco de dados PostgreSQL).

**Regras de Negócio (core)**
* Um usuário pode ter, no máximo, 3 livros emprestados simutaniamente.
* Prazo de devolução: 7 dias, renovável uma vez por mais 7 dias.
* Multa por atraso: R$ 50,00 + R$ 2.00 por dia de atraso.
* Punição: Usuário com atraso ficam na lista negra por 1 mês, impedidos de realizar novos empréstimos.

---
## Status do Projeto - Log de Desenvolvimento

### **Dia 1: Configuração do Projeto e Ambiente**
- [x] Configuração do projeto Spring Boot.
- [x] Inicialização do repositório Git e criação do 'README.md'.
- [x] Setup do Docker para PostgreSQL.
- [x] Configuração do 'application.properties' para conexão com o banco de dados.

---
### **Dia 2: Modelagem das Entidades (Book e Person) e Enums**
- [x] Criação da entidade Livro.
- [x] Criação da entidade abstrata Pessoa.
- [x] Criação do Enum gênero dos livros.
- [x] Criação do Enum gênero das pessoas.

---
### **Dia 3: Modelagem das Entidades (Funcionário e Cliente)**
- [x] Criação da entidade Funcionário.
- [x] Criação da entidade Cliente.
- [x] Criação da endidade empréstimo

---
### **Dia 4: Repositórios e Serviços**
- [x] Criação dos repositórios JPA para todas as entidades.
- [x] criar Exceptions personalizadas para Livro não encontrado e Pessoa Não encontrada.
- [x] Implementação do pacote service do book (criando o CRUD completo).
- [x] Implementação do pacote service do employee (criando o CRUD completo).
- [x] Implementação do pacote service do user (criando o CRUD completo).

---
### **Dia 5: criação do Controller**
- [x] Implementação do pacote controller do book (criando o CRUD completo).
- [x] Implementação do pacote controller do employee (criando o CRUD completo).
- [x] Implementação do pacote controller do user (criando o CRUD completo).
- [x] Criação do Enum para status do empréstimo (EMPRESTADO, DEVOLVIDO, ATRASADO).
- [x] Criação da Exception para lista negra e para limite de empréstimos.
- [x] Criação do BorrowingService para gerenciar empréstimos.

---
### **Dia 6: Implementação do Empréstimo**
- [x] Implementação do método para realizar empréstimos no BorrowingService.
- [ ] Implementação do método para renovar empréstimos no BorrowingService.
- [ ] Implementação do método para devolver livros no BorrowingService.
- [ ] Implementação do cálculo de multas por atraso no BorrowingService.
- [ ] Implementação da punição para usuários com atraso no BorrowingService.