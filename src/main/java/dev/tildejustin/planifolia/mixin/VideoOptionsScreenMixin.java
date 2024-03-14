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
            (gameOptions, option, entityCulling) -> SodiumClientMod.options().performance.useEntityCulling = entityCulling
    );

    @Mutable
    @Shadow
    @Final
    private static Option[] OPTIONS;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void addEntityCullingOption(CallbackInfo ci) {
        Option[] newOptions = Arrays.copyOf(OPTIONS, OPTIONS.length + 1);
        newOptions[newOptions.length - 1] = ENTITY_CULLING;
        OPTIONS = newOptions;
    }
}
