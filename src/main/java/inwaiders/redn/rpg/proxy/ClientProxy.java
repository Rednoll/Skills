package inwaiders.redn.rpg.proxy;

import inwaiders.redn.rpg.gui.gui.SkillRenderer;
import inwaiders.redn.rpg.gui.helpers.TableHarrington;
import inwaiders.redn.rpg.handlers.SkillsKeys;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class ClientProxy extends CommonProxy{
	
	@Override
	public void init(FMLInitializationEvent e)
	{
		super.init(e);
		TableHarrington.initialize();
		SkillsKeys.init();
		MinecraftForge.EVENT_BUS.register(new SkillsKeys());
		MinecraftForge.EVENT_BUS.register(new SkillRenderer(Minecraft.getMinecraft()));
	}
	
	@Override
	public EntityPlayer getPlayerEntity(MessageContext ctx)
	{
		return (ctx.side.isClient() ? Minecraft.getMinecraft().thePlayer : super.getPlayerEntity(ctx));
	}
}
