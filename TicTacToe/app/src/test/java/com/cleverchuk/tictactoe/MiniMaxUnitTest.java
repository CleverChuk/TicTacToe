package com.cleverchuk.tictactoe;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by root on 1/10/18.
 * Domain ${DOMAIN}
 */


public class MiniMaxUnitTest {
    char[][] board0 = {
            {'o', 'x', 'x'},
            {'x', 'o', 'o'},
            {'o', 'x', 'x'}
    };

    char[][] board1 = {
            {'o', 'o', '-'},
            {'x', 'x', '-'},
            {'-', '-', '-'}
    };
    char[][] board2 = {
            {'o', 'x', 'x'},
            {'o', 'o', '-'},
            {'x', 'x', '-'}
    };
    char[][] board3 = {
            {'o', 'x', 'o'},
            {'x', 'o', '-'},
            {'x', 'x', '-'}
    };

    MiniMaxAi miniMaxAi = new MiniMaxAi('o', 'x');

    @Test
    public void test() {
        int[] expectedMove = {1,2};
        int[] actualMove = miniMaxAi.findBestMove(board2);

        Assert.assertArrayEquals(expectedMove, actualMove);
    }
}
