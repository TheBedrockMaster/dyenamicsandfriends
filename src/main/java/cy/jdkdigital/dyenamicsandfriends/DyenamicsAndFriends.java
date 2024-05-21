package cy.jdkdigital.dyenamicsandfriends;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import cy.jdkdigital.dyenamicsandfriends.loot.OptionalLootItem;
import cy.jdkdigital.dyenamicsandfriends.loot.condition.ModLoadedCondition;
import cy.jdkdigital.dyenamicsandfriends.loot.condition.OptionalLootItemBlockStatePropertyCondition;
import cy.jdkdigital.dyenamicsandfriends.registry.DyenamicRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.repository.RepositorySource;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryType;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forgespi.language.IModFileInfo;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.resource.PathPackResources;
import org.slf4j.Logger;

import java.util.function.Consumer;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(DyenamicsAndFriends.MODID)
public class DyenamicsAndFriends
{
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MODID = "dyenamicsandfriends";

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<MenuType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MODID);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, MODID);
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MODID);
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, MODID);
    public static final DeferredRegister<LootPoolEntryType> LOOT_POOL_ENTRIES = DeferredRegister.create(Registries.LOOT_POOL_ENTRY_TYPE, MODID);
    public static final DeferredRegister<LootItemConditionType> LOOT_POOL_CONDITIONS = DeferredRegister.create(Registries.LOOT_CONDITION_TYPE, MODID);

    public static final RegistryObject<LootPoolEntryType> OPTIONAL_LOOT_ITEM = LOOT_POOL_ENTRIES.register("optional_loot_item", () -> new LootPoolEntryType(new OptionalLootItem.Serializer()));
    public static final RegistryObject<LootItemConditionType> OPTIONAL_BLOCK_STATE_PROPERTY = LOOT_POOL_CONDITIONS.register("optional_block_state_property", () -> new LootItemConditionType(new OptionalLootItemBlockStatePropertyCondition.Serializer()));
    public static final RegistryObject<LootItemConditionType> MOD_LOADED_CONDITION = LOOT_POOL_CONDITIONS.register("mod_loaded", () -> new LootItemConditionType(new ModLoadedCondition.Serializer()));

    public DyenamicsAndFriends()
    {
        var modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::onPackEvent);

        DyenamicRegistry.setup();
        DyenamicRegistry.registerCompatBlocks();

        BLOCKS.register(modEventBus);
        BLOCK_ENTITIES.register(modEventBus);
        CONTAINER_TYPES.register(modEventBus);
        ITEMS.register(modEventBus);
        RECIPE_SERIALIZERS.register(modEventBus);
        RECIPE_TYPES.register(modEventBus);
        PARTICLE_TYPES.register(modEventBus);
        LOOT_SERIALIZERS.register(modEventBus);
        LOOT_POOL_ENTRIES.register(modEventBus);
        LOOT_POOL_CONDITIONS.register(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        if (ModList.get().isLoaded("create")) {
//            CreateCompat.setup(event);
        }
    }

    private void clientSetup(final FMLClientSetupEvent event)
    {
        DyenamicRegistry.clientRegister();
    }

    private void onPackEvent(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.SERVER_DATA) {
            event.addRepositorySource(new ModLoadedPackFinder(event.getPackType()));
        }
        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
            event.addRepositorySource(new ModLoadedPackFinder(event.getPackType()));
        }
    }

    private static class ModLoadedPackFinder implements RepositorySource
    {
        private final PackType packType;

        public ModLoadedPackFinder(PackType packType) {
            this.packType = packType;
        }

        @Override
        public void loadPacks(Consumer<Pack> packLoader) {
            IModFileInfo modFile = ModList.get().getModContainerById(DyenamicsAndFriends.MODID).get().getModInfo().getOwningFile();

            for (String modId : DyenamicRegistry.MODS) {
                try {
                    if (ModList.get().isLoaded(modId) && DyenamicRegistry.MODS.contains(modId)) {
                        if (packType.equals(PackType.SERVER_DATA)) {
                            var pack = Pack.readMetaAndCreate(
                                    DyenamicsAndFriends.MODID + ":" + modId,
                                    Component.translatable("dataPack." + MODID + "." + modId),
                                    true,
                                    (name) -> new PathPackResources(DyenamicsAndFriends.MODID + ":" + modId, true, modFile.getFile().findResource("compat_packs/" + modId + "/")),
                                    PackType.SERVER_DATA,
                                    Pack.Position.TOP,
                                    PackSource.BUILT_IN
                            );
                            packLoader.accept(pack);
                        }
                        if (packType.equals(PackType.CLIENT_RESOURCES) && modId.equals("connectedglass")) {
                            DyenamicsAndFriends.LOGGER.info("adding connected glass resourcepack");
                            var clientPack = Pack.readMetaAndCreate(
                                    DyenamicsAndFriends.MODID + ":" + modId + "_resources",
                                    Component.translatable("resourcePack." + MODID + "." + modId),
                                    true,
                                    (name) -> new PathPackResources(DyenamicsAndFriends.MODID + ":" + modId + "_resources", true, modFile.getFile().findResource("compat_packs/" + modId + "/")),
                                    PackType.CLIENT_RESOURCES,
                                    Pack.Position.TOP,
                                    PackSource.BUILT_IN
                            );
                            packLoader.accept(clientPack);
                        }
                    }
                } catch (Exception e) {
                    DyenamicsAndFriends.LOGGER.debug("Failed to load compat pack: " + modId);
                }
            }
        }
    }
}
