package com.nightsky.musicplayer.states;

import com.nightsky.musicplayer.tools.MusicPlayer;
import javazoom.jl.decoder.JavaLayerException;

import java.io.FileNotFoundException;

public abstract class State {
    protected MusicPlayer musicPlayer;

    public State(MusicPlayer musicPlayer) {
        this.musicPlayer = musicPlayer;
    }

    public MusicPlayer getMusicPlayer() {
        return musicPlayer;
    }

    public void setMusicPlayer(MusicPlayer musicPlayer) {
        this.musicPlayer = musicPlayer;
    }

    public abstract void play() throws FileNotFoundException, JavaLayerException;
    public abstract void pause() throws InterruptedException;
    public abstract void lock();
    public abstract void reset();
}
