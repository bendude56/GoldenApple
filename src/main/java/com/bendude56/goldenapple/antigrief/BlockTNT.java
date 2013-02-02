package com.bendude56.goldenapple.antigrief;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.bukkit.Material;

import com.bendude56.goldenapple.GoldenApple;

import net.minecraft.server.v1_4_R1.Block;
import net.minecraft.server.v1_4_R1.Entity;
import net.minecraft.server.v1_4_R1.EntityArrow;
import net.minecraft.server.v1_4_R1.EntityTNTPrimed;
import net.minecraft.server.v1_4_R1.StepSound;
import net.minecraft.server.v1_4_R1.World;

public class BlockTNT extends net.minecraft.server.v1_4_R1.BlockTNT {
	
	public static void registerBlock() throws Exception {
		Block.byId[Material.TNT.getId()] = null;
		Block tnt = prepClass((Block)BlockTNT.class.getConstructors()[0].newInstance(46, 8));
		
		Field f = Block.class.getField("TNT");
		Field mod = Field.class.getDeclaredField("modifiers");
		mod.setAccessible(true);
		mod.setInt(f, mod.getInt(f)  & ~Modifier.FINAL);
		
		f.set(null, tnt);
	}
	
	public static void unregisterBlock() throws Exception {
		Block.byId[Material.TNT.getId()] = null;
		Block tnt = prepClass((Block)net.minecraft.server.v1_4_R1.BlockTNT.class.getConstructors()[0].newInstance(46, 8));
		
		Field f = Block.class.getField("TNT");
		Field mod = Field.class.getDeclaredField("modifiers");
		mod.setAccessible(true);
		mod.setInt(f, mod.getInt(f)  & ~Modifier.FINAL);
		
		f.set(null, tnt);
	}
	
	private static Block prepClass(Block b) throws Exception {
		Method m = Block.class.getDeclaredMethod("c", new Class<?>[] { float.class });
		m.setAccessible(true);
		m.invoke(b, 0.0F);
		
		m = Block.class.getDeclaredMethod("a", new Class<?>[] { StepSound.class });
		m.setAccessible(true);
		m.invoke(b, Block.g);
		
		b.b("tnt");
		
		return b;
	}

	public BlockTNT(int i, int j) {
		super(i, j);
	}
	
	@Override
	public void onPlace(World world, int i, int j, int k) {
        if (!world.suppressPhysics && world.isBlockIndirectlyPowered(i, j, k) && !GoldenApple.getInstance().mainConfig.getBoolean("modules.antigrief.noRedstoneTnt", true)) {
            this.postBreak(world, i, j, k, 1);
            world.setTypeId(i, j, k, 0);
        }
    }
	
	@Override
	public void doPhysics(World world, int i, int j, int k, int l) {
        if (l > 0 && Block.byId[l].isPowerSource() && world.isBlockIndirectlyPowered(i, j, k) && !GoldenApple.getInstance().mainConfig.getBoolean("modules.antigrief.noRedstoneTnt", true)) {
            this.postBreak(world, i, j, k, 1);
            world.setTypeId(i, j, k, 0);
        }
    }
	
	@Override
	public void wasExploded(World world, int i, int j, int k) {
        if (!world.isStatic  && !GoldenApple.getInstance().mainConfig.getBoolean("modules.antigrief.noExplosionTnt", true)) {
            EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(world, (double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F));

            entitytntprimed.fuseTicks = world.random.nextInt(entitytntprimed.fuseTicks / 4) + entitytntprimed.fuseTicks / 8;
            world.addEntity(entitytntprimed);
        }
    }
	
	@Override
	public void a(World world, int i, int j, int k, Entity entity) {
        if (entity instanceof EntityArrow && !world.isStatic) {
            EntityArrow entityarrow = (EntityArrow) entity;

            if (entityarrow.isBurning()  && !GoldenApple.getInstance().mainConfig.getBoolean("modules.antigrief.noFireArrowTnt", true)) {
                this.postBreak(world, i, j, k, 1);
                world.setTypeId(i, j, k, 0);
            }
        }
    }

}
