package raven.crystalcircuitry.power;

import com.hypixel.hytale.protocol.BlockPosition;
import com.hypixel.hytale.server.core.universe.world.World;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class CC_PowerRegistry {

    private static final Map<Key, Boolean> POWER_MAP = new ConcurrentHashMap<>();

    private CC_PowerRegistry() {}

    public static void set(World world, BlockPosition pos, boolean powered) {
        POWER_MAP.put(new Key(world, pos), powered);
    }

    public static boolean get(World world, BlockPosition pos) {
        return POWER_MAP.getOrDefault(new Key(world, pos), false);
    }

    public static void clear(World world, BlockPosition pos) {
        POWER_MAP.remove(new Key(world, pos));
    }

    private static final class Key {
        private final World world;
        private final BlockPosition pos;

        private Key(World world, BlockPosition pos) {
            this.world = world;
            this.pos = pos;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Key)) return false;
            Key key = (Key) o;
            return world.equals(key.world) && pos.equals(key.pos);
        }

        @Override
        public int hashCode() {
            int result = world.hashCode();
            result = 31 * result + pos.hashCode();
            return result;
        }
    }
}
