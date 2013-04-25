package com.bendude56.goldenapple.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import com.bendude56.goldenapple.GoldenApple;
import com.bendude56.goldenapple.User;
import com.bendude56.goldenapple.warp.WarpManager;

public class TpCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		User user = User.getUser(sender);
		
		if (args.length == 0) {
			return false;
		} else if (args.length == 1) {
			User user2 = User.getUser(args[0]);
			if (!(user.getHandle() instanceof Player)) {
				user.sendLocalizedMessage("shared.noConsole");
			} else if (!user.hasPermission(WarpManager.tpSelfToOtherPermission)) {
				GoldenApple.logPermissionFail(user, commandLabel, args, true);
			} else if (user2 == null) {
				user.sendLocalizedMessage("shared.userNotFoundError", args[0]);
			} else if (user.getPlayerHandle().teleport(user2.getPlayerHandle(), TeleportCause.COMMAND)) {
			} else {
				user.sendLocalizedMessage("error.warps.pluginCancel");
			}
		} else {
			User user1 = User.getUser(args[0]);
			User user2 = User.getUser(args[1]);
			if (!user.hasPermission(WarpManager.tpOtherToOtherPermission)) {
				GoldenApple.logPermissionFail(user, commandLabel, args, true);
			} else if (user1 == null) {
				user.sendLocalizedMessage("shared.userNotFoundError", args[0]);
			} else if (user2 == null) {
				user.sendLocalizedMessage("shared.userNotFoundError", args[1]);
			} else if (user1.getPlayerHandle().teleport(user2.getPlayerHandle(), TeleportCause.COMMAND)) {
				user1.sendLocalizedMessage("general.warps.teleportBy", user.getName());
			} else {
				user.sendLocalizedMessage("error.warps.pluginCancel");
			}
		}
		
		return true;
	}
}