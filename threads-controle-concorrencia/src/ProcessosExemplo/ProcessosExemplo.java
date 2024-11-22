// O que ensina?
// Este código simula como processos podem ser executados em paralelo (usando threads) e como o processo principal pode esperar que subprocessos sejam concluídos.

package ProcessosExemplo;
public class ProcessosExemplo {

    public static void main(String[] args) {
        System.out.println("Processo principal iniciado.");

        // Criando subprocessos simulados usando threads
        Thread processo1 = new Thread(() -> {
            // Código do subprocesso 1
            System.out.println("Subprocesso 1 executando...");
            try {
                // Simula uma tarefa com duração de 2 segundos
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Erro no subprocesso 1.");
            }
            System.out.println("Subprocesso 1 finalizado.");
        });

        Thread processo2 = new Thread(() -> {
            // Código do subprocesso 2
            System.out.println("Subprocesso 2 executando...");
            try {
                // Simula uma tarefa com duração de 1 segundo
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Erro no subprocesso 2.");
            }
            System.out.println("Subprocesso 2 finalizado.");
        });

        // Iniciando os subprocessos
        processo1.start();
        processo2.start();

        try {
            // Aguarda a conclusão de ambos os subprocessos
            processo1.join();
            processo2.join();
        } catch (InterruptedException e) {
            System.out.println("Erro ao aguardar subprocessos.");
        }

        // Processo principal finaliza após os subprocessos
        System.out.println("Processo principal finalizado.");
    }
}
