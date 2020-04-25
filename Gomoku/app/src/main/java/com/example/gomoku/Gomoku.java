package com.example.gomoku;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Gomoku extends Activity {

    final static int N=15;
    private Context context;
    private ImageView[][] ivCell = new ImageView[N][N];
    private Drawable[] drawCell = new Drawable[4];
    private Button newgame;
    private TextView tvTurn;
    private int[][] valueCell = new int[N][N];
    private int winner_play;
    private boolean firstMove;
    private int xMove,yMove;
    private int turnPlay;
    private boolean isClicked=true;
    private int who_starts=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gomoku);
        context = this;
        setListen();
        loadResources();
        newgame = (Button) findViewById(R.id.newgameButton);
        designBoardGame();
    }

    private void setListen() {
        newgame = (Button) findViewById(R.id.newgameButton);
        tvTurn = (TextView) findViewById(R.id.turnTextView);

        newgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init_game();
                play_game();
            }
        });
    }

    private void init_game() {
        firstMove = true;
        winner_play=0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                ivCell[i][j].setImageDrawable(drawCell[0]);
                valueCell[i][j] = 0;
            }
        }
    }

    private void play_game() {
        if(who_starts == 1) {
            who_starts = 2;
            Toast.makeText(context,"Player 2 goes first!", Toast.LENGTH_SHORT).show();
            turnPlay = 2;
            player2Turn();
        }
        else {
            who_starts = 1;
            Toast.makeText(context,"Player 1 goes first!", Toast.LENGTH_SHORT).show();
            turnPlay = 1;
            player1Turn();
        }

    }

    private void player1Turn() {
        tvTurn.setText("Player 1's Turn");
        isClicked = false;
    }

    private void player2Turn() {
        tvTurn.setText("Player 2's Turn");
        isClicked = false;
    }

    private void make_a_move() {
        ivCell[xMove][yMove].setImageDrawable(drawCell[turnPlay]);
        valueCell[xMove][yMove]=turnPlay;

    }

//    private boolean checkWinner() {
//        if(winner_play!=0) return true;
//        VectorEnd(xMove,0,0,1,xMove,yMove);
//        return false;
//    }
//
//    private void VectorEnd(int xx, int yy, int vx, int vy, int rx, int ry) {
//        if (winner_play != 0) return;
//        final int range = 4;
//        int i, j;
//        int xbelow = rx - range * vx;
//        int ybelow = ry - range * vy;
//        int xabove = rx + range * vx;
//        int yabove = rx + range * vy;
//        String st = "";
//        i = xx;
//        j = yy;
//        while (!inside(i, xbelow, xabove) || !inside(j, ybelow, yabove)) {
//            i += vx;
//            j += vy;
//        }
//        while(true) {
//            st = st + String.valueOf(valueCell[i][j]);
//            if (st.length() == 5) {
//                EvalEnd(st);
//                st = st.substring(1, 5);
//            }
//            i+=vx;j+=vy;
//            if(!inBoard(i,j) || !inside(i,xbelow,xabove) || !inside(j,xbelow,xabove) || winner_play!=0) {
//                break;
//            }
//        }
//    }
//
//    private boolean inBoard(int i, int j) {
//        if (i < 0 || i > N - 1 || j < 0 || j > N - 1) return false;
//        return true;
//    }
//
//    private void EvalEnd(String st) {
//        switch(st) {
//            case "11111":
//                winner_play = 1;
//                break;
//            case "22222":
//                winner_play = 2;
//                break;
//            default:
//                break;
//        }
//    }
//
//    private boolean inside(int i, int xbelow, int xabove) {
//        return (i - xbelow) * (i - xabove) <= 0;
//    }
//
    private boolean noEmptycell() {
        for (int i = 0; i<N ; i++) {
            for(int j = 0; j<N; j++) {
                if(valueCell[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkWinner() {

        // vertical
        int i=xMove;
        int j=yMove;
        int consec=0;
        while(valueCell[i][j]==turnPlay && consec<5){
            consec++;
            if(i>0) {
                i--;
            }
            else {
                break;
            }
        }
        if(xMove<N-1){
            i=xMove+1;
        }

        while(valueCell[i][j]==turnPlay && consec<5){
            consec++;
            if(i<N-1) {
                i++;
            }
            else {
                break;
            }
        }
        if(consec>=5) {
            winner_play=turnPlay;
            return true;
        }

        // horizontal
        i=xMove;
        j=yMove;
        consec=0;
        while(valueCell[i][j]==turnPlay && consec<5){
            consec++;
            if(j>0) {
                j--;
            }
            else {
                break;
            }
        }
        if(yMove<N-1){
            j=yMove+1;
        }

        while(valueCell[i][j]==turnPlay && consec<5){
            consec++;
            if(j<N-1) {
                j++;
            }
            else {
                break;
            }
        }
        if(consec>=5) {
            winner_play=turnPlay;
            return true;
        }

        // Diag 1
        i=xMove;
        j=yMove;
        consec=0;
        while(valueCell[i][j]==turnPlay && consec<5){
            consec++;
            if(j>0 && i>0) {
                j--;
                i--;
            }
            else {
                break;
            }
        }
        if(yMove<N-1 && xMove<N-1){
            j=yMove+1;
            i=xMove+1;
        }

        while(valueCell[i][j]==turnPlay && consec<5){
            consec++;
            if(j<N-1 && i<N-1) {
                j++;
                i++;
            }
            else {
                break;
            }
        }
        if(consec>=5) {
            winner_play=turnPlay;
            return true;
        }

        // Diag 2
        i=xMove;
        j=yMove;
        consec=0;
        while(valueCell[i][j]==turnPlay && consec<5){
            consec++;
            if(j>0 && i<N-1) {
                j--;
                i++;
            }
            else {
                break;
            }
        }
        if(yMove<N-1 && xMove>0){
            j=yMove+1;
            i=xMove-1;
        }

        while(valueCell[i][j]==turnPlay && consec<5){
            consec++;
            if(j<N-1 && i>0) {
                j++;
                i--;
            }
            else {
                break;
            }
        }
        if(consec>=5) {
            winner_play=turnPlay;
            return true;
        }

        return false;

    }

    private void loadResources() {
        drawCell[3] = context.getResources().getDrawable(R.drawable.board_bg);
        drawCell[0] = null;
        drawCell[1] = context.getResources().getDrawable(R.drawable.black_stone);
        drawCell[2] = context.getResources().getDrawable(R.drawable.white_stone);
    }

    private void designBoardGame() {

        int sizeofCell = Math.round(ScreenWidth()/N);
        LinearLayout.LayoutParams lpRow = new LinearLayout.LayoutParams(sizeofCell*N, sizeofCell);
        LinearLayout.LayoutParams lpCell = new LinearLayout.LayoutParams(sizeofCell,sizeofCell);

        LinearLayout linBoardGame = (LinearLayout) findViewById(R.id    .linBoardGame);

        for(int i = 0; i<N; i++) {
            LinearLayout linRow = new LinearLayout(context);
            for (int j = 0; j < N; j++) {
                ivCell[i][j] = new ImageView(context);
                ivCell[i][j].setBackground(drawCell[3]);
                final int x = i;
                final int y = j;
                ivCell[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(valueCell[x][y]==0 && winner_play==0) {
                            if (!isClicked) {
                                isClicked = true;
                                xMove = x;
                                yMove = y;
                                make_a_move();
                                if(turnPlay != 0) {
                                    checkWinner();
                                    if (winner_play == 1) {
                                        tvTurn.setText("Player 1 Wins!");
                                    } else if (winner_play == 2) {
                                        tvTurn.setText("Player 2 Wins!");
                                    }
                                    if (noEmptycell()) {
                                        tvTurn.setText("It's a draw!");
                                    }
                                }
                                if (turnPlay==1 && winner_play==0) {
                                    turnPlay = 2;
                                    player2Turn();
                                }
                                else if(turnPlay==2 && winner_play==0){
                                    turnPlay = 1;
                                    player1Turn();
                                }
                            }
                        }
                    }
                });
                linRow.addView(ivCell[i][j],lpCell);
            }
            linBoardGame.addView(linRow,lpRow);
        }
    }

    private float ScreenWidth() {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

}