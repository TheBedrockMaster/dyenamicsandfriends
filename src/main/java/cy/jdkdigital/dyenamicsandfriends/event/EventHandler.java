package cy.jdkdigital.dyenamicsandfriends.event;

import cy.jdkdigital.dyenamics.common.items.DyenamicDyeItem;
import cy.jdkdigital.dyenamics.core.util.DyenamicDyeColor;
import cy.jdkdigital.dyenamicsandfriends.DyenamicsAndFriends;
import cy.jdkdigital.dyenamicsandfriends.registry.DyenamicRegistry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = DyenamicsAndFriends.MODID)
public class EventHandler
{
    @SubscribeEvent
    public static void entityRightClicked(PlayerInteractEvent.RightClickBlock event) {
        ItemStack itemStack = event.getItemStack();

        if (event.getLevel() instanceof ServerLevel level) {
            if (!itemStack.isEmpty() && itemStack.getItem() instanceof DyenamicDyeItem) {
                BlockState state = level.getBlockState(event.getPos());

//                var blockKey = ForgeRegistries.BLOCKS.getKey(state.getBlock());
//                if (blockKey != null && blockKey.getNamespace().equals("create") && blockKey.getPath().contains("sail")) {
//                    DyenamicsSailBlock.applyDye(state, level, event.getPos(), event.getHitVec().getLocation(), DyenamicDyeColor.getColor(itemStack));
//                    event.getEntity().swing(event.getHand());
//                }
            }
        }
    }

    @SubscribeEvent
    public static void blockInteract(PlayerInteractEvent event) {
        if (!event.getLevel().isClientSide()) {
            DyenamicRegistry.onBlockInteract(event);
        }
    }

    @SubscribeEvent
    public static void entityPlace(BlockEvent.EntityPlaceEvent event) {
        if (!event.getLevel().isClientSide()) {
            DyenamicRegistry.onEntityPlace(event);
        }
    }

    @SubscribeEvent
    public static void playerRightClick(PlayerInteractEvent.RightClickBlock event) {
        if (!event.getLevel().isClientSide()) {
            DyenamicRegistry.onPlayerRightClick(event);
        }
    }
}
