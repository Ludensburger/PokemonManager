package pokemon.util;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioHandler {
    Clip clip;
    AudioInputStream ais;
    public AudioHandler(String fileName) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        setAis(getAudio(fileName));
        setClip(AudioSystem.getClip());
    }

    public AudioInputStream getAudio(String fileName) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        String FOLDER_PATH = "src" + File.separator + "audio";
        String FILE_PATH = FOLDER_PATH + File.separator + fileName;
        File AUDIO_FILE = new File(FILE_PATH);

        return AudioSystem.getAudioInputStream(AUDIO_FILE);
    }

    public Clip getClip() {
        return clip;
    }

    public void setClip(Clip clip) {
        this.clip = clip;
    }

    public AudioInputStream getAis() {
        return ais;
    }

    public void setAis(AudioInputStream ais) {
        this.ais = ais;
    }

    public void play(int loopCount) throws LineUnavailableException, IOException {
        //  Retrieves music file
        Clip clip = getClip();
        if(!clip.isOpen()) {
            clip.open(getAis());
        }
        clip.loop(loopCount);
        clip.start();
    }

    public void pause() {
        getClip().stop();
    }
}
