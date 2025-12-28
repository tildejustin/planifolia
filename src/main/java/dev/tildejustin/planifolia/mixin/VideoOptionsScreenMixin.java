package dev.tildejustin.planifolia.mixin;

import net.caffeinemc.mods.sodium.client.SodiumClientMod;
import net.minecraft.client.gui.screen.option.VideoOptionsScreen;
import net.minecraft.client.option.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;

@Mixin(VideoOptionsScreen.class)
public abstract class VideoOptionsScreenMixin {
    @Inject(method = "getInterfaceOptions", at = @At("TAIL"), cancellable = true)
    private static void addEntityCullingOption(GameOptions gameOptions, CallbackInfoReturnable<SimpleOption<?>[]> cir) {
        SimpleOption<?>[] newOptions = Arrays.copyOf(cir.getReturnValue(), cir.getReturnValue().length + 2);
        newOptions[newOptions.length - 2] = SimpleOption.ofBoolean(
                "Entity Culling",
                SodiumClientMod.options().performance.useEntityCulling,
                value -> SodiumClientMod.options().performance.useEntityCulling = value
        );
        newOptions[newOptions.length - 1] = SimpleOption.ofBoolean(
                "Fog Occlusion",
                SodiumClientMod.options().performance.useFogOcclusion,
                value -> SodiumClientMod.options().performance.useFogOcclusion = value
        );
        cir.setReturnValue(newOptions);
    }
}
