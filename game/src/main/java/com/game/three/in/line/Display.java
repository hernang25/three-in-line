package com.game.three.in.line;

public class Display {

  public Display() {
  }

  public void clear() {
    // códigos ANSI: mueve el cursor arriba y borra toda la pantalla
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public void render(Board board, String status) {
    clear();
    System.out.println("=== TRES EN RAYA ===");
    System.out.println(status);
    System.out.println();
    for (int row = 0; row < 3; row++) {
      System.out.println(
          " " + board.getCell(row, 0) + " | " + board.getCell(row, 1) + " | " + board.getCell(row, 2));
      if (row < 2) {
        System.out.println("---+---+---");
      }
    }
    System.out.println();
  }
}
