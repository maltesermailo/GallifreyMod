package de.maltesermailo.gallifrey.handlers;

import de.maltesermailo.gallifrey.client.gui.EyeofHarmonyScreen;
import de.maltesermailo.gallifrey.inventory.container.EyeofHarmonyContainer;
import de.maltesermailo.gallifrey.tileentities.TileEyeofHarmony;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GallifreyModGuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new EyeofHarmonyContainer(player.inventory, (TileEyeofHarmony) world.getTileEntity(new BlockPos(x, y, z)));
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new EyeofHarmonyScreen(new EyeofHarmonyContainer(player.inventory, (TileEyeofHarmony) world.getTileEntity(new BlockPos(x, y, z))));
	}

}
