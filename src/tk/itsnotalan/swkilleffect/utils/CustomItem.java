package tk.itsnotalan.swkilleffect.utils;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

public class CustomItem extends ItemStack {

	private ItemStack customitem;

	public CustomItem(Material type, int amount, String name, List<String> lore) {
		ItemStack item = new ItemStack(type, amount);
		ItemMeta im = item.getItemMeta();
		if (name != null) {
			im.setDisplayName(name);
		}
		if (lore != null) {
			im.setLore(lore);
		}
		item.setItemMeta(im);
		customitem = item;
	}

	public CustomItem(Material type, int amount, String name, String... lore) {
		ItemStack item = new ItemStack(type, amount);
		ItemMeta im = item.getItemMeta();
		if (name != null) {
			im.setDisplayName(name);
		}
		if (lore != null) {
			im.setLore(Arrays.asList(lore));
		}
		item.setItemMeta(im);
		customitem = item;
		this.setType(type);
		this.setAmount(amount);
		this.setItemMeta(im);
	}

	public CustomItem(Material type, int amount, String name, int data, String... lore) {
		ItemStack item = new ItemStack(type, amount, (byte) data);
		ItemMeta im = item.getItemMeta();
		if (name != null) {
			im.setDisplayName(name);
		}
		if (lore != null) {
			im.setLore(Arrays.asList(lore));
		}
		item.setItemMeta(im);
		customitem = item;
		this.setType(type);
		this.setAmount(amount);
		this.setItemMeta(im);
		this.setDurability((byte) data);
	}

	public CustomItem(Material type, int amount, String name) {
		ItemStack item = new ItemStack(type, amount);
		ItemMeta im = item.getItemMeta();
		if (name != null) {
			im.setDisplayName(name);
		}
		item.setItemMeta(im);
		customitem = item;
		this.setType(type);
		this.setAmount(amount);
		this.setItemMeta(im);
	}

	public ItemStack getItemStack() {
		return customitem;
	}

	public void setDisplayName(String name) {
		ItemMeta im = this.getItemMeta();
		im.setDisplayName(name);
		this.setItemMeta(im);
		customitem.setItemMeta(im);
	}

	public void setLore(String... lore) {
		ItemMeta im = this.getItemMeta();
		im.setLore(Arrays.asList(lore));
		this.setItemMeta(im);
		customitem.setItemMeta(im);
	}

	@SuppressWarnings("deprecation")
	public void setData(int data) {
		MaterialData mdata = new MaterialData((byte) data);
		this.setData(mdata);
		customitem.setData(mdata);
	}

}
