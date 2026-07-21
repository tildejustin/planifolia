package dev.tildejustin.planifolia.mixin;

import net.caffeinemc.mods.sodium.client.gui.VideoSettingsScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(OptionsScreen.class)
public abstract class OptionsScreenMixin {
    @Inject(method = "lambda$init$3", at = @At("HEAD"), cancellable = true, remap = false)
    private void openVanillaMenu(CallbackInfoReturnable<Screen> cir) {
        if (Minecraft.getInstance().level == null) {
            cir.setReturnValue(VideoSettingsScreen.createScreen((Screen) (Object) this));
        }
    }
}
