package Puzzle_8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;          // conjuntos de elementos únicos sem ordem específica (implementado com hashSet)

public class Puzzle8 {
	
    Estado estadoInicial;
    private Set<Estado> visitados;     // conjunto de estados vizitados

    public Puzzle8() {                  // Construtor - novo objeto estado é criado e embaralhado
        this.estadoInicial = new Estado();
        this.estadoInicial.embaralhar();
        this.estadoInicial.setMovimento(null); 
        this.visitados = new HashSet<>();
    }

    public void buscaHorizontal() {
        long inicio = System.nanoTime();   // inicializa o tempo
        int nosVisitados = 0;              

        Queue<Estado> fila = new LinkedList<>();
        fila.add(this.estadoInicial);               // adiciona o estado inicial à fila de estados a serem visitados
        this.visitados.add(this.estadoInicial);     // adiciona o estado inicial ao conjunto de estado visitados

        while (!fila.isEmpty()) {               // Enquanto a fila não estiver vazia
            Estado estadoAtual = fila.poll();     // remove o próxmo estado da fila
            nosVisitados++;           // incrementa o conador de nós vizitados

            if (estadoAtual.estaResolvido()) {          // Verifica se o estado atual é o estado final (resolvido)
                long fim = System.nanoTime();
                System.out.println("\nEstado final:");
                estadoAtual.imprimir();
                System.out.println("\nNós visitados: " + nosVisitados);
                System.out.println("Tempo de busca: " + ((fim - inicio) / 1e9) + " segundos");    // conversão para segundos

                System.out.println("\nConjunto solução:");
                List<Estado> caminho = new ArrayList<>();
                List<String> movimentos = new ArrayList<>(); // lista para armazenar os movimentos
                Estado estado = estadoAtual;
                while (estado != null) {
                    caminho.add(estado);              // adiciona estados ao caminho
                    if (estado.getMovimento() != null) {
                        movimentos.add(estado.getMovimento());        // armazena os movimentos
                    }
                    estado = estado.pai;      //  seta o estado pai do caminho
                }
                Collections.reverse(caminho); // inverter o caminho para começar do estado inicial
                Collections.reverse(movimentos); // inverter a lista de movimentos para começar do estado inicial
                for (Estado e : caminho) {    
                    e.imprimir();             // imprime o caminho
                    System.out.println();
                }

                System.out.println("\nSequência de movimentos:");
                for (String movimento : movimentos) {
                    System.out.println("Movimento: " + movimento);   // imprime movimentos
                }

                return;
            }

            for (Estado vizinho : estadoAtual.gerarVizinhos()) {        
                if (!this.visitados.contains(vizinho)) { // Verificar se o vizinho já foi visitado
                    fila.add(vizinho);            // adiciona os vizinhos desse estado a fila
                    this.visitados.add(vizinho); // Adiciona os vizinhos ao conjunto de estados visitados
                }
        
            }
        }
    }
    
    public void buscaAStar() {
        long inicio = System.nanoTime();
        int nosVisitados = 0;
        Estado estadoAtual = null;

        PriorityQueue<Estado> fila = new PriorityQueue<>(Comparator.comparingInt(this::heuristica));   // elementos com menor valor de heurística terão prioridade na fila
        fila.add(this.estadoInicial);

        while (!fila.isEmpty()) {
            estadoAtual = fila.poll();
            nosVisitados++;

            if (estadoAtual.estaResolvido()) {
                break;
            }

            for (Estado vizinho : estadoAtual.gerarVizinhos()) {
                if (!this.visitados.contains(vizinho)) {          // Verificar se o vizinho já foi visitado
                    fila.add(vizinho);                       // Adiciona os vizinhos desse estado a fila
                    this.visitados.add(vizinho);            // Adiciona os vizinhos ao conjunto de estados visitados
                } 
            }
        }

        // Verifique se uma solução foi encontrada
        if (estadoAtual != null && estadoAtual.estaResolvido()) {
            long fim = System.nanoTime();
            System.out.println("\nEstado final:");
            estadoAtual.imprimir();
            System.out.println("\nNós visitados: " + nosVisitados);
            System.out.println("Tempo de busca: " + ((fim - inicio) / 1e9) + " segundos");

            System.out.println("\nConjunto solução:");
            List<Estado> caminho = new ArrayList<>();
            List<String> movimentos = new ArrayList<>(); // lista para armazenar os movimentos
            Estado estado = estadoAtual;
            while (estado != null) {
                caminho.add(estado);
                if (estado.getMovimento() != null) {
                    movimentos.add(estado.getMovimento());
                }
                estado = estado.pai;
            }
            Collections.reverse(caminho); // inverter o caminho para começar do estado inicial
            Collections.reverse(movimentos); // inverter a lista de movimentos para começar do estado inicial
            for (Estado e : caminho) {
                e.imprimir();
                System.out.println();
            }

            System.out.println("\nSequência de movimentos:");
            for (String movimento : movimentos) {
                System.out.println("Movimento: " + movimento);
            }
        }
    }


    private int heuristica(Estado estado) {      // Conta as peças que estão fora do lugar
        int peçasForaDoLugar = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (estado.tabuleiro[i][j] != i * 3 + j + 1) {    // verifica se a peça está no lugar
                    peçasForaDoLugar++;              // caso negativo incrementa o contador
                }
            }
        }
        return peçasForaDoLugar;
    }
    
    public void buscaAStar2() {
        long inicio = System.nanoTime();
        int nosVisitados = 0;
        Estado estadoAtual = null;

        PriorityQueue<Estado> fila = new PriorityQueue<>(Comparator.comparingInt(this::heuristica2));    // elementos com menor valor de heurística terão prioridade na fila
        fila.add(this.estadoInicial);

        while (!fila.isEmpty()) {
            estadoAtual = fila.poll();
            nosVisitados++;

            if (estadoAtual.estaResolvido()) {
                break;
            }

            for (Estado vizinho : estadoAtual.gerarVizinhos()) {
                if (!this.visitados.contains(vizinho)) {            // Verificar se o vizinho já foi visitado
                    fila.add(vizinho);                             // Adiciona os vizinhos desse estado a fila
                    this.visitados.add(vizinho);                   // Adiciona os vizinhos ao conjunto de estados visitados
                }
            }
        }

        // Verifique se uma solução foi encontrada
        if (estadoAtual != null && estadoAtual.estaResolvido()) {
            long fim = System.nanoTime();
            System.out.println("\nEstado final:");
            estadoAtual.imprimir();
            System.out.println("\nNós visitados: " + nosVisitados);
            System.out.println("Tempo de busca: " + ((fim - inicio) / 1e9) + " segundos");

            System.out.println("\nConjunto solução:");
            List<Estado> caminho = new ArrayList<>();
            List<String> movimentos = new ArrayList<>(); // lista para armazenar os movimentos
            Estado estado = estadoAtual;
            while (estado != null) {
                caminho.add(estado);
                if (estado.getMovimento() != null) {
                    movimentos.add(estado.getMovimento());
                }
                estado = estado.pai;
            }
            Collections.reverse(caminho); // inverter o caminho para começar do estado inicial
            Collections.reverse(movimentos); // inverter a lista de movimentos para começar do estado inicial
            for (Estado e : caminho) {
                e.imprimir();
                System.out.println();
            }

            System.out.println("\nSequência de movimentos:");
            for (String movimento : movimentos) {
                System.out.println("Movimento: " + movimento);
            }
        }
    }


    private int heuristica2(Estado estado) {
        int peçasForaDoLugar = 0;
        int distanciaManhattan = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int valor = estado.tabuleiro[i][j];
                if (valor != 0) { // não considerar a peça vazia
                    peçasForaDoLugar += valor != i * 3 + j + 1 ? 1 : 0;       // retorna 1 se true e 0 se false
                    distanciaManhattan += Math.abs(i - (valor - 1) / 3) + Math.abs(j - (valor - 1) % 3);    // índice de linha + índice de coluna
                }
            }
        }
        return peçasForaDoLugar + distanciaManhattan;
    }



}

