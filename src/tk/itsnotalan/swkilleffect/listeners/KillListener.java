package tk.itsnotalan.swkilleffect.listeners;

import tk.itsnotalan.swkilleffect.SWKillEffect;
import tk.itsnotalan.swkilleffect.effects.KillEffect;
import tk.itsnotalan.swkilleffect.manager.KillEffectManager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class KillListener implements Listener {

	private SWKillEffect plugin;
	private String nmsVersion;

	public KillListener(SWKillEffect plugin) {
		this.plugin = plugin;
		this.nmsVersion = plugin.getNmsVersion();
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Player p = event.getEntity();
		KillEffectManager killEffectManager = plugin.getKillEffectManager();
		if (p.getKiller() != null) {
			Player k = p.getKiller();
			KillEffect killEffect = killEffectManager.getKillEffect(k);
			if (killEffect != null) {
				killEffect.play(p);
			}
			if (killEffectManager.isKillSoundEnabled()) {
				k.playSound(k.getLocation(), killEffectManager.getKillSound(), 1, killEffectManager.getKillSoundPitch());
			}
		}
		if (killEffectManager.isAutoRespawnEnabled()) {
			Bukkit.getScheduler().runTask(
					plugin,
					() -> {
						switch (nmsVersion) {
						case "v1_8_R3":
							((org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer) p).getHandle().playerConnection
									.a(new net.minecraft.server.v1_8_R3.PacketPlayInClientCommand(
											net.minecraft.server.v1_8_R3.PacketPlayInClientCommand.EnumClientCommand.PERFORM_RESPAWN));
							break;
						default:
							break;
						}
					});
		}
	}

}
