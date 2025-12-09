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
- [ ] Setup do Docker para PostgreSQL.
- [ ] Configuração do 'application.properties' para conexão com o banco de dados.