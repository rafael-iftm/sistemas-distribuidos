// O que ensina?
// Implementa a sincronização entre threads (produtor e consumidor) usando wait() e notifyAll(), garantindo acesso seguro ao buffer compartilhado.

package ProdutorConsumidor;
import java.util.LinkedList;
import java.util.Queue;

public class ProdutorConsumidor {

    public static void main(String[] args) {
        // Buffer compartilhado com capacidade limitada
        Buffer buffer = new Buffer(5);

        // Criação do produtor e consumidor como threads separadas
        Thread produtor = new Thread(new Produtor(buffer), "Produtor");
        Thread consumidor = new Thread(new Consumidor(buffer), "Consumidor");

        // Início das threads
        produtor.start();
        consumidor.start();
    }
}

// Classe que implementa o buffer compartilhado entre produtor e consumidor
class Buffer {
    private final Queue<Integer> fila; // Fila para armazenar os itens
    private final int capacidade;     // Capacidade máxima do buffer

    public Buffer(int capacidade) {
        this.fila = new LinkedList<>();
        this.capacidade = capacidade;
    }

    // Método sincronizado para produzir um item
    public synchronized void produzir(int item) throws InterruptedException {
        // Aguarda até que haja espaço disponível no buffer
        while (fila.size() == capacidade) {
            wait();
        }
        fila.add(item); // Adiciona o item ao buffer
        System.out.println("Produzido: " + item);
        notifyAll(); // Notifica as threads que estão esperando
    }

    // Método sincronizado para consumir um item
    public synchronized int consumir() throws InterruptedException {
        // Aguarda até que haja itens disponíveis no buffer
        while (fila.isEmpty()) {
            wait();
        }
        int item = fila.poll(); // Remove o item do buffer
        System.out.println("Consumido: " + item);
        notifyAll(); // Notifica as threads que estão esperando
        return item;
    }
}

// Classe que implementa o produtor
class Produtor implements Runnable {
    private final Buffer buffer;

    public Produtor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int item = 0;
        try {
            // Produz itens continuamente
            while (true) {
                buffer.produzir(item++);
                Thread.sleep(500); // Tempo entre produções
            }
        } catch (InterruptedException e) {
            System.out.println("Produtor interrompido.");
        }
    }
}

// Classe que implementa o consumidor
class Consumidor implements Runnable {
    private final Buffer buffer;

    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            // Consome itens continuamente
            while (true) {
                buffer.consumir();
                Thread.sleep(1000); // Tempo entre consumos
            }
        } catch (InterruptedException e) {
            System.out.println("Consumidor interrompido.");
        }
    }
}
