/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package irproj1;

/**
 *
 * @author Siddharth1
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import org.json.simple.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.regex.*;



public class Parser {

    public static void main(String[] args) throws IOException {

  JSONParser parser = new JSONParser();
  String nu = null;

  String[] politics = {"us presidential elections","us election 2016","us election","usa elections","trump for president","hilary for president","donald trump","hilary clinton","trump","clinton foundation","#clintonfoundation","#newUSelections","#US2016elections","#uselections","#USAElections","#USelection","#uselection","#hilary4president","#trump4president","#donaldtrump","#voteforUS","#USAelection","#hilaryclinton","#clintonforpresident","#clinton4president","#trumpforpresident","#hatetrump","#hatehilary","#election2016","#SaferThanATrumpRally","#NeverTrump","#MakeDonaldDrumpfAgain","#MakeAmericaGreatAgain","#USrepublican","#USdemocratic","#fuckhillary","#fucktrump","#dumptrump","#nevertrump","#drumpf","#neverhillary","#hillno","#trumptrain","#wakeupamerica","#votetrump","#votehilary","#donaldtrump2016"};

  String[] entert = {"winds of winter","a song of ice and fire","winter is coming","game of thrones season 7","got season 7","game of thrones","got","george martin","george r r martin","R.R.Martin","George R.R.Martin","Lannister","Stark","Jon Snow","peter dinklage","kit harrington","tyrian lannister","tyrian","lannister","#starks",
"#aryastark","#cerseilannister","#sansastark","#gotseason7","#gotseason8","#gameofthronesseason7","#gameofthronesseason8","#starksfamily","#govtnewseason","#winteriscoming","#hodor","#gameofthronespost","#gameofthrones","#hbothe","#iainglen","#jorahmormont","#daenerys","#daenerystargaryen","#khaleesi","#gotmemes","#gameofthronesmemes","#gameofthronesfamily","#gameofthroneshbo","#got","#catelynstark","#stark","#tully","#asoiaf","#gameofthronesfacts","#tyrionlannister","#peterdinklage","#georgerrmartin","#alannisteralwayspayshisdebts","#jonsnow","#windsofwinter","#gotnewbook","#gotreleasedate","#gotbookpublishdate","#gotbooks","#gameofthronesbooks","#jaimelannister","#targaryen","#baratheon","tyrell","#martell","#lannister","#arryn","#greyjoy","#asongoficeandfire","#westeros"};

  String[] world = {"#syrianwar","#syriancivilwar","#prayforsyria","#syria","#syriaceasefire","#syriaschildren","#prayforsyrianchildren","#stopsyrianwar","#stopsyriancivilwar","#speakup4syriaschildren","#syrianarmy","#syriacrisis","#syrianarabarmy","#takfiri","#syrianrevolution","#syrianregime","#syriarussiawar","#syrianrefugees"
};

  String[] tech = { "#iphone","#apple","#Iphone","#ios","#iphone7","#i7","#Ios","#ipad","#appleevent","#iphone7plus","#jetblack","#iphonejetblack","#iphoneblack","#airpod","#airpods","#iwatch","#iwatch2","#iwatchseries2","#Applewatch","#applewatchseries2","#newiwatch","#ios10","#waterresistanti7","#i7waterresistant","#i7dustresistant","#A10fusionchip","#iphoneA10","#iphonefusionchip","iphone","apple","Iphone","iPhone","ios","Ios","ios10","Ios10","ipad","Ipad","Apple event","Apple september event","Air pods","new Ipad ","iwatch 2","iwatch 2 series","#iphone7launch","#iphone7specs"};

  String[] sports = {"us open","us open finals","us open mens final","us open womens final","us open winner","usopen","#usopen2016","#usopen2k16","#USOpen","#usopentennis","#usopen","#usopentennis","#usopenfinals","#usopensemis","#usopensemifinals","#usopenmens","#usopenwomens","#usopendoubles","#usopenmixeddoubles","#novakvsstan","#novakdjokovic","#stanwarinka","#usopenmensfinal","#usopenwomensfinal","#angeliquekerber","#AngeliqueKerber","#wawrinka","#djokovic","#novak","#angelique","#kerber","#Angelique","#Kerber","#Novak","#Djokovic","#usopenwinner","#usopenrunnersup","#excited4usopen"};

  boolean pol = false,ent = false,news = false,tec = false,spo = false, top = false;
  String topic = null;
  String emot = "[:;xX]-?[DP()]";
  String htag = "#[A-Za-z0-9]+";
  String ment = "@[A-Za-z0-9]+";
  String url = "https?://\\S+\\s?";
 // String emoj = "[\\u20a0-\\u32ff\\ud83c\\udc00-\\ud83d\\udeff\\udbb9\\udce5-\\udbb9\\udcee]";
  String emoj = "u'('u'\ud83c[\udf00-\udfff]|'u'\ud83d[\udc00-\ude4f\ude80-\udeff]|'u'[\u2600-\u26FF\u2700-\u27BF]+')";
  for (int i = 51;i<=52;i++)   
  try{
         String oldjson = i + ".json";
             Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;


    		File file2 = new File("New" + oldjson + ".json");
                if(!file2.exists()){
    			file2.createNewFile();
    		}
                final FileWriter fileWritter2 = new FileWriter(file2.getName(),true);
    	        final BufferedWriter bufferWritter2 = new BufferedWriter(fileWritter2);
    BufferedReader br = new BufferedReader(new FileReader(oldjson));
    String strLine = null;
    String co1 = null;
    //Read File Line By Line
    while ((strLine = br.readLine()) != null)   {


boolean hasco = false;
      // Print the content on the console
        JSONObject jsonObject = (JSONObject) parser.parse(strLine);
        JSONObject coord = null;
    //  System.out.println (strLine);
//JSONObject jsonObject = (JSONObject) strLine;
    String text = (String)jsonObject.get("text");
   String lang = (String)jsonObject.get("lang");
   String time = (String)jsonObject.get("timestamp_ms");
   if (jsonObject.containsKey("coordinates")){
   coord = (JSONObject)jsonObject.get("coordinates");
   hasco = (coord == null);
  // hasco = coord.containsKey("coordinates");
   if (hasco == false){
   JSONArray cord = (JSONArray) coord.get("coordinates");
   co1 = cord.toString();
    }}
top = false;
topic = null;
////Check for Topic and match them to hashtags,keywords
     for (String d: politics)
        {

        if (text.contains(d))
        {
            //System.out.println("Topic Apple");
           top = true;
          topic = "Politics";
            break;
        }
        }
        if (!top == true)
        {
            for (String d: entert)
        {
        if (text.toLowerCase().contains(d.toLowerCase()))
        {
            top = true;
            topic = "Entertainment";
            break;
        }
        }
        }
        if (!top == true)
            {
            for (String d: world)
        {
        if (text.toLowerCase().contains(d.toLowerCase()))
        {
            top = true;
            topic = "World News";
            break;
        }
        }
        }

      if (!top == true)
            {
            for (String d: tech)
        {
        if (text.toLowerCase().contains(d.toLowerCase()))
        {
            top = true;
            topic = "Tech";
            break;
        }
        }
        }
      if (!top == true)
            {
            for (String d: sports)
        {
        if (text.toLowerCase().contains(d.toLowerCase()))
        {
            top = true;
            topic = "Sports";
            break;
        }
        }
        }
///End Topic Identify

//Remove hashtags, keywords, urls and emoticons from tweet text

 String tweet_text = text;

//Store Emoticons and remove from text
 boolean emo1 = false;
 Pattern reg = Pattern.compile(emot);
Matcher rm = reg.matcher(text);
List <String> emoticons = new ArrayList<String>();
while (rm.find()){
       emoticons.add(rm.group());
       emo1 = true;
        }
        //System.out.println(emo.size());
        //for (int i = 0; i< emo.size(); i++)
         //   System.out.println(emo.get(i) + "\b");
 text = text.replaceAll(emot, "");              // replace all emoticons with space


 //Store Emojis and remove from text
 boolean emo2 = false;
 Pattern regem = Pattern.compile(emoj);
Matcher rmem = regem.matcher(text);
List <String> emojis = new ArrayList<String>();
while (rmem.find()){
       emojis.add(rmem.group());
       emo2 = true;
        }
        //System.out.println(emo.size());
        //for (int i = 0; i< emo.size(); i++)
         //   System.out.println(emo.get(i) + "\b");
 text = text.replaceAll(emoj, "");              // replace all emojis with space






 //Store hashtags and remove them from text
  boolean hash = false;
 Pattern reg1 = Pattern.compile(htag);
Matcher rm1 = reg1.matcher(text);
List <String> hashtags = new ArrayList<String>();

while (rm1.find()){
       hashtags.add(rm1.group());
       hash = true;
        }
        //System.out.println(emo.size());
        //for (int i = 0; i< emo.size(); i++)
         //   System.out.println(emo.get(i) + "\b");
 text = text.replaceAll(htag, "");              // replace all hashtags with space

  //Store mentions and remove them from text
  boolean men1 = false;
 Pattern reg2 = Pattern.compile(ment);
Matcher rm2 = reg2.matcher(text);
List <String> mentions = new ArrayList<String>();

while (rm2.find()){
       mentions.add(rm2.group());
       men1 = true;
        }
        //System.out.println(emo.size());
        //for (int i = 0; i< emo.size(); i++)
         //   System.out.println(emo.get(i) + "\b");
 text = text.replaceAll(ment, "");              // replace all mentions with space


   //Store URLs and remove them from text
  boolean url1 = false;
 Pattern reg3 = Pattern.compile(url);
Matcher rm3 = reg3.matcher(text);
List <String> urls = new ArrayList<String>();

while (rm3.find()){
       urls.add(rm3.group());
        url1 = true;
        }
        //System.out.println(emo.size());
        //for (int i = 0; i< emo.size(); i++)
         //   System.out.println(emo.get(i) + "\b");
 text = text.replaceAll(url, " ");              // replace all URLs with space

text = text.trim().replaceAll(" +", " ");      //replace 2 or more space characters with just one space
text = text.trim().replaceAll(",", "");        // remove comma

String text_en = null, text_es = null, text_tr = null, text_ko = null, text_xx = null;
Boolean en = false, es = false, tr = false, ko = false, xx = false,is = false, tl = false;

if (!lang.isEmpty() == true)
{
    if (lang.equals("en")) {
text_en = text;
en = true;
        }
    else if(lang.equals("es")) {
text_es = text;
es = true;
        }
    else if(lang.equals("tr")) {
text_tr = text;
tr = true;
        }
    else if(lang.equals("ko")) {
text_ko = text;
ko = true;
        }
    else{
        text_xx = text;
        xx = true;
    }}

// Get timestamp and convert to nearest hour in GMT

        long t = Long.parseLong(time);
        Date d = new Date(t);

        SimpleDateFormat sdf = new SimpleDateFormat("EEE yyyy-MM-dd HH:mm:ss z");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        //Date d = sdf.parse(time);
        String formattedDate = sdf.format(d);
       // System.out.println(formattedDate);

        Calendar c = new GregorianCalendar();
        c.setTime(d);
        if (c.get(Calendar.MINUTE) >= 30)
        c.add(Calendar.HOUR, 1);

    c.set(Calendar.MINUTE, 0);
    c.set(Calendar.SECOND, 0);

String out = sdf.format(c.getTime());   //date in required format

//Write JSON

JSONObject file = new JSONObject();
file.put("topic", topic);
file.put("tweet_text", tweet_text);
file.put("tweet_lang", lang);


if (en == true)
file.put("text_en", text);

if (es == true)
file.put("text_es", text);

if (tr == true)
file.put("text_tr", text);

if (ko == true)
file.put("text_ko", text);

if (xx == true)
file.put("text_"+lang, text);

if (emo1 == true)
    if (emo2 == true){
    emoticons.addAll(emojis);}
file.put("tweet_emoticons", emoticons);
if (emo1 == false){
    if (emo2 == true)
    //emoticons.addAll(emojis);
   file.put("tweet_emoticons", emojis);}

if (men1 == true)
file.put("mentions", mentions);
if (men1 == false)
    file.put("mentions", nu);

if (url1 == true)
file.put("tweet_urls", urls);
if (url1 == false)
    file.put("tweet_urls", nu);

if (hash == true)
file.put("hashtags", hashtags);
if (hash == false)
    file.put("hashtags", nu);


file.put("tweet_date", out);

if (hasco == false)
file.put("tweet_loc", co1 );
if (hasco == true)
    file.put("tweet_loc", null);
try {

		//FileWriter file1 = new FileWriter("c:\\test1.json");
    if(is == false && tl == false)
    {bufferWritter2.write(file.toJSONString());
                bufferWritter2.newLine();
                bufferWritter2.flush();
    }
	} catch (IOException e) {
		e.printStackTrace();
	}



}
    bufferWritter2.close();
    
    //Close the input stream
    //in.close();
    }catch (Exception e){//Catch exception if any
     System.err.println("Error: " + e.getMessage());
  }finally{
    // in.close();
        }
    }
}
    

  









