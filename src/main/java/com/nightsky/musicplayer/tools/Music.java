package com.nightsky.musicplayer.tools;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Music implements Runnable{
    AdvancedPlayer advancedPlayer;
    MusicPlayer musicPlayer;
    File song;

    public Music(File song, final MusicPlayer musicPlayer) {
        this.musicPlayer = musicPlayer;
        this.song = song;
    }

    public void run() {
        try{
            FileInputStream fis = new FileInputStream(song);
            advancedPlayer = new AdvancedPlayer(fis);
            advancedPlayer.setPlayBackListener(new PlaybackListener() {
                @Override
                public void playbackFinished(PlaybackEvent playbackEvent) {
                    playbackEvent.setSource(advancedPlayer);
                    System.out.println("pause " + playbackEvent.getFrame());
                    musicPlayer.setFrame(playbackEvent.getFrame() / 27);
                    super.playbackFinished(playbackEvent);
                }
            });
            int frame = musicPlayer.getFrame();
            if (frame == 0){
                advancedPlayer.play(frame, Integer.MAX_VALUE);
            } else {
                System.out.println("music " + frame);
                advancedPlayer.play(frame, Integer.MAX_VALUE);
            }
        } catch (JavaLayerException e){
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Music{" +
                "advancedPlayer=" + advancedPlayer +
                ", musicPlayer=" + musicPlayer +
                '}';
    }

    public AdvancedPlayer getAdvancedPlayer() {
        return advancedPlayer;
    }

    public void setAdvancedPlayer(AdvancedPlayer advancedPlayer) {
        this.advancedPlayer = advancedPlayer;
    }
}
