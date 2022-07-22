package com.game

import com.game.setup.CardDeck.Card
import com.game.setup.GameServerHelpers._

final case class PlayerDetails(
  playerUuid: String,
  playerName: String,
  var balance: Double = 1000,
  var gameType: GameType = NoGameType,
  var inSession: Boolean = false,
  var pairedPlayer: String = "",
  var playersPick: PlayersPick = NoPick,
  var dealtCards: Seq[Card] = Seq.empty[Card],
  var playerResult: PlayerResult = NoResult
)
