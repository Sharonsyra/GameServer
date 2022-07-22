package com.game.scorer

import com.game.PlayerDetails
import com.game.setup.CardDeck.Card
import com.game.setup.GameServerHelpers._

object SessionResult {

  /**
   * Update player details for two losses
   *
   * @param player1 Player 1
   * @param player2 Player 2
   * @param token   token value to deduct
   * @return Game outcome
   */
  def twoLossesResult(player1: PlayerDetails, player2: PlayerDetails, token: Int): GameOutcome = {
    player1.balance = player1.balance - token
    player2.balance = player2.balance - token
    player1.playerResult = LoseTokens
    player2.playerResult = LoseTokens
    resetWinOrLosePlayerToDefault(player1)
    resetWinOrLosePlayerToDefault(player2)
    TwoLosses
  }

  /**
   * Update player details for one win one loss
   *
   * @param winPlayer  winning player
   * @param losePlayer losing player
   * @param token      token value to add and deduct
   * @return Game outcome
   */
  def oneWinOneLossResult(winPlayer: PlayerDetails, losePlayer: PlayerDetails, token: Int): GameOutcome = {
    winPlayer.balance = winPlayer.balance + token
    losePlayer.balance = losePlayer.balance - token
    winPlayer.playerResult = GainTokens
    losePlayer.playerResult = LoseTokens
    resetWinOrLosePlayerToDefault(winPlayer)
    resetWinOrLosePlayerToDefault(losePlayer)
    OneWinOneLoss
  }

  /**
   * Update player details for a draw outcome
   *
   * @param player1 Player 1
   * @param player2 Player 2
   * @return Game outcome
   */
  def drawResult(player1: PlayerDetails, player2: PlayerDetails): GameOutcome = {
    player1.dealtCards = Seq.empty[Card]
    player2.dealtCards = Seq.empty[Card]
    player1.playerResult = Draw
    player2.playerResult = Draw
    SessionDraw
  }

  /**
   * Reset win on lose player tp default settings
   *
   * @param player win or lose player
   */
  def resetWinOrLosePlayerToDefault(player: PlayerDetails): Unit = {
    player.inSession = false
    player.pairedPlayer = ""
    player.playersPick = NoPick
    player.gameType = NoGameType
    player.dealtCards = Seq.empty[Card]
    player.playerResult = NoResult
  }
}
