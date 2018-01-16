package com.cleverchuk.tictactoe;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cleverchuk.tictactoe.databinding.ActivityGameBinding;

import java.io.IOException;

public class Game extends AppCompatActivity {
    private ActivityGameBinding binding;
    private MiniMaxAi ai;
    private char[][] board;
    private MediaPlayer player;
    private boolean gameState;
    private String rowOne = "board_row_1", rowTwo = "board_row_2", rowThree = "board_row_3";
    private String gameStateKey = "game_state", currentScore = "currentScore";
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_game);
        if (savedInstanceState != null) {
            board = new char[][]{savedInstanceState.getCharArray(rowOne),
                    savedInstanceState.getCharArray(rowTwo),
                    savedInstanceState.getCharArray(rowThree)
            };
            gameState = savedInstanceState.getBoolean(gameStateKey);
            score = savedInstanceState.getInt(currentScore);

            recover();
            startPlayer();
        } else
            board = new char[][]{
                    {' ', ' ', ' '},
                    {' ', ' ', ' '},
                    {' ', ' ', ' '}
            };

        binding.i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEmpty(v))
                    return;

                binding.i.setText(String.valueOf(ai.getOpponent()));
                board[0][0] = ai.getOpponent();
                int[] move = ai.findBestMove(board);

                aiPlay(move);
                endGame(board);
            }
        });

        binding.ii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEmpty(v))
                    return;

                binding.ii.setText(String.valueOf(ai.getOpponent()));
                board[0][1] = ai.getOpponent();
                int[] move = ai.findBestMove(board);

                aiPlay(move);
                endGame(board);
            }
        });

        binding.iii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEmpty(v))
                    return;

                binding.iii.setText(String.valueOf(ai.getOpponent()));
                board[0][2] = ai.getOpponent();
                int[] move = ai.findBestMove(board);

                aiPlay(move);
                endGame(board);
            }
        });

        binding.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEmpty(v))
                    return;

                binding.iv.setText(String.valueOf(ai.getOpponent()));
                board[1][0] = ai.getOpponent();
                int[] move = ai.findBestMove(board);

                aiPlay(move);
                endGame(board);
            }
        });
        binding.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEmpty(v))
                    return;
                binding.v.setText(String.valueOf(ai.getOpponent()));
                board[1][1] = ai.getOpponent();
                int[] move = ai.findBestMove(board);

                aiPlay(move);
                endGame(board);
            }
        });

        binding.vi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEmpty(v))
                    return;

                binding.vi.setText(String.valueOf(ai.getOpponent()));
                board[1][2] = ai.getOpponent();
                int[] move = ai.findBestMove(board);

                aiPlay(move);
                endGame(board);
            }
        });

        binding.vii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEmpty(v))
                    return;

                binding.vii.setText(String.valueOf(ai.getOpponent()));
                board[2][0] = ai.getOpponent();
                int[] move = ai.findBestMove(board);

                aiPlay(move);
                endGame(board);
            }
        });

        binding.viii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEmpty(v))
                    return;

                binding.viii.setText(String.valueOf(ai.getOpponent()));
                board[2][1] = ai.getOpponent();
                int[] move = ai.findBestMove(board);

                aiPlay(move);
                endGame(board);
            }
        });

        binding.ix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEmpty(v))
                    return;

                binding.ix.setText(String.valueOf(ai.getOpponent()));
                board[2][2] = ai.getOpponent();
                int[] move = ai.findBestMove(board);

                aiPlay(move);
                endGame(board);
            }
        });

        binding.start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.vs.showNext();
                startGame();
            }
        });
        updateScore(score);
    }

    private void startPlayer() {
        player = MediaPlayer.create(getApplicationContext(), R.raw.lovelace);
        player.setLooping(true);
        player.start();
    }

    private void recover() {
        if (gameState)
            binding.vs.showNext();

        binding.i.setText(String.valueOf(board[0][0]));
        binding.v.setText(String.valueOf(board[0][1]));
        binding.ix.setText(String.valueOf(board[0][2]));
        binding.ii.setText(String.valueOf(board[1][0]));
        binding.vi.setText(String.valueOf(board[1][1]));
        binding.iii.setText(String.valueOf(board[1][2]));
        binding.vii.setText(String.valueOf(board[2][0]));
        binding.iv.setText(String.valueOf(board[2][1]));
        binding.viii.setText(String.valueOf(board[2][2]));
    }

    private void aiPlay(int[] move) {
        if (move[0] == 0 && move[1] == 0) {
            binding.i.setText(String.valueOf(ai.getPlayer()));
            board[move[0]][move[1]] = ai.getPlayer();
        } else if (move[0] == 0 && move[1] == 1) {
            binding.ii.setText(String.valueOf(ai.getPlayer()));
            board[move[0]][move[1]] = ai.getPlayer();
        } else if (move[0] == 0 && move[1] == 2) {
            binding.iii.setText(String.valueOf(ai.getPlayer()));
            board[move[0]][move[1]] = ai.getPlayer();
        } else if (move[0] == 1 && move[1] == 0) {
            binding.iv.setText(String.valueOf(ai.getPlayer()));
            board[move[0]][move[1]] = ai.getPlayer();
        } else if (move[0] == 1 && move[1] == 1) {
            binding.v.setText(String.valueOf(ai.getPlayer()));
            board[move[0]][move[1]] = ai.getPlayer();
        } else if (move[0] == 1 && move[1] == 2) {
            binding.vi.setText(String.valueOf(ai.getPlayer()));
            board[move[0]][move[1]] = ai.getPlayer();
        } else if (move[0] == 2 && move[1] == 0) {
            binding.vii.setText(String.valueOf(ai.getPlayer()));
            board[move[0]][move[1]] = ai.getPlayer();
        } else if (move[0] == 2 && move[1] == 1) {
            binding.viii.setText(String.valueOf(ai.getPlayer()));
            board[move[0]][move[1]] = ai.getPlayer();
        } else if (move[0] == 2 && move[1] == 2) {
            binding.ix.setText(String.valueOf(ai.getPlayer()));
            board[move[0]][move[1]] = ai.getPlayer();
        }
    }

    private boolean isEmpty(View view) {
        return ((Button) view).getText().toString().isEmpty();
    }

    private void endGame(char[][] board) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Announce Winner")
                .setCancelable(false)
                .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        restartGame();
                        updateScore(score);
                    }
                })
                .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        quit();
                        updateScore(score);
                    }
                });
        int heuristicsValue = ai.heuristics(board);
        if (heuristicsValue == MiniMaxAi.playerHeuristicValue) {
            builder.setMessage("You lose!")
                    .create()
                    .show();
            score -= 2;
        } else if (heuristicsValue == MiniMaxAi.opponentHeuristicValue) {
            builder.setMessage("You Win!")
                    .create()
                    .show();
            score += 2;
        } else if (heuristicsValue == 0) {
            builder.setMessage("Draw!!")
                    .create()
                    .show();
            ++score;
        }
    }

    private void restartPlayer() {
        if (player.isPlaying()) {
            player.stop();
            try {
                player.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            player.start();
        } else
            player.start();
    }

    private void restartGame() {
        restartPlayer();
        board = new char[][]{
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };

        binding.i.setText("");
        binding.v.setText("");
        binding.ix.setText("");
        binding.ii.setText("");
        binding.vi.setText("");
        binding.iii.setText("");
        binding.vii.setText("");
        binding.iv.setText("");
        binding.viii.setText("");
    }

    private void stopPlayer() {
        player.stop();
        player.release();
        player = null;
    }

    private void quit() {
        binding.vs.showPrevious();
        stopPlayer();
    }

    private void updateScore(int score) {
        String s = getText(R.string.score).toString() + "  " + score;
        binding.score.setText(s);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharArray(rowOne, board[0]);
        outState.putCharArray(rowTwo, board[1]);
        outState.putCharArray(rowThree, board[2]);
        outState.putBoolean(gameStateKey, gameState);
        outState.putInt(currentScore, score);
    }

    private void startGame() {

        final EditText et = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Type your Identifier")
                .setView(et)
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String id = et.getText().toString();
                        if (id.isEmpty()) {
                            ai = new MiniMaxAi('o', 'x');
                            Toast.makeText(getApplicationContext(), "Your ID: X", Toast.LENGTH_SHORT)
                                    .show();
                        } else {
                            char opponent = id.charAt(0);
                            char player = (char) (opponent + 1);
                            ai = new MiniMaxAi(player, opponent);
                        }


                    }
                })
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        gameState = true;
                        restartGame();
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences preferences = getPreferences(0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(currentScore, score);
        editor.apply();
    }

    @Override
    protected void onStart() {
        super.onStart();
        score = getPreferences(0).getInt(currentScore, 0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopPlayer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (player != null)
            restartPlayer();
        else
            startPlayer();
    }
}
