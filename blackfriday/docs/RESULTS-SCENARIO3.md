# Análise dos Resultados do Teste de Carga - Cenário 3

## Cenário de Teste

- **Produto Testado:** Produto 1
- **Preço:** R$ 19.99
- **Estoque Inicial:** 100 unidades
- **Requisições:** Compras entre 1 a 5 unidades por requisição

## Requisições e Processo

Cada requisição realiza as seguintes etapas:
1. Buscar o estoque no banco de dados.
2. Retornar uma resposta de erro (409 Conflict) caso não haja estoque suficiente.
3. Caso tenha estoque, salvar o pedido com os itens comprados.
4. Debitar o estoque do produto.
5. Operações de salvamento e débito de estoque são atômicas dentro da mesma transação.

## Resultados

### Estatísticas Gerais

- **Total de Requisições:** 300
- **Requisições Bem-Sucedidas (200 OK):** 35 (11.66%)
- **Requisições com Falha (409 Conflict):** 265 (88.33%)

### Desempenho das Requisições

- **Tempo Médio de Resposta:** 10.19ms
- **Tempo Mínimo de Resposta:** 368.5µs
- **Tempo Máximo de Resposta:** 204.95ms
- **Tempo de Resposta P90:** 5.64ms
- **Tempo de Resposta P95:** 13.9ms

### Tempos de Conexão

- **Tempo Médio de Conexão:** 6.23µs
- **Tempo Máximo de Conexão:** 467.3µs

### Outros Tempos de Processamento

- **Tempo Médio de Recebimento:** 236.26µs
- **Tempo Médio de Envio:** 4.82µs

### Detalhamento das Requisições

- **Tempo Médio de Espera:** 9.95ms
- **Tempo Médio de Bloqueio:** 195.09µs

## Análise das Requisições

1. **Tempo Médio de Resposta para Cada Requisição:**
   - Calculado como 10.19ms.
   - Requisições bem-sucedidas têm um tempo médio ligeiramente maior (64.44ms) devido ao processamento adicional de salvamento e débito de estoque.

2. **Tempo Total para Responder Todas as Requisições:**
   - O tempo total para processar e responder todas as requisições foi 2.6725 minutos.

3. **Status das Requisições:**
   - **Requisições Bem-Sucedidas (200 OK):** 35
   - **Requisições com Erro (409 Conflict):** 265
   - **Outros Códigos de Erro:** Nenhum registrado.

4. **Inconsistências Detectadas:**
   - Nenhuma inconsistência detectada no estoque, como estoque negativo ou variações no saldo final de estoque entre as abordagens.
   - Todas as requisições com falha foram devidas à insuficiência de estoque (409 Conflict).

## Considerações Finais

Os resultados indicam que o sistema teve um desempenho consistente ao processar as requisições, embora tenha falhado em 88.33% delas devido à falta de estoque. As requisições bem-sucedidas foram processadas rapidamente, com tempos de resposta dentro da média esperada. O sistema manteve a integridade do estoque, e os erros (409 Conflict) foram retornados corretamente quando o estoque não foi suficiente para atender a demanda.
