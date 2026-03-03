# Ecommerce API

API REST de e-commerce com autenticação JWT, controle de perfis (```ADMIN & CUSTOMER```), gerenciamento de produtos, carrinho e pedidos.

## Visão Geral
- **Arquitetura:** Monólito modular inspirado em Clean Architecture. Cada módulo segue a estrutura:
 ```
    /modules/{domínio}/{camada}
    domínios: user, product, cart, order
    camadas: domain | application | infrastructure | presentation
 ```
- **Objetivo:** Demonstrar boas práticas de backend, modularidade e design orientado a dominio (DDD).

## Funcionalidades
- **Autenticação:** cadastro, login e uso de tokens JWT (Access e Refresh)
- **Produtos:** CRUD e listagem com filtros (ADMIN)
- **Carrinho:** adicionar, remover, alterar itens e checkout
- **Pedidos:** criar, consultar e cancelar
- **Admin:** gerenciar usuários e roles (perfis de acesso).

## Tecnologias utilizadas

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/SpringBoot-4-green)
[![Postgres](https://img.shields.io/badge/Postgres-%23316192.svg?logo=postgresql&logoColor=white)](#)
[![Docker](https://img.shields.io/badge/Docker-2496ED?logo=docker&logoColor=fff)](#)
[![JUnit5](https://img.shields.io/badge/JUnit5-C21325?logo=junit5&logoColor=fff)](#)
[![GitHub Actions](https://img.shields.io/badge/GitHub_Actions-2088FF?logo=github-actions&logoColor=white)](#)
[![OpenAPI](https://img.shields.io/badge/OpenAPI-6BA539?logo=openapiinitiative&logoColor=white)](#)
[![Swagger](https://img.shields.io/badge/Swagger-85EA2D?logo=swagger&logoColor=173647)](#)
[![Insomnia](https://img.shields.io/badge/Insomnia-4000BF?logo=insomnia&logoColor=white)](#)

## Como rodar
1. Copie variáveis de ambiente:
```bash
   cp .env.example .env 
```
2. Opções de execução:

**Docker Compose (API + DB)**
```bash
   docker compose up --build 
```
**Banco no Docker + API local**
```bash
   docker compose up -d db
   ./mvnw spring-boot:run 
```

## Endpoints principais

### Auth
- ```POST /api/v1/auth/register```
- ```POST /api/v1/auth/login```
- ```POST /api/v1/auth/refresh```

### Produtos
- ```GET /api/v1/products``` — listar produtos
- ```GET /api/v1/products/{id}``` — detalhes de produto específico
- ```POST/PUT/DELETE /api/v1/products/...``` — admin

### Carrinho (CUSTOMER)
- ```GET /api/v1/cart``` — visualizar carrinho
- ```POST/PATCH/DELETE /api/v1/cart/items``` — adicionar/alterar/remover
- ```POST /api/v1/cart/checkout``` — finalizar

### Pedidos (CUSTOMER)
- ```GET /api/v1/orders``` — listar pedidos
- ```GET /api/v1/orders/{id}``` — detalhes de pedido específico
- ```POST /api/v1/orders/{id}/cancel``` — cancelar pedido

### Usuários (ADMIN)
- ```GET /api/v1/admin/users``` — listar todos usuários
- ```GET /api/v1/admin/users/{id}```  — detalhes de usuário específico
- ```PATCH /api/v1/admin/users/{id}/roles``` — alterar roles de um usuário

## Status de produto e pedido
- **Produtos:** ```[ACTIVE, DEACTIVATED]```
- **Pedidos:** ```[CREATED, CONFIRMED, PAID (ainda não utilizado), CANCELED, SHIPPED (ainda não utilizado), DELIVERED (ainda não utilizado)]```

## Páginação
Listagens retornam:
```
   [page, size, totalElements, totalPages, first, last]
```

## Documentação da API
- OpenAPI JSON: ```GET /v3/api-docs```
- UI (Swagger/Scalar): ```GET /scalar```

## Integração Contínua (CI)
Pipeline configurada no **Github Actions**:
- Dispara em push e PRs
- Executa **build, lint e testes automatizados**
- Utiliza **PostgreSQL em container** para testes integrados
- Verifica **warnings de compilação** (falha se houver)
- Variáveis de ambiente e secrets configurados para segurança

## Testes e Build
```bash
./mvnw -DskipTests compile
./mvnw test
```