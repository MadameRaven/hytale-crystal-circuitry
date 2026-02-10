package raven.crystalcircuitry.power;

import com.hypixel.hytale.server.core.asset.type.blocktype.config.BlockType;

public final class GC_BlockTypes {

    private GC_BlockTypes() {}

    // These strings must exactly match your block asset keys
    public static final String GREEN_SWITCH_ON_KEY  = "cc:CC_GreenSwitchOn";
    public static final String GREEN_SWITCH_OFF_KEY = "cc:CC_GreenSwitchOff";

    public static final BlockType GREEN_SWITCH_ON  =
            BlockType.fromString(GREEN_SWITCH_ON_KEY);

    public static final BlockType GREEN_SWITCH_OFF =
            BlockType.fromString(GREEN_SWITCH_OFF_KEY);
}
