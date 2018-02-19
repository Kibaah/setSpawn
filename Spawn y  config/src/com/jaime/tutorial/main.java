package com.jaime.tutorial;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin implements Listener{

	//CONSOLE SENDER
	@Override 		
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Pugin activado!");
		
		Bukkit.getPluginManager().registerEvents(this, this);
		
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	//COMAND*
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String []args) {
		
		if (cmd.getName().equals("setspawn")) {
			if (sender.hasPermission("player.spawn")) {
				Player p = (Player) sender;
				
				getConfig().set("Spawn.x", (int) p.getLocation().getX());
				getConfig().set("Spawn.y", (int) p.getLocation().getY());
				getConfig().set("Spawn.z", (int) p.getLocation().getZ());
				getConfig().set("Spawn.world", p.getWorld().getName());
				saveConfig();
				reloadConfig();
				
				int x = getConfig().getInt("Spawn.x");
				int y = getConfig().getInt("Spawn.y");
				int z = getConfig().getInt("Spawn.z");
				String mundo = getConfig().getString("Spawn.world");
				
				p.sendMessage(ChatColor.GREEN + "Has puesto el spawn en " + x + " " + y + " " + z + " " + "en el mundo: " + mundo);
				p.playSound(p.getPlayer().getLocation(), Sound.ENTITY_VILLAGER_YES, 1, 1);
			}
		} 
		if (cmd.getName().equals("spawn")) {
			
			if (sender.hasPermission("player.spawn")) {
			
				Player p = (Player) sender;
				
			int x = getConfig().getInt("Spawn.x");
			int y = getConfig().getInt("Spawn.y");
			int z = getConfig().getInt("Spawn.z");
			String mundo = getConfig().getString("Spawn.world");
			Location n = new Location(Bukkit.getWorld(mundo), x, y, z);
			p.getPlayer().teleport(n);
			
			}
		} 
		return false;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
		if(!getConfig().getString("Spawn.world").contains("nulo")) {
			int x = getConfig().getInt("Spawn.x");
			int y = getConfig().getInt("Spawn.y");
			int z = getConfig().getInt("Spawn.z");
			String mundo = getConfig().getString("Spawn.world");
			Location l = new Location(Bukkit.getWorld(mundo), x, y, z);
			e.getPlayer().teleport(l);
		}
	}
}
