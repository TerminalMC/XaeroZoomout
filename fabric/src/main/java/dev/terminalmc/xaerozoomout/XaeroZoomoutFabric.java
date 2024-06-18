/*
 * Copyright 2023, 2024 NotRyken
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.terminalmc.xaerozoomout;

import net.fabricmc.api.ClientModInitializer;

public class XaeroZoomoutFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        XaeroZoomout.init();
    }
}
