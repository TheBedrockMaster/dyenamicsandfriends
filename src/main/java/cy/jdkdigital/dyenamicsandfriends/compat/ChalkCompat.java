//package cy.jdkdigital.dyenamicsandfriends.compat;
//
//import cy.jdkdigital.dyenamics.core.util.DyenamicDyeColor;
//import cy.jdkdigital.dyenamicsandfriends.common.block.chalk.DyenamicsChalkMarkBlock;
//import cy.jdkdigital.dyenamicsandfriends.common.item.chalk.DyenamicsChalkBoxItem;
//import cy.jdkdigital.dyenamicsandfriends.common.item.chalk.DyenamicsChalkItem;
//import cy.jdkdigital.dyenamicsandfriends.registry.DyenamicRegistry;
//import io.github.mortuusars.chalk.Chalk;
//import io.github.mortuusars.chalk.block.ChalkMarkBlock;
//import io.github.mortuusars.chalk.core.ChalkMark;
//import io.github.mortuusars.chalk.core.MarkSymbol;
//import io.github.mortuusars.chalk.core.SymbolOrientation;
//import io.github.mortuusars.chalk.items.ChalkBoxItem;
//import io.github.mortuusars.chalk.render.ChalkMarkBakedModel;
//import io.github.mortuusars.chalk.render.ChalkMarkBlockColor;
//import io.github.mortuusars.chalk.utils.ParticleUtils;
//import io.github.mortuusars.chalk.utils.PositionUtils;
//import net.minecraft.client.color.block.BlockColor;
//import net.minecraft.client.renderer.ItemBlockRenderTypes;
//import net.minecraft.client.renderer.RenderType;
//import net.minecraft.client.renderer.block.BlockModelShaper;
//import net.minecraft.client.renderer.item.ItemProperties;
//import net.minecraft.client.resources.model.BakedModel;
//import net.minecraft.client.resources.model.ModelResourceLocation;
//import net.minecraft.core.BlockPos;
//import net.minecraft.core.Direction;
//import net.minecraft.core.particles.DustParticleOptions;
//import net.minecraft.sounds.SoundEvents;
//import net.minecraft.sounds.SoundSource;
//import net.minecraft.world.InteractionResult;
//import net.minecraft.world.item.CreativeModeTabs;
//import net.minecraft.world.item.Item;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.SoundType;
//import net.minecraft.world.level.block.state.BlockBehaviour;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.phys.Vec3;
//import net.minecraftforge.client.event.ModelEvent;
//import net.minecraftforge.client.event.RegisterColorHandlersEvent;
//import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
//import net.minecraftforge.registries.RegistryObject;
//import org.joml.Vector3f;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Random;
//
//public class ChalkCompat
//{
//    private static final Map<DyenamicDyeColor, RegistryObject<? extends Block>> CHALK_MARK_BLOCKS = new HashMap<>();
//    public static final Map<DyenamicDyeColor, RegistryObject<? extends Item>> CHALKS = new HashMap<>();
//    public static RegistryObject<? extends Item> CHALK_BOX = null;
//
//    public static void registerBlocks(DyenamicDyeColor color) {
//        String prefix = "chalk_" + color.getSerializedName();
//
//        CHALK_MARK_BLOCKS.put(color, DyenamicRegistry.registerBlock(prefix + "_chalk_mark", () -> new DyenamicsChalkMarkBlock(color, BlockBehaviour.Properties.of().mapColor(color.getMapColor()).instabreak().noOcclusion().noCollission().sound(SoundType.GRAVEL).lightLevel(state -> color.getLightValue())), false));
//    }
//
//    public static void registerItems(DyenamicDyeColor color) {
//        String prefix = "chalk_" + color.getSerializedName();
//        CHALKS.put(color, DyenamicRegistry.registerItem(prefix + "_chalk", () -> new DyenamicsChalkItem(color, new Item.Properties())));
//
//        if (color.equals(DyenamicDyeColor.PEACH)) {
//            CHALK_BOX = DyenamicRegistry.registerItem("chalk_box", () -> new DyenamicsChalkBoxItem(new Item.Properties().stacksTo(1)));
//        }
//    }
//
//    public static void buildTabContents(BuildCreativeModeTabContentsEvent event) {
//        if (event.getTabKey().equals(CreativeModeTabs.TOOLS_AND_UTILITIES)) {
//            CHALKS.forEach((dyenamicDyeColor, registryObject) -> {
//                event.accept(registryObject);
//            });
//            event.accept(CHALK_BOX);
//        }
//    }
//
//    public static class Client
//    {
//        public static void registerBlockRendering() {
//            var chalkBox = (DyenamicsChalkBoxItem)CHALK_BOX.get();
//            ItemProperties.register(chalkBox, ChalkBoxItem.SELECTED_PROPERTY, (stack, level, entity, damage) -> {
//                return chalkBox.getSelectedChalkColor(stack);
//            });
//        }
//
//        public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
//            event.register(
//                CHALK_MARK_BLOCK_COLOR,
//                CHALK_MARK_BLOCKS.values().stream().map(RegistryObject::get).toArray(Block[]::new)
//            );
//        }
//
//        private static final BlockColor CHALK_MARK_BLOCK_COLOR = (blockState, blockAndTintGetter, blockPos, index) -> {
//            if (!(blockState.getBlock() instanceof DyenamicsChalkMarkBlock)) {
//                return 16777215;
//            } else {
//                DyenamicDyeColor blockColor = ((DyenamicsChalkMarkBlock)blockState.getBlock()).getDyenamicColor();
//                return blockColor.getColorValue();
//            }
//        };
//
//        public static void bakeModel(ModelEvent.BakingCompleted event) {
//            CHALK_MARK_BLOCKS.forEach((color, block) -> {
//                for (BlockState blockState : block.get().getStateDefinition().getPossibleStates()) {
//                    ModelResourceLocation variantMRL = BlockModelShaper.stateToModelLocation(blockState);
//                    BakedModel existingModel = event.getModels().get(variantMRL);
//
//                    if (existingModel instanceof ChalkMarkBakedModel) {
//                        Chalk.LOGGER.warn("Tried to replace " + block + " model twice");
//                    } else if (existingModel != null) {
//                        ChalkMarkBakedModel customModel = new ChalkMarkBakedModel(existingModel);
//                        event.getModels().put(variantMRL, customModel);
//                    } else {
//                        Chalk.LOGGER.warn(variantMRL + " model not found. ChalkMarkBakedModel would not be added for this blockstate.");
//                    }
//                }
//            });
//        }
//    }
//
//    public static InteractionResult draw(MarkSymbol symbol, DyenamicDyeColor color, boolean isGlowing, BlockPos clickedPos, Direction clickedFace, Vec3 clickLocation, Level level) {
//        if (!ChalkMark.canBeDrawnAt(clickedPos.relative(clickedFace), clickedPos, clickedFace, level)) {
//            Chalk.LOGGER.info("Chalk cannot be drawn at this position. ({}, {}, {})", clickedPos.getX(), clickedPos.getY(), clickedPos.getZ());
//            return InteractionResult.FAIL;
//        } else {
//            boolean isClickedOnAMark = false; // TODO level.getBlockState(clickedPos).is(ModTags.Blocks.CHALK_MARK);
//            BlockPos newMarkPosition = isClickedOnAMark ? clickedPos : clickedPos.relative(clickedFace);
//            Direction newMarkFacing = isClickedOnAMark ? level.getBlockState(newMarkPosition).getValue(ChalkMarkBlock.FACING) : clickedFace;
//            BlockState markBlockState = createMarkBlockState(symbol, color, newMarkFacing, clickLocation, clickedPos, isGlowing);
//            if (isClickedOnAMark) {
//                BlockState oldMarkBlockState = level.getBlockState(newMarkPosition);
//                if (markBlockState.getValue(ChalkMarkBlock.ORIENTATION).equals(oldMarkBlockState.getValue(ChalkMarkBlock.ORIENTATION)) && newMarkFacing == oldMarkBlockState.getValue(ChalkMarkBlock.FACING) && symbol == oldMarkBlockState.getValue(ChalkMarkBlock.SYMBOL) && (!isGlowing || (Boolean)oldMarkBlockState.getValue(ChalkMarkBlock.GLOWING))) {
//                    return InteractionResult.FAIL;
//                }
//
//                level.removeBlock(newMarkPosition, false);
//            }
//
//            drawMark(markBlockState, newMarkPosition, level);
//            return InteractionResult.SUCCESS;
//        }
//    }
//
//    private static BlockState createMarkBlockState(MarkSymbol symbol, DyenamicDyeColor color, Direction clickedFace, Vec3 clickLocation, BlockPos clickedPos, boolean isGlowing) {
//        BlockState newBlockState =
//                CHALK_MARK_BLOCKS.get(color).get().defaultBlockState()
//                        .setValue(ChalkMarkBlock.FACING, clickedFace)
//                        .setValue(ChalkMarkBlock.SYMBOL, symbol)
//                        .setValue(ChalkMarkBlock.GLOWING, isGlowing);
//
//        MarkSymbol.OrientationBehavior rotBehavior = symbol.getOrientationBehavior();
//
//        SymbolOrientation orientation;
//
//        if (rotBehavior == MarkSymbol.OrientationBehavior.FULL) {
//            orientation = initialOrientation;
//        } else if (rotBehavior == MarkSymbol.OrientationBehavior.CARDINAL) {
//            orientation = SymbolOrientation.fromClickLocationCardinal(hitResult.getLocation(), face);
//        } else if (rotBehavior == MarkSymbol.OrientationBehavior.UP_DOWN_CARDINAL && (face == Direction.UP || face == Direction.DOWN)) {
//            orientation = SymbolOrientation.fromRotation(player.getDirection().getOpposite().get2DDataValue() * 90);
//        } else {
//            orientation = symbol.getDefaultOrientation();
//        }
//
//        newBlockState = newBlockState.setValue(ChalkMarkBlock.ORIENTATION, orientation);
//
//        return newBlockState;
//    }
//
//    private static boolean drawMark(BlockState markState, BlockPos markPos, Level level) {
//        boolean isMarkDrawn = level.setBlock(markPos, markState, 11);
//        if (isMarkDrawn) {
//            double pX = (double)markPos.getX() + 0.5D;
//            double pY = (double)markPos.getY() + 0.5D;
//            double pZ = (double)markPos.getZ() + 0.5D;
//            level.playSound(null, pX, pY, pZ, SoundEvents.UI_CARTOGRAPHY_TABLE_TAKE_RESULT, SoundSource.BLOCKS, 0.7F, (new Random()).nextFloat() * 0.2F + 0.8F);
//            if (level.isClientSide && markState.getBlock() instanceof DyenamicsChalkMarkBlock markBlock) {
//                spawnColorDustParticles(markBlock.getDyenamicColor(), level, markPos, markState.getValue(ChalkMarkBlock.FACING));
//            }
//        }
//
//        return isMarkDrawn;
//    }
//
//    public static void spawnColorDustParticles(DyenamicDyeColor color, Level level, BlockPos pos, Direction face) {
//        int colorValue = color.getColorValue();
//        float R = (float)((colorValue & 16711680) >> 16);
//        float G = (float)((colorValue & '\uff00') >> 8);
//        float B = (float)(colorValue & 255);
//        ParticleUtils.spawnParticle(level, new DustParticleOptions(new Vector3f(R / 255.0F, G / 255.0F, B / 255.0F), 2.0F), PositionUtils.blockCenterOffsetToFace(pos, face, 0.25F), 1);
//    }
//}
