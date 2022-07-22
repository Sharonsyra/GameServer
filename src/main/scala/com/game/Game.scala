package com.game

import com.game.GameEngine.{playersDict, removePlayerFromWaitingList}
import com.game.GameSession.startGameSession
import com.game.setup.GameServerHelpers.{DoubleCardGameType, Fold, Play, SingleCardGameType}

object Game {

  var singleCardList: Seq[Int] = Seq.empty[Int]

  var doubleCardList: Seq[Int] = Seq.empty[Int]

  def main(args: Array[String]): Unit = {

    // This aids in testing. Ideally it should wait for the player's input
    def playersSetup(): PlayerDetails = {
      Player.createPlayerAndAddThemToWaitingList(playerId = 1, gameType = SingleCardGameType, playersPick = Play)
      Player.createPlayerAndAddThemToWaitingList(playerId = 2, gameType = SingleCardGameType, playersPick = Play)
      Player.createPlayerAndAddThemToWaitingList(playerId = 3, gameType = DoubleCardGameType, playersPick = Play)
      Player.createPlayerAndAddThemToWaitingList(playerId = 4, gameType = DoubleCardGameType, playersPick = Play)
      Player.createPlayerAndAddThemToWaitingList(playerId = 5, gameType = SingleCardGameType, playersPick = Play)
      Player.createPlayerAndAddThemToWaitingList(playerId = 6, gameType = SingleCardGameType, playersPick = Fold)
      Player.createPlayerAndAddThemToWaitingList(playerId = 7, gameType = DoubleCardGameType, playersPick = Fold)
      Player.createPlayerAndAddThemToWaitingList(playerId = 8, gameType = DoubleCardGameType, playersPick = Fold)
      Player.createPlayerAndAddThemToWaitingList(playerId = 9, gameType = SingleCardGameType, playersPick = Fold)
      Player.createPlayerAndAddThemToWaitingList(playerId = 10, gameType = DoubleCardGameType, playersPick = Fold)
      Player.createPlayerAndAddThemToWaitingList(playerId = 11, gameType = SingleCardGameType, playersPick = Play)
      Player.createPlayerAndAddThemToWaitingList(playerId = 12, gameType = SingleCardGameType, playersPick = Play)
      Player.createPlayerAndAddThemToWaitingList(playerId = 13, gameType = DoubleCardGameType, playersPick = Play)
      Player.createPlayerAndAddThemToWaitingList(playerId = 14, gameType = DoubleCardGameType, playersPick = Play)
      Player.createPlayerAndAddThemToWaitingList(playerId = 15, gameType = SingleCardGameType, playersPick = Play)
      Player.createPlayerAndAddThemToWaitingList(playerId = 16, gameType = SingleCardGameType, playersPick = Fold)
      Player.createPlayerAndAddThemToWaitingList(playerId = 17, gameType = DoubleCardGameType, playersPick = Fold)
      Player.createPlayerAndAddThemToWaitingList(playerId = 18, gameType = DoubleCardGameType, playersPick = Fold)
      Player.createPlayerAndAddThemToWaitingList(playerId = 19, gameType = SingleCardGameType, playersPick = Fold)
      Player.createPlayerAndAddThemToWaitingList(playerId = 20, gameType = DoubleCardGameType, playersPick = Fold)
    }

    playersSetup()

    // Keep querying the waiting list to start game sessions
    while (true) {
      println(s"SingleCardGame waiting list: $singleCardList")
      println(s"DoubleCardGame waiting list: $doubleCardList")
      if (doubleCardList.length >= 2) {
        val players = doubleCardList.take(2)
        startGameSession(
          player1 = playersDict(players.head),
          player2 = playersDict(players.last),
          gameType = DoubleCardGameType
        )
        removePlayerFromWaitingList(playerId = players.head, gameType = DoubleCardGameType)
        removePlayerFromWaitingList(playerId = players.last, gameType = DoubleCardGameType)
      }

      if (singleCardList.length >= 2) {
        val players = singleCardList.take(2)
        startGameSession(
          player1 = playersDict(players.head),
          player2 = playersDict(players.last),
          gameType = SingleCardGameType
        )
        removePlayerFromWaitingList(playerId = players.head, gameType = SingleCardGameType)
        removePlayerFromWaitingList(playerId = players.last, gameType = SingleCardGameType)
      }
      Thread.sleep(5000)
    }
  }
}
