package com.game

import com.game.GameEngine.{addPlayerToWaitingList, playersDict}
import com.game.setup.GameServerHelpers._

import java.util.UUID

object Player {
  def setPlayersPick(player: PlayerDetails, playersPick: PlayersPick): Unit = {
    var i = 0
    while (i < 5) {
      if (player.inSession) {
        player.playersPick = playersPick
        i = 5
      }
      i += 1
    }
  }

  // This aids in testing. Ideally it should wait for the player's input
  def playersSetup(): PlayerDetails = {
    Player.createPlayerAndAddThemToWaitingList(playerUuid = UUID.randomUUID().toString, playerName = "nyambu",gameType = SingleCardGameType, playersPick = Play)
    Player.createPlayerAndAddThemToWaitingList(playerUuid = UUID.randomUUID().toString, playerName = "rhoda", gameType = SingleCardGameType, playersPick = Play)
    Player.createPlayerAndAddThemToWaitingList(playerUuid = UUID.randomUUID().toString, playerName = "sofia", gameType = DoubleCardGameType, playersPick = Play)
    Player.createPlayerAndAddThemToWaitingList(playerUuid = UUID.randomUUID().toString, playerName = "nyawīra", gameType = DoubleCardGameType, playersPick = Play)
    Player.createPlayerAndAddThemToWaitingList(playerUuid = UUID.randomUUID().toString, playerName = "jaijī", gameType = SingleCardGameType, playersPick = Play)
    Player.createPlayerAndAddThemToWaitingList(playerUuid = UUID.randomUUID().toString, playerName = "waithīra", gameType = SingleCardGameType, playersPick = Fold)
    Player.createPlayerAndAddThemToWaitingList(playerUuid = UUID.randomUUID().toString, playerName = "nyondo", gameType = DoubleCardGameType, playersPick = Fold)
    Player.createPlayerAndAddThemToWaitingList(playerUuid = UUID.randomUUID().toString, playerName = "matei", gameType = DoubleCardGameType, playersPick = Fold)
    Player.createPlayerAndAddThemToWaitingList(playerUuid = UUID.randomUUID().toString, playerName = "mwende", gameType = SingleCardGameType, playersPick = Fold)
    Player.createPlayerAndAddThemToWaitingList(playerUuid = UUID.randomUUID().toString, playerName = "mwendwa", gameType = DoubleCardGameType, playersPick = Fold)
    Player.createPlayerAndAddThemToWaitingList(playerUuid = UUID.randomUUID().toString, playerName = "karīmi", gameType = SingleCardGameType, playersPick = Play)
    Player.createPlayerAndAddThemToWaitingList(playerUuid = UUID.randomUUID().toString, playerName = "kendi", gameType = SingleCardGameType, playersPick = Play)
    Player.createPlayerAndAddThemToWaitingList(playerUuid = UUID.randomUUID().toString, playerName = "mūkami", gameType = DoubleCardGameType, playersPick = Play)
    Player.createPlayerAndAddThemToWaitingList(playerUuid = UUID.randomUUID().toString, playerName = "karendi", gameType = DoubleCardGameType, playersPick = Play)
    Player.createPlayerAndAddThemToWaitingList(playerUuid = UUID.randomUUID().toString, playerName = "nkobo", gameType = SingleCardGameType, playersPick = Play)
    Player.createPlayerAndAddThemToWaitingList(playerUuid = UUID.randomUUID().toString, playerName = "baariū", gameType = SingleCardGameType, playersPick = Fold)
    Player.createPlayerAndAddThemToWaitingList(playerUuid = UUID.randomUUID().toString, playerName = "matī", gameType = DoubleCardGameType, playersPick = Fold)
    Player.createPlayerAndAddThemToWaitingList(playerUuid = UUID.randomUUID().toString, playerName = "ncororo", gameType = DoubleCardGameType, playersPick = Fold)
    Player.createPlayerAndAddThemToWaitingList(playerUuid = UUID.randomUUID().toString, playerName = "mbiti", gameType = SingleCardGameType, playersPick = Fold)
    Player.createPlayerAndAddThemToWaitingList(playerUuid = UUID.randomUUID().toString, playerName = "mūtuma", gameType = DoubleCardGameType, playersPick = Fold)
    Player.createPlayerAndAddThemToWaitingList(playerUuid = UUID.randomUUID().toString, playerName = "keino", gameType = DoubleCardGameType, playersPick = Play)
    Player.createPlayerAndAddThemToWaitingList(playerUuid = UUID.randomUUID().toString, playerName = "chebet", gameType = DoubleCardGameType, playersPick = Play)
    Player.createPlayerAndAddThemToWaitingList(playerUuid = UUID.randomUUID().toString, playerName = "linda", gameType = SingleCardGameType, playersPick = Play)
    Player.createPlayerAndAddThemToWaitingList(playerUuid = UUID.randomUUID().toString, playerName = "zawadi", gameType = SingleCardGameType, playersPick = Play)
  }

  def createPlayerAndAddThemToWaitingList(
    playerUuid: String,
    playerName: String,
    gameType: GameType,
    playersPick: PlayersPick
  ): PlayerDetails = {
    val playerDetails: PlayerDetails = PlayerDetails(
      playerUuid = playerUuid,
      playerName = playerName,
      playersPick = playersPick
    )
    playersDict += (
      playerUuid -> playerDetails
      )

    addPlayerToWaitingList(playerUuid = playerUuid, gameType = gameType)
    playerDetails
  }
}
