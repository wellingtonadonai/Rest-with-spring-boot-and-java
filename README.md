📘 Rest API com Spring Boot e Java
API RESTful construída com Spring Boot e Java, utilizando boas práticas de arquitetura, versionamento de endpoints e organização em camadas. Este projeto serve como base para o desenvolvimento de microsserviços ou sistemas REST escaláveis.

🚀 Tecnologias e Bibliotecas
Java 21 – Linguagem principal

Spring Boot 3 – Framework para desenvolvimento rápido de aplicações Java

Spring Web – Criação de controladores REST

Spring Data JPA – Persistência com banco de dados relacional

Hibernate – ORM utilizado pelo JPA

H2 Database – Banco de dados em memória (ideal para testes)

Maven – Gerenciador de dependências

JUnit – Testes unitários

ModelMapper – Conversão entre DTOs e Entidades

Swagger/OpenAPI (opcional) – Documentação interativa dos endpoints

Lombok – Redução de boilerplate com anotações para Getters, Setters etc.

🧱 Estrutura de Pacotes
rust
Copiar
Editar
com.seuprojeto
├── controllers    -> Camada responsável pelos endpoints da API
├── services       -> Regras de negócio
├── repositories   -> Interface com o banco de dados (JPA)
├── models         -> Entidades JPA
├── dto            -> Objetos de transferência de dados
└── config         -> Configurações gerais (ex: CORS, Swagger, segurança)
📦 Como executar
Clone o projeto:

bash
Copiar
Editar
git clone https://github.com/wellingtonadonai/Rest-with-spring-boot-and-java.git
cd Rest-with-spring-boot-and-java
Compile e execute com Maven:

bash
Copiar
Editar
mvn spring-boot:run
Acesse a aplicação:

arduino
Copiar
Editar
http://localhost:8080
🧪 Testes
Execute os testes com:

bash
Copiar
Editar
mvn test
🧾 Documentação (Swagger)
Se estiver habilitado, acesse a documentação interativa da API:

bash
Copiar
Editar
http://localhost:8080/swagger-ui.html
📚 Exemplos de Endpoints
Método	Endpoint	Descrição
GET	/api/v1/pessoas	Lista todas as pessoas
GET	/api/v1/pessoas/1	Busca pessoa por ID
POST	/api/v1/pessoas	Cria uma nova pessoa
PUT	/api/v1/pessoas/1	Atualiza uma pessoa
DELETE	/api/v1/pessoas/1	Remove uma pessoa do banco
