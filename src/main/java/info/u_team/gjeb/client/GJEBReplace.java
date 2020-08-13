package info.u_team.gjeb.client;

import net.minecraft.client.gui.screen.VideoSettingsScreen;
import net.minecraft.client.settings.SliderPercentageOption;
import net.minecraft.util.text.TranslationTextComponent;

public class GJEBReplace {
	
	public static void replaceSlider() {
		// Gamma setting is index 8 here
		VideoSettingsScreen.OPTIONS[8] = new SliderPercentageOption("options.gamma", 0, 10, 0, settings -> {
			return settings.gamma;
		}, (settings, value) -> {
			settings.gamma = value;
		}, (settings, option) -> {
			final double value = option.normalizeValue(option.get(settings));
			if (value == 0) {
				return option.func_243222_a(new TranslationTextComponent("options.gamma.min"));
			} else {
				return value == 1 ? option.func_243222_a(new TranslationTextComponent("options.gamma.max")) : option.func_243223_b((int) (value * 1000));
			}
		});
	}
}
