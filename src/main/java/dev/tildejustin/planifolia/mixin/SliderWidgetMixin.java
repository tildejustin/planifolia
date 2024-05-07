package dev.tildejustin.planifolia.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SliderWidget.class)
public abstract class SliderWidgetMixin {
    @ModifyExpressionValue(
            method = {
                    // class_357 -> SliderWidget
                    // method_48579 -> renderWidget
                    // class_332 -> MatrixStack
                    /* 1.20.3+ */ "Lnet/minecraft/class_357;method_48579(Lnet/minecraft/class_332;IIF)V",
                    /* 1.20-1.20.2 */ "renderButton",
            }, at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/widget/SliderWidget;value:D"), require = 1, allow = 1
    )
    private double keepSliderInBounds(double original) {
        return MathHelper.clamp(original, 0, 1);
    }
}
