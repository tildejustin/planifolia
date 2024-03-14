package dev.tildejustin.planifolia.mixin;

import com.bawnorton.mixinsquared.TargetHandler;
import com.llamalad7.mixinextras.injector.wrapoperation.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.*;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = OptionsScreen.class, priority = 1005)
public abstract class OptionsScreenMixin extends Screen {
    protected OptionsScreenMixin(Text title) {
        super(title);
    }

    @Dynamic
    @Group(min = 1, max = 1)
    @TargetHandler(mixin = "me.jellysquid.mods.sodium.mixin.features.options.MixinOptionsScreen", name = "open")
    @WrapOperation(method = "@MixinSquared:Handler", at = @At(value = "INVOKE", target = "Lorg/spongepowered/asm/mixin/injection/callback/CallbackInfoReturnable;setReturnValue(Ljava/lang/Object;)V"), require = 0)
    private void openVanillaMenu1(CallbackInfoReturnable<Screen> instance, Object screen, Operation<Void> original) {
        openVanillaMenu(instance, screen, original);
    }

    @Dynamic
    @Group
    @TargetHandler(mixin = "me.jellysquid.mods.sodium.mixin.features.gui.hooks.settings.OptionsScreenMixin", name = "open")
    @WrapOperation(method = "@MixinSquared:Handler", at = @At(value = "INVOKE", target = "Lorg/spongepowered/asm/mixin/injection/callback/CallbackInfoReturnable;setReturnValue(Ljava/lang/Object;)V"), require = 0)
    private void openVanillaMenu2(CallbackInfoReturnable<Screen> instance, Object screen, Operation<Void> original) {
        openVanillaMenu(instance, screen, original);
    }

    @Unique
    private void openVanillaMenu(CallbackInfoReturnable<Screen> instance, Object screen, Operation<Void> original) {
        if (MinecraftClient.getInstance().world != null) {
            instance.setReturnValue(new VideoOptionsScreen(this, this.client.options));
        } else {
            original.call(instance, screen);
        }
    }
}
