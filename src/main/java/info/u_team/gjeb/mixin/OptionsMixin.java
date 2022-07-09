package info.u_team.gjeb.mixin;

import java.util.Optional;

import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;

import net.minecraft.client.OptionInstance;
import net.minecraft.client.OptionInstance.SliderableValueSet;
import net.minecraft.client.Options;
import net.minecraft.network.chat.Component;

@Mixin(Options.class)
abstract class OptionsMixin {
	
	@Shadow
	@Final
	@Mutable
	public OptionInstance<Double> gamma;
	
	@Redirect(method = "<init>", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Options;gamma:Lnet/minecraft/client/OptionInstance;", opcode = Opcodes.PUTFIELD))
	private void gjeb_replaceGammaOption(Options options, OptionInstance<?> instance) {
		gamma = new OptionInstance<>("options.gamma", OptionInstance.noTooltip(), (component, value) -> {
			final int intValue = (int) (value * 100);
			if (intValue == 0) {
				return Options.genericValueLabel(component, Component.translatable("options.gamma.min"));
			} else if (intValue == 50) {
				return Options.genericValueLabel(component, Component.translatable("options.gamma.default"));
			} else {
				return intValue == 1000 ? Options.genericValueLabel(component, Component.translatable("options.gamma.max")) : Options.genericValueLabel(component, intValue);
			}
		}, new SliderableValueSet<>() {
			
			@Override
			public Optional<Double> validateValue(Double value) {
				return value >= 0 && value <= 10 ? Optional.of(value) : Optional.empty();
			}
			
			@Override
			public double toSliderValue(Double value) {
				return value / 10D;
			}
			
			@Override
			public Double fromSliderValue(double sliderValue) {
				return sliderValue * 10D;
			}
			
			@Override
			public Codec<Double> codec() {
				return Codec.either(Codec.doubleRange(0, 10), Codec.BOOL).xmap(either -> {
					return either.map(value -> {
						return value;
					}, leftRight -> {
						return leftRight ? 10D : 0D;
					});
				}, Either::left);
			}
		}, 0.5D, onValueUpdate -> {
		});
	}
	
}
