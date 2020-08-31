package de.maltesermailo.gallifrey.items;

import java.util.ArrayList;
import java.util.List;

import de.maltesermailo.gallifrey.GalliyfreyMod;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = GalliyfreyMod.MODID)
public class GItems {

	public static List<Item> ITEMS = new ArrayList<Item>();
	
	public static ItemSingularity singularityItem = register(new ItemSingularity(), "singularity");
	public static ItemStabiliser stabiliserItem = register(new ItemStabiliser(), "stabiliser");

	public static <T extends Item> T register(T item, String name) {
		ResourceLocation loc = new ResourceLocation(GalliyfreyMod.MODID, name);
		item.setRegistryName(loc);

		ITEMS.add(item);
		
		return item;
	}
	
	@SubscribeEvent
	public static void register(RegistryEvent.Register<Item> event) {
		for(Item item : ITEMS) {
			event.getRegistry().registerAll(item);
		}
	}


}
