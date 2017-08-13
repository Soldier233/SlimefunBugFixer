package me.zhanshi123.sbf;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
	private static Main instance=null;
	public static Main getInstance()
	{
		return instance;
	}
	public void onEnable()
	{
		instance=this;
		new Messages();
		Bukkit.getPluginManager().registerEvents(new Listeners(), instance);
		new Metrics(instance);
		Bukkit.getConsoleSender().sendMessage("§6§lSlimefunBugFixer §7>>> §a§l插件成功加载");
	}
	
}
