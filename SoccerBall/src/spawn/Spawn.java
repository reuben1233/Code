package spawn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftSlime;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import net.minecraft.server.v1_8_R1.EntitySlime;
import npc.NMSUtils;
import utils.PacketUtils;

public class Spawn extends JavaPlugin implements Listener{
	
	public List<Slime> slime = new ArrayList<> ();
	
	public List<Player> blue = new ArrayList<> ();
	
	public List<Player> red = new ArrayList<> ();
	
	int redpoints;
	
	int bluepoints;
	
	public List<Player> soccer = new ArrayList<> ();
	
	String scorer;
	
	public static Random r = new Random();
	   public static int rint = r.nextInt(2); 
	
	public void onEnable(){
		
		Bukkit.getPluginManager().registerEvents(this, this);
		
		for(Entity e : Bukkit.getWorld("Lobby").getEntities()){
			if(e instanceof Slime){
				e.remove();
			}
		}
		
		onRun();
		
		new NMSUtils().registerEntity("Slime", 55, EntitySlime.class, npc.Slime.class);

		
	}
	
	    public void onRun(){
	    	 Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
		            @Override
		            public void run() {
		            	if(slime.isEmpty() || slime.get(0) == null || slime.get(0).isDead()){
		            		slime.clear();
		            		final Slime z = npc.Slime.spawn(new Location(Bukkit.getWorld("Lobby"), 285.5,30,-1141.5));
		            		z.setCustomName("");
		            		z.setCustomNameVisible(false);
		            		z.setSize(2);
		            		slime.add(z);
		            	}
		            	
		            	Cuboid field = new Cuboid(new Location(Bukkit.getWorld("Lobby"),277, 31, -1155), new Location(Bukkit.getWorld("Lobby"),294, 24, -1128));
		            	
				        if(!field.containsLocation(slime.get(0).getLocation())){
				        	
			        		for(Entity e : Bukkit.getWorld("Lobby").getEntities()){
			        			if((e instanceof Slime)){
			        				e.remove();
			        			}
			        		}

			        		slime.get(0).teleport(new Location(Bukkit.getWorld("Lobby"),281, 28, -1155));
					    }
		            	
			            Cuboid redgoal = new Cuboid(new Location(Bukkit.getWorld("Lobby"),281, 28, -1155), new Location(Bukkit.getWorld("Lobby"),289, 24, -1159));
		            	
		            	Cuboid bluegoal = new Cuboid(new Location(Bukkit.getWorld("Lobby"),281, 28, -1123), new Location(Bukkit.getWorld("Lobby"),289, 24, -1128));
		            	
		            	for(Player p : Bukkit.getOnlinePlayers()){
		            		
			            if(field.containsLocation(p.getLocation()) && !soccer.contains(p)){
			            	    	soccer.add(p);
					                p.getInventory().setHelmet(new ItemStack(Material.AIR));
					            	p.getInventory().setChestplate(new ItemStack(Material.AIR));
					            	p.getInventory().setLeggings(new ItemStack(Material.AIR));
					            	p.getInventory().setBoots(new ItemStack(Material.AIR));
				            	    p.sendMessage("§b[Soccer] §7You joined §eSoccer§7.");
			            		if(red.size() < blue.size()){
			            			red.add(p);
			            			ItemStack bchestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
			            			LeatherArmorMeta lam = (LeatherArmorMeta)bchestplate.getItemMeta();
			            			lam.setColor(Color.RED);
			            			bchestplate.setItemMeta(lam);
			            			p.getInventory().setChestplate(bchestplate);
			            			ItemStack bleggings = new ItemStack(Material.LEATHER_LEGGINGS, 1);
			            			bleggings.setItemMeta(lam);
			            			p.getInventory().setLeggings(bleggings);
			            			ItemStack bboots = new ItemStack(Material.LEATHER_BOOTS, 1);
			            			bboots.setItemMeta(lam);
			            			p.getInventory().setBoots(bboots);
			            			p.sendMessage("§b[Soccer] §7You joined the §c§lRed Team§7.");
			            		} else if(blue.size() < red.size()){
			            			blue.add(p);
			            			ItemStack bchestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
			            			LeatherArmorMeta lam = (LeatherArmorMeta)bchestplate.getItemMeta();
			            			lam.setColor(Color.AQUA);
			            			bchestplate.setItemMeta(lam);
			            			p.getInventory().setChestplate(bchestplate);
			            			ItemStack bleggings = new ItemStack(Material.LEATHER_LEGGINGS, 1);
			            			bleggings.setItemMeta(lam);
			            			p.getInventory().setLeggings(bleggings);
			            			ItemStack bboots = new ItemStack(Material.LEATHER_BOOTS, 1);
			            			bboots.setItemMeta(lam);
			            			p.getInventory().setBoots(bboots);
			            			p.sendMessage("§b[Soccer] §7You joined the §b§lBlue Team§7.");
			            		} else if(rint == 0){
			            			red.add(p);
			            			ItemStack bchestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
			            			LeatherArmorMeta lam = (LeatherArmorMeta)bchestplate.getItemMeta();
			            			lam.setColor(Color.RED);
			            			bchestplate.setItemMeta(lam);
			            			p.getInventory().setChestplate(bchestplate);
			            			ItemStack bleggings = new ItemStack(Material.LEATHER_LEGGINGS, 1);
			            			bleggings.setItemMeta(lam);
			            			p.getInventory().setLeggings(bleggings);
			            			ItemStack bboots = new ItemStack(Material.LEATHER_BOOTS, 1);
			            			bboots.setItemMeta(lam);
			            			p.getInventory().setBoots(bboots);
			            			p.sendMessage("§b[Soccer] §7You joined the §c§lRed Team§7.");
			            		} else if(rint == 1){
			            			blue.add(p);
			            			ItemStack bchestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
			            			LeatherArmorMeta lam = (LeatherArmorMeta)bchestplate.getItemMeta();
			            			lam.setColor(Color.AQUA);
			            			bchestplate.setItemMeta(lam);
			            			p.getInventory().setChestplate(bchestplate);
			            			ItemStack bleggings = new ItemStack(Material.LEATHER_LEGGINGS, 1);
			            			bleggings.setItemMeta(lam);
			            			p.getInventory().setLeggings(bleggings);
			            			ItemStack bboots = new ItemStack(Material.LEATHER_BOOTS, 1);
			            			bboots.setItemMeta(lam);
			            			p.getInventory().setBoots(bboots);
			            			p.sendMessage("§b[Soccer] §7You joined the §b§lBlue Team§7.");
			            		}
		                    } else if(!field.containsLocation(p.getLocation()) && !redgoal.containsLocation(p.getLocation()) && !bluegoal.containsLocation(p.getLocation())){
		                    	if(soccer.contains(p)){
					            	   blue.remove(p);
					            	   red.remove(p);
					            	   soccer.remove(p);
					            	   p.getInventory().setHelmet(new ItemStack(Material.AIR));
					            	   p.getInventory().setChestplate(new ItemStack(Material.AIR));
					            	   p.getInventory().setLeggings(new ItemStack(Material.AIR));
					            	   p.getInventory().setBoots(new ItemStack(Material.AIR));
					            	   p.sendMessage("§b[Soccer] §7You left §eSoccer§7.");
				            	   }
		               }
			            
			            if(redgoal.containsLocation(slime.get(0).getLocation())){
			            	bluepoints++;
			            	
			            	for(Player p1 : soccer){
			            	PacketUtils.sendTitle(p1, "§fGoal! (§c" + redpoints + " §f: §9" + bluepoints + "§f)", "§b" + scorer + " §fscored for the §bBlue Team", 20, 200, 20);
			            	}
			            	
			        		for(Entity e : Bukkit.getWorld("Lobby").getEntities()){
			        			if((e instanceof Slime)){
			        				e.remove();
			        			}
			        		}

			        		slime.clear();
			        		final Slime z = npc.Slime.spawn(new Location(Bukkit.getWorld("Lobby"), 285.5,30,-1141.5));
		            		z.setCustomName("");
		            		z.setCustomNameVisible(false);
		            		z.setSize(2);
		            		slime.add(z);
			        		
			            }
			            
			            
			            if(bluegoal.containsLocation(slime.get(0).getLocation())){
			            	redpoints++;
			            	
			            	for(Player p1 : soccer){
			            	PacketUtils.sendTitle(p1, "§fGoal! (§c" + redpoints + " §f: §9" + bluepoints + "§f)", "" + scorer + " §fscored for the §cRed Team", 20, 200, 20);
			            	}
			            	
			        		for(Entity e : Bukkit.getWorld("Lobby").getEntities()){
			        			if((e instanceof Slime)){
			        				e.remove();
			        			}
			        		}
			        		
			        		slime.clear();
			        		final Slime z = npc.Slime.spawn(new Location(Bukkit.getWorld("Lobby"), 285.5,30,-1141.5));
		            		z.setCustomName("");
		            		z.setCustomNameVisible(false);
		            		z.setSize(2);
		            		slime.add(z);
			            }
			            
		            }
	    	      }},0,1);
	    }
	    
	    public static Entity[] getNearbyEntities(int radius, Location l) {
	        int chunkRadius = radius < 16 ? 1 : (radius - (radius % 16)) / 16;
	        HashSet <Entity> radiusEntities = new HashSet < Entity > ();
	     
	        for (int chX = 0 - chunkRadius; chX <= chunkRadius; chX++) {
	            for (int chZ = 0 - chunkRadius; chZ <= chunkRadius; chZ++) {
	                int x = (int) l.getX(), y = (int) l.getY(), z = (int) l.getZ();
	                for (Entity e: new Location(l.getWorld(), x + (chX * 16), y, z + (chZ * 16)).getChunk().getEntities()) {
	                    if (e.getLocation().distance(l) <= radius && e.getLocation().getBlock() != l.getBlock())
	                        radiusEntities.add(e);
	                }
	            }
	        }
	     
	        return radiusEntities.toArray(new Entity[radiusEntities.size()]);
	    }
	    
	    @EventHandler
	    public void onPlayerMove(PlayerMoveEvent e){
	    	Entity[] entity = getNearbyEntities(2, e.getPlayer().getLocation());
	    	for(Entity slime1 : entity){
	    	if(slime1 instanceof Slime){
				if(red.contains(e.getPlayer())){
				scorer = "§c" + e.getPlayer().getName();
				} else if(blue.contains(e.getPlayer())) {
				scorer = "§b" + e.getPlayer().getName();
				}
				if(slime.get(0).getLocation().getY() < 26){
				Vector v = new Vector(((CraftLivingEntity) e.getPlayer()).getEyeLocation().getDirection().getX(),0.3,((CraftLivingEntity) e.getPlayer()).getEyeLocation().getDirection().getZ());
				slime.get(0).setVelocity(v);
				}
	    	}
	    	}
	    }
	    
	    @EventHandler
	    public void onSlimeDamage(EntityDamageEvent e){
	    	if(e.getEntity() instanceof Slime)
	    	e.setCancelled(true);
	    }
		
		@EventHandler
		public void onSlimeHit(EntityDamageByEntityEvent e){
			if(e.getEntity() instanceof CraftSlime && e.getDamager() instanceof Player && soccer.contains(e.getDamager())){
				e.setCancelled(true);
				if(red.contains(e.getDamager())){
				scorer = "§c" + e.getDamager().getName();
				} else if(blue.contains(e.getDamager())) {
				scorer = "§b" + e.getDamager().getName();
				}
				if(slime.get(0).getLocation().getY() < 26){
				Vector v = new Vector(((CraftLivingEntity) e.getDamager()).getEyeLocation().getDirection().getX(),0.3,((CraftLivingEntity) e.getDamager()).getEyeLocation().getDirection().getZ());
				slime.get(0).setVelocity(v);
				}
		}
		}
}
