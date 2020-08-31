package de.maltesermailo.gallifrey.network.packets;

import de.maltesermailo.gallifrey.tileentities.TileEyeofHarmony;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class EyeOfHarmonyToggleMessage implements IMessage {

	private boolean toggle;
	
	private BlockPos pos;
	
	public EyeOfHarmonyToggleMessage(BlockPos pos, boolean toggle) {
		this.pos = pos;
		this.toggle = toggle;
	}
	
	public EyeOfHarmonyToggleMessage() {
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.toggle = buf.readBoolean();
		this.pos = BlockPos.fromLong(buf.readLong());
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(this.toggle);
		buf.writeLong(this.pos.toLong());
	}
	
	public boolean isToggle() {
		return toggle;
	}
	
	public void setToggle(boolean toggle) {
		this.toggle = toggle;
	}
	
	public BlockPos getPos() {
		return pos;
	}
	
	public void setPos(BlockPos pos) {
		this.pos = pos;
	}
	
	public static class EyeOfHarmonyToggleMessageHandler implements IMessageHandler<EyeOfHarmonyToggleMessage, EyeOfHarmonyToggleMessage> {

		@Override
		public EyeOfHarmonyToggleMessage onMessage(EyeOfHarmonyToggleMessage message, MessageContext ctx) {
			ctx.getServerHandler().player.getServerWorld().addScheduledTask(new Runnable() {
				
				@Override
				public void run() {
					TileEyeofHarmony te = (TileEyeofHarmony)ctx.getServerHandler().player.getServerWorld().getTileEntity(message.pos);
					
					System.out.println(te.getOwner());
					
					if(te.getOwner().equals(ctx.getServerHandler().player.getUniqueID())) {
						te.setRunning(message.toggle);
						te.markDirty();
					}
				}
				
			});
			return null;
		}
		
	}

}
