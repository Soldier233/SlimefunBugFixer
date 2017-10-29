package me.zhanshi123.sbf;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.protection.ResidenceManager;

import me.zhanshi123.sbf.metrics.Metrics;

public class Main extends JavaPlugin
{
	private static ResidenceManager RESIDENCE_MAMAGER=null;
	private static Main instance=null;
	public static Main getInstance()
	{
		return instance;
	}
    public static ResidenceManager getResidenceManager() {
        return RESIDENCE_MAMAGER;
    }
	public void onEnable()
	{
		instance=this;
		new Messages();
		Bukkit.getPluginManager().registerEvents(new Listeners(), instance);
		new Metrics(instance);
		new ConfigManager();
		new BackpackCooldown();
		new InteractCooldown();
		Plugin sf=Bukkit.getPluginManager().getPlugin("Slimefun");
		if(sf==null){
			setEnabled(false);
			Bukkit.getConsoleSender().sendMessage("§6§lSlimefunBugFixer §7>>> §c§l未找到Slimefun，停止加载");
			return;
		}
		else{
			if(sf.getDescription().getVersion().startsWith("4.")){
				Bukkit.getPluginManager().registerEvents(new ListenersV4(), instance);
			}
		}
		Plugin res=Bukkit.getPluginManager().getPlugin("Residence");
		if(res!=null){
			if(res.getDescription().getVersion().startsWith("4")){
				RESIDENCE_MAMAGER = Residence.getInstance().getResidenceManager();
				Bukkit.getPluginManager().registerEvents(new ListenersRes4(), this);
			}
		}
		if(RESIDENCE_MAMAGER==null){
			Bukkit.getConsoleSender().sendMessage("§6§lSlimefunBugFixer §7>>> §c§l未找到ResideceV4.x版本，爆炸镐修复无法启用!");
		}
		Bukkit.getConsoleSender().sendMessage("§6§lSlimefunBugFixer §7>>> §a§l插件成功加载");
	}
	
}
