package me.zhanshi123.sbf;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.mrCookieSlime.Slimefun.Events.MultiBlockInteractEvent;

public class ListenersV4 implements Listener{
	@EventHandler
	public void onInteract(MultiBlockInteractEvent e){
		if(e.isCancelled())
			return;
		InteractCooldown.getInstance().put(e.getClickedBlock());
	}
}
