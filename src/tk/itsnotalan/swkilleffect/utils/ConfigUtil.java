package tk.itsnotalan.swkilleffect.utils;

import tk.itsnotalan.swkilleffect.SWKillEffect;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class ConfigUtil {

	public static boolean ENABLE_AUTORESPAWN;
	public static boolean ENABLE_KILLSOUND;
	public static Sound KILLSOUND;
	public static float KILLSOUND_PITCH;
	public static float HEART_EXPLOSION_OFFSET_X;
	public static float HEART_EXPLOSION_OFFSET_Y;
	public static float HEART_EXPLOSION_OFFSET_Z;
	public static float HEART_EXPLOSION_ADD_X;
	public static float HEART_EXPLOSION_ADD_Y;
	public static float HEART_EXPLOSION_ADD_Z;
	public static float HEART_EXPLOSION_SPEED;
	public static float HEAD_ROCKET_ADD_YAW;
	public static float HEAD_ROCKET_ADD_Y;
	public static int HEAD_ROCKET_DURATION;
	public static float FINAL_SMASH_ADD_YAW;
	public static float FINAL_SMASH_ADD_Y;
	public static int FINAL_SMASH_DURATION;
	public static int FINAL_SMASH_DURATION_TURN;

	public static void init(SWKillEffect plugin) {
		try {
			ENABLE_AUTORESPAWN = plugin.getConfig().getBoolean("enable-autorespawn");
			ENABLE_KILLSOUND = plugin.getConfig().getBoolean("enable-killsound");
			KILLSOUND = Sound.valueOf(plugin.getConfig().getString("killsound"));
			KILLSOUND_PITCH = (float) plugin.getConfig().getDouble("killsound-pitch");
			HEART_EXPLOSION_OFFSET_X = (float) plugin.getConfig().getDouble("HEART_EXPLOSION.offset-x");
			HEART_EXPLOSION_OFFSET_Y = (float) plugin.getConfig().getDouble("HEART_EXPLOSION.offset-y");
			HEART_EXPLOSION_OFFSET_Z = (float) plugin.getConfig().getDouble("HEART_EXPLOSION.offset-z");
			HEART_EXPLOSION_ADD_X = (float) plugin.getConfig().getDouble("HEART_EXPLOSION.add-x");
			HEART_EXPLOSION_ADD_Y = (float) plugin.getConfig().getDouble("HEART_EXPLOSION.add-y");
			HEART_EXPLOSION_ADD_Z = (float) plugin.getConfig().getDouble("HEART_EXPLOSION.add-z");
			HEART_EXPLOSION_SPEED = (float) plugin.getConfig().getDouble("HEART_EXPLOSION.speed");
			HEAD_ROCKET_ADD_YAW = (float) plugin.getConfig().getDouble("HEAD_ROCKET.add-yaw");
			HEAD_ROCKET_ADD_Y = (float) plugin.getConfig().getDouble("HEAD_ROCKET.add-y");
			HEAD_ROCKET_DURATION = plugin.getConfig().getInt("HEAD_ROCKET.duration");
			FINAL_SMASH_ADD_YAW = (float) plugin.getConfig().getDouble("FINAL_SMASH.add-yaw");
			FINAL_SMASH_ADD_Y = (float) plugin.getConfig().getDouble("FINAL_SMASH.add-y");
			FINAL_SMASH_DURATION = plugin.getConfig().getInt("FINAL_SMASH.duration");
			FINAL_SMASH_DURATION_TURN = plugin.getConfig().getInt("FINAL_SMASH.duration-turn");
		} catch (Exception e) {
			for (Player all : Bukkit.getOnlinePlayers()) {
				if (all.isOp()) {
					all.sendMessage("\nconfig.yml の読み込み中にエラーが発生しました (" + e.getLocalizedMessage() + ")");
					all.sendMessage("コンソールを見て、 config.yml を修正してください\n");
				}
			}
			e.printStackTrace();
		}
	}

}
