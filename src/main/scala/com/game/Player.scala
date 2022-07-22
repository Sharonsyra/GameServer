package com.game

import com.game.GameEngine.{addPlayerToWaitingList, playersDict}
import com.game.setup.GameServerHelpers.{Draw, Fold, GameType, NoResult, Play, PlayersPick}

import scala.io.StdIn.readLine

object Player {
  def createPlayerAndAddThemToWaitingList(playerId: Int, gameType: GameType, playersPick: PlayersPick): PlayerDetails = {
    val playerDetails: PlayerDetails = PlayerDetails(
      playerId = playerId,
      playersPick = playersPick
    )
    playersDict += (
      playerId -> playerDetails
      )

    addPlayerToWaitingList(playerId = playerId, gameType = gameType)
    playerDetails
  }

  def setPlayersPick(player: PlayerDetails, playersPick: PlayersPick): Unit = {
    var i = 0
    while(i < 5) {
      if (player.inSession) {
        player.playersPick = playersPick
        i = 5
      }
      i += 1
    }
  }

  /**
   * Prompt user to play or fold
   *
   * @param player player details
   */
  // prompt user to pick or play example. If the client is a CLI app
  def playOrFold(player: PlayerDetails): Unit = {
    var played = true
    while (played) {
      if (player.inSession) {
        println(
          """Pick Play or Fold:
            |Pick 1 for Play
            |Pick 2 for Fold""".stripMargin
        )
        player.playersPick = readLine() match {
          case "1" => Play
          case "2" => Fold
          case _ => throw new Exception("invalid choice")
        }
        played = false
      }
    }
  }

  /**
   * Play again if user draws
   *
   * @param player player details
   */
  // prompt user to pick or play if game resulted in draw example. If the client is a CLI app
  def playOrDraw(player: PlayerDetails): Unit = {
    var played = true
    while (played) {
      if (player.playerResult != NoResult && player.playerResult == Draw) {
        println(
          """Pick Play or Fold:
            |Pick 1 for Play
            |Pick 2 for Fold""".stripMargin
        )
        player.playersPick = readLine() match {
          case "1" => Play
          case "2" => Fold
          case _ => throw new Exception("invalid choice")
        }
        played = false
      }
    }
  }
}
