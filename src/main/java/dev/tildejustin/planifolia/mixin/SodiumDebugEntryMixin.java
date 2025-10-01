package dev.tildejustin.planifolia.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.caffeinemc.mods.sodium.client.gui.SodiumDebugEntry;
import net.minecraft.client.gui.hud.debug.DebugHudLines;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Collection;

@Mixin(value = SodiumDebugEntry.class, remap = false)
public abstract class SodiumDebugEntryMixin {
    @Shadow
    @Final
    private static Identifier DEBUG_GROUP;

    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Ljava/lang/String;formatted([Ljava/lang/Object;)Ljava/lang/String;"))
    private String removeColor(String instance, Object[] objects, Operation<String> original) {
        // Remove %s at the start and only pass the version argument
        return instance.substring(2).formatted(objects[1]);
    }

    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/debug/DebugHudLines;addLinesToSection(Lnet/minecraft/util/Identifier;Ljava/util/Collection;)V"))
    private void removeDebugGroup(DebugHudLines instance, Identifier identifier, Collection<String> strings, Operation<Void> original) {
        if (identifier.equals(DEBUG_GROUP)) {
            return;
        }
        original.call(instance, identifier, strings);
    }

}
