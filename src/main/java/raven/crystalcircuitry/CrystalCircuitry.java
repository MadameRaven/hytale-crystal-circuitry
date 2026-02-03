package raven.crystalcircuitry;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import raven.crystalcircuitry.command.CC_Command;
import javax.annotation.Nonnull;
import java.util.logging.Level;

/**
 * Main plugin class.
 * @author MadameRaven
 * @version 1.0.0
 */
public class CrystalCircuitry extends JavaPlugin {

    private static CrystalCircuitry instance;

    /**
     * Constructor - Called when plugin is loaded.
     */
    public CrystalCircuitry(@Nonnull JavaPluginInit init) {
        super(init);
        instance = this;
        getLogger().at(Level.INFO).log("[CrystalCircuitry] Plugin loaded!");
    }

    /**
     * Get plugin instance.
     */
    public static CrystalCircuitry getInstance() {
        return instance;
    }

    /**
     * Called when plugin is set up.
     */
    @Override
    protected void setup() {
        getLogger().at(Level.INFO).log("[CrystalCircuitry] Plugin setup!");

        // TODO: Initialize your plugin here
        // - Load configuration
        // - Register event listeners
        // - Register commands
        // - Start services
        registerEvents();
        registerCommands();
    }

    /**
     * Called when plugin is enabled.
     */
    @Override
    protected void start() {
        getLogger().at(Level.INFO).log("[CrystalCircuitry] Plugin enabled!");
    }

    /**
     * Called when plugin is disabled.
     */
    @Override
    public void shutdown() {
        getLogger().at(Level.INFO).log("[CrystalCircuitry] Plugin disabled!");

        // TODO: Cleanup your plugin here
        // - Save data
        // - Stop services
        // - Close connections
    }

    /**
     * Register your commands here.
     */
    private void registerEvents() {

    }

    /**
     * Register your commands here.
     */
    private void registerCommands() {
        getLogger().at(Level.INFO).log("[CrystalCircuitry] Registering commands...");

        this.getCommandRegistry().registerCommand(
                new CC_Command(this.getName(), this.getManifest().getVersion().toString())
        );

        getLogger().at(Level.INFO).log("[CrystalCircuitry] Registered /cc command.");
    }

}
