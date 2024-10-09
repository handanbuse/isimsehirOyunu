package com.example.isimsehiroyunu;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class NormalOyunActivity extends AppCompatActivity {

    private String[] iller = {"Adana", "Adıyaman", "Afyonkarahisar", "Ağrı", "Aksaray", "Amasya", "Ankara", "Antalya", "Ardahan", "Artvin", "Aydın",

            "Balıkesir", "Bartın", "Batman", "Bayburt", "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur", "Bursa",
            "Çanakkale", "Çankırı", "Çorum", "Denizli", "Diyarbakır", "Düzce", "Edirne", "Elazığ", "Erzincan", "Erzurum", "Eskişehir",
            "Gaziantep", "Giresun", "Gümüşhane", "Hakkâri", "Hatay", "Iğdır", "Isparta", "İstanbul", "İzmir",
            "Kahramanmaraş", "Karabük", "Karaman", "Kars", "Kastamonu", "Kayseri", "Kilis", "Kırıkkale", "Kırklareli", "Kırşehir", "Kocaeli", "Konya", "Kütahya",
            "Malatya", "Manisa", "Mardin", "Mersin", "Muğla", "Muş", "Nevşehir", "Niğde", "Ordu", "Osmaniye", "Rize", "Sakarya", "Samsun", "Şanlıurfa", "Siirt", "Sinop",
            "Sivas", "Şırnak", "Tekirdağ", "Tokat", "Trabzon", "Tunceli", "Uşak", "Van", "Yalova", "Yozgat", "Zonguldak"};

    private Random rndil, rndharf;
    private int rndilnumber, rndnumberharf;
    private String gelenil, ilboyutu="", edittxtgelentahmin;
    private TextView txtİLbilgi, txtharfAl;
    private ArrayList<Character> ilharfleri;
    private EditText editTxtTahmin;
    private Button btnharfal, btntahminet;
    private int baslagıcharfsayisi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_oyun);

        txtİLbilgi = findViewById(R.id.txtİLbilgi);
        txtharfAl = findViewById(R.id.txtharfAl);
        editTxtTahmin = findViewById(R.id.editTxtTahmin);
        btnharfal = findViewById(R.id.btnHarfAl);
        btntahminet = findViewById(R.id.btnTahminEt);

        rndharf = new Random(); // harf için tanımlanan random
        // random il alması
        randomdegeribelirle();

        }

        private  void randomdegeribelirle () {
        ilboyutu="";

            rndil = new Random();
            rndilnumber = rndil.nextInt(iller.length); // illeri uzunluğuna göre al
            gelenil = iller[rndilnumber];
            System.out.println(gelenil);
            // 6harfli il yerine gelecekler

            txtİLbilgi.setText(gelenil.length() + " harfli ilimiz");
            

            for (int i = 0; i < gelenil.length(); i++) {
                if (i < gelenil.length() - 1)
                    ilboyutu += "_ ";
                else
                    ilboyutu += "_";
        }


        btntahminet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edittxtgelentahmin = editTxtTahmin.getText().toString();

                if (!TextUtils.isEmpty(edittxtgelentahmin)) {
                    if (edittxtgelentahmin.equalsIgnoreCase(gelenil)){
                        Toast.makeText(getApplicationContext()," doğru tahmin", Toast.LENGTH_SHORT) . show();
                    }else{
                      Toast.makeText(getApplicationContext(), "yanlış tahmin", Toast.LENGTH_SHORT).show();
                    }

                }  else {
                    Toast.makeText(getApplicationContext(),"tahmin değeri boş olamaz", Toast.LENGTH_SHORT).show();
                }
            }
        });


        txtharfAl.setText(ilboyutu);

        //illeri harflerine göre ayırıp yazdıık
        ilharfleri = new ArrayList<>();
        for (char c : gelenil.toCharArray())
            ilharfleri.add(c);

        // harf AL butonuna basınca tek tek harf versin
        btnharfal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ilharfleri.isEmpty()) {
                    rndnumberharf = rndharf.nextInt(ilharfleri.size());
                    char harf=ilharfleri.get(rndnumberharf);
                    ilharfleri.remove(rndnumberharf);

                    String[] txtharfler = txtİLbilgi.getText().toString().replace(" ", "").split("");
                    char[] gelenilharfler = gelenil.toCharArray();

                    for (int i = 0; i < gelenil.length(); i++) {
                        if (txtharfler[i].equals("_") && gelenilharfler[i] == ilharfleri.get(rndnumberharf)) {
                            txtharfler[i] = String.valueOf(ilharfleri.get(rndnumberharf));
                            ilboyutu = "";

                            for (int j = 0; j < gelenil.length(); j++) {
                                if (j == i)
                                    ilboyutu += txtharfler[j] + " "; // il boyutunun gelen index değerini harfle değiştirdi
                                else if (j < gelenil.length() - 1) // son değerine kadar gel bir tane alttire ve boşluk ekle
                                    ilboyutu += txtharfler[j] + " ";
                                else
                                    ilboyutu += txtharfler[j];
                            }

                        }

                    }
                    txtharfAl.setText(ilboyutu);
                    ilharfleri.remove(rndnumberharf);
                } else System.out.println("tüm harfler alındı");
            }


        });





    }


}







