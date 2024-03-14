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
    @Group(min = 1, max = 1)
    @TargetHandler(mixin = "me.jellysquid.mods.sodium.mixin.features.options.MixinOptionsScreen", name = "open")
    @ModifyArg(method = "@MixinSquared:Handler", at = @At(value = "INVOKE", target = "Lorg/spongepowered/asm/mixin/injection/callback/CallbackInfoReturnable;setReturnValue(Ljava/lang/Object;)V"), require = 0)
    private Object openVanillaMenu1(Object original) {
        return MinecraftClient.getInstance().world != null ? new VideoOptionsScreen(this, this.client.options) : original;
    }

    @Dynamic
    @Group
    @TargetHandler(mixin = "me.jellysquid.mods.sodium.mixin.features.gui.hooks.settings.OptionsScreenMixin", name = "open")
    @ModifyArg(method = "@MixinSquared:Handler", at = @At(value = "INVOKE", target = "Lorg/spongepowered/asm/mixin/injection/callback/CallbackInfoReturnable;setReturnValue(Ljava/lang/Object;)V"), require = 0)
    private Object openVanillaMenu2(Object original) {
        return MinecraftClient.getInstance().world != null ? new VideoOptionsScreen(this, this.client.options) : original;
    }
}
