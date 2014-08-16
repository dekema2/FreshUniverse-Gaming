package me.king.main;

import java.util.ArrayList;
import java.util.Arrays;

import me.king.main.commands.Add;
import me.king.main.commands.Balance;
import me.king.main.commands.CommandSetup;
import me.king.main.commands.Remove;
import me.king.main.commands.TopBal;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandManager implements CommandExecutor {

	private ArrayList<CommandSetup> cmds = new ArrayList<CommandSetup>();
	MessageManager msg = MessageManager.getInstance();
			
	
	public CommandManager() {
		cmds.add(new Add());
		cmds.add(new Remove());
		cmds.add(new TopBal());
		cmds.add(new Balance());
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("econ")) {
			if (args.length == 0) {
				
				for (CommandSetup c : cmds) {
					msg.info(sender, "/econ " + c.getName() + " " + c.getArgs() + " - " + c.getDescription());
				}
				
				return true;
			}
			
			ArrayList<String> a = new ArrayList<String>(Arrays.asList(args));
			a.remove(0);
			
			for (CommandSetup c : cmds) {
				if (c.getName().equalsIgnoreCase(args[0])) {
					try { c.run(sender, a.toArray(new String[a.size()])); }
					catch (Exception e) { sender.sendMessage(ChatColor.RED + "Report this to a admin. Error in economy plugin."); e.printStackTrace(); }
					return true;
				}
			}
			
			sender.sendMessage(ChatColor.RED + "Thats not a command!");
		}
		
		return true;
	}
}