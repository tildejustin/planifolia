package dev.tildejustin.planifolia;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import net.minecraft.client.option.SimpleOption;

import java.util.Optional;

public enum DoubleSliderCallbacksGamma implements SimpleOption.SliderCallbacks<Double> {
    INSTANCE;

    @Override
    public Optional<Double> validate(Double value) {
        return value >= 0.0 && value <= 5.0 ? Optional.of(value) : Optional.empty();
    }

    @Override
    public double toSliderProgress(Double value) {
        return Planifolia.restrictGamma() ? value : value / 5;
    }

    @Override
    public Double toValue(double progress) {
        return Planifolia.restrictGamma() ? progress : progress * 5;
    }

    @Override
    public Codec<Double> codec() {
        return Codec.either(Codec.doubleRange(0.0, 5.0), Codec.BOOL).xmap(either -> either.map(value -> value, value -> value ? 1.0 : 0.0), Either::left);
    }
}
