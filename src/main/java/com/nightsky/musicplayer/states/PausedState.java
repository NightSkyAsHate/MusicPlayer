package com.nightsky.musicplayer.states;

import com.nightsky.musicplayer.tools.MusicPlayer;

public class PausedState extends State{

    public PausedState(MusicPlayer musicPlayer) {
        super(musicPlayer);
    }

    public void play() {
        Thread thread = new Thread(musicPlayer.getMusic());
        thread.start();
        musicPlayer.setPlayThread(thread);
        musicPlayer.setState(new PlayingState(musicPlayer));
    }

    public void pause() {

    }

    public void lock() {
        musicPlayer.setState(new LockedState(musicPlayer, this));
    }

    public void reset() {
        musicPlayer.getMusic().getAdvancedPlayer().stop();
        musicPlayer.setState(new ReadyState(musicPlayer));
        musicPlayer.setFrame(0);
    }
}
