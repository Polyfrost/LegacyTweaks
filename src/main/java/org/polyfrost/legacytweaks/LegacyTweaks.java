package org.polyfrost.legacytweaks;

//#if FABRIC
//$$ import net.fabricmc.api.ModInitializer;
//#elseif FORGE
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
//#endif

import org.polyfrost.legacytweaks.config.LegacyTweaksConfig;

//#if FORGE-LIKE
@Mod(modid = LegacyTweaks.ID, name = LegacyTweaks.NAME, version = LegacyTweaks.VERSION)
//#endif
public class LegacyTweaks
        //#if FABRIC
        //$$ implements ModInitializer
        //#endif
{
    public static final String ID = "@MOD_ID@";
    public static final String NAME = "@MOD_NAME@";
    public static final String VERSION = "@MOD_VERSION@";

    public static LegacyTweaksConfig config;

    public static boolean isRenderingItemInFirstPerson;

    //#if FABRIC
    //$$ @Override
    //#elseif FORGE
    @Mod.EventHandler
    //#endif
    public void onInitialize(
            //#if FORGE
            FMLInitializationEvent event
            //#endif
    ) {
        config = new LegacyTweaksConfig();
    }
}
