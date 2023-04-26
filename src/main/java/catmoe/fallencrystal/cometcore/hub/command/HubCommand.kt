package catmoe.fallencrystal.cometcore.hub.command

import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.plugin.Command

class HubCommand : Command("hub") {

    override fun execute(sender: CommandSender, args: Array<out String>?) {
        TODO("Not yet implemented")
    }

    override fun getAliases(): Array<String> { return listOf("lobby").toTypedArray<String>() }
}