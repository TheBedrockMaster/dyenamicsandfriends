//package cy.jdkdigital.dyenamicsandfriends.compat;
//
//import cy.jdkdigital.dyenamics.core.util.DyenamicDyeColor;
//import cy.jdkdigital.dyenamicsandfriends.common.block.entity.supplementaries.DyenamicsPresentBlockEntity;
//import cy.jdkdigital.dyenamicsandfriends.common.block.entity.supplementaries.DyenamicsTrappedPresentBlockEntity;
//import cy.jdkdigital.dyenamicsandfriends.common.block.supplementaries.DyenamicsPresentBlock;
//import cy.jdkdigital.dyenamicsandfriends.common.block.supplementaries.DyenamicsTrappedPresentBlock;
//import cy.jdkdigital.dyenamicsandfriends.registry.DyenamicRegistry;
//import net.mehvahdjukaar.supplementaries.common.block.blocks.CandleHolderBlock;
//import net.mehvahdjukaar.supplementaries.reg.ModSounds;
//import net.minecraft.client.renderer.ItemBlockRenderTypes;
//import net.minecraft.client.renderer.RenderType;
//import net.minecraft.world.item.BlockItem;
//import net.minecraft.world.item.Item;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.SoundType;
//import net.minecraft.world.level.block.state.BlockBehaviour;
//import net.minecraftforge.client.event.EntityRenderersEvent;
//import net.minecraftforge.registries.RegistryObject;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class SupplementariesCompat
//{
//    private static final Map<DyenamicDyeColor, RegistryObject<? extends Block>> PRESENTS = new HashMap<>();
//    private static final Map<DyenamicDyeColor, RegistryObject<? extends Block>> TRAPPED_PRESENTS = new HashMap<>();
//    public static final Map<DyenamicDyeColor, RegistryObject<? extends Block>> FLAGS = new HashMap<>();
//    private static final Map<DyenamicDyeColor, RegistryObject<? extends Block>> CANDLE_HOLDERS = new HashMap<>();
//
//    public static void registerBlocks(DyenamicDyeColor color) {
//        String prefix = "supplementaries_" + color.getSerializedName();
//
//        PRESENTS.put(color, DyenamicRegistry.registerBlock(prefix + "_present", () -> new DyenamicsPresentBlock(color, BlockBehaviour.Properties.of().mapColor(color.getMapColor()).strength(1.0F).sound(ModSounds.PRESENT).lightLevel(state -> color.getLightValue()), DyenamicRegistry.registerBlockEntity(prefix + "_present", () -> DyenamicRegistry.createBlockEntityType((pos, state) -> new DyenamicsPresentBlockEntity((DyenamicsPresentBlock) PRESENTS.get(color).get(), pos, state), PRESENTS.get(color).get()))), () -> new BlockItem(PRESENTS.get(color).get(), new Item.Properties())));
//        TRAPPED_PRESENTS.put(color, DyenamicRegistry.registerBlock(prefix + "_trapped_present", () -> new DyenamicsTrappedPresentBlock(color, BlockBehaviour.Properties.of().mapColor(color.getMapColor()).strength(1.0F).sound(ModSounds.PRESENT).lightLevel(state -> color.getLightValue()), DyenamicRegistry.registerBlockEntity(prefix + "_trapped_present", () -> DyenamicRegistry.createBlockEntityType((pos, state) -> new DyenamicsTrappedPresentBlockEntity((DyenamicsTrappedPresentBlock) TRAPPED_PRESENTS.get(color).get(), pos, state), TRAPPED_PRESENTS.get(color).get()))), () -> new BlockItem(TRAPPED_PRESENTS.get(color).get(), new Item.Properties())));
//        CANDLE_HOLDERS.put(color, DyenamicRegistry.registerBlock(prefix + "_candle_holder", () -> new CandleHolderBlock(color.getAnalogue(), BlockBehaviour.Properties.of().mapColor(color.getMapColor()).noCollission().instabreak().sound(SoundType.LANTERN).lightLevel(state -> color.getLightValue())), () -> new BlockItem(CANDLE_HOLDERS.get(color).get(), new Item.Properties())));
//    }
//
//    public static class Client
//    {
//        public static void registerBlockRendering() {
//            CANDLE_HOLDERS.values().forEach(registryObject -> {
//                if (registryObject.get() instanceof CandleHolderBlock candleHolder) {
//                    ItemBlockRenderTypes.setRenderLayer(candleHolder, RenderType.cutout());
//                }
//            });
//        }
//
//        public static void registerBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
//        }
//    }
//}
