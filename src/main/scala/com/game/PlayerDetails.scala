package com.game

import com.game.setup.CardDeck.Card
import com.game.setup.GameServerHelpers._

final case class PlayerDetails(
  playerId: Int,
  var balance: Double = 1000,
  var gameType: GameType = NoGameType,
  var inSession: Boolean = false,
  var pairedPlayer: Int = 0,
  var playersPick: PlayersPick = NoPick,
  var dealtCards: Seq[Card] = Seq.empty[Card],
  var playerResult: PlayerResult = NoResult
)
