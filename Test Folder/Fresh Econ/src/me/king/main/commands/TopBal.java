package me.king.main.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import me.king.main.MessageManager;
import me.king.main.SettingsManager;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class TopBal extends CommandSetup {

	public TopBal() {
		super("TopBall", "Get top ten richest players", "");
	}
	
	MessageManager msg = MessageManager.getInstance();			
	
	public void run(CommandSender sender, String[] args) {
		
		ArrayList<String> data = SettingsManager.getInstance().getValues();
		
		Collections.sort(data, new Comparator<String>(){
			public int compare(String a, String b) {
				int aVal = Integer.parseInt(a.split(" ")[0]);
				int bVal = Integer.parseInt(b.split(" ")[0]);
				
				return Integer.compare(aVal, bVal);
			}
		});
		
		for (int i = (data.size() > 10 ? 10 : data.size()) - 1; i >= 0; i--){
			String line = data.get(i); //10
			String player = line.split(" ")[0], bal = line.split(" ")[1];
			
			sender.sendMessage(ChatColor.GOLD + player + ":$" + bal);
			msg.info(sender, player + ":$" + bal);
			
		}
		
		
	}
}