package org.plugin;

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.modules.interaction.interaction.config.Interaction;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import raven.crystalcircuitry.command.CC_Command;
import raven.crystalcircuitry.power.CC_GC_PW_ON;

import javax.annotation.Nonnull;

/**
 * This class serves as the entrypoint for your plugin. Use the setup method to register into game registries or add
 * event listeners.
 */
public class CC_Plugin extends JavaPlugin {

    private static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();

    public CC_Plugin(@Nonnull JavaPluginInit init) {
        super(init);
        LOGGER.atInfo().log("Hello from " + this.getName() + " version " + this.getManifest().getVersion().toString());
    }

    @Override
    protected void setup() {
        LOGGER.atInfo().log("Setting up plugin " + this.getName());
        this.getCommandRegistry().registerCommand(new CC_Command(this.getName(), this.getManifest().getVersion().toString()));
        this.getCodecRegistry(Interaction.CODEC)
                .register("cc:cc_gc_pw_on", CC_GC_PW_ON.class, CC_GC_PW_ON.CODEC);
    }
}