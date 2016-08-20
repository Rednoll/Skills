package inwaiders.redn.rpg.files.json;

import inwaiders.redn.rpg.Constants;
import inwaiders.redn.rpg.utils.FileUtils;
import inwaiders.redn.rpg.utils.MiscUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import net.minecraft.entity.player.EntityPlayer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
	public static final String HOTBAR = "hotbar";//Object, contains 6 string, named by their position (1,2,3,4,5,6)
	public static final String BANK = "bank";//Array
	public static final String TEAM = "team";//String
	public static final String LEARNPOINTS = "learnpts";//Int
	public static final String XP = "XP";//Int
	public static final String LEVEL = "lvl";
	//Bank skill
	public static final String NAME = "name";//String
	public static final String LVL = "lvl";//Int
	public static final String COOLDOWN = "cd";//Int
	
	private final String name;
	private final File file;
	private final JsonObject json;

	public PlayerJson(EntityPlayer p, boolean forceRewrite)
	{
		name = p.getCommandSenderName();
		file = new File(FileUtils.generateSubDir(p.worldObj.getSaveHandler().getWorldDirectory(), "skillengine"), "SKILLS_" + name + ".json");
		if (forceRewrite || !file.exists())
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

	public PlayerJson(EntityPlayer p) {
		this(p, false);
	}
	
	private void initJson()
	{
		initHotbar();
		initBank();
		json.addProperty(TEAM, "ANY");
		json.addProperty(LEARNPOINTS, 0);
		json.addProperty(XP, 0);
		json.addProperty(LVL, 0);
	}

	public void write()
	{
		try
		{
			FileUtils.initFile(file);
			new FileOutputStream(file).write(new GsonBuilder().setPrettyPrinting().create().toJson(json).getBytes());
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
		for (int i = 0; i < 6; i++)
		{
			hotbar.addProperty(i + "", "NONE");
		}
		json.add(HOTBAR, hotbar);
	}

	public void setHotbar(int slot, String name)
	{
		JsonObject skills = json.getAsJsonObject(HOTBAR);
		skills.addProperty(slot + "", name);
		json.add(HOTBAR, skills);
	}

	public String getHotbar(int slot)
	{
		return json.getAsJsonObject(HOTBAR).get(slot + "").getAsString();
	}
	
	//Bank
	private void initBank()
	{
		JsonArray bank = new JsonArray();
		json.add(BANK, bank);
	}
	
	public void addBank(String name, int lvl, int cd)
	{
		JsonArray bank = json.getAsJsonArray(BANK);
		JsonObject skill = new JsonObject();
		writeSkill(skill, name, lvl, cd);
		bank.add(skill);
	}
	 
	public BankSkill getBank(int slot)
	{
		JsonArray bank = json.getAsJsonArray(BANK);
		if(bank.size() <= slot)
		{
			return null;
		}
		JsonObject skill = bank.get(slot).getAsJsonObject();
		return new BankSkill(skill.get(NAME).getAsString(), skill.get(LVL).getAsInt(), skill.get(COOLDOWN).getAsInt());
	}
	
	private static void writeSkill(JsonObject json, String name, int lvl, int cd)
	{
		json.addProperty(NAME, name);
		json.addProperty(LVL, lvl);
		json.addProperty(COOLDOWN, cd);
	}
	
	public static class BankSkill
	{
		public final int lvl, cd;
		public final String name;
		public BankSkill(String name, int lvl, int cd)
		{
			this.name = name;
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
	
	//Learn
	public void setLearnPoints(int points)
	{
		json.addProperty(LEARNPOINTS, points);
	}
	
	public int getLearnPoints() {
		return json.get(LEARNPOINTS).getAsInt();
	}
	
	public void setXP(int xp)
	{
		json.addProperty(XP, xp);
	}
	
	public int getXP() {
		return json.get(XP).getAsInt();
	}
	
	public void setLvl(int lvl)
	{
		json.addProperty(LVL, lvl);
	}
	
	public int getLvl()
	{
		return json.get(LVL).getAsInt();
	}
	
}
