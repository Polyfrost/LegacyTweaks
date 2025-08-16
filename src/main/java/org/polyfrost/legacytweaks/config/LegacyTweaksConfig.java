package org.polyfrost.legacytweaks.config;

import org.polyfrost.legacytweaks.LegacyTweaks;
import org.polyfrost.oneconfig.api.config.v1.Config;
import org.polyfrost.oneconfig.api.config.v1.annotations.Switch;

public class LegacyTweaksConfig extends Config {
    public LegacyTweaksConfig() {
        super(LegacyTweaks.ID + ".json", LegacyTweaks.NAME, Category.QOL);
    }

    //#if MC == 1.8.9
    @Switch(
            title = "Use left hand",
            description = "Makes you left handed. (First person only)"
    )
    public boolean leftHanded = false;
    //#endif
}
