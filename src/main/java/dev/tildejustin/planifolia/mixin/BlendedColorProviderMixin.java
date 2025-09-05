package dev.tildejustin.planifolia.mixin;

import me.jellysquid.mods.sodium.client.model.quad.ModelQuadView;
import me.jellysquid.mods.sodium.client.world.WorldSlice;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@Mixin(targets = "me.jellysquid.mods.sodium.client.model.quad.blender.BlendedColorProvider", remap = false)
public abstract class BlendedColorProviderMixin<T> {
    @Shadow
    protected abstract int getColor(WorldSlice levelSlice, T t, BlockPos blockPos);

    @Dynamic
    @Inject(method = "getColors", at = @At("HEAD"), cancellable = true)
    private void disableBiomeBlendingWhenAppropriate(WorldSlice slice, BlockPos pos, BlockPos.Mutable scratchPos, T state, ModelQuadView quad, int[] output, CallbackInfo ci) {
        if (MinecraftClient.getInstance().options.getBiomeBlendRadius().getValue() == 0) {
            Arrays.fill(output, this.getColor(slice, state, pos));
            ci.cancel();
        }
    }
}
