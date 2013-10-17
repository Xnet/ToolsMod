// Coded by Flann

package mods.tools.src;

import static net.minecraftforge.common.Property.Type.BOOLEAN;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Property;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@NetworkMod(clientSideRequired=true,serverSideRequired=false)
@Mod(modid=CoreTools.modid,name="Tools Mod",version="#1")
public class CoreTools {
	
	public static final String modid = "Tools";
	public static final String texLoc = "tools:";
	
	public static int ItemID = 14600;
	
	public static boolean enableCoreSteel			= false;
	public static boolean enableCoreStickSteel		= false;
	public static boolean enableCoreIngotRedstone	= false;
	
	public static int emeraldID, netherrackID, obsidianID, redstoneID, steelID;
	public static boolean enableEmerald, enableNetherrack, enableObsidian, enableRedstone, enableSteel;
	
	public static Item swordE, spadeE, pickE, axeE, hoeE;
	public static Item swordN, spadeN, pickN, axeN, hoeN;
	public static Item swordO, spadeO, pickO, axeO, hoeO;
	public static Item swordR, spadeR, pickR, axeR, hoeR;
	public static Item swordS, spadeS, pickS, axeS, hoeS;
	
	public static EnumToolMaterial EMERALD = EnumHelper.addToolMaterial("EMERALD", 3, 1700, 10.0F, 6, 25);
	public static EnumToolMaterial NETHERRACK = EnumHelper.addToolMaterial("NETHERRACK", 1, 163, 4F, 1, 5);
	public static EnumToolMaterial OBSIDIAN = EnumHelper.addToolMaterial("OBSIDIAN", 3, 1049, 10F, 5, 8);
	public static EnumToolMaterial REDSTONE = EnumHelper.addToolMaterial("REDSTONE", 2, 218, 7F, 2, 14);
	public static EnumToolMaterial STEEL = EnumHelper.addToolMaterial("STEEL", 2, 500, 6F, 3, 14);
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
			emeraldID = config.get("item", "emeraldToolsID", ItemID+0, "5 tools (this+0 to this+4)").getInt()-256;
			netherrackID = config.get("item", "netherrackToolsID", ItemID+5, "5 tools (this+0 to this+4)").getInt()-256;
			obsidianID = config.get("item", "obsidianToolsID", ItemID+10, "5 tools (this+0 to this+4)").getInt()-256;
			redstoneID = config.get("item", "redstoneToolsID", ItemID+15, "5 tools (this+0 to this+4)").getInt()-256;
			steelID = config.get("item", "steelToolsID", ItemID+20, "5 tools (this+0 to this+4)").getInt()-256;
			
			enableEmerald = config.get("enable", "enableEmerald", true).getBoolean(true);
			enableNetherrack = config.get("enable", "enableNetherrack", true).getBoolean(true);
			enableObsidian = config.get("enable", "enableObsidian", true).getBoolean(true);
			enableRedstone = config.get("enable", "enableRedstone", true).getBoolean(true);
			enableSteel = config.get("enable", "enableSteel", true).getBoolean(true);
		config.save();
		
		Configuration coreConfig = new Configuration(new File(event.getSuggestedConfigurationFile().getPath().replace(modid, "FlannCore")));
		coreConfig.load();
			if(enableCoreSteel && enableSteel)
				set(coreConfig, "general", "enableSteel", true);
			if(enableCoreStickSteel)
				set(coreConfig, "general", "enableStickSteel", true);
			if(enableCoreIngotRedstone && enableRedstone)
				set(coreConfig, "general", "enableIngotRedstone", true);
		coreConfig.save();
		
		init_pre();
	}
	
	@Init
	public void load(FMLInitializationEvent event){
		init();
	}
	
	public void init_pre(){
		if(enableEmerald){
			String ingot = "emerald";
			String ingot2 = "emerald";
			boolean useOD = false;
			ItemStack ccm = new ItemStack(Item.emerald);
			boolean ccmDam = false;
			int iID = emeraldID;
			String Mat = "Emerald";
			
			swordE = new Flann_ItemSword  (iID+0, Mat+" Sword",  texLoc+"swordE", ccm, ccmDam, EMERALD).setUnlocalizedName("sword"+Mat);
			spadeE = new Flann_ItemSpade  (iID+1, Mat+" Shovel", texLoc+"spadeE", ccm, ccmDam, EMERALD).setUnlocalizedName("spade"+Mat);
			pickE =  new Flann_ItemPickaxe(iID+2, Mat+" Pickaxe",texLoc+"pickaxeE", ccm, ccmDam, EMERALD).setUnlocalizedName("pick"+Mat);
			axeE = 	 new Flann_ItemAxe	  (iID+3, Mat+" Axe", 	texLoc+"axeE", ccm, ccmDam, EMERALD).setUnlocalizedName("axe"+Mat);
			hoeE =	 new Flann_ItemHoe	  (iID+4, Mat+" Hoe", 	texLoc+"hoeE", ccm, ccmDam, EMERALD).setUnlocalizedName("hoe"+Mat);
		}
		if(enableNetherrack){
			String ingot = "netherrack";
			String ingot2 = "netherrack";
			boolean useOD = false;
			ItemStack ccm = new ItemStack(Block.netherrack);
			boolean ccmDam = false;
			int iID = netherrackID;
			String Mat = "Netherrack";
			
			swordN = new Flann_ItemSword  (iID+0, Mat+" Sword",  texLoc+  "swordN", ccm, ccmDam, NETHERRACK).setUnlocalizedName("sword"+Mat);
			spadeN = new Flann_ItemSpade  (iID+1, Mat+" Shovel", texLoc+  "spadeN", ccm, ccmDam, NETHERRACK).setUnlocalizedName("spade"+Mat);
			 pickN = new Flann_ItemPickaxe(iID+2, Mat+" Pickaxe",texLoc+"pickaxeN", ccm, ccmDam, NETHERRACK).setUnlocalizedName("pick"+Mat);
			  axeN = new Flann_ItemAxe	  (iID+3, Mat+" Axe", 	 texLoc+    "axeN", ccm, ccmDam, NETHERRACK).setUnlocalizedName("axe"+Mat);
			  hoeN = new Flann_ItemHoe	  (iID+4, Mat+" Hoe", 	 texLoc+    "hoeN", ccm, ccmDam, NETHERRACK).setUnlocalizedName("hoe"+Mat);
		}
		if(enableObsidian){
			String ingot = "obsidian";
			String ingot2 = "obsidian";
			boolean useOD = false;
			ItemStack ccm = new ItemStack(Block.obsidian);
			boolean ccmDam = false;
			int iID = obsidianID;
			String Mat = "Obsidian";
			
			swordO = new Flann_ItemSword  (iID+0, Mat+" Sword",  texLoc+  "swordO", ccm, ccmDam, OBSIDIAN).setUnlocalizedName("sword"+Mat);
			spadeO = new Flann_ItemSpade  (iID+1, Mat+" Shovel", texLoc+  "spadeO", ccm, ccmDam, OBSIDIAN).setUnlocalizedName("spade"+Mat);
			 pickO = new Flann_ItemPickaxe(iID+2, Mat+" Pickaxe",texLoc+"pickaxeO", ccm, ccmDam, OBSIDIAN).setUnlocalizedName("pick"+Mat);
			  axeO = new Flann_ItemAxe	  (iID+3, Mat+" Axe", 	 texLoc+    "axeO", ccm, ccmDam, OBSIDIAN).setUnlocalizedName("axe"+Mat);
			  hoeO = new Flann_ItemHoe	  (iID+4, Mat+" Hoe", 	 texLoc+    "hoeO", ccm, ccmDam, OBSIDIAN).setUnlocalizedName("hoe"+Mat);
		}
		if(enableRedstone){
			String ingot = "ingotRedstone";
			String ingot2 = "ingotRedstone";
			boolean useOD = true;
			ItemStack ccm = new ItemStack(Item.ingotIron);
			boolean ccmDam = false;
			int iID = redstoneID;
			String Mat = "Redstone";
			
			swordR = new Flann_ItemSword  (iID+0, Mat+" Sword",  texLoc+  "swordR", ccm, ccmDam, REDSTONE).setUnlocalizedName("sword"+Mat);
			spadeR = new Flann_ItemSpade  (iID+1, Mat+" Shovel", texLoc+  "spadeR", ccm, ccmDam, REDSTONE).setUnlocalizedName("spade"+Mat);
			 pickR = new Flann_ItemPickaxe(iID+2, Mat+" Pickaxe",texLoc+"pickaxeR", ccm, ccmDam, REDSTONE).setUnlocalizedName("pick"+Mat);
			  axeR = new Flann_ItemAxe	  (iID+3, Mat+" Axe", 	 texLoc+    "axeR", ccm, ccmDam, REDSTONE).setUnlocalizedName("axe"+Mat);
			  hoeR = new Flann_ItemHoe	  (iID+4, Mat+" Hoe", 	 texLoc+    "hoeR", ccm, ccmDam, REDSTONE).setUnlocalizedName("hoe"+Mat);
		}
		if(enableSteel){
			String ingot = "ingotSteel";
			String ingot2 = "ingotSteelUnhardened";
			boolean useOD = true;
			ItemStack ccm = new ItemStack(Item.ingotIron);
			boolean ccmDam = false;
			int iID = steelID;
			String Mat = "Steel";
			
			swordS = new Flann_ItemSword  (iID+0, Mat+" Sword",  texLoc+  "swordS", ccm, ccmDam, STEEL).setUnlocalizedName("sword"+Mat);
			spadeS = new Flann_ItemSpade  (iID+1, Mat+" Shovel", texLoc+  "spadeS", ccm, ccmDam, STEEL).setUnlocalizedName("spade"+Mat);
			 pickS = new Flann_ItemPickaxe(iID+2, Mat+" Pickaxe",texLoc+"pickaxeS", ccm, ccmDam, STEEL).setUnlocalizedName("pick"+Mat);
			  axeS = new Flann_ItemAxe	  (iID+3, Mat+" Axe", 	 texLoc+    "axeS", ccm, ccmDam, STEEL).setUnlocalizedName("axe"+Mat);
			  hoeS = new Flann_ItemHoe	  (iID+4, Mat+" Hoe", 	 texLoc+    "hoeS", ccm, ccmDam, STEEL).setUnlocalizedName("hoe"+Mat);
		}
	}
	
	public void init(){
		if(enableEmerald){
			String ingot = "emerald";
			String ingot2 = "emerald";
			boolean useOD = false;
			ItemStack ccm = new ItemStack(Item.emerald);
			boolean ccmDam = false;
			int iID = emeraldID;
			String Mat = "Emerald";
			
			MinecraftForge.setToolClass(spadeE, "shovel", EMERALD.getHarvestLevel());
			MinecraftForge.setToolClass(pickE, "pickaxe", EMERALD.getHarvestLevel());
			MinecraftForge.setToolClass(axeE, "axe", EMERALD.getHarvestLevel());
			
			OreDictionary.registerOre("sword"+Mat, swordE);
			OreDictionary.registerOre("spade"+Mat, spadeE);
			OreDictionary.registerOre("shovel"+Mat, spadeE);
			OreDictionary.registerOre("pick"+Mat, pickE);
			OreDictionary.registerOre("pickaxe"+Mat, pickE);
			OreDictionary.registerOre("hatchet"+Mat, axeE);
			OreDictionary.registerOre("axe"+Mat, axeE);
			OreDictionary.registerOre("hoe"+Mat, hoeE);
			
			if(useOD){
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(swordE), " # ", " # ", " @ ", Character.valueOf('#'), ingot, Character.valueOf('@'), Item.stick));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(spadeE), " # ", " @ ", " @ ", Character.valueOf('#'), ingot, Character.valueOf('@'), Item.stick));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack( pickE), "###", " @ ", " @ ", Character.valueOf('#'), ingot, Character.valueOf('@'), Item.stick));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(  axeE), "## ", "#@ ", " @ ", Character.valueOf('#'), ingot, Character.valueOf('@'), Item.stick));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(  hoeE), "## ", " @ ", " @ ", Character.valueOf('#'), ingot, Character.valueOf('@'), Item.stick));
				if(!ingot2.equals("") && !ingot2.equals(ingot)){
					GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(swordE), " # ", " # ", " @ ", Character.valueOf('#'), ingot2, Character.valueOf('@'), Item.stick));
					GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(spadeE), " # ", " @ ", " @ ", Character.valueOf('#'), ingot2, Character.valueOf('@'), Item.stick));
					GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack( pickE), "###", " @ ", " @ ", Character.valueOf('#'), ingot2, Character.valueOf('@'), Item.stick));
					GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(  axeE), "## ", "#@ ", " @ ", Character.valueOf('#'), ingot2, Character.valueOf('@'), Item.stick));
					GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(  hoeE), "## ", " @ ", " @ ", Character.valueOf('#'), ingot2, Character.valueOf('@'), Item.stick));
				}
			}else{
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(swordE), " # ", " # ", " @ ", Character.valueOf('#'), ccm, Character.valueOf('@'), Item.stick));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(spadeE), " # ", " @ ", " @ ", Character.valueOf('#'), ccm, Character.valueOf('@'), Item.stick));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack( pickE), "###", " @ ", " @ ", Character.valueOf('#'), ccm, Character.valueOf('@'), Item.stick));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(  axeE), "## ", "#@ ", " @ ", Character.valueOf('#'), ccm, Character.valueOf('@'), Item.stick));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(  hoeE), "## ", " @ ", " @ ", Character.valueOf('#'), ccm, Character.valueOf('@'), Item.stick));
			}
		}
		if(enableNetherrack){
			String ingot = "netherrack";
			String ingot2 = "netherrack";
			boolean useOD = false;
			ItemStack ccm = new ItemStack(Block.netherrack);
			boolean ccmDam = false;
			int iID = netherrackID;
			String Mat = "Netherrack";
			
			MinecraftForge.setToolClass(spadeN, "shovel", NETHERRACK.getHarvestLevel());
			MinecraftForge.setToolClass( pickN, "pickaxe", NETHERRACK.getHarvestLevel());
			MinecraftForge.setToolClass(  axeN, "axe", NETHERRACK.getHarvestLevel());
			
			OreDictionary.registerOre("sword"+Mat, 	swordN);
			OreDictionary.registerOre("spade"+Mat, 	spadeN);
			OreDictionary.registerOre("shovel"+Mat, spadeN);
			OreDictionary.registerOre("pick"+Mat, 	 pickN);
			OreDictionary.registerOre("pickaxe"+Mat, pickN);
			OreDictionary.registerOre("hatchet"+Mat,  axeN);
			OreDictionary.registerOre("axe"+Mat, 	  axeN);
			OreDictionary.registerOre("hoe"+Mat, 	  hoeN);
			
			if(useOD){
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(swordN), " # ", " # ", " @ ", Character.valueOf('#'), ingot, Character.valueOf('@'), Item.stick));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(spadeN), " # ", " @ ", " @ ", Character.valueOf('#'), ingot, Character.valueOf('@'), Item.stick));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack( pickN), "###", " @ ", " @ ", Character.valueOf('#'), ingot, Character.valueOf('@'), Item.stick));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(  axeN), "## ", "#@ ", " @ ", Character.valueOf('#'), ingot, Character.valueOf('@'), Item.stick));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(  hoeN), "## ", " @ ", " @ ", Character.valueOf('#'), ingot, Character.valueOf('@'), Item.stick));
				if(!ingot2.equals("") && !ingot2.equals(ingot)){
					GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(swordN), " # ", " # ", " @ ", Character.valueOf('#'), ingot2, Character.valueOf('@'), Item.stick));
					GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(spadeN), " # ", " @ ", " @ ", Character.valueOf('#'), ingot2, Character.valueOf('@'), Item.stick));
					GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack( pickN), "###", " @ ", " @ ", Character.valueOf('#'), ingot2, Character.valueOf('@'), Item.stick));
					GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(  axeN), "## ", "#@ ", " @ ", Character.valueOf('#'), ingot2, Character.valueOf('@'), Item.stick));
					GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(  hoeN), "## ", " @ ", " @ ", Character.valueOf('#'), ingot2, Character.valueOf('@'), Item.stick));
				}
			}else{
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(swordN), " # ", " # ", " @ ", Character.valueOf('#'), ccm, Character.valueOf('@'), Item.stick));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(spadeN), " # ", " @ ", " @ ", Character.valueOf('#'), ccm, Character.valueOf('@'), Item.stick));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack( pickN), "###", " @ ", " @ ", Character.valueOf('#'), ccm, Character.valueOf('@'), Item.stick));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(  axeN), "## ", "#@ ", " @ ", Character.valueOf('#'), ccm, Character.valueOf('@'), Item.stick));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(  hoeN), "## ", " @ ", " @ ", Character.valueOf('#'), ccm, Character.valueOf('@'), Item.stick));
			}
		}
		if(enableObsidian){
			String ingot = "obsidian";
			String ingot2 = "obsidian";
			boolean useOD = false;
			ItemStack ccm = new ItemStack(Block.obsidian);
			boolean ccmDam = false;
			int iID = obsidianID;
			String Mat = "Obsidian";
			
			MinecraftForge.setToolClass(spadeO, "shovel", OBSIDIAN.getHarvestLevel());
			MinecraftForge.setToolClass( pickO, "pickaxe", OBSIDIAN.getHarvestLevel());
			MinecraftForge.setToolClass(  axeO, "axe", OBSIDIAN.getHarvestLevel());
			
			OreDictionary.registerOre("sword"+Mat, 	swordO);
			OreDictionary.registerOre("spade"+Mat, 	spadeO);
			OreDictionary.registerOre("shovel"+Mat, spadeO);
			OreDictionary.registerOre("pick"+Mat, 	 pickO);
			OreDictionary.registerOre("pickaxe"+Mat, pickO);
			OreDictionary.registerOre("hatchet"+Mat,  axeO);
			OreDictionary.registerOre("axe"+Mat, 	  axeO);
			OreDictionary.registerOre("hoe"+Mat, 	  hoeO);
			
			if(useOD){
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(swordO), " # ", " # ", " @ ", Character.valueOf('#'), ingot, Character.valueOf('@'), Item.stick));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(spadeO), " # ", " @ ", " @ ", Character.valueOf('#'), ingot, Character.valueOf('@'), Item.stick));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack( pickO), "###", " @ ", " @ ", Character.valueOf('#'), ingot, Character.valueOf('@'), Item.stick));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(  axeO), "## ", "#@ ", " @ ", Character.valueOf('#'), ingot, Character.valueOf('@'), Item.stick));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(  hoeO), "## ", " @ ", " @ ", Character.valueOf('#'), ingot, Character.valueOf('@'), Item.stick));
				if(!ingot2.equals("") && !ingot2.equals(ingot)){
					GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(swordO), " # ", " # ", " @ ", Character.valueOf('#'), ingot2, Character.valueOf('@'), Item.stick));
					GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(spadeO), " # ", " @ ", " @ ", Character.valueOf('#'), ingot2, Character.valueOf('@'), Item.stick));
					GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack( pickO), "###", " @ ", " @ ", Character.valueOf('#'), ingot2, Character.valueOf('@'), Item.stick));
					GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(  axeO), "## ", "#@ ", " @ ", Character.valueOf('#'), ingot2, Character.valueOf('@'), Item.stick));
					GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(  hoeO), "## ", " @ ", " @ ", Character.valueOf('#'), ingot2, Character.valueOf('@'), Item.stick));
				}
			}else{
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(swordO), " # ", " # ", " @ ", Character.valueOf('#'), ccm, Character.valueOf('@'), Item.stick));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(spadeO), " # ", " @ ", " @ ", Character.valueOf('#'), ccm, Character.valueOf('@'), Item.stick));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack( pickO), "###", " @ ", " @ ", Character.valueOf('#'), ccm, Character.valueOf('@'), Item.stick));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(  axeO), "## ", "#@ ", " @ ", Character.valueOf('#'), ccm, Character.valueOf('@'), Item.stick));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(  hoeO), "## ", " @ ", " @ ", Character.valueOf('#'), ccm, Character.valueOf('@'), Item.stick));
			}
		}
		if(enableRedstone){
			String ingot = "ingotRedstone";
			String ingot2 = "ingotRedstone";
			boolean useOD = true;
			ItemStack ccm = new ItemStack(Item.ingotIron);
			boolean ccmDam = false;
			int iID = redstoneID;
			String Mat = "Redstone";
			
			MinecraftForge.setToolClass(spadeR, "shovel", REDSTONE.getHarvestLevel());
			MinecraftForge.setToolClass( pickR, "pickaxe", REDSTONE.getHarvestLevel());
			MinecraftForge.setToolClass(  axeR, "axe", REDSTONE.getHarvestLevel());
			
			OreDictionary.registerOre("sword"+Mat, 	swordR);
			OreDictionary.registerOre("spade"+Mat, 	spadeR);
			OreDictionary.registerOre("shovel"+Mat, spadeR);
			OreDictionary.registerOre("pick"+Mat, 	 pickR);
			OreDictionary.registerOre("pickaxe"+Mat, pickR);
			OreDictionary.registerOre("hatchet"+Mat,  axeR);
			OreDictionary.registerOre("axe"+Mat, 	  axeR);
			OreDictionary.registerOre("hoe"+Mat, 	  hoeR);
			
			if(useOD){
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(swordR), " # ", " # ", " @ ", Character.valueOf('#'), ingot, Character.valueOf('@'), Item.stick));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(spadeR), " # ", " @ ", " @ ", Character.valueOf('#'), ingot, Character.valueOf('@'), Item.stick));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack( pickR), "###", " @ ", " @ ", Character.valueOf('#'), ingot, Character.valueOf('@'), Item.stick));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(  axeR), "## ", "#@ ", " @ ", Character.valueOf('#'), ingot, Character.valueOf('@'), Item.stick));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(  hoeR), "## ", " @ ", " @ ", Character.valueOf('#'), ingot, Character.valueOf('@'), Item.stick));
				if(!ingot2.equals("") && !ingot2.equals(ingot)){
					GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(swordR), " # ", " # ", " @ ", Character.valueOf('#'), ingot2, Character.valueOf('@'), Item.stick));
					GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(spadeR), " # ", " @ ", " @ ", Character.valueOf('#'), ingot2, Character.valueOf('@'), Item.stick));
					GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack( pickR), "###", " @ ", " @ ", Character.valueOf('#'), ingot2, Character.valueOf('@'), Item.stick));
					GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(  axeR), "## ", "#@ ", " @ ", Character.valueOf('#'), ingot2, Character.valueOf('@'), Item.stick));
					GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(  hoeR), "## ", " @ ", " @ ", Character.valueOf('#'), ingot2, Character.valueOf('@'), Item.stick));
				}
			}else{
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(swordR), " # ", " # ", " @ ", Character.valueOf('#'), ccm, Character.valueOf('@'), Item.stick));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(spadeR), " # ", " @ ", " @ ", Character.valueOf('#'), ccm, Character.valueOf('@'), Item.stick));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack( pickR), "###", " @ ", " @ ", Character.valueOf('#'), ccm, Character.valueOf('@'), Item.stick));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(  axeR), "## ", "#@ ", " @ ", Character.valueOf('#'), ccm, Character.valueOf('@'), Item.stick));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(  hoeR), "## ", " @ ", " @ ", Character.valueOf('#'), ccm, Character.valueOf('@'), Item.stick));
			}
		}
		if(enableSteel){
			String ingot = "ingotSteel";
			String ingot2 = "ingotSteelUnhardened";
			boolean useOD = true;
			ItemStack ccm = new ItemStack(Item.ingotIron);
			boolean ccmDam = false;
			int iID = steelID;
			String Mat = "Steel";
			
			MinecraftForge.setToolClass(spadeS, "shovel", STEEL.getHarvestLevel());
			MinecraftForge.setToolClass( pickS, "pickaxe", STEEL.getHarvestLevel());
			MinecraftForge.setToolClass(  axeS, "axe", STEEL.getHarvestLevel());
			
			OreDictionary.registerOre("sword"+Mat, 	swordS);
			OreDictionary.registerOre("spade"+Mat, 	spadeS);
			OreDictionary.registerOre("shovel"+Mat, spadeS);
			OreDictionary.registerOre("pick"+Mat, 	 pickS);
			OreDictionary.registerOre("pickaxe"+Mat, pickS);
			OreDictionary.registerOre("hatchet"+Mat,  axeS);
			OreDictionary.registerOre("axe"+Mat, 	  axeS);
			OreDictionary.registerOre("hoe"+Mat, 	  hoeS);
			
			if(useOD){
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(swordS), " # ", " # ", " @ ", Character.valueOf('#'), ingot, Character.valueOf('@'), Item.stick));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(spadeS), " # ", " @ ", " @ ", Character.valueOf('#'), ingot, Character.valueOf('@'), Item.stick));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack( pickS), "###", " @ ", " @ ", Character.valueOf('#'), ingot, Character.valueOf('@'), Item.stick));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(  axeS), "## ", "#@ ", " @ ", Character.valueOf('#'), ingot, Character.valueOf('@'), Item.stick));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(  hoeS), "## ", " @ ", " @ ", Character.valueOf('#'), ingot, Character.valueOf('@'), Item.stick));
				if(!ingot2.equals("") && !ingot2.equals(ingot)){
					GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(swordS), " # ", " # ", " @ ", Character.valueOf('#'), ingot2, Character.valueOf('@'), Item.stick));
					GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(spadeS), " # ", " @ ", " @ ", Character.valueOf('#'), ingot2, Character.valueOf('@'), Item.stick));
					GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack( pickS), "###", " @ ", " @ ", Character.valueOf('#'), ingot2, Character.valueOf('@'), Item.stick));
					GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(  axeS), "## ", "#@ ", " @ ", Character.valueOf('#'), ingot2, Character.valueOf('@'), Item.stick));
					GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(  hoeS), "## ", " @ ", " @ ", Character.valueOf('#'), ingot2, Character.valueOf('@'), Item.stick));
				}
			}else{
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(swordS), " # ", " # ", " @ ", Character.valueOf('#'), ccm, Character.valueOf('@'), Item.stick));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(spadeS), " # ", " @ ", " @ ", Character.valueOf('#'), ccm, Character.valueOf('@'), Item.stick));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack( pickS), "###", " @ ", " @ ", Character.valueOf('#'), ccm, Character.valueOf('@'), Item.stick));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(  axeS), "## ", "#@ ", " @ ", Character.valueOf('#'), ccm, Character.valueOf('@'), Item.stick));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(  hoeS), "## ", " @ ", " @ ", Character.valueOf('#'), ccm, Character.valueOf('@'), Item.stick));
			}
		}
	}
	
	public Property set(Configuration config, String category, String key, boolean defaultValue)
    {
		return set(config, category, key, defaultValue, null);
    }
	public Property set(Configuration config, String category, String key, boolean defaultValue, String comment)
    {
        Property prop = config.get(category, key, Boolean.toString(defaultValue), comment, BOOLEAN);
        prop.set(defaultValue);
        return prop;
    }
}
