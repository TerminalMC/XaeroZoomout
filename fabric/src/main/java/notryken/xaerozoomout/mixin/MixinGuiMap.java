package notryken.xaerozoomout.mixin;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xaero.map.animation.SlowingAnimation;
import xaero.map.gui.GuiMap;
import xaero.map.gui.IRightClickableElement;
import xaero.map.gui.ScreenBase;

@Mixin(value = GuiMap.class, remap = false)
public abstract class MixinGuiMap extends ScreenBase implements IRightClickableElement {
    @Shadow
    private int lastZoomMethod;
    @Shadow
    private SlowingAnimation cameraDestinationAnimX;
    @Shadow
    private SlowingAnimation cameraDestinationAnimZ;
    @Shadow
    private static double destScale;
    @Shadow
    private void closeDropdowns() {}

    protected MixinGuiMap(Screen parent, Screen escape, Component titleIn) {
        super(parent, escape, titleIn);
    }

    @Inject(method = "changeZoom", at = @At(value = "HEAD"), cancellable = true)
    private void changeZoom(double factor, int zoomMethod, CallbackInfo ci) {
        this.closeDropdowns();
        this.lastZoomMethod = zoomMethod;
        this.cameraDestinationAnimX = null;
        this.cameraDestinationAnimZ = null;
        if (hasControlDown()) {
            double destScaleBefore = destScale;
            if (destScale >= 1.0) {
                if (factor > 0.0) {
                    destScale = Math.ceil(destScale);
                } else {
                    destScale = Math.floor(destScale);
                }

                if (destScaleBefore == destScale) {
                    destScale += factor > 0.0 ? 1.0 : -1.0;
                }

                if (destScale == 0.0) {
                    destScale = 0.5;
                }
            } else {
                double reversedScale = 1.0 / destScale;
                double log2 = Math.log(reversedScale) / Math.log(2.0);
                if (factor > 0.0) {
                    log2 = Math.floor(log2);
                } else {
                    log2 = Math.ceil(log2);
                }

                destScale = 1.0 / Math.pow(2.0, log2);
                if (destScaleBefore == destScale) {
                    destScale = 1.0 / Math.pow(2.0, log2 + (double) (factor > 0.0 ? -1 : 1));
                }
            }
        } else {
            destScale *= Math.pow(1.2, factor);
        }

        if (destScale < 0.0025) {
            destScale = 0.0025;
        } else if (destScale > 50.0) {
            destScale = 50.0;
        }

        ci.cancel();
    }
}
