package me.cth451.transit.enums;

import net.minecraft.util.StringIdentifiable;

public enum TowardEnum implements StringIdentifiable {
    IN,
    OUT;

    @Override
    public String asString() {
        switch (this) {
            case IN:
                return "in";
            case OUT:
            default:
                return "out";
        }
    }
}