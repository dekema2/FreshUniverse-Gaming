package me.king.main;

import java.lang.annotation.Target;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Teleport implements CommandExecutor{

	Main plugin;
	
	public Teleport(Main plugin){
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		Player p = (Player) sender;
		
		if (cmd.getName().equalsIgnoreCase("teleport")){
			if (!(sender.hasPermission("main.teleport"))){
				sender.sendMessage(ChatColor.RED + "You can't do that!");
				return true;
			}
			
			if (args.length <= 0){
				p.sendMessage(ChatColor.RED + "Ussage: /tp <player> or <player> to <player>");
				return true;
			}
			
			Player t = Bukkit.getServer().getPlayer(args[0]);
			
			if (args.length == 1){
				if (t == null){
					p.sendMessage(ChatColor.RED + "That user is not online!");
					return true;
				}
				
				p.teleport(t.getLocation());
				p.sendMessage(ChatColor.GOLD + "You've been teleported to " + t.getName());
				return true;
				
			} else if (args.length == 2){
				
				Player t2 = Bukkit.getServer().getPlayer(args[1]);
				if (t == null || t2 == null){
					p.sendMessage(ChatColor.RED + "One of the users are not online!");
					return true;
				}
			
			 t.teleport(t2.getLocation());
			 t.sendMessage(ChatColor.GOLD + "You've been teleported to " + t.getName());
			 return true;
			}
		}
		
		
		if (cmd.getName().equalsIgnoreCase("tpcords")){
			if (!(sender.hasPermission("main.cords"))){
				sender.sendMessage(ChatColor.RED + "You can't do that!");
				return true;
			}
			
			try { 
				
				if (args.length < 2 ){
					p.sendMessage(ChatColor.RED + "You need to enter all 3 cords!");
					return true;
				}
				
				int x = Integer.parseInt(args[0]);
				int y = Integer.parseInt(args[1]);
				int z = Integer.parseInt(args[2]);
				
				p.teleport(new Location(p.getWorld(), x,y,z));
				p.sendMessage(ChatColor.GOLD + "Teleported you to your cords!");
				
			}
			catch (Exception e){ p.sendMessage(ChatColor.RED + "You entered a invalid number!"); return true;}
			
		}
		
		return true;
	}
	
}
