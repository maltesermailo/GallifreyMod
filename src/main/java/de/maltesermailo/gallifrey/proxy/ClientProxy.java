package de.maltesermailo.gallifrey.proxy;

import java.util.HashMap;
import java.util.Map;

import de.maltesermailo.gallifrey.GalliyfreyMod;
import de.maltesermailo.gallifrey.blocks.BlockEyeOfHarmonyTop;
import de.maltesermailo.gallifrey.blocks.GBlocks;
import de.maltesermailo.gallifrey.client.models.ModelEyeofHarmony;
import de.maltesermailo.gallifrey.client.renderers.RenderEyeofHarmony;
import de.maltesermailo.gallifrey.client.renderers.RenderItem;
import de.maltesermailo.gallifrey.items.GItems;
import de.maltesermailo.gallifrey.tileentities.TileEyeofHarmony;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = GalliyfreyMod.MODID, value = Side.CLIENT)
public class ClientProxy implements IProxy {

	@Override
	public void register() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEyeofHarmony.class, new RenderEyeofHarmony());
		
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public void init() {
		Item.getItemFromBlock(GBlocks.eyeOfHarmonyBlock).setTileEntityItemStackRenderer(new RenderItem(new ModelEyeofHarmony(), new ResourceLocation(GalliyfreyMod.MODID, "textures/tile/eyeofharmony.png")));
	}
	
	@SubscribeEvent
	public void onModelRegistry(ModelRegistryEvent e) {
		for(Block block : GBlocks.BLOCKS) {
			if(block instanceof BlockEyeOfHarmonyTop) {
				ModelLoader.setCustomStateMapper(block, new IStateMapper() {
					
					@Override
					public Map<IBlockState, ModelResourceLocation> putStateModelLocations(Block blockIn) {
						return new HashMap<IBlockState, ModelResourceLocation>();
					}
				});
			}
			
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "normal"));
		}
		
		for (Item item : GItems.ITEMS) {
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}
	}

}
