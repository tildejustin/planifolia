package dev.tildejustin.planifolia;

import com.bawnorton.mixinsquared.api.MixinCanceller;

import java.util.*;

public class PlanifoliaCanceller implements MixinCanceller {
    @Override
    public boolean shouldCancel(List<String> targetClassNames, String mixinClassName) {
        return Arrays.asList(
                "me.jellysquid.mods.sodium.mixin.features.options.MixinOptionsScreen"
        ).contains(mixinClassName);
    }
}
