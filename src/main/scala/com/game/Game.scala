package com.game

import com.game.GameEngine.waitingListTracker
import com.game.Player.playersSetup

object Game {

  def main(args: Array[String]): Unit = {
    // Set mock players
    playersSetup()
    //track the waiting lists
    waitingListTracker()
  }
}
