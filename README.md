# Api Spring

Projeto sobre API e organização de negócios e relacionamento entre entindidades, buscando organização e estabilidade do sistema.

## Como executar

### Subindo a API juntamente com o banco, via Docker-compose.

Abra o terminal na pasta do projeto e rode:

```bash
docker-copmpose up --build
```

## Rotas funcionais até o então

```bash
/clients -> GET
/clients/{clientId} -> GET, DELETE
/clients/create -> POST
```

Ainda falta a lógica mais estruturada que liga um Client já existente como proprietário de um Card.
Até o então as rotas de Card funcionam, mas ainda são mais burocráticas.