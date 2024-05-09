package dev.tildejustin.planifolia.mixin.universal;

import dev.tildejustin.planifolia.DoubleSliderCallbacksGamma;
import net.minecraft.client.option.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(GameOptions.class)
public abstract class GameOptionsMixin {
    @ModifyArg(
            method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/option/SimpleOption;<init>(Ljava/lang/String;Lnet/minecraft/client/option/SimpleOption$TooltipFactory;Lnet/minecraft/client/option/SimpleOption$ValueTextGetter;Lnet/minecraft/client/option/SimpleOption$Callbacks;Ljava/lang/Object;Ljava/util/function/Consumer;)V",
                    ordinal = 0
            ),
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=options.gamma")),
            index = 3
    )
    private SimpleOption.Callbacks<?> replaceGammaSliderCallback(SimpleOption.Callbacks<?> original) {
        return DoubleSliderCallbacksGamma.INSTANCE;
    }
}
