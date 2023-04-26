@file:Suppress("DEPRECATION")

package catmoe.fallencrystal.cometcore.config

import net.md_5.bungee.api.ProxyServer

object ConfigManager {
    var test: String? = null
    var mysqlAddress: String? = null
    var mysqlPort: Int? = null
    var mysqlUser: String? = null
    var mysqlPassword: String? = null
    var mysqlDatabase: String? = null

    var mainLobby: String? = null
    fun init(cfg: IConfiguration?) {
        mysqlAddress = cfg!!.getString("mysql.address")
        mysqlPort = cfg.getInt("mysql.address")
        mysqlUser = cfg.getString("mysql.user")
        mysqlPassword = cfg.getString("mysql.password")
        mysqlDatabase = cfg.getString("mysql.database")
        mainLobby = cfg.getString("lobby.main").ifEmpty { ProxyServer.getInstance().config.listeners.iterator().next().defaultServer }
    }
}
