package tracker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import utils.PacketUtils;
import utils.UtilMath;

public class TrackerTask extends BukkitRunnable implements Listener{
	
   private UUID player;
   
   public TrackerTask (UUID player) {
	   this.player = player;
   }
   
   String message = null;
 	
List<UUID> players = new ArrayList<> ();
   @Override
   public void run() {
	   Boolean perms = Bukkit.getPluginManager().getPlugin("PlayerTracker").getConfig().getBoolean("permissions");
	   try{
	  if(Bukkit.getPlayer(player).hasPermission("tracker.use") || perms == false){
 	for (Player p : Bukkit.getPlayer(player).getWorld().getPlayers()) {
		if(!p.getUniqueId().equals(player)){
			players.add(p.getUniqueId());
		}}}
		}catch(Exception e){
			
		} 
	
	      Collections.sort (players, new TrackerCompare(player));
	      Player nearest = null;
	      try{
		  nearest = Bukkit.getPlayer(players.get(0));
	      } catch(IndexOutOfBoundsException ignore){
	    	  
	      }     
	      
	      if(nearest != null)
		  message = Bukkit.getPluginManager().getPlugin("PlayerTracker").getConfig().getString("Nearest Player") + nearest.getName() + "   " + Bukkit.getPluginManager().getPlugin("PlayerTracker").getConfig().getString("Distance") + UtilMath.trim(1, nearest.getLocation().distance(Bukkit.getPlayer(player).getLocation())) + "   " + Bukkit.getPluginManager().getPlugin("PlayerTracker").getConfig().getString("Height") + UtilMath.trim(1, nearest.getLocation().getY() - Bukkit.getPlayer(player).getLocation().getY());
	      if(nearest == null)
	    	  message = "";
	   if(message.equals(null))
		   message = "";
	   
	   try{
	   if(Bukkit.getPlayer(player).getItemInHand().getType() == Material.COMPASS) {
		 PacketUtils.send(Bukkit.getPlayer(player), "" + message);
	   }} catch(Exception e){
		   
	   }
	   
	   try {
		   Bukkit.getPlayer(player).setCompassTarget(nearest != null ? nearest.getLocation() : null);
	   } catch(NullPointerException ignore){
		   
	   }
	   
	   players.clear();
   }
	  
   }
