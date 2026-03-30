package dev.tildejustin.planifolia.mixin;

import com.bawnorton.mixinsquared.TargetHandler;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.client.texture.*;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = SpriteContents.class, priority = 1500)
public class SpriteContentsMixin {
    @Shadow
    @Final
    Identifier id;

    @Dynamic
    @TargetHandler(mixin = "net.caffeinemc.mods.sodium.mixin.features.textures.mipmaps.SpriteContentsMixin", name = "sodium$beforeGenerateMipLevels", prefix = "wrapOperation")
    // sodium$fillInTransparentPixelColors is an @Unique merged method
    @WrapWithCondition(method = "@MixinSquared:Handler", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_7764;sodium$fillInTransparentPixelColors(Lnet/minecraft/class_1011;)V"))
    public boolean skipSodiumFill(NativeImage nativeImage) {
        return !id.getPath().contains("leaves");
    }
}
