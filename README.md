# Serviço de Pagamento - Vinhos Sales API

Este é o serviço de pagamento que faz parte do sistema de vendas de vinhos. Ele processa pagamentos para os pedidos e verifica o status dos pagamentos.

## Pré-requisitos

Antes de executar a aplicação, certifique-se de ter os seguintes softwares instalados:

- Java 17
- Maven
- MongoDB
- RabbitMQ

## Instalação

1. Clone o repositório:
    ```bash
    git clone https://github.com/alyssonfp/pagamento-service.git
    ```
2. Navegue até o diretório do projeto:
    ```bash
    cd pagamento-service
    ```
3. Construa o projeto utilizando Maven:
    ```bash
    mvn clean install
    ```
4. Execute a aplicação:
    ```bash
    mvn spring-boot:run
    ```

## Endpoints da API

### Endpoints de Pagamento

- **Processar Pagamento**
    - **POST** `/pagamentos/processar/{pedidoId}`
    - **Parâmetro**: `pedidoId` (ID do pedido)
    - **Resposta**:
      ```json
      {
        "id": "pagamento-id",
        "pedidoId": "pedido-id",
        "status": "WAIT_PAYMENT | DONE | ERROR_PAYMENT",
        "dataCriacao": "data-criacao"
      }
      ```

- **Buscar Pagamento por ID**
    - **GET** `/pagamentos/{id}`
    - **Parâmetro**: `id` (ID do pagamento)
    - **Resposta**:
      ```json
      {
        "id": "pagamento-id",
        "pedidoId": "pedido-id",
        "status": "WAIT_PAYMENT | DONE | ERROR_PAYMENT",
        "dataCriacao": "data-criacao"
      }
      ```