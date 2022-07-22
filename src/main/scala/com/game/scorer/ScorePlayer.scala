package com.game.scorer

import com.game.PlayerDetails
import com.game.setup.GameServerHelpers.GameOutcome

trait ScorePlayer {

  /**
   * Compute results when both players fold
   *
   * @param player1 Player 1
   * @param player2 Player 2
   * @return GameOutcome
   */
  def twoFolds(player1: PlayerDetails, player2: PlayerDetails): GameOutcome

  /**
   * Compute results when one player plays and one player fold
   *
   * @param playPlayer Play Player
   * @param foldPlayer Fold Player
   * @return GameOutcome
   */
  def onePlayOneFold(playPlayer: PlayerDetails, foldPlayer: PlayerDetails): GameOutcome

  /**
   * Compute results when both players play
   *
   * @param player1 Player 1
   * @param player2 Player 2
   * @return GameOutcome
   */
  def twoPlays(player1: PlayerDetails, player2: PlayerDetails): GameOutcome
}
