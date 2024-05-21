package cy.jdkdigital.dyenamicsandfriends.common.block.entity.furnish;

import cy.jdkdigital.dyenamicsandfriends.common.block.furnish.DyenamicsPlate;
import io.github.wouink.furnish.block.blockentity.PlateBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class DyenamicsPlateBlockEntity extends PlateBlockEntity
{
    private final DyenamicsPlate plate;

    public DyenamicsPlateBlockEntity(BlockPos pos, BlockState state, DyenamicsPlate plate) {
        super(pos, state);
        this.plate = plate;
    }

    @Override
    public BlockEntityType<?> getType() {
        return plate != null ? plate.getBlockEntitySupplier().get() : null;
    }
}