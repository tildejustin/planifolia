package dev.tildejustin.planifolia.mixin;

import me.jellysquid.mods.sodium.client.gui.SodiumOptionsGUI;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(OptionsScreen.class)
public abstract class OptionsScreenMixin {
    @ModifyArg(
            method = "method_19828",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;setScreen(Lnet/minecraft/client/gui/screen/Screen;)V", remap = true),
            remap = false
    )
    private Screen openVanillaMenu(Screen original) {
        return MinecraftClient.getInstance().world == null ? new SodiumOptionsGUI((Screen) (Object) this) : original;
    }
}
