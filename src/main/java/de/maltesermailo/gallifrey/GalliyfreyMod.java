package de.maltesermailo.gallifrey;

import org.apache.logging.log4j.Logger;

import de.maltesermailo.gallifrey.handlers.GallifreyModGuiHandler;
import de.maltesermailo.gallifrey.network.GallifreyNetwork;
import de.maltesermailo.gallifrey.proxy.IProxy;
import de.maltesermailo.gallifrey.tileentities.TileEyeofHarmony;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = GalliyfreyMod.MODID, name = GalliyfreyMod.NAME, version = GalliyfreyMod.VERSION)
public class GalliyfreyMod
{
    public static final String MODID = "gallifreymod";
    public static final String NAME = "Gallifreyan Technology Mod";
    public static final String VERSION = "1.0";

    private static Logger logger;
    
    public static GalliyfreyMod INSTANCE;
    
    @SidedProxy(clientSide = "de.maltesermailo.gallifrey.proxy.ClientProxy", serverSide = "de.maltesermailo.gallifrey.proxy.ServerProxy")
    private static IProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	INSTANCE = this;
    	
        logger = event.getModLog();
        proxy.register();
        
        GameRegistry.registerTileEntity(TileEyeofHarmony.class, new ResourceLocation(GalliyfreyMod.MODID, "eye_of_harmony"));
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	proxy.init();
    	
    	GallifreyNetwork.init();
    	
    	NetworkRegistry.INSTANCE.registerGuiHandler(this, new GallifreyModGuiHandler());
    }
}
