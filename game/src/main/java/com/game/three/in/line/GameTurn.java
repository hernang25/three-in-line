package com.game.three.in.line;

public class GameTurn {
  private boolean humanTurn;

  public GameTurn(boolean humanTurn) {
    this.humanTurn = humanTurn;
  }

  public boolean isHumanTurn() {
    return humanTurn;
  }

  public boolean isMachineTurn() {
    return !humanTurn;
  }

  public void setTurnAsHumanTurn() {
    this.humanTurn = true;
  }

  public void setTurnAsMachineTurn() {
    this.humanTurn = false;
  }

  public void toggle() {
    this.humanTurn = !this.humanTurn;
  }
}
