package com.example.tic_tac_toe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Color
import android.view.View
import android.widget.Button
import android.widget.Toast

// TODO - Get PVC working
// TODO - clean up code
// TODO - clean up styling

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private var player1 = ArrayList<Int>()
    private var player2 = ArrayList<Int>()
    private var activePlayer = 1
    private var setPlayer = 1

    fun restartGame(view: View) {
        var button1 = findViewById<Button>(R.id.button1);
        var button2 = findViewById<Button>(R.id.button2);
        var button3 = findViewById<Button>(R.id.button3);
        var button4 = findViewById<Button>(R.id.button4);
        var button5 = findViewById<Button>(R.id.button5);
        var button6 = findViewById<Button>(R.id.button6);
        var button7 = findViewById<Button>(R.id.button7);
        var button8 = findViewById<Button>(R.id.button8);
        var button9 = findViewById<Button>(R.id.button9);
        var pvp = findViewById<Button>(R.id.PVP);
        var pvc = findViewById<Button>(R.id.PVC);

        button1.setBackgroundResource(android.R.drawable.btn_default)
        button2.setBackgroundResource(android.R.drawable.btn_default)
        button3.setBackgroundResource(android.R.drawable.btn_default)
        button4.setBackgroundResource(android.R.drawable.btn_default)
        button5.setBackgroundResource(android.R.drawable.btn_default)
        button6.setBackgroundResource(android.R.drawable.btn_default)
        button7.setBackgroundResource(android.R.drawable.btn_default)
        button8.setBackgroundResource(android.R.drawable.btn_default)
        button9.setBackgroundResource(android.R.drawable.btn_default)

        button1.text = ""
        button2.text = ""
        button3.text = ""
        button4.text = ""
        button5.text = ""
        button6.text = ""
        button7.text = ""
        button8.text = ""
        button9.text = ""

        player1.clear()
        player2.clear()
        activePlayer = 1

        button1.isEnabled = true
        button2.isEnabled = true
        button3.isEnabled = true
        button4.isEnabled = true
        button5.isEnabled = true
        button6.isEnabled = true
        button7.isEnabled = true
        button8.isEnabled = true
        button9.isEnabled = true

        setPlayer = 1
        pvp.setBackgroundColor(Color.CYAN)
        pvc.setBackgroundColor(android.R.drawable.btn_default)
    }

    fun btnClick(view: View) {
        val selectedButton = view as Button
        var cellID = 0

        when (selectedButton.id) {
            R.id.button1 -> cellID = 1
            R.id.button2 -> cellID = 2
            R.id.button3 -> cellID = 3
            R.id.button4 -> cellID = 4
            R.id.button5 -> cellID = 5
            R.id.button6 -> cellID = 6
            R.id.button7 -> cellID = 7
            R.id.button8 -> cellID = 8
            R.id.button9 -> cellID = 9
        }

        playGame(cellID, selectedButton)
    }

    fun playerChoose(view:View) {
        val ps:Button = view as Button
        var pvp = findViewById<Button>(R.id.PVP);
        var pvc = findViewById<Button>(R.id.PVC);

        when(ps.id) {
            R.id.PVP -> {
                setPlayer = 1
                ps.setBackgroundColor(Color.CYAN)
                pvc.setBackgroundColor(android.R.drawable.btn_default)
            }
            R.id.PVC -> {
                setPlayer = 2
                ps.setBackgroundColor(Color.CYAN)
                pvp.setBackgroundColor(android.R.drawable.btn_default)
            }
        }
    }

    private fun playGame(cellId: Int, selectedButton: Button) {
        if (activePlayer == 1) {
            selectedButton.text = "X"
            selectedButton.setBackgroundColor(Color.GREEN)
            player1.add(cellId)
            activePlayer = 2
        } else {
            selectedButton.text = "O"
            selectedButton.setBackgroundColor(Color.BLUE)
            player2.add(cellId)
            activePlayer = 1
        }

        selectedButton.isEnabled = false
        checkWinner()

        if (checkWinner() == 1) {
            stopTouch()
        }
    }

    private fun checkWinner (): Int {
        var winner = -1

        // Player 1
        var row1Player1 = player1.contains(1) && player1.contains(2) && player1.contains(3)
        var row2Player1 = player1.contains(4) && player1.contains(5) && player1.contains(6)
        var row3Player1 = player1.contains(7) && player1.contains(8) && player1.contains(9)
        var col1Player1 = player1.contains(1) && player1.contains(4) && player1.contains(7)
        var col2Player1 = player1.contains(2) && player1.contains(5) && player1.contains(8)
        var col3Player1 = player1.contains(3) && player1.contains(6) && player1.contains(9)
        var diag1Player1 = player1.contains(1) && player1.contains(5) && player1.contains(9)
        var diag2Player1 = player1.contains(3) && player1.contains(5) && player1.contains(7)

        // Player 2
        var row1Player2 = player2.contains(1) && player2.contains(2) && player2.contains(3)
        var row2Player2 = player2.contains(4) && player2.contains(5) && player2.contains(6)
        var row3Player2 = player2.contains(7) && player2.contains(8) && player2.contains(9)
        var col1Player2 = player2.contains(1) && player2.contains(4) && player2.contains(7)
        var col2Player2 = player2.contains(2) && player2.contains(5) && player2.contains(8)
        var col3Player2 = player2.contains(3) && player2.contains(6) && player2.contains(9)
        var diag1Player2 = player2.contains(1) && player2.contains(5) && player2.contains(9)
        var diag2Player2 = player2.contains(3) && player2.contains(5) && player2.contains(7)

        if (row1Player1 || row2Player1 || row3Player1 ||
            col1Player1 || col2Player1 || col3Player1 ||
            diag1Player1 || diag2Player1) {
            winner = 1
        }

        if (row1Player2 || row2Player2 || row3Player2 ||
            col1Player2 || col2Player2 || col3Player2 ||
            diag1Player2 || diag2Player2) {
            winner = 2
        }

        if (winner != -1) {
            if (winner == 1) {
                Toast.makeText(this,"Player 1 win",Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Player 2 win", Toast.LENGTH_LONG).show()
            }
        }

        return winner;
    }

    private fun stopTouch() {
        var button1 = findViewById<Button>(R.id.button1);
        var button2 = findViewById<Button>(R.id.button2);
        var button3 = findViewById<Button>(R.id.button3);
        var button4 = findViewById<Button>(R.id.button4);
        var button5 = findViewById<Button>(R.id.button5);
        var button6 = findViewById<Button>(R.id.button6);
        var button7 = findViewById<Button>(R.id.button7);
        var button8 = findViewById<Button>(R.id.button8);
        var button9 = findViewById<Button>(R.id.button9);

        button1.isEnabled = false
        button2.isEnabled = false
        button3.isEnabled = false
        button4.isEnabled = false
        button5.isEnabled = false
        button6.isEnabled = false
        button7.isEnabled = false
        button8.isEnabled = false
        button9.isEnabled = false
    }
}
