package catmoe.fallencrystal.cometcore.report

import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.plugin.Command

class ReportCommand : Command("report") {

    override fun execute(sender: CommandSender, args: Array<out String>?) {
        if (args!![0] == "help") { helpCommand(sender) }
    }

    override fun getPermission(): String { return "comet.report" }

    private fun helpCommand(sender: CommandSender) {

    }
}