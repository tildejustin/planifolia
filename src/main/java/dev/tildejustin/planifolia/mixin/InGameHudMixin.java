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
    @Group(min = 1, max = 1)
    @TargetHandler(mixin = "me.jellysquid.mods.sodium.mixin.features.options.MixinInGameHud", name = "redirectFancyGraphicsVignette")
    @Inject(method = "@MixinSquared:Handler", at = @At("HEAD"), cancellable = true, require = 0)
    private void useVanillaVignetteTrigger(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(MinecraftClient.isFancyGraphicsOrBetter());
    }

    @Dynamic
    @Group
    @TargetHandler(mixin = "me.jellysquid.mods.sodium.mixin.features.options.overlays.InGameHudMixin", name = "redirectFancyGraphicsVignette")
    @Inject(method = "@MixinSquared:Handler", at = @At("HEAD"), cancellable = true, require = 0)
    private void useVanillaVignetteTrigger2(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(MinecraftClient.isFancyGraphicsOrBetter());
    }
}
