package dev.xyzcraft.net.bkbw;

import java.util.Map.Entry;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.UserConnection;

public class PlayerKicker {

public static void kickPlayer(String fullPlayer, String message) {
	for (Entry<String, UserConnection> entry : BungeeCord.instance.connections.entrySet()) {
		if (entry.getKey().equals(fullPlayer)) {
			entry.getValue().disconnect(message);
			break;
		}
	}
}
}
