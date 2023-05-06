package info.u_team.gjeb.mixin;

import java.util.stream.Stream;

import net.fabricmc.loader.api.FabricLoader;

public class GJEBMixinPlugin extends GJEBAbstractMixinPlugin {
	
	@Override
	protected boolean modLoaded(String... mods) {
		return Stream.of(mods).anyMatch(FabricLoader.getInstance()::isModLoaded);
	}
	
}
