package com.mycompany;

import java.util.ArrayList;

public class MINIMAX {

	static ArrayList<Successor> successors = new ArrayList<Successor>();
	int length, maxDepth, expanded, verifieds;
	
	public MINIMAX(int length, int maxDepth) {
		this.length = length;
		this.expanded = 0;
		this.verifieds = 0;
		
		if(maxDepth > 0) {
			this.maxDepth = maxDepth;
		} else {
			this.maxDepth = Integer.MAX_VALUE;
		}
	}
	
	public int[][] decision(int[][] board) {
		successors.clear();
		
		// Inicia a busca pela melhor jogada
		int v = max(board, true, 1);
		
		for(Successor s: successors) {
			// 	Busca dentre os sucessores, o primeiro board que tem a utilidade
			// igual ao valor encontrado utilizando minimax
			if(s.util == v) {
				return s.board;
			}
		}
		
		// É esperado que somente quando o jogo terminar, seja retornado o mesmo board
		return board;
	}
	
	public int max(int[][] board, boolean first, int depth)
	{
		// Se a profundidade for maior que a máxima, ou se encontrar um 
		// tabuleiro em que o jogo seja considerado como encerrado
		if(depth++ > this.maxDepth || terminalTest(board)) {
			return util(board);
		}
		
		int v = Integer.MIN_VALUE;
		
		// Expande os próximos movimentos possíveis do jogador 2 (IA)
		for(Successor s: expandSuccessors(board, 1)) {
			this.verifieds++;
			// Busca o maior valor dentre os menores valores encontrados anteriormente
			v = Math.max(v, min(s.board, depth));
			
			// Guarda qual o maior valor para o tabuleiro atual
			s.util = v;
			
			// Se for a primeira expansão
			if(first) {
				successors.add(s);
			}
		}
		
		return v;
	}
	
	public int min(int[][] board, int depth)
	{
		// 	Se a profundidade for maior que a máxima, ou se encontrar um 
		// tabuleiro em que o jogo seja considerado como encerrado
		if(depth++ > maxDepth || terminalTest(board)) {
			return util(board);
		}
		
		int v = Integer.MAX_VALUE;
		
		// Expande os próximos movimentos possíveis do Jogador 1 (Humano)
		for(Successor s: expandSuccessors(board, -1)) {
			this.verifieds++;
			// Busca o menor valor dentre os maiores valores encontrados anteriormente
			v = Math.min(v,  max(s.board, false, depth));
			
			// Guarda qual o menor valor para o tabuleiro atual
			s.util = v;
		}
		
		return v;
	}
	
	public int[][] decisionPruning(int[][] board) {
		successors.clear();
		
		// 	Inicia a busca pela melhor jogada utilizando poda alfa-beta
		// os valores alfa e beta são inicialmemte o menor e o maior inteiro
		int v = maxPruning(board, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
		
		for(Successor s: successors) {
			// 	Busca dentre os sucessores, o primeiro board que tem a utilidade
			// igual ao valor encontrado utilizando minimax
			if(s.util == v) {
				return s.board;
			}
		}
		
		return board;
	}
	
	// Expande os próximos movimentos possíveis do jogador 2 (IA)
	public int maxPruning(int[][] board, int alpha, int beta, boolean first) {
		// Se encontrar um tabuleiro em que o jogo seja considerado como encerrado
		if(terminalTest(board)) {
			return util(board);
		}
		
		int v = Integer.MIN_VALUE;
		
		for(Successor s: expandSuccessors(board, 1)) {
			this.verifieds++;
			
			// Busca o maior valor dentre os menores valores encontrados anteriormente
			v = Math.max(v, minPruning(s.board, alpha, beta));
			
			// Guarda qual o maior valor para o tabuleiro atual
			s.util = v;
			
			// Se for a primeira expansão
			if(first) {
				successors.add(s);
			}
			
			// Se o valor for maior que o maior valor encontrado até o momento, retorna
			if(v >= beta) {
				return v;
			}
			
			alpha = Math.max(alpha, v);
		}
		
		return v;
	}
	
	public int minPruning(int[][] board, int alpha, int beta) {
		// Se encontrar um tabuleiro em que o jogo seja considerado como encerrado
		if(terminalTest(board)) {
			return util(board);
		}
		
		int v = Integer.MAX_VALUE;
		
		// Expande os próximos movimentos possíveis do Jogador 1 (Humano)
		for(Successor s: expandSuccessors(board, -1)) {
			this.verifieds++;
			// Busca o menor valor dentre os maiores valores encontrados anteriormente
			v = Math.min(v, maxPruning(s.board, alpha, beta, false));
			
			// Guarda qual o menor valor para o tabuleiro atual
			s.util = v;
			
			// Se o valor for menor que o menor valor encontrado até o momento, retorna
			if(v <= alpha) {
				return v;
			}
			
			beta = Math.min(beta, v);
		}
		
		return v;
	}
	
	public ArrayList<Successor> expandSuccessors(int[][] board, int v) {
		ArrayList<Successor> successors = new ArrayList<Successor>();
		
		for(int x=0; x < length; x++) {
			for(int y=0; y < length; y++) {
				if(board[x][y] == 0) {
					board[x][y] = v;
					successors.add(new Successor(board));
					board[x][y] = 0;
				}
			}	
		}
		
		this.expanded += successors.size();
		return successors;
	}
	
	public boolean terminalTest(int[][] board) {
		return (won(board, 1) || won(board, -1) || checkSpace(board));
	}
	
	public boolean won(int [][] board, int v) {
		for(int i = 0; i < this.length; i++) {
			if(wonX(board, i, v) || wonY(board, i, v)) {
				return true;
			}
		}
		
		if(wonZ1(board, v) || wonZ2(board, v)) {
			return true;
		}
		
		return false;
	}
	
	public boolean checkSpace(int[][] board) {
		for(int x=0; x<this.length; x++) {
			for(int y=0; y<this.length; y++) {
				if(board[x][y] == 0) {
					return false;
				}
			}	
		}
		
		return true;
	}
	
	public int util(int[][] board) {
		if(won(board, 1)) {
			return 1;
		} else if(won(board, -1)) {
			return -1;
		} else {
			return 0;
		}
	}
	
	private boolean wonX(int[][] board, int x, int v) {
		for(int i = 0; i < this.length; i++) {
			if(board[x][i] != v) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean wonY(int[][] board, int y, int v) {
		for(int i = 0; i < this.length; i++) {
			if(board[i][y] != v) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean wonZ1(int[][] board, int v) {
		for(int i = 0; i < this.length; i++) {
			if(board[i][i] != v) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean wonZ2(int[][] board, int v) {
		for(int i = 0; i < this.length; i++) {
			if(board[(this.length-1)-i][i] != v) {
				return false;
			}
		}
		
		return true;
	}
	
}
