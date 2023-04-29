package catmoe.fallencrystal.cometcore.hub.util

import net.md_5.bungee.api.connection.ProxiedPlayer

object LobbyPlayers {
    private val list = mutableListOf<ProxiedPlayer>()

    fun lobbyPlayersGet(): List<ProxiedPlayer> { return list.toList() }

    fun lobbyPlayersAdd(uuid: ProxiedPlayer) { list.add(uuid) }

    fun lobbyPlayersDel(uuid: ProxiedPlayer) { list.remove(uuid) }
}