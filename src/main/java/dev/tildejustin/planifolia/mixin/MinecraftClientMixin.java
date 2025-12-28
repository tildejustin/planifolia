package dev.tildejustin.planifolia.mixin;

import net.caffeinemc.mods.sodium.mixin.features.gui.hooks.debug.DebugScreenEntriesAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Inject(method = "run", at = @At("HEAD"))
    private void removeSodiumDebugHudEntry(CallbackInfo ci) {
        // Note for porting, sodium does a gross accessor to add its debug hud entry, this removes it, in future
        // versions they may use something from Fabric API, so adjust accordingly
        assert DebugScreenEntriesAccessor.getEntries() != null;
        DebugScreenEntriesAccessor.getEntries().remove(Identifier.of("sodium", "debug_full"));
        DebugScreenEntriesAccessor.getEntries().remove(Identifier.of("sodium", "debug_reduced"));
    }
}
