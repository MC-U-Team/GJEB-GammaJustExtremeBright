package info.u_team.gjeb.asm.integration.rubidium;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.IntInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

public class RubidiumSodiumGameOptionPagesAsm {
	
	private static Logger LOGGER = LogUtils.getLogger();
	
	public static void asm(ClassNode targetClass) {
		for (final MethodNode method : targetClass.methods) {
			for (final AbstractInsnNode ins : method.instructions) {
				if (ins instanceof final MethodInsnNode methodIns) {
					if (methodIns.getOpcode() == Opcodes.INVOKESTATIC && //
							methodIns.owner.equals("me/jellysquid/mods/sodium/client/gui/options/control/ControlValueFormatter") && //
							methodIns.name.equals("brightness") && //
							methodIns.desc.equals("()Lme/jellysquid/mods/sodium/client/gui/options/control/ControlValueFormatter;")) {
						final AbstractInsnNode node = ins.getPrevious().getPrevious(); // Find IntInsNode with 100 value
						
						if (node instanceof final IntInsnNode intInsNode) {
							intInsNode.setOpcode(Opcodes.SIPUSH);
							intInsNode.operand = 1000;
							LOGGER.debug("Inject gjeb into rubidiums guis");
						} else {
							LOGGER.warn("Cannot make rubidium compatible with gjeb");
						}
					}
				}
			}
		}
	}
	
}
