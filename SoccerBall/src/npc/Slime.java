package npc;

import java.lang.reflect.Field;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftSlime;
import org.bukkit.craftbukkit.v1_8_R1.util.UnsafeList;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.util.Vector;

import net.minecraft.server.v1_8_R1.EntitySlime;
import net.minecraft.server.v1_8_R1.PathfinderGoalSelector;
import net.minecraft.server.v1_8_R1.World;

public class Slime extends EntitySlime{

	public Slime(World world) {
		super(world);	
		try {
			Field bField = PathfinderGoalSelector.class.getDeclaredField("b");
			bField.setAccessible(true);
			Field cField = PathfinderGoalSelector.class.getDeclaredField("c");
			cField.setAccessible(true);
			bField.set(goalSelector, new UnsafeList<PathfinderGoalSelector>());
			bField.set(targetSelector, new UnsafeList<PathfinderGoalSelector>());
			cField.set(goalSelector, new UnsafeList<PathfinderGoalSelector>());
			cField.set(targetSelector, new UnsafeList<PathfinderGoalSelector>());
			} catch (Exception exc) {
			exc.printStackTrace();
			}
	}
	
	@Override
	public String z() {
		return "";
	}
	
	@Override
	public String bo() {
		return "";
	}
	
	@Override
	public void g(double x, double y, double z) {
		Vector vector = this.getBukkitEntity().getVelocity();
		super.g(vector.getX(), vector.getY(), vector.getZ());
	}

	public static org.bukkit.entity.Slime spawn(Location location) {
		World mcWorld = (World) ((CraftWorld) location.getWorld()).getHandle();
		final Slime customEntity = new Slime(
				mcWorld);
		customEntity.setLocation(location.getX(), location.getY(),
				location.getZ(), location.getYaw(), location.getPitch());
		((CraftLivingEntity) customEntity.getBukkitEntity())
				.setRemoveWhenFarAway(false);
		mcWorld.addEntity(customEntity, SpawnReason.CUSTOM);
		return (CraftSlime) customEntity.getBukkitEntity();
	}
}