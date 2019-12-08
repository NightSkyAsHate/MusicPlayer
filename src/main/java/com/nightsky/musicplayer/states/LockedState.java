package com.nightsky.musicplayer.states;

import com.nightsky.musicplayer.tools.MusicPlayer;

public class LockedState extends State{
    private State previos;

    public LockedState(MusicPlayer musicPlayer, State state) {
        super(musicPlayer);
        this.previos = state;
    }

    public void play() {

    }

    public void pause() {

    }

    public void lock() {
        musicPlayer.setState(previos);
    }

    public void reset() {

    }
}
