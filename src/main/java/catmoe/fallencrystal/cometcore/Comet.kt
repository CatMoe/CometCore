package catmoe.fallencrystal.cometcore

import catmoe.fallencrystal.cometcore.config.Config
import catmoe.fallencrystal.cometcore.config.ConfigManager
import catmoe.fallencrystal.cometcore.report.ReportCommand
import net.md_5.bungee.api.ProxyServer
import net.md_5.bungee.api.plugin.Plugin

class Comet : Plugin() {
    private val proxy = ProxyServer.getInstance()!!
    override fun onEnable() {
        proxy.pluginManager.registerCommand(this, ReportCommand())
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    fun loadConfig() {
        val config = Config(this, "%datafolder%/config.yml")
        ConfigManager.init(config)
    }
}
