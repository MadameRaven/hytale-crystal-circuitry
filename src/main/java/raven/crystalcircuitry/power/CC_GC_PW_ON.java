package raven.crystalcircuitry.power;

import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.protocol.BlockPosition;
import com.hypixel.hytale.protocol.InteractionState;
import com.hypixel.hytale.protocol.InteractionType;
import com.hypixel.hytale.server.core.asset.type.blocktype.config.BlockType;
import com.hypixel.hytale.server.core.entity.InteractionContext;
import com.hypixel.hytale.server.core.modules.interaction.interaction.CooldownHandler;
import com.hypixel.hytale.server.core.modules.interaction.interaction.config.SimpleInstantInteraction;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nonnull;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CC_GC_PW_ON extends SimpleInstantInteraction {

    private static final Logger LOG = Logger.getLogger("CrystalCircuitry");

    public static final BuilderCodec<CC_GC_PW_ON> CODEC = BuilderCodec.builder(
            CC_GC_PW_ON.class, CC_GC_PW_ON::new, SimpleInstantInteraction.CODEC).build();

    @Override
    protected void firstRun(@Nonnull InteractionType interactionType, @Nonnull InteractionContext interactionContext, @Nonnull CooldownHandler cooldownHandler) {
        // 1️⃣ Target block
        final BlockPosition pos = interactionContext.getTargetBlock();
        if (pos == null) {
            LOG.warning("[CC] CC_GC_PW_ON fired but target block was null");
            interactionContext.getState().state = InteractionState.Failed;
            return;
        }

        // 2️⃣ World
        final CommandBuffer<EntityStore> commandBuffer = interactionContext.getCommandBuffer();
        if (commandBuffer == null || commandBuffer.getExternalData() == null) {
            interactionContext.getState().state = InteractionState.Failed;
            return;
        }

        final World world = commandBuffer.getExternalData().getWorld();
        if (world == null) {
            interactionContext.getState().state = InteractionState.Failed;
            return;
        }

        LOG.log(Level.INFO, "[CC] CC_GC_PW_ON target pos = {0},{1},{2}", new Object[]{pos.x, pos.y, pos.z});

        // 3️⃣ Read block type at position
        final BlockType type = world.getBlockType(pos.x, pos.y, pos.z);
        if (type == null) {
            LOG.warning("[CC] world.getBlockType returned null at target position");
            return;
        }

        LOG.log(Level.INFO, "[CC] BlockType at target = {0}", type); // prints toString; good enough for now

        // 4️⃣ Determine power by comparing BlockType identity
        final boolean powered;
        if (type == GC_BlockTypes.GREEN_SWITCH_ON) {
            powered = true;
        } else if (type == GC_BlockTypes.GREEN_SWITCH_OFF) {
            powered = false;
        } else {
            // Not a green switch variant
            return;
        }

        LOG.log(Level.INFO, "[CC] Computed powered={0}", powered);

        // 5️⃣ Store per-switch-instance power
        CC_PowerRegistry.set(world, pos, powered);

        // ✅ immediate readback test
        boolean readBack = CC_PowerRegistry.get(world, pos);
        LOG.log(Level.INFO, "[CC] Registry readback powered={0}", readBack);
    }
}