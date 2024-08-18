package com.mycompany;

public class Board {

	static char[] replaces = {'o', ' ', 'x'};
	
	public int[][] board;
	
	int length;
	
	String divider;
	
	public Board(int length) {
		this.length = length;
		board = new int[length][length];
		
		divider = generateDivider();
	}
	
	public String generateDivider() {
		String d = new String("\r\n");
		
		for(int i=0; i < (this.length - 1); i++) {
			d += "---+";
		}
		
		d += "---+";
		
		return d;
	}
	
	public boolean makeMove (int x, int y) {
		if(board[x][y] == 0) {
			board[x][y] = -1;
		} else {
			System.out.println("\nJogada inválida, jogue em uma posição ainda não ocupada...");
			return false;
		}
		return true;
	}
	
	public void print() {
		for(int i = 0; i < this.length; i++) {
			for(int j = 0; j < this.length; j++) {
				System.out.printf(" %c %c", replaces[board[i][j] +1], j == (this.length-1) ? ' ' : '|');
			}
			
			if(i != (this.length-1)) {
				System.out.println(this.divider);
			}
		}
		
		System.out.println("\r\n");
	}
	
	
}
