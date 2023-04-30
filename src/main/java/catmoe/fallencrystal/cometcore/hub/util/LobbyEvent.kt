package catmoe.fallencrystal.cometcore.hub.util

import catmoe.fallencrystal.cometcore.hub.chat.JoinQuitBroadcast
import catmoe.fallencrystal.cometcore.hub.chat.LobbyChat
import net.md_5.bungee.api.event.ChatEvent
import net.md_5.bungee.api.event.ServerConnectedEvent
import net.md_5.bungee.api.event.ServerDisconnectEvent
import net.md_5.bungee.api.plugin.Listener
import net.md_5.bungee.api.plugin.Plugin
import net.md_5.bungee.event.EventHandler

class LobbyEvent(plugin: Plugin) : Listener {

    val jqbroadcast = JoinQuitBroadcast()
    val chat = LobbyChat(plugin)

    @EventHandler(priority = -127)
    fun playerJoin(event: ServerConnectedEvent) {
        val player = event.player
        val target = event.server.info
        if (target.name.contains("Lobby")) { LobbyPlayers.lobbyPlayersAdd(player) }
        jqbroadcast.join(event)
    }

    @EventHandler(priority = -127)
    fun playerQuit(event: ServerDisconnectEvent) {
        val player = event.player
        val target = event.target
        val playerList = LobbyPlayers.lobbyPlayersGet()
        if (target.name.contains("Lobby") and playerList.contains(player)) {
            LobbyPlayers.lobbyPlayersDel(player)
        }
        jqbroadcast.quit(event)
    }

    @EventHandler(priority = -127)
    fun lobbyChat(event: ChatEvent) { chat.lobbyChat(event) }

}