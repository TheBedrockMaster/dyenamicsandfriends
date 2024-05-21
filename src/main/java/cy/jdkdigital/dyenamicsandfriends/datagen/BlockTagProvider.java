package cy.jdkdigital.dyenamicsandfriends.datagen;

import cy.jdkdigital.dyenamicsandfriends.DyenamicsAndFriends;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class BlockTagProvider extends BlockTagsProvider
{
    public BlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper helper) {
        super(output, provider, DyenamicsAndFriends.MODID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        var axeMineable = tag(BlockTags.MINEABLE_WITH_AXE);
        var storageBlocks = tag(Tags.Blocks.STORAGE_BLOCKS);
        var flowers = tag(BlockTags.FLOWERS);
        var planks = tag(BlockTags.PLANKS);
        var logs = tag(BlockTags.LOGS);
        var logsThatBurn = tag(BlockTags.LOGS_THAT_BURN);
        var sapling = tag(BlockTags.SAPLINGS);
        var leaves = tag(BlockTags.LEAVES);
        var slabs = tag(BlockTags.WOODEN_SLABS);
        var pressurePlates = tag(BlockTags.WOODEN_PRESSURE_PLATES);
        var stairs = tag(BlockTags.WOODEN_STAIRS);
        var fences = tag(BlockTags.WOODEN_FENCES);
        var fenceGates = tag(BlockTags.FENCE_GATES);
        var buttons = tag(BlockTags.WOODEN_BUTTONS);
        var doors = tag(BlockTags.WOODEN_DOORS);
        var trapdoors = tag(BlockTags.WOODEN_TRAPDOORS);
        var bookshelves = tag(Tags.Blocks.BOOKSHELVES);
        var enchantment = tag(BlockTags.ENCHANTMENT_POWER_PROVIDER);
        var signs = tag(BlockTags.STANDING_SIGNS);
        var hangingSigns = tag(BlockTags.CEILING_HANGING_SIGNS);
        var wallHangingSigns = tag(BlockTags.WALL_HANGING_SIGNS);
        var wallSigns = tag(BlockTags.WALL_SIGNS);
    }

    @Override
    public String getName() {
        return "Dyenamics and Friends Block Tags Provider";
    }
}
