package me.king.main;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Main extends JavaPlugin implements Listener{

	ArrayList<UUID> hub = new ArrayList<UUID>();
	private Menu menu;
	
	public void onEnable(){
		menu = new Menu(this);
		Bukkit.getServer().getPluginManager().registerEvents(new Menu(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		
		this.getCommand("teleport").setExecutor(new Teleport(this));
		this.getCommand("tpcords").setExecutor(new Teleport(this));
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]){
		
		if (!(sender instanceof Player)){
			sender.sendMessage(ChatColor.RED + "Only players can use this plugin!");
			return true;
		}
		
		final Player p = (Player) sender;
		Location pl = p.getLocation();
		final UUID uuid = p.getUniqueId();
		
		if (cmd.getName().equalsIgnoreCase("hub")){
			
        	hub.add(uuid);
        	
        	p.sendMessage(ChatColor.GOLD + "Teleporting you to the hub please don't move, teleporting in 5!");
        	
        	
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                public void run() {

                	if (hub.contains(uuid)){
                		int y = 75;
                		int x = 28;
                		int z = 9;
                		float yaw = -110;
                		float pitch = 0;
                		World w = Bukkit.getServer().getWorld("NewSpawn");
                		p.teleport(new Location(w, x, y, z, yaw, pitch));
                		p.sendMessage(ChatColor.GOLD + "You've been teleported to the hub!");
                		hub.remove(p.getUniqueId());
                		
                	}else {
                		return;
                	}
                	
                }
        }, 5 * 20);
			
		}		
		
		return true;
	}
	
	@EventHandler
	public void onPlayerMove3(PlayerMoveEvent e ){
		Player p = e.getPlayer();
		if (!(p.getWorld() == Bukkit.getWorld("NewSpawn"))) { return; }
			if (p.hasPotionEffect(PotionEffectType.SPEED)) {return;}
			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2));
		}

	@EventHandler
	public void onPlayerMove1(PlayerMoveEvent e ){
		Player p = e.getPlayer();

		if (!(hub.contains(p.getUniqueId()))) { return; }
		hub.remove(p.getUniqueId());
		p.sendMessage(ChatColor.RED + "You moved your teleport has been canceled!");
		return;
		}
	
	@EventHandler
	public void onPlayerRightClick(PlayerInteractEvent e){
		
		Player p = e.getPlayer();
		
		if (!(e.getAction() == Action.RIGHT_CLICK_AIR)) {return;}
		if (e.getItem().getType() == Material.WATCH &&
				p.getWorld().getName() == "NewSpawn"){
			menu.show(p);
		}else{
			return;
		}
		
		
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e ){
		Player p = e.getPlayer();
		if (!(p.getWorld().getName() == "NewSpawn")) { return; }
		
		if (p.getLocation().getY() <= 0) {
    		int y = 75;
    		int x = 28;
    		int z = 9;
    		float yaw = -110;
    		float pitch = 0;
    		World w = p.getWorld();
    		p.teleport(new Location(w, x, y, z, yaw, pitch));
		}else{
			return;
		}
		
	}
	
}
