# 📦 Product Microservice

Este projeto é um **template base** para aplicações Spring Boot configuradas para rodar em containers Docker, orquestrados via **Docker Compose**.

---

## 🚀 Estrutura do Projeto

O projeto segue a Hexagonal Architeture.

```bash
├── comandos-docker.md
├── docker-compose.yml
├── Dockerfile
├── HELP.md
├── infra
│   ├── grafana
│   │   └── provisioning
│   │       └── datasources
│   │           └── datasource.yml
│   ├── nginx
│   │   └── nginx.conf
│   └── prometheus
│       └── prometheus.yml
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
└── src
    ├── main
    │   ├── java
    │   │   ├── br
    │   │   │   └── com
    │   │   │       └── asa
    │   │   │           └── product_microservice
    │   │   │               ├── application
    │   │   │               │   ├── domain
    │   │   │               │   │   └── exception
    │   │   │               │   └── port
    │   │   │               │       ├── in
    │   │   │               │       │   └── service
    │   │   │               │       └── out
    │   │   │               │           └── repository
    │   │   │               └── infrastructure
    │   │   │                   ├── adapter
    │   │   │                   │   ├── in
    │   │   │                   │   │   └── web
    │   │   │                   │   │       └── controller
    │   │   │                   │   └── out
    │   │   │                   │       ├── repository
    │   │   │                   │       │   └── entity
    │   │   │                   │       ├── service
    │   │   │                   │       │   └── impl
    │   │   │                   │       └── web
    │   │   │                   │           └── client
    │   │   │                   ├── config
    │   │   │                   ├── dto
    │   │   │                   ├── mapper
    │   │   │                   └── util
    │   │   └── tech
    │   │       └── ada
    │   └── resources
    │       ├── application-dev.yml
    │       ├── application-local.yml
    │       ├── application.yml
    │       ├── data.sql
    │       ├── static
    │       └── templates
    └── test
        └── java
            └── br
                └── com
                    └── asa
                        └── product_microservice
```

---

## 🧩 Tecnologias Utilizadas

- **Java 21+**
- **Spring Boot** (Web, Data JPA, Actuator, Security, OpenFeign, Cache...)
- **PostgreSQL** (banco de dados)
- **H2** (banco de dados) - rodando local
- **Docker** e **Docker Compose**

---

## ▶️ Como Rodar

### 1. Construir o projeto

Se estiver usando **Maven**:
```bash
mvn clean package
```

### 2. Subir os containers

```bash
docker-compose up --build -d
```

A aplicação estará disponível em: [http://localhost](http://localhost)

---

## 🧰 Comandos Úteis

| Comando | Descrição |
|----------|------------|
| `docker-compose up -d` | Inicia os containers em background |
| `docker-compose down` | Encerra e remove os containers |
| `docker-compose logs -f` | Exibe logs em tempo real |
| `docker ps` | Lista containers ativos |

---

## 🧱 Extensões

- **Prometheus** e **Grafana** para monitoramento.
- **Zipkin** para trace.
- **NGINX** como proxy reverso.
- **Profiles** para diferentes ambientes (dev, prod). Sendo esse ultimo, necessario configuracao.

---

## Monitoramento

- **Zipkin** - Apos subir o docker-compose, acessar [http://localhost:9411](http://localhost:9411).
- **Prometheus** - Apos subir o docker-compose, acessar [http://localhost:9090](http://localhost:9090).
- **Grafana** - Apos subir o docker-compose, acessar [http://localhost:3000](http://localhost:3000).
    - Em Dashboards, importar os templates 20727 e 21308. 

## 📜 Licença

Distribuído sob a licença MIT. Consulte o arquivo `LICENSE` para mais detalhes.

---

## 👨‍💻 Autor

Template criado por Alex Araujo.