package cy.jdkdigital.dyenamicsandfriends.compat;

import cy.jdkdigital.dyenamics.core.util.DyenamicDyeColor;
import cy.jdkdigital.dyenamicsandfriends.registry.DyenamicRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

public class RegionsUnexploredCompat
{
    private static final Map<DyenamicDyeColor, RegistryObject<? extends Block>> PLANKS = new HashMap<>();
    private static final Map<DyenamicDyeColor, RegistryObject<? extends Block>> STAIRS = new HashMap<>();
    private static final Map<DyenamicDyeColor, RegistryObject<? extends Block>> SLABS = new HashMap<>();

    public static void registerBlocks(DyenamicDyeColor color) {
        String prefix = "regions_unexplored_" + color.getSerializedName();
        PLANKS.put(color, DyenamicRegistry.registerBlock(prefix + "_painted_planks", () -> new Block(BlockBehaviour.Properties.of().mapColor(color.getMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD).lightLevel(state -> color.getLightValue())), true));
        STAIRS.put(color, DyenamicRegistry.registerBlock(prefix + "_painted_stairs", () -> new StairBlock(() -> PLANKS.get(color).get().defaultBlockState(),BlockBehaviour.Properties.of().mapColor(color.getMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD).lightLevel(state -> color.getLightValue())), true));
        SLABS.put(color, DyenamicRegistry.registerBlock(prefix + "_painted_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(color.getMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD).lightLevel(state -> color.getLightValue())), true));
    }

    public static void buildTabContents(BuildCreativeModeTabContentsEvent event) {
        var key = ResourceKey.create(Registries.CREATIVE_MODE_TAB, new ResourceLocation("regions_unexplored:ru_main"));
        if (event.getTabKey().equals(key)) {
            PLANKS.forEach((dyenamicDyeColor, registryObject) -> {event.accept(registryObject);});
            STAIRS.forEach((dyenamicDyeColor, registryObject) -> {event.accept(registryObject);});
            SLABS.forEach((dyenamicDyeColor, registryObject) -> {event.accept(registryObject);});
        }
    }
}
