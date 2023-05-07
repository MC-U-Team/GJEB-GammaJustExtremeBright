package info.u_team.gjeb.mixin.integration.sodium;

import org.spongepowered.asm.mixin.Mixin;

import info.u_team.gjeb.mixin.GJEBAbstractMixinPlugin;

@Mixin(targets = GJEBAbstractMixinPlugin.SODIUM_CLASS)
abstract class SodiumGameOptionPagesMixin {
	
	@SuppressWarnings("unused")
	private static void gjeb_forceMixinApply() {
		System.out.println();
	}
	
}
