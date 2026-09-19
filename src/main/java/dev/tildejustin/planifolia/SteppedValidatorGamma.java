package dev.tildejustin.planifolia;

import net.caffeinemc.mods.sodium.api.config.option.SteppedValidator;

public class SteppedValidatorGamma implements SteppedValidator {
    @Override
    public int min() {
        return 0;
    }

    @Override
    public int max() {
        return Planifolia.restrictGamma() ? 100 : 500;
    }

    @Override
    public int step() {
        return 1;
    }

    @Override
    public boolean isValueValid(int value) {
        return value >= 0 && value <= 500;
    }
}
