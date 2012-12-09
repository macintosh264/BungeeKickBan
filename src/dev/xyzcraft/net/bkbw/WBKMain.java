package dev.xyzcraft.net.bkbw;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.ChatColor;
import net.md_5.bungee.plugin.JavaPlugin;
import net.md_5.bungee.plugin.LoginEvent;

public class WBKMain extends JavaPlugin{
	public BanDatabase bandb = new BanDatabase();
	public WhitelistDatabase whitelistb = new WhitelistDatabase();
	public StringFormatter formatter = new StringFormatter();
	public void onEnable() {
		BungeeCord.instance.commandMap.put("ban", new CommandBan(this));
		BungeeCord.instance.commandMap.put("banlist", new CommandBanList(this));
		BungeeCord.instance.commandMap.put("unban", new CommandUnban(this));
		BungeeCord.instance.commandMap.put("kick", new CommandKick(this));
		BungeeCord.instance.commandMap.put("whitelist", new CommandWhitelist(this));
	}
	public void onHandshake(LoginEvent event) {
		if(this.bandb.isBanned(event.getUsername())) {
			event.setCancelReason(this.bandb.banReason(event.getUsername()));
			event.setCancelled(true);
		}
		if (this.whitelistb.whitelistOn()) {
			if (!this.whitelistb.whitelisted(event.getUsername())) {
				event.setCancelReason(ChatColor.AQUA + "You have not been whitelisted!");
			}
		}
	}
}
