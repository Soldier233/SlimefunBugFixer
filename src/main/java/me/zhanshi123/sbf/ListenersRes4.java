package me.zhanshi123.sbf;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.containers.Flags;
import com.bekvon.bukkit.residence.containers.lm;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;
import com.bekvon.bukkit.residence.protection.ResidenceManager;
import com.bekvon.bukkit.residence.protection.ResidencePermissions;

/**
 * 
 * @author byxiaobai,Soldier
 *  
 */
public class ListenersRes4 implements Listener{
	
	private static final ResidenceManager RESIDENCE_MAMAGER = Main.getResidenceManager();

	private static class LocationUtil {
		private static Location[] getPlotAllLocations(Location location) {
			double locationY = location.getY();

			double locationX = location.getX();
			double locationZ = location.getZ();

			double[] allLocationX = new double[3];
			allLocationX[0] = (locationX + 1.0D);
			allLocationX[1] = locationX;
			allLocationX[2] = (locationX - 1.0D);

			double[] allLocationZ = new double[3];
			allLocationZ[0] = (locationZ + 1.0D);
			allLocationZ[1] = locationZ;
			allLocationZ[2] = (locationZ - 1.0D);

			Location[] locations = new Location[9];

			World world = location.getWorld();
			byte number = 0;
			double[] arrayOfDouble1;
			int j = (arrayOfDouble1 = allLocationX).length;
			for (int i = 0; i < j; i++) {
				double FOR_locationX = arrayOfDouble1[i];
				double[] arrayOfDouble2;
				int m = (arrayOfDouble2 = allLocationZ).length;
				for (int k = 0; k < m; k++) {
					double FOR_locationZ = arrayOfDouble2[k];
					locations[number] = new Location(world, FOR_locationX, locationY, FOR_locationZ);
					number = (byte) (number + 1);
				}
			}
			return locations;
		}

		public static Location[][] getExplosivePickaxeBreakLocations(Location location) {
			Location[][] allLocation = new Location[3][9];

			double loactionY = location.getY();
			location.setY(loactionY - 1.0D);
			allLocation[0] = getPlotAllLocations(location);
			location.setY(loactionY);
			allLocation[1] = getPlotAllLocations(location);
			location.setY(loactionY + 1.0D);
			allLocation[2] = getPlotAllLocations(location);
			return allLocation;
		}
	}

	private String ITEM_NAME=ConfigManager.getInstance().getExplosivePickaxe();
	private Residence RESIDENCE = Residence.getInstance();

	@EventHandler(priority = EventPriority.LOWEST)
	public void onBlockBreak(BlockBreakEvent evt) {
		Player player = evt.getPlayer();
		if (player == null) {
			return;
		}
		ItemStack itemStack = player.getItemInHand();
		if (itemStack.getType() != Material.DIAMOND_PICKAXE) {
			return;
		}
		ItemMeta itemMeta = itemStack.getItemMeta();
		if (itemMeta == null) {
			return;
		}
		String displayName = itemMeta.getDisplayName();
		if (displayName == null) {
			return;
		}
		if (!displayName.contains(ITEM_NAME)) {
			return;
		}
		Block block = evt.getBlock();
		if (block == null) {
			return;
		}
		Location location = block.getLocation();
		String playerName = player.getName();
		Location[][] arrayOfLocation;
		int j = (arrayOfLocation = LocationUtil.getExplosivePickaxeBreakLocations(location)).length;
		for (int i = 0; i < j; i++) {
			Location[] locations = arrayOfLocation[i];
			Location[] arrayOfLocation1;
			int m = (arrayOfLocation1 = locations).length;
			for (int k = 0; k < m; k++) {
				Location FOR_location = arrayOfLocation1[k];
				ClaimedResidence claimedResidence = RESIDENCE_MAMAGER.getByLoc(FOR_location);
				if (claimedResidence != null) {
					ResidencePermissions perms = claimedResidence.getPermissions();
					boolean hasdestroy = perms.playerHas(playerName, Flags.destroy, true);
					if ((!hasdestroy) && (!player.hasPermission("residence.bypass.destroy"))) {
						this.RESIDENCE.msg(player, lm.Flag_Deny, new Object[] { Flags.destroy });
						evt.setCancelled(true);
						return;
					}
				}
			}
		}
	}
}
