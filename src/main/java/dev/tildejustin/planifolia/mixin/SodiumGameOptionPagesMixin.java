package dev.tildejustin.planifolia.mixin;

import me.jellysquid.mods.sodium.client.gui.SodiumGameOptionPages;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Pseudo
@Mixin(value = SodiumGameOptionPages.class, remap = false)
public abstract class SodiumGameOptionPagesMixin {
    @ModifyArg(
            method = "lambda$general$6",
            at = @At(value = "INVOKE", target = "Lme/jellysquid/mods/sodium/client/gui/options/control/SliderControl;<init>(Lme/jellysquid/mods/sodium/client/gui/options/Option;IIILme/jellysquid/mods/sodium/client/gui/options/control/ControlValueFormatter;)V"),
            index = 2,
            remap = false
    )
    private static int modifyGammaSliderMaximum(int original) {
        return MinecraftClient.getInstance().world == null ? 500 : original;
    }
}
