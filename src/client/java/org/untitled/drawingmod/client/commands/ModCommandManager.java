package org.untitled.drawingmod.client.commands;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;

import java.io.IOException;

import static net.minecraft.text.Text.literal;

public class ModCommandManager {
    public static void register() {
        ClientCommandRegistrationCallback.EVENT.register(
                (dispatcher, registryAccess) -> dispatcher.register(
                        ClientCommandManager.literal("import")
                                .then(ClientCommandManager.argument(
                                        "paintingNum",
                                        IntegerArgumentType.integer()
                                ).executes(ctx -> {
                                    FabricClientCommandSource src = ctx.getSource();
                                    int argument = IntegerArgumentType.getInteger(ctx, "paintingNum");

                                    src.sendFeedback(Text.literal(
                                            "used arg : " + argument
                                    ));
                                    try {
                                        new ImportPaintingCommand().importPainting(argument);
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                    return argument;
                                }))
                )
        );
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(ClientCommandManager.literal("clienttater").executes(context -> {
                context.getSource().sendFeedback(literal("Called /clienttater with no arguments."));
                return 1;
            }));
        });
        //need to figure out how to use command arguments / make a UI for paintings to import

    }


}
