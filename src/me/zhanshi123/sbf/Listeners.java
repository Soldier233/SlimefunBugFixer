package me.zhanshi123.sbf;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class Listeners implements Listener
{
	@EventHandler
	public void onPlace(BlockPlaceEvent e)
	{
		if(e.isCancelled())
		{
			return;
		}
		if(!e.getBlock().getType().equals(Material.CAULDRON))
		{
			return;
		}
		Block b=e.getBlock();
		Location loc=b.getLocation();
		loc.setY(loc.getY()+1);
		Block upon=loc.getBlock();
		if(upon.getType().equals(Material.AIR))
		{
			return;
		}
		e.setCancelled(true);
		e.getPlayer().sendMessage(Messages.getMessages().getNoPlace().replace("&", "¡ì"));
	}
	
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e)
	{
		if(e.isCancelled())
		{
			return;
		}
		Player p=e.getPlayer();
		if(!p.getOpenInventory().getTitle().contains("Backpack"))
		{
			return;
		}
		e.setCancelled(true);
		p.sendMessage(Messages.getMessages().getNoDrop().replace("&", "¡ì"));
	}
}
