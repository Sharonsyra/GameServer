package com.game

import com.game.Game.{doubleCardList, singleCardList}
import com.game.setup.CardDeck.{Card, deck}
import com.game.setup.GameServerHelpers
import com.game.setup.GameServerHelpers.{DoubleCardGameType, GameType, NoGameType, SingleCardGameType}

import scala.collection.mutable
import scala.util.Random

object GameEngine {

  val playersDict: mutable.Map[Int, PlayerDetails] = mutable.Map.empty[Int, PlayerDetails]

  /**
   * Add player to game waiting list
   *
   * @param playerUuid player uuid
   * @param gameType   player game type
   */
  def addPlayerToWaitingList(playerId: Int, gameType: GameType): Unit = {
    playersDict(playerId).gameType = gameType
    gameType match {
      case GameServerHelpers.SingleCardGameType =>
        singleCardList = singleCardList.appended(playerId)
      case GameServerHelpers.DoubleCardGameType =>
        doubleCardList = doubleCardList.appended(playerId)
      case GameServerHelpers.NoGameType =>
        throw new Exception("invalid game type picked")
    }
  }

  /**
   * Remove player from game waiting list
   *
   * @param playerUuid player uuid
   * @param gameType   player game type
   */
  def removePlayerFromWaitingList(playerId: Int, gameType: GameType): Unit = {
    playersDict(playerId).gameType = NoGameType
    gameType match {
      case GameServerHelpers.SingleCardGameType =>
        singleCardList = singleCardList.filterNot(_.equals(playerId))
      case GameServerHelpers.DoubleCardGameType =>
        doubleCardList = doubleCardList.filterNot(_.equals(playerId))
      case GameServerHelpers.NoGameType =>
        throw new Exception("invalid game type picked")
    }
  }
  /**
   * Deal cards for specified game type
   *
   * @param gameType Game Type to deal cards for
   * @return Cards dealt Seq[Card]
   */
  def dealCards(gameType: GameType): (Seq[Card], Seq[Card]) =
    gameType match {
      case SingleCardGameType => Random.shuffle(deck()).take(2).splitAt(1)
      case DoubleCardGameType => Random.shuffle(deck()).take(4).splitAt(2)
      case NoGameType => (Seq.empty[Card], Seq.empty[Card])
    }
}
