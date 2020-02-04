package tk.itsnotalan.swkilleffect.effects;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public abstract class KillEffect {

	protected String name;
	protected String id;
	protected Material icon;

	public KillEffect(String name, String id, Material icon) {
		this.name = name;
		this.id = id;
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public Material getIcon() {
		return icon;
	}

	public abstract void play(Player target);

}
