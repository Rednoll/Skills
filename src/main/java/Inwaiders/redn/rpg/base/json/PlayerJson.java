package inwaiders.redn.rpg.base.json;

import inwaiders.redn.rpg.base.utils.FileUtils;
import inwaiders.redn.rpg.base.utils.MiscUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import net.minecraft.entity.player.EntityPlayer;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

/**
 	Json for saving info about player: Skill hotbar, Skill bank and team
 	@author Gt22
 */
public class PlayerJson
{
	//Names
	//Main
	public static final String HOTBAR = "hotbar";//Object
	public static final String BANK = "bank";//Object
	public static final String TEAM = "team";//String
	//Hotbar
	public static final String SKILLS = "skills";//Array
	//Bank
	public static final String SKILL = "skill-";//Object, index mus be added after -
	//Hobar|Bank skill
	public static final String ID = "id";//Int
	//Bank skill
	public static final String LVL = "lvl";//Int
	public static final String COOLDOWN = "cd";//Int
	
	private final String name;
	private final File file;
	private final JsonObject json;

	public PlayerJson(EntityPlayer p)
	{
		name = p.getCommandSenderName();
		file = new File(FileUtils.generateSubDir(p.worldObj.getSaveHandler().getWorldDirectory(), "skillengine"), "SKILLS_" + name + ".json");
		if (!file.exists())
		{
			json = new JsonObject();
			initJson();
			write();	
		}
		else
		{
			try
			{
				json = (JsonObject) new JsonParser().parse(new InputStreamReader(new FileInputStream(file)));
			}
			catch (Exception e)
			{
				MiscUtils.crashGame("[SKILLENGINE] Unable to read player info, do you edited it manualy?", e);
				throw new RuntimeException();//Unreacheble, but created becouse we need to initialize json field, or destroy program,
				//but if we make json = null compiler will say that json can be alredy initializer
			}
		}
	}

	private void initJson()
	{
		initHotbar();
		initBank();
		json.addProperty(TEAM, "ANY");
	}

	public void write()
	{
		try
		{
			FileUtils.initFile(file);
			new FileOutputStream(file).write(json.toString().getBytes());
		}
		catch (IOException e)
		{
			MiscUtils.crashGame("[SKILLENGINE] Unable to write player data", e);
		}
	}
	
	//Hotbar
	private void initHotbar()
	{
		JsonObject hotbar = new JsonObject();
		JsonArray hbskills = new JsonArray();
		JsonObject skillplaceholder = new JsonObject();
		skillplaceholder.addProperty("id", -1);
		for (int i = 0; i < 6; i++)
		{
			hbskills.add(skillplaceholder);
		}
		hotbar.add(SKILLS, hbskills);
		json.add(HOTBAR, hotbar);
	}

	public void setHotbar(int slot, int id)
	{
		JsonObject skill = json.getAsJsonObject(HOTBAR).getAsJsonArray(SKILLS).get(slot).getAsJsonObject();
		skill.addProperty(ID, id);
	}

	public int getHotbar(int slot)
	{
		return json.getAsJsonObject(HOTBAR).getAsJsonArray(SKILLS).get(slot).getAsJsonObject().get(ID).getAsInt();
	}
	
	//Bank
	private void initBank()
	{
		JsonObject bank = new JsonObject();
		json.add(BANK, bank);
	}
	
	public void setBank(int slot, int id, int lvl, int cd)
	{
		JsonObject bank = json.getAsJsonObject(BANK);
		JsonObject skill = new JsonObject();
		writeSkill(skill, id, lvl, cd);
		bank.add(SKILL + slot, skill);
	}
	 
	public BankSkill getBank(int slot)
	{
		JsonObject bank = json.getAsJsonObject(BANK);
		if(!bank.has(SKILL + slot))
		{
			return null;
		}
		JsonObject skill = bank.getAsJsonObject(SKILL + slot);
		return new BankSkill(skill.get(ID).getAsInt(), skill.get(LVL).getAsInt(), skill.get(COOLDOWN).getAsInt());
	}
	
	private static void writeSkill(JsonObject json, int id, int lvl, int cd)
	{
		json.addProperty(ID, id);
		json.addProperty(LVL, lvl);
		json.addProperty(COOLDOWN, cd);
	}
	
	public static class BankSkill
	{
		public final int id, lvl, cd;
		public BankSkill(int id, int lvl, int cd)
		{
			this.id = id;
			this.lvl = lvl;
			this.cd = cd;
		}
	}
	
	//Team
	public void setTeam(String team)
	{
		json.addProperty(TEAM, team);
	}
	
	public String getTeam()
	{
		return json.get(TEAM).getAsString();
	}
	
}
