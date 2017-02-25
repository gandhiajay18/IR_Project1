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
public class apple {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) throws TwitterException, FileNotFoundException, UnsupportedEncodingException, IOException {
    FileOutputStream fos = null;
    OutputStreamWriter osw = null;
    BufferedWriter bw = null;
ConfigurationBuilder cb = new ConfigurationBuilder();
    cb.setDebugEnabled(true);
    cb.setOAuthConsumerKey("q1tkwnKyybnvOCwqvnDbKbggr");
    cb.setOAuthConsumerSecret("pWi3PCTxEz1P1YKoGBdyeygpnrqajYMNF7QFHbfcxZmVbkkZiQ");
    cb.setOAuthAccessToken("2384903437-dVoMkaUkrZYwd8xn6aZupotwZ35qgv6LUl6Rh0s");
    cb.setOAuthAccessTokenSecret("wvkNsjFfKHyJSpmvcqtMCZN3cR77kJl3BEH3MH4i9x6Cn");
    cb.setJSONStoreEnabled(true);
    
    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
    File file= new File("Apple"+timeStamp+".json");
    
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
    String keywords[] = {"#iphone","#apple","#Iphone","#ios","#iphone7","#i7","#Ios","#ipad","#appleevent","#iphone7plus","#jetblack","#iphonejetblack","#iphoneblack","#airpod","#airpods","#iwatch","#iwatch2","#iwatchseries2","#Applewatch","#applewatchseries2","#newiwatch","#ios10","#waterresistanti7","#i7waterresistant","#i7dustresistant","#A10fusionchip","#iphoneA10","#iphonefusionchip","iphone","apple","Iphone","iPhone","ios","Ios","ios10","Ios10","ipad","Ipad","Apple event","Apple september event","Air pods","new Ipad","iwatch 2","iwatch 2 series","#iphone7launch","#iphone7specs"
};
    String lang[] = {"es","en","tr","ko"};
    fq.track(keywords);
    fq.language(lang);
    twitterStream.addListener(listener);
    twitterStream.filter(fq);
}
}