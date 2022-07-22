package com.game

import com.game.GameSession.startGameSession
import com.game.setup.CardDeck.{Card, deck}
import com.game.setup.GameServerHelpers
import com.game.setup.GameServerHelpers.{DoubleCardGameType, GameType, NoGameType, SingleCardGameType}

import scala.collection.mutable
import scala.util.Random

object GameEngine {

  val playersDict: mutable.Map[String, PlayerDetails] = mutable.Map.empty[String, PlayerDetails]

  var singleCardList: Seq[String] = Seq.empty[String]

  var doubleCardList: Seq[String] = Seq.empty[String]

  def waitingListTracker(): Unit = {
    // Keep querying the waiting list to start game sessions
    while (true) {
      println(s"SingleCardGame waiting list length is ${singleCardList.length}")
      println(s"DoubleCardGame waiting list length is ${doubleCardList.length}")
      if (doubleCardList.length >= 2) {
        val players = doubleCardList.take(2)
        startGameSession(
          player1 = playersDict(players.head),
          player2 = playersDict(players.last),
          gameType = DoubleCardGameType
        )
        removePlayerFromWaitingList(playerUuid = players.head, gameType = DoubleCardGameType)
        removePlayerFromWaitingList(playerUuid = players.last, gameType = DoubleCardGameType)
      }

      if (singleCardList.length >= 2) {
        val players = singleCardList.take(2)
        startGameSession(
          player1 = playersDict(players.head),
          player2 = playersDict(players.last),
          gameType = SingleCardGameType
        )
        removePlayerFromWaitingList(playerUuid = players.head, gameType = SingleCardGameType)
        removePlayerFromWaitingList(playerUuid = players.last, gameType = SingleCardGameType)
      }
      Thread.sleep(5000)
    }
  }

  /**
   * Remove player from game waiting list
   *
   * @param playerUuid player uuid
   * @param gameType   player game type
   */
  def removePlayerFromWaitingList(playerUuid: String, gameType: GameType): Unit = {
    playersDict(playerUuid).gameType = NoGameType
    gameType match {
      case GameServerHelpers.SingleCardGameType =>
        singleCardList = singleCardList.filterNot(_.equals(playerUuid))
      case GameServerHelpers.DoubleCardGameType =>
        doubleCardList = doubleCardList.filterNot(_.equals(playerUuid))
      case GameServerHelpers.NoGameType =>
        throw new Exception("invalid game type picked")
    }
  }

  /**
   * Add player to game waiting list
   *
   * @param playerUuid player uuid
   * @param gameType   player game type
   */
  def addPlayerToWaitingList(playerUuid: String, gameType: GameType): Unit = {
    playersDict(playerUuid).gameType = gameType
    gameType match {
      case GameServerHelpers.SingleCardGameType =>
        singleCardList = singleCardList.appended(playerUuid)
      case GameServerHelpers.DoubleCardGameType =>
        doubleCardList = doubleCardList.appended(playerUuid)
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
