package catmoe.fallencrystal.cometcore.hub.util

import catmoe.fallencrystal.cometcore.hub.chat.LobbyChat
import net.md_5.bungee.api.event.ChatEvent
import net.md_5.bungee.api.event.ServerConnectedEvent
import net.md_5.bungee.api.event.ServerDisconnectEvent
import net.md_5.bungee.api.plugin.Listener
import net.md_5.bungee.api.plugin.Plugin
import net.md_5.bungee.event.EventHandler

class LobbyEvent(private val plugin: Plugin) : Listener {

    @EventHandler(priority = -127)
    fun playerAdd(event: ServerConnectedEvent) {
        val player = event.player
        val target = event.server.info
        if (target.name.contains("Lobby")) { LobbyPlayers.lobbyPlayersAdd(player) }
    }

    @EventHandler(priority = -127)
    fun playerDel(event: ServerDisconnectEvent) {
        val player = event.player
        val target = event.target
        val playerList = LobbyPlayers.lobbyPlayersGet()
        if (target.name.contains("Lobby") and playerList.contains(player)) {
            LobbyPlayers.lobbyPlayersDel(player)
        }
    }

    @EventHandler(priority = -127)
    fun lobbyChat(event: ChatEvent) {
        val chat = LobbyChat(plugin)
        chat.lobbyChat(event)
    }

}