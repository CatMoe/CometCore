package catmoe.fallencrystal.cometcore.hub.command

object SpecificLobby {
    @JvmStatic
    fun getLobby(server: String): String {return lobbyFromGameType(server)}

    private fun lobbyFromGameType(server:String): String {
        return if (server.contains("BW")) {
            "BW-Lobby"
        } else if (server.contains("SW")) {
            "SW-Lobby"
        } else { "null" }
    }
}