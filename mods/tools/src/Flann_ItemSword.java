// Coded by Flann

package mods.tools.src;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class Flann_ItemSword extends ItemSword{

	public String t, name;
	public ItemStack ccm;
	public boolean hasDam;
	
	public Flann_ItemSword(int id, String displayName, String tex, ItemStack customCraftingMaterial, boolean hasDamage, EnumToolMaterial enumtoolmaterial) {
		super(id, enumtoolmaterial);
		setCreativeTab(CreativeTabs.tabCombat);
		t = tex;
		ccm = customCraftingMaterial;
		hasDam = hasDamage;
		name = displayName;
	}
	
	public Flann_ItemSword(int id, String displayName, String tex, Item ccm, EnumToolMaterial enumtoolmaterial){
		this(id, displayName, tex, new ItemStack(ccm), false, enumtoolmaterial);
	}
	
	public Flann_ItemSword(int id, String displayName, String tex, Block ccm, EnumToolMaterial enumtoolmaterial){
		this(id, displayName, tex, new ItemStack(ccm), false, enumtoolmaterial);
	}
	
	@Override
	public String getItemDisplayName(ItemStack is){
		return name;
	}
	
	@Override
	@SideOnly(Side.CLIENT) //Makes sure that only the client side can call this method
	public void registerIcons(IconRegister IR){
		this.itemIcon = IR.registerIcon(t);
	}
	
	@Override
	public boolean getIsRepairable(ItemStack i, ItemStack j){
		if(hasDam)
			return (i.itemID == itemID && j.itemID == ccm.itemID && j.getItemDamage() == ccm.getItemDamage());
		return (i.itemID == itemID && j.itemID == ccm.itemID);
	}
}
