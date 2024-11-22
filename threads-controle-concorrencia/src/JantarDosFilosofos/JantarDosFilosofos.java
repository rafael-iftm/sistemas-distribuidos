// O que ensina?
// Aborda o problema clássico do controle de concorrência, demonstrando como evitar deadlocks e gerenciar recursos compartilhados (garfos) entre threads (filósofos).

package JantarDosFilosofos;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class JantarDosFilosofos {

    public static void main(String[] args) {
        final int NUM_FILOSOFOS = 5;
        Filosofo[] filosofos = new Filosofo[NUM_FILOSOFOS];
        Lock[] garfos = new Lock[NUM_FILOSOFOS];

        // Inicializando os garfos como recursos compartilhados
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            garfos[i] = new ReentrantLock();
        }

        // Criando os filósofos e associando seus garfos
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            filosofos[i] = new Filosofo(i, garfos[i], garfos[(i + 1) % NUM_FILOSOFOS]);
            new Thread(filosofos[i], "Filósofo-" + i).start();
        }
    }
}

// Classe que representa um filósofo
class Filosofo implements Runnable {
    private final int id;
    private final Lock garfoEsquerdo;
    private final Lock garfoDireito;

    public Filosofo(int id, Lock garfoEsquerdo, Lock garfoDireito) {
        this.id = id;
        this.garfoEsquerdo = garfoEsquerdo;
        this.garfoDireito = garfoDireito;
    }

    @Override
    public void run() {
        try {
            while (true) {
                pensar();  // Filósofo pensa
                pegarGarfos();  // Tenta pegar os garfos
                comer();  // Come
                soltarGarfos();  // Solta os garfos
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void pensar() throws InterruptedException {
        System.out.println("Filósofo-" + id + " está pensando.");
        Thread.sleep((int) (Math.random() * 1000));
    }

    private void pegarGarfos() {
        garfoEsquerdo.lock();
        garfoDireito.lock();
    }

    private void comer() throws InterruptedException {
        System.out.println("Filósofo-" + id + " está comendo.");
        Thread.sleep((int) (Math.random() * 1000));
    }

    private void soltarGarfos() {
        garfoDireito.unlock();
        garfoEsquerdo.unlock();
    }
}
