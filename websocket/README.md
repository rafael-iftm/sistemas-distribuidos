# Introdução aos Sockets em Java

## Descrição
Este projeto demonstra a comunicação cliente/servidor utilizando sockets TCP. Ele inclui:
- Um servidor que aceita conexões de múltiplos clientes.
- Um cliente que envia e recebe mensagens.

## Requisitos
- JDK 11 ou superior.
- VS Code com Java Extension Pack.

## Como Executar
1. Compile o servidor e o cliente:
   ```bash
   javac src/servidor/Servidor.java src/cliente/Cliente.java
   ```

2. Inicie o servidor:
   ```bash
   java src/servidor/Servidor.java
   ```

3. Inicie o cliente:
   ```bash
   java src/cliente/Cliente.java
   ```

4. Troque mensagens entre cliente e servidor.

## Estrutura

- Servidor.java: Código do servidor TCP.
- Cliente.java: Código do cliente TCP.
