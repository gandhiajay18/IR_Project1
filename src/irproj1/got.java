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
public class got {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) throws TwitterException, FileNotFoundException, UnsupportedEncodingException, IOException {
    FileOutputStream fos = null;
    OutputStreamWriter osw = null;
    BufferedWriter bw = null;
ConfigurationBuilder cb = new ConfigurationBuilder();
    cb.setDebugEnabled(true);
    cb.setOAuthConsumerKey("QQRojTPEzROLh1XJ9VEM1EvdI");
    cb.setOAuthConsumerSecret("vlvmiJcpsZH2Y0DlcvIHgQ84QJBwpQ3QSLpzbSsb88eL8en3vU");
    cb.setOAuthAccessToken("2384903437-WcycAH0twaGdIQrhNZZ1r92ZXR5Hv4mjnYOxVif");
    cb.setOAuthAccessTokenSecret("2ziDjIdeE2jCniZbFGQAGLdGjFvvKSufiEjlVBKuirLsL");
    cb.setJSONStoreEnabled(true);
    
    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
    File file= new File("GOT"+timeStamp+".json");
    
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
    String keywords[] = {"winds of winter","a song of ice and fire","winter is coming","game of thrones season 7","got season 7","game of thrones","george martin","george r r martin","R.R.Martin","George R.R.Martin","Lannister","Stark","Jon Snow","peter dinklage","kit harrington","tyrian lannister","tyrian","lannister","#starks","#aryastark","#cerseilannister","#sansastark","#gotseason7","#gotseason8","#gameofthronesseason7","#gameofthronesseason8","#starksfamily","#gotnewseason","#winteriscoming","#hodor","#gameofthronespost","#gameofthrones","#hbothe","#iainglen","#jorahmormont","#daenerys","#daenerystargaryen","#khaleesi","#gotmemes","#gameofthronesmemes","#gameofthronesfamily","#gameofthroneshbo","#got","#catelynstark","#stark","#tully","#asoiaf","#gameofthronesfacts","#tyrionlannister","#peterdinklage","#georgerrmartin","#alannisteralwayspayshisdebts","#jonsnow","#windsofwinter","#gotnewbook","#gotreleasedate","#gotbookpublishdate","#gotbooks","#gameofthronesbooks","#jaimelannister","#targaryen","#baratheon","tyrell","#martell","#lannister","#arryn","#greyjoy","#asongoficeandfire","#westeros"};
 //   String lang[] = {"es","en","tr","ko"};
    fq.track(keywords);
   // fq.language(lang);
    twitterStream.addListener(listener);
    twitterStream.filter(fq);
}
}