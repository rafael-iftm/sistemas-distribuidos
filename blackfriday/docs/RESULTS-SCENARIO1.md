# Análise dos Resultados do Teste de Carga

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
- **Requisições Bem-Sucedidas (200 OK):** 32 (10.66%)
- **Requisições com Falha (409 Conflict):** 268 (89.33%)

### Desempenho das Requisições

- **Tempo Médio de Resposta:** 7.45ms
- **Tempo Mínimo de Resposta:** 847.3µs
- **Tempo Máximo de Resposta:** 111.14ms
- **Tempo de Resposta P90:** 9.09ms
- **Tempo de Resposta P95:** 16.01ms

### Tempos de Conexão

- **Tempo Médio de Conexão:** 3.38µs
- **Tempo Máximo de Conexão:** 508.2µs

### Outros Tempos de Processamento

- **Tempo Médio de Recebimento:** 249.51µs
- **Tempo Médio de Envio:** 12.96µs

### Detalhamento das Requisições

- **Tempo Médio de Espera:** 7.19ms
- **Tempo Médio de Bloqueio:** 172.54µs

## Análise das Requisições

1. **Tempo Médio de Resposta para Cada Requisição:**
   - Calculado como 7.45ms.
   - Requisições bem-sucedidas têm um tempo médio ligeiramente maior (8.74ms) devido ao processamento adicional de salvamento e débito de estoque.

2. **Tempo Total para Responder Todas as Requisições:**
   - O tempo total para processar e responder todas as requisições foi 2.4161 minutos.

3. **Status das Requisições:**
   - **Requisições Bem-Sucedidas (200 OK):** 32
   - **Requisições com Erro (409 Conflict):** 268
   - **Outros Códigos de Erro:** Nenhum registrado.

4. **Inconsistências Detectadas:**
   - Nenhuma inconsistência detectada no estoque, como estoque negativo ou variações no saldo final de estoque entre as abordagens.
   - Todas as requisições com falha foram devidas à insuficiência de estoque (409 Conflict).

## Considerações Finais

Os resultados mostram um alto número de requisições falhadas devido à insuficiência de estoque, o que era esperado dado o cenário de teste com estoque limitado e compras de múltiplas unidades por requisição. O sistema manteve consistência no manejo do estoque, retornando erros apropriados quando necessário e processando corretamente as requisições bem-sucedidas.
