package dev.tildejustin.planifolia.mixin;

import com.bawnorton.mixinsquared.TargetHandler;
import net.minecraft.client.texture.*;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = SpriteContents.class, priority = 1500)
public class SpriteContentsMixin {
    @Mutable
    @Shadow
    @Final
    private NativeImage image;

    @TargetHandler(mixin = "me.jellysquid.mods.sodium.mixin.features.textures.mipmaps.SpriteContentsMixin", name = "sodium$beforeGenerateMipLevels", prefix = "redirect")
    @Inject(method = "@MixinSquared:Handler", at = @At("HEAD"), cancellable = true, require = 0)
    public void skipSodiumScan(SpriteContents instance, NativeImage nativeImage, Identifier identifier, CallbackInfo ci) {
        if (identifier != null && identifier.getPath().contains("leaves")) {
            this.image = nativeImage;
            ci.cancel();
        }
    }
}
