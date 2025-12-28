package dev.tildejustin.planifolia.mixin.universal;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.util.math.MathHelper;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SliderWidget.class)
public abstract class SliderWidgetMixin {
    @Dynamic
    @ModifyExpressionValue(method = "renderWidget", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/widget/SliderWidget;value:D", opcode = Opcodes.GETFIELD))
    private double keepSliderInBounds(double original) {
        return MathHelper.clamp(original, 0, 1);
    }
}
