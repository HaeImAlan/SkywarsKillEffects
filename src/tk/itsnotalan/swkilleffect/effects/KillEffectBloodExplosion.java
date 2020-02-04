package tk.itsnotalan.swkilleffect.effects;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class KillEffectBloodExplosion extends KillEffect {

	public KillEffectBloodExplosion() {
		super("Blood Explosion", "BLOOD_EXPLOSION", Material.REDSTONE);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void play(Player p) {
		Location loc = p.getLocation();
		for (Player all : Bukkit.getOnlinePlayers()) {
			all.playEffect(loc.clone().add(0, 0.5, 0), Effect.STEP_SOUND, 152);
			all.playEffect(loc.clone().add(0, 1, 0), Effect.STEP_SOUND, 152);
		}
	}

}
