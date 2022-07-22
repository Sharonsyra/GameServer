package com.game.setup

object GameServerHelpers {

  sealed trait GameType

  object SingleCardGameType extends GameType {
    override def toString: String = "SingleCardGameType"
  }

  object DoubleCardGameType extends GameType {
    override def toString: String = "DoubleCardGameType"
  }

  object NoGameType extends GameType

  sealed trait PlayersPick

  object Play extends PlayersPick

  object Fold extends PlayersPick

  object NoPick extends PlayersPick

  sealed trait GameOutcome

  object TwoLosses extends GameOutcome {
    override def toString: String = "TwoLosses"
  }

  object OneWinOneLoss extends GameOutcome {
    override def toString: String = "OneWinOneLoss"
  }

  object SessionDraw extends GameOutcome {
    override def toString: String = "SessionDraw"
  }

  sealed trait PlayerResult

  object GainTokens extends PlayerResult

  object LoseTokens extends PlayerResult

  object Draw extends PlayerResult

  object NoResult extends PlayerResult
}
