package info.u_team.gjeb;

import net.minecraft.client.gui.screen.VideoSettingsScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.SliderPercentageOption;
import net.minecraftforge.api.distmarker.*;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@Mod(GJEBMod.MODID)
@EventBusSubscriber
public class GJEBMod {
	
	public static final String MODID = "gjeb";
	
	public GJEBMod() {
		DistExecutor.runWhenOn(Dist.CLIENT, () -> GJEBMod::replaceSlider);
	}
	
	@OnlyIn(Dist.CLIENT)
	private static void replaceSlider() {
		// Gamma setting is index 8 here
		VideoSettingsScreen.OPTIONS[8] = new SliderPercentageOption("options.gamma", 0, 10, 0, settings -> {
			return settings.gamma;
		}, (settings, value) -> {
			settings.gamma = value;
		}, (settings, option) -> {
			final double value = option.func_216726_a(option.get(settings));
			final String displayName = option.getDisplayString();
			if (value == 0.0D) {
				return displayName + I18n.format("options.gamma.min");
			} else {
				return value == 1.0D ? displayName + I18n.format("options.gamma.max") : displayName + "+" + (int) (value * 1000.0D) + "%";
			}
		});
	}
	
}
