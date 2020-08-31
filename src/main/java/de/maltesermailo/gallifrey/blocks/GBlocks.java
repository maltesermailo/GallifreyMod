package de.maltesermailo.gallifrey.blocks;

import java.util.ArrayList;
import java.util.List;

import de.maltesermailo.gallifrey.GalliyfreyMod;
import de.maltesermailo.gallifrey.items.GItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = GalliyfreyMod.MODID)
public class GBlocks {

	public static List<Block> BLOCKS = new ArrayList<Block>();
	
	public static BlockEyeOfHarmony eyeOfHarmonyBlock = register(new BlockEyeOfHarmony(Material.IRON), "eye_of_harmony", true);
	public static BlockEyeOfHarmonyTop eyeOfHarmonyBlockTop = register(new BlockEyeOfHarmonyTop(Material.IRON), "eye_of_harmony_top", true);

	public static <T extends Block> T register(T block, String name, boolean hasItem) {
		ResourceLocation loc = new ResourceLocation(GalliyfreyMod.MODID, name);
		block.setRegistryName(loc);

		BLOCKS.add(block);
		
		if(hasItem) {
			ItemBlock blockItem = new ItemBlock(block);
			blockItem.setCreativeTab(block.getCreativeTabToDisplayOn());
			
			GItems.register(blockItem, name);
		}
		
		return block;
	}
	
	@SubscribeEvent
	public static void register(RegistryEvent.Register<Block> event) {
		for(Block block : BLOCKS) {
			event.getRegistry().registerAll(block);
		}
	}


}
