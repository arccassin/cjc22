import java.util.ArrayDeque;
import java.util.Objects;
public class ChessKnight {

    public static int countMoves(
            int width, int height,
            int startCol, int startRow,
            int endCol, int endRow) {
        final int movesX[] = {-2, -2, -1, -1, 1, 1, 2, 2};
        final int movesY[] = {-1, 1, -2, 2, -2, 2, -1, 1};
        Point start = new Point(0, 0);
        Point end = new Point(Math.abs(endCol - startCol), Math.abs(endRow - startRow));
        int newWidth = Math.abs(endCol - startCol) + 2;
        int newHeight = Math.abs(endRow - startRow) + 2;
        if (start.equals(end)) return 0;
        int[][] chessMas = new int[newWidth][newHeight];
        for (int i = 0; i < newWidth; i++) {
            for (int j = 0; j < newHeight; j++) {
                chessMas[i][j] = -1;
            }
        }
        chessMas[start.x][start.y] = 0;
        ArrayDeque<Point> queue = new ArrayDeque<Point>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Point cur = queue.pollFirst();
            for (int i = 0; i < 8; i++) {
                int x = cur.x + movesX[i];
                int y = cur.y + movesY[i];
                if (checkCorrect(x, y, newWidth, newHeight) && chessMas[x][y] == -1) {
                    chessMas[x][y] = chessMas[cur.x][cur.y] + 1;
                    if (end.equals(new Point(x, y))) {
                        return chessMas[end.x][end.y];
                    }
                    queue.add(new Point(x, y));
                }
            }
        }
        return -1;
    }

    static boolean checkCorrect(int x, int y, int n, int m) {
        if (x < 0 || y < 0)
            return false;
        if (x >= n || y >= m)
            return false;
        return true;
    }
}
