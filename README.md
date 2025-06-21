# LiterAlura 📚

Projeto desenvolvido como parte do **Challenge LiterAlura** do programa **ONE | Geração 8 - Oracle + Alura**.

## Descrição

Este projeto é uma aplicação Java com Spring Boot que interage com a API do [Gutendex](https://gutendex.com/) para realizar buscas por livros, autores e filtrar dados por idioma ou ano de vida dos autores.

Os dados obtidos da API são armazenados localmente em um banco de dados relacional, utilizando JPA e Hibernate. A aplicação permite consultar e exibir essas informações via terminal.

## Funcionalidades

- 🔍 Buscar livro pelo título e armazenar no banco de dados
- 📚 Listar livros registrados
- ✍️ Listar autores registrados
- ⏳ Filtrar autores vivos em determinado ano
- 🌐 Filtrar livros por idioma

## Tecnologias utilizadas

- Java 17+
- Spring Boot
- JPA / Hibernate
- PostgreSQL (ou outro banco relacional)
- Gson (para parsear JSON da API)
- Gutendex API (https://gutendex.com/)

## Como executar

1. Clone este repositório
2. Configure o banco de dados no `application.properties`
3. Execute a aplicação com `mvn spring-boot:run` ou pela sua IDE
4. Utilize o terminal para interagir com o menu da aplicação
