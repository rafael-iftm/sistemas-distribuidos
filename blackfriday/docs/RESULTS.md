# Comparação dos Cenários de Teste de Carga

| Métrica                                | Cenário 1                      | Cenário 2                      | Cenário 3                      |
|----------------------------------------|--------------------------------|--------------------------------|--------------------------------|
| **Produto Testado**                    | Produto 1                      | Produto 1                      | Produto 1                      |
| **Preço**                              | R$ 19.99                       | R$ 19.99                       | R$ 19.99                       |
| **Estoque Inicial**                    | 100 unidades                   | 100 unidades                   | 100 unidades                   |
| **Total de Requisições**               | 300                            | 300                            | 300                            |
| **Requisições Bem-Sucedidas (200 OK)** | 276 (92%)                     | 33 (11%)                       | 35 (11.66%)                    |
| **Requisições com Falha (409 Conflict)** | 24 (8%)                       | 267 (89%)                      | 265 (88.33%)                   |
| **Tempo Médio de Resposta**            | 10.65ms                        | 9.59ms                         | 10.19ms                        |
| **Tempo Mínimo de Resposta**           | 207.9µs                        | 443.3µs                        | 368.5µs                        |
| **Tempo Máximo de Resposta**           | 17.43ms                        | 164.34ms                       | 204.95ms                       |
| **Tempo de Resposta P90**              | 13.62ms                        | 10.92ms                        | 5.64ms                         |
| **Tempo de Resposta P95**              | 16.21ms                        | 17.21ms                        | 13.9ms                         |
| **Tempo Médio de Conexão**             | 8.36µs                         | 13.43µs                        | 6.23µs                         |
| **Tempo Máximo de Conexão**            | 143.3µs                        | 503.9µs                        | 467.3µs                        |
| **Tempo Médio de Recebimento**         | 166.45µs                       | 313.16µs                       | 236.26µs                       |
| **Tempo Médio de Envio**               | 2.71µs                         | 9.49µs                         | 4.82µs                         |
| **Tempo Médio de Espera**              | 10.35ms                        | 9.27ms                         | 9.95ms                         |
| **Tempo Médio de Bloqueio**            | 99.93µs                        | 310.18µs                       | 195.09µs                       |
| **Tempo Total para Responder Todas as Requisições** | 2.91 minutos                 | 2.2974 minutos                 | 2.6725 minutos                 |
| **Iterations por Segundo**             | 9.88/s                         | 9.88/s                         | 9.8887/s                       |

