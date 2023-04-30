package catmoe.fallencrystal.cometcore.hub.chat

import catmoe.fallencrystal.cometcore.utils.display.DisplayCache
import catmoe.fallencrystal.cometcore.utils.misc.MessageUtil
import net.md_5.bungee.api.config.ServerInfo
import net.md_5.bungee.api.event.ServerConnectedEvent
import net.md_5.bungee.api.event.ServerDisconnectEvent
import java.util.*
import kotlin.concurrent.schedule

class JoinQuitBroadcast {

    private val prefix = "&bComet&fCore &7» "

    fun join(event: ServerConnectedEvent) {
        val player = event.player
        val server = event.server.info
        val receivedPermission = "comet.lobby.join.received"
        val broadcastPermission = "comet.lobby.join.broadcast"
        if (server.name.contains("Lobby") and player.hasPermission(broadcastPermission)) {
            val message = "$prefix {DISPLAY-NAME} &a加入了大厅"
            val info = message.replace("{DISPLAY-NAME}", DisplayCache.getDisplayName(player))
            Timer().schedule(1000L) {
                broadcastServerAndPermission(server, receivedPermission, info)
            }
        }
    }

    fun quit(event: ServerDisconnectEvent) {
        val player = event.player
        val server = event.target
        val receivedPermission = "comet.lobby.quit.received"
        val broadcastPermission = "comet.lobby.quit.broadcast"
        if (server.name.contains("Lobby") and player.hasPermission(broadcastPermission)) {
            val message = "$prefix {DISPLAY-NAME} &c离开了大厅"
            val info = message.replace("{DISPLAY-NAME}", DisplayCache.getDisplayName(player))
            broadcastServerAndPermission(server, receivedPermission, info)
        }
    }

    fun broadcastServerAndPermission(server: ServerInfo, permission: String, message: String) {
        val players = server.players
        for (player in players) { if (player.hasPermission(permission)) { MessageUtil.rawchat(player, message) } }
    }

}
