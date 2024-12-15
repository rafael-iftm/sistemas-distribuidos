# Projeto: Controle de Concorrência no Gerenciamento de Pedidos

Este projeto demonstra o uso de três abordagens de controle de concorrência em um sistema de gerenciamento de pedidos para um e-commerce durante a Black Friday. As abordagens implementadas são:

1. **Sem controle de locks**
2. **Lock otimista** (com controle de versão)
3. **Lock pessimista** (com bloqueio explícito)

## Requisitos do Sistema

Certifique-se de que seu ambiente atende aos seguintes requisitos:

- **Java**: Versão 11 ou superior
- **Maven**: Versão 3.6 ou superior
- **IDE**: IntelliJ, Eclipse ou outra de sua preferência
- **Postman** ou outro cliente REST: Para testar os endpoints

## Configuração do Projeto

### 1. Clone o Repositório

```bash
git https://github.com/rafael-iftm/sistemas-distribuidos
cd sistemas-distribuidos/blackfriday
```

### 2. Configuração do Banco de Dados

Este projeto utiliza o banco de dados H2 em memória para simplificar a configuração e execução. Não é necessário instalar ou configurar um banco de dados externo.

A configuração do H2 está definida no arquivo `src/main/resources/application.yml`

### 3. Construção e Execução

1. **Compile o projeto:**

```bash
mvn clean install
```

2. **Execute o aplicativo:**

```bash
mvn spring-boot:run
```

### 4. Acessando o Console do H2

Após iniciar o aplicativo, você pode acessar o console do H2 para visualizar os dados diretamente no navegador:

- URL: http://localhost:8080/h2-console
- JDBC URL: jdbc:h2:mem:testdb
- Username: sa
- Password: (deixe em branco)

Depois de se conectar, você pode rodar consultas SQL, como:

```bash
SELECT * FROM produto;
SELECT * FROM pedido;
```

## Testando as Funcionalidades

### Endpoints Disponíveis

Os seguintes endpoints REST foram implementados para cada abordagem de controle de concorrência:

1. Sem controle de locks
    - `POST /pedido/novo`

    ```bash
    curl -X POST "http://localhost:8080/pedido/novo?produtoId=1&quantidade=2"
    ```

2. Lock otimista (com controle de versão)
    - `POST /pedido/novo_otimista`

    ```bash
    curl -X POST "http://localhost:8080/pedido/novo_otimista?produtoId=1&quantidade=2"
    ```

3. Lock pessimista (com bloqueio explícito)
    - `POST /pedido/novo_pessimista`

    ```bash
    curl -X POST "http://localhost:8080/pedido/novo_pessimista?produtoId=1&quantidade=2"
    ```

## Cenários de Validação

### Como instalar o K6
1. Acesse o site oficial [clicando aqui](https://grafana.com/docs/k6/latest/set-up/install-k6/)
2. Faça o setup de acordo com o seu sistema operacional
3. Verifique se o setup foi feito corretamente digitando o comando `k6` no seu terminal

### Cenário 1: Sem controle de locks

1. Envie requisições simultâneas para o endpoint `POST /pedido/novo`.
2. Comando para executar o script K6:
    ```bash
    k6 run src/k6/scenario1.js
    ```
3. Resultado esperado:
   - Observe que conflitos podem ocorrer, resultando em inconsistências no processamento dos pedidos, como duplicação de estoque ou falha ao processar o pedido devido a alterações simultâneas.
   - Dados coletados:
     - Tempo médio de resposta.
     - Tempo total para responder todas as requisições.
     - Quantidade de requisições bem-sucedidas (200 OK) e as que retornaram erro (409 Conflict).

### Cenário 2: Lock otimista

1. Envie requisições simultâneas para o endpoint `POST /pedido/novo_otimista`.
2. Comando para executar o script K6:
    ```bash
    k6 run src/k6/scenario2.js
    ```
3. Resultado esperado:
   - Observe que, se a versão do produto for diferente entre as requisições, o sistema irá lançar uma exceção de "conflito de versão" e não permitirá que o pedido seja salvo.
   - Dados coletados:
     - Tempo médio de resposta.
     - Tempo total para responder todas as requisições.
     - Quantidade de requisições bem-sucedidas (200 OK) e as que retornaram erro (409 Conflict).

### Cenário 3: Lock pessimista

1. Envie requisições simultâneas para o endpoint `POST /pedido/novo_pessimista`.
2. Comando para executar o script K6:
    ```bash
    k6 run src/k6/scenario3.js
    ```
3. Resultado esperado:
   - Observe que as requisições são processadas sequencialmente. O segundo pedido aguardará o primeiro ser finalizado antes de ser processado, garantindo que o estoque e os dados do pedido não sejam corrompidos.
   - Dados coletados:
     - Tempo médio de resposta.
     - Tempo total para responder todas as requisições.
     - Quantidade de requisições bem-sucedidas (200 OK) e as que retornaram erro (409 Conflict).

### Coleta de Dados

Após realizar os testes, foram coletados os seguintes dados para as três abordagens implementadas (sem locks, lock otimista e lock pessimista):

1. **Tempo médio de resposta para cada requisição**: O tempo médio necessário para que cada requisição fosse processada e respondida.
2. **Tempo total para responder todas as requisições**: O tempo total necessário para processar e responder todas as requisições enviadas ao servidor.
3. **Status das Requisições**: A quantidade de requisições bem-sucedidas (200 OK) e as que retornaram erro:
   - 409 (Conflict) para situações de estoque insuficiente (no caso do lock otimista e lock pessimista).
   - Outros códigos de erro, se aplicável.
4. **Inconsistências Detectadas**: Foram verificadas e reportadas as seguintes inconsistências no sistema:
   - Estoque negativo.
   - Pedidos processados com quantidade de estoque incorreta.

A análise detalhada de cada cenário e a comparação dos resultados pode ser encontrada na pasta `/docs`. Ela aborda as métricas de desempenho como o tempo de resposta, status das requisições, inconsistências e outras variáveis importantes que foram observadas durante os testes.
