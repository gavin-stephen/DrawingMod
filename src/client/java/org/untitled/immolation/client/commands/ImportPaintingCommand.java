package org.untitled.immolation.client.commands;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.navigation.GuiNavigation;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.text.Text;
import org.untitled.immolation.client.gui.Drawing;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;

public class ImportPaintingCommand {
    public void importPainting(int argument) throws IOException {
        File gameDir = MinecraftClient.getInstance().runDirectory;
        File exportDir = new File(gameDir, "exports");
        System.out.println("export dir : " + exportDir.getAbsolutePath());
        for (File file : Objects.requireNonNull(exportDir.listFiles())) {
            if (file.getName().equals("export" + argument + ".txt") ) {
                System.out.println(file.getName() + " is in the folder");

                loadPainting(file);

            }
            System.out.println(file.getName());
        }
    }


    private void loadPainting(File file) throws IOException {
        MinecraftClient client = MinecraftClient.getInstance();

        // Create a Reader BEFORE entering client.execute
        Reader reader = Files.newBufferedReader(file.toPath());


        //10 tick delay (around 0.5s)
        final int[] tickDelay = {10};

        //super ugly fix, may be worth looking into other ways to wait for currentScreen not instanceof ChatScreen
        ClientTickEvents.END_CLIENT_TICK.register(curClient -> {
            if (tickDelay[0] > 0) {
                tickDelay[0]--;
                if (tickDelay[0] == 0) {
                    try {
                        Drawing drawing = new Drawing(Text.literal(file.getName()));
                        //insanely dumb solution

                        System.out.println("pre drawimport");
                        drawing.importFrom(reader); // safe: reader is still open
                        client.setScreen(drawing);
                        System.out.println("does it get here");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

}
