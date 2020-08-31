package de.maltesermailo.gallifrey.blocks;

import de.maltesermailo.gallifrey.GalliyfreyMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockEyeOfHarmonyTop extends Block {

	public BlockEyeOfHarmonyTop(Material materialIn) {
		super(materialIn);
	}
	
	@Override
	public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.setBlockToAir(pos.down());
		
		if(worldIn.getBlockState(pos.down().down()).getBlock() instanceof BlockEyeOfHarmonyTop || worldIn.getBlockState(pos.down().down()).getBlock() instanceof BlockEyeOfHarmony) {
			worldIn.setBlockToAir(pos.down().down());
		}
		
		if(worldIn.getBlockState(pos.up()).getBlock() instanceof BlockEyeOfHarmonyTop) {
			worldIn.setBlockToAir(pos.up());
		}
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			if(worldIn.getBlockState(pos.down()).getBlock() instanceof BlockEyeOfHarmony) {
				playerIn.openGui(GalliyfreyMod.INSTANCE, 0, worldIn, pos.getX(), pos.down().getY(), pos.getZ());
			} else if(worldIn.getBlockState(pos.down().down()).getBlock() instanceof BlockEyeOfHarmony) {
				playerIn.openGui(GalliyfreyMod.INSTANCE, 0, worldIn, pos.getX(), pos.down().down().getY(), pos.getZ());
			}
	    }
		
		return true;
	}

}
