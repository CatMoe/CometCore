package catmoe.fallencrystal.cometcore.utils.triton

import com.rexcantor64.triton.api.TritonAPI
import com.rexcantor64.triton.api.language.Language
import net.md_5.bungee.api.connection.ProxiedPlayer

class Triton {
    fun getLanguage(player: ProxiedPlayer): Language? {
        val uuid = player.uniqueId
        val api = TritonAPI.getInstance()
        val languagePlayer = api.playerManager.get(uuid)
        return languagePlayer.language
    }
}