 package Puzzle_8;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Puzzle8 puzzle = new Puzzle8();

        System.out.println("***********Puzzle-8************");
        System.out.println();
        System.out.println("Estado inicial:");
        puzzle.estadoInicial.imprimir();

        System.out.println("\nEscolha o algoritmo de busca:");
        System.out.println("1. Busca horizontal");
        System.out.println("2. Busca A*");
        System.out.println("3. Busca A*2");
        System.out.print("Opção: ");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        int opção = scanner.nextInt();

        switch (opção) {
            case 1:
                puzzle.buscaHorizontal();
                break;
            case 2:
                puzzle.buscaAStar();
                break;
            case 3:
                puzzle.buscaAStar2();
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
        scanner.close();
    }
}
