package dev.tildejustin.planifolia.mixin;

import net.caffeinemc.mods.sodium.client.render.chunk.RenderSectionManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = RenderSectionManager.class, remap = false)
public class RenderSectionManagerMixin {
    @Shadow
    @Final
    private ClientLevel level;

    @Inject(method = "getTotalSections", at = @At("HEAD"), cancellable = true)
    private void fixTotalSectionCount(CallbackInfoReturnable<Integer> cir) {
        int renderDistance = Minecraft.getInstance().options.getEffectiveRenderDistance() * 2 + 1;
        cir.setReturnValue(renderDistance * this.level.getSectionsCount() * renderDistance);
    }
}
