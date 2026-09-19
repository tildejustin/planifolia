package dev.tildejustin.planifolia.mixin;

import dev.tildejustin.planifolia.Planifolia;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Inject(method = "joinWorld", at = @At("HEAD"))
    private void storeSetLevel(ClientWorld world, CallbackInfo ci) {
        if (world != null) Planifolia.setLevel = true;
    }
}
