package me.zhanshi123.sbf;


import java.util.HashMap;

import org.bukkit.block.Block;

public class InteractCooldown
{
	private static InteractCooldown instance;
	
	public static InteractCooldown getInstance()
	{
		return instance;
	}
	
	private HashMap<Block,Long> data=new HashMap<Block,Long>();
	
	public InteractCooldown()
	{
		instance=this;
	}
	
	public void put(Block block)
	{
		if(data.containsKey(block))
		{
			data.remove(block);
		}
		data.put(block, System.currentTimeMillis());
	}
	/**
	 * @author Soldier
	 * 
	 * @param time 需要冷却的时间，单位毫秒
	 * @param block 玩家名
	 * 
	 * @return 如果无需冷却，返回true<br>
	 *         仍在冷却返回false
	 */
	public boolean isReady(Block block,long time)
	{
		if(!data.containsKey(block))
		{
			return true;
		}
		long start=data.get(block);
		long now=System.currentTimeMillis();
		if(now-start>=time)
		{
			data.remove(block);
			return true;
		}
		else
		{
			return false;
		}
	}
}
