package ohtu;

public class TennisGame {

    private static final int POINTS_TO_WIN = 4, ADVANTAGE = 1, WIN = 2;
    private int player1 = 0;
    private int player2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void pointWinner(String playerName) {
        if (playerName == "player1")
            player1 += 1;
        else
            player2 += 1;
    }

    private String pointsToScoreText(int points) {
        switch (points) {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
            default:
                return "";
        }

    }

    private String advantageOrWin() {
        String score;
        int minusResult = player1 - player2;
        if (minusResult == ADVANTAGE) {
            score = "Advantage player1";
        } else if (minusResult * -1 == ADVANTAGE) {
            score = "Advantage player2";
        } else if (minusResult >= WIN) {
            score = "Win for player1";
        } else {
            score = "Win for player2";
        }

        return score;
    }
    
    private boolean eitherOneAboutToWin() {
        return player1 >= POINTS_TO_WIN || player2 >= POINTS_TO_WIN;
    }

    public String getScore() {
        String score = "";
        if (player1 == player2) {
            if (player1 < 4) {
                score = pointsToScoreText(player1) + "-All";
            } else {
                score = "Deuce";
            }
        } else if (eitherOneAboutToWin()) {
            score = advantageOrWin();
        } else {
            score = pointsToScoreText(player1) + "-" + pointsToScoreText(player2);
        }
        return score;
    }
}