package org.untitled.drawingmod.client.gui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.util.List;

public class ExportPainting extends ClickableWidget {
    //add functionality to export current painting to png  / possibly .dat
    public ExportPainting(int x, int y, int width, int height, Text text) {
        super(x,y,width,height,text);

    }

    @Override
    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
//        int x = getX();
//        int y = getY();
//        int width = getWidth();
//        int height = getHeight();
        System.out.println("rendered ExportPainting");

    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {

    }

    /***
     * Converts the in game painting to a PNG file
     * @param drawStack
     * @param canvasWidth
     * @param canvasHeight
     */
    public static void exportAsPNG(List<Drawing.DrawCommand> drawStack, int[][] savedImage, int canvasWidth, int canvasHeight) {
        //add functionality later
        System.out.println("CURRENT WORKING DIRECTORY IS" + System.getProperty("user.dir"));
        NativeImage img = new NativeImage(canvasWidth, canvasHeight, true);
        for (Drawing.DrawCommand cmd : drawStack) {
            //TODO:add overload to DrawCommand that pushes the pixel to the NativeImage to export.
            //fix "Caused by: java.lang.IllegalArgumentException: (166, 120) outside of image bounds (213, 120)"

            cmd.draw(img);
        }


        //Folder to write to + able to open in chat
        File gameDir = MinecraftClient.getInstance().runDirectory;
        File folder = new File(gameDir, "exports");
        folder.mkdirs();


        File[] files = folder.listFiles();
        int count = (files == null) ? 0 : files.length /  2;

        //create new png file and write to it using the NativeImage buffer
        File file = new File(folder, "export" + count + ".png");
        File file2 = new File(folder, "export" + count + ".txt");
        try {
            //write the savedImage to export.txt, with each int being an index of the color in painting
            try (Writer writer = Files.newBufferedWriter(file2.toPath())){
                for (int  i= 0 ; i < savedImage.length; i++) {
                    for (int j = 0 ; j < savedImage[0].length; j++) {
                        writer.write(Integer.toString(savedImage[i][j]));

                        if (j != savedImage[i].length - 1) {
                            writer.write(' ');
                        }

                    }
                    writer.write("\n");
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            img.writeTo(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        img.close();


        openFolderChat(folder);

        //https://github.com/TeamMidnightDust/PictureSign/blob/architectury-1.21.4/common/src/main/java/eu/midnightdust/picturesign/util/PictureDownloader.java

    }

    /***
     * Sends a CLICKABLE message in chat that opens folder
     * @param folder
     */
    private static void openFolderChat( File folder) {
        //normal text then OPEN FOLDER is underlined + clickable
        Text message = Text.literal("Image exported to ")
                .append(
                        Text.literal("OPEN FOLDER")
                        .formatted(Formatting.GREEN, Formatting.UNDERLINE)
                        .styled(style -> style.withClickEvent(new ClickEvent(
                            ClickEvent.Action.OPEN_FILE,
                            folder.getAbsolutePath()
                        )).withHoverEvent(new HoverEvent(
                            HoverEvent.Action.SHOW_TEXT,
                            Text.literal(folder.getAbsolutePath())
                        )
                        ))
                );
        assert MinecraftClient.getInstance().player != null;
        MinecraftClient.getInstance().player.sendMessage(message, false);

    }
}
