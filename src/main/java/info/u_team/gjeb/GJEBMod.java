package info.u_team.gjeb;

import java.lang.reflect.Method;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import info.u_team.gjeb.client.GJEBReplace;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.FMLNetworkConstants;

@Mod(GJEBMod.MODID)
public class GJEBMod {
	
	public static final String MODID = "gjeb";
	
	public static final Logger LOGGER = LogManager.getLogger(MODID);
	
	public GJEBMod() {
		tryCheckSigned();
		ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));
		DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> GJEBReplace::replaceSlider);
	}
	
	private void tryCheckSigned() {
		try {
			if (ModList.get().isLoaded("uteamcore")) {
				final Class<?> clazz = Class.forName("info.u_team.u_team_core.util.verify.JarSignVerifier");
				final Method method = clazz.getDeclaredMethod("checkSigned", String.class);
				method.invoke(null, MODID);
				return;
			}
		} catch (final Exception ex) {
			LOGGER.warn("JarSignVerifier could not be executed, because uteamcore is not installed.");
		}
	}
	
}
