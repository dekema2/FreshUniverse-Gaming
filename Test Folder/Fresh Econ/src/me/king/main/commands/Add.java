package me.king.main.commands;

import me.king.main.MessageManager;
import me.king.main.SettingsManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Add extends CommandSetup {

	MessageManager msg = MessageManager.getInstance();
	SettingsManager settings = SettingsManager.getInstance();
	
	public Add() {
		super("Add", "Add money to balance.", "<player> <amount>");
	}
	
	public void run(CommandSender sender, String[] args) {
		if (!sender.hasPermission("fuecon.add")) {
			sender.sendMessage(ChatColor.RED + "You can't use this command!");
			return;
		}
		
		if (args.length < 2) {
			sender.sendMessage(ChatColor.RED + "You did not enter enough information.");
			msg.severe(sender, "You did not enter enough information!");
			return;
		}
		
		Player p = Bukkit.getServer().getPlayer(args[0]);
		int amnt;
		
		try { amnt = Integer.parseInt(args[1]); }
		catch (Exception e) {
			msg.severe(sender, "Invalid number.");
			return;
		}
		
		SettingsManager.getInstance().addBalance(p.getName(), amnt);
		msg.good(sender, "Added $ " + amnt + " to " + p.getName() + ".");
	}
}