package com.bendude56.goldenapple.antigrief;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.bukkit.Material;

import com.bendude56.goldenapple.GoldenApple;

import net.minecraft.server.v1_5_R3.DispenseBehaviorItem;
import net.minecraft.server.v1_5_R3.IDispenseBehavior;
import net.minecraft.server.v1_5_R3.ItemStack;
import net.minecraft.server.v1_5_R3.MinecraftServer;
import net.minecraft.server.v1_5_R3.SourceBlock;
import net.minecraft.server.v1_5_R3.StepSound;
import net.minecraft.server.v1_5_R3.TileEntityDispenser;
import net.minecraft.server.v1_5_R3.World;
import net.minecraft.server.v1_5_R3.Block;

public class BlockDispenser extends net.minecraft.server.v1_5_R3.BlockDispenser {

	public static void registerBlock() throws Exception {
		Block.byId[Material.DISPENSER.getId()] = null;
		Block disp = prepClass((Block)BlockDispenser.class.getConstructors()[0].newInstance(23));
		
		Field f = Block.class.getField("DISPENSER");
		Field mod = Field.class.getDeclaredField("modifiers");
		mod.setAccessible(true);
		mod.setInt(f, mod.getInt(f)  & ~Modifier.FINAL);
		
		f.set(null, disp);
	}
	
	public static void unregisterBlock() throws Exception {
		Block.byId[Material.DISPENSER.getId()] = null;
		Block disp = prepClass((Block)net.minecraft.server.v1_5_R3.BlockDispenser.class.getConstructors()[0].newInstance(23));
		
		Field f = Block.class.getField("DISPENSER");
		Field mod = Field.class.getDeclaredField("modifiers");
		mod.setAccessible(true);
		mod.setInt(f, mod.getInt(f)  & ~Modifier.FINAL);
		
		f.set(null, disp);
	}
	
	private static Block prepClass(Block b) throws Exception {
		Method m = Block.class.getDeclaredMethod("c", new Class<?>[] { float.class });
		m.setAccessible(true);
		m.invoke(b, 3.5F);
		
		m = Block.class.getDeclaredMethod("a", new Class<?>[] { StepSound.class });
		m.setAccessible(true);
		m.invoke(b, Block.j);
		
		b.c("dispenser");
		
		return b;
	}
	
	public BlockDispenser(int i) {
		super(i);
	}
	
	public void dispense(World world, int i, int j, int k) {
        SourceBlock sourceblock = new SourceBlock(world, i, j, k);
        TileEntityDispenser tileentitydispenser = (TileEntityDispenser) sourceblock.getTileEntity();

        if (tileentitydispenser != null) {
            int l = tileentitydispenser.j();

            if (l < 0) {
                world.triggerEffect(1001, i, j, k, 0);
            } else {
                ItemStack itemstack = tileentitydispenser.getItem(l);
                IDispenseBehavior idispensebehavior = (IDispenseBehavior) net.minecraft.server.v1_5_R3.BlockDispenser.a.a(itemstack.getItem());

                if (idispensebehavior != IDispenseBehavior.a) {
                	if (itemstack.id == Material.POTION.getId())
                		idispensebehavior = new DispenseBehaviorPotion(MinecraftServer.getServer());
                	else if (itemstack.id == Material.TNT.getId() && GoldenApple.getInstanceMainConfig().getBoolean("modules.antigrief.noDispenserTnt", true))
                		idispensebehavior = new DispenseBehaviorItem();
                	
                    ItemStack itemstack1 = idispensebehavior.a(sourceblock, itemstack);

                    tileentitydispenser.setItem(l, itemstack1.count == 0 ? null : itemstack1);
                }
            }
        }
    }

}
