package com.b1n_ry.short_grass.mixin;

import com.b1n_ry.short_grass.block.NotAsTallPlantBlock;
import com.b1n_ry.short_grass.block.ShorterPlantBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShortPlantBlock;
import net.minecraft.block.TallPlantBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(Blocks.class)
public class BlocksMixin {
    @Redirect(
        method = "<clinit>", at = @At(value = "NEW", target = "(Lnet/minecraft/block/AbstractBlock$Settings;)Lnet/minecraft/block/ShortPlantBlock;", ordinal = 0),
        slice = @Slice(from = @At(value = "CONSTANT", args="stringValue=short_grass"))
    )
    private static ShortPlantBlock setGrass(AbstractBlock.Settings settings) {
        return new ShorterPlantBlock(settings);
    }

    @Redirect(
        method = "<clinit>", at = @At(value = "NEW", target = "(Lnet/minecraft/block/AbstractBlock$Settings;)Lnet/minecraft/block/TallPlantBlock;", ordinal = 0),
        slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=tall_grass"))
    )
    private static TallPlantBlock setTallGrass(AbstractBlock.Settings settings) {
        return new NotAsTallPlantBlock(settings);
    }
}
