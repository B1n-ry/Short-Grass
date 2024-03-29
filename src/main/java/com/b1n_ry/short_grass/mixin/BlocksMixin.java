package com.b1n_ry.short_grass.mixin;

import com.b1n_ry.short_grass.block.NotAsTallPlantBlock;
import com.b1n_ry.short_grass.block.ShortFernBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.FernBlock;
import net.minecraft.block.TallPlantBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(Blocks.class)
public class BlocksMixin {
    @Redirect(
        method = "<clinit>", at = @At(value = "NEW", target = "(Lnet/minecraft/block/AbstractBlock$Settings;)Lnet/minecraft/block/FernBlock;", ordinal = 0),
        slice = @Slice(from = @At(value = "CONSTANT", args="stringValue=grass"))
    )
    private static FernBlock setGrass(AbstractBlock.Settings settings) {
        return new ShortFernBlock(settings);
    }

    @Redirect(
        method = "<clinit>", at = @At(value = "NEW", target = "(Lnet/minecraft/block/AbstractBlock$Settings;)Lnet/minecraft/block/TallPlantBlock;", ordinal = 0),
        slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=tall_grass"))
    )
    private static TallPlantBlock setTallGrass(AbstractBlock.Settings settings) {
        return new NotAsTallPlantBlock(settings);
    }
}
