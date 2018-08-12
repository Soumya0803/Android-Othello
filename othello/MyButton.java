package com.example.soumya.othello;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by Soumya on 19-06-2017.
 */

        public class MyButton extends Button {


            int x,y;
           private int player;
            public MyButton (Context context)
            {
                super(context);
            }

               public int getplayer()

            {
                return player;
            }

              public void setplayer(int player)
            {
                this.player=player;
            }



}
