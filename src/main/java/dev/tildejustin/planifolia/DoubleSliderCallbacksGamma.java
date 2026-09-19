package dev.tildejustin.planifolia;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import net.minecraft.client.OptionInstance;
import org.jspecify.annotations.NonNull;

import java.util.Optional;

public enum DoubleSliderCallbacksGamma implements OptionInstance.SliderableValueSet<Double> {
    INSTANCE;

    @Override
    public @NonNull Optional<Double> validateValue(@NonNull Double value) {
        return value >= 0.0 && value <= 5.0 ? Optional.of(value) : Optional.empty();
    }

    @Override
    public double toSliderValue(@NonNull Double value) {
        return Planifolia.restrictGamma() ? value : value / 5;
    }

    @Override
    public @NonNull Double fromSliderValue(double progress) {
        return Planifolia.restrictGamma() ? progress : progress * 5;
    }

    @Override
    public @NonNull Codec<Double> codec() {
        return Codec.either(Codec.doubleRange(0.0, 5.0), Codec.BOOL).xmap(either -> either.map(value -> value, value -> value ? 1.0 : 0.0), Either::left);
    }
}
