package dev.tildejustin.planifolia.mixin.universal;

import dev.tildejustin.planifolia.DoubleSliderCallbacksGamma;
import net.minecraft.client.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(Options.class)
public abstract class GameOptionsMixin {
    @ModifyArg(
            method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/OptionInstance;<init>(Ljava/lang/String;Lnet/minecraft/client/OptionInstance$TooltipSupplier;Lnet/minecraft/client/OptionInstance$CaptionBasedToString;Lnet/minecraft/client/OptionInstance$ValueSet;Ljava/lang/Object;Lnet/minecraft/client/OptionInstance$ValueUpdateListener;)V",
                    ordinal = 0
            ),
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=options.gamma")),
            index = 3
    )
    private OptionInstance.ValueSet<?> replaceGammaSliderCallback(OptionInstance.ValueSet<?> original) {
        return DoubleSliderCallbacksGamma.INSTANCE;
    }
}
