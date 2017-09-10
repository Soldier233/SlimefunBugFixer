package me.zhanshi123.sbf;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Listeners implements Listener
{
	@EventHandler(priority=EventPriority.LOWEST)
	public void onClick(PlayerInteractEvent e)
	{
		Action act=e.getAction();
		if(!(act.equals(Action.RIGHT_CLICK_AIR)||act.equals(Action.RIGHT_CLICK_BLOCK)))
			return;
		ItemStack item=e.getPlayer().getItemInHand();
		if(item==null)
			return;
		if(!item.hasItemMeta())
			return;
		if(!item.getItemMeta().getDisplayName().contains(ConfigManager.getInstance().getBackpackName()))
			return;
		Player p=e.getPlayer();
		String name=p.getName();
		if(CooldownManager.getInstance().isReady(name,500))
		{
			CooldownManager.getInstance().put(name);
		}
		else
		{
			e.setCancelled(true);
			p.sendMessage(Messages.getMessages().getNoQuickOpen().replace("&", "§"));
		}
	}
	
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
		e.getPlayer().sendMessage(Messages.getMessages().getNoPlace().replace("&", "§"));
	}
	
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e)
	{
		if(e.isCancelled())
		{
			return;
		}
		Player p=e.getPlayer();
		if(!p.getOpenInventory().getTitle().contains(ConfigManager.getInstance().getBackpackTitle()))
		{
			return;
		}
		e.setCancelled(true);
		p.sendMessage(Messages.getMessages().getNoDrop().replace("&", "§"));
	}
}
