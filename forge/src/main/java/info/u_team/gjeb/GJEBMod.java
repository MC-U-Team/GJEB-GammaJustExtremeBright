package info.u_team.gjeb;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraftforge.fml.IExtensionPoint.DisplayTest;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkConstants;

@Mod(GJEBMod.MODID)
public class GJEBMod {
	
	public static final String MODID = GJEBReference.MODID;
	
	public static final Logger LOGGER = LogUtils.getLogger();
	
	public GJEBMod() {
		tryCheckSigned();
		ModLoadingContext.get().registerExtensionPoint(DisplayTest.class, () -> new DisplayTest(() -> NetworkConstants.IGNORESERVERONLY, (remoteVersion, network) -> true));
	}
	
	private void tryCheckSigned() {
		try {
			if (ModList.get().isLoaded("uteamcore")) {
				final Class<?> clazz = Class.forName("info.u_team.u_team_core.util.verify.JarSignVerifier");
				
				final Lookup lookup = MethodHandles.publicLookup();
				final MethodHandle method = lookup.findStatic(clazz, "checkSigned", MethodType.methodType(void.class, String.class));
				method.invoke(MODID);
				return;
			}
		} catch (final Throwable th) {
			LOGGER.warn("JarSignVerifier could not be executed, because uteamcore is not installed.", th);
		}
	}
	
}
