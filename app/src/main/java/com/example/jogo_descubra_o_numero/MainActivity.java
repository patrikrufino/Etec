package com.example.jogo_descubra_o_numero;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Minhas Variáveis
    TextView txtDicaProg;
    EditText edtValorProg;
    EditText edtValorOcultoProg;
    int valorOculto = gerarNumero();
    MediaPlayer mp;
    int vidas = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtDicaProg = findViewById(R.id.txtDica);
        edtValorProg = findViewById(R.id.edtValor);
        edtValorOcultoProg = findViewById(R.id.edtValorOculto);
    }

    public int gerarNumero()
    {
        Random gerarNumero = new Random();
        int numero = gerarNumero.nextInt(10) + 1;
        return numero;
    }

    public void clickBtnEnviar(View v)
    {
        int valor = Integer.parseInt(edtValorProg.getText().toString());

            if (vidas <= 1)
            {
                if (valor == valorOculto) {
                    txtDicaProg.setText("Parabéns! Você salvou o Astronauta!");
                    edtValorOcultoProg.setText("" + valorOculto);
                    mp = MediaPlayer.create(MainActivity.this, R.raw.aplauso);
                    mp.start();
                } else {
                    mp = MediaPlayer.create(MainActivity.this, R.raw.erro);
                    mp.start();
                    if (valor < valorOculto) {
                        txtDicaProg.setText("O valor oculto é maior que: " + valor);
                        edtValorProg.setText("");
                        edtValorProg.requestFocus();
                    } else {
                        txtDicaProg.setText("O valor oculto é menor que: " + valor);
                        edtValorProg.setText("");
                        edtValorProg.requestFocus();
                    }
                    vidas ++;
                }
            }
            else{
                mp = MediaPlayer.create(MainActivity.this, R.raw.gameover);
                mp.start();
                txtDicaProg.setText("O Astronauta morreu");
                vidas = 0;
            }

    }

    public void clickBtnNovo(View v)
    {
        txtDicaProg.setText("Valor de 1 até 10");
        edtValorProg.setText("");
        edtValorOcultoProg.setText("?");
        valorOculto = gerarNumero();
    }
}