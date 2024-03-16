package dev.tildejustin.planifolia.mixin;

import net.minecraft.client.option.GameOptions;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameOptions.class)
public abstract class GameOptionsMixin {
    @Shadow
    public double gamma;

    @Inject(method = "load", at = @At("TAIL"))
    private void maxGamma(CallbackInfo ci) {
        this.gamma = MathHelper.clamp(this.gamma, 0, 5);
    }
}
