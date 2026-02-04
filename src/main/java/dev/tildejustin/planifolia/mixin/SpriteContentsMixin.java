package dev.tildejustin.planifolia.mixin;

import com.bawnorton.mixinsquared.TargetHandler;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.SpriteContents;
import net.minecraft.client.texture.SpriteDimensions;
import net.minecraft.resource.metadata.ResourceMetadata;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = SpriteContents.class, priority = 1500)
public class SpriteContentsMixin {
    @Mutable
    @Shadow
    @Final
    private NativeImage image;

    /**
     * We can't use a MixinExtras @Share here because we're not targeting the same method,
     * therefore we need to store it the good old way™
     */
    @Unique
    private static Identifier idRef = null;

    @Inject(method = "<init>", at = @At("HEAD"))
    private static void storeIdentifier(Identifier id, SpriteDimensions dimensions, NativeImage image, ResourceMetadata metadata, CallbackInfo ci) {
        idRef = id;
    }

    @Dynamic
    @TargetHandler(mixin = "net.caffeinemc.mods.sodium.mixin.features.textures.scan.SpriteContentsMixin", name = "sodium$beforeGenerateMipLevels", prefix = "wrapOperation")
    @Inject(method = "@MixinSquared:Handler", at = @At("HEAD"), cancellable = true)
    public void skipSodiumScan(SpriteContents instance, NativeImage nativeImage, Operation<Void> original, CallbackInfo ci) {
        if (idRef != null && idRef.getPath().contains("leaves")) {
            this.image = nativeImage;
            ci.cancel();
        }
    }
}
