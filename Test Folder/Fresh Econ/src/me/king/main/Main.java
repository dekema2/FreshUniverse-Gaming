package me.king.main;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public void onEnable() {
		SettingsManager.getInstance().setup(this);
		
		CommandManager cm = new CommandManager();
		getCommand("econ").setExecutor(cm);
		
		if (Bukkit.getServer().getPluginManager().getPermission("Vault") != null) {
			Bukkit.getServer().getServicesManager().register(Economy.class, new VaultConnect(), this, ServicePriority.Highest);
		}
		//TODO /pay
		//TODO /bal
		//TODO Fancy
		
		/*	   @
		 *     public void tes(event e){
		 *     
		 *     }
		 * 
		 */
	}
	
	MessageManager msg = MessageManager.getInstance();
	SettingsManager settings = SettingsManager.getInstance();
	
	public boolean onCommand(CommandSender sender, Command cmd, String[] commandLable,String args[]){
		
		Player p = (Player) sender;
		
		if (cmd.getName().equalsIgnoreCase("pay")){
			if (args.length < 2){
				msg.severe(p, "You must enter a name and a amount!!");
				return true;
			}
			
			Player t = Bukkit.getServer().getPlayer(args[0]);
			
			if (t == null){
				msg.severe(p, "The player must be online for you to pay them!");
				return true;
			}
 			
			try { 
			
				int money = (int) Integer.parseInt(args[0]);
				int tbal = (int) settings.getBalance(t.getName());
				int bal = (int) settings.getBalance(p.getName());
				
				if (money > bal){
					msg.severe(p, "You lack the funds to do that!");
					return true;
				}
				
	 			msg.good(t,  p.getName() + " has sent you $" + Integer.toString(money));
	 			msg.info(p, "You have sent " + t.getName() + " $" + Integer.toString(money) );
	 			settings.removeBalance(p.getName(), money);
	 			settings.addBalance(t.getName(), money);
				
			}
			catch (Exception e) {
				msg.severe(sender, "You didn't put in a valid number!");
				return true;
			}
			
		}
		
		
		
		if (cmd.getName().equalsIgnoreCase("bal")){
			
		}
		
		return true;
	}
	
}