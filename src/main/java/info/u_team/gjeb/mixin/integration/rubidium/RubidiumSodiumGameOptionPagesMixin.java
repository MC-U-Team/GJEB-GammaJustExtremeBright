package info.u_team.gjeb.mixin.integration.rubidium;

import org.spongepowered.asm.mixin.Mixin;

import info.u_team.gjeb.mixin.GJEBMixinPlugin;

@Mixin(targets = GJEBMixinPlugin.RUBIDIUM_CLASS)
abstract class RubidiumSodiumGameOptionPagesMixin {
	
	@SuppressWarnings("unused")
	private static void gjeb_force_mixin_apply() {
		System.out.println();
	}
	
}
