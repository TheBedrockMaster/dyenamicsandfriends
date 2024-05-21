package cy.jdkdigital.dyenamicsandfriends.datagen;

import com.supermartijn642.connectedglass.CGGlassType;
import cy.jdkdigital.dyenamics.core.util.DyenamicDyeColor;
import cy.jdkdigital.dyenamicsandfriends.DyenamicsAndFriends;
import cy.jdkdigital.dyenamicsandfriends.compat.AnotherFurnitureCompat;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;

import java.util.Locale;

public class LanguageProvider extends net.minecraftforge.common.data.LanguageProvider
{
    public LanguageProvider(PackOutput output) {
        super(output, DyenamicsAndFriends.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("item.dyenamicsandfriends.chalk_box", "Dyenamics Chalk Box");
        add("item.dyenamicsandfriends.chalk_box.tooltip.dyenamics", "This one works with Dyenamics dyes");

        for (DyenamicDyeColor color: DyenamicDyeColor.dyenamicValues()) {
            String colorName = capName(Component.translatable(color.getTranslationKey()).getString());

            add("block.dyenamicsandfriends.another_furniture_" + color.getSerializedName() + "_lamp", colorName + " Lamp");
            add("block.dyenamicsandfriends.another_furniture_" + color.getSerializedName() + "_curtain", colorName + " Curtain");
            add("block.dyenamicsandfriends.another_furniture_" + color.getSerializedName() + "_sofa", colorName + " Sofa");
            add("block.dyenamicsandfriends.another_furniture_" + color.getSerializedName() + "_stool", colorName + " Stool");
            add("block.dyenamicsandfriends.another_furniture_" + color.getSerializedName() + "_tall_stool", colorName + " Tall Stool");
            add("block.dyenamicsandfriends.create_" + color.getSerializedName() + "_valve_handle", colorName + " Valve Handle");
            add("block.dyenamicsandfriends.create_" + color.getSerializedName() + "_seat", colorName + " Seat");
            add("block.dyenamicsandfriends.create_" + color.getSerializedName() + "_sail", colorName + " Sail");
            add("block.dyenamicsandfriends.comforts_" + color.getSerializedName() + "_hammock", colorName + " Hammock");
            add("block.dyenamicsandfriends.comforts_" + color.getSerializedName() + "_sleeping_bag", colorName + " Sleeping Bag");
            add("block.dyenamicsandfriends.elevatorid_" + color.getSerializedName() + "_elevator", colorName + " Elevator");
            add("block.dyenamicsandfriends.quark_" + color.getSerializedName() + "_framed_glass", colorName + " Framed Glass");
            add("block.dyenamicsandfriends.quark_" + color.getSerializedName() + "_framed_glass_pane", colorName + " Framed Glass Pane");
            add("block.dyenamicsandfriends.quark_" + color.getSerializedName() + "_shingles", colorName + " Terracotta Shingles");
            add("block.dyenamicsandfriends.quark_" + color.getSerializedName() + "_shingles_slab", colorName + " Terracotta Shingles Slab");
            add("block.dyenamicsandfriends.quark_" + color.getSerializedName() + "_shingles_stairs", colorName + " Terracotta Shingles Stairs");
            add("item.dyenamicsandfriends.quark_" + color.getSerializedName() + "_shard", colorName + " Glass Shard");
            add("item.dyenamicsandfriends.quark_" + color.getSerializedName() + "_rune", colorName + " Rune");
            add("item.dyenamicsandfriends.handcrafted_" + color.getSerializedName() + "_sheet", colorName + " Sheet");
            add("item.dyenamicsandfriends.handcrafted_" + color.getSerializedName() + "_cushion", colorName + " Cushion");
            add("block.dyenamicsandfriends.botanypots_" + color.getSerializedName() + "_terracotta_botany_pot", colorName + " Botany Pot");
            add("block.dyenamicsandfriends.botanypots_" + color.getSerializedName() + "_terracotta_hopper_botany_pot", colorName + " Hopper Botany Pot");
            add("block.dyenamicsandfriends.botanypots_" + color.getSerializedName() + "_concrete_botany_pot", colorName + " Botany Pot");
            add("block.dyenamicsandfriends.botanypots_" + color.getSerializedName() + "_concrete_hopper_botany_pot", colorName + " Hopper Botany Pot");
            add("block.dyenamicsandfriends.botanypots_" + color.getSerializedName() + "_glazed_terracotta_botany_pot", colorName + " Glazed Botany Pot");
            add("block.dyenamicsandfriends.botanypots_" + color.getSerializedName() + "_glazed_terracotta_hopper_botany_pot", colorName + " Glazed Hopper Botany Pot");
            add("block.dyenamicsandfriends.furnish_" + color.getSerializedName() + "_amphora", colorName + " Amphora");
            add("block.dyenamicsandfriends.furnish_" + color.getSerializedName() + "_awning", colorName + " Awning");
            add("block.dyenamicsandfriends.furnish_" + color.getSerializedName() + "_curtain", colorName + " Curtain");
            add("block.dyenamicsandfriends.furnish_" + color.getSerializedName() + "_paper_lamp", colorName + " Paper Lamp");
            add("block.dyenamicsandfriends.furnish_" + color.getSerializedName() + "_plate", colorName + " Plate");
            add("block.dyenamicsandfriends.furnish_" + color.getSerializedName() + "_showcase", colorName + " Showcase");
            add("block.dyenamicsandfriends.furnish_" + color.getSerializedName() + "_sofa", colorName + " Sofa");
            add("block.dyenamicsandfriends.oreganized_" + color.getSerializedName() + "_crystal_glass", colorName + " Crystal Glass");
            add("block.dyenamicsandfriends.oreganized_" + color.getSerializedName() + "_crystal_glass_pane", colorName + " Crystal Glass Pane");
            add("block.dyenamicsandfriends.oreganized_" + color.getSerializedName() + "_waxed_concrete_powder", "Waxed " + colorName + " Concrete Powder");
            add("block.dyenamicsandfriends.ceramics_" + color.getSerializedName() + "_porcelain", colorName + " Porcelain");
            add("block.dyenamicsandfriends.ceramics_" + color.getSerializedName() + "_terracotta_cistern", colorName + " Terracotta Cistern");
            add("block.dyenamicsandfriends.ceramics_" + color.getSerializedName() + "_porcelain_cistern", colorName + " Porcelain Cistern");
            add("block.dyenamicsandfriends.clayworks_" + color.getSerializedName() + "_terracotta_wall", colorName + " Terracotta Wall");
            add("block.dyenamicsandfriends.clayworks_" + color.getSerializedName() + "_terracotta_slab", colorName + " Terracotta Slab");
            add("block.dyenamicsandfriends.clayworks_" + color.getSerializedName() + "_terracotta_vertical_slab", colorName + " Terracotta Vertical Slab");
            add("block.dyenamicsandfriends.clayworks_" + color.getSerializedName() + "_terracotta_stairs", colorName + " Terracotta Stairs");
            add("block.dyenamicsandfriends.clayworks_" + color.getSerializedName() + "_terracotta_bricks", colorName + " Terracotta Bricks");
            add("block.dyenamicsandfriends.clayworks_" + color.getSerializedName() + "_terracotta_brick_wall", colorName + " Terracotta Brick Wall");
            add("block.dyenamicsandfriends.clayworks_" + color.getSerializedName() + "_terracotta_brick_stairs", colorName + " Terracotta Brick Stairs");
            add("block.dyenamicsandfriends.clayworks_" + color.getSerializedName() + "_terracotta_brick_slab", colorName + " Terracotta Brick Slab");
            add("block.dyenamicsandfriends.clayworks_" + color.getSerializedName() + "_terracotta_brick_vertical_slab", colorName + " Terracotta Brick Vertical Slab");
            add("block.dyenamicsandfriends.clayworks_" + color.getSerializedName() + "_terracotta_chiseled_bricks", "Chiseled " + colorName + " Terracotta Brick");
            add("block.dyenamicsandfriends.glazedresymmetry_" + color.getSerializedName() + "_centered_glazed_terracotta", colorName + " Centered Glazed Terracotta");
            add("block.dyenamicsandfriends.glazedresymmetry_" + color.getSerializedName() + "_glazed_terracotta_pillar", colorName + " Glazed Terracotta Pillar");
            add("block.dyenamicsandfriends.glazedresymmetry_" + color.getSerializedName() + "_glazed_terracotta_slab", colorName + " Glazed Terracotta Slab");
            add("block.dyenamicsandfriends.glazedresymmetry_" + color.getSerializedName() + "_glazed_terracotta_pillar_slab", colorName + " Glazed Terracotta Pillared Slab");
            add("block.dyenamicsandfriends.glazedresymmetry_" + color.getSerializedName() + "_centered_glazed_terracotta_slab", colorName + " Centered Glazed Terracotta Slab");
            add("block.dyenamicsandfriends.glazedresymmetry_" + color.getSerializedName() + "_glazed_terracotta_pillar_side_slab", colorName + " Glazed Terracotta Side Pillared Slab");
            add("block.dyenamicsandfriends.bumblezone_" + color.getSerializedName() + "_super_candle_base", colorName + " Super Candle Base");
            add("block.dyenamicsandfriends.bumblezone_" + color.getSerializedName() + "_string_curtain", colorName + " String Curtain");
            add("block.dyenamicsandfriends.sleep_tight_" + color.getSerializedName() + "_hammock", colorName + " Hammock");
            add("block.dyenamicsandfriends.supplementaries_" + color.getSerializedName() + "_present", colorName + " Present");
            add("block.dyenamicsandfriends.supplementaries_" + color.getSerializedName() + "_trapped_present", colorName + " Trapped Present");
            add("block.dyenamicsandfriends.supplementaries_" + color.getSerializedName() + "_candle_holder", colorName + " Candle Holder");
            add("block.dyenamicsandfriends.supplementaries_" + color.getSerializedName() + "_flag", colorName + " Flag");
            add("block.dyenamicsandfriends.regions_unexplored_" + color.getSerializedName() + "_painted_planks", colorName + " Painted Planks");
            add("block.dyenamicsandfriends.regions_unexplored_" + color.getSerializedName() + "_painted_slab", colorName + " Painted Slab");
            add("block.dyenamicsandfriends.regions_unexplored_" + color.getSerializedName() + "_painted_stairs", colorName + " Painted Stairs");
            add("block.dyenamicsandfriends.farmersdelight_" + color.getSerializedName() + "_canvas_sign", colorName + " Canvas Sign");
            add("block.dyenamicsandfriends.farmersdelight_" + color.getSerializedName() + "_canvas_wall_sign", colorName + " Canvas Wall Sign");
            add("item.dyenamicsandfriends.chalk_" + color.getSerializedName() + "_chalk", colorName + " Chalk");
            add("block.dyenamicsandfriends.suppsquared_" + color.getSerializedName() + "_gold_candle_holder", "Gold " + colorName + " Candle Holder");
            add("block.dyenamicsandfriends.suppsquared_" + color.getSerializedName() + "_sack", colorName + " Sack");

            for (CGGlassType glassType : CGGlassType.values()) {
                var typeName = glassType.name().toLowerCase(Locale.ROOT);
                add("block.dyenamicsandfriends.connectedglass_" + typeName + "_" + color.getSerializedName(), glassType.translation + " " + colorName + " Stained Glass");
                if (glassType.hasPanes) {
                    add("block.dyenamicsandfriends.connectedglass_" + typeName + "_" + color.getSerializedName() + "_pane", glassType.translation + " " + colorName + " Stained Glass Pane");
                }
            }
        }
    }

    @Override
    public String getName() {
        return "dyenamicsandfriends translation provider";
    }

    public static String capName(String name) {
        String[] nameParts = name.split("_");

        for(int i = 0; i < nameParts.length; ++i) {
            String var10002 = nameParts[i].substring(0, 1).toUpperCase();
            nameParts[i] = var10002 + nameParts[i].substring(1);
        }

        return String.join(" ", nameParts);
    }
}
