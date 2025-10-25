# 🧱 MELI Hexagonal Order API

Projeto de exemplo de arquitetura **Hexagonal (Ports & Adapters)** em **Java + Spring Boot**, com **Swagger** e **testes unitários (JUnit + Mockito)**.  
Simula um caso de uso real de **cadastro e gerenciamento de pedidos (Orders)**.

---

## ⚙️ Tecnologias utilizadas
- Java 17  
- Spring Boot 3.3  
- Spring Data JPA  
- H2 Database (em memória)  
- Springdoc OpenAPI (Swagger)  
- JUnit 5 / Mockito  
- Maven

---

## 🏗️ Arquitetura Hexagonal

A arquitetura hexagonal (ou Ports & Adapters) separa a aplicação em **camadas independentes**, isolando o **domínio** da **infraestrutura**.

```
                 +-----------------------+
                 |   Controller REST     |  --> Adapter IN
                 +-----------+-----------+
                             |
                             v
                 +-----------------------+
                 |  Application Service  |  --> Casos de uso (UseCases)
                 +-----------+-----------+
                             |
                 +-----------v-----------+
                 |     Domain Model      |  --> Regras de negócio
                 +-----------+-----------+
                             |
                 +-----------v-----------+
                 |   Persistence Adapter |  --> Adapter OUT
                 +-----------------------+
```

📁 **Pacotes principais:**
```
com.meli.hexagonal
 ├── application
 │    ├── port.in         # Interfaces de entrada (use cases)
 │    ├── port.out        # Interfaces de saída (persistence, APIs externas)
 │    └── service         # Implementações dos casos de uso
 ├── domain
 │    ├── model           # Entidades e regras de negócio
 │    └── exception       # Exceções de domínio
 ├── infrastructure
 │    ├── adapter.in.web  # REST Controllers (entrada)
 │    ├── adapter.out.persistence  # JPA / banco de dados
 │    └── config          # Configurações (Swagger, exceções)
```

---

## 🚀 Como executar o projeto

### 1️⃣ Pré-requisitos
- JDK 17+
- Maven 3.9+

### 2️⃣ Executar o projeto

```bash
mvn clean spring-boot:run
```

### 3️⃣ Acessar endpoints

- **Swagger UI:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- **H2 Console:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)  
  - JDBC URL: `jdbc:h2:mem:meli_hexagonal_db`
  - User: `sa`
  - Password: *(vazio)*

---

## 📚 Endpoints principais

| Método | Endpoint       | Descrição                |
|---------|----------------|--------------------------|
| `POST`  | `/orders`      | Cria novo pedido         |
| `GET`   | `/orders/{id}` | Busca pedido por ID      |
| `DELETE`| `/orders/{id}` | Cancela pedido existente |

**Exemplo JSON (POST /orders):**
```json
{
  "customerName": "João Silva"
}
```

---

## 🧪 Testes unitários

Rodar todos os testes:
```bash
mvn test
```

**Cobertura de testes:**
- `OrderServiceTest` → testa criação e cancelamento de pedidos  
- `OrderControllerTest` → testa endpoint de criação com MockMvc  

---

## 🧩 Configuração do Swagger

Arquivo: `application.yml`
```yaml
swagger:
  title: MELI Hexagonal Orders API
  description: API exemplo com arquitetura hexagonal
  version: @project.version@
```

O valor `@project.version@` é substituído automaticamente pelo valor definido no `pom.xml` (via resource filtering).

---

## 🧠 Como estender o projeto

1️⃣ Crie um novo **Use Case** na pasta `application.port.in` (ex: `ListOrdersUseCase.java`)  
2️⃣ Implemente-o na camada `service` (ex: `OrderService.java`)  
3️⃣ Adicione uma nova rota no `OrderController`  
4️⃣ Caso use banco, defina uma interface em `port.out` e crie um **Adapter** correspondente em `adapter.out.persistence`  

---

## 🧰 Estrutura final

```
meli-hexagonal-order
 ├── application
 │    ├── port
 │    │    ├── in
 │    │    │    └── CreateOrderUseCase.java
 │    │    └── out
 │    │         ├── SaveOrderPort.java
 │    │         └── LoadOrderPort.java
 │    └── service
 │         └── OrderService.java
 ├── domain
 │    ├── model
 │    │    └── Order.java
 │    └── exception
 │         └── InvalidOrderException.java
 ├── infrastructure
 │    ├── adapter
 │    │    ├── in.web
 │    │    │    └── OrderController.java
 │    │    └── out.persistence
 │    │         ├── JpaOrderRepository.java
 │    │         └── OrderPersistenceAdapter.java
 │    └── config
 │         ├── SwaggerConfig.java
 │         └── GlobalExceptionHandler.java
 ├── resources
 │    └── application.yml
 └── test
      ├── application.service
      │    └── OrderServiceTest.java
      └── infrastructure.adapter.in.web
           └── OrderControllerTest.java
```

---

## 🧾 Licença
Projeto de exemplo educacional — livre para estudo e uso em entrevistas técnicas.
