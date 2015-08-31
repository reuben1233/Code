package main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftArmorStand;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.EulerAngle;

public class Main extends JavaPlugin implements Listener{
	
	Inventory ArmorStand;
	
	Inventory Rotation;
	
	int headx = 0;
	int heady = 0;
	int headz = 0;
	
	int bodyx = 0;
	int bodyy = 0;
	int bodyz = 0;
	
	int armsx = 0;
	int armsy = 0;
	int armsz = 0;
	
	int legsx = 0;
	int legsy = 0;
	int legsz = 0;
	
	boolean arms = false;
	
	boolean size = false;
	
	boolean invis = true;
	
	boolean gravity = true;
	
	boolean invul = false;
	
	boolean look = false;

	List<ArmorStand> am = new ArrayList<> ();
	
	public void onEnable(){
		Bukkit.getPluginManager().registerEvents(this, this);
	}
	
	public Inventory armorStand(Player p){
		
		ArmorStand = Bukkit.createInventory(null, 54, "§8Armor Stand Menu");
		
		ItemStack rotation = new ItemStack(Material.ITEM_FRAME, 1);
		
		ItemMeta rotation1 = (ItemMeta) rotation.getItemMeta();
		
		rotation1.setDisplayName("§a§lRotation");
		
		rotation.setItemMeta(rotation1);
		
		ArmorStand.setItem(19, rotation);
		
		ItemStack gravity = new ItemStack(Material.PISTON_BASE, 1);
		
		ItemMeta gravity1 = (ItemMeta) rotation.getItemMeta();
		
		gravity1.setDisplayName("§a§lGravity");
		
		gravity.setItemMeta(gravity1);
		
		ArmorStand.setItem(21, gravity);
		
		ItemStack invisibility = new ItemStack(Material.POTION, 1, (byte)8270);
		
		ItemMeta invisibility1 = (ItemMeta) invisibility.getItemMeta();
		
		invisibility1.setDisplayName("§a§lInvisibility");
		
		invisibility.setItemMeta(invisibility1);
		
		ArmorStand.setItem(23, invisibility);
		
		ItemStack arms = new ItemStack(Material.STICK, 1);
		
		ItemMeta arms1 = (ItemMeta) arms.getItemMeta();
		
		arms1.setDisplayName("§a§lArms");
		
		arms.setItemMeta(arms1);
		
		ArmorStand.setItem(25, arms);
		
		ItemStack invulnerability = new ItemStack(Material.POTION, 1, (byte)8257);
		
		ItemMeta invulnerability1 = (ItemMeta) invulnerability.getItemMeta();
		
		invulnerability1.setDisplayName("§a§lInvulnerability");
		
		invulnerability.setItemMeta(invulnerability1);
		
		ArmorStand.setItem(37, invulnerability);
		
		ItemStack size = new ItemStack(Material.ARMOR_STAND, 1);
		
		ItemMeta size1 = (ItemMeta) size.getItemMeta();
		
		size1.setDisplayName("§a§lSize");
		
		size.setItemMeta(size1);
		
		ArmorStand.setItem(39, size);
		
		ItemStack armor = new ItemStack(Material.DIAMOND_HELMET, 1);
		
		ItemMeta armor1 = (ItemMeta) armor.getItemMeta();
		
		armor1.setDisplayName("§a§lArmor");
		
	    List<String> armor2 = new ArrayList<String>();
	    
	    armor2.add("§eClick with a piece armor you want");
		
	    armor1.setLore(armor2);
		
		armor.setItemMeta(armor1);
		
		ArmorStand.setItem(41, armor);
		
		ItemStack item = new ItemStack(Material.DIAMOND_SWORD, 1);
		
		ItemMeta item1 = (ItemMeta) item.getItemMeta();
		
		item1.setDisplayName("§a§lItem in Hand");
		
	    List<String> item2 = new ArrayList<String>();
	    
	    item2.add("§eClick with the item you want in the hand");
		
	    item1.setLore(item2);
		
		item.setItemMeta(item1);
		
		ArmorStand.setItem(43, item);
		
		ItemStack look = new ItemStack(Material.SKULL_ITEM, 1, (byte)3);
		
		ItemMeta look1 = (ItemMeta) look.getItemMeta();
		
		look1.setDisplayName("§a§lLook at Player");
		
		look.setItemMeta(look1);
		
		SkullMeta meta = (SkullMeta) look.getItemMeta();
		
		meta.setOwner(p.getName());
		
		look.setItemMeta(meta);
		
		ArmorStand.setItem(4, look);
		
		return ArmorStand;
	}
	
	public Inventory rotation(){
		
		Rotation = Bukkit.createInventory(null, 54, "§8Armor Stand Menu");
		
		ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (byte)3);
		
		ItemMeta head1 = (ItemMeta) head.getItemMeta();
		
		head1.setDisplayName("§a§lHead");
		
	    List<String> head2 = new ArrayList<String>();
	    
		head2.add("§eLeft-Click to rotate x");
		
		head2.add("§eRight-Click to rotate z");
		
		head2.add("§eShift-Click to rotate y");
		
		head1.setLore(head2);
		
		head.setItemMeta(head1);
		
		Rotation.setItem(13, head);
		
		ItemStack body = new ItemStack(Material.IRON_CHESTPLATE);
		
		ItemMeta body1 = (ItemMeta) body.getItemMeta();
		
		body1.setDisplayName("§a§lBody");
		
	    List<String> body2 = new ArrayList<String>();
	    
		body2.add("§eLeft-Click to rotate x");
		
		body2.add("§eRight-Click to rotate z");
		
		body2.add("§eShift-Click to rotate y");
		
		body1.setLore(body2);
		
		body.setItemMeta(body1);
		
		Rotation.setItem(22, body);
		
        ItemStack arms = new ItemStack(Material.STICK);
		
		ItemMeta arms1 = (ItemMeta) arms.getItemMeta();
		
		arms1.setDisplayName("§a§lArms");
		
	    List<String> arms2 = new ArrayList<String>();
	    
		arms2.add("§eLeft-Click to rotate x");
		
		arms2.add("§eRight-Click to rotate z");
		
		arms2.add("§eShift-Click to rotate y");
		
		arms1.setLore(arms2);
		
		arms.setItemMeta(arms1);
		
		Rotation.setItem(31, arms);
		
        ItemStack legs = new ItemStack(Material.IRON_BOOTS);
		
		ItemMeta legs1 = (ItemMeta) legs.getItemMeta();
		
		legs1.setDisplayName("§a§lLegs");
		
	    List<String> legs2 = new ArrayList<String>();
	    
		legs2.add("§eLeft-Click to rotate x");
		
		legs2.add("§eRight-Click to rotate z");
		
		legs2.add("§eShift-Click to rotate y");
		
		legs1.setLore(legs2);
		
		legs.setItemMeta(legs1);
		
		Rotation.setItem(40, legs);
		
		return Rotation;
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e){
		if(e.getInventory().equals(ArmorStand)){
		   if(e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains("Arms")){
           if(arms == false){
        	   am.get(0).setArms(true);
        	   arms = true;
           } else if(arms == true){
        	   am.get(0).setArms(false);
        	   arms = false;
           }
		   am.clear();
		   e.setCancelled(true);
		   e.getWhoClicked().closeInventory();
		   }
		   if(e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains("Size")){
	           if(size == false){
	        	   am.get(0).setSmall(true);
	        	   size = true;
	           } else if(size == true){
	        	   am.get(0).setSmall(false);
	        	   size = false;
	        	   
	           }
			   am.clear();
			   e.setCancelled(true);
			   e.getWhoClicked().closeInventory();
		   }
		   if(e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains("Invisibility")){
	           if(invis == false){
	        	   am.get(0).setVisible(true);
	        	   invis = true;
	           } else if(invis == true){
	        	   am.get(0).setVisible(false);
	        	   invis = false;
	           }
			   am.clear();
			   e.setCancelled(true);
			   e.getWhoClicked().closeInventory();
		   }
		   if(e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains("Gravity")){
	           if(gravity == false){
	        	   am.get(0).setGravity(false);
	        	   gravity = true;
	           } else if(gravity == true){
	        	   am.get(0).setGravity(true);
	        	   gravity = false;
	           }
			   am.clear();
			   e.setCancelled(true);
			   e.getWhoClicked().closeInventory();
		   }
		   if(e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains("Invulnerability")){
	           if(invul == false){
	        	   invul = true;
	           } else if(invul == true){
	        	   invul = false;
	           }
			   am.clear();
			   e.setCancelled(true);
			   e.getWhoClicked().closeInventory();
		   }
		   if(e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains("Rotation")){
			   e.getWhoClicked().openInventory(rotation());
			   e.setCancelled(true);
		   }
		   if(e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains("Item in Hand")){
			   am.get(0).setItemInHand(e.getCursor());
			   am.clear();
			   e.setCancelled(true);
			   e.getWhoClicked().closeInventory();
		   }
		   if(e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains("Armor")){
			   am.get(0).setHelmet(e.getCursor());
			   am.get(0).setChestplate(e.getCursor());
			   am.get(0).setLeggings(e.getCursor());
			   am.get(0).setBoots(e.getCursor());
			   am.clear();
			   e.setCancelled(true);
			   e.getWhoClicked().closeInventory();
		   }
		   if(e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains("Look at Player")){
			   if(look == false){
				   look = true;
			   Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
		            @Override
		            public void run() {
		            	if(look == true){
		            		try{
		            	 ((CraftArmorStand) am.get(0)).getHandle().setPositionRotation(am.get(0).getLocation().getX(), am.get(0).getLocation().getY(), am.get(0).getLocation().getZ(), e.getWhoClicked().getLocation().getYaw() - 180, e.getWhoClicked().getLocation().getPitch() - 180);
		            		} catch(Exception e){
		            			
		            		}
		            	}
		            }
		        }, 0, 1);	
			   } else if(look == true){
				  look = false;
				  am.clear();
	              Bukkit.getScheduler().cancelAllTasks();
			   }
			   e.getWhoClicked().closeInventory();
			   e.setCancelled(true);
		   }
		} else if(e.getInventory().equals(Rotation)){
			   if(e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains("Head")){
				   if(e.isLeftClick()){
				   headx++;
				   am.get(0).setHeadPose(new EulerAngle(headx, heady, headz));
				   } else if(e.isRightClick()){
					   headz++;
				   am.get(0).setHeadPose(new EulerAngle(headx, heady, headz));
				   } else if(e.isRightClick()){
					   heady++;
				   am.get(0).setHeadPose(new EulerAngle(headx, heady, headz));
				   }
				   e.setCancelled(true);
				   am.clear();
				   e.getWhoClicked().closeInventory();
			   } else if(e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains("Body")){
				   if(e.isLeftClick()){
					   bodyx++;
				   am.get(0).setBodyPose(new EulerAngle(bodyx, bodyy, bodyz));
				   } else if(e.isRightClick()){
					   bodyz++;
				   am.get(0).setBodyPose(new EulerAngle(bodyx, bodyy, bodyz));
				   } else if(e.isRightClick()){
					   bodyy++;
				   am.get(0).setBodyPose(new EulerAngle(bodyx, bodyy, bodyz));
				   }
				   e.setCancelled(true);
				   am.clear();
				   e.getWhoClicked().closeInventory();
			   } else if(e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains("Arms")){
				   if(e.isLeftClick()){
					   armsx++;
					   am.get(0).setRightArmPose(new EulerAngle(armsx, armsy, armsz));
					   am.get(0).setLeftArmPose(new EulerAngle(armsx, armsy, armsz));
				   } else if(e.isRightClick()){
					   armsz++;
					   am.get(0).setRightArmPose(new EulerAngle(armsx, armsy, armsz));
					   am.get(0).setLeftArmPose(new EulerAngle(armsx, armsy, armsz));
				   } else if(e.isRightClick()){
					   armsy++;
				   am.get(0).setRightArmPose(new EulerAngle(armsx, armsy, armsz));
				   am.get(0).setLeftArmPose(new EulerAngle(armsx, armsy, armsz));
				   }
				   e.setCancelled(true);
				   am.clear();
				   e.getWhoClicked().closeInventory();
			   } else if(e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains("Legs")){
				   if(e.isLeftClick()){
					   legsx++;
					   am.get(0).setRightLegPose(new EulerAngle(legsx, legsy, legsz));
					   am.get(0).setLeftLegPose(new EulerAngle(legsx, legsy, legsz));
				   } else if(e.isRightClick()){
					   legsz++;
					   am.get(0).setRightLegPose(new EulerAngle(legsx, legsy, legsz));
					   am.get(0).setLeftLegPose(new EulerAngle(legsx, legsy, legsz));
				   } else if(e.isRightClick()){
					   legsy++;
					   am.get(0).setRightLegPose(new EulerAngle(legsx, legsy, legsz));
					   am.get(0).setLeftLegPose(new EulerAngle(legsx, legsy, legsz));
				   }
				   e.setCancelled(true);
				   am.clear();
				   e.getWhoClicked().closeInventory();
			   }
		}
		}
	
	@EventHandler
	public void onArmorStandDamage(EntityDamageEvent e){
		if(e.getEntity() instanceof CraftArmorStand){
			if(invul == true){
				e.setCancelled(true);
			} else{
				e.setCancelled(false);
			}
		}
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e){
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onArmorStandClick(PlayerInteractAtEntityEvent e){
		if(e.getRightClicked() instanceof CraftArmorStand){
			e.getPlayer().openInventory(armorStand(e.getPlayer()));
			am.add((CraftArmorStand) e.getRightClicked());
			e.setCancelled(true);
		}
	}
	
}
