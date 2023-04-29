package catmoe.fallencrystal.cometcore.hub.chat

import catmoe.fallencrystal.cometcore.hub.util.LobbyPlayers
import catmoe.fallencrystal.cometcore.utils.display.DisplayCache
import catmoe.fallencrystal.cometcore.utils.misc.MessageUtil
import net.md_5.bungee.api.ProxyServer
import net.md_5.bungee.api.connection.ProxiedPlayer
import net.md_5.bungee.api.event.ChatEvent
import net.md_5.bungee.api.plugin.Plugin

class LobbyChat(private val plugin: Plugin) {

    val proxy = ProxyServer.getInstance()

    fun lobbyChat(event: ChatEvent) {
        val player = proxy.getPlayer(event.sender.toString())
        if (player.server.info.name.contains("Lobby")) {
            event.isCancelled = true
            proxy.scheduler.runAsync(plugin) {
                val displayName = DisplayCache.getDisplayName(player.uniqueId)
                val format = "{DISPLAY-NAME} &f: &f{MESSAGE}"
                val message = event.message
                val info = format
                    .replace("{DISPLAY-NAME}", displayName)
                    .replace("{MESSAGE}", message)
                val playerList = LobbyPlayers.lobbyPlayersGet()

                for (lobbyPlayer in playerList) {
                    MessageUtil.rawchat(lobbyPlayer, info)
                }
            }
        }
    }
}