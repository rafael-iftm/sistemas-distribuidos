package cliente;
import java.io.*; // Para manipulação de entrada/saída
import java.net.*; // Para sockets e conexões de rede

// Classe principal do cliente
public class Cliente {
    public static void main(String[] args) {
        String servidorEndereco = "localhost"; // Endereço do servidor
        int porta = 12345; // Porta para conectar

        try (Socket cliente = new Socket(servidorEndereco, porta); // Criação do socket do cliente
             DataInputStream entrada = new DataInputStream(cliente.getInputStream());
             DataOutputStream saida = new DataOutputStream(cliente.getOutputStream());
             BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Conectado ao servidor em " + servidorEndereco + ":" + porta);

            String mensagem;
            while (true) {
                System.out.print("Digite uma mensagem: ");
                mensagem = teclado.readLine(); // Lê entrada do teclado

                // Envia mensagem ao servidor
                saida.writeUTF(mensagem);
                saida.flush(); // Garante envio imediato

                // Lê resposta do servidor
                String resposta = entrada.readUTF();
                System.out.println("Servidor respondeu: " + resposta);
            }
        } catch (IOException e) {
            System.err.println("Erro no cliente: " + e.getMessage());
        }
    }
}

