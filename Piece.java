public class Piece {
    private boolean is_fire;
    private Board board;
    private String piecetype;
    private boolean is_king = false;
    private boolean has_captured = false;
    private int now_x;
    private int now_y;

    public Piece(boolean isFire, Board b, int x, int y, String type) {
        is_fire = isFire;
        board = b;
        now_x = x;
        now_y = y;
        piecetype = type;
    }
    

    public boolean isFire() {
        if (is_fire) {
            return true;
        } else {
            return false;
        }
    }

    public int side() {
        if (is_fire) {
            return 0;
        } else {
            return 1;
        }
    }

    public boolean isKing() {
        if (is_king) {
            return true;
        } else {
            return false;
        }
    }


    public boolean isBomb() {
        if (piecetype == "bomb") {
            return true;
        } else {
            return false;
        }
    }

    public boolean isShield() {
        if (piecetype == "shield") {
            return true;
        } else {
            return false;
        }
    }
    
    public void move(int x, int y) {
        board.place(this, x, y);
        board.remove(now_x, now_y);
        int x_dif = x - now_x;
        int y_dif = y - now_y;
        if (Math.abs(x_dif) == 2) {
            board.remove(now_x + x_dif/2, now_y + y_dif/2);
            has_captured = true;
            if (piecetype == "bomb") {
                for (int i = x - 1; i <= x + 1; i++) {
                    for (int j = y - 1; j <= y + 1; j++) {
                        if ((i>=0) && (i<8) && (j>=0) && (j<8) && (board.pieceAt(i, j) != null) && (!board.pieceAt(i, j).isShield())) {
                            board.remove(i, j);
                        }
                    }
                }
            }
        }
        if (is_fire) {
            if (y == 7) {
                is_king = true;
            }
        }
        if (!is_fire) {
            if (y == 0) {
                is_king = true;
            }
        }
        now_x = x;
        now_y = y;
    }

    public boolean hasCaptured() {
        return has_captured;
    } 

    public void doneCapturing() {
        has_captured = false;
    }
}