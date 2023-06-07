package catmoe.fallencrystal.cometcore.hub.inv

import dev.simplix.protocolize.api.Protocolize
import net.md_5.bungee.api.connection.ProxiedPlayer

class HubInventory {

    fun getInventory(player: ProxiedPlayer) {
        val protocolPlayer = Protocolize.playerProvider().player(player.uniqueId)
        val inventory = protocolPlayer.proxyInventory()
        TODO()
    }
}