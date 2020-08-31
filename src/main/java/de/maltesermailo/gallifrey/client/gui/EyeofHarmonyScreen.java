package de.maltesermailo.gallifrey.client.gui;

import java.io.IOException;

import de.maltesermailo.gallifrey.GalliyfreyMod;
import de.maltesermailo.gallifrey.inventory.container.EyeofHarmonyContainer;
import de.maltesermailo.gallifrey.network.GallifreyNetwork;
import de.maltesermailo.gallifrey.network.packets.EyeOfHarmonyToggleMessage;
import de.maltesermailo.gallifrey.tileentities.TileEyeofHarmony;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class EyeofHarmonyScreen extends GuiContainer {

	private static ResourceLocation TEXTURE = new ResourceLocation(GalliyfreyMod.MODID, "textures/gui/eyeofharmony.png");
	
	private GuiButton buttonStart;
	
	public EyeofHarmonyScreen(Container inventorySlotsIn) {
		super(inventorySlotsIn);
		
		this.ySize = 224;
		
		this.setGuiSize(177, 166);
	}
	
	@Override
	public void initGui() {
		super.initGui();
		
		this.buttonStart = new GuiButton(0, this.width / 2 - 80, this.height / 2, 140, 20, "Start Energy Extraction");
		
		this.addButton(this.buttonStart);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
		
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
		
		this.drawTexturedModalRect(i, j-1, 0, 0, this.xSize, 225);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.fontRenderer.drawString("Eye of Harmony", 8, 6, 4210752);
		
		EyeofHarmonyContainer container = (EyeofHarmonyContainer)this.inventorySlots;
		
		TileEyeofHarmony te = container.getTile();
		
		if(te.isRunning())
			this.buttonStart.displayString = "Stop Energy Extraction";
		else
			this.buttonStart.displayString = "Start Energy Extraction";
		
		int stabilization = (int) (((48000.0D - te.getStabilisationTicks()) / 48000.0D) * 100.0D);
		
		this.fontRenderer.drawString("Stabilization: " + stabilization + "%", 8, 96, 4210752);
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if(this.buttonStart.equals(button)) {
			EyeofHarmonyContainer container = (EyeofHarmonyContainer)this.inventorySlots;
			
			TileEyeofHarmony te = container.getTile();
			
			GallifreyNetwork.INSTANCE.sendToServer(new EyeOfHarmonyToggleMessage(te.getPos(), !te.isRunning()));
		}
	}

}
