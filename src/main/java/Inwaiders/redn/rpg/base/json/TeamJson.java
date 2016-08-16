package Inwaiders.redn.rpg.base.json;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import Inwaiders.redn.rpg.base.utils.FileUtils;
import Inwaiders.redn.rpg.base.utils.MiscUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TeamJson
{
	//Names
	//Main|Member
	public static final String NAME = "name";//String
	//Main
	public static final String OWNER = "owner";//String
	public static final String SIZE = "size";//Int
	public static final String MEMBERS = "members";//Array of members;
	
	private final String name;
	private final File file;
	private final JsonObject json;

	public TeamJson(String name, File worlddir)
	{
		this.name = name;
		file = new File(FileUtils.generateSubDir(worlddir, "skillengine"), "TEAM_" + name + ".json");
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
		json.addProperty(NAME, name);
		json.addProperty(OWNER, "");
		json.addProperty(SIZE, 0);
		json.add(MEMBERS, new JsonArray());
	}
	
	public void write()
	{
		try
		{
			System.out.println(json.toString());
			file.delete();
			FileUtils.initFile(file);
			new FileOutputStream(file).write(json.toString().getBytes());
		}
		catch (IOException e)
		{
			MiscUtils.crashGame("[SKILLENGINE] Unable to write team data", e);
		}
	}
	
	//Name
	public void setName(String name)
	{
		json.addProperty(NAME, name);
	}
	
	public String getName()
	{
		return json.get(NAME).getAsString();
	}
	
	//Owner
	public void setOwner(String owner)
	{
		json.addProperty(OWNER, owner);
	}
	
	public String getOwner()
	{
		return json.get(OWNER).getAsString();
	}
	
	//Size
	public void setSize(int size)
	{
		json.addProperty(SIZE, size);
	}
	
	public int getSize()
	{
		return json.get(SIZE).getAsInt();
	}
	
	public void incrSize()
	{
		setSize(getSize() + 1);
	}
	
	public void decrSize()
	{
		setSize(getSize() - 1);
	}
	
	//Members
	/**
		Also setting new size
	 */
	public void setMembers(EntityPlayer[] members)
	{
		JsonArray m = new JsonArray();
		for(EntityPlayer s : members)
		{
			System.out.println("adding member " + s.getCommandSenderName());
			m.add(createMember(s.getCommandSenderName()));
		}
		json.add(MEMBERS, m);
		setSize(members.length);
	}
	
	public void setMembers(List<EntityPlayer> members)
	{
		setMembers(members.toArray(new EntityPlayer[members.size()]));
	}
	
	/**
	 	Also increasing size
	 */
	public void addMember(String name)
	{
		JsonArray members = json.getAsJsonArray(MEMBERS);
		members.add(createMember(name));
		incrSize();
	}
	
	public void removeMember(String name)
	{
		JsonArray members = json.getAsJsonArray(MEMBERS);
		JsonArray newmembers = new JsonArray();
		for(int i = 0; i < members.size(); i++)
		{
			if(!name.equals(members.get(i).getAsJsonObject().get(NAME).getAsString()))
			{
				newmembers.add(members.get(i));
			}
		}
		json.add(MEMBERS, newmembers);
		decrSize();
	}
	
	public String[] getMembers()
	{
		ArrayList<String> list = new Gson().fromJson(json.getAsJsonArray(MEMBERS), ArrayList.class);
		return list.toArray(new String[list.size()]);
	}
	
	private JsonObject createMember(String name)
	{
		JsonObject ret = new JsonObject();
		ret.addProperty(NAME, name);
		return ret;
	}
}
