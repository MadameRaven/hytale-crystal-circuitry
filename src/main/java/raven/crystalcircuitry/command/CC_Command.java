package raven.crystalcircuitry.command;

import com.hypixel.hytale.protocol.GameMode;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;

import javax.annotation.Nonnull;

/**
 * This is an example command that will simply confirm CC is loaded in game.
 */
public class CC_Command extends CommandBase {

    private final String pluginName;
    private final String pluginVersion;

    public CC_Command(String pluginName, String pluginVersion) {
        super("cc", "CrystalCircuitry: verify the mod is loaded (/cc).");
        this.setPermissionGroup(GameMode.Adventure); // Allows the command to be used by anyone, not just OP
        this.pluginName = pluginName;
        this.pluginVersion = pluginVersion;
    }

    @Override
    protected void executeSync(@Nonnull CommandContext ctx) {
        final String msg = "[CrystalCircuitry] ACTIVE — " + pluginName + " v" + pluginVersion + " (namespace: raven)";
        ctx.sendMessage(Message.raw(msg));
        System.out.println(msg);
    }
}