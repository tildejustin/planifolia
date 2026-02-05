package dev.tildejustin.planifolia.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resource.ResourcePack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(targets = "net.fabricmc.fabric.impl.resource.loader.ModNioResourcePack$1")
public abstract class ModNioResourcePackMixin {
    @Dynamic
    @WrapWithCondition(
            method = "visitFile(Ljava/nio/file/Path;Ljava/nio/file/attribute/BasicFileAttributes;)Ljava/nio/file/FileVisitResult;",
            // net/minecraft/resource/ResourcePack$ResultConsumer, loom hates me
            at = @At(value = "INVOKE", target = "Lnet/minecraft/class_3262$class_7664;accept(Ljava/lang/Object;Ljava/lang/Object;)V")
    )
    private boolean stopSodiumFromReplacingMinecraftAssets(ResourcePack.ResultConsumer visitor, Object identifier, Object inputSupplier) {
        Identifier id = (Identifier) identifier;
        return !("minecraft".equals(id.getNamespace()) && id.getPath().startsWith("textures") && !FabricLoader.getInstance().isModLoaded("fabric"));
    }
}
