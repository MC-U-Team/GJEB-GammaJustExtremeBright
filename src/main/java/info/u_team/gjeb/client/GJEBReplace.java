package info.u_team.gjeb.client;

import net.minecraft.client.gui.screen.VideoSettingsScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.SliderPercentageOption;

public class GJEBReplace {
	
	public static void replaceSlider() {
		// Gamma setting is index 8 here
		VideoSettingsScreen.OPTIONS[8] = new SliderPercentageOption("options.gamma", 0, 10, 0, settings -> {
			return settings.gamma;
		}, (settings, value) -> {
			settings.gamma = value;
		}, (settings, option) -> {
			final double value = option.normalizeValue(option.get(settings));
			final String displayName = option.getDisplayString();
			if (value == 0.0D) {
				return displayName + I18n.format("options.gamma.min");
			} else {
				return value == 1.0D ? displayName + I18n.format("options.gamma.max") : displayName + "+" + (int) (value * 1000.0D) + "%";
			}
		});
	}
	
}
