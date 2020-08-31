package de.maltesermailo.gallifrey.events;

import de.maltesermailo.gallifrey.tileentities.TileEyeofHarmony;
import net.minecraftforge.fml.common.eventhandler.Event;

public class ChrononTransmissionEvent extends Event {

	private TileEyeofHarmony tile;
	
	public ChrononTransmissionEvent(TileEyeofHarmony tile) {
		this.tile = tile;
	}
	
	public TileEyeofHarmony getTile() {
		return tile;
	}
	
}
