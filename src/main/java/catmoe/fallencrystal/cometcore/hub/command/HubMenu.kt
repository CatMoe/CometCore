package catmoe.fallencrystal.cometcore.hub.command

import catmoe.fallencrystal.cometcore.utils.menu.ForceFormatCode
import catmoe.fallencrystal.cometcore.utils.menu.GUIBuilder
import catmoe.fallencrystal.cometcore.utils.menu.ItemBuilder
import catmoe.fallencrystal.cometcore.utils.triton.Triton
import dev.simplix.protocolize.data.ItemType
import dev.simplix.protocolize.data.inventory.InventoryType
import net.md_5.bungee.api.connection.ProxiedPlayer

class HubMenu : GUIBuilder() {

    val inventoryType = InventoryType.GENERIC_9X3

    private var invTitle : String? = null
    private var toMainLobby : String? = null
    private var toSpecificLobby : String? =  null
    var specificLobbyNotFound : String? = null

    override fun open(player: ProxiedPlayer) {
        flushedMessage(player)
        clear()
        define(player)
        super.open(player)
    }

    private fun flushedMessage(p: ProxiedPlayer?) {
        val triton = Triton()
        val language = triton.getLanguage(p!!)!!.languageId
        if (language.equals("zh_CN")) {
            invTitle = "&b你想去哪里?"
            toMainLobby = "&a回到主大厅"
            toSpecificLobby = "&e前往当前游戏的指定大厅"
            specificLobbyNotFound = "&c当前游戏不支持或没有指定的大厅"
        } else {
            invTitle = "&bWhere are u going?"
            toMainLobby = "&aBack to main lobby"
            toSpecificLobby = "To the specific lobby of the current game"
            specificLobbyNotFound = "Current game does not support/don't have a specific lobby"
        }
    }


    override fun define(p: ProxiedPlayer?) {
        super.define(p)
        this.type(inventoryType)
        setTitle(ca("$invTitle"))
    }

    fun toSpecificLobby(p: ProxiedPlayer?) {
        val lobby = SpecificLobby.getLobby(p!!.server.info.name)
        val lobbyNotFoundItem = ItemType.BARRIER
        val lobbyItem = ItemType.ARROW
        val slot = 15
        if (lobby == ("Unknown")) {
            setItem(slot, ItemBuilder(lobbyNotFoundItem)
                .name(ca(toSpecificLobby!!))
                .lore("")
                .lore(ca(specificLobbyNotFound!!))
                .build()
            )
        } else {
            setItem(slot, ItemBuilder(lobbyItem)
                .name(ca(toSpecificLobby!!))
                .build()
            )
        }
    }

    fun placeholderItem() {
        val slot = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 14, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25 ,26)
        val itemType = ItemType.LIGHT_GRAY_STAINED_GLASS_PANE
        for (item in slot) { setItem(item, ItemBuilder(itemType).name("").build()) }
    }

    fun ca(text: String): String { return ForceFormatCode.replaceFormat(text) }
}