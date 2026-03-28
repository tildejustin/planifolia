package dev.tildejustin.planifolia.mixin;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.components.debug.*;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DebugScreenEntries.class)
public class DebugHudEntriesMixin {
    @Unique
    private static final Identifier FABRIC_ACTIVE_RENDERER = Identifier.fromNamespaceAndPath("fabric", "active_renderer");

    @Inject(method = "register(Lnet/minecraft/resources/Identifier;Lnet/minecraft/client/gui/components/debug/DebugScreenEntry;)Lnet/minecraft/resources/Identifier;", at = @At("HEAD"), cancellable = true)
    private static void preventEntries(Identifier id, DebugScreenEntry entry, CallbackInfoReturnable<Identifier> cir) {
        if (id.equals(FABRIC_ACTIVE_RENDERER) && !FabricLoader.getInstance().isModLoaded("fabric")) {
            cir.setReturnValue(id);
        }
    }
}
