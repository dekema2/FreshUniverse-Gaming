package me.king.main;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class MessageManager {

	private MessageManager() { }
	
	private static MessageManager instance = new MessageManager();
	
	public static MessageManager getInstance() {
		return instance;
	}
	
	private String prefix = ChatColor.BLACK + "[" + ChatColor.GREEN + "F" + ChatColor.BLUE + "U" +  ChatColor.GOLD + "Econ" + ChatColor.BLACK + "] ";
	
	public void info(CommandSender s, String msg) {
		msg(s, ChatColor.YELLOW, msg);
	}
	
	public void severe(CommandSender s, String msg) {
		msg(s, ChatColor.RED, msg);
	}
	
	public void good(CommandSender s, String msg) {
		msg(s, ChatColor.GREEN, msg);
	}
	
	private void msg(CommandSender s, ChatColor color, String msg) {
		s.sendMessage(prefix + color + msg);
	}
}