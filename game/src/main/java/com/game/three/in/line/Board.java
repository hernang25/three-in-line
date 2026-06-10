package com.game.three.in.line;

public class Board {
  protected char[][] board = new char[3][3];

  public Board() {
    this.init();
  }

  private void init() {
    for (int row = 0; row < 3; row++) {
      for (int column = 0; column < 3; column++) {
        board[row][column] = ' ';
      }
    }
  }

  public void setTokenToCell(char token, int row, int column) {
    if (!isFreeCell(row, column)) {
      return;
    }

    board[row][column] = token;
  }

  public boolean isFreeCell(int row, int column) {
    return board[row][column] == ' ';
  }

  public char getCell(int row, int column) {
    return board[row][column];
  }

  public boolean isFull() {
    for (int row = 0; row < 3; row++) {
      for (int column = 0; column < 3; column++) {
        if (isFreeCell(row, column)) {
          return false;
        }
      }
    }

    return true;
  }
}
