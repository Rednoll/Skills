package Inwaiders.redn.skillsengine.hotbar;

import org.lwjgl.opengl.GL11;

import Inwaiders.redn.rpg.base.base;
import Inwaiders.redn.skillsengine.bank.GeterBCtoP;
import Inwaiders.redn.skillsengine.bank.SkillsBankClientProvider;
import Inwaiders.redn.skillsengine.bank.SkillsBankServerProvider;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

public class SkillRenderer extends Gui {
	
    private Minecraft mc;
    private static final ResourceLocation texturepath = new ResourceLocation(base.MODID, "textures/base/skills_bar.png");

    public SkillRenderer(Minecraft mc) {
        super();
        
        this.mc = mc;
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onRenderExperienceBar(RenderGameOverlayEvent event) {
    	
    	if(event.type == ElementType.HOTBAR){
    		
    	    ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
   	     
    		int xPos = sr.getScaledWidth()/2-60;
    		
    		int yPos = (int)(sr.getScaledWidth()/2.08F);
    		
    		yPos = 0;
    		
            int size = 159;
            int sizefood = 134;
           
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glDepthMask(false);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            this.mc.getTextureManager().bindTexture(texturepath);

            drawTexturedModalRect(xPos, yPos, 0, 0, 122, 22);
           
            for(int i = 0;i<6;i++){
            	renderSkill(i, xPos+3+20*i, yPos+3);
            }
            
    
            	
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glDepthMask(true);
    	
    	}
    }
    
    public void renderSkill(int pos, int xPos, int yPos){
    	
    	SkillsHotBar se = null;
    	SkillsBankClientProvider b = null;
    	int id = mc.thePlayer.getEntityId();
    	
    	se = GeterHtoP.getParam(id);
    	b = GeterBCtoP.getParam(mc.thePlayer);
    	
    	int posId = se.getSkillIdByPos(pos);
    	
    	if(b.getTextureById(posId) != null){
    		this.mc.getTextureManager().bindTexture(b.getTextureById(posId));
    		
    		GL11.glPushMatrix();
    		GL11.glScalef(0.5F, 0.5F, 0.5F);
    		mc.ingameGUI.drawTexturedModalRect(xPos*2, yPos*2, 0, 0, 32, 32);
    		
    		if(b.getCoolDownById(posId) != 0)		
    			mc.fontRenderer.drawString(""+b.getCoolDownById(posId)/20,(xPos + 11)*2 - mc.fontRenderer.getStringWidth(""+b.getCoolDownById(posId)/20), yPos*2 +12 ,0xFF0000);
    		
    		this.mc.getTextureManager().bindTexture(new ResourceLocation("minecraft:textures/gui/icons.png"));
    		
    		GL11.glColor3f(1, 1, 1);
    		GL11.glPopMatrix();
    	}
    }

}