// O que ensina?
// Demonstra como criar e gerenciar threads em Java, explorando o conceito de execução paralela e como sincronizar sua conclusão.

package ThreadsExemplo;
public class ThreadsExemplo {

    public static void main(String[] args) {
        System.out.println("Início da execução com threads.");

        // Criando uma tarefa paralela usando Runnable
        Runnable tarefa = () -> {
            // A thread exibe seu nome e simula uma execução
            System.out.println(Thread.currentThread().getName() + " executando...");
            try {
                // Simula um tempo de trabalho de 1,5 segundos
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " foi interrompida.");
            }
            System.out.println(Thread.currentThread().getName() + " finalizou.");
        };

        // Criando e nomeando duas threads para executar a mesma tarefa
        Thread thread1 = new Thread(tarefa, "Thread-1");
        Thread thread2 = new Thread(tarefa, "Thread-2");

        // Inicia as threads
        thread1.start();
        thread2.start();

        try {
            // Aguarda que ambas as threads concluam
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println("Erro ao aguardar threads.");
        }

        // Mensagem após todas as threads concluírem
        System.out.println("Execução finalizada.");
    }
}
