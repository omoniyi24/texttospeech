package com.texttospeech.project;


import com.texttospeech.project.AudioPlayer;
import marytts.LocalMaryInterface;
import marytts.MaryInterface;
import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;
import marytts.modules.synthesis.Voice;
import marytts.signalproc.effects.AudioEffect;
import marytts.signalproc.effects.AudioEffects;

import javax.sound.sampled.AudioInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


public class TextToSpeechService {

    private AudioPlayer tts;
    private MaryInterface marytts;

    public  TextToSpeechService(){

        try{
            marytts = new LocalMaryInterface();
        }catch(MaryConfigurationException maryConfigurationException){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, maryConfigurationException);
        }
    }

    public Collection<Voice> getAvailableVoices(){
        return Voice.getAvailableVoices();
    }

    public void setVoices(String voice){
        marytts.setVoice(voice);
    }

    public void speak(String text, float gainValue, boolean daemon, boolean join){
        // Stop the previous player
        stopSpeaking();

        try (AudioInputStream audio = marytts.generateAudio(text)) {

            // Player is a thread(threads can only run one time) so it can be
            // used has to be initiated every time
            tts = new AudioPlayer();
            tts.setAudio(audio);
            tts.setGain(gainValue);
            tts.setDaemon(daemon);
            tts.start();
            if (join)
                tts.join();

        } catch (SynthesisException ex) {
            Logger.getLogger(getClass().getName()).log(Level.WARNING, "Error saying phrase.", ex);
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.WARNING, "IO Exception", ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(getClass().getName()).log(Level.WARNING, "Interrupted ", ex);
            tts.interrupt();
        }
    }

    public void stopSpeaking() {
        // Stop the previous player
        if (tts != null)
            tts.cancel();
    }

    public List<AudioEffect> getAudioEffects() {
        return StreamSupport.stream(AudioEffects.getEffects().spliterator(), false).collect(Collectors.toList());
    }

    public MaryInterface getMarytts() {
        return marytts;
    }



}
