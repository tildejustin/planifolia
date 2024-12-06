package dev.tildejustin.planifolia.mixin;

import net.minecraft.client.gui.hud.DebugHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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
            list.add(version);
            if (version.equals("Sodium Renderer")) {
                list.add(list.remove(i));
            }
            while (list.size() > i && !list.get(i).isEmpty() && !list.get(i).startsWith("Resetting")) {
                list.remove(i);
            }
            break;
        }
    }
}
