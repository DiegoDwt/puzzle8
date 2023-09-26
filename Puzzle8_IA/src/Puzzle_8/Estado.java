package Puzzle_8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Estado {

	    int[][] tabuleiro;
	    private int posX, posY; // posição da peça vazia
	    Estado pai; // campo para armazenar o estado pai
	    private String movimento; // campo para armazenar o movimento que levou a este estado
	    
	    public Estado() {
	        // Inicializar o tabuleiro em estado resolvido
	        this.tabuleiro = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
	        posX = 2;
	        posY = 2;
	    }
	    
	    private Estado(int[][] tabuleiro, int posX, int posY) {
	        this.tabuleiro = tabuleiro;
	        this.posX = posX;
	        this.posY = posY;
	    }
	    
	    
	    public String getMovimento() {
	        return movimento;
	    }

	    public void setMovimento(String movimento) {
	        this.movimento = movimento;
	    }	    

	   
	    public void embaralhar() {
	        Random random = new Random();
	        for (int i = 0; i < 100; i++) {        // altera aleatoriamete 100 vezes o tabuleiro inicial
	            List<Estado> vizinhos = gerarVizinhos();
	            Estado vizinho = vizinhos.get(random.nextInt(vizinhos.size()));
	            this.tabuleiro = vizinho.tabuleiro;   // copia tabuleiro
	            this.posX = vizinho.posX;    // copia as coordenadas do espaço vazio
	            this.posY = vizinho.posY;
	        }
	    }

	    public boolean estaResolvido() {
	        return Arrays.deepEquals(this.tabuleiro, new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});
	    }

	    public List<Estado> gerarVizinhos() {
	        List<Estado> vizinhos = new ArrayList<>();     // lista para armazenar os estados vizinhos
	        int[][] direcoes = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // baixo, cima, direita, esquerda (direções que o espaço vazio pode se mover)
	        String[] nomesDirecoes = {"baixo", "cima", "direita", "esquerda"};    // nome das direções

	        for (int i = 0; i < direcoes.length; i++) {
	            int[] direcao = direcoes[i];
	            int novoX = posX + direcao[0];    // calcula nova posição do espaço vazio
	            int novoY = posY + direcao[1];
	            if (novoX >= 0 && novoX < 3 && novoY >= 0 && novoY < 3) {   // verifica se a posição é válida no limite do tabuleiro ( de 0 a 2 para x e y)
	                int[][] novoTabuleiro = copiarTabuleiro();          // cria cópia do tabuleiro
	                novoTabuleiro[posX][posY] = novoTabuleiro[novoX][novoY];
	                novoTabuleiro[novoX][novoY] = 0;
	                Estado vizinho = new Estado(novoTabuleiro, novoX, novoY);  // cria um novo estado com a nova posição do espaço vazio
	                vizinho.pai = this; // definir o estado pai do vizinho como o estado atual
	                vizinho.setMovimento(nomesDirecoes[i]); // registrar o movimento que levou ao vizinho
	                vizinhos.add(vizinho);   // adiciona o estado a lista de estados vizinhos
	            }
	        }
	        return vizinhos;
	    }


	    
	    private int[][] copiarTabuleiro() {
	        int[][] copia = new int[3][3];
	        for (int i = 0; i < 3; i++) {
	            System.arraycopy(this.tabuleiro[i], 0, copia[i], 0, 3);
	        }
	        return copia;
	    } 

	  

	    public void imprimir() {
	        for (int i = 0; i < 3; i++) {
	            for (int j = 0; j < 3; j++) {
	                System.out.print(tabuleiro[i][j] + " ");
	            }
	            System.out.println();
	        }
	    }
	    
	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        Estado estado = (Estado) o;
	        return posX == estado.posX &&
	                posY == estado.posY &&
	                Arrays.deepEquals(tabuleiro, estado.tabuleiro);
	    }

	    @Override
	    public int hashCode() {
	        int result = Objects.hash(posX, posY);
	        result = 31 * result + Arrays.deepHashCode(tabuleiro);
	        return result;
	    }        

}
