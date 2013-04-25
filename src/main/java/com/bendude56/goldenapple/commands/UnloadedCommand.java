package com.bendude56.goldenapple.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.bendude56.goldenapple.GoldenApple;
import com.bendude56.goldenapple.User;
import com.bendude56.goldenapple.permissions.PermissionManager;

public class UnloadedCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		User user = User.getUser(sender);
		
		if (user.getHandle().isOp() || user.hasPermission(PermissionManager.moduleQueryPermission))
			user.sendLocalizedMessage("shared.cmdUnload.specific", GoldenApple.getInstance().getCommandManager().getCommand(command.getName()).module);
		else
			user.sendLocalizedMessage("shared.cmdUnload.generic");
		
		return true;
	}
}
