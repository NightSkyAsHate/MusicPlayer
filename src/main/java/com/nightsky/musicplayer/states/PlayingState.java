package com.nightsky.musicplayer.states;

import com.nightsky.musicplayer.tools.MusicPlayer;

public class PlayingState extends State {
    public PlayingState(MusicPlayer musicPlayer) {
        super(musicPlayer);
    }

    public void play() {

    }

    public void pause(){
        musicPlayer.getMusic().getAdvancedPlayer().stop();
        musicPlayer.setState(new PausedState(musicPlayer));
    }

    public void lock() {
        musicPlayer.setState(new LockedState(musicPlayer, this));
    }

    public void reset() {
        musicPlayer.getMusic().getAdvancedPlayer().stop();
        musicPlayer.setFrame(0);
        musicPlayer.setState(new ReadyState(musicPlayer));
    }
}
