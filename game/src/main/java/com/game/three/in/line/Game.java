package com.game.three.in.line;

import java.util.Random;
import java.util.Scanner;

public class Game {
  private final Board board;
  private final GameTurn turn;
  private final Player human;
  private final Player machine;
  private final Display display;
  private final Random random;

  public Game(Player human, Player machine) {
    this.board = new Board();
    this.human = human;
    this.machine = machine;
    // siempre empieza el humano
    this.turn = new GameTurn(true);
    this.display = new Display();
    this.random = new Random();
  }

  // de quién es el turno ahora mismo
  public Player currentPlayer() {
    return turn.isHumanTurn() ? human : machine;
  }

  // intenta colocar la ficha del jugador actual; devuelve true si la jugada fue
  // válida
  public boolean play(int row, int column) {
    if (!board.isFreeCell(row, column)) {
      return false;
    }

    board.setTokenToCell(currentPlayer().getToken(), row, column);
    turn.toggle();
    return true;
  }

  public void start(Scanner scanner) {
    boolean finished = false;

    // El juego siempre continua mientras no haya acabado
    while (!finished) {
      Player currentPlayer = currentPlayer();
      display.render(board, "Turno de: " + currentPlayer.getName());

      if (turn.isHumanTurn()) {
        humanMove(scanner);
      } else {
        machineMove();
      }

      if (checkWinner()) {
        display.render(board, "¡Ganó " + currentPlayer.getName() + "!");
        finished = true;
      } else if (board.isFull()) {
        display.render(board, "¡Empate!");
        finished = true;
      }
    }

    System.out.print("Pulsa ENTER para volver al menú...");
    scanner.nextLine();
  }

  private void humanMove(Scanner scanner) {
    while (true) {
      try {
        System.out.print("Fila (0-2): ");
        int row = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Columna (0-2): ");
        int column = Integer.parseInt(scanner.nextLine().trim());

        if (row < 0 || row > 2 || column < 0 || column > 2) {
          System.out.println("Usa valores entre 0 y 2.");
          continue;
        }

        if (play(row, column)) {
          return;
        }
        System.out.println("Esa celda está ocupada, prueba otra.");
      } catch (NumberFormatException e) {
        System.out.println("Introduce un número válido.");
      }
    }
  }

  private void machineMove() {
    int row;
    int column;
    do {
      row = random.nextInt(3);
      column = random.nextInt(3);
    } while (!play(row, column));
  }

  public boolean checkWinner() {
    for (int i = 0; i < 3; i++) {
      // filas
      if (sameToken(board.getCell(i, 0), board.getCell(i, 1), board.getCell(i, 2))) {
        return true;
      }
      // columnas
      if (sameToken(board.getCell(0, i), board.getCell(1, i), board.getCell(2, i))) {
        return true;
      }
    }

    // diagonales
    if (sameToken(board.getCell(0, 0), board.getCell(1, 1), board.getCell(2, 2))) {
      return true;
    }

    return sameToken(board.getCell(0, 2), board.getCell(1, 1), board.getCell(2, 0));
  }

  private boolean sameToken(char a, char b, char c) {
    return a != ' ' && a == b && b == c;
  }
}
