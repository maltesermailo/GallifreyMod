package de.maltesermailo.gallifrey.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.12
// Paste this class into your mod and generate all required imports


public class ModelEyeofHarmony extends ModelBase {
	private final ModelRenderer base;
	private final ModelRenderer hexagon_side_1;
	private final ModelRenderer hexagon_side_2;
	private final ModelRenderer top;

	public ModelEyeofHarmony() {
		textureWidth = 128;
		textureHeight = 128;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 0, -5.0F, -30.0F, -5.0F, 10, 30, 10, 0.0F, false));

		hexagon_side_1 = new ModelRenderer(this);
		hexagon_side_1.setRotationPoint(9.0F, 0.0F, 0.0F);
		base.addChild(hexagon_side_1);
		hexagon_side_1.cubeList.add(new ModelBox(hexagon_side_1, 0, 0, -4.0F, -30.0F, -4.0F, 1, 30, 8, 0.0F, false));
		hexagon_side_1.cubeList.add(new ModelBox(hexagon_side_1, 0, 0, -3.0F, -30.0F, -3.0F, 1, 30, 6, 0.0F, false));
		hexagon_side_1.cubeList.add(new ModelBox(hexagon_side_1, 0, 0, -2.0F, -30.0F, -2.0F, 1, 30, 4, 0.0F, false));
		hexagon_side_1.cubeList.add(new ModelBox(hexagon_side_1, 0, 0, -1.0F, -30.0F, -1.0F, 1, 30, 2, 0.0F, false));

		hexagon_side_2 = new ModelRenderer(this);
		hexagon_side_2.setRotationPoint(-9.0F, 0.0F, 0.0F);
		base.addChild(hexagon_side_2);
		setRotationAngle(hexagon_side_2, 0.0F, 3.1416F, 0.0F);
		hexagon_side_2.cubeList.add(new ModelBox(hexagon_side_2, 0, 0, -4.0F, -30.0F, -4.0F, 1, 30, 8, 0.0F, false));
		hexagon_side_2.cubeList.add(new ModelBox(hexagon_side_2, 0, 0, -3.0F, -30.0F, -3.0F, 1, 30, 6, 0.0F, false));
		hexagon_side_2.cubeList.add(new ModelBox(hexagon_side_2, 0, 0, -2.0F, -30.0F, -2.0F, 1, 30, 4, 0.0F, false));
		hexagon_side_2.cubeList.add(new ModelBox(hexagon_side_2, 0, 0, -1.0F, -30.0F, -1.0F, 1, 30, 2, 0.0F, false));

		top = new ModelRenderer(this);
		top.setRotationPoint(0.0F, 0.0F, 0.0F);
		base.addChild(top);
		top.cubeList.add(new ModelBox(top, 0, 0, -8.0F, -31.0F, -1.0F, 1, 1, 2, 0.0F, false));
		top.cubeList.add(new ModelBox(top, 0, 0, 7.0F, -31.0F, -1.0F, 1, 1, 2, 0.0F, false));
		top.cubeList.add(new ModelBox(top, 0, 0, 6.0F, -31.0F, -2.0F, 1, 1, 4, 0.0F, false));
		top.cubeList.add(new ModelBox(top, 0, 0, 6.0F, -32.0F, -1.0F, 1, 1, 2, 0.0F, false));
		top.cubeList.add(new ModelBox(top, 0, 0, 5.0F, -31.0F, -3.0F, 1, 1, 6, 0.0F, false));
		top.cubeList.add(new ModelBox(top, 0, 0, 5.0F, -32.0F, -2.0F, 1, 1, 4, 0.0F, false));
		top.cubeList.add(new ModelBox(top, 0, 0, 5.0F, -33.0F, -1.0F, 1, 1, 2, 0.0F, false));
		top.cubeList.add(new ModelBox(top, 0, 0, -7.0F, -31.0F, -2.0F, 1, 1, 4, 0.0F, false));
		top.cubeList.add(new ModelBox(top, 0, 0, -7.0F, -32.0F, -1.0F, 1, 1, 2, 0.0F, false));
		top.cubeList.add(new ModelBox(top, 0, 0, -6.0F, -31.0F, -3.0F, 1, 1, 6, 0.0F, false));
		top.cubeList.add(new ModelBox(top, 0, 0, -6.0F, -32.0F, -2.0F, 1, 1, 4, 0.0F, false));
		top.cubeList.add(new ModelBox(top, 0, 0, -6.0F, -33.0F, -1.0F, 1, 1, 2, 0.0F, false));
		top.cubeList.add(new ModelBox(top, 0, 0, -5.0F, -31.0F, -4.0F, 10, 1, 8, 0.0F, false));
		top.cubeList.add(new ModelBox(top, 0, 0, -5.0F, -32.0F, -3.0F, 10, 1, 6, 0.0F, false));
		top.cubeList.add(new ModelBox(top, 0, 0, -5.0F, -33.0F, -2.0F, 10, 1, 4, 0.0F, false));
		top.cubeList.add(new ModelBox(top, 0, 0, -5.0F, -34.0F, -1.0F, 10, 1, 2, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		base.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}