package com.nightsky.musicplayer.view;

import com.nightsky.musicplayer.tools.MusicPlayer;
import javazoom.jl.decoder.JavaLayerException;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main extends JFrame {
    private JLabel songName = new JLabel();
    private JButton play = new JButton("play");
    private JButton selectSongButton = new JButton("open");
    private JButton pause = new JButton("pause");
    private JButton lock = new JButton("lock");
    private JButton reset = new JButton("reset");
    private MusicPlayer musicPlayer = new MusicPlayer();
    private JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

    public Main(){
        super("NightSky");
        this.setBounds(100, 100, 250, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3,2,2,2));
        container.add(songName);
        selectSongButton.addActionListener(new OpenEventListener());
        container.add(selectSongButton);
        play.addActionListener(new PlayEventListener());
        container.add(play);
        pause.addActionListener(new PauseEventListener());
        container.add(pause);
        lock.addActionListener(new LockEventListener());
        container.add(lock);
        reset.addActionListener(new ResetEventListener());
        container.add(reset);
    }

    class PlayEventListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            try {
                musicPlayer.getState().play();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (JavaLayerException ex) {
                ex.printStackTrace();
            }
        }
    }
    class OpenEventListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            int r = fileChooser.showSaveDialog(null);
            if (r == JFileChooser.APPROVE_OPTION){
                try {
                    String path = fileChooser.getSelectedFile().getAbsolutePath();
                    File file = new File(path);
                    musicPlayer.setSong(file);
                    songName.setText(file.getName());
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (JavaLayerException ex) {
                    ex.printStackTrace();
                } catch (UnsupportedAudioFileException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    class PauseEventListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            try {
                musicPlayer.getState().pause();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    class LockEventListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            musicPlayer.getState().lock();
        }
    }
    class ResetEventListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            musicPlayer.getState().reset();
        }
    }

    public synchronized static void main(String[] args) throws UnsupportedAudioFileException, IOException, JavaLayerException, InterruptedException {
        Main main = new Main();
        main.setVisible(true);
    }
}
