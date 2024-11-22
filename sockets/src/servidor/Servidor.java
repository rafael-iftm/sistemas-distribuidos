package servidor;
import java.io.*; // Para manipulação de entrada/saída
import java.net.*; // Para sockets e conexões de rede

// Classe principal do servidor
public class Servidor {
    public static void main(String[] args) {
        int porta = 12345; // Porta que o servidor vai escutar
        try (ServerSocket servidor = new ServerSocket(porta)) { // Criação do socket do servidor
            System.out.println("Servidor iniciado na porta " + porta);

            // Loop infinito para aceitar múltiplas conexões de clientes
            while (true) {
                Socket conexao = servidor.accept(); // Aguarda conexão de um cliente
                System.out.println("Cliente conectado: " + conexao.getInetAddress());

                // Cria uma nova thread para gerenciar a comunicação com o cliente
                new Thread(new GerenciadorDeCliente(conexao)).start();
            }
        } catch (IOException e) {
            System.err.println("Erro no servidor: " + e.getMessage());
        }
    }
}

// Classe para gerenciar a comunicação com um cliente específico
class GerenciadorDeCliente implements Runnable {
    private Socket conexao; // Socket do cliente

    public GerenciadorDeCliente(Socket conexao) {
        this.conexao = conexao; // Associa o socket à thread
    }

    @Override
    public void run() {
        try (DataInputStream entrada = new DataInputStream(conexao.getInputStream());
             DataOutputStream saida = new DataOutputStream(conexao.getOutputStream())) {

            String mensagem;
            while ((mensagem = entrada.readUTF()) != null) { // Lê mensagem do cliente
                System.out.println("Cliente disse: " + mensagem);

                // Envia uma resposta ao cliente
                saida.writeUTF("Servidor recebeu: " + mensagem);
                saida.flush(); // Garante envio imediato
            }
        } catch (IOException e) {
            System.err.println("Erro ao comunicar com o cliente: " + e.getMessage());
        } finally {
            try {
                conexao.close(); // Fecha o socket ao finalizar
            } catch (IOException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
}
