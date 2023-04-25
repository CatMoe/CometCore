@file:Suppress("DEPRECATION")

package catmoe.fallencrystal.cometcore.utils.server

import net.md_5.bungee.api.config.ServerInfo
import net.md_5.bungee.api.plugin.Plugin
import java.net.InetSocketAddress
import java.net.Socket
import java.net.SocketAddress

class SStatus : Plugin() {
    fun isOnline(server: ServerInfo): Boolean {
        return try {
            val socket = Socket()
            val address = server.address.address
            val port = server.address.port
            socket.connect(InetSocketAddress(address, port), 1000)
            socket.close()
            true
        } catch (_: Exception) { false }
    }

    fun getDescription(server: ServerInfo): String { return server.motd }
    fun getOnlinePlayer(server: ServerInfo): Int { return server.players.size }
}