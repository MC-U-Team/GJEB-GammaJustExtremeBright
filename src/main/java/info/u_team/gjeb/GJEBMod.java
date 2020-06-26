package info.u_team.gjeb;

import java.lang.reflect.Method;

import org.apache.logging.log4j.*;

import net.minecraft.client.gui.screen.VideoSettingsScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.SliderPercentageOption;
import net.minecraftforge.api.distmarker.*;
import net.minecraftforge.fml.*;
import net.minecraftforge.fml.common.Mod;

@Mod(GJEBMod.MODID)
public class GJEBMod {
	
	public static final String MODID = "gjeb";
	
	private static final Logger LOGGER = LogManager.getLogger(MODID);
	
	public GJEBMod() {
		tryCheckSigned();
		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> GJEBMod::replaceSlider);
	}
	
	private void tryCheckSigned() {
		try {
			if (ModList.get().isLoaded("uteamcore")) {
				Class<?> clazz = Class.forName("info.u_team.u_team_core.util.verify.JarSignVerifier");
				Method method = clazz.getDeclaredMethod("checkSigned", String.class);
				method.invoke(null, MODID);
				return;
			}
		} catch (Exception ex) {
		}
		LOGGER.warn("If the mod gjeb is signed can only be checked if uteamcore is installed.");
	}
	
	@OnlyIn(Dist.CLIENT)
	private static void replaceSlider() {
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
