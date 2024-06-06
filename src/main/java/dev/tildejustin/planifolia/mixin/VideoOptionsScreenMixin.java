package dev.tildejustin.planifolia.mixin;

import me.jellysquid.mods.sodium.client.SodiumClientMod;
import net.minecraft.client.gui.screen.option.VideoOptionsScreen;
import net.minecraft.client.option.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@Mixin(VideoOptionsScreen.class)
public abstract class VideoOptionsScreenMixin {
    @Unique
    private static final CyclingOption<Boolean> ENTITY_CULLING = CyclingOption.create(
            "Entity Culling",
            gameOptions -> SodiumClientMod.options().performance.useEntityCulling,
            (gameOptions, option, value) -> SodiumClientMod.options().performance.useEntityCulling = value
    );

    @Unique
    private static final CyclingOption<Boolean> FOG_OCCLUSION = CyclingOption.create(
            "Fog Occlusion",
            gameOptions -> SodiumClientMod.options().performance.useFogOcclusion,
            (gameOptions, option, value) -> SodiumClientMod.options().performance.useFogOcclusion = value
    );

    @Mutable
    @Shadow
    @Final
    private static Option[] OPTIONS;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void addEntityCullingOption(CallbackInfo ci) {
        Option[] newOptions = Arrays.copyOf(OPTIONS, OPTIONS.length + 2);
        newOptions[newOptions.length - 2] = ENTITY_CULLING;
        newOptions[newOptions.length - 1] = FOG_OCCLUSION;
        OPTIONS = newOptions;
    }
}
