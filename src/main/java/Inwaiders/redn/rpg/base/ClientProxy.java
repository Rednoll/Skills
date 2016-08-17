package inwaiders.redn.rpg.base;

import inwaiders.redn.skillsengine.hotbar.SkillRenderer;
import inwaiders.redn.skillsengine.hotbar.SkillStartetHandler;
import inwaiders.redn.skillsengine.hotbar.SkillsKeys;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy{
	
	@Override
	public void init(FMLInitializationEvent e)
	{
		super.init(e);
		MinecraftForge.EVENT_BUS.register(new SkillRenderer(Minecraft.getMinecraft()));
		SkillsKeys.init();
		FMLCommonHandler.instance().bus().register(new SkillStartetHandler());
	}
	
	@Override
	public EntityPlayer getPlayerEntity(MessageContext ctx)
	{
		return (ctx.side.isClient() ? Minecraft.getMinecraft().thePlayer : super.getPlayerEntity(ctx));
	}
}
