package me.zhanshi123.sbf;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Messages
{
	private static Messages message=null;
	Main plugin=null;
	FileConfiguration config=new YamlConfiguration();
	public Messages()
	{
		plugin=Main.getInstance();
		File f=new File(Main.getInstance().getDataFolder(),"messages.yml");
		if(!f.exists())
		{
			plugin.saveResource("messages.yml", false);
		}
		
		try 
		{
			config.load(new InputStreamReader(new FileInputStream(f),"UTF-8"));
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		message=this;
	}
	public static Messages getMessages()
	{
		return message;
	}
	public String getNoDrop()
	{
		return config.getString("NoDrop");
	}
	public String getNoPlace()
	{
		return config.getString("NoPlace");
	}
}
