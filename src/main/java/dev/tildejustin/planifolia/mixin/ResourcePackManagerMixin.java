package dev.tildejustin.planifolia.mixin;

import com.bawnorton.mixinsquared.TargetHandler;
import com.llamalad7.mixinextras.injector.wrapoperation.*;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.packs.repository.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Set;

@Mixin(value = PackRepository.class, priority = 1050)
public abstract class ResourcePackManagerMixin {
    @Dynamic
    @TargetHandler(mixin = "net.fabricmc.fabric.mixin.resource.PackRepositoryMixin", name = "construct")
    @WrapOperation(method = "@MixinSquared:Handler", at = @At(value = "INVOKE", target = "Ljava/util/Set;add(Ljava/lang/Object;)Z"))
    private boolean removeFabricDataPacks(Set<RepositorySource> providers, Object provider, Operation<Boolean> operation) {
        return FabricLoader.getInstance().isModLoaded("fabric") ? operation.call(providers, provider) : true;
    }
}
