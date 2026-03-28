package dev.tildejustin.planifolia.mixin;

import net.caffeinemc.mods.sodium.client.SodiumClientMod;
import net.minecraft.client.*;
import net.minecraft.client.gui.screens.options.VideoSettingsScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;

@Mixin(VideoSettingsScreen.class)
public abstract class VideoOptionsScreenMixin {
    @Inject(method = "qualityOptions", at = @At("TAIL"), cancellable = true)
    private static void addEntityCullingOption(Options gameOptions, CallbackInfoReturnable<OptionInstance<?>[]> cir) {
        OptionInstance<?>[] newOptions = Arrays.copyOf(cir.getReturnValue(), cir.getReturnValue().length + 2);
        newOptions[newOptions.length - 2] = OptionInstance.createBoolean(
                "Entity Culling",
                SodiumClientMod.options().performance.useEntityCulling,
                value -> SodiumClientMod.options().performance.useEntityCulling = value
        );
        newOptions[newOptions.length - 1] = OptionInstance.createBoolean(
                "Fog Occlusion",
                SodiumClientMod.options().performance.useFogOcclusion,
                value -> SodiumClientMod.options().performance.useFogOcclusion = value
        );
        cir.setReturnValue(newOptions);
    }
}
