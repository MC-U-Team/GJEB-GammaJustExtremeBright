package info.u_team.gjeb;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.fabricmc.api.ClientModInitializer;

public class GJEBMod implements ClientModInitializer {
	
	public static final String MODID = GJEBReference.MODID;
	
	public static final Logger LOGGER = LogUtils.getLogger();
	
	@Override
	public void onInitializeClient() {
	}
	
}
