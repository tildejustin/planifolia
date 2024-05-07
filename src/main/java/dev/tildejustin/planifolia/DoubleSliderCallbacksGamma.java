package dev.tildejustin.planifolia;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import net.minecraft.client.MinecraftClient;
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
        // when not in a world, value may be is 0 -> 5, and is scaled to 0 -> 1 on the slider
        // when in a world, any value > 1 will be rendered at 1, via SliderWidgetMixin
        return MinecraftClient.getInstance().world == null ? value / 5 : value;
    }

    @Override
    public Double toValue(double progress) {
        // when not in a world, a slider progress is interpreted as being from 0 -> 5, when in a world it is the standard 0 -> 1
        return MinecraftClient.getInstance().world == null ? progress * 5 : progress;
    }

    @Override
    public Codec<Double> codec() {
        return Codec.either(Codec.doubleRange(0.0, 5.0), Codec.BOOL).xmap(either -> either.map(value -> value, value -> value ? 1.0 : 0.0), Either::left);
    }
}
