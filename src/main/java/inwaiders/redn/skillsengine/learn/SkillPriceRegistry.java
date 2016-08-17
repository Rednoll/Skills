package inwaiders.redn.skillsengine.learn;

import inwaiders.redn.skillsengine.bank.BankManagerServer;
import inwaiders.redn.skillsengine.bank.PlayerSkillBankServer;
import java.util.HashMap;
import net.minecraft.entity.player.EntityPlayer;

public class SkillPriceRegistry
{

	private static final HashMap<Integer, LearnPointsPrice> prices = new HashMap<Integer, LearnPointsPrice>();

	public static void registerSkill(int id, LearnPointsPrice base)
	{
		prices.put(id, base);
	}

	private static LearnPointsPrice getSkill(int i)
	{
		return prices.get(i);
	}

	public static int getSize()
	{
		return prices.size();
	}

	public static LearnPointsPrice getSkillCPById(int id)
	{
		return prices.get(id);
	}

	public static boolean learnSkill(PlayerSkillBankServer s, LearnPointsServer p, int id)
	{
		LearnPointsPrice c = getSkillCPById(id);
		if (p.canLearn(c.getPrice()))
		{
			s.learnSkill(id);
			p.learn(c.getPrice());
			return true;
		}
		return false;

	}
	
	public static boolean learnSkill(EntityPlayer p, int id)
	{
		return learnSkill(BankManagerServer.instance.get(p), PlayerLearnPointManagerServer.instance.get(p), id);
	}

}
