package dev.tildejustin.planifolia.mixin;

import com.bawnorton.mixinsquared.TargetHandler;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.hud.DebugHud;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

import java.util.List;

@Mixin(value = DebugHud.class, priority = 1050)
public abstract class DebugHudMixin {
    @Inject(method = "getRightText", at = @At(value = "RETURN"))
    private void minimizeSodiumText(CallbackInfoReturnable<List<String>> cir) {
        List<String> list = cir.getReturnValue();
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i).startsWith("Allocated:")) {
                list.remove(i + 1);
            }
            if (!list.get(i).contains("Sodium Renderer")) {
                continue;
            }
            list.remove(--i);
            list.add("");
            String version = list.remove(i);
            if (version.equals("Sodium Renderer")) {
                version += " " + list.remove(i);
            }
            list.add(version.replaceAll("§(?i)[0-9a-fk-or]", ""));
            while (list.size() > i && !list.get(i).isEmpty() && !list.get(i).startsWith("Resetting")) {
                list.remove(i);
            }
            break;
        }
    }

    @Dynamic
    @TargetHandler(mixin = "net.fabricmc.fabric.mixin.client.rendering.DebugHudMixin", name = "getLeftText")
    @Inject(method = "@MixinSquared:Handler", at = @At("HEAD"), cancellable = true)
    private void getLeftText(CallbackInfo ci) {
        if (!FabricLoader.getInstance().isModLoaded("fabric")) {
            ci.cancel();
        }
    }
}
