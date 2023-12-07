package pokemon.util;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioHandler {
    public AudioHandler() {}

    public AudioInputStream getAudio(String fileName) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        String FOLDER_PATH = "src" + File.separator + "audio";
        String FILE_PATH = FOLDER_PATH + File.separator + fileName;
        File AUDIO_FILE = new File(FILE_PATH);

        return AudioSystem.getAudioInputStream(AUDIO_FILE);
    }
}
