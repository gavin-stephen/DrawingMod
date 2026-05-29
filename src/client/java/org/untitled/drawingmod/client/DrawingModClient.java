package org.untitled.drawingmod.client;

import net.fabricmc.api.ClientModInitializer;
import org.untitled.drawingmod.client.keybinds.KeyBindManager;
import org.untitled.drawingmod.client.commands.ModCommandManager;

public class DrawingModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        KeyBindManager.register(); //register and wait for key binds
        ModCommandManager.register();

        System.out.println("test");
    }
}
