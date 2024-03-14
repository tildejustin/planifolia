package dev.tildejustin.planifolia.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.*;
import dev.tildejustin.planifolia.DoubleSliderCallbacksGamma;
import net.minecraft.client.option.*;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(GameOptions.class)
public abstract class GameOptionsMixin {
    @Shadow
    public static Text getGenericValueText(Text prefix, Text value) {
        return null;
    }

    // 1.19-1.19.2
    @SuppressWarnings({"InvalidInjectorMethodSignature", "MixinAnnotationTarget", "UnresolvedMixinReference"})
    @Group(min = 1, max = 1)
    @ModifyArg(
            method = "<init>",
            at = @At(
                    value = "INVOKE",
                    // class_7172 -> SimpleOption
                    // class_7172$class_7307 -> SimpleOption$TooltipFactoryGetter
                    // class_7172class_7303 -> SimpleOption$ValueTextGetter
                    // class_7172$class_7178 -> SimpleOption$Callbacks
                    // can't do partial remapped signatures because we're not using the legacy Mixin AP
                    target = "Lnet/minecraft/class_7172;<init>(Ljava/lang/String;Lnet/minecraft/class_7172$class_7307;Lnet/minecraft/class_7172$class_7303;Lnet/minecraft/class_7172$class_7178;Ljava/lang/Object;Ljava/util/function/Consumer;)V",
                    remap = false,
                    ordinal = 0
            ),
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=options.gamma")),
            index = 3,
            require = 0
    )
    private SimpleOption.Callbacks<?> replaceGammaSliderCallbackOld(SimpleOption.Callbacks<?> original) {
        return DoubleSliderCallbacksGamma.INSTANCE;
    }

    // 1.19.3+
    @Group
    @ModifyArg(
            method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/option/SimpleOption;<init>(Ljava/lang/String;Lnet/minecraft/client/option/SimpleOption$TooltipFactory;Lnet/minecraft/client/option/SimpleOption$ValueTextGetter;Lnet/minecraft/client/option/SimpleOption$Callbacks;Ljava/lang/Object;Ljava/util/function/Consumer;)V",
                    ordinal = 0
            ),
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=options.gamma")),
            index = 3,
            require = 0
    )
    private SimpleOption.Callbacks<?> replaceGammaSliderCallback(SimpleOption.Callbacks<?> original) {
        return DoubleSliderCallbacksGamma.INSTANCE;
    }

    @WrapOperation(method = "method_42492", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/option/GameOptions;getGenericValueText(Lnet/minecraft/text/Text;I)Lnet/minecraft/text/Text;"))
    private static Text addMaxGammaText(Text prefix, int value, Operation<Text> original) {
        if (value == 500) {
            return getGenericValueText(prefix, Text.translatable("options.ao.max"));
        }
        return original.call(prefix, value);
    }
}
