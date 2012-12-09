package dev.xyzcraft.net.bkbw;
import net.md_5.bungee.ChatColor;
import net.md_5.bungee.Permission;
import net.md_5.bungee.command.CommandSender;
import net.md_5.bungee.plugin.JavaPlugin;

public class CommandUnban extends MacCommand {

	public CommandUnban(JavaPlugin pl) {
		super(pl);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(CommandSender arg0, String[] arg1) {
		if (getPermission(arg0) == Permission.ADMIN) {
		// TODO Auto-generated method stub
		if (arg1.length < 1) {
			arg0.sendMessage(StringFormatter.error("Specify a player to unban!"));
			return;
		}
		String fullPlayer = arg1[0];
		if (!((WBKMain)this.plugin).bandb.isBanned(fullPlayer)) {
			arg0.sendMessage(StringFormatter.error("Player " + ChatColor.GREEN + fullPlayer + ChatColor.AQUA + " is not banned"));
			return;
		}
		((WBKMain)this.plugin).bandb.unban(fullPlayer);
		arg0.sendMessage(ChatColor.GREEN + fullPlayer + " has been unbanned!");
		}
		else {
			arg0.sendMessage(StringFormatter.error("No Permission"));
		}
	}

}
