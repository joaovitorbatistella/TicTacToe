package com.mycompany;

import java.util.Scanner;

public class TicTacToe {
	static int LENGTH = 3, DEPTH = -1;

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// instancia classe que será usada para o tabuleiro
		Board b = new Board(LENGTH);
		
		// instancia classe que contém o algoritmo MiniMax
		MINIMAX mm = new MINIMAX(LENGTH, DEPTH);
		
		int totalTime = 0;
		
		// looping até que algum jogador ganhe ou empate
		do {
			int x, y;
			
			// validação de jogada, se for inválida, permite jogar novamente
			boolean valid = true;
			do {
				b.print();
				System.out.println("Sua jogada> \r\n Linha [0-3]: ");
				x = input.nextInt();
				
				System.out.println("Sua jogada> \r\n Coluna [0-3]: ");
				y = input.nextInt();
				
				valid = b.makeMove(x, y);
			} while(!valid);
			b.print();
			
			// verifica se board não contém um final de jogo
			if(!mm.terminalTest(b.board)); {
				long time = System.currentTimeMillis();
				// Busca a melhor jogada
				// mm.decision() 		- sem poda alfa-beta
				// mm.decisionPruning() - com poda alfa-beta
				b.board = mm.decisionPruning(b.board);
				time = System.currentTimeMillis() - time;
				
				System.out.println("Jogada do Computador ("+ time + " ms)");
				totalTime += time;
				b.print();
			}
			
		} while(!mm.terminalTest(b.board));
		
		input.close();
		
		// Imprime resultado da partida
		int u = mm.util(b.board);
		if(u < 0) {
			System.out.println("Parabéns! Você ganhou...");
		} else if(u == 0) {
			System.out.println("Empatou");
		} else {
			System.out.println("Computador ganhou!");
		}
		
		// 	Imprime resiltados de performance do algoritmo MiniMax
		// para comparar a utilização ou não da poda alfa-beta
		System.out.println("\n============ Results ============");
		System.out.println("\nExpandeds: " + mm.expanded);
		System.out.println("\nVerifieds: " + mm.verifieds);
		System.out.println("\nTotal IA time: " + totalTime);
	}
}
