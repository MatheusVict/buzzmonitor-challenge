# E commerce de livros

Um e-commerce para apaixonados por livros e que desejam comprar seus livros por categoria

### Tecnologias Utilizadas

Liste as tecnologias (linguagens, ferramentas, bibliotecas) que você utilizou para elaborar o projeto. Essa parte é
importante para quando um recrutador (que não tem conhecimento de programação) acessar o seu projeto, ele vai saber só
olhando a documentação quais tecnologias você já conhece!

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

ou rodando diretamente a aplicação (**OBS:** tenha certeza de está com o postgres rodando e de passar as variaveis de
ambiente para execução e mudar suas configurações de conexão no ```application-prod.yml```)
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

**OBS:** todas as rotas possuem o prefixo ```/api/v1```

### Categories:

```POST``` /category
Retorno ```Status 201 Created```
Body:

```json
{
  "name": "Samurai jack",
  "description": "this is the way"
}
```

```GET``` /category
Retorno ```Status 200 OK```

```GET``` /category/{uuid}
```Status 200 OK```

```PUT``` /category/{uuid}
```Status 204 No Content```
Body:

```json
{
  "name": "harley",
  "description": "aaaaaaaaaaaaa"
}
```

```DELETE``` /category/{uuid}
Retorno ```Status 204 No Content```

### Books:

```POST``` /books
Retorno ```Status 201 Created```

Body:

```json
{
  "title": "programatic programmer24",
  "description": "this is the way",
  "author": "José",
  "publicationDate": "01/01/2004",
  "stock": 5,
  "category_uuid": "9a2fdac4-072b-48f2-aa5e-6835ab2bffce"
}
```

```GET``` /books
```Status 200 OK```

```GET``` /books/{uuid}
```Status 200 OK```

```json
    {
  "id": 3,
  "uuid": "ba0808fd-7c91-4625-bdfd-c97d5a5355f7",
  "title": "programatic programmer2",
  "description": "this is the way",
  "author": "José",
  "publicationDate": "2024-06-24T12:34:56.789+00:00",
  "stock": 5,
  "createdAt": "2024-06-24T16:16:09.736966",
  "updatedAt": "2024-06-24T16:16:09.73701"
}
```

```GET``` /books/category?name=nome_da_categoria
```Status 200 OK```

```json
[
  {
    "id": 3,
    "uuid": "ba0808fd-7c91-4625-bdfd-c97d5a5355f7",
    "title": "programatic programmer2",
    "description": "this is the way",
    "author": "José",
    "publicationDate": "2024-06-24T12:34:56.789+00:00",
    "stock": 5,
    "createdAt": "2024-06-24T16:16:09.736966",
    "updatedAt": "2024-06-24T16:16:09.73701"
  }
]
```

```GET``` /books/categorytitle?title=titulo_do_livro
```Status 200 OK```

```json
[
  {
    "id": 3,
    "uuid": "ba0808fd-7c91-4625-bdfd-c97d5a5355f7",
    "title": "programatic programmer2",
    "description": "this is the way",
    "author": "José",
    "publicationDate": "2024-06-24T12:34:56.789+00:00",
    "stock": 5,
    "createdAt": "2024-06-24T16:16:09.736966",
    "updatedAt": "2024-06-24T16:16:09.73701"
  }
]
```

```PUT``` /books/{uuid}
```Status 204 No Content```
Body:

```json
{
  "title": "clean code",
  "description": "this is the way2",
  "author": "Bob",
  "publicationDate": "2024-06-24T12:34:56.789Z",
  "stock": 5,
  "category_uuid": "3bfb0127-e85f-4796-8ba9-8f443fb91fac"
}
```

```DELETE``` /books/{uuid}
```Status 204 No Content```

### Order

```POST``` /order
```Status 201 Created```
Body:

```json
{
  "bookId": "1c46b418-b20a-4b1d-82a8-d17233946aac",
  "amount": 2
}
```

```GET``` /order
```Status 200 OK```

```GET``` /order/{uuid}
```Status 200 OK```
```json
{
  "id": 4,
  "orderId": "33b34089-2aed-4e46-860e-c223ec09a65a",
  "book": {
    "id": 1,
    "uuid": "1c46b418-b20a-4b1d-82a8-d17233946aac",
    "title": "programatic programmer24",
    "description": "this is the way",
    "author": "José",
    "publicationDate": "2004-01-01T00:00:00.000+00:00",
    "stock": 0,
    "createdAt": "2024-06-24T17:32:04.369577",
    "updatedAt": "2024-06-24T17:32:04.369621"
  },
  "amount": 1,
  "status": "APPROVED",
  "createdAt": "2024-06-24T17:38:05.689603",
  "updatedAt": "2024-06-24T17:40:16.367602"
}
```

```PUT``` /order/{uuid}
```Status 204 No Content```
Body:
```json
{
  "bookId": "1c46b418-b20a-4b1d-82a8-d17233946aac",
  "amount": 2
}
```

```DELETE``` /order/{uuid}
```Status 204 No Content```

## ⚠️ Problemas enfrentados

### Erro ao subir o projeto sem o docker:

O projeto não sobe quando tenta rodar sem o docker, pois o banco de dados não está configurado corretamente.

* Verifique se suas configurações do banco estão corretas no ```application-dev.yml```.

### Erro no flyway:

Flyway da erro ao executar

* verifique se dentro do banco que você configurou já não existe as tableas: "category", "book" e "order".

## ⏭️ Próximos passos

Adicionar tratamento de excessões
Swagger


