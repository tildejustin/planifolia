package dev.tildejustin.planifolia.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.*;
import dev.tildejustin.planifolia.mixin.accessor.OptionAccessor;
import net.minecraft.client.option.*;
import net.minecraft.text.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Option.class)
public abstract class OptionMixin {
    @WrapOperation(method = "method_18545", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/option/DoubleOption;getGenericLabel(Lnet/minecraft/text/Text;)Lnet/minecraft/text/Text;", ordinal = 2))
    private static Text addMaxGammaText(DoubleOption instance, Text value, Operation<Text> original, GameOptions gameOptions, DoubleOption option) {
        return option.get(gameOptions) == 5 ? ((OptionAccessor) option).callGetGenericLabel(new TranslatableText("options.ao.max")) : original.call(instance, value);
    }
}
