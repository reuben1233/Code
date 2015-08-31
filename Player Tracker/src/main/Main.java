package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

import tracker.TrackerCompare;
import tracker.TrackerTask;
import utils.UtilMath;

public class Main extends JavaPlugin implements Listener{
	
	public void onEnable(){
		Bukkit.getPluginManager().registerEvents(this, this);
		this.saveDefaultConfig();
	}
	
	Inventory spec;
	
	ArrayList<UUID> player = new ArrayList<UUID>();
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		if(!player.contains(e.getPlayer().getUniqueId())){
		new TrackerTask(e.getPlayer().getUniqueId()).runTaskTimer(this, 0L, 1L);
		player.add(e.getPlayer().getUniqueId());
		}
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e){
		if(player.contains(e.getPlayer().getUniqueId())){
		player.remove(e.getPlayer().getUniqueId());
		}
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e){
		if(!player.contains(e.getPlayer().getUniqueId())){
		new TrackerTask(e.getPlayer().getUniqueId()).runTaskTimer(this, 0L, 1L);
		player.add(e.getPlayer().getUniqueId());
		}
	}
	
	public Inventory spec(Player p){
		List<UUID> players = new ArrayList<> ();
		for (Player p1 : p.getWorld().getPlayers()) {
			if(!p1.getUniqueId().equals(p.getUniqueId())){
				players.add(p1.getUniqueId());
			}
		}
	    Collections.sort(players, new TrackerCompare(p.getUniqueId()));
		
		spec = Bukkit.createInventory(null, 54, "§8Spectator Menu");
		
		ItemStack pl = new ItemStack(Material.WOOL, 1, (byte)4);
		
		ItemMeta pl1 = (ItemMeta) pl.getItemMeta();
		
		pl1.setDisplayName("§e§lPlayers");
		
	    List<String> lore = new ArrayList<String>();
	    
		lore.add("");
		
		lore.add("§ePlayers: §f" + players.size());
		
		pl1.setLore(lore);
		
		pl.setItemMeta(pl1);
		
		spec.setItem(13, pl);
		
		for(int i = 0; i < players.size(); i++)
		{
		ItemStack player = new ItemStack(Material.SKULL_ITEM, 1, (byte)3);
			
		ItemMeta player1 = (ItemMeta) player.getItemMeta();
		
		player1.setDisplayName("§e" + Bukkit.getPlayer(players.get(i)).getName());
		
	    List<String> lore1 = new ArrayList<String>();
	    
	    lore1.add("");
	    
	    lore1.add("§eDistance: §f" + UtilMath.trim(1, Bukkit.getPlayer(players.get(i)).getLocation().distance(p.getLocation())));
	    
	    lore1.add("§eHeight Difference: §f" + UtilMath.trim(1,Bukkit.getPlayer(players.get(i)).getLocation().getY() - p.getLocation().getY()));
	    
	    lore1.add("");
	    
	    lore1.add("§f§nClick to Spectate");
	    
	    player1.setLore(lore1);
		
		player.setItemMeta(player1);
		
		SkullMeta meta = (SkullMeta) player.getItemMeta();
		
		meta.setOwner(Bukkit.getPlayer(players.get(i)).getName());
		
		player.setItemMeta(meta);
		
		if(i <= 6){
		spec.setItem(i+19, player);
		} else if(i <= 13){
	    spec.setItem(i+28, player);	
		} else if(i <= 20){
	    spec.setItem(i+37, player);		
		} else {
		return spec;
		}
		}
		return spec;
	}
	
	@EventHandler
	public void InventoryClick(InventoryClickEvent e){
	    
		UUID player = e.getWhoClicked().getUniqueId();
		
		List<UUID> players = new ArrayList<> ();
		for (Player p1 : Bukkit.getPlayer(player).getWorld().getPlayers()) {
			if(!p1.getUniqueId().equals(player)){
				players.add(p1.getUniqueId());
			} 
		}
	    Collections.sort(players, new TrackerCompare(player));
	    
	    try{
		
	    if(players.size() <= 1 && e.getInventory().equals(spec)){
			e.setCancelled(true);
	    }
		
	    for(int i = 0; i < players.size(); i++){
	    if(e.getCurrentItem().hasItemMeta() && e.getInventory().equals(spec) &&  e.getCurrentItem().getItemMeta().getDisplayName().contains(Bukkit.getPlayer(players.get(i)).getName())){
			Bukkit.getPlayer(player).teleport(Bukkit.getPlayer(players.get(i)).getLocation().add(0.0D, 1.0D, 0.0D));
		} else if(e.getInventory().equals(spec)){
			e.setCancelled(true);
		}
	    }
	    
	    players.clear();
	} catch(Exception ignore){
		
	}}
		
	@EventHandler
	public void onCompassClick(PlayerInteractEvent e){
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
		 Boolean perms = this.getConfig().getBoolean("permissions");
			if(e.getPlayer().getItemInHand().getType() == Material.COMPASS && e.getPlayer().hasPermission("tracker.menu") || e.getPlayer().getItemInHand().getType() == Material.COMPASS && perms == false){
				e.getPlayer().openInventory(spec(e.getPlayer()));
			}
			}
		Boolean perms = this.getConfig().getBoolean("permissions");
		if(e.getAction() == Action.LEFT_CLICK_AIR && e.getPlayer().hasPermission("tracker.teleport")  || e.getAction() == Action.LEFT_CLICK_AIR && perms == false || e.getAction() == Action.LEFT_CLICK_BLOCK && e.getPlayer().hasPermission("tracker.teleport")  || e.getAction() == Action.LEFT_CLICK_BLOCK && perms == false){
			if(e.getPlayer().getItemInHand().getType() == Material.COMPASS){
				List<UUID> players = new ArrayList<> ();
				for (Player p1 : e.getPlayer().getWorld().getPlayers()) {
					if(!p1.getUniqueId().equals(e.getPlayer().getUniqueId())){
						players.add(p1.getUniqueId());
					}
				}
				
			    Collections.sort (players, new TrackerCompare(e.getPlayer().getUniqueId()));
				
			    Player nearest = null;
			    
			    try{
			    nearest = Bukkit.getPlayer(players.get(0));
			    } catch(IndexOutOfBoundsException ignore){
			    	  
			    }    
			    
				if(nearest != null){
			    e.getPlayer().teleport(nearest.getLocation().add(0.0D, 1.0D, 0.0D));
				}
				
				players.clear();
		}
	  }
   }
}
