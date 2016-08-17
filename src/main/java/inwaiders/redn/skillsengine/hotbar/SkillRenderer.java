package inwaiders.redn.skillsengine.hotbar;

import inwaiders.redn.rpg.base.core.Core;
import inwaiders.redn.skillsengine.bank.BankManagerClient;
import inwaiders.redn.skillsengine.bank.PlayerSkillBankClient;
import inwaiders.redn.skillsengine.bank.PlayerSkillBankServer;
import org.lwjgl.opengl.GL11;
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
    private static final ResourceLocation texturepath = new ResourceLocation(Core.MODID, "textures/base/skills_bar.png");

    public SkillRenderer(Minecraft mc) {
        super();
        
        this.mc = mc;
    }

    @SubscribeEvent()
    public void onHUDRender(RenderGameOverlayEvent event) {
    	
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
    	
    	SkillsHotBar se = SkillsHotBarManager.instance.get(mc.thePlayer);
    	PlayerSkillBankClient b = BankManagerClient.instance.get(mc.thePlayer);
    	
    	int id = se.getSkillIdByPos(pos);
    	ResourceLocation skilltexture = b.getTextureById(id);
    	if(skilltexture != null){
    		this.mc.getTextureManager().bindTexture(skilltexture);	
    		GL11.glPushMatrix();
    		GL11.glScalef(0.5F, 0.5F, 0.5F);
    		mc.ingameGUI.drawTexturedModalRect(xPos*2, yPos*2, 0, 0, 32, 32);
    		int cd = b.getCoolDownById(id);
    		if(cd != 0)		
    			mc.fontRenderer.drawString(""+cd/20,(xPos + 11)*2 - mc.fontRenderer.getStringWidth(""+cd/20), yPos*2 +12 ,0xFF0000);
    		this.mc.getTextureManager().bindTexture(new ResourceLocation("minecraft:textures/gui/icons.png"));
    		GL11.glColor3f(1, 1, 1);
    		GL11.glPopMatrix();
    	}
    }

}