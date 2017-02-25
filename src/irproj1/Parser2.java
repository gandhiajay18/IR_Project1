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
import java.io.FileNotFoundException;
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



public class Parser2 {

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
  String emot = "[:;]-?[DP()]";
  String htag = "#[A-Za-z0-9]+";
  String ment = "@[A-Za-z0-9]+";
  String url = "https?://\\S+\\s?";
 // String emoj = "[\\u20a0-\\u32ff\\ud83c\\udc00-\\ud83d\\udeff\\udbb9\\udce5-\\udbb9\\udcee]";
 // String emoj = "u'('u'\ud83c[\udf00-\udfff]|'u'\ud83d[\udc00-\ude4f\ude80-\udeff]|'u'[\u2600-\u26FF\u2700-\u27BF]+')";
  String emoj = "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]";
  String[] kaomoji = {"¢‿¢","©¿©o","ª{•̃̾_•̃̾}ª","¬_¬","¯＼(º_o)/¯","°ω°","°Д°","°‿‿°","°ﺑ°","´▽`)ﾉ","¿ⓧ_ⓧﮌ","Ò,ó","ó‿ó","ô⌐ô","ôヮô","ŎםŎ","ŏﺡó","ʕ•̫͡•ʔ","ʕ•ᴥ•ʔ","ʘ‿ʘ","˚•_•˚","˚⌇˚","˚▱˚","͡\"°͜ʖ﻿͡°","Σ◕◡◕","Σ(ﾟДﾟ;）","Σ(ﾟДﾟ；≡；ﾟдﾟ)","Σ(ﾟДﾟ)","Σ(||ﾟДﾟ)","Φ,Φ","δﺡό","σ_σ","д_д","ф_ф","щ（ﾟДﾟщ）","щ(ಠ益ಠщ)","щ(ಥДಥщ)","Ծ_Ծ","أ‿أ","ب_ب","ح˚௰˚づ","ح˚ᆺ˚ว","حᇂﮌᇂ)","٩๏̯͡๏۶","٩๏̯͡๏)۶","٩◔̯◔۶","٩(×̯×)۶","٩(̾●̮̮̃̾•̃̾)۶","٩(͡๏̯͡๏)۶","٩(͡๏̯͡๏)۶","٩(ಥ_ಥ)۶","٩(•̮̮̃•̃)۶","٩(●̮̮̃•̃)۶","٩(●̮̮̃●̃)۶","٩(｡͡•‿•｡)۶","٩(-̮̮̃•̃)۶","٩(-̮̮̃-̃)۶","۞_۞","۞_۟۞","۹ↁﮌↁ","۹⌤_⌤۹","॓_॔","१✌◡✌५","१|˚–˚|५","ਉ_ਉ","ଘ_ଘ","இ_இ","ఠ_ఠ","రృర","ಠ¿ಠi","ಠ‿ಠ","ಠ⌣ಠ","ಠ╭╮ಠ","ಠ▃ಠ","ಠ◡ಠ","ಠ益ಠ","ಠ益ಠ","ಠ︵ಠ凸","ಠ,ಥ","ಠ.ಠ","ಠoಠ","ಠ_ృ","ಠ_ಠ","ಠ_๏","ಠ~ಠ","ಡ_ಡ","ತಎತ","ತ_ತ","ಥдಥ","ಥ‿ಥ","ಥ⌣ಥ","ಥ◡ಥ","ಥ﹏ಥ","ಥ_ಥ","ಭ_ಭ","ರ_ರ","ಸ,໖","ಸ_ಸ","ക_ക","อ้_อ้","อ_อ","โ๏௰๏ใื","๏̯͡๏﴿","๏̯͡๏","๏̯͡๏﴿","๏[-ิิ_•ิ]๏","๏_๏","໖_໖","༺‿༻","ლ(´ڡ`ლ)","ლ(́◉◞౪◟◉‵ლ)","ლ(ಠ益ಠლ)","ლ(╹◡╹ლ)","ლ(◉◞౪◟◉‵ლ)","ლ,ᔑ•ﺪ͟͠•ᔐ.ლ","ᄽὁȍ̪őὀᄿ","ᕕ(ᐛ)ᕗ","ᕙ(⇀‸↼‶)ᕗ","ᕦ(ò_óˇ)ᕤ","ᶘᵒᴥᵒᶅ","‘︿’","•▱•","•✞_✞•","•ﺑ•","•(⌚_⌚)•","•_•)","‷̗ↂ凸ↂ‴̖","‹•.•›","‹›‹(•¿•)›‹›","‹(ᵒᴥᵒ­­­­­)›﻿","‹(•¿•)›","ↁ_ↁ","⇎_⇎","∩(︶▽︶)∩","∩(・ω・)∩","≖‿≖","≧ヮ≦","⊂•⊃_⊂•⊃","⊂⌒~⊃｡Д｡)⊃","⊂(◉‿◉)つ","⊂(ﾟДﾟ,,⊂⌒｀つ","⊙ω⊙","⊙▂⊙","⊙▃⊙","⊙△⊙","⊙︿⊙","⊙﹏⊙","⊙０⊙","⊛ठ̯⊛","⋋ō_ō`","━━━ヽ(ヽ(ﾟヽ(ﾟ∀ヽ(ﾟ∀ﾟヽ(ﾟ∀ﾟ)ﾉﾟ∀ﾟ)ﾉ∀ﾟ)ﾉﾟ)ﾉ)ﾉ━━━","┌∩┐(◕_◕)┌∩┐","┌(ಠ_ಠ)┘","┌(ಥ_ಥ)┘","╚(•⌂•)╝","╭╮╭╮☜{•̃̾_•̃̾}☞╭╮╭╮","╭✬⌢✬╮","╮(─▽─)╭","╯‵Д′)╯彡┻━┻","╰☆╮","□_□","►_◄","◃┆◉◡◉┆▷","◉△◉","◉︵◉","◉_◉","○_○","●_●","◔̯◔","◔ᴗ◔","◔⌣◔","◔_◔","◕ω◕","◕‿◕","◕◡◕","◕◡◕","◖♪_♪|◗","◖|◔◡◉|◗<Huuuuur","◘_◘","◙‿◙","◜㍕◝","◪_◪","◮_◮","☁☝ˆ~ˆ☂","☆¸☆","☉‿⊙","☉_☉","☐_☐","☜ق❂Ⴢ❂ق☞","☜(⌒▽⌒)☞","☜(ﾟヮﾟ☜)","☜-(ΘLΘ)-☞","☝☞✌","☮▁▂▃▄☾♛◡♛☽▄▃▂▁☮","☹_☹","☻_☻","☼.☼","☾˙❀‿❀˙☽","♀ح♀ヾ","♥‿♥","♥╣[-_-]╠♥","♥╭╮♥","♥◡♥","✌♫♪˙❤‿❤˙♫♪✌","✌.ʕʘ‿ʘʔ.✌","✌.|•͡˘‿•͡˘|.✌","✖‿✖","✖_✖","〆(・∀・＠)","《〠_〠》","【•】_【•】","〠_〠","〴⋋_⋌〵","のヮの","ニガー?━━━━━━(ﾟ∀ﾟ)━━━━━━ニガー?","ヽ(´ｰ｀)ﾉ","ヽ(๏∀๏)ﾉ","ヽ(｀Д´)ﾉ","ヽ(ｏ`皿′ｏ)ﾉ","ヽ(`Д´)ﾉ","ㅎ_ㅎ","乂◜◬◝乂","凸ಠ益ಠ)凸","句_句","Ꙩ⌵Ꙩ","Ꙩ_Ꙩ","ꙩ_ꙩ","Ꙫ_Ꙫ","ꙫ_ꙫ","ꙮ_ꙮ","흫_흫","句_句","﴾͡๏̯͡๏﴿O'RLY?","¯\\(ºдಠ)/¯","（·×·）","（⌒Д⌒）","（╹ェ╹）","（♯・∀・）⊃","（　´∀｀）☆","（　´∀｀）","（゜Д゜）","（・∀・）","（・Ａ・）","（ﾟ∀ﾟ）","（￣へ￣）","（´☣///_ゝ///☣｀）","（つД｀）","＿☆（´_⊃｀）☆＿","｡◕‿‿◕｡","｡◕‿◕｡","!⑈ˆ~ˆ!⑈","!(｀･ω･｡)","(¬‿¬)","(¬▂¬)","(¬_¬)","(°ℇ°)","(°∀°)","(´ω｀)","(´◉◞౪◟◉)","(´ヘ｀;)","(´・ω・｀)","(´ー｀)","(ʘ‿ʘ)","(ʘ_ʘ)","(˚இ˚)","(͡๏̯͡๏)","(ΘεΘ;)","(ι´Д｀)ﾉ","(Ծ‸Ծ)","(॓_॔)","(०्०)","(ு८ு_.:)","(ಠ‾ಠ﻿)","(ಠ‿ʘ)","(ಠ‿ಠ)","(ಠ⌣ಠ)","(ಠ益ಠ╬)","(ಠ益ಠ)","(ಠ_ృ)","(ಠ_ಠ)","(ಥ﹏ಥ)","(ಥ_ಥ)","(๏̯͡๏)","(ღˇ◡ˇ)~♥","(ღ˘⌣˘ღ)♫･*:.｡..｡.:*･","(ღ˘⌣˘ღ)","(ᵔᴥᵔ)","(•ω•)","(•‿•)","(•⊙ω⊙•)","(•ε•)","(∩▂∩)","(∩︵∩)","(∪◡∪)","(≧ω≦)","(≧◡≦)","(≧ロ≦)","(⊙ヮ⊙)","(⊙_◎)","(⋋▂⋌)","(⌐■_■)","(─‿‿─)","(┛◉Д◉)┛┻━┻","(╥_╥)","(╬ಠ益ಠ)","(╬◣д◢)","(╬ಠ益ಠ)","(╯°□°）╯︵┻━┻","(╯ಊ╰)","(╯◕_◕)╯","(╯︵╰,)","(╯3╰)","(╯_╰)","(╹◡╹)凸","(▰˘◡˘▰)","(●´ω｀●)","(●´∀｀●)","(◑‿◐)","(◑◡◑)","(◕‿◕✿)","(◕‿◕)","(◕‿-)","(◕︵◕)","(◕^◕)","(◕_◕)","(◜௰◝)","(◡‿◡✿)","(◣_◢)","(☞ﾟ∀ﾟ)☞","(☞ﾟヮﾟ)☞","(☞ﾟ∀ﾟ)☞","(☼◡☼)","(☼_☼)","(✌ﾟ∀ﾟ)☞","(✖╭╮✖)","(✪㉨✪)","(✿◠‿◠)","(✿♥‿♥)","(　・∀・)","(　･ัω･ั)？","(　ﾟ∀ﾟ)o彡゜えーりんえーりん!!","(。・_・。)","(つд｀)","(づ｡◕‿‿◕｡)づ","(ノಠ益ಠ)ノ彡┻━┻","(ノ◑‿◑)ノ","(ノ_・。)","(・∀・)","(屮ﾟДﾟ)屮","(︶ω︶)","(︶︹︺)","(ﺧ益ﺨ)","(；一_一)","(｀・ω・´)”","(｡◕‿‿◕｡)","(｡◕‿◕｡)","(｡◕‿◕｡)","(｡♥‿♥｡)","(｡･ω..･)っ","(･ｪ-)","(ﾉ◕ヮ◕)ﾉ*:・ﾟ✧","(ﾉ◕ヮ◕)ﾉ*:･ﾟ✧","(ﾟДﾟ)","(ﾟДﾟ)y─┛~~","(ﾟヮﾟ)","(￣□￣)","(￣。￣)","(￣ー￣)","(￣(エ)￣)","(°٢°)","(´_ゝ｀)","(͡°͜ʖ͡°)","(͡~͜ʖ͡°)","(ಠ◡ಠ)","(•_•)>⌐■-■","(　ﾟ,_ゝﾟ)","(･ิз･ิ)","(ﾟдﾟ)､","(^▽^)σ)~O~)","((((゜д゜;))))","(*´д｀*)","(*..Д｀)","(*..д｀*)","(*~▽~)","(-’๏_๏’-)","(-＿-)ノ","(/◔◡◔)/","(///_ಥ)","(;´Д`)","(=ω=;)","(=゜ω゜)","(>'o')>♥<('o'<)","(n˘v˘•)¬","(o´ω｀o)","(V)(°,,°)(V)","(\\/)(°,,°)(\\/)","(^▽^)","(`･ω･´)<--Pedobear?","(~￣▽￣)~","/╲/\\╭ºoꍘoº╮/\\╱\\","<【☯】‿【☯】>","=(ﾟдﾟ)ｳ","@_@","(*⌒▽⌒*)b","o(≧∀≦)o","o(≧o≦)o","q(❂‿❂)p","y=ｰ(ﾟдﾟ)･∵.","\\˚ㄥ˚\\","\\ᇂ_ᇂ\\","\\(ಠὡಠ)/","\\(◕◡◕\\)","^̮^","^ㅂ^","_(͡๏̯͡๏)_","{´◕◡◕｀}","{ಠ_ಠ}__,,|,","{◕◡◕}"
  ,"ニガー?━━━━━━(ﾟ∀ﾟ)━━━━━━ニガー?","━━━━(ﾟ∀ﾟ)━━━━ｯ","٩(^ᴗ^)۶","(ﾟ∀ﾟ)","(⌒▽⌒)"};

String err = "kao";
  int i = 1;
  for (i=1;i<=59;i++)
     try{
         String oldjson = i + ".json";
             Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;


    		File file2 = new File("NewJSON" + oldjson);
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
   coord = (JSONObject)jsonObject.get("geo");
   hasco = (coord == null);
  // hasco = coord.containsKey("coordinates");
   if (hasco == false){
   JSONArray cord = (JSONArray) coord.get("coordinates");
   co1 = cord.toString().replaceAll("\\[|\\]","");
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
          topic = "politics";
            break;
        }
        }
        if (!top == true)
        {
            for (String d: entert)
        {
        if (text.contains(d))
        {
            top = true;
            topic = "entertainment";
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
            topic = "news";
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
            topic = "tech";
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
            topic = "sports";
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
 //Pattern regem = Pattern.compile(emoj);
 Pattern regem = Pattern.compile(emoj,Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
Matcher rmem = regem.matcher(text);
List <String> emojis = new ArrayList<String>();
while (rmem.find()){
       emoticons.add(rmem.group());
       emojis.add(rmem.group());
       //Test for emojis
       emo2 = true;
        }
if (emo2 == true){

         //   System.out.println(emo.get(i) + "\b");
 text = text.replaceAll(emoj, "");              // replace all emojis with space
        }

List <String> kao = new ArrayList<String>();
boolean emo3 = false;
for(int k =0; k < kaomoji.length; k++)
    {
        if(text.contains(kaomoji[k]))
        {
            emo3 = true;
            err = br.readLine();
            System.out.println(kaomoji[k]);
            emoticons.add(kaomoji[k]);

           text = text.trim().replaceAll(kaomoji[k], "");
        }
    }




 //Store hashtags and remove them from text
  boolean hash = false;
 Pattern reg1 = Pattern.compile(htag);
Matcher rm1 = reg1.matcher(text);
List <String> hashtags = new ArrayList<String>();

while (rm1.find()){
    String hashs = rm1.group();
    hashs = hashs.replaceAll("#", "");
    hashs = hashs.trim().replaceAll(" +", " ");
       hashtags.add(hashs);
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
    String men = rm2.group();
    men = men.replaceAll("@", "");
    men = men.trim().replaceAll(" +", " ");
       mentions.add(men);
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

 //Stopword removal
String sten = "\\b(a|about|above|across|after|again|against|almost|alone|along|already|also|although|always|all|am|among|an|and|another|anybody|anyone|anything|anywhere|any|are|area|areas|around|aren't|as|ask|asked|asking|asks|at|away|b|back|backed|backing|backs|became|become|becomes|began|behind|beings|best|better|big|be|because|been|before|being|below|between|both|but|by|c|came|can|case|cases|certain|certainly|clear|clearly|come|can't|cannot|could|couldn't|d|differ|different|differently|done|downed|downing|downs|during|did|didn't|do|does|doesn't|doing|don't|down|during|each|few|for|from|further|had|hadn't|has|hasn't|have|haven't|having|he|he'd|he'll|he's|her|here|here's|hers|herself|him|himself|his|how|how's|i|i'd|i'll|i'm|i've|if|in|into|is|isn't|it|it's|its|itself|let's|me|more|most|mustn't|my|myself|no|nor|of|off|on|once|only|or|other|ought|our|ours|ourselves|out|over|own|same|shan't|she|she'd|she'll|she's|should|shouldn't|so|some|such|than|that|that's|the|their|theirs|them|themselves|then|there|there's|these|they|they'd|they'll|they're|they've|this|those|through|to|too|under|until|up|very|w|was|wasn't|we|we'd|we'll|we're|we've|were|weren't|whole|whose|what|what's|when|when's|where|where's|whether|which|while|with|will|within|without|who|who's|whom|why|why's|won't|work|worked|working|works|would|wouldn't|want|wanted|wanting|wants|was|way|ways|well|wells|went|were|year|years|yet|you|you'd|you'll|you're|you've|young|younger|youngest|your|yours|yourself|yourselves|each|early|either|end|ended|ending|ends|enough|even|evenly|ever|every|everybody|everyone|everything|everywhere|face|faces|fact|facts|far|felt|few|find|finds|first|for|four|from|full|fully|further|furthered|furthering|furthers|gave|general|generally|get|gets|give|given|gives|go|going|good|goods|got|great|greater|greatest|group|grouped|grouping|groups|had|has|have|having|he|her|here|herself|high|higher|highest|him|himself|his|how|however|if|important|in|interest|interested|interesting|interests|into|is|it|its|itself|j|just|k|keep|keeps|kind|knew|know|known|knows|large|largely|last|later|latest|least|less|let|lets|like|likely|long|longer|longest|m|made|make|making|man|many|may|me|member|members|men|might|more|most|mostly|mr|mrs|much|must|my|myself|n|necessary|need|needed|needing|needs|never|newer|newest|next|no|nobody|non|noone|nothing|now|nowhere|number|numbers|o|of|off|often|old|older|oldest|one|open|opened|opening|opens|order|ordered|ordering|orders|others|part|parted|parting|parts|per|perhaps|place|places|point|pointed|pointing|points|possible|present|presented|presenting|presents|problem|problems|put|puts|q|quite|r|rather|really|right|room|rooms|s|said|same|saw|say|says|second|seconds|see|seem|seemed|seeming|seems|sees|several|shall|she|should|show|showed|showing|shows|side|sides|since|small|smaller|smallest|so|some|somebody|someone|something|somewhere|state|states|still|such|sure|take|taken|than|that|their|then|there|therefore|these|they|thing|things|think|thinks|this|those|though|thought|thoughts|three|through|thus|to|today|together|too|took|toward|turn|turned|turning|turns|two|under|until|up|upon|us|use|used|uses|very)\\b\\s?";
String stes = "\\b(un|una|unas|unos|uno|sobre|todo|también|tras|otro|algún|alguno|alguna|algunos|algunas|ser|es|soy|eres|somos|sois|estoy|esta|estamos|estais|estan|como|en|para|atras|porque|por qué|estado|estaba|ante|antes|siendo|ambos|pero|por|poder|puede|puedo|podemos|podeis|pueden|fui|fue|fuimos|fueron|hacer|hago|hace|hacemos|haceis|hacen|cada|fin|incluso|primero|desde|conseguir|consigo|consigue|consigues|conseguimos|consiguen|ir|voy|va|vamos|vais|van|vaya|gueno|ha|tener|tengo|tiene|tenemos|teneis|tienen|el|la|lo|las|los|su|aqui|mio|tuyo|ellos|ellas|nos|nosotros|vosotros|vosotras|si|dentro|solo|solamente|saber|sabes|sabe|sabemos|sabeis|saben|ultimo|largo|bastante|haces|muchos|aquellos|aquellas|sus|entonces|tiempo|verdad|verdadero|verdadera|cierto|ciertos|cierta|ciertas|intentar|intento|intenta|intentas|intentamos|intentais|intentan|dos|bajo|arriba|encima|usar|uso|usas|usa|usamos|usais|usan|emplear|empleo|empleas|emplean|ampleamos|empleais|valor|muy|era|eras|eramos|eran|modo|bien|cual|cuando|donde|mientras|quien|con|entre|sin|trabajo|trabajar|trabajas|trabaja|trabajamos|trabajais|trabajan|podria|podrias|podriamos|podrian|podriais|yo|aquel)\\b\\s?";
String sttr = "\\b(acaba|altmýþ|altý|ama|bana|bazý|belki|ben|benden|beni|benim|beþ|bin|bir|biri|birkaç|birkez|birþey|birþeyi|biz|bizden|bizi|bizim|bu|buna|bunda|bundan|bunu|bunun|da|daha|dahi|de|defa|diye|doksan|dokuz|dört|elli|en	|gibi|hem|hep|hepsi|her|hiç|iki|ile|INSERmi|ise|için|katrilyon|kez|ki|kim|kimden|kime|kimi|kýrk|milyar|milyon|mu|mü|mý|nasýl|ne|neden|nerde|nerede|nereye|niye|niçin|on|ona|ondan|onlar|onlardan|onlari|onlarýn|onu|otuz|sanki|sekiz|seksen|sen|senden|seni|senin|siz|sizden|sizi|sizin|trilyon|tüm|ve|veya|ya|yani|yedi|yetmiþ|yirmi|yüz|çok|çünkü|üç|þey|þeyden|þeyi|þeyler|þu|þuna|þunda|þundan|þunu)\\b\\s?";
String stko = "\\b(아|휴|아이구|아이쿠|아이고|어|나|우리|저희|따라|의해|을|를|에|의|가|으로|로|에게|뿐이다|의거하여|근거하여|입각하여|기준으로|예하면|예를 들면|예를 들자면|저|소인|소생|저희|지말고|하지마|하지마라|다른|물론|또한|그리고|비길수 없다|해서는 안된다|뿐만 아니라|만이 아니다|만은 아니다|막론하고|관계없이|그치지 않다|그러나|그런데|하지만|든간에|논하지 않다|따지지 않다|설사|비록|더라도|아니면|만 못하다|하는 편이 낫다|불문하고|향하여|향해서|향하다|쪽으로|틈타|이용하여|타다|오르다|제외하고|이 외에|이 밖에|하여야|비로소|한다면 몰라도|외에도|이곳|여기|부터|기점으로|따라서|할 생각이다|하려고하다|이리하여|그리하여|그렇게 함으로써|하지만|일때|할때|앞에서|중에서|보는데서|으로써|로써|까지|해야한다|일것이다|반드시|할줄알다|할수있다|할수있어|임에 틀림없다|한다면|등|등등|제|겨우|단지|다만|할뿐|딩동|댕그|대해서|대하여|대하면|훨씬|얼마나|얼마만큼|얼마큼|남짓|여|얼마간|약간|다소|좀|조금|다수|몇|얼마|지만|하물며|또한|그러나|그렇지만|하지만|이외에도|대해 말하자면|뿐이다|다음에|반대로|반대로 말하자면|이와 반대로|바꾸어서 말하면|바꾸어서 한다면|만약|그렇지않으면|까악|툭|딱|삐걱거리다|보드득|비걱거리다|꽈당|응당|해야한다|에 가서|각|각각|여러분|각종|각자|제각기|하도록하다|와|과|그러므로|그래서|고로|한 까닭에|하기 때문에|거니와|이지만|대하여|관하여|관한|과연|실로|아니나다를가|생각한대로|진짜로|한적이있다|하곤하였다|하|하하|허허|아하|거바|와|오|왜|어째서|무엇때문에|어찌|하겠는가|무슨|어디|어느곳|더군다나|하물며|더욱이는|어느때|언제|야|이봐|어이|여보시오|흐흐|흥|휴|헉헉|헐떡헐떡|영차|여차|어기여차|끙끙|아야|앗|아야|콸콸|졸졸|좍좍|뚝뚝|주룩주룩|솨|우르르|그래도|또|그리고|바꾸어말하면|바꾸어말하자면|혹은|혹시|답다|및|그에 따르는|때가 되어|즉|지든지|설령|가령|하더라도|할지라도|일지라도|지든지|몇|거의|하마터면|인젠|이젠|된바에야|된이상|만큼	어찌됏든|그위에|게다가|점에서 보아|비추어 보아|고려하면|하게될것이다|일것이다|비교적|좀|보다더|비하면|시키다|하게하다|할만하다|의해서|연이서|이어서|잇따라|뒤따라|뒤이어|결국|의지하여|기대여|통하여|자마자|더욱더|불구하고|얼마든지|마음대로|주저하지 않고|곧|즉시|바로|당장|하자마자|밖에 안된다|하면된다|그래|그렇지|요컨대|다시 말하자면|바꿔 말하면|즉|구체적으로|말하자면|시작하여|시초에|이상|허|헉|허걱|바와같이|해도좋다|해도된다|게다가|더구나|하물며|와르르|팍|퍽|펄렁|동안|이래|하고있었다|이었다|에서|로부터|까지|예하면|했어요|해요|함께|같이|더불어|마저|마저도|양자|모두|습니다|가까스로|하려고하다|즈음하여|다른|다른 방면으로|해봐요|습니까|했어요|말할것도 없고|무릎쓰고|개의치않고|하는것만 못하다|하는것이 낫다|매|매번|들|모|어느것|어느|로써|갖고말하자면|어디|어느쪽|어느것|어느해|어느 년도|라 해도|언젠가|어떤것|어느것|저기|저쪽|저것|그때|그럼|그러면|요만한걸|그래|그때|저것만큼|그저|이르기까지|할 줄 안다|할 힘이 있다|너|너희|당신|어찌|설마|차라리|할지언정|할지라도|할망정|할지언정|구토하다|게우다|토하다|메쓰겁다|옆사람|퉤|쳇|의거하여|근거하여|의해|따라|힘입어|그|다음|버금|두번째로|기타|첫번째로|나머지는|그중에서|견지에서|형식으로 쓰여|입장에서|위해서|단지|의해되다|하도록시키다|뿐만아니라|반대로|전후|전자|앞의것|잠시|잠깐|하면서|그렇지만|다음에|그러한즉|그런즉|남들|아무거나|어찌하든지|같다|비슷하다|예컨대|이럴정도로|어떻게|만약|만일|위에서 서술한바와같이|인 듯하다|하지 않는다면|만약에|무엇|무슨|어느|어떤|아래윗|조차|한데|그럼에도 불구하고|여전히|심지어|까지도|조차도|하지 않도록|않기 위하여|때|시각|무렵|시간|동안|어때|어떠한|하여금|네|예|우선|누구|누가 알겠는가|아무도|줄은모른다|줄은 몰랏다|하는 김에|겸사겸사|하는바|그런 까닭에|한 이유는|그러니|그러니까|때문에|그|너희|그들|너희들|타인|것|것들|너|위하여|공동으로|동시에|하기 위하여|어찌하여|무엇때문에|붕붕|윙윙|나|우리|엉엉|휘익|윙윙|오호|아하|어쨋든|만 못하다	하기보다는|차라리|하는 편이 낫다|흐흐|놀라다|상대적으로 말하자면|마치|아니라면|쉿|그렇지 않으면|그렇지 않다면|안 그러면|아니었다면|하든지|아니면|이라면|좋아|알았어|하는것도|그만이다|어쩔수 없다|하나|일|일반적으로|일단|한켠으로는|오자마자|이렇게되면|이와같다면|전부|한마디|한항목|근거로|하기에|아울러|하지 않도록|않기 위해서|이르기까지|이 되다|로 인하여|까닭으로|이유만으로|이로 인하여|그래서|이 때문에|그러므로|그런 까닭에|알 수 있다|결론을 낼 수 있다|으로 인하여|있다|어떤것|관계가 있다|관련이 있다|연관되다|어떤것들|에 대해|이리하여|그리하여|여부|하기보다는|하느니|하면 할수록|운운|이러이러하다|하구나|하도다|다시말하면|다음으로|에 있다|에 달려 있다|우리|우리들|오히려|하기는한데|어떻게|어떻해|어찌됏어|어때|어째서|본대로|자|이|이쪽|여기|이것|이번|이렇게말하자면|이런|이러한|이와 같은|요만큼|요만한 것|얼마 안 되는 것|이만큼|이 정도의|이렇게 많은 것|이와 같다|이때|이렇구나|것과 같이|끼익|삐걱|따위|와 같은 사람들|부류의 사람들|왜냐하면|중의하나|오직|오로지|에 한하다|하기만 하면|도착하다|까지 미치다|도달하다|정도에 이르다|할 지경이다|결과에 이르다|관해서는|여러분|하고 있다|한 후|혼자|자기|자기집|자신|우에 종합한것과같이|총적으로 보면|총적으로 말하면|총적으로|대로 하다|으로서|참|그만이다|할 따름이다|쿵|탕탕|쾅쾅|둥둥|봐|봐라|아이야|아니|와아|응|아이|참나|년|월|일|령|영|일|이|삼|사|오|육|륙|칠|팔|구|이천육|이천칠|이천팔|이천구|하나|둘|셋|넷|다섯|여섯|일곱|여덟|아홉|령|영)\\b\\s";
String slist = null;
if (lang.equals("en"))
    slist = sten;
if (lang.equals("tr"))
    slist = sttr;
if (lang.equals("es"))
    slist = stes;
if (lang.equals("ko"))
    slist = stko;
if (slist != null){
Pattern p = Pattern.compile(slist, Pattern.CASE_INSENSITIVE);
    Matcher m = p.matcher(text);
    text = m.replaceAll("");}
   // System.out.println(s);


text = text.replaceAll("\n", "");
text = text.replaceAll("(RT|rt)", "");
text = text.trim().replaceAll(",", "");        // remove comma
text = text.trim().replaceAll("[.]", "");
text = text.trim().replaceAll("[\\p{Punct}]", "");
text = text.trim().replaceAll(" +", " ");      //replace 2 or more space characters with just one space

String text_en = null, text_es = null, text_tr = null, text_ko = null, text_xx = null;
Boolean en = false, es = false, tr = false, ko = false, xx = false, is = false, tl = false;

if (!lang.isEmpty() == true)
{
    if (lang.equals("en")) {
text_en = text.replaceAll(":", "");
text_en = text.replaceAll(" +", " ");
en = true;
        }
    else if(lang.equals("es")) {
text_es = text.replaceAll(":", "");
text_es = text.replaceAll(" +", " ");
es = true;
        }
    else if(lang.equals("tr")) {
text_tr = text.replaceAll(":", "");
text_tr = text.replaceAll(" +", " ");
tr = true;
        }
    else if(lang.equals("ko")) {
text_ko = text.replaceAll(":", "");
text_ko = text.replaceAll(" +", " ");
ko = true;
        }
    else{
        text_xx = text;
        xx = true;
    }

if (lang.equals("is")){
            is = true;}
if (lang.equals("tl")){
tl = true;
}

}

// Get timestamp and convert to nearest hour in GMT

        long t = Long.parseLong(time);
        Date d = new Date(t);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
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
tweet_text = tweet_text.replaceAll("\n", "");
tweet_text = tweet_text.trim().replaceAll(" +", " ");
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

if (emo1 == true || emo2 == true || emo3 == true)
    
file.put("tweet_emoticons", emoticons);
//if (emo1 == false && emo2 == false && emo3)
    //emoticons.addAll(emojis);
  // file.put("tweet_emoticons", nu);}

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
    if (is == false && tl == false)
    {
        bufferWritter2.write(file.toJSONString());
                bufferWritter2.newLine();
                bufferWritter2.flush();}

	} catch (IOException e) {
		e.printStackTrace();
	}



}
    bufferWritter2.close();
    
    //Close the input stream
    //in.close();
    }catch (Exception e){//Catch exception if any
     System.err.println("Error: " + e.getMessage());
     System.err.println("Error: " + err);
  }finally{
    // in.close();
        }}}
    

  









