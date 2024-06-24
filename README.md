# E commerce de livros

Um e-commerce para apaixonados por livros e que desejam comprar seus livros por categoria

### Tecnologias Utilizadas

Liste as tecnologias (linguagens, ferramentas, bibliotecas) que você utilizou para elaborar o projeto. Essa parte é importante para quando um recrutador (que não tem conhecimento de programação) acessar o seu projeto, ele vai saber só olhando a documentação quais tecnologias você já conhece!

Exemplo:
* java
* Spring
* Postgres

## Dependências e Versões Necessárias

* Docker - Versão: 26.1.4 ou superior

## ✅ Como rodar o projeto

Pode rodar por docker usando:

```
docker compose up -d
```

ou rodando diretamente a aplicação (**OBS:** tenha certeza de está com o postgres rodando e de passar as variaveis de ambiente para execução e mudar suas configurações de conexão no ```application-prod.properties```)
Mude para
```
spring:
  application:
    name: book-e-commerce

  datasource:
    url: jdbc:postgresql://localhost:5432/nome_do_banco
    username: seu_usuario
    password: sua_senha
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

```
./mvnw spring-boot:run -Dspring.profiles.active=prod
```

Você será capaz de ver o projeto rodando em [http://localhost:8080](http://localhost:8080)

## Como rodar os testes

```
./mvnw tests
```

## Rotas da aplicação


## ⚠️ Problemas enfrentados


Exemplo:

### Problema 1:
Descrição do problema
* Como solucionar: explicar a solução.

### Problema 2:
Descrição do problema
* Como solucionar: explicar a solução.

## ⏭️ Próximos passos

Descreva se você pretende, pensou ou gostaria de elaborar uma nova feature para o seu projeto definindo os próximos passos.


