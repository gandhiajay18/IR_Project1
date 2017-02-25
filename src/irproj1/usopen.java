/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package irproj1;
import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import twitter4j.*;
import twitter4j.FilterQuery;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author Ajay-Pc
 */
public class usopen {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) throws TwitterException, FileNotFoundException, UnsupportedEncodingException, IOException {
    FileOutputStream fos = null;
    OutputStreamWriter osw = null;
    BufferedWriter bw = null;
ConfigurationBuilder cb = new ConfigurationBuilder();
    cb.setDebugEnabled(true);
    cb.setOAuthConsumerKey("IfyYgEOyYZvUH2VclbHjK7az4");
    cb.setOAuthConsumerSecret("YBuKOIngpkVsFexRGd55GW9dMrX4pnklp46kln4XUYCJnGz1Uk");
    cb.setOAuthAccessToken("775091537973694464-9Wyl0cmPN3lQkx9gguBBn1453ZCO9O4");
    cb.setOAuthAccessTokenSecret("W0gD3Xk5hL2lQTvKKu2ijWvBMymkZitpmd7JwW6NiTv93");
    cb.setJSONStoreEnabled(true);
    
    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
    File file= new File("US open"+timeStamp+".json");
    
    if(!file.exists())
    {
        file.createNewFile();
    }
    final FileWriter filewriter = new FileWriter(file.getName(),true);
    final BufferedWriter bufferedwriter = new BufferedWriter(filewriter);
     
    TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
     
    StatusListener listener = new StatusListener() {
     int ctr=0;
            
        public void onStatus(Status status) {
            
            Boolean RT = status.isRetweet();
            if(RT == false)
            {      String json = TwitterObjectFactory.getRawJSON(status);
            try {
                ctr++;
                System.out.println(ctr);
                bufferedwriter.write(json);
                bufferedwriter.newLine();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(IRProj1.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }

        public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
            System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
        }

        public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
            System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
        }

        public void onScrubGeo(long userId, long upToStatusId) {
            System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
        }

        public void onException(Exception ex) {
            ex.printStackTrace();
        }

        @Override
        public void onStallWarning(StallWarning sw) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };

    FilterQuery fq = new FilterQuery();
    String keywords[] = {"us open","us open finals","us open mens final","us open womens final","us open winner","usopen","#usopen2016","#usopen2k16","#USOpen","#usopentennis","#usopen","#usopentennis","#usopenfinals","#usopensemis","#usopensemifinals","#usopenmens","#usopenwomens","#usopendoubles","#usopenmixeddoubles","#novakvsstan","#novakdjokovic","#stanwarinka","#usopenmensfinal","#usopenwomensfinal","#angeliquekerber","#AngeliqueKerber","#wawrinka","#djokovic","#novak","#angelique","#kerber","#Angelique","#Kerber","#Novak","#Djokovic","#usopenwinner","#usopenrunnersup","#excited4usopen"};
    String lang[] = {"es","en","tr","ko"};
    fq.track(keywords);
    fq.language(lang);
    twitterStream.addListener(listener);
    twitterStream.filter(fq);
}
}