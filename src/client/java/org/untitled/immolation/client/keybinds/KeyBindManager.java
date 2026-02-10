package org.untitled.immolation.client.keybinds;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import org.untitled.immolation.client.gui.Drawing;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class KeyBindManager {
    public static KeyBinding keyBinding1;
    public static KeyBinding keyBinding2;
    public static void register() {
        keyBinding1 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.immolation.openmenu",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_K,
                "category.immolation.test"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBinding1.wasPressed()) {
                System.out.println("key 1 was pressed");
                client.player.sendMessage(Text.literal("Key 1 was pressed! "), false);
                if (client.getInstance().currentScreen instanceof Drawing) {
                    client.setScreen(null);
                }
                client.setScreen(new Drawing(Text.empty()));

            }

        });

        keyBinding2 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.immolation.openimported",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_L,
                "category.immolation.test"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBinding2.wasPressed()) {
                System.out.println("key 2 was pressed");
                client.player.sendMessage(Text.literal("Key 2 was pressed! "), false);
                if (client.getInstance().currentScreen instanceof Drawing) {
                    client.setScreen(null);
                } else {
                    File gameDir = MinecraftClient.getInstance().runDirectory;
                    File folder = new File(gameDir, "exports");
                    File file = new File(folder, "export0.txt");
                    try {
                        Reader reader = Files.newBufferedReader(file.toPath());
                        Drawing drawing = new Drawing(Text.literal(file.getName()));
                        drawing.importFrom(reader);
                        client.setScreen(drawing);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }


                //client.setScreen(new Drawing(Text.empty()));

            }

        });

    }
}
