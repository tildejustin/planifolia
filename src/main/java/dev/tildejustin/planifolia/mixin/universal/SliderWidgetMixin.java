package dev.tildejustin.planifolia.mixin.universal;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.util.Mth;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractSliderButton.class)
public abstract class SliderWidgetMixin {
    @Dynamic
    @ModifyExpressionValue(method = "extractWidgetRenderState", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/components/AbstractSliderButton;value:D", opcode = Opcodes.GETFIELD))
    private double keepSliderInBounds(double original) {
        return Mth.clamp(original, 0, 1);
    }
}
