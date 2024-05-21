package cy.jdkdigital.dyenamicsandfriends.datagen;

import cy.jdkdigital.dyenamicsandfriends.DyenamicsAndFriends;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = DyenamicsAndFriends.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataProvider
{
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        PackOutput output = gen.getPackOutput();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();

        gen.addProvider(event.includeClient(), new LanguageProvider(output));

        gen.addProvider(event.includeClient(), new BlockstateProvider(output));

//        gen.addProvider(event.includeServer(), new LootDataProvider(output, List.of(new LootTableProvider.SubProviderEntry(LootDataProvider.LootProvider::new, LootContextParamSets.BLOCK))));
//        gen.addProvider(event.includeServer(), new LootModifierProvider(output));
//        gen.addProvider(event.includeServer(), new FeatureProvider(output));
//        gen.addProvider(event.includeServer(), new RecipeProvider(output));

//        BlockTagProvider blockTags = new BlockTagProvider(output, provider, helper);
//        gen.addProvider(event.includeServer(), blockTags);
//        gen.addProvider(event.includeServer(), new ItemTagProvider(output, provider, blockTags.contentsGetter(), helper));
    }
}
