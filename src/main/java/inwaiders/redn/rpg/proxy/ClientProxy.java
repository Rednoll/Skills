package inwaiders.redn.rpg.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import inwaiders.redn.rpg.gui.gui.SkillRenderer;
import inwaiders.redn.rpg.gui.gui.ThirdEyeGui;
import inwaiders.redn.rpg.gui.helpers.TableHarrington;
import inwaiders.redn.rpg.handlers.SkillsKeys;
import inwaiders.redn.rpg.render.tile.SkillInjectorRenderer;
import inwaiders.redn.rpg.tiles.TileSkillInjector;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy{
	
	@Override
	public void init(FMLInitializationEvent e)
	{
		super.init(e);
		TableHarrington.initialize();
		SkillsKeys.init();
		FMLCommonHandler.instance().bus().register(new SkillsKeys());
		MinecraftForge.EVENT_BUS.register(new SkillRenderer(Minecraft.getMinecraft()));
		//MinecraftForge.EVENT_BUS.register(new ThirdEyeGui(Minecraft.getMinecraft()));
		ClientRegistry.bindTileEntitySpecialRenderer(TileSkillInjector.class, new SkillInjectorRenderer());
		
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent e) {
		super.postInit(e);
//		ClientRegistry.bindTileEntitySpecialRenderer(TileSkillInjector.class, new SkillInjectorRenderer());
	}
	
	@Override
	public EntityPlayer getPlayerEntity(MessageContext ctx)
	{
		return (ctx.side.isClient() ? Minecraft.getMinecraft().thePlayer : super.getPlayerEntity(ctx));
	}
}
