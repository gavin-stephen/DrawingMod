package org.untitled.immolation.client.keybinds;

import com.mojang.brigadier.Command;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class ModCommandManager {
    public static void register() {

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(ClientCommandManager.literal("clienttater").executes(context -> {
                context.getSource().sendFeedback(Text.literal("Called /clienttater with no arguments."));
                return 1;
            }));
        });
        //need to figure out how to use command arguments / make a UI for paintings to import
    }

    private static Command<ServerCommandSource> importPainting() {
        return null;
    }

}
