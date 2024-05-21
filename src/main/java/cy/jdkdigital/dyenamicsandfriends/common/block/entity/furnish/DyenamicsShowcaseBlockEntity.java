package cy.jdkdigital.dyenamicsandfriends.common.block.entity.furnish;

import cy.jdkdigital.dyenamicsandfriends.common.block.furnish.DyenamicsShowcase;
import io.github.wouink.furnish.block.blockentity.ShowcaseBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class DyenamicsShowcaseBlockEntity extends ShowcaseBlockEntity
{
    private final DyenamicsShowcase showcase;

    public DyenamicsShowcaseBlockEntity(BlockPos pos, BlockState state, DyenamicsShowcase showcase) {
        super(pos, state);
        this.showcase = showcase;
    }

    @Override
    public BlockEntityType<?> getType() {
        return showcase != null ? showcase.getBlockEntitySupplier().get() : null;
    }
}