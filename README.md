# ${\textsf{\color{#112bda}F\color{#e1278a}u\color{#ff8818}ll\color{#808080}Stack [Education]}}$<br/><sub>Módulo 1 - Projeto Avaliativo</sub>

## Contexto

O LAB365 Developer, empresa líder no segmento tecnológico de gestão estudantil, está com um novo projeto intitulado
labPCP, uma API Rest completa para gestão de cursos, turmas, conteúdos e docentes, que será futuramente integrada a
soluções web de gestão em escolas e creches da rede pública.

Esta é a documentação deste Projeto Avaliativo. Aqui você encontrará informações sobre a sua
funcionalidade, como os endpoints disponíveis, seus métodos, parâmetros necessários, e exemplos de resposta.

___

# Executando

Antes de começar, certifique-se de atualizar os dados de conexão de banco de dados.

```
spring.datasource.url=jdbc:postgresql://SEU_HOST_DE_BD/SUA_DATABASE_DE_BD
spring.datasource.username=SEU_USUARIO_DE_BD
spring.datasource.password=SUA_SENHA_DE_BD
```

# Autenticação

Para garantir a segurança das suas requisições, nossa API utiliza tokens JWT (JSON Web Tokens) para autenticação. Isso
significa que você deve incluir um token de autenticação válido em cada requisição ao servidor.

## Obtendo seu Token JWT

Para obter seu token JWT, você precisará fazer uma requisição `POST` para o endpoint `/login` com suas credenciais de
usuário. Você receberá uma resposta contendo o token JWT que será utilizado para as próximas requisições.

## Utilizando o Token JWT

Com o token JWT em mãos, você deve incluí-lo no cabeçalho das suas requisições utilizando o esquema `Bearer`.

```json
{
  "Authorization": "Bearer seu_token_jwt_aqui"
}
```

Certifique-se de substituir `seu_token_jwt_aqui` pelo token que você obteve anteriormente.

> [!NOTE]
> O token JWT é sensível e deve ser mantido em segurança. Não compartilhe seu token publicamente ou com terceiros.

## Base URL

A URL base para todas as solicitações é `http://localhost:8080`.

## Endpoints

#### `POST /login`

Realiza o login de um usuário.

> [!IMPORTANT]
> Para primeiro acesso, utilizar como usuário e senha a palavra "admin"
>
> ```json
> {
> "username": "admin",
> "password": "admin"
> }
> ```

##### Corpo da Requisição

O corpo da requisição deve conter um objeto JSON com as informações a serem atualizadas, seguindo o seguinte formato:

```json
{
  "username": "string",
  "password": "string"
}
```

##### Respostas

- `200 OK`: Login realizado com sucesso.
- `401 Unauthorized`: Credenciais inválidas.
- `400 Bad Request`: Requisição inválida.

___

### Cadastro

#### `POST /cadastro`

Cadastra um novo usuário.\
> **Observação**: Este usuário terá perfil ADM

##### Corpo da Requisição

```json
{
  "username": "string",
  "password": "string"
}
```

> [!IMPORTANT]
> A Senha deve respeitar as seguintes regras:
> - **Tamanho**: Entre 8 e 30 caracteres.
> - **Espaços em Branco**: Sem espaços em branco.
> - **Letras Maiúsculas**: Pelo menos 1 letra maiúscula.
> - **Dígitos Numéricos**: Pelo menos 1 dígito numérico.
> - **Caracteres Especiais**: Pelo menos 1 caractere especial (ex: !, @, #).
> - **Sequências Numéricas Ilegais**: Sem sequências de mais de 2 dígitos consecutivos. (ex: 123, 456).
> - **Sequências Alfabéticas Ilegais**: Sem sequências de mais de 2 letras consecutivas. (ex: abc, def).
> - **Sequências de Teclado Ilegais**: Sem sequências de mais de 2 teclas consecutivas no teclado QWERTY dos EUA. (ex:
    qwe, asd).

##### Respostas

- `201 Created`: Usuário cadastrado com sucesso.
- `400 Bad Request`: Requisição inválida.

___

### Docentes

#### `GET /docentes`

Lista todos os docentes.

##### Respostas

- `200 OK`: Retorna uma lista de docentes.
- `404 Not Found`: Não há Docentes Cadastrados.

#### `POST /docentes`

Cria um novo docente.

##### Corpo da Requisição

```json
{
  "name": "string",
  "entryDate": "dd/mm/yyyy",
  "profile": "PEDAGOGICO|RECRUITER|PROFESSOR",
  "login": "string",
  "password": "string"
}
```

> [!IMPORTANT]
> O campo *password* deve respeitar as mesmas regras do cadastro de usuário

##### Respostas

- `200 OK`: Docente criado com sucesso.
- `400 Bad Request`: Requisição inválida.

#### `GET /docentes/{id}`

Obtém um docente pelo ID.

##### Parâmetros

- `id`: ID do docente (inteiro, obrigatório)

##### Respostas

- `200 OK`: Retorna os detalhes do docente.
- `404 Not Found`: Docente não encontrado.

#### `PUT /docentes/{id}`

Atualiza um docente existente.

##### Parâmetros

- `id`: ID do docente (inteiro, obrigatório)

##### Corpo da Requisição

```json
{
  "name": "string",
  "entryDate": "dd/mm/yyyy"
}
```

##### Respostas

- `200 OK`: Docente atualizado com sucesso.
- `400 Bad Request`: Requisição inválida.
- `404 Not Found`: Docente não encontrado.

#### `DELETE /docentes/{id}`

Exclui um docente pelo ID.

##### Parâmetros

- `id`: ID do docente (inteiro, obrigatório)

##### Respostas

- `204 No Content`: Docente excluído com sucesso.
- `404 Not Found`: Docente não encontrado.

___

### Cursos

#### `POST /cursos`

Cria um novo curso.

##### Corpo da Requisição

```json
{
  "name": "string"
}
```

##### Respostas

- `200 OK`: Curso criado com sucesso.
- `400 Bad Request`: Requisição inválida.

#### `GET /cursos/{id}`

Obtém um curso pelo ID.

##### Parâmetros

- `id`: ID do curso (inteiro, obrigatório)

##### Respostas

- `200 OK`: Retorna os detalhes do curso.
- `404 Not Found`: Curso não encontrado.

#### `PUT /cursos/{id}`

Atualiza um curso existente.

##### Parâmetros

- `id`: ID do curso (inteiro, obrigatório)

##### Corpo da Requisição

```json
{
  "name": "string"
}
```

##### Respostas

- `200 OK`: Curso atualizado com sucesso.
- `400 Bad Request`: Requisição inválida.
- `404 Not Found`: Curso não encontrado.

#### `DELETE /cursos/{id}`

Exclui um curso pelo ID.

##### Parâmetros

- `id`: ID do curso (inteiro, obrigatório)

##### Respostas

- `204 No Content`: Curso excluído com sucesso.
- `404 Not Found`: Curso não encontrado.

#### `GET /cursos/{id}/materias`

Lista as matérias de um curso.

##### Parâmetros

- `id`: ID do curso (inteiro, obrigatório)

##### Respostas

- `200 OK`: Retorna uma lista de matérias do curso.
- `404 Not Found`: Não há Matérias Cadastradas.

___

### Matérias

#### `POST /materias`

Cria uma nova matéria.

##### Corpo da Requisição

```json
{
  "name": "string",
  "courseid": 0
}
```

##### Respostas

- `200 OK`: Matéria criada com sucesso.
- `400 Bad Request`: Requisição inválida.

#### `GET /materias/{id}`

Obtém uma matéria pelo ID.

##### Parâmetros

- `id`: ID da matéria (inteiro, obrigatório)

##### Respostas

- `200 OK`: Retorna os detalhes da matéria.
- `404 Not Found`: Matéria não encontrada.

#### `PUT /materias/{id}`

Atualiza uma matéria existente.

##### Parâmetros

- `id`: ID da matéria (inteiro, obrigatório)

##### Corpo da Requisição

```json
{
  "name": "string",
  "courseid": 0
}
```

##### Respostas

- `200 OK`: Matéria atualizada com sucesso.
- `400 Bad Request`: Requisição inválida.
- `404 Not Found`: Matéria não encontrada.

#### `DELETE /materias/{id}`

Exclui uma matéria pelo ID.

##### Parâmetros

- `id`: ID da matéria (inteiro, obrigatório)

##### Respostas

- `204 No Content`: Matéria excluída com sucesso.
- `404 Not Found`: Matéria não encontrada.

___

### Turmas

#### `GET /turmas`

Lista todas as turmas.

- **ID da Operação**: list

##### Respostas

- `200 OK`: Retorna uma lista de turmas.
- `404 Not Found`: Não há Turmas Cadastradas.

#### `POST /turmas`

Cria uma nova turma.

##### Corpo da Requisição

```json
{
  "name": "string",
  "teacherid": 0,
  "courseid": 0
}
```

##### Respostas

- `200 OK`: Turma criada com sucesso.
- `400 Bad Request`: Requisição inválida.

#### `GET /turmas/{id}`

Obtém uma turma pelo ID.

##### Parâmetros

- `id`: ID da turma (inteiro, obrigatório)

##### Respostas

- `200 OK`: Retorna os detalhes da turma.
- `404 Not Found`: Turma não encontrada.

#### `PUT /turmas/{id}`

Atualiza uma turma existente.

##### Parâmetros

- `id`: ID da turma (inteiro, obrigatório)

##### Corpo da Requisição

```json
{
  "name": "string"
}
```

##### Respostas

- `200 OK`: Turma atualizada com sucesso.
- `400 Bad Request`: Requisição inválida.
- `404 Not Found`: Turma não encontrada.

#### `DELETE /turmas/{id}`

Exclui uma turma pelo ID.

##### Parâmetros

- `id`: ID da turma (inteiro, obrigatório)

##### Respostas

- `204 No Content`: Turma excluída com sucesso.
- `404 Not Found`: Turma não encontrada.

___

### Alunos

#### `GET /alunos`

Lista todos os alunos.

- **ID da Operação**: list

##### Respostas

- `200 OK`: Retorna uma lista de alunos.
- `404 Not Found`: Não há Alunos Cadastrados.

#### `POST /alunos`

Cria um novo aluno.

##### Corpo da Requisição

```json
{
  "name": "string",
  "birthday": "dd/mm/yyyy",
  "classroomid": 0,
  "login": "string",
  "password": "string"
}
```

> [!IMPORTANT]
> O campo *password* deve respeitar as mesmas regras do cadastro de usuário

##### Respostas

- `200 OK`: Aluno criado com sucesso.
- `400 Bad Request`: Requisição inválida.

#### `GET /alunos/{id}`

Obtém um aluno pelo ID.

##### Parâmetros

- `id`: ID do aluno (inteiro, obrigatório)

##### Respostas

- `200 OK`: Retorna os detalhes do aluno.
- `404 Not Found`: Aluno não encontrado.

#### `PUT /alunos/{id}`

Atualiza um aluno existente.

##### Parâmetros

- `id`: ID do aluno (inteiro, obrigatório)

##### Corpo da Requisição

```json
{
  "name": "string",
  "birthday": "dd/mm/yyyy"
}
```

##### Respostas

- `200 OK`: Aluno atualizado com sucesso.
- `400 Bad Request`: Requisição inválida.
- `404 Not Found`: Aluno não encontrado.

#### `DELETE /alunos/{id}`

Exclui um aluno pelo ID.

##### Parâmetros

- `id`: ID do aluno (inteiro, obrigatório)

##### Respostas

- `204 No Content`: Aluno excluído com sucesso.
- `404 Not Found`: Aluno não encontrado.

#### `GET /alunos/{id}/notas`

Retorna as notas do aluno especificado pelo ID.

##### Parâmetros

- `id`: ID do aluno (obrigatório, inteiro)

##### Respostas

- `200 OK`: Retorna uma lista de todas as Notas do Aluno.
- `404 Not Found`: Não há Notas Cadastradas para o Aluno.

### `GET /alunos/{id}/pontuacao`

Retorna a pontuação do aluno especificado pelo ID.

##### Parâmetros

- `id`: ID do aluno (obrigatório, inteiro)

##### Respostas

- `200 OK`: Pontuação calculada com Sucesso.
- `404 Not Found`: Aluno não encontrado.

___

### Notas

#### `POST /notas`

Cria uma nova nota.

##### Corpo da Requisição

```json
{
  "value": 0.00,
  "date": "string",
  "studentid": 0,
  "teacherid": 0,
  "subjectid": 0
}
```

##### Respostas

- `200 OK`: Nota criada com sucesso.
- `400 Bad Request`: Requisição inválida.

#### `GET /notas/{id}`

Obtém uma nota pelo ID.

##### Parâmetros

- `id`: ID da nota (inteiro, obrigatório)

##### Respostas

- `200 OK`: Retorna os detalhes da nota.
- `404 Not Found`: Nota não encontrada.

#### `PUT /notas/{id}`

Atualiza uma nota existente.

##### Parâmetros

- `id`: ID da nota (inteiro, obrigatório)

##### Corpo da Requisição

```json
{
  "value": 0.00,
  "date": "dd/mm/yyyy"
}
```

##### Respostas

- `200 OK`: Nota atualizada com sucesso.
- `400 Bad Request`: Requisição inválida.
- `404 Not Found`: Nota não encontrada.

#### `DELETE /notas/{id}`

Exclui uma nota pelo ID.

##### Parâmetros

- `id`: ID da nota (inteiro, obrigatório)

##### Respostas

- `204 No Content`: Nota excluída com sucesso.
- `404 Not Found`: Nota não encontrada.

## Tecnologias utilizadas:

- JAVA
- Spring Boot

## Trello

O Acompanhamento de cards do projeto pode ser realizado pelo link do
Trello: [Quadro Kanban](https://trello.com/b/IpfttuB1/projeto-avaliativo)
