package dev.tildejustin.planifolia;

import net.fabricmc.loader.api.FabricLoader;

public class Planifolia {
    public static boolean setLevel = false;
    private static final boolean draftoutPresent = FabricLoader.getInstance().isModLoaded("draftout");

    public static boolean restrictGamma() {
        return setLevel && !draftoutPresent;
    }
}
