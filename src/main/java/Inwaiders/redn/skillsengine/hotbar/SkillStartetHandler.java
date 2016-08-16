package Inwaiders.redn.skillsengine.hotbar;

import Inwaiders.redn.rpg.packetdispatcher.PacketDispatcher;
import Inwaiders.redn.skillsengine.bank.GeterBCtoP;
import Inwaiders.redn.skillsengine.bank.SkillsBankClientProvider;
import Inwaiders.redn.skillsengine.bank.SkillsStartPacket;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

public class SkillStartetHandler {

	   @SubscribeEvent
	   public void onKeyInput(InputEvent.KeyInputEvent event) {
		   
		   KeyBinding[] keys = new KeyBinding[6];
		   
		   keys[0] = SkillsKeys.skill1;
		   keys[1] = SkillsKeys.skill2;
		   keys[2] = SkillsKeys.skill3;
		   keys[3] = SkillsKeys.skill4;
		   keys[4] = SkillsKeys.skill5;
		   keys[5] = SkillsKeys.skill6;
		   
		   for(int i = 0;i<keys.length;i++){

			   if(keys[i].isPressed()){
				   
				   Minecraft mc = Minecraft.getMinecraft();
				   
				   SkillsHotBar h = GeterHtoP.getParam(mc.thePlayer.getEntityId());
				   SkillsBankClientProvider b = GeterBCtoP.getParam(mc.thePlayer);
				   
				   if(b.hasSkill(h.getSkillIdByPos(i))){
					   PacketDispatcher.sendToServer(new SkillsStartPacket(h.getSkillIdByPos(i)));
					   b.activeSkill(h.getSkillIdByPos(i), mc.thePlayer);
				   }
					   
			   }
		   }

	   }
}
