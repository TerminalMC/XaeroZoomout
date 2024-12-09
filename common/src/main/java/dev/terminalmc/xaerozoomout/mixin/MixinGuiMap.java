/*
 * Copyright 2024 TerminalMC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
