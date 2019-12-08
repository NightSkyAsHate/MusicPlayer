package com.nightsky.musicplayer.tools;

/*
    Есть кнопки:
        *Играть
        *Пауза
        *Сбросить
        *Заблокировать
 */

import com.nightsky.musicplayer.states.ReadyState;
import com.nightsky.musicplayer.states.State;
import javazoom.jl.decoder.JavaLayerException;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class MusicPlayer {
    private State state;
    private Thread playThread;
    private File song;
    private int frame;
    private Music music;

    public MusicPlayer() {
        this.state = new ReadyState(this);
    }

    public void setState(State state){
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public Thread getPlayThread() {
        return playThread;
    }

    public void setPlayThread(Thread playThread) {
        this.playThread = playThread;
    }

    public File getSong() {
        return song;
    }

    public void setSong(File song) throws IOException, JavaLayerException, UnsupportedAudioFileException {
        this.song = song;
        this.music = new Music(song, this);
    }

    public int getFrame() {
        return frame;
    }

    public void setFrame(int frame) {
        this.frame = frame;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }
}
