package de.maltesermailo.gallifrey.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderItem extends TileEntityItemStackRenderer {

	private ModelBase base;
	private ResourceLocation texture;
	
	public RenderItem(ModelBase base, ResourceLocation texture) {
		this.base = base;
		this.texture = texture;
	}
	
	@Override
	public void renderByItem(ItemStack itemStackIn) {
		GlStateManager.pushMatrix();
		Minecraft.getMinecraft().getTextureManager().bindTexture(this.texture);
		
		GlStateManager.scale(0.05, 0.05, 0.05);
		GlStateManager.translate(7.5, 10, 5.5);
		GlStateManager.rotate(180F, 1F, 0F, 0F);
		
		base.render(null, 0, 0, 0, 0, 0, 1F);
		GlStateManager.popMatrix();
	}
	
}
