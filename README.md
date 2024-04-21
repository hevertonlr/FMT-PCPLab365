# FMT-PCPLab365

Este `README` documenta os endpoints disponíveis na API de Autenticação.

## Endpoints

### POST /cadastro

- **Resumo**: Cadastra um novo usuário.
- **ID da Operação**: register

**Corpo da Requisição**:

```json
{
  "username": "",
  "password": ""
}
```

**Respostas**:

- **200 OK**: Usuário cadastrado com sucesso.

### POST /login

- **Resumo**: Realiza o login de um usuário.
- **ID da Operação**: login

**Corpo da Requisição**:

```json
{
  "username": "",
  "password": ""
}
```

**Respostas**:

- **200 OK**: Login realizado com sucesso.

### GET /docentes

- **Resumo**: Lista todos os docentes.
- **ID da Operação**: list

**Respostas**:

- **200 OK**: Retorna uma lista de docentes.

### POST /docentes

- **Resumo**: Cria um novo docente.
- **ID da Operação**: create

**Corpo da Requisição**:

```json
{
  "name": "",
  "entryDate": "",
  "profile": "",
  "login": "",
  "password": ""
}
```

**Respostas**:

- **200 OK**: Docente criado com sucesso.

### GET /docentes/{id}

- **Resumo**: Obtém um docente pelo ID.
- **ID da Operação**: findById

**Parâmetros**:

- **id**: ID do docente (inteiro, obrigatório)

**Respostas**:

- **200 OK**: Retorna os detalhes do docente.

### PUT /docentes/{id}

- **Resumo**: Atualiza um docente existente.
- **ID da Operação**: update

**Parâmetros**:

- **id**: ID do docente (inteiro, obrigatório)

**Corpo da Requisição**:

```json
{
  "name": "",
  "entryDate": ""
}
```

**Respostas**:

- **200 OK**: Docente atualizado com sucesso.

### DELETE /docentes/{id}

- **Resumo**: Exclui um docente pelo ID.
- **ID da Operação**: delete

**Parâmetros**:

- **id**: ID do docente (inteiro, obrigatório)

**Respostas**:

- **200 OK**: Docente excluído com sucesso.

### POST /cursos

- **Resumo**: Cria um novo curso.
- **ID da Operação**: create

**Corpo da Requisição**:

```json
{
  "name": ""
}
```

**Respostas**:

- **200 OK**: Curso criado com sucesso.

### GET /cursos/{id}

- **Resumo**: Obtém um curso pelo ID.
- **ID da Operação**: findById

**Parâmetros**:

- **id**: ID do curso (inteiro, obrigatório)

**Respostas**:

- **200 OK**: Retorna os detalhes do curso.

### PUT /cursos/{id}

- **Resumo**: Atualiza um curso existente.
- **ID da Operação**: update

**Parâmetros**:

- **id**: ID do curso (inteiro, obrigatório)

**Corpo da Requisição**:

```json
{
  "name": ""
}
```

**Respostas**:

- **200 OK**: Curso atualizado com sucesso.

### DELETE /cursos/{id}

- **Resumo**: Exclui um curso pelo ID.
- **ID da Operação**: delete

**Parâmetros**:

- **id**: ID do curso (inteiro, obrigatório)

**Respostas**:

- **200 OK**: Curso excluído com sucesso.

### GET /cursos/{id}/materias

- **Resumo**: Lista as matérias de um curso.
- **ID da Operação**: list

**Parâmetros**:

- **id**: ID do curso (inteiro, obrigatório)

**Respostas**:

- **200 OK**: Retorna uma lista de matérias do curso.

### POST /materias

- **Resumo**: Cria uma nova matéria.
- **ID da Operação**: create

**Corpo da Requisição**:

```json
{
  "name": "",
  "courseid": 0
}
```

**Respostas**:

- **200 OK**: Matéria criada com sucesso.

### GET /materias/{id}

- **Resumo**: Obtém uma matéria pelo ID.
- **ID da Operação**: findById

**Parâmetros**:

- **id**: ID da matéria (inteiro, obrigatório)

**Respostas**:

- **200 OK**: Retorna os detalhes da matéria.

### PUT /materias/{id}

- **Resumo**: Atualiza uma matéria existente.
- **ID da Operação**: update

**Parâmetros**:

- **id**: ID da matéria (inteiro, obrigatório)

**Corpo da Requisição**:

```json
{
  "name": "",
  "courseid": 0
}
```

**Respostas**:

- **200 OK**: Matéria atualizada com sucesso.

### DELETE /materias/{id}

- **Resumo**: Exclui uma matéria pelo ID.
- **ID da Operação**: delete

**Parâmetros**:

- **id**: ID da matéria (inteiro, obrigatório)

**Respostas**:

- **200 OK**: Matéria excluída com sucesso.

### GET /turmas

- **Resumo**: Lista todas as turmas.
- **ID da Operação**: list

**Respostas**:

- **200 OK**: Retorna uma lista de turmas.

### POST /turmas

- **Resumo**: Cria uma nova turma.
- **ID da Operação**: create

**Corpo da Requisição**:

```json
{
  "name": "",
  "teacherid": 0,
  "courseid": 0
}
```

**Respostas**:

- **200 OK**: Turma criada com sucesso.

### GET /turmas/{id}

- **Resumo**: Obtém uma turma pelo ID.
- **ID da Operação**: findById

**Parâmetros**:

- **id**: ID da turma (inteiro, obrigatório)

**Respostas**:

- **200 OK**: Retorna os detalhes da turma.

### PUT /turmas/{id}

- **Resumo**: Atualiza uma turma existente.
- **ID da Operação**: update

**Parâmetros**:

- **id**: ID da turma (inteiro, obrigatório)

**Corpo da Requisição**:

```json
{
  "name": ""
}
```

**Respostas**:

- **200 OK**: Turma atualizada com sucesso.

### DELETE /turmas/{id}

- **Resumo**: Exclui uma turma pelo ID.
- **ID da Operação**: delete

**Parâmetros**:

- **id**: ID da turma (inteiro, obrigatório)

**Respostas**:

- **200 OK**: Turma excluída com sucesso.

### GET /alunos

- **Resumo**: Lista todos os alunos.
- **ID da Operação**: list

**Respostas**:

- **200 OK**: Retorna uma lista de alunos.

### POST /alunos

- **Resumo**: Cria um novo aluno.
- **ID da Operação**: create

**Corpo da Requisição**:

```json
{
  "name": "",
  "birthday": "",
  "classroomid": 0,
  "login": "",
  "password": ""
}
```

**Respostas**:

- **200 OK**: Aluno criado com sucesso.

### GET /alunos/{id}

- **Resumo**: Obtém um aluno pelo ID.
- **ID da Operação**: findById

**Parâmetros**:

- **id**: ID do aluno (inteiro, obrigatório)

**Respostas**:

- **200 OK**: Retorna os detalhes do aluno.

### PUT /alunos/{id}

- **Resumo**: Atualiza um aluno existente.
- **ID da Operação**: update

**Parâmetros**:

- **id**: ID do aluno (inteiro, obrigatório)

**Corpo da Requisição**:

```json
{
  "name": "",
  "birthday": ""
}
```

**Respostas**:

- **200 OK**: Aluno atualizado com sucesso.

### DELETE /alunos/{id}

- **Resumo**: Exclui um aluno pelo ID.
- **ID da Operação**: delete

**Parâmetros**:

- **id**: ID do aluno (inteiro, obrigatório)

**Respostas**:

- **200 OK**: Aluno excluído com sucesso.

### GET /alunos/{id}/notas

### POST /notas

- **Resumo**: Cria uma nova nota.
- **ID da Operação**: create

**Corpo da Requisição**:

```json
{
  "value": 0.00,
  "date": "",
  "studentid": 0,
  "teacherid": 0,
  "subjectid": 0
}
```

**Respostas**:

- **200 OK**: Nota criada com sucesso.

### GET /notas/{id}

- **Resumo**: Obtém uma nota pelo ID.
- **ID da Operação**: findById

**Parâmetros**:

- **id**: ID da nota (inteiro, obrigatório)

**Respostas**:

- **200 OK**: Retorna os detalhes da nota.

### PUT /notas/{id}

- **Resumo**: Atualiza uma nota existente.
- **ID da Operação**: update

**Parâmetros**:

- **id**: ID da nota (inteiro, obrigatório)

**Corpo da Requisição**:

```json
{
  "value": 0.00,
  "date": ""
}
```

**Respostas**:

- **200 OK**: Nota atualizada com sucesso.

### DELETE /notas/{id}

- **Resumo**: Exclui uma nota pelo ID.
- **ID da Operação**: delete

**Parâmetros**:

- **id**: ID da nota (inteiro, obrigatório)

**Respostas**:

- **200 OK**: Nota excluída com sucesso.

```