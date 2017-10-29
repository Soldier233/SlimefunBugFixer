package me.zhanshi123.sbf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigManager {
	private Main plugin;
	private FileConfiguration config = new YamlConfiguration();
	private static ConfigManager instance;

	public static ConfigManager getInstance() {
		return instance;
	}

	public ConfigManager() {
		plugin = Main.getInstance();
		File f = new File(plugin.getDataFolder(), "config.yml");
		if (!f.exists())
			plugin.saveDefaultConfig();
		try {
			config.load(new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		instance = this;
	}

	public String getBackpackTitle() {
		return config.getString("Backpack_title");
	}

	public String getBackpackName() {
		return config.getString("Backpack_item_displayname");
	}
	
	public String getExplosivePickaxe(){
		return config.getString("ExplosivePickaxe");
	}
}
