package tk.itsnotalan.swkilleffect.effects.v1_8_R3;

import tk.itsnotalan.swkilleffect.SWKillEffect;
import tk.itsnotalan.swkilleffect.effects.KillEffect;
import tk.itsnotalan.swkilleffect.utils.ConfigUtil;
import tk.itsnotalan.swkilleffect.utils.ParticleEffect;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.MathHelper;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityEquipment;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityHeadRotation;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_8_R3.World;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class KillEffectFinalSmash extends KillEffect {

	public KillEffectFinalSmash() {
		super("Final Smash", "FINAL_SMASH", Material.ARMOR_STAND);
	}

	@Override
	public void play(Player p) {
		Location loc = p.getLocation();
		World world = ((CraftWorld) loc.getWorld()).getHandle();
		EntityArmorStand entityArmorStand = new EntityArmorStand(world);
		entityArmorStand.setLocation(loc.getX(), loc.getY(), loc.getZ(),
				MathHelper.d((entityArmorStand.pitch * 256F) / 360F),
				MathHelper.d((entityArmorStand.yaw * 256F) / 360F));
		entityArmorStand.setInvisible(false);
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
		skullMeta.setOwner(p.getName());
		skull.setItemMeta(skullMeta);
		ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		net.minecraft.server.v1_8_R3.ItemStack nmsSkull = CraftItemStack.asNMSCopy(skull);
		net.minecraft.server.v1_8_R3.ItemStack nmsChest = CraftItemStack.asNMSCopy(chest);
		net.minecraft.server.v1_8_R3.ItemStack nmsLeg = CraftItemStack.asNMSCopy(leg);
		net.minecraft.server.v1_8_R3.ItemStack nmsBoots = CraftItemStack.asNMSCopy(boots);
		PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(entityArmorStand);
		PacketPlayOutEntityEquipment packetEquipSkull = new PacketPlayOutEntityEquipment(entityArmorStand.getId(), 4,
				nmsSkull);
		PacketPlayOutEntityEquipment packetEquipChest = new PacketPlayOutEntityEquipment(entityArmorStand.getId(), 3,
				nmsChest);
		PacketPlayOutEntityEquipment packetEquipLeg = new PacketPlayOutEntityEquipment(entityArmorStand.getId(), 2,
				nmsLeg);
		PacketPlayOutEntityEquipment packetEquipBoots = new PacketPlayOutEntityEquipment(entityArmorStand.getId(), 1,
				nmsBoots);
		for (Player all : Bukkit.getOnlinePlayers()) {
			((CraftPlayer) all).getHandle().playerConnection.sendPacket(packet);
			((CraftPlayer) all).getHandle().playerConnection.sendPacket(packetEquipSkull);
			((CraftPlayer) all).getHandle().playerConnection.sendPacket(packetEquipChest);
			((CraftPlayer) all).getHandle().playerConnection.sendPacket(packetEquipLeg);
			((CraftPlayer) all).getHandle().playerConnection.sendPacket(packetEquipBoots);
		}
		BukkitRunnable runnable = new BukkitRunnable() {
			int i = ConfigUtil.FINAL_SMASH_DURATION;
			double addX = 0.5d;
			double addZ = 0.5d;
			Location lastPos = new Location(loc.getWorld(), entityArmorStand.locX, entityArmorStand.locY, entityArmorStand.locZ);

			@Override
			public void run() {
				if (i == ConfigUtil.FINAL_SMASH_DURATION || i == ConfigUtil.FINAL_SMASH_DURATION_TURN) {
					double randomDouble1 = Math.random();
					if (randomDouble1 < 0.5d) {
						addX = 0.5d;
					}
					if (randomDouble1 >= 0.5d) {
						addX = -0.5d;
					}
					double randomDouble2 = Math.random();
					if (randomDouble2 < 0.5d) {
						addZ = 0.5d;
					}
					if (randomDouble2 >= 0.5d) {
						addZ = -0.5d;
					}
				}
				if (i > 0) {
					entityArmorStand.locY += ConfigUtil.FINAL_SMASH_ADD_Y;
					entityArmorStand.locX += addX;
					entityArmorStand.locZ += addZ;
					Location pos = new Location(loc.getWorld(), entityArmorStand.locX, entityArmorStand.locY, entityArmorStand.locZ);
					if (pos.getBlock().getType() == Material.AIR) {
						PacketPlayOutEntityHeadRotation packetPlayOutEntityHeadRotation = new PacketPlayOutEntityHeadRotation(
								entityArmorStand,
								(byte) MathHelper.floor(((entityArmorStand.yaw += ConfigUtil.HEAD_ROCKET_ADD_YAW) * 256.0F) / 360.0F));
						PacketPlayOutEntityTeleport packetPlayOutEntityTeleport = new PacketPlayOutEntityTeleport(entityArmorStand);
						for (Player all : Bukkit.getOnlinePlayers()) {
							((CraftPlayer) all).getHandle().playerConnection.sendPacket(packetPlayOutEntityTeleport);
							((CraftPlayer) all).getHandle().playerConnection.sendPacket(packetPlayOutEntityHeadRotation);
						}
						ParticleEffect.CLOUD.display(0, 0, 0, 0, 1, pos, 256f);
						lastPos = pos;
						i--;
					} else {
						i = 0;
					}
					if (i == 0) {
						PacketPlayOutEntityDestroy packetPlayOutEntityDestroy = new PacketPlayOutEntityDestroy(entityArmorStand.getId());
						for (Player all : Bukkit.getOnlinePlayers()) {
							((CraftPlayer) all).getHandle().playerConnection.sendPacket(packetPlayOutEntityDestroy);
						}
						Firework fw = (Firework) lastPos.getWorld().spawn(lastPos, Firework.class);
						FireworkMeta fm = fw.getFireworkMeta();
						fm.addEffect(FireworkEffect.builder().flicker(true).trail(true).with(Type.BALL_LARGE).withColor(Color.RED)
								.withFade(Color.RED).build());
						fw.setFireworkMeta(fm);
						Bukkit.getScheduler().runTaskLater(SWKillEffect.getInstance(), () -> {
							fw.detonate();
						}, 1L);
						cancel();
					}
				}
			}

		};
		runnable.runTaskTimer(SWKillEffect.getInstance(), 1, 1);
	}
}
