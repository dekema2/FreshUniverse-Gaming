package me.king.main;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class Menu implements Listener {

	private Inventory inv;
	private ItemStack kitpvp;
	
	public Menu(Plugin p) {
		inv = Bukkit.getServer().createInventory(null, 9, "Choose your game!");
		
		kitpvp = createItem(new ItemStack(Material.DIAMOND_SWORD), ChatColor.RED + "Kit Pvp", Arrays.asList("Go to the Kit Pvp!"));
		
		inv.setItem(1, kitpvp);
		
		Bukkit.getServer().getPluginManager().registerEvents(this, p);
	}
	
	private ItemStack createItem(ItemStack item, String name,List<String> list) {
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(name);
		im.setLore(list);
		item.setItemMeta(im);
		return item;
	}
	
	public void show(Player p) {
		p.openInventory(inv);
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (!e.getInventory().getName().equalsIgnoreCase(inv.getName())) return;
		if (e.getCurrentItem().getItemMeta() == null) return;
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Kit Pvp")) {
			e.setCancelled(true);

			//TODO Teleport to name
			
		}
	}
}