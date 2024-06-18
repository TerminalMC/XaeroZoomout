/*
 * Copyright 2023, 2024 NotRyken
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.terminalmc.xaerozoomout.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import xaero.map.gui.GuiMap;

@Mixin(value = GuiMap.class, remap = false)
public abstract class MixinGuiMap {
    @ModifyExpressionValue(
            method = "changeZoom",
            at = @At(
                    value = "CONSTANT",
                    args = "doubleValue=0.0625"
            )
    )
    private double setMin(double original) {
        return 0.0025;
    }
}
