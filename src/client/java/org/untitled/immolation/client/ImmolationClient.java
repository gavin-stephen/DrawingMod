package org.untitled.immolation.client;

import net.fabricmc.api.ClientModInitializer;
import org.untitled.immolation.client.keybinds.KeyBindManager;
import org.untitled.immolation.client.keybinds.ModCommandManager;

public class ImmolationClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        KeyBindManager.register(); //register and wait for key binds
        ModCommandManager.register();

        System.out.println("test");
    }
}
