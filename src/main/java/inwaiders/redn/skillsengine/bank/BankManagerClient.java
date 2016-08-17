package inwaiders.redn.skillsengine.bank;

import inwaiders.redn.rpg.base.ManagerBase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;

public class BankManagerClient extends ManagerBase<PlayerSkillBankClient>
{
	public static final BankManagerClient instance = new BankManagerClient();

	@Override
	protected PlayerSkillBankClient getDefaultValue(EntityPlayer key)
	{
		return new PlayerSkillBankClient(key);
	}
}
