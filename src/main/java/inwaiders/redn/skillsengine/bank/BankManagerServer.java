package inwaiders.redn.skillsengine.bank;

import inwaiders.redn.rpg.base.ManagerBase;
import net.minecraft.entity.player.EntityPlayer;

public class BankManagerServer extends ManagerBase<PlayerSkillBankServer> {
	public static final BankManagerServer instance = new BankManagerServer();
	@Override
	protected PlayerSkillBankServer getDefaultValue(EntityPlayer ep)
	{
		return new PlayerSkillBankServer(ep);
	}
}
