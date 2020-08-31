package de.maltesermailo.gallifrey.blocks;

import de.maltesermailo.gallifrey.GalliyfreyMod;
import de.maltesermailo.gallifrey.handlers.GallifreyModGuiHandler;
import de.maltesermailo.gallifrey.tileentities.TileEyeofHarmony;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockEyeOfHarmony extends Block {

	private static AxisAlignedBB BOX = new AxisAlignedBB(1, 0, 1, 1, 3, 1);
	
	public BlockEyeOfHarmony(Material materialIn) {
		super(materialIn);
		this.setLightOpacity(0);
		this.setHardness(1.0F);
		this.setHarvestLevel("pickaxe", 3);
		this.setSoundType(SoundType.ANVIL);
		this.setCreativeTab(CreativeTabs.MISC);
	}
	
	@Override
	public boolean canBeReplacedByLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
		return true;
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return state.getBlock() instanceof BlockEyeOfHarmony;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		TileEyeofHarmony tile = new TileEyeofHarmony();
		
		return tile;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return BOX;
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		worldIn.setBlockState(pos.up(), GBlocks.eyeOfHarmonyBlockTop.getDefaultState());
		worldIn.setBlockState(pos.up().up(), GBlocks.eyeOfHarmonyBlockTop.getDefaultState());
		
		TileEyeofHarmony te = (TileEyeofHarmony) worldIn.getTileEntity(pos);
		te.setOwner(placer.getUniqueID());
		
		worldIn.notifyBlockUpdate(pos, state, state, 2);
	}
	
	@Override
	public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
		// TODO Auto-generated method stub
		worldIn.setBlockToAir(pos.up());
		worldIn.setBlockToAir(pos.up().up());
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
	        playerIn.openGui(GalliyfreyMod.INSTANCE, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
	    }
		
		return true;
	}
	
	@Override
	public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		if (!worldIn.isRemote) {
	        playerIn.openGui(GalliyfreyMod.INSTANCE, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
	    }
	}

}
