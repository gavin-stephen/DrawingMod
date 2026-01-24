package org.untitled.immolation.client.gui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.text.Text;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
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

    public static void exportAsPNG(List<Drawing.DrawCommand> drawStack, int canvasWidth, int canvasHeight) {
        //add functionality later
        System.out.println("CURRENT WORKING DIRECTORY IS" + System.getProperty("user.dir"));
        NativeImage img = new NativeImage(canvasWidth, canvasHeight, true);
        for (Drawing.DrawCommand cmd : drawStack) {
            //TODO:add overload to DrawCommand that pushes the pixel to the NativeImage to export.
            //fix "Caused by: java.lang.IllegalArgumentException: (166, 120) outside of image bounds (213, 120)"

            cmd.draw(img);
        }
        //create new png file and write to it using the NativeImage buffer
        File file = new File("export.png");
        try {
            img.writeTo(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        img.close();

        //https://github.com/TeamMidnightDust/PictureSign/blob/architectury-1.21.4/common/src/main/java/eu/midnightdust/picturesign/util/PictureDownloader.java

    }
}
