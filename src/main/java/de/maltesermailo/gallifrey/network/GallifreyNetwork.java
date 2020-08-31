package de.maltesermailo.gallifrey.network;

import de.maltesermailo.gallifrey.network.packets.EyeOfHarmonyToggleMessage;
import de.maltesermailo.gallifrey.network.packets.EyeOfHarmonyToggleMessage.EyeOfHarmonyToggleMessageHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class GallifreyNetwork {

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("gallifreymod");
	
    public static void init() {
    	INSTANCE.registerMessage(EyeOfHarmonyToggleMessageHandler.class, EyeOfHarmonyToggleMessage.class, 0, Side.SERVER);
    }
}
