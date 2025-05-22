
<div align="center">
<img src="https://ik.imagekit.io/mix7flwol/Classic%20Word%20Cloud.png?updatedAt=1747873908306" title="source: https://ik.imagekit.io/mix7flwol/Classic%20Word%20Cloud.png?updatedAt=1747873908306" /> 
</div>
 
<br />


# 🕹️ Loja Games - Backend com Spring Boot

Este é um projeto de backend desenvolvido com **Spring Boot** como parte da trilha de formação da **Generation Brasil**. A aplicação simula uma **loja de games**, permitindo o cadastro e a manipulação de produtos e categorias.


## 🚀Tecnologias utilizadas 

 - [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
 - [Spring boot](https://spring.io/projects/spring-boot)
 - [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
 - [MySQL](https://www.mysql.com/)
 - [Hibernate](https://www.devmedia.com.br/guia/hibernate/38312)
 - [Maven](https://maven.apache.org/)
 - [Insomnia (para testes)](https://insomnia.rest/)
 - [Git & GitHub](https://docs.github.com/en/get-started/using-git/about-git)



## 📌 Funcionalidades

### 📦 Produtos
- [x] Cadastro de produtos
- [x] Atualização de produtos
- [x] Listagem de todos os produtos
- [x] Consulta por ID
- [x] Consulta por nome (parcial, ignorando maiúsculas/minúsculas)
- [x] Deleção de produto
- [x] Filtro de produtos por:
  - Preço **maior** que um valor informado
  - Preço **menor** que um valor informado

### 🧩 Categorias
- [x] Cadastro de categorias
- [x] Atualização de categorias
- [x] Listagem de todas as categorias
- [x] Consulta por ID
- [x] Consulta por nome
- [x] Deleção de categoria

## 🔗 Relacionamentos
- Um produto pertence a **uma categoria**
- Uma categoria pode ter **múltiplos produtos** (relação OneToMany)
## ⚙️ Configuração

### Banco de dados
Configure o banco de dados no arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/db_loja_games
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```


## 🧪 Testes com Insomnia
Os endpoints da API foram testadas com o Insomnia. Você pode realizar os seguintes testes:

- GET /produtos

- POST /produtos

- GET /produtos/maior/{preco}

- GET /produtos/menor/{preco}

- GET /categorias

- etc.

## 📁 Organização do Projeto
```
src
└── main
    └── java
        └── com.generation.lojagames
            ├── controller
            ├── model
            ├── repository
            └── LojaGamesApplication.java

```
## 🧑‍💻 Desenvolvido por
**Aylla Scaglia** -
[GitHub](https://github.com/scaglia-aylla1) -
Aluno(a) Generation Brasil - Trilha FullStack

📌 Este projeto é parte de uma atividade prática da Generation Brasil com o objetivo de aplicar os conhecimentos adquiridos sobre Spring Boot, JPA e boas práticas de desenvolvimento backend.
