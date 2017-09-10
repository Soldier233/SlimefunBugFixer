package me.zhanshi123.sbf;


import java.util.HashMap;

public class CooldownManager
{
	private static CooldownManager instance;
	
	public static CooldownManager getInstance()
	{
		return instance;
	}
	
	private HashMap<String,Long> data=new HashMap<String,Long>();
	
	public CooldownManager()
	{
		instance=this;
	}
	
	public void put(String name)
	{
		if(data.containsKey(name))
		{
			data.remove(name);
		}
		data.put(name, System.currentTimeMillis());
	}
	/**
	 * @author Soldier
	 * 
	 * @param time 需要冷却的时间，单位毫秒
	 * @param name 玩家名
	 * 
	 * @return 如果无需冷却，返回true<br>
	 *         仍在冷却返回false
	 */
	public boolean isReady(String name,long time)
	{
		if(!data.containsKey(name))
		{
			return true;
		}
		long start=data.get(name);
		long now=System.currentTimeMillis();
		if(now-start>=time)
		{
			data.remove(name);
			return true;
		}
		else
		{
			return false;
		}
	}
}
