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
public class turkish {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) throws TwitterException, FileNotFoundException, UnsupportedEncodingException, IOException {
    FileOutputStream fos = null;
    OutputStreamWriter osw = null;
    BufferedWriter bw = null;
ConfigurationBuilder cb = new ConfigurationBuilder();
    cb.setDebugEnabled(true);
   cb.setOAuthConsumerKey("O2BtMMfQeTMl1OfAOawaNt1YT");
    cb.setOAuthConsumerSecret("j1oAnxzSyo3RbYa2boAviXkk4Hb9fds0rPv7Gg3VhMyFBWzjO4");
    cb.setOAuthAccessToken("2384903437-KqURbsY3aQdDzRokeVxKFswo8czk5pRCLFVX4XK");
    cb.setOAuthAccessTokenSecret("UBPK8gPTPg3vuJgWYu76D5Z2ktLDu7ZgM7uqXrYYPGooH");
    cb.setJSONStoreEnabled(true);
    
    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
    File file= new File("TURKISH"+timeStamp+".json");
    
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
    String keywords[] = {"#syrianwar","#syriancivilwar","#prayforsyria","#syria","#syriaceasefire","#syriaschildren","#prayforsyrianchildren","#stopsyrianwar","#stopsyriancivilwar","#speakup4syriaschildren","#syrianarmy","#syriacrisis","#syrianarabarmy","#takfiri","#syrianrevolution","#syrianregime","#syriarussiawar","#syrianrefugees","syrian war","syrian civil war","save syria","stop syrian war","save syrian children","save children of syria","syrian children","syrian soldiers","syrian army","iran syria relations","SAA","#SyrianArabArmy","Syrian refugees","syrian arab army","syrian revolution","syrian rebels","hama","syrian crisis","#syriancrisis","#allepo","winds of winter","a song of ice and fire","winter is coming","game of thrones season 7","got season 7","game of thrones","george martin","george r r martin","R.R.Martin","George R.R.Martin","Lannister","Stark","Jon Snow","peter dinklage","kit harrington","tyrian lannister","tyrian","lannister","#starks","#aryastark","#cerseilannister","#sansastark","#gotseason7","#gotseason8","#gameofthronesseason7","#gameofthronesseason8","#starksfamily","#gotnewseason","#winteriscoming","#hodor","#gameofthronespost","#gameofthrones","#hbothe","#iainglen","#jorahmormont","#daenerys","#daenerystargaryen","#khaleesi","#gotmemes","#gameofthronesmemes","#gameofthronesfamily","#gameofthroneshbo","#got","#catelynstark","#stark","#tully","#asoiaf","#gameofthronesfacts","#tyrionlannister","#peterdinklage","#georgerrmartin","#alannisteralwayspayshisdebts","#jonsnow","#windsofwinter","#gotnewbook","#gotreleasedate","#gotbookpublishdate","#gotbooks","#gameofthronesbooks","#jaimelannister","#targaryen","#baratheon","tyrell","#martell","#lannister","#arryn","#greyjoy","#asongoficeandfire","#westeros","us open","us open finals","us open mens final","us open womens final","us open winner","usopen","#usopen2016","#usopen2k16","#USOpen","#usopentennis","#usopen","#usopentennis","#usopenfinals","#usopensemis","#usopensemifinals","#usopenmens","#usopenwomens","#usopendoubles","#usopenmixeddoubles","#novakvsstan","#novakdjokovic","#stanwarinka","#usopenmensfinal","#usopenwomensfinal","#angeliquekerber","#AngeliqueKerber","#wawrinka","#djokovic","#novak","#angelique","#kerber","#Angelique","#Kerber","#Novak","#Djokovic","#usopenwinner","#usopenrunnersup","#excited4usopen","us presidential elections","us election 2016","us election","usa elections","trump for president","hilary for president","donald trump","hilary clinton","trump","clinton foundation","#clintonfoundation","#newUSelections","#US2016elections","#uselections","#USAElections","#USelection","#uselection","#hilary4president","#trump4president","#donaldtrump","#voteforUS","#USAelection","#hilaryclinton","#clintonforpresident","#clinton4president","#trumpforpresident","#hatetrump","#hatehilary","#election2016","#SaferThanATrumpRally","#NeverTrump","#MakeDonaldDrumpfAgain","#MakeAmericaGreatAgain","#USrepublican","#USdemocratic","#fuckhillary","#fucktrump","#dumptrump","#nevertrump","#drumpf","#neverhillary","#hillno","#trumptrain","#wakeupamerica","#votetrump","#votehilary","#donaldtrump2016","#iphone","#apple","#Iphone","#ios","#iphone7","#i7","#Ios","#ipad","#appleevent","#iphone7plus","#jetblack","#iphonejetblack","#iphoneblack","#airpod","#airpods","#iwatch","#iwatch2","#iwatchseries2","#Applewatch","#applewatchseries2","#newiwatch","#ios10","#waterresistanti7","#i7waterresistant","#i7dustresistant","#A10fusionchip","#iphoneA10","#iphonefusionchip","iphone","apple","Iphone","iPhone","ios","Ios","ios10","Ios10","ipad","Ipad","Apple event","Apple september event","Air pods","new Ipad","iwatch 2","iwatch 2 series","#iphone7launch","#iphone7specs"};
    String lang[] = {"tr"};
    fq.track(keywords);
    fq.language(lang);
    twitterStream.addListener(listener);
    twitterStream.filter(fq);
}
}
