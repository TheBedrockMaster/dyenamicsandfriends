package cy.jdkdigital.dyenamicsandfriends.event;

import cy.jdkdigital.dyenamicsandfriends.DyenamicsAndFriends;
import cy.jdkdigital.dyenamicsandfriends.compat.*;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = DyenamicsAndFriends.MODID)
public class ModEventHandler
{
    @SubscribeEvent
    public static void buildTabContents(BuildCreativeModeTabContentsEvent event) {
        if (ModList.get().isLoaded("create")) {
        }
        if (ModList.get().isLoaded("another_furniture")) {
            AnotherFurnitureCompat.buildTabContents(event);
        }
        if (ModList.get().isLoaded("botanypots")) {
            BotanyPotsCompat.buildTabContents(event);
        }
        if (ModList.get().isLoaded("comforts")) {
            ComfortsCompat.buildTabContents(event);
        }
        if (ModList.get().isLoaded("elevatorid")) {
            ElevatoridCompat.buildTabContents(event);
        }
        if (ModList.get().isLoaded("farmersdelight")) {
        }
        if (ModList.get().isLoaded("supplementaries")) {
        }
        if (ModList.get().isLoaded("suppsquared")) {
        }
        if (ModList.get().isLoaded("regions_unexplored")) {
            RegionsUnexploredCompat.buildTabContents(event);
        }
        if (ModList.get().isLoaded("quark")) {
        }
        if (ModList.get().isLoaded("handcrafted")) {
            HandcraftedCompat.buildTabContents(event);
        }
        if (ModList.get().isLoaded("furnish")) {
            FurnishCompat.buildTabContents(event);
        }
        if (ModList.get().isLoaded("chalk")) {
//            ChalkCompat.buildTabContents(event);
        }
        if (ModList.get().isLoaded("oreganized")) {
        }
        if (ModList.get().isLoaded("ceramics")) {
        }
        if (ModList.get().isLoaded("glazedresymmetry")) {
        }
        if (ModList.get().isLoaded("clayworks")) {
        }
        if (ModList.get().isLoaded("the_bumblezone")) {
        }
        if (ModList.get().isLoaded("sleep_tight")) {
            SleepTightCompat.buildTabContents(event);
        }
        if (ModList.get().isLoaded("connectedglass")) {
            ConnectedGlassCompat.buildTabContents(event);
        }
    }
}
