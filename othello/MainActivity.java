package com.example.soumya.othello;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener //implements View.OnClickListener
 {
    MyButton buttons[][];
    LinearLayout rows[];
    LinearLayout mainLayout;
     public int player=PLAYER1;
    public final static int NOPLAYER=0;
    public final static int PLAYER1=1;
    public final static int PLAYER2=2;
    public final static int PLAYER1WINS=1;
    public final static int PLAYER2WINS=2;
    public final static int INCOMPLETE=0;
     public final static int DRAW=3;
    int n=8;
     int q;
     public String currentcolor;
    // public String oppcoolor;
    //public boolean player1turn;
    public boolean gameover;
    public int whitecount=0;//player2
     public int blackcount=0;//player1

     //int winner;




     @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            mainLayout = (LinearLayout) findViewById(R.id.activity_main);
            setUpBoard();
        }

        private void setUpBoard() {
            //player1turn = true;
            player=PLAYER1;
            buttons = new MyButton[n][n];
            rows = new LinearLayout[n];
            gameover = false;
            mainLayout.removeAllViews();
            whitecount=0;
            blackcount=0;
            currentcolor="B";
            for (int i = 0; i < n; i++) {
                rows[i] = new LinearLayout(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
                params.setMargins(1, 1, 1, 1);
                rows[i].setLayoutParams(params);
                rows[i].setOrientation(LinearLayout.HORIZONTAL);
                mainLayout.addView(rows[i]);

            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    buttons[i][j] = new MyButton(this);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);

                    params.setMargins(1, 1, 1, 1);
                    buttons[i][j].setLayoutParams(params);
                    buttons[i][j].setText("");
                    buttons[i][j].setplayer(NOPLAYER);
                   // buttons[i][j].setOnClickListener(this);
                    rows[i].addView(buttons[i][j]);

                    buttons[3][3].setText("W");
                    buttons[3][4].setText("B");
                    buttons[4][3].setText("B");
                    buttons[4][4].setText("W");
                    buttons[i][j].setOnClickListener(this);


                }
            }


        }

      public int winner()
      {
          for(int i=0; i<n; i++ ) {
              for (int j = 0; j < n; j++) {
                  if (buttons[i][j].getText() == "B")
                      blackcount++;

                  else if (buttons[i][j].getText() == "W") {
                      whitecount++;
                  }
              }
          }
          if(blackcount > whitecount)
              {
                  return PLAYER1WINS;
              }

              else if (blackcount < whitecount) {
                  return PLAYER2WINS;
              }
              return DRAW;

      }
        public boolean inrange(int x2,int y2,int n)
 {
     if(x2>0 && x2<n && y2>0 &&y2<n)
     {
         return true;
     }
     return false;
 }

      public void makeamove(int player,MyButton p)
     {
         int i=p.x;
         int j=p.y;
         int [] steprow={-1,0,1,1,1,0-1,-1};
         int [] stepcol={-1,-1,-1,0,1,1,1,0};
         if(player==PLAYER1)
         {
             p.setText("B");
         }
         else
         {
             p.setText("W");
         }
         for( q=0; q<8; q++ )
         {
             int x2=i+steprow[q];
             int y2=j+stepcol[q];
             if(inrange(x2,y2,n))
             {
                 flip(x2,y2,q);
             }

         }

         if(player==PLAYER1)
         {
             player=PLAYER2;
             currentcolor="W";
         }
         else
         {
             player=PLAYER2;
             currentcolor="W";
         }
         // call hint
     }


     public void flip(int x2,int y2,int q)//here q moves anticlockwise starting from left
     // diagonal and checks in coresspondin row,col,diagonal
     {
          int end=-1;
         int end2=-1;

         if(buttons[x2][y2].getText()=="" )
         {
             return;
         }

         if(q==5)   //checking in row towards right
         {

            for(int i=y2 ;i<n; i++)
            {
                if(buttons[x2][i].getText().toString()==currentcolor)
                {
                    end=i;
                    break;
                }
            }

             if(end==-1)
             {
                 return;
             }
             else
             {
                 for(int m=end; m>=y2; m--)
                 {
                     buttons[x2][m].setText(currentcolor);
                 }
                 end=-1;
             }

         }


         if(q==3) ////checking in column downwards
         {

             for(int i=x2 ;i<n; i++)
             {
                 if(buttons[i][y2].getText().toString()==currentcolor)
                 {
                     end=i;
                     break;
                 }
             }

             if(end==-1)
             {
                 return;
             }
             else
             {
                 for(int m=end; m>=x2; m--)
                 {
                     buttons[m][y2].setText(currentcolor);
                 }
                 end=-1;
             }

         }




         if(q==1)//for row towards left
         {

             for(int i=y2;i>=0; i--)
             {
                 if(buttons[x2][i].getText().toString()==currentcolor)
                 {
                     end=i;
                     break;
                 }
             }

             if(end==-1)
             {
                 return;
             }
             else
             {
                 for(int m=end; m<=y2; m++)
                 {
                     buttons[x2][m].setText(currentcolor);
                 }
                 end=-1;
             }

         }

         if(q==7) ////checking in column upwards
         {

             for(int i=x2 ;i>=0; i--)
             {
                 if(buttons[i][y2].getText().toString()==currentcolor)
                 {
                     end=i;
                     break;
                 }
             }

             if(end==-1)
             {
                 return;
             }
             else
             {
                 for(int m=end; m<x2; m++)
                 {
                     buttons[m][y2].setText(currentcolor);
                 }
                 end=-1;
             }

         }
         if(q==0) {
             for (int i = x2; i >= 0; i--) {
                 if (buttons[i][i].getText().toString() == currentcolor) {
                     end = i;
                     end2 = i;
                     break;
                 }
             }

             if (end == -1 && end2 == -1) {
                 return;
             } else {
                 for (int m = end; m <= x2; m++) {
                     buttons[m][m].setText(currentcolor);
                 }
                 end=-1;
                 end2=-1;
             }
         }



         if(q==7)
         {

             for(int i=x2 ;i<n; i++)
             {
                 if(buttons[i][i].getText().toString()==currentcolor)
                 {
                     end=i;
                     end2=i;
                     break;
                 }
             }

             if(end==-1 && end2==-1)
             {
                 return;
             }
             else
             {
                 for(int m=end; m>=x2; m--)
                 {
                     buttons[m][m].setText(currentcolor);
                 }
                 end=-1;
                 end=-1;

             }

         }
         if(q==2) {
             for (int i = x2,j=y2; i <=n && j>=0 ; i++,j--) {
                 if (buttons[i][j].getText().toString() == currentcolor) {
                     end = i;
                     end2 = i;
                     break;
                 }
             }

             if (end == -1 && end2 == -1) {
                 return;
             } else {
                 for (int m =end,n=end2; m>=x2 && n<=y2; m--,n++) {
                     buttons[m][n].setText(currentcolor);
                 }
                 end=-1;
                 end2=-1;
             }
         }

         if(q==6) {
             for (int i = x2,j=y2; i >=0 && j<n ; i--,j++) {
                 if (buttons[i][j].getText().toString() == currentcolor) {
                     end = i;
                     end2 = i;
                     break;
                 }
             }

             if (end == -1 && end2 == -1) {
                 return;
             } else {
                 for (int m =end,n=end2; m<=x2 & n>=y2; m++,n--) {
                     buttons[m][n].setText(currentcolor);
                 }
                 end=-1;
                 end2=-1;
             }
         }




     }
     public boolean isComplete()
     {
         for(int i=0; i< n; i++)
             for(int j=0;j<n;j++){
                if ( buttons[i][j].getText()==""){
                    return  false;
                }
             }

         return true;
     }
     @Override
     public void onClick(View view) {
         MyButton b=(MyButton) view;
         if(isComplete())
         {
             int i=winner();

                 if(i==PLAYER1WINS)
                     Toast.makeText(this,"Player 1 wins",Toast.LENGTH_SHORT).show();
             if(i==PLAYER2WINS)
                 Toast.makeText(this,"Player 1 wins",Toast.LENGTH_SHORT).show();
             if(i==DRAW)
                 Toast.makeText(this,"Player 1 wins",Toast.LENGTH_SHORT).show();


         }




     }

   /* @Override
    public void onClick(View view) {

    }*/
}

