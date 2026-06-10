package com.game.three.in.line;

import java.util.Scanner;

public class Menu {
  private final Scanner scanner;

  public Menu() {
    this.scanner = new Scanner(System.in);
  }

  public void show() {
    boolean running = true;

    while (running) {
      printOptions();
      String option = scanner.nextLine().trim();

      switch (option) {
        case "1":
          startGame();
          break;
        case "2":
          System.out.println("¡Hasta luego!");
          running = false;
          break;
        default:
          System.out.println("Opción no válida, inténtalo de nuevo.\n");
      }
    }
  }

  private void printOptions() {
    System.out.println("=== TRES EN RAYA ===");
    System.out.println("1. Jugar");
    System.out.println("2. Salir");
    System.out.print("Elige una opción: ");
  }

  private void startGame() {
    System.out.print("¿Cómo te llamas? ");
    String name = scanner.nextLine().trim();

    Player human = new Player(name, 'X');
    Player machine = new Player("Máquina", 'O');

    Game game = new Game(human, machine);
    game.start(scanner);
  }
}
