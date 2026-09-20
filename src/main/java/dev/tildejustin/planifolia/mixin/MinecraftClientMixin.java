package dev.tildejustin.planifolia.mixin;

import dev.tildejustin.planifolia.Planifolia;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Shadow
    @Nullable
    public ClientWorld world;

    @Inject(method = {"joinWorld", "method_1481(Lnet/minecraft/class_638;)V"}, at = @At("TAIL"))
    private void storeSetLevel(CallbackInfo ci) {
        if (this.world != null) Planifolia.setLevel = true;
    }
}
