package tk.itsnotalan.swkilleffect;

import java.io.File;
import java.io.IOException;

import tk.itsnotalan.swkilleffect.commands.CommandSWKillEffect;
import tk.itsnotalan.swkilleffect.gui.GuiKillEffectSelector;
import tk.itsnotalan.swkilleffect.listeners.KillListener;
import tk.itsnotalan.swkilleffect.manager.KillEffectManager;
import tk.itsnotalan.swkilleffect.utils.ConfigUtil;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class SWKillEffect extends JavaPlugin {

	private static SWKillEffect instance;
	private KillEffectManager killEffectManager;
	private FileConfiguration dataYml;
	private GuiKillEffectSelector guiKillEffectSelector;
	private String nmsVersion;

	@Override
	public void onEnable() {
		instance = this;
		loadVersion();
		saveDefaultConfig();
		ConfigUtil.init(this);
		loadDataYml();
		killEffectManager = new KillEffectManager(this, nmsVersion);
		Bukkit.getPluginManager().registerEvents(new KillListener(this), this);
		Bukkit.getPluginManager().registerEvents(guiKillEffectSelector = new GuiKillEffectSelector(killEffectManager), this);
		getCommand("swkilleffect").setExecutor(new CommandSWKillEffect(this));
	}

	@Override
	public void onDisable() {
		killEffectManager.saveKillEffects();
	}

	public void loadVersion() {
		nmsVersion = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
	}

	public void loadDataYml() {
		saveResource("data.yml", false);
		File dataYmlFile = new File(getDataFolder() + "/data.yml");
		if (!dataYmlFile.exists()) {
			try {
				dataYmlFile.createNewFile();
			} catch (IOException e) {
				log("There was an error loading the §cdata.yml");
				e.printStackTrace();
				return;
			}
		}
		dataYml = YamlConfiguration.loadConfiguration(dataYmlFile);
		log("§adata.yml Loaded");
	}

	public void saveDataYml() {
		File dataYmlFile = new File(getDataFolder() + "/data.yml");
		if (dataYmlFile.exists()) {
			try {
				dataYml.save(dataYmlFile);
			} catch (IOException e) {
				log("An error occurred while saving §cdata.yml");
				e.printStackTrace();
			}
		}
		log(" Saved §adata.yml");
	}

	public FileConfiguration getDataYml() {
		return dataYml;
	}

	public static SWKillEffect getInstance() {
		return instance;
	}

	public static void log(String text) {
		Bukkit.getConsoleSender().sendMessage(text);
	}

	public KillEffectManager getKillEffectManager() {
		return killEffectManager;
	}

	public GuiKillEffectSelector getGuiKillEffectSelector() {
		return guiKillEffectSelector;
	}

	public String getNmsVersion() {
		return nmsVersion;
	}

}
