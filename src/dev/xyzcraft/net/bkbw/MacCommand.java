package dev.xyzcraft.net.bkbw;

import net.md_5.bungee.command.Command;
import net.md_5.bungee.command.CommandSender;
import net.md_5.bungee.plugin.JavaPlugin;

public abstract class MacCommand extends Command{
	JavaPlugin plugin;
	public MacCommand(JavaPlugin pl) {
		plugin = pl;
	}
	public abstract void execute(CommandSender arg0, String[] arg1);
}
