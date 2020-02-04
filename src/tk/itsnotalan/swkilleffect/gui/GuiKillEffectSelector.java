package tk.itsnotalan.swkilleffect.gui;

import tk.itsnotalan.swkilleffect.manager.KillEffectManager;
import tk.itsnotalan.swkilleffect.utils.CustomItem;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GuiKillEffectSelector implements Listener {

	private KillEffectManager killEffectManager;

	public GuiKillEffectSelector(KillEffectManager killEffectManager) {
		this.killEffectManager = killEffectManager;
	}

	public void open(Player p) {
		Inventory inventory = Bukkit.createInventory(null, 54, "Kill Effects");
		ItemStack NONE = new CustomItem(Material.BARRIER, 1, "§aNONE",
				"§7Selecting this option",
				"§7disables your Kill Effect.",
				"",
				(killEffectManager.hasSelectedOne(p) ? "§eClick to select!" : "§aSELECTED!"));
		ItemStack BLOOD_EXPLOSION = new CustomItem(killEffectManager.getKillEffectById("BLOOD_EXPLOSION").getIcon(), 1,
				"§aBlood Explosion Kill Effect",
				"§7Select the Blood Explosion",
				"§7Kill Effect. This change is",
				"§7cosmetic.",
				"",
				(!killEffectManager.hasSelected(p, "BLOOD_EXPLOSION") ? "§eClick to select!" : "§aSELECTED!"));
		ItemStack HEART_EXPLOSION = new CustomItem(killEffectManager.getKillEffectById("HEART_EXPLOSION").getIcon(), 1,
				"§aHeart Explosion Kill Effect",
				"§7Select the Heart Explosion",
				"§7Kill Effect. This change is",
				"§7cosmetic.",
				"",
				(!killEffectManager.hasSelected(p, "HEART_EXPLOSION") ? "§eClick to select!" : "§aSELECTED!"));
		ItemStack HEAD_ROCKET = new CustomItem(killEffectManager.getKillEffectById("HEAD_ROCKET").getIcon(), 1,
				"§aHead Rocket Kill Effect",
				"§7Select the Head Rocket",
				"§7Kill Effect. This change is",
				"§7cosmetic.",
				"",
				(!killEffectManager.hasSelected(p, "HEAD_ROCKET") ? "§eClick to select!" : "§aSELECTED!"));
		ItemStack FINAL_SMASH = new CustomItem(killEffectManager.getKillEffectById("FINAL_SMASH").getIcon(), 1,
				"§aFinal Smash Kill Effect",
				"§7Select the Final Smash",
				"§7Kill Effect. This change is",
				"§7cosmetic.",
				"",
				(!killEffectManager.hasSelected(p, "FINAL_SMASH") ? "§eClick to select!" : "§aSELECTED!"));
		ItemStack close = new CustomItem(Material.BARRIER, 1, "§cClose");
		// ItemStack comingsoon = new CustomItem(Material.INK_SACK, 1, "§cComing soon!", 8);
		inventory.setItem(11, NONE);
		inventory.setItem(12, BLOOD_EXPLOSION);
		inventory.setItem(13, HEART_EXPLOSION);
		inventory.setItem(14, HEAD_ROCKET);
		inventory.setItem(15, FINAL_SMASH);
		inventory.setItem(49, close);
		p.openInventory(inventory);
	}

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		Player p = (Player) event.getWhoClicked();
		if (p.getOpenInventory().getTopInventory() != null) {
			if (p.getOpenInventory().getTopInventory().getName().equalsIgnoreCase("Kill Effects")) {
				event.setCancelled(true);
				if (event.getSlotType().equals(SlotType.OUTSIDE) || event.getCurrentItem().getType().equals(Material.AIR)) {
					return;
				}
				if (event.getClickedInventory().equals(p.getOpenInventory().getTopInventory())) {
					event.setCancelled(true);
					if (event.getSlot() == 49) {
						p.closeInventory();
					}
					if (event.getSlot() == 11) {
						if (killEffectManager.hasSelectedOne(p)) {
							killEffectManager.setKillEffect(p, "NONE");
							open(p);
							p.playSound(p.getLocation(), Sound.NOTE_PLING, 1f, 2f);
							p.sendMessage("§6You selected §aNONE§6!");
						} else {
							p.sendMessage("§cYou have already selected this item!");
							p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1f, 0.5f);
						}
					}
					if (event.getSlot() == 12) {
						if (!killEffectManager.hasSelected(p, "BLOOD_EXPLOSION")) {
							killEffectManager.setKillEffect(p, "BLOOD_EXPLOSION");
							open(p);
							p.playSound(p.getLocation(), Sound.NOTE_PLING, 1f, 2f);
							p.sendMessage("§6You selected §aBlood Explosion Kill Effect§6!");
						} else {
							p.sendMessage("§cYou have already selected this item!");
							p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1f, 0.5f);
						}
					}
					if (event.getSlot() == 13) {
						if (!killEffectManager.hasSelected(p, "HEART_EXPLOSION")) {
							killEffectManager.setKillEffect(p, "HEART_EXPLOSION");
							open(p);
							p.playSound(p.getLocation(), Sound.NOTE_PLING, 1f, 2f);
							p.sendMessage("§6You selected §aHeart Explosion Kill Effect§6!");
						} else {
							p.sendMessage("§cYou have already selected this item!");
							p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1f, 0.5f);
						}
					}
					if (event.getSlot() == 14) {
						if (!killEffectManager.hasSelected(p, "HEAD_ROCKET")) {
							killEffectManager.setKillEffect(p, "HEAD_ROCKET");
							open(p);
							p.playSound(p.getLocation(), Sound.NOTE_PLING, 1f, 2f);
							p.sendMessage("§6You selected §aHead Rocket Kill Effect§6!");
						} else {
							p.sendMessage("§cYou have already selected this item!");
							p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1f, 0.5f);
						}
					}
					if (event.getSlot() == 15) {
						if (!killEffectManager.hasSelected(p, "FINAL_SMASH")) {
							killEffectManager.setKillEffect(p, "FINAL_SMASH");
							open(p);
							p.playSound(p.getLocation(), Sound.NOTE_PLING, 1f, 2f);
							p.sendMessage("§6You selected §aFinal Smash Kill Effect§6!");
						} else {
							p.sendMessage("§cYou have already selected this item!");
							p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1f, 0.5f);
						}
					}
				}
			}
		}
	}

}
