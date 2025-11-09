package org.polyfrost.legacytweaks.config;

import org.polyfrost.legacytweaks.LegacyTweaks;
import org.polyfrost.oneconfig.api.config.v1.Config;
import org.polyfrost.oneconfig.api.config.v1.annotations.Slider;
import org.polyfrost.oneconfig.api.config.v1.annotations.Switch;

public class LegacyTweaksConfig extends Config {
    public LegacyTweaksConfig() {
        super(LegacyTweaks.ID + ".json", LegacyTweaks.NAME, Category.QOL);

        loadFrom("patcher.toml");
    }

    //#if MC == 1.8.9
    @Switch(
            title = "Left Hand in First Person",
            description = "Render the first-person hand on the left of the screen."
    )
    public boolean leftHandInFirstPerson = false;
    //#endif

    @Switch(
            title = "Natural Capes",
            description = "Changes some physics in capes to fix rotation bugs and look more natural."
    )
    public boolean naturalCapes = false;

    @Switch(
            title = "Clean Projectiles",
            description = "Show projectiles 2 ticks after they're shot to stop them from obstructing your view."
    )
    public boolean cleanProjectiles;

    @Slider(
            title = "Distortion Effects (%)",
            description = "Changes the distortion effects (e.g. Nausea and nether portal distortion).",
            min = 0, max = 100
    )
    public int distortionEffect = 100;
}
