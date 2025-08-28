# Microsserviço de Gestão de Apólices

[![Java](https://img.shields.io/badge/Java-17+-blue?style=for-the-badge&logo=openjdk)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.5-green?style=for-the-badge&logo=spring)](https://spring.io/projects/spring-boot)
[![Kafka](https://img.shields.io/badge/Apache%20Kafka-2.8.0-black?style=for-the-badge&logo=apachekafka)](https://kafka.apache.org/)
[![Docker](https://img.shields.io/badge/Docker-20.10-blue?style=for-the-badge&logo=docker)](https://www.docker.com/)

---

## 📌 Visão Geral do Projeto

Este projeto é um microsserviço **assíncrono** para gerenciar o ciclo de vida de apólices de seguro.  
A aplicação foi construída seguindo os princípios da **Arquitetura Limpa** e é **orientada a eventos**, garantindo **desacoplamento, escalabilidade e resiliência**.

O fluxo de processamento utiliza **Apache Kafka** para orquestrar a comunicação entre os componentes, bem como uma **API externa de análise de fraudes**.

---

## ✅ Análise de Conformidade com os Requisitos do Desafio

A solução proposta neste projeto foi desenvolvida para atender integralmente aos requisitos especificados no desafio do Itaú. A seguir, detalhamos como cada uma das solicitações foi atendida:

1. **Receber e Persistir Solicitações** → O sistema recebe solicitações via uma API REST, persiste as informações em uma base de dados e retorna o ID da solicitação e a data e hora.  
2. **Processar e Validar Solicitações** → O serviço consulta uma **API de Fraudes** e aplica regras de validação conforme a classificação de risco do cliente, alterando o status da apólice.  
3. **Permitir Consultas por API REST** → O projeto permite a consulta de solicitações por ID da apólice ou ID do cliente através de endpoints REST.  
4. **Receber e Processar Eventos Externos** → A arquitetura orientada a eventos permite que o microsserviço receba e processe eventos relacionados a pagamento e subscrição, cruciais para a emissão da apólice.  
5. **Cancelamento de Solicitação** → A solução prevê o cancelamento de uma solicitação a qualquer momento, exceto quando a apólice já foi emitida. O ciclo de vida da solicitação é encerrado após o cancelamento.  
6. **Alterar o Estado da Solicitação** → O serviço gerencia o ciclo de vida da apólice, alterando seu estado conforme o avanço do processo (e.g., de `RECEIVED` para `VALIDATED` ou `PENDING`).  
7. **Publicar Eventos de Estado** → Após cada alteração de estado, o serviço publica eventos para que outros serviços possam reagir (exemplo: serviço de Notificação).  

---

## 🏛️ Análise Arquitetural

### 🔹 Arquitetura Limpa (Clean Architecture) & Hexagonal

- **Sim, os conceitos da Arquitetura Limpa foram plenamente respeitados.**  
- O projeto adota a Arquitetura Limpa e Hexagonal (Ports and Adapters) para isolar a lógica de negócio de frameworks, bancos de dados e APIs externas.  
- Isso garante **manutenibilidade** e **robustez**.  

### 🔹 Arquitetura Orientada a Eventos (EDA)

- **Sim, a Arquitetura Orientada a Eventos foi adotada.**  
- O endpoint REST recebe a requisição → publica no Kafka → um listener processa em segundo plano.  
- Isso evita **timeouts** e melhora a experiência do usuário.  

### 🔹 Domain-Driven Design (DDD)

- O projeto segue princípios de DDD, aplicados de forma pragmática:  
  - **Linguagem Ubíqua**: Termos do domínio (Apolice, Cliente, status).  
  - **Camada de Domínio**: Entidades e regras de negócio em `core/domain`.  
  - **Bounded Context**: Gestão de Apólices.  

### 🔹 Estratégia API-First

- As APIs REST foram projetadas com abordagem **API-First** usando **OpenAPI/Swagger**.  
- Isso garante clareza e facilita a integração com outros times.  

---

## ⚙️ Desafios e Soluções

- **Múltiplos Serviços** → Resolvido com **Docker Compose**.  
- **Processamento Assíncrono** → Cliente pode consultar `GET /v1/apolice/{id}/status`.  
- **Observabilidade** → Uso de **Kafdrop** e **PgAdmin** no `docker-compose.yml`.  

---

## 🧹 Princípios de Código (Clean Code)

- **Nomenclatura clara** → Ex.: `ProcessaApoliceUseCase`.  
- **SRP (Responsabilidade Única)** → Cada classe tem papel definido.  
- **Injeção de Dependência** → Interfaces facilitam substituição de implementações.  
- **Testabilidade** → Core testado isoladamente sem infraestrutura externa.  

---

## 🚀 Tecnologias Utilizadas

- **Aplicação**: Java 17+, Spring Boot, Spring Data JPA, Lombok.  
- **Build/Testes**: Apache Maven, Jacoco.  
- **Infraestrutura (Docker Compose)**: PostgreSQL, Kafka, Zookeeper, Kafdrop, PgAdmin.  

---

## ▶️ Como Executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/MiguelProgrammer/apolice.git
   cd apolice
   ```
2. Suba os serviços:
   ```bash
   docker-compose up --build
   ```

### 🔗 Serviços Disponíveis

- **Microsserviço de Apólices (Swagger)** → [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)  
- **Microsserviço de Fraudes (Swagger)** → [http://localhost:9090/swagger-ui.html](http://localhost:9090/swagger-ui.html)  
- **PgAdmin** → [http://localhost:8081](http://localhost:8081)  
- **Kafdrop** → [http://localhost:9000](http://localhost:9000)  

---

## 📂 Estrutura do Projeto

```
com.acme.apolice
├── core
│   ├── domain      # Entidades e interfaces (Ports)
│   └── usecase     # Casos de uso (lógica central)
│
└── adapter
    ├── inbound     # APIs REST, listeners Kafka
    └── outbound    # Repositórios, clientes HTTP
```

---

## 📡 Endpoints da API

### Microsserviço de Apólices (`:8080`)

- **POST** `/v1/apolice` → Cria nova apólice.  
- **GET** `/v1/apolice/{id}` → Detalhes da apólice.  
- **GET** `/v1/apolice/{id}/status` → Status da apólice.  

### Microsserviço de Fraudes (`:9090`)

- **POST** `/v1/analise` → Retorna classificação de risco simulada.  

---

## 👤 Autor

- **Miguel Programmer** → [github.com/MiguelProgrammer](https://github.com/MiguelProgrammer)

---

> **Obs.:** Estrutura e análise validadas com apoio de inteligência artificial.
