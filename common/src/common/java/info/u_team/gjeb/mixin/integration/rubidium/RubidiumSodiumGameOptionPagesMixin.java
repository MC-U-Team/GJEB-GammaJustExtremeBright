package info.u_team.gjeb.mixin.integration.rubidium;

import org.spongepowered.asm.mixin.Mixin;

import info.u_team.gjeb.mixin.GJEBAbstractMixinPlugin;

@Mixin(targets = GJEBAbstractMixinPlugin.RUBIDIUM_CLASS)
abstract class RubidiumSodiumGameOptionPagesMixin {
	
	@SuppressWarnings("unused")
	private static void gjeb_forceMixinApply() {
		System.out.println();
	}
	
}
