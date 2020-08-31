package de.maltesermailo.gallifrey.client.renderers;

import de.maltesermailo.gallifrey.GalliyfreyMod;
import de.maltesermailo.gallifrey.client.models.ModelEyeofHarmony;
import de.maltesermailo.gallifrey.tileentities.TileEyeofHarmony;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderEyeofHarmony extends TileEntitySpecialRenderer<TileEyeofHarmony> {

	public static final ResourceLocation TEXTURE = new ResourceLocation(GalliyfreyMod.MODID, "textures/tile/eyeofharmony.png");
	public static ModelEyeofHarmony model = new ModelEyeofHarmony();
	
	@Override
	public void render(TileEyeofHarmony te, double x, double y, double z, float partialTicks, int destroyStage,
			float alpha) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 0.5, y + 2.4, z + 0.5);
		GlStateManager.rotate(180F, 1F, 0F, 0F);
		GlStateManager.scale(0.5, 0.5, 0.5);
		Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
		model.render(null, 0, 0, 0, 0, 0, 0.20F);
		GlStateManager.popMatrix();
	}
	
}
