
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public final class SoundBasis{
    
    private Clip clip;
    
   public SoundBasis(){clip=null;}
   
   public SoundBasis(String songToPlay){
       this();
       setClip(songToPlay);
   }
   
   public void play(String songToPlay)
   {
       setClip(songToPlay);
       play();
   }
   
   public void setClip(String songToPlay)
   {
       try {
         // Open an audio input stream.
         URL url = getClass().getClassLoader().getResource("resources/sound/"+songToPlay+".wav");
         AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
         // Get a sound clip resource.
         clip = AudioSystem.getClip();
         // Open audio clip and load samples from the audio input stream.
         clip.open(audioIn);
      } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | NullPointerException e) {
          System.out.println("EXCEPTION INITIALIZING A CLIP: "+e.getMessage());
      }
   }
   
   public void play()
   {
       try{
           clip.start();
       }
       catch(NullPointerException e)
       {
           System.out.println("ERROR: CLIP IS NOT INITIALIZED");
       }
   }
   
   //This method doesn't save the clip that's being played. Useful for short-term plays where we do not want to keep track of the clip.
   public void reproduce(String name)
   {
       try {
         // Open an audio input stream.
         URL url = getClass().getClassLoader().getResource("resources/sound/"+name+".wav");
         AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
         // Create a sound clip resource.
         Clip song;
         song = AudioSystem.getClip();
         // Open audio clip and load samples from the audio input stream.
         song.open(audioIn);
         song.start();
      
      } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | NullPointerException e) {
          System.out.println("EXCEPTION REPRODUCING A CLIP: "+e.getMessage());
      }
   }
   
   public Clip getClip (){return clip;}
}
