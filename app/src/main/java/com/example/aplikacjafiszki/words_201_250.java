package com.example.aplikacjafiszki;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class words_201_250 extends AppCompatActivity {
    private Random rand= new Random();
    private Button bReverse;
    private Button bNext;
    private TextView message;
    private TextView wordNumber;

    //ZBIÓR WSZYSTKICH SŁOW GDZIE {"NUMER_FISZKI","SLOWO_POLSKIE","SLOWO_W_JEZYKU_OBCYM"},
    //W ZALEZNOSCI OD WYBRANEGO ZESTAWU ID FISZKI PRZY WYSWIETLANIU BEDZIE ZWIEKSZANE O KONKRETNA LICZBE SETEK
    public String[][] tab = {
            {"1","kierować, jechać samochodem","kjøre / kjørte / kjørt"},{"2","ubierać (się), rozbierać (się)","kle / kledde / kledd"},{"3","zawiązać, związać, przywiązać","knytte / knyttet / knyttet"},{"4","gotować","koke / kokte / kokt"},{"5","przyjść, pójść","komme / kom / kommet"},{"6","kontaktować się","kontakte / kontaktet / kontaktet"},{"7","kłócić się","krangle / kranglet / kranglet"},{"8","krytykować","kritisere / kritiserte / kritisert"},{"9","móc, umieć","kunne / kunne / kunnet"}, {"10","ałować","kysse / kysset / kysset"},
            {"11","pozwalać","la / lot / latt"},{"12","robić, przygotowywać","lage / laget / laget "},{"13","śmiać się","le / lo / ledd"},{"14","kłaść","legge / la / lagt"},{"15","wynajmować, wypożyczać","leie / leide / leid"},{"16","bawić się","leke / lekte / lekt"},{"17","czytać","lese / leste / lest"},{"18","szukać","lete / lette / lett"},{"19","żyć","leve / levde / levd"},{"20","leżeć","ligge / lå / ligget"},
            {"21","lubić","like / likte / likt"},{"22","zamykać","lukke / lukket / lukket"},{"23","pachnieć, wąchać","lukte / luktet / luktet"},{"24","słuchać","lytte / lyttet / lyttet"},{"25","kłamać","lyve / løy / løyet "},{"26","uczyć","lære / lærte / lært"},{"27","biegać","løpe / løp / løpt"},{"28","pożyczać","låne / lånte / lånt"},{"29","zamykać na klucz","låse / låste / låst"},{"30","malować","male / malte / malt "},
            {"31","mieć na myśli, uważać","mene / mente / ment"},{"32","zauważać, zaznaczać","merke / merket / merket"},{"33","gubić, tracić","miste / mistet / mistet"},{"34","spotykać","møte / møtte / møtt"},{"35","musieć","måtte / måtte / måttet"},{"36","przelać , przenieść","overføre / overførte / overført"},{"37","pakować","pakke / pakket / pakket"},{"38","parkować","parkere / parkerte / parkert"},{"39","opiekować się, dbać","passe / passet / passet"},{"40","wskazywać","peke / pekte / pekt "},
            {"41","planować","planlegge / planla / planlagt"},{"42","rozmawiać","prate / pratet / pratet "},{"43","próbować","prøve / prøvde / prøvd"},{"44","czyścić","pusse / pusset / pusset"},{"45","oddychać","puste / pustet / pustet"},{"46","kłaść","putte / puttet / puttet"},{"47","czyścić","rense / renset / renset"},{"48","naprawiać, reperować","reparere / reparerte / reparert"},{"49","jeździć konno","ri / red / ridd"},{"50","sprzątać","rydde / ryddet / ryddet"}
    };
    View view;
    // FLAGA SLUŻY DO ZMIANY JEZYKA. GDZIE 0- POLSKI, 1- NORWESKI
    private Integer flag =1;
    // ZIENNA losowa SŁUŻY DO WYLOSOWANIA NOWEGO NUMERU FISZKI
    private Integer    random= rand.nextInt(50) ;
    private String cutString = new String();
    // POLA SLUZACE DO PRZESUNIECIA NUMERU FISZEK O SETKI
    private int numberWordInt;
    private int shift =200;
    private int wordNumberAfterAddition;
    private String wordNumberString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        message =(TextView) findViewById(R.id.tvMessage);
        wordNumber =(TextView) findViewById(R.id.word_number);

        message.setText(tab[random][1]);
        //ZWIEKSZENIE NUMERU FISZKI POPRZEZ DODANIE SETEK, ZAMIANA STRING NA INT -> DODANIE SETEK -> ZAMIANA INT NA STRING
        wordNumber.setText(returnWordNumber());

        view  = this.getWindow().getDecorView();
        view.setBackgroundResource(R.drawable.gradient_grey);
        // PRZYCISK "DALEJ"
        bNext =(Button) findViewById(R.id.b_next);
        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer currentRandom =random;
                flag =1;
                // PĘTLA UNIEMOŻLIWIAJĄCA POWTARZANIE SIĘ FISZEK
                // -->
                while(currentRandom==random)
                {
                    random = rand.nextInt(50);
                }
                // <--
                //ZMIANA FISZKI ORAZ ZMIANA NUMERU FISZKI
                message.setText(tab[random][1]);
                wordNumber.setText(returnWordNumber());
                view.setBackgroundResource(R.drawable.gradient_grey);

            }
        });

        //PRZYCISK ZMIANY JEZYKA FISZKI -->
        bReverse =(Button) findViewById(R.id.b_ChangeText);
        bReverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wordNumber.setText(returnWordNumber());
                cutString= tab[random][2].substring(0,3);
                //SPRAWDZENIE RODZAJNIKA W JEZYKU NORWESKIM, W ZALEZNOSCI OD NIEGO ZMIENA SIE KOLOR TŁA APLIKACJI
                switch (cutString){
                    case "en " : {view.setBackgroundResource(R.drawable.gradient_blue);break;}
                    case "ei " : {view.setBackgroundResource(R.drawable.gradient_red);break;}
                    case "en/" : {view.setBackgroundResource(R.drawable.gradient_orange);break;}
                    case "et " : {view.setBackgroundResource(R.drawable.gradient_green);break;}
                    default: view.setBackgroundResource(R.drawable.gradient_grey);break;
                }
                // SPRAWDZENIE I ZMIANA FLAGI. ZMIANA JEZYKA FISZKI
                switch(flag) {
                    case 0:   message.setText(tab[random][1]);flag =1;
                        break;
                    case 1:   message.setText(tab[random][2]);flag =0;
                        break;
                }
            }
        });
        //<--PRZYCISK ZMIANY JEZYKA FISZKI

    }
    private String returnWordNumber(){
        numberWordInt = Integer.parseInt(tab[random][0]);
        wordNumberAfterAddition = numberWordInt + shift;
        wordNumberString = String.valueOf(wordNumberAfterAddition);
        return wordNumberString;
    }
}
