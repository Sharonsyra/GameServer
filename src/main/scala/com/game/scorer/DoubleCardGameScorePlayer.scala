package com.game.scorer

import com.game.GameSession.rateCards
import com.game.PlayerDetails
import com.game.scorer.SessionResult.{drawResult, oneWinOneLossResult, twoLossesResult}
import com.game.setup.GameServerHelpers.GameOutcome

object DoubleCardGameScorePlayer extends ScorePlayer {

  /**
   * Compute results when both players fold
   *
   * @param player1 Player 1
   * @param player2 Player 2
   * @return GameOutcome
   */
  def twoFolds(player1: PlayerDetails, player2: PlayerDetails): GameOutcome = {
    twoLossesResult(player1 = player1, player2 = player2, token = 2)
  }

  /**
   * Compute results when one player plays and one player fold
   *
   * @param playPlayer Play Player
   * @param foldPlayer Fold Player
   * @return GameOutcome
   */
  def onePlayOneFold(playPlayer: PlayerDetails, foldPlayer: PlayerDetails): GameOutcome = {
    oneWinOneLossResult(winPlayer = playPlayer, losePlayer = foldPlayer, token = 5)
  }

  /**
   * Compute results when both players play
   *
   * @param player1 Player 1
   * @param player2 Player 2
   * @return GameOutcome
   */
  def twoPlays(player1: PlayerDetails, player2: PlayerDetails): GameOutcome = {
    rateCards(player1.dealtCards.max, player2.dealtCards.max) match {
      case 1 =>
        oneWinOneLossResult(winPlayer = player1, losePlayer = player2, token = 20)
      case -1 =>
        oneWinOneLossResult(winPlayer = player2, losePlayer = player1, token = 20)
      case 0 =>
        rateCards(player1.dealtCards.min, player2.dealtCards.min) match {
          case 1 =>
            oneWinOneLossResult(winPlayer = player1, losePlayer = player2, token = 20)
          case -1 =>
            oneWinOneLossResult(winPlayer = player2, losePlayer = player1, token = 20)
          case 0 =>
            drawResult(player1 = player1, player2)
        }
    }
  }
}
