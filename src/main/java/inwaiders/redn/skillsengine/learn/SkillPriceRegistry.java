package inwaiders.redn.skillsengine.learn;

import inwaiders.redn.skillsengine.bank.PlayerSkillBankServer;
import java.util.HashMap;

public class SkillPriceRegistry
{

	private static final HashMap<Integer, LeanPointsPrice> prices = new HashMap<Integer, LeanPointsPrice>();

	public static void registerSkill(int id, LeanPointsPrice base)
	{
		prices.put(id, base);
	}

	private static LeanPointsPrice getSkill(int i)
	{
		return prices.get(i);
	}

	public static int getSize()
	{
		return prices.size();
	}

	public static LeanPointsPrice getSkillCPById(int id)
	{
		return prices.get(id);
	}

	public static boolean learnSkill(PlayerSkillBankServer s, LearnPointsServer p, int id)
	{
		LeanPointsPrice c = getSkillCPById(id);
		if (p.canLearn(c.getPrice()))
		{
			s.learnSkill(id);
			p.learn(c.getPrice());
			return true;
		}
		return false;

	}

}
