/*
 * Copyright 2023, 2024 NotRyken
 * SPDX-License-Identifier: Apache-2.0
 */

package com.notryken.xaerozoomout.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import xaero.map.gui.GuiMap;

@Mixin(value = GuiMap.class, remap = false)
public abstract class MixinGuiMap {
    @ModifyConstant(method = "changeZoom", constant = @Constant(doubleValue = 0.0625))
    private double changeZoom2(double original) {
        return 0.0025;
    }
}
