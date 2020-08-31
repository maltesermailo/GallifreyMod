package de.maltesermailo.gallifrey.inventory.container;

import de.maltesermailo.gallifrey.items.ItemSingularity;
import de.maltesermailo.gallifrey.items.ItemStabiliser;
import de.maltesermailo.gallifrey.tileentities.TileEyeofHarmony;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class EyeofHarmonyContainer extends Container {
	
	private TileEyeofHarmony tile;
	
	public EyeofHarmonyContainer(IInventory playerInv, TileEyeofHarmony te) {
		addSlotToContainer(new EyeofHarmonySlot(te, 0, 80, 53));
		addSlotToContainer(new EyeofHarmonySlot(te, 1, 80, 35));
		addSlotToContainer(new EyeofHarmonySlot(te, 2, 98, 53));
		addSlotToContainer(new EyeofHarmonySlot(te, 3, 80, 71));
		addSlotToContainer(new EyeofHarmonySlot(te, 4, 62, 53));
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 139 + i * 18));
			}
		}
		
		for (int k = 0; k < 9; k++) {
			addSlotToContainer(new Slot(playerInv, k, 8 + k * 18, 197));
		}
		
		this.tile = te;
	}
	
	public TileEyeofHarmony getTile() {
		return tile;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack previous = null;
	    Slot slot = this.inventorySlots.get(index);

	    if (slot != null && slot.getHasStack()) {
	        ItemStack current = slot.getStack();
	        previous = current.copy();

	        if (index < 5) {
	            // From TE Inventory to Player Inventory
	            if (!this.mergeItemStack(current, 5, 40, true))
	                return null;
	        } else {
	            // From Player Inventory to TE Inventory
	            if (!this.mergeItemStack(current, 0, 4, false))
	                return null;
	        }
	        
	        if (current.getCount() == 0)
	            slot.putStack((ItemStack) null);
	        else
	            slot.onSlotChanged();

	        if (current.getCount() == previous.getCount())
	            return null;
	        slot.onTake(playerIn, current);
	    }
	    return previous;
	}
	
	public class EyeofHarmonySlot extends Slot {

		public EyeofHarmonySlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
			super(inventoryIn, index, xPosition, yPosition);
		}
		
		@Override
		public boolean isEnabled() {
			return true;
		}
		
		@Override
		public boolean isItemValid(ItemStack stack) {
			if(stack.getItem() instanceof ItemSingularity) {
				if(this.getSlotIndex() == 0) {
					return true;
				}
			} else if(stack.getItem() instanceof ItemStabiliser) {
				if(this.getSlotIndex() != 0) {
					return true;
				}
			}
			
			return false;
		}
		
	}

}
