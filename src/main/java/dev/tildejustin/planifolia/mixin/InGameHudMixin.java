package dev.tildejustin.planifolia.mixin;

import com.bawnorton.mixinsquared.TargetHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {
    @Dynamic
    @TargetHandler(mixin = "me.jellysquid.mods.sodium.mixin.features.options.MixinInGameHud", name = "redirectFancyGraphicsVignette")
    @Inject(method = "@MixinSquared:Handler", at = @At("HEAD"), cancellable = true)
    private void useVanillaVignetteTrigger(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(MinecraftClient.isFancyGraphicsOrBetter());
    }
}
