package dev.tildejustin.planifolia.mixin;

import dev.tildejustin.planifolia.Planifolia;
import net.caffeinemc.mods.sodium.mixin.features.gui.hooks.debug.DebugScreenEntriesAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Minecraft.class, priority = 900)
public abstract class MinecraftClientMixin {
    // need to run after client modinit (top of <init>) and before srapi postlaunch (bottom of <init>)
    @Inject(method = "<init>", at = @At("TAIL"))
    private static void removeSodiumDebugHudEntry(CallbackInfo ci) {
        // Note for porting, sodium does a gross accessor to add its debug hud entry, this removes it, in future
        // versions they may use something from Fabric API, so adjust accordingly
        DebugScreenEntriesAccessor.sodium$getEntries().remove(Identifier.fromNamespaceAndPath("sodium", "debug_full"));
        DebugScreenEntriesAccessor.sodium$getEntries().remove(Identifier.fromNamespaceAndPath("sodium", "debug_reduced"));
        DebugScreenEntriesAccessor.sodium$getEntries().remove(Identifier.fromNamespaceAndPath("sodium", "fps_percentiles"));
        DebugScreenEntriesAccessor.sodium$getEntries().remove(Identifier.fromNamespaceAndPath("sodium", "buffer_arena"));
    }

    @Inject(method = "setLevel", at = @At("HEAD"))
    private void storeSetLevel(ClientLevel level, CallbackInfo ci) {
        if (level != null) Planifolia.setLevel = true;
    }
}
