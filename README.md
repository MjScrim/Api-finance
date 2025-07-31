# Api Spring

Projeto sobre API e organização e arquitetura DDD(Domain-Driven Design), isolando o problema e tecnologia das regras de negócios.

## Como executar

### Subindo a API juntamente com o banco, via Docker-compose.

Abra o terminal na pasta do projeto e rode:

```bash
docker-copmpose up --build
```

## Rotas funcionais até o então

```bash
/api/v1/clients/{clientId} -> GET, DELETE
/api/v1/clients/create -> POST
```

Refatorando a estrutura, e buscando aplicar arquitetura hexagonal. Mantendo a prioridade e acessibilidade da aplicação via o AggregatoRoot Client.