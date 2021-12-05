package com.b1n_ry.short_grass.mixin;

import com.b1n_ry.short_grass.block.ShortFernBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.FernBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(Blocks.class)
public class BlocksMixin {
    @Redirect(
        method = "<clinit>", at = @At(value = "NEW", target = "net/minecraft/block/FernBlock", ordinal = 0),
        slice = @Slice(from = @At(value = "CONSTANT", args="stringValue=grass"))
    )
    private static FernBlock setGrass(AbstractBlock.Settings settings) {
        return new ShortFernBlock(settings);
    }
}
