package dev.tildejustin.planifolia.mixin;

import net.caffeinemc.mods.sodium.client.model.quad.ModelQuadView;
import net.caffeinemc.mods.sodium.client.model.quad.blender.BlendedColorProvider;
import net.caffeinemc.mods.sodium.client.world.LevelSlice;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@Mixin(BlendedColorProvider.class)
public abstract class BlendedColorProviderMixin<T> {
    @Shadow
    protected abstract int getColor(LevelSlice levelSlice, T t, BlockPos blockPos);

    @Inject(method = "getColors", at = @At("HEAD"), cancellable = true)
    private void disableBiomeBlendingWhenAppropriate(LevelSlice slice, BlockPos pos, BlockPos.Mutable scratchPos, T state, ModelQuadView quad, int[] output, boolean smooth, CallbackInfo ci) {
        if (MinecraftClient.getInstance().options.getBiomeBlendRadius().getValue() == 0) {
            Arrays.fill(output, this.getColor(slice, state, pos));
            ci.cancel();
        }
    }
}
