package com.game

import com.game.GameEngine.dealCards
import com.game.scorer.{DoubleCardGameScorePlayer, SingleCardGameScorePlayer}
import com.game.setup.CardDeck.Card
import com.game.setup.GameServerHelpers._

import scala.annotation.tailrec
import scala.util.{Failure, Success, Try}

object GameSession {

  /**
   * Set up a session
   *
   * @param player1  Player 1
   * @param player2  Player 2
   * @param gameType Game Type
   */
  def sessionSetup(player1: PlayerDetails, player2: PlayerDetails, gameType: GameType): Unit = {
    player1.pairedPlayer = player2.playerUuid
    player2.pairedPlayer = player1.playerUuid
    player1.inSession = true
    player2.inSession = true
  }

  /**
   * Deal player cards and play
   *
   * @param player1  Player 1
   * @param player2  Player 2
   * @param gameType Game Type
   * @return GameOutcome
   */
  def dealAndPlay(player1: PlayerDetails, player2: PlayerDetails, gameType: GameType): GameOutcome = {
    val dealSingleCardGameCards = dealCards(gameType = gameType)
    player1.dealtCards = player1.dealtCards.appendedAll(dealSingleCardGameCards._1)
    player2.dealtCards = player2.dealtCards.appendedAll(dealSingleCardGameCards._2)

    scorePlayers(gameType = gameType, player1 = player1, player2 = player2)
  }

  /**
   * Start a game session
   *
   * @param player1  Player 1
   * @param player2  Player 2
   * @param gameType Game Type
   * @return GameOutcome
   */
  def startGameSession(player1: PlayerDetails, player2: PlayerDetails, gameType: GameType): GameOutcome = {
    println(
      s"""
         |Starting a $gameType game session between:\n
         |${player1.playerName} with balance - ${player1.balance} and ${player2.playerName} with balance ${player2.balance}
         |\n
         |""".stripMargin
    )
    sessionSetup(player1 = player1, player2 = player2, gameType = gameType)

    val gameOutcome = dealAndPlay(player1: PlayerDetails, player2: PlayerDetails, gameType: GameType)

    println(
      s"""
         |The Game Session Outcome is $gameOutcome\n
         |${player1.playerName}'s balance is ${player1.balance} and ${player2.playerName}'s balance is ${player2.balance}
         |\n
         |""".stripMargin
    )
    gameOutcome match {
      case SessionDraw =>
        dealAndPlay(player1: PlayerDetails, player2: PlayerDetails, gameType: GameType)
      case _ => gameOutcome
    }
  }

  /**
   * Compare two cards
   *
   * @param card1 Card 1
   * @param card2 Card 2
   * @return Int
   */
  def rateCards(card1: Card, card2: Card): Int =
    card1.rank.value.compare(card2.rank.value)

  /**
   * Compute game session results
   *
   * @param gameType Game Type
   * @param player1  Player 1
   * @param player2  Player 2
   * @return GameOutCome
   */
  def scorePlayers(gameType: GameType, player1: PlayerDetails, player2: PlayerDetails): GameOutcome = {
    (player1.playersPick, player2.playersPick) match {
      case (Fold, Fold) =>
        gameType match {
          case SingleCardGameType =>
            SingleCardGameScorePlayer.twoFolds(player1 = player1, player2 = player2)
          case DoubleCardGameType =>
            DoubleCardGameScorePlayer.twoFolds(player1 = player1, player2 = player2)
          case NoGameType => throw new Exception("invalid pick")
        }
      case (Play, Fold) =>
        gameType match {
          case SingleCardGameType =>
            SingleCardGameScorePlayer.onePlayOneFold(playPlayer = player1, foldPlayer = player2)
          case DoubleCardGameType =>
            DoubleCardGameScorePlayer.onePlayOneFold(playPlayer = player1, foldPlayer = player2)
          case NoGameType => throw new Exception("invalid pick")
        }
      case (Fold, Play) =>
        gameType match {
          case SingleCardGameType =>
            SingleCardGameScorePlayer.onePlayOneFold(playPlayer = player2, foldPlayer = player1)
          case DoubleCardGameType =>
            DoubleCardGameScorePlayer.onePlayOneFold(playPlayer = player2, foldPlayer = player1)
          case NoGameType => throw new Exception("invalid pick")
        }
      case (Play, Play) =>
        gameType match {
          case SingleCardGameType =>
            SingleCardGameScorePlayer.twoPlays(player1 = player1, player2 = player2)
          case DoubleCardGameType =>
            DoubleCardGameScorePlayer.twoPlays(player1 = player1, player2 = player2)
          case NoGameType => throw new Exception("invalid pick")
        }
      case _ => throw new Exception("invalid play")
    }
  }
}
