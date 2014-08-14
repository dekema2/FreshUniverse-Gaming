package me.ash.MOTDBroadcast;

//import relavent api
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class MOTDBroadcast extends JavaPlugin {
	
	SettingsManager settings = SettingsManager.getInstance();
	
	@Override
	public void onEnable(){
		//lists on console 
		getLogger().info("MOTD and Broadcaster has been started.");
		getConfig().options().copyDefaults(true);
		saveConfig();
		
		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
	         public void run() {
	        	 
	        	 Bukkit.getServer().broadcastMessage(ChatColor.BLACK + "[" +  ChatColor.GREEN + "F" + ChatColor.BLUE + "U" + ChatColor.BLACK + "]" + ChatColor.GOLD +
	        	 "FU 2.0 is on the way! More info at http://fugaming.co!");
	        	 
	         }
	     }, 60, 100);
		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
	         public void run() {
	        	 
	        	 Bukkit.getServer().broadcastMessage(ChatColor.BLACK + "[" +  ChatColor.GREEN + "F" + ChatColor.BLUE + "U" + ChatColor.BLACK + "]" + ChatColor.GOLD +
	        	 "Follow our Twitter account @FUGaming");
	        	 
	         }
	     }, 200, 300);
		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
	         public void run() {
	        	 
	        	 Bukkit.getServer().broadcastMessage(ChatColor.BLACK + "[" +  ChatColor.GREEN + "F" + ChatColor.BLUE + "U" + ChatColor.BLACK + "]" + ChatColor.GOLD +
	        	 "Vote every day to receive kits, rewards and perks at http://fugaming.co!");
	        	 
	         }
	     }, 400, 500);
		
		
		
	}
	@Override
	public void onDisable(){
		getLogger().info("MOTD and Broadcaster has been shutdown.");
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]){
		Player player = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("motd")){
			
			if(args.length > 0){
				player.sendMessage("You have entered too much.");
			}else{
				player.sendMessage(ChatColor.GOLD + "[MOTD]: " + ChatColor.translateAlternateColorCodes('&', this.getConfig().get("motd").toString()));
			}
			return true;
		}else if(cmd.getName().equalsIgnoreCase("setMOTD")){
			
			//checks if player has permission to overwrite the motd
			if(player.hasPermission("setMOTD.motdbroadcaster") || player.isOp()){
				
				if(args.length < 1){
					
					player.sendMessage("Sorry but please enter a message.");
					
				}else{
					StringBuilder motd = new StringBuilder();
					for(int i = 0; i < args.length; i++){
						motd.append(args[i]).append(" ");
					}
					this.getConfig().set("motd", motd);
					player.sendMessage("The MOTD has been changed");
				}
			}
			return true;
			
		}else if(cmd.getName().equalsIgnoreCase("broadcast")){
			if(player.hasPermission("broadcast.motdbroadcaster") || player.isOp()){
				if(args.length < 1){
					player.sendMessage("Sorry but please enter a message to broadcast.");
				}else{
					StringBuilder message = new StringBuilder();
					for(int i = 0; i < args.length; i++){
						message.append(args[i]).append(" ");
					}
					Bukkit.broadcastMessage(ChatColor.RED + "[BROADCAST]: " + ChatColor.translateAlternateColorCodes('&', message.toString()));
				}
			}
			return true;
		}
		
		return true;
		
	}
	
	// event for signs
	@EventHandler
    public void onSignChange(SignChangeEvent e){
            String line1 = e.getLine(0);
            String line2 = e.getLine(1);
            String line3 = e.getLine(2);
            String line4 = e.getLine(3);
            e.setLine(0, ChatColor.translateAlternateColorCodes('&', line1));
            e.setLine(1, ChatColor.translateAlternateColorCodes('&', line2));;
            e.setLine(2, ChatColor.translateAlternateColorCodes('&', line3));
            e.setLine(3, ChatColor.translateAlternateColorCodes('&', line4));
    }
	
}
