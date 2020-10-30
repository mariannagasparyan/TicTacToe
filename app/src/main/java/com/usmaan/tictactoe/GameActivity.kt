package com.usmaan.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat

class GameActivity : AppCompatActivity() {

    private lateinit var gameManager: GameManager
    private lateinit var one: TextView
    private lateinit var two: TextView
    private lateinit var three: TextView
    private lateinit var four: TextView
    private lateinit var five: TextView
    private lateinit var six: TextView
    private lateinit var seven: TextView
    private lateinit var eight: TextView
    private lateinit var nine: TextView
    private lateinit var startNewGameButton: Button
    private lateinit var player1Points: TextView
    private lateinit var player2Points: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        gameManager = GameManager()

        one = findViewById(R.id.one)
        two = findViewById(R.id.two)
        three = findViewById(R.id.three)
        four = findViewById(R.id.four)
        five = findViewById(R.id.five)
        six = findViewById(R.id.six)
        seven = findViewById(R.id.seven)
        eight = findViewById(R.id.eight)
        nine = findViewById(R.id.nine)
        startNewGameButton = findViewById(R.id.start_new_game_button)
        player1Points = findViewById(R.id.player_one_score)
        player2Points = findViewById(R.id.player_two_score)

        one.setOnClickListener { onButtonClicked(one, Position(0, 0)) }
        two.setOnClickListener { onButtonClicked(two, Position(0, 1)) }
        three.setOnClickListener { onButtonClicked(three, Position(0, 2)) }
        four.setOnClickListener { onButtonClicked(four, Position(1, 0)) }
        five.setOnClickListener { onButtonClicked(five, Position(1, 1)) }
        six.setOnClickListener { onButtonClicked(six, Position(1, 2)) }
        seven.setOnClickListener { onButtonClicked(seven, Position(2, 0)) }
        eight.setOnClickListener { onButtonClicked(eight, Position(2, 1)) }
        nine.setOnClickListener { onButtonClicked(nine, Position(2, 2)) }

        startNewGameButton.setOnClickListener {
            startNewGameButton.visibility = View.GONE
            gameManager.reset()
            resetButton()
        }

        updatePoints()
    }

    private fun updatePoints() {
        player1Points.text = "Player 1 Points: ${gameManager.player1Points}"
        player2Points.text = "Player 2 Points: ${gameManager.player2Points}"
    }


    private fun resetButton() {
        one.text = ""
        two.text = ""
        three.text = ""
        four.text = ""
        five.text = ""
        six.text = ""
        seven.text = ""
        eight.text = ""
        nine.text = ""
        one.background = null
        two.background = null
        three.background = null
        four.background = null
        five.background = null
        six.background = null
        seven.background = null
        eight.background = null
        nine.background = null
        one.isEnabled = true
        two.isEnabled = true
        three.isEnabled = true
        four.isEnabled = true
        five.isEnabled = true
        six.isEnabled = true
        seven.isEnabled = true
        eight.isEnabled = true
        nine.isEnabled = true
    }

    private fun onButtonClicked(button: TextView, position: Position) {
        if (button.text.isEmpty()) {
            button.text = gameManager.currentPlayerMark
            val winningLine = gameManager.makeMove(position)
            if (winningLine != null) {
                updatePoints()
                disableButtons()
                startNewGameButton.visibility = View.VISIBLE
                showWinner(winningLine)
            }
        }
    }

    private fun disableButtons() {
        one.isEnabled = false
        two.isEnabled = false
        three.isEnabled = false
        four.isEnabled = false
        five.isEnabled = false
        six.isEnabled = false
        seven.isEnabled = false
        eight.isEnabled = false
        nine.isEnabled = false
    }

    private fun showWinner(winningLine: WinningLine) {
        val (winningButtons, background) = when (winningLine) {
            WinningLine.ROW_0 -> Pair(listOf(one, two, three), R.drawable.horizontal_line)
            WinningLine.ROW_1 -> Pair(listOf(four, five, six), R.drawable.horizontal_line)
            WinningLine.ROW_2 -> Pair(listOf(seven, eight, nine), R.drawable.horizontal_line)
            WinningLine.COLUMN_0 -> Pair(listOf(one, four, seven), R.drawable.vertical_line)
            WinningLine.COLUMN_1 -> Pair(listOf(two, five, eight), R.drawable.vertical_line)
            WinningLine.COLUMN_2 -> Pair(listOf(three, six, nine), R.drawable.vertical_line)
            WinningLine.DIAGONAL_LEFT -> Pair(listOf(one, five, nine), R.drawable.left_diagonal_line)
            WinningLine.DIAGONAL_RIGHT -> Pair(listOf(three, five, seven), R.drawable.right_diagonal_line)
        }

        winningButtons.forEach { button ->
            button.background = ContextCompat.getDrawable(GameActivity@ this, background)
        }
    }
}