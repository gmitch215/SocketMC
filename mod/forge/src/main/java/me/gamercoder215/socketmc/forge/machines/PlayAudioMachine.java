package me.gamercoder215.socketmc.forge.machines;

import me.gamercoder215.socketmc.instruction.Instruction;
import me.gamercoder215.socketmc.instruction.InstructionId;
import me.gamercoder215.socketmc.instruction.Machine;
import org.jetbrains.annotations.NotNull;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;

@InstructionId(Instruction.PLAY_AUDIO)
public final class PlayAudioMachine implements Machine {

    @Override
    public void onInstruction(@NotNull Instruction instruction) throws Exception {
        byte[] data = instruction.parameter(0, byte[].class);

        ByteArrayInputStream in = new ByteArrayInputStream(data);
        AudioInputStream audio = AudioSystem.getAudioInputStream(in);
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audio.getFormat());

        Clip clip = (Clip) AudioSystem.getLine(info);
        clip.addLineListener(event -> {});
        clip.open(audio);
        clip.start();

        clip.close();
        audio.close();
    }

}
