package org.untitled.immolation.client.commands;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import org.untitled.immolation.client.gui.Drawing;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;

public class ImportPaintingCommand {
    //TODO: FIX import painting opening screen with new drawstack.
    // currently does not setScreen properly
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

        client.execute(() -> {
            try {
                Drawing drawing = new Drawing(Text.literal(file.getName()));
                client.setScreen(drawing);
                drawing.importFrom(reader); // safe: reader is still open

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
