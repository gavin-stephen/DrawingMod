package org.untitled.immolation.client.gui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;

import javax.swing.*;

public class ExportPainting extends ClickableWidget {
    //add functionality to export current painting to png  / possibly .dat
    public ExportPainting(int x, int y, int width, int height, Text text) {
        super(x,y,width,height,text);

    }

    @Override
    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {

    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {

    }

    private void exportAsPNG() {
        //add functionality later
    }
}
