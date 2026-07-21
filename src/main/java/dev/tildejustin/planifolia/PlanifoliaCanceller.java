package dev.tildejustin.planifolia;

import com.bawnorton.mixinsquared.api.MixinCanceller;
import net.fabricmc.loader.api.FabricLoader;

import java.util.*;

public class PlanifoliaCanceller implements MixinCanceller {
    @Override
    public boolean shouldCancel(List<String> targetClassNames, String mixinClassName) {
        if (Arrays.asList(
                "net.caffeinemc.mods.sodium.mixin.features.gui.hooks.debug.DebugEntryMemoryMixin",
                "net.caffeinemc.mods.sodium.mixin.features.gui.hooks.debug.DebugScreenEntryListMixin"
        ).contains(mixinClassName)) {
            return true;
        }

        return Arrays.asList(
                "net.fabricmc.fabric.mixin.resource.loader.client.ClientDataPackManagerMixin",
                "net.fabricmc.fabric.mixin.resource.loader.client.CreateWorldScreenMixin"
        ).contains(mixinClassName) && !FabricLoader.getInstance().isModLoaded("fabric-api");
    }
}
