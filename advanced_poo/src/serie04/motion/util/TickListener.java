package serie04.motion.util;

import java.util.EventListener;

public interface TickListener extends EventListener {
    void tickOccurred(TickEvent e);
}
