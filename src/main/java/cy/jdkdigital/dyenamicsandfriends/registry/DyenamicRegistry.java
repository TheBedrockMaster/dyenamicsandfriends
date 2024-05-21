package cy.jdkdigital.dyenamicsandfriends.registry;

import cy.jdkdigital.dyenamics.core.init.BlockInit;
import cy.jdkdigital.dyenamics.core.util.DyenamicDyeColor;
import cy.jdkdigital.dyenamicsandfriends.DyenamicsAndFriends;
import cy.jdkdigital.dyenamicsandfriends.compat.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class DyenamicRegistry
{
    public static final List<String> MODS = new ArrayList<>()
    {{
        add("another_furniture");
        add("productivebees");
        add("connectedglass");
        add("botanypots");
        add("comforts");
        add("elevatorid");
        add("furnish");
        add("regions_unexplored");
        add("sleep_tight");
//        add("handcrafted"); // OUT! it's hardcoded to only work with the 16 vanilla colors
//        add("ceramics");
//        add("chalk");
//        add("clayworks");
//        add("create");
//        add("farmersdelight");
//        add("glazedresymmetry");
//        add("oreganized");
//        add("quark");
//        add("the_bumblezone");
//        add("supplementaries");
//        add("suppsquared");
    }};

    public static void setup() {
//        QuarkCompat.setup();
    }

    public static void registerCompatBlocks() {
        DyenamicsAndFriends.LOGGER.info("registerCompatBlocks");
        for (DyenamicDyeColor color : DyenamicDyeColor.dyenamicValues()) {
            // Vanilla
            // - banners
            //

            if (ModList.get().isLoaded("create")) {
//                CreateCompat.registerBlocks(color);
            }
            if (ModList.get().isLoaded("createdeco")) {
                // decal item
                // zinc lamp
            }
            if (ModList.get().isLoaded("another_furniture")) {
                AnotherFurnitureCompat.registerBlocks(color);
            }
            if (ModList.get().isLoaded("botanypots")) {
                BotanyPotsCompat.registerBlocks(color);
            }
            if (ModList.get().isLoaded("comforts")) {
                ComfortsCompat.registerBlocks(color);
            }
            if (ModList.get().isLoaded("elevatorid")) {
                ElevatoridCompat.registerBlocks(color);
            }
            if (ModList.get().isLoaded("farmersdelight")) {
//                FarmersDelightCompat.registerBlocks(color);
            }
            if (ModList.get().isLoaded("reliquary")) {
                // pedestal
                // pedestal (display only)
            }
            if (ModList.get().isLoaded("snowyspirit")) {
                // glow lights
                // gumdrop
            }
            if (ModList.get().isLoaded("supplementaries")) {
                // blackboard
                // flag
//                SupplementariesCompat.registerBlocks(color);
            }
            if (ModList.get().isLoaded("suppsquared")) {
//                SupplementariesSquaredCompat.registerBlocks(color);
            }
            if (ModList.get().isLoaded("regions_unexplored")) {
                RegionsUnexploredCompat.registerBlocks(color);
            }
            if (ModList.get().isLoaded("waystones")) {
                // sharestone
            }
            if (ModList.get().isLoaded("quark")) {
//                QuarkCompat.registerBlocks(color);
//                QuarkCompat.registerItems(color);
            }
            if (ModList.get().isLoaded("handcrafted")) {
//                HandcraftedCompat.registerBlocks(color);
//                HandcraftedCompat.registerItems(color);
            }
            if (ModList.get().isLoaded("furnish")) {
                FurnishCompat.registerBlocks(color);
            }
            if (ModList.get().isLoaded("skinnedlanterns")) {
                // paper lantern
                // paper soul lantern
                // ornament lantern
                // ornament soul lantern
            }
            if (ModList.get().isLoaded("nightlights")) {
                // mushroom night light
                // hanging lights
                // octopus night light
                // frog night light
                // fairy lights
            }
            if (ModList.get().isLoaded("cfm")) { // crayfish

            }
            if (ModList.get().isLoaded("chalk")) {
//                ChalkCompat.registerBlocks(color);
//                ChalkCompat.registerItems(color);
            }
            if (ModList.get().isLoaded("oreganized")) {
//                OreganizedCompat.registerBlocks(color);
            }
            if (ModList.get().isLoaded("ceramics")) {
//                CeramicsCompat.registerBlocks(color);
            }
            if (ModList.get().isLoaded("glazedresymmetry")) {
//                GlazedResymmetryCompat.registerBlocks(color);
            }
            if (ModList.get().isLoaded("clayworks")) {
//                ClayworksCompat.registerBlocks(color);
            }
            if (ModList.get().isLoaded("the_bumblezone")) {
//                BumblezoneCompat.registerBlocks(color);
            }
            if (ModList.get().isLoaded("sleep_tight")) {
                SleepTightCompat.registerBlocks(color);
            }
            if (ModList.get().isLoaded("connectedglass")) {
                ConnectedGlassCompat.registerBlocks(color);
            }
        }
    }

    public static void clientRegister() {
        if (ModList.get().isLoaded("connectedglass")) {
            ConnectedGlassCompat.Client.register();
        }
    }

    public static RegistryObject<? extends Block> registerBlock(final String name, final Supplier<? extends Block> sup, boolean registerItem) {
        var block = DyenamicsAndFriends.BLOCKS.register(name, sup);
        if (registerItem) {
            registerItem(name, () -> new BlockItem(block.get(), new Item.Properties()));
        }
        return block;
    }

    public static RegistryObject<? extends Block> registerBlock(final String name, final Supplier<? extends Block> sup, @Nullable Supplier<Item> itemSupplier) {
        var block = DyenamicsAndFriends.BLOCKS.register(name, sup);
        if (itemSupplier != null) {
            registerItem(name, itemSupplier);
        }
        return block;
    }

    public static RegistryObject<? extends Item> registerItem(final String name, @Nullable Supplier<Item> itemSupplier) {
        return DyenamicsAndFriends.ITEMS.register(name, itemSupplier);
    }

    public static <E extends BlockEntity, T extends BlockEntityType<E>> Supplier<T> registerBlockEntity(String id, Supplier<T> supplier) {
        return DyenamicsAndFriends.BLOCK_ENTITIES.register(id, supplier);
    }

    public static <E extends BlockEntity> BlockEntityType<E> createBlockEntityType(BlockEntityType.BlockEntitySupplier<E> factory, Block... blocks) {
        return BlockEntityType.Builder.of(factory, blocks).build(null);
    }

    public static void registerBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        if (ModList.get().isLoaded("botanypots")) {
            BotanyPotsCompat.Client.registerBlockEntityRenderers(event);
        }
        if (ModList.get().isLoaded("comforts")) {
            ComfortsCompat.Client.registerBlockEntityRenderers(event);
        }
        if (ModList.get().isLoaded("farmersdelight")) {
//            FarmersDelightCompat.Client.registerBlockEntityRenderers(event);
        }
        if (ModList.get().isLoaded("furnish")) {
            FurnishCompat.Client.registerBlockEntityRenderers(event);
        }
        if (ModList.get().isLoaded("ceramics")) {
//            CeramicsCompat.Client.registerBlockEntityRenderers(event);
        }
        if (ModList.get().isLoaded("sleep_tight")) {
            SleepTightCompat.Client.registerBlockEntityRenderers(event);
        }
        if (ModList.get().isLoaded("supplementaries")) {
//            SupplementariesCompat.Client.registerBlockEntityRenderers(event);
        }
    }

    public static void registerBlockColorHandlers(RegisterColorHandlersEvent.Block event) {
        if (ModList.get().isLoaded("chalk")) {
//            ChalkCompat.Client.registerBlockColors(event);
        }
        if (ModList.get().isLoaded("elevatorid")) {
            ElevatoridCompat.Client.registerBlockColors(event);
        }
    }

    public static void registerBlockRendering(FMLClientSetupEvent event) {
        if (ModList.get().isLoaded("ceramics")) {
//            CeramicsCompat.Client.registerBlockRendering();
        }
        if (ModList.get().isLoaded("supplementaries")) {
//            SupplementariesCompat.Client.registerBlockRendering();
        }
        if (ModList.get().isLoaded("suppsquared")) {
//            SupplementariesSquaredCompat.Client.registerBlockRendering();
        }
        if (ModList.get().isLoaded("chalk")) {
//            ChalkCompat.Client.registerBlockRendering();
        }
    }

//    public static void onTextureStitch(TextureStitchEvent.Pre event) {
//        if (ModList.get().isLoaded("comforts")) {
//            ComfortsCompat.stitchTextures(event);
//        }
//        if (ModList.get().isLoaded("farmersdelight")) {
////            FarmersDelightCompat.stitchTextures(event);
//        }
//        if (ModList.get().isLoaded("sleep_tight")) {
//            SleepTightCompat.stitchTextures(event);
//        }
//    }

    public static void onModelBake(ModelEvent.ModifyBakingResult event) {
        if (ModList.get().isLoaded("chalk")) {
//            ChalkCompat.Client.bakeModel(event); // MOVE ModelEvent.BakingCompleted
        }
        if (ModList.get().isLoaded("elevatorid")) {
            ElevatoridCompat.Client.bakeModel(event);
        }
    }

    public static void onBlockInteract(PlayerInteractEvent event) {
        if (ModList.get().isLoaded("productivebees")) {
            ProductiveBeesCompat.blockInteract(event);
        }
    }

    public static void onEntityPlace(BlockEvent.EntityPlaceEvent event) {
        if (ModList.get().isLoaded("furnish")) {
            FurnishCompat.entityPlace(event);
        }
    }

    public static void onPlayerRightClick(PlayerInteractEvent.RightClickBlock event) {
        if (ModList.get().isLoaded("farmersdelight")) {
//            FarmersDelightCompat.playerRightClick(event);
        }
    }

    public static Block getDyenamicsBlock(DyenamicDyeColor color, String block) {
        return BlockInit.DYED_BLOCKS.get(color.getSerializedName()).get(block).get();
    }
}
