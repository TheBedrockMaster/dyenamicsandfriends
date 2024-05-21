package cy.jdkdigital.dyenamicsandfriends.compat;

import com.illusivesoulworks.comforts.common.item.HammockItem;
import com.illusivesoulworks.comforts.common.item.SleepingBagItem;
import cy.jdkdigital.dyenamics.core.util.DyenamicDyeColor;
import cy.jdkdigital.dyenamicsandfriends.client.render.comforts.DyenamicsHammockBlockRenderer;
import cy.jdkdigital.dyenamicsandfriends.client.render.comforts.DyenamicsSleepingBagBlockRenderer;
import cy.jdkdigital.dyenamicsandfriends.common.block.comforts.DyenamicsHammockBlock;
import cy.jdkdigital.dyenamicsandfriends.common.block.comforts.DyenamicsSleepingBagBlock;
import cy.jdkdigital.dyenamicsandfriends.common.block.entity.comforts.DyenamicsHammockBlockEntity;
import cy.jdkdigital.dyenamicsandfriends.common.block.entity.comforts.DyenamicsSleepingBagBlockEntity;
import cy.jdkdigital.dyenamicsandfriends.registry.DyenamicRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

public class ComfortsCompat
{
    private static final Map<DyenamicDyeColor, RegistryObject<? extends Block>> HAMMOCKS = new HashMap<>();
    private static final Map<DyenamicDyeColor, RegistryObject<? extends Block>> SLEEPING_BAGS = new HashMap<>();

    public static void registerBlocks(DyenamicDyeColor color) {
        String prefix = "comforts_" + color.getSerializedName();
        HAMMOCKS.put(color, DyenamicRegistry.registerBlock(prefix + "_hammock", () -> new DyenamicsHammockBlock(color, BlockBehaviour.Properties.of().sound(SoundType.WOOL).strength(0.1F).lightLevel(state -> color.getLightValue()), DyenamicRegistry.registerBlockEntity(prefix + "_hammock", () -> DyenamicRegistry.createBlockEntityType((pos, state) -> new DyenamicsHammockBlockEntity((DyenamicsHammockBlock) HAMMOCKS.get(color).get(), pos, state), HAMMOCKS.get(color).get()))), () -> new HammockItem(HAMMOCKS.get(color).get())));
        SLEEPING_BAGS.put(color, DyenamicRegistry.registerBlock(prefix + "_sleeping_bag", () -> new DyenamicsSleepingBagBlock(color, BlockBehaviour.Properties.of().sound(SoundType.WOOL).strength(0.1F).lightLevel(state -> color.getLightValue()), DyenamicRegistry.registerBlockEntity(prefix + "_sleeping_bag", () -> DyenamicRegistry.createBlockEntityType((pos, state) -> new DyenamicsSleepingBagBlockEntity((DyenamicsSleepingBagBlock) SLEEPING_BAGS.get(color).get(), pos, state), SLEEPING_BAGS.get(color).get()))), () -> new SleepingBagItem(SLEEPING_BAGS.get(color).get())));
    }

    public static void buildTabContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(CreativeModeTabs.COLORED_BLOCKS) || event.getTabKey().equals(CreativeModeTabs.FUNCTIONAL_BLOCKS)) {
            HAMMOCKS.forEach((dyenamicDyeColor, registryObject) -> event.accept(registryObject));
            SLEEPING_BAGS.forEach((dyenamicDyeColor, registryObject) -> event.accept(registryObject));
        }
    }

    public static class Client
    {
        public static void registerBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
            HAMMOCKS.values().forEach(registryObject -> {
                if (registryObject.get() instanceof DyenamicsHammockBlock hammock) {
                    event.registerBlockEntityRenderer(hammock.getBlockEntitySupplier().get(), DyenamicsHammockBlockRenderer::new);
                }
            });
            SLEEPING_BAGS.values().forEach(registryObject -> {
                if (registryObject.get() instanceof DyenamicsSleepingBagBlock hammock) {
                    event.registerBlockEntityRenderer(hammock.getBlockEntitySupplier().get(), DyenamicsSleepingBagBlockRenderer::new);
                }
            });
        }
    }
}
