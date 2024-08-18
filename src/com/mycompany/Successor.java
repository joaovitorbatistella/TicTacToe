package com.mycompany;

public class Successor {

	int[][] board;
	int util;
	
	public Successor(int[][] board) {
		int length = board.length;
		this.board = new int[length][length];
		
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < length; j++) {
				this.board[i][j] = board[i][j];
			}
		}
	}
}
