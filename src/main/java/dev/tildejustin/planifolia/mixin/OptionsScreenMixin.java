package dev.tildejustin.planifolia.mixin;

import com.bawnorton.mixinsquared.TargetHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.*;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(value = OptionsScreen.class, priority = 1005)
public abstract class OptionsScreenMixin extends Screen {
    protected OptionsScreenMixin(Text title) {
        super(title);
    }

    @Dynamic
    @TargetHandler(mixin = "net.caffeinemc.mods.sodium.mixin.features.gui.hooks.settings.OptionsScreenMixin", name = "open")
    @ModifyArg(method = "@MixinSquared:Handler", at = @At(value = "INVOKE", target = "Lorg/spongepowered/asm/mixin/injection/callback/CallbackInfoReturnable;setReturnValue(Ljava/lang/Object;)V"))
    private Object openVanillaMenu(Object original) {
        return MinecraftClient.getInstance().world != null ? new VideoOptionsScreen(this, this.client.options) : original;
    }
}
