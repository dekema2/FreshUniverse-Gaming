package me.king.main.commands;

import me.king.main.MessageManager;
import me.king.main.SettingsManager;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Balance extends CommandSetup {

	MessageManager msg = MessageManager.getInstance();
	
	public Balance() {
		super("Balance", "Check balance", "Optional:<player>");
	}
	
	@SuppressWarnings("unused")
	public void run(CommandSender sender, String[] args) {	
		
		Player p = (Player) sender;
		
		int bal =  (int) SettingsManager.getInstance().getBalance(p.getName());
		
		if (args.length == 0) {
			msg.good(sender, "Your balance is $" + Integer.toString(bal));
			
		}else{
		
		int amnt;
		
		int otheramnt = (int) SettingsManager.getInstance().getBalance(args[0]);
		
		try { 
			msg.good(sender, args[0] + "'s balance is $" + SettingsManager.getInstance().getBalance(args[0]));
		}
		catch (Exception e) { sender.sendMessage(ChatColor.RED + "That player has not been on the server"); e.printStackTrace(); }
		return;
			
		}
		//TODO Add for other players
		
	}
}