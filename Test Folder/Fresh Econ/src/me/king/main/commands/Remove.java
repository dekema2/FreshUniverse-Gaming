package me.king.main.commands;

import me.king.main.MessageManager;
import me.king.main.SettingsManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Remove extends CommandSetup {

	public Remove() {
		super("Remove", "Remove money player's balance.", "<player> <amount>");
	}
	
	MessageManager msg = MessageManager.getInstance();
	
	public void run(CommandSender sender, String[] args) {
		if (!sender.hasPermission("fuecon.remove")) {
			sender.sendMessage(ChatColor.RED + "You can't use this command!");
			return;
		}
		
		if (args.length < 2) {
			msg.severe(sender, "You did not enter enough args.");
			return;
		}
		
		Player p = Bukkit.getServer().getPlayer(args[0]);
		int amnt;
		
		try { amnt = Integer.parseInt(args[1]); }
		catch (Exception e) {			
			msg.severe(sender, "Thats not a number.");				
			return;
		}
		
		SettingsManager.getInstance().removeBalance(p.getName(), amnt);
		msg.good(sender, "Removed $ " + amnt + " from " + p.getName() + ".");
	}
}