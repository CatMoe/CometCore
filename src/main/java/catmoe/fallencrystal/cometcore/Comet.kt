package catmoe.fallencrystal.cometcore

import catmoe.fallencrystal.cometcore.config.Config
import catmoe.fallencrystal.cometcore.config.ConfigManager
import net.md_5.bungee.api.plugin.Plugin

class Comet : Plugin() {
    override fun onEnable() {
        // Plugin startup logic
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    fun loadConfig() {
        val config = Config(this, "%datafolder%/config.yml")
        ConfigManager.init(config)
    }
}
