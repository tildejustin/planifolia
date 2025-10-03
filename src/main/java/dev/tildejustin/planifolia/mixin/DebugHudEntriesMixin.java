package dev.tildejustin.planifolia.mixin;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.hud.debug.DebugHudEntries;
import net.minecraft.client.gui.hud.debug.DebugHudEntry;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DebugHudEntries.class)
public class DebugHudEntriesMixin {
    @Unique
    private static final Identifier FABRIC_ACTIVE_RENDERER = Identifier.of("fabric", "active_renderer");
    @Unique
    private static final Identifier SODIUM = Identifier.of("sodium", "sodium");

    @Inject(method = "register", at = @At("HEAD"), cancellable = true)
    private static void preventEntries(Identifier id, DebugHudEntry entry, CallbackInfoReturnable<Identifier> cir) {
        if (id.equals(SODIUM)) {
            cir.setReturnValue(id);
        }
        if (id.equals(FABRIC_ACTIVE_RENDERER) && !FabricLoader.getInstance().isModLoaded("fabric")) {
            cir.setReturnValue(id);
        }
    }
}
