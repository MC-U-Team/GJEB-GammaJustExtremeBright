package info.u_team.gjeb.mixin;

import java.util.List;
import java.util.Set;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import info.u_team.gjeb.asm.integration.rubidium.RubidiumSodiumGameOptionPagesAsm;
import net.minecraftforge.fml.loading.LoadingModList;

public class GJEBMixinPlugin implements IMixinConfigPlugin {
	
	public static final String RUBIDIUM_CLASS = "me.jellysquid.mods.sodium.client.gui.SodiumGameOptionPages";
	
	@Override
	public void onLoad(String mixinPackage) {
	}
	
	@Override
	public String getRefMapperConfig() {
		return null;
	}
	
	@Override
	public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
		return true;
	}
	
	@Override
	public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
	}
	
	@Override
	public List<String> getMixins() {
		if (LoadingModList.get().getModFileById("rubidium") != null) {
			return List.of("integration.rubidium.RubidiumSodiumGameOptionPagesMixin");
		}
		return null;
	}
	
	@Override
	public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
		if (targetClassName.equals(RUBIDIUM_CLASS)) {
			RubidiumSodiumGameOptionPagesAsm.asm(targetClass);
		}
	}
	
	@Override
	public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
	}
	
}
