package catmoe.fallencrystal.cometcore.hub.chat

import catmoe.fallencrystal.cometcore.hub.util.LobbyPlayers
import catmoe.fallencrystal.cometcore.utils.display.DisplayCache
import catmoe.fallencrystal.cometcore.utils.misc.MessageUtil
import com.github.benmanes.caffeine.cache.Caffeine
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.ProxyServer
import net.md_5.bungee.api.connection.ProxiedPlayer
import net.md_5.bungee.api.event.ChatEvent
import net.md_5.bungee.api.plugin.Plugin
import java.util.*
import java.util.concurrent.TimeUnit

class LobbyChat(private val plugin: Plugin) {

    private val proxy = ProxyServer.getInstance()

    private val limitTime = 3

    private val spamLimitCache = Caffeine.newBuilder().expireAfterWrite(limitTime.toLong(), TimeUnit.SECONDS).build<UUID, Int>()

    fun lobbyChat(event: ChatEvent) {
        val player = proxy.getPlayer(event.sender.toString())
        val isCommand = event.isCommand
        val message = event.message
        if (player.server.info.name.contains("Lobby") and !isCommand) {
            event.isCancelled = true
            sendChat(player, message)
        }
    }

    private fun colorize(player: ProxiedPlayer, message: String): String {
        val formatCode = mapOf(
            "0" to "black",
            "1" to "dark_blue",
            "2" to "dark_green",
            "3" to "dark_aqua",
            "4" to "dark_red",
            "5" to "dark_purple",
            "6" to "gold",
            "7" to "gray",
            "8" to "dark_gray",
            "9" to "blue",
            "a" to "green",
            "b" to "aqua",
            "c" to "red",
            "d" to "light_purple",
            "e" to "yellow",
            "f" to "white",
            "m" to "strikethrough",
            "n" to "underline",
            "l" to "bold",
            "k" to "magic",
            "o" to "italic",
            "r" to "reset"
        )
        var colorizedMessage = message
        for ((format, permission) in formatCode) { if (player.hasPermission("comet.chat.color.$permission"))
        { colorizedMessage = colorizedMessage.replace("&$format", "§$format") } }
        return colorizedMessage
    }

    private fun cancelSending(player: ProxiedPlayer): Boolean {
        val uuid = player.uniqueId
        if (player.hasPermission("comet.chat.bypass")) return false
        return spamLimitCache.getIfPresent(uuid) != null
    }

    private fun sendChat(player: ProxiedPlayer, message: String) {
        if (cancelSending(player)) {
            val warn : List<String> = listOf(
                "",
                "&c&m                                                                                ",
                "&e您只能每3秒发送1条消息!",
                "&c&m                                                                                ",
                ""
            )
            warn.forEach { MessageUtil.rawchat(player, it) }
            return
        }
        proxy.scheduler.runAsync(plugin) {
            val displayName = DisplayCache.getDisplayName(player)
            val format = ChatColor.translateAlternateColorCodes('&', "{DISPLAY-NAME} &f: &f{MESSAGE}")
            val info = format
                .replace("{DISPLAY-NAME}", ChatColor.translateAlternateColorCodes('&', displayName))
                .replace("{MESSAGE}", message)
            val playerList = LobbyPlayers.lobbyPlayersGet()
            if (!player.hasPermission("comet.chat.bypass")) { spamLimitCache.put(player.uniqueId, 1) }
            if (player.hasPermission("comet.chat.color.all")) { for (players in playerList) { MessageUtil.rawchat(players, info) } }
            else { val colorizedMessage = colorize(player, info); for (players in playerList) { MessageUtil.rawChatNoColor(players, colorizedMessage) } }
        }
    }
}