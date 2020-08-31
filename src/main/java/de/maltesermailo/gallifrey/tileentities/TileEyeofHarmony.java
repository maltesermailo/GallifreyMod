package de.maltesermailo.gallifrey.tileentities;

import java.util.UUID;

import de.maltesermailo.gallifrey.api.IChrononReceiver;
import de.maltesermailo.gallifrey.api.IChrononSender;
import de.maltesermailo.gallifrey.events.ChrononTransmissionEvent;
import de.maltesermailo.gallifrey.items.ItemSingularity;
import de.maltesermailo.gallifrey.items.ItemStabiliser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.Constants.NBT;

public class TileEyeofHarmony extends TileEntity implements ITickable, IInventory, IChrononSender {

	private NonNullList<ItemStack> slots;
	
	private boolean running;
	private int stabilisationTicks;
	
	private UUID owner;
	
	public TileEyeofHarmony() {
		slots = NonNullList.withSize(5, ItemStack.EMPTY);
		
		this.running = false;
		this.stabilisationTicks = 48000;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		
		if(compound.getCompoundTag("inventory") != null) {
			ItemStackHelper.loadAllItems(compound.getCompoundTag("inventory"), this.slots);
		}
		
		if(compound.hasKey("owner_id")) {
			this.owner = UUID.fromString(compound.getString("owner_id"));
		}
		
		this.running = compound.getBoolean("started");
		this.stabilisationTicks = compound.getInteger("stabilisation");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		NBTTagCompound tag = new NBTTagCompound();
		ItemStackHelper.saveAllItems(tag, slots, true);
		compound.setTag("inventory", tag);
		
		if(this.owner != null) {
			compound.setString("owner_id", this.owner.toString());
		}
		
		compound.setBoolean("started", this.running);
		compound.setInteger("stabilisation", this.stabilisationTicks);
		
		return super.writeToNBT(compound);
	}
	
	@Override
	public NBTTagCompound getUpdateTag() {
		return this.writeToNBT(new NBTTagCompound());
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(this.pos, -1, this.getUpdateTag());
	}
	
	@Override
	public void handleUpdateTag(NBTTagCompound tag) {
		this.readFromNBT(tag);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.getNbtCompound());
	}

	@Override
	public void update() {
		int stabilisers = 0;
		
		for(ItemStack stack : this.slots) {
			if(stack.getItem() instanceof ItemStabiliser) {
				stabilisers++;
			}
		}
		
		if(this.stabilisationTicks > 0) {
			this.stabilisationTicks -= stabilisers;
			
			if(stabilisers == 0 || !this.hasSingularity()) {
				this.stabilisationTicks = 48000;
			}
			
			if(this.running) {
				this.running = false;
			}
			
			this.getWorld().notifyBlockUpdate(pos, this.getWorld().getBlockState(getPos()), this.getWorld().getBlockState(getPos()), 2);
		}
		
		if(this.running && this.stabilisationTicks <= 0) {
			if(stabilisers == 0 || !this.hasSingularity()) {
				this.stabilisationTicks = 48000;
				this.running = false;
				
				//Chunk destruction
			}
			
			this.broadcastChronon(10000000F);
		}
	}

	@Override
	public String getName() {
		return "Eye of Harmony";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public int getSizeInventory() {
		return 5;
	}

	@Override
	public boolean isEmpty() {
		for(ItemStack item : this.slots) {
			if(!item.isEmpty()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return this.slots.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return this.removeStackFromSlot(index);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		ItemStack stack = this.getStackInSlot(index);
		
		if (index < slots.size()) {
			slots.set(index, ItemStack.EMPTY);
		}
		
		return stack;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		this.slots.set(index, stack);
		
		this.markDirty();
	}
	
	@Override
	public void markDirty() {
		super.markDirty();
		
		this.getWorld().notifyBlockUpdate(pos, this.getWorld().getBlockState(getPos()), this.getWorld().getBlockState(getPos()), 2);
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player) {
	}

	@Override
	public void closeInventory(EntityPlayer player) {
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if(index == 0 && stack.getItem() instanceof ItemSingularity) {
			return true;
		} else if (index <= 4 && stack.getItem() instanceof ItemStabiliser) {
			return true;
		}
		return false;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {
	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		this.slots.clear();
	}
	
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	public void setStabilisationTicks(int stabilisationTicks) {
		this.stabilisationTicks = stabilisationTicks;
	}
	
	public void setOwner(UUID owner) {
		this.owner = owner;
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public int getStabilisationTicks() {
		return stabilisationTicks;
	}
	
	public UUID getOwner() {
		return owner;
	}
	
	public boolean isStabilized() {
		return this.stabilisationTicks == 0;
	}
	
	public boolean hasSingularity() {
		return this.getStackInSlot(0).getItem() instanceof ItemSingularity;
	}
 
	@Override
	public float broadcastChronon(float amount) {
		int stabilisers = 0;
		
		for(ItemStack stack : this.slots) {
			if(stack.getItem() instanceof ItemStabiliser) {
				stabilisers++;
			}
		}
		
		int range = stabilisers * 16;
		
		BlockPos pos = this.getPos();
		
		for(int x = -range; x > range; x++) {
			for(int z = -range; z > range; z++) {
				int chunkX = (pos.getX() >> 4) + x;
				int chunkZ = (pos.getZ() >> 4) + z;
				
				chrononTransmission(amount, chunkX, chunkZ);
			}
		}
		
		MinecraftForge.EVENT_BUS.post(new ChrononTransmissionEvent(this));
		
		return 0;
	}
	
	public float chrononTransmission(float amount, int chunkX, int chunkZ) {
		Chunk chunk = this.getWorld().getChunkFromChunkCoords(chunkX, chunkZ);
		
		for(TileEntity tile : chunk.getTileEntityMap().values()) {
			if(tile instanceof IChrononReceiver) {
				IChrononReceiver receiver = (IChrononReceiver) tile;
				receiver.receiveChronon(amount);
			}
		}
		
		return amount;
	}
	
}
