package xyz.plooshi.startmaximized;

import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.WindowEventHandler;
import net.minecraft.client.WindowSettings;
import net.minecraft.client.util.MonitorTracker;
import net.minecraft.client.util.Window;
import org.lwjgl.glfw.GLFW;

// most of this is ripped from sodium tbh

@Mixin(Window.class)
public abstract class MainMixin {
	
	@Shadow
	@Final
	private long handle;
	
	@Inject(method = "<init>", at = @At(value = "INVOKE_ASSIGN", target = "Lorg/lwjgl/glfw/GLFW;glfwCreateWindow(IILjava/lang/CharSequence;JJ)J", shift = At.Shift.BEFORE))
	private void modifyRequestedContext(WindowEventHandler eventHandler, MonitorTracker monitorTracker, WindowSettings settings, String videoMode, String title, CallbackInfo ci) {
		GLFW.glfwWindowHint(GLFW.GLFW_MAXIMIZED, GLFW.GLFW_TRUE);
		GLFW.glfwMaximizeWindow(this.handle);
	}
}
