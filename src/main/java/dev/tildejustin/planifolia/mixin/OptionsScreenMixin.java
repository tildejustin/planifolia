package dev.tildejustin.planifolia.mixin;

import com.bawnorton.mixinsquared.TargetHandler;
import com.llamalad7.mixinextras.injector.wrapoperation.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.*;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = OptionsScreen.class, priority = 1005)
public abstract class OptionsScreenMixin extends Screen {
    protected OptionsScreenMixin(Text title) {
        super(title);
    }

    @Dynamic
    @TargetHandler(mixin = "me.jellysquid.mods.sodium.mixin.features.options.MixinOptionsScreen", name = "open")
    @WrapOperation(method = "@MixinSquared:Handler", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;setScreen(Lnet/minecraft/client/gui/screen/Screen;)V"))
    private void openVanillaMenu(MinecraftClient instance, Screen screen, Operation<Void> original) {
        if (MinecraftClient.getInstance().world != null) {
            this.client.setScreen(new VideoOptionsScreen(this, this.client.options));
        } else {
            original.call(instance, screen);
        }
    }
}
