# Análise dos Resultados do Teste de Carga - Cenário 2

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
- **Requisições Bem-Sucedidas (200 OK):** 33 (11%)
- **Requisições com Falha (409 Conflict):** 267 (89%)

### Desempenho das Requisições

- **Tempo Médio de Resposta:** 9.59ms
- **Tempo Mínimo de Resposta:** 443.3µs
- **Tempo Máximo de Resposta:** 164.34ms
- **Tempo de Resposta P90:** 10.92ms
- **Tempo de Resposta P95:** 17.21ms

### Tempos de Conexão

- **Tempo Médio de Conexão:** 13.43µs
- **Tempo Máximo de Conexão:** 503.9µs

### Outros Tempos de Processamento

- **Tempo Médio de Recebimento:** 313.16µs
- **Tempo Médio de Envio:** 9.49µs

### Detalhamento das Requisições

- **Tempo Médio de Espera:** 9.27ms
- **Tempo Médio de Bloqueio:** 310.18µs

## Análise das Requisições

1. **Tempo Médio de Resposta para Cada Requisição:**
   - Calculado como 9.59ms.
   - Requisições bem-sucedidas têm um tempo médio ligeiramente maior (11.21ms) devido ao processamento adicional de salvamento e débito de estoque.

2. **Tempo Total para Responder Todas as Requisições:**
   - O tempo total para processar e responder todas as requisições foi 2.2974 minutos.

3. **Status das Requisições:**
   - **Requisições Bem-Sucedidas (200 OK):** 33
   - **Requisições com Erro (409 Conflict):** 267
   - **Outros Códigos de Erro:** Nenhum registrado.

4. **Inconsistências Detectadas:**
   - Nenhuma inconsistência detectada no estoque, como estoque negativo ou variações no saldo final de estoque entre as abordagens.
   - Todas as requisições com falha foram devidas à insuficiência de estoque (409 Conflict).

## Considerações Finais

Os resultados mostram um alto número de requisições falhadas devido à insuficiência de estoque, o que era esperado dado o cenário de teste com estoque limitado e compras de múltiplas unidades por requisição. O sistema manteve consistência no manejo do estoque, retornando erros apropriados quando necessário e processando corretamente as requisições bem-sucedidas.
