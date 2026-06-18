import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MatrixProgram {

    // Instance variable untuk menyimpan state dari matriks (pengganti self.matrix)
    private int[][] matrix;
    private Scanner scanner;

    // Constructor (pengganti def __init__(self))
    public MatrixProgram() {
        this.scanner = new Scanner(System.in);
    }

    // ==========================
    // INPUT MATRIX
    // ==========================
    public void inputMatrix() {
        int rows = 0;
        int cols = 0;

        while (true) {
            try {
                System.out.print("Masukkan jumlah baris : ");
                rows = Integer.parseInt(scanner.nextLine().trim());
                System.out.print("Masukkan jumlah kolom : ");
                cols = Integer.parseInt(scanner.nextLine().trim());
                if (rows > 0 && cols > 0)
                    break;
                System.out.println("Dimensi harus lebih dari 0!");
            } catch (NumberFormatException e) {
                System.out.println("Input harus angka!");
            }
        }

        // Inisialisasi ukuran matriks di memori
        this.matrix = new int[rows][cols];
        System.out.println("\nMasukkan elemen matrix:");

        for (int i = 0; i < rows; i++) {
            while (true) {
                try {
                    System.out.print("Baris " + (i + 1) + ": ");
                    String[] parts = scanner.nextLine().trim().split("\\s+");

                    if (parts.length != cols) {
                        System.out.println("Harus " + cols + " angka!");
                        continue;
                    }

                    for (int j = 0; j < cols; j++) {
                        this.matrix[i][j] = Integer.parseInt(parts[j]);
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Masukkan angka saja!");
                }
            }
        }
    }

    // ==========================
    // TAMPILKAN MATRIX
    // ==========================
    public void printMatrix() {
        System.out.println("\nMatrix:");
        if (this.matrix == null || this.matrix.length == 0) {
            System.out.println("(Kosong)");
            return;
        }
        for (int[] row : this.matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // ==========================
    // 1-a SORT ROW WISE
    // ==========================
    public void sortRowWise() {
        for (int i = 0; i < this.matrix.length; i++) {
            Arrays.sort(this.matrix[i]);
        }
        System.out.println("\nMatrix setelah sort row-wise:");
        printMatrix();
    }

    // ==========================
    // 1-b SORT COLUMN WISE
    // ==========================
    public void sortColumnWise() {
        int rows = this.matrix.length;
        int cols = this.matrix[0].length;

        for (int c = 0; c < cols; c++) {
            int[] column = new int[rows];
            for (int r = 0; r < rows; r++) {
                column[r] = this.matrix[r][c];
            }

            Arrays.sort(column);

            for (int r = 0; r < rows; r++) {
                this.matrix[r][c] = column[r];
            }
        }
        System.out.println("\nMatrix setelah sort column-wise:");
        printMatrix();
    }

    // ==========================
    // 2-a CLOCKWISE BY 1
    // ==========================
    public void rotateClockwiseOne() {
        int rows = this.matrix.length;
        int cols = this.matrix[0].length;
        if (rows < 2 || cols < 2)
            return;

        List<Integer> elements = new ArrayList<>();

        // Atas
        for (int j = 0; j < cols; j++)
            elements.add(this.matrix[0][j]);
        // Kanan
        for (int i = 1; i < rows; i++)
            elements.add(this.matrix[i][cols - 1]);
        // Bawah
        for (int j = cols - 2; j >= 0; j--)
            elements.add(this.matrix[rows - 1][j]);
        // Kiri
        for (int i = rows - 2; i > 0; i--)
            elements.add(this.matrix[i][0]);

        // Shift Right (Elemen terakhir pindah ke depan)
        int last = elements.remove(elements.size() - 1);
        elements.add(0, last);

        int k = 0;
        for (int j = 0; j < cols; j++)
            this.matrix[0][j] = elements.get(k++);
        for (int i = 1; i < rows; i++)
            this.matrix[i][cols - 1] = elements.get(k++);
        for (int j = cols - 2; j >= 0; j--)
            this.matrix[rows - 1][j] = elements.get(k++);
        for (int i = rows - 2; i > 0; i--)
            this.matrix[i][0] = elements.get(k++);

        System.out.println("\nMatrix setelah rotate clockwise 1 langkah:");
        printMatrix();
    }

    // ==========================
    // 2-b COUNTER CLOCKWISE BY 1
    // ==========================
    public void rotateCounterClockwiseOne() {
        int rows = this.matrix.length;
        int cols = this.matrix[0].length;
        if (rows < 2 || cols < 2)
            return;

        List<Integer> elements = new ArrayList<>();

        // Atas
        for (int j = 0; j < cols; j++)
            elements.add(this.matrix[0][j]);
        // Kanan
        for (int i = 1; i < rows; i++)
            elements.add(this.matrix[i][cols - 1]);
        // Bawah
        for (int j = cols - 2; j >= 0; j--)
            elements.add(this.matrix[rows - 1][j]);
        // Kiri
        for (int i = rows - 2; i > 0; i--)
            elements.add(this.matrix[i][0]);

        // Shift Left (Elemen pertama pindah ke belakang)
        int first = elements.remove(0);
        elements.add(first);

        int k = 0;
        for (int j = 0; j < cols; j++)
            this.matrix[0][j] = elements.get(k++);
        for (int i = 1; i < rows; i++)
            this.matrix[i][cols - 1] = elements.get(k++);
        for (int j = cols - 2; j >= 0; j--)
            this.matrix[rows - 1][j] = elements.get(k++);
        for (int i = rows - 2; i > 0; i--)
            this.matrix[i][0] = elements.get(k++);

        System.out.println("\nMatrix setelah rotate counter-clockwise 1 langkah:");
        printMatrix();
    }

    // ==========================
    // 2-c ROTATE 90
    // ==========================
    public void rotate90() {
        int rows = this.matrix.length;
        int cols = this.matrix[0].length;
        int[][] newMatrix = new int[cols][rows];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                newMatrix[c][rows - 1 - r] = this.matrix[r][c];
            }
        }

        // Update referensi objek matriks ke hasil yang baru
        this.matrix = newMatrix;
        System.out.println("\nMatrix setelah rotate 90 derajat:");
        printMatrix();
    }

    // ==========================
    // 2-d ROTATE 180
    // ==========================
    public void rotate180() {
        int rows = this.matrix.length;
        int cols = this.matrix[0].length;
        int[][] newMatrix = new int[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                newMatrix[rows - 1 - r][cols - 1 - c] = this.matrix[r][c];
            }
        }

        this.matrix = newMatrix;
        System.out.println("\nMatrix setelah rotate 180 derajat:");
        printMatrix();
    }

    // ==========================
    // 3-a ROW TRAVERSAL
    // ==========================
    public void rowTraversal() {
        System.out.println("\nRow-wise Traversal:");
        for (int[] row : this.matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
        }
        System.out.println("\n");
    }

    // ==========================
    // 3-b COLUMN TRAVERSAL
    // ==========================
    public void columnTraversal() {
        System.out.println("\nColumn-wise Traversal:");
        int rows = this.matrix.length;
        int cols = this.matrix[0].length;

        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++) {
                System.out.print(this.matrix[r][c] + " ");
            }
        }
        System.out.println("\n");
    }

    // ==========================
    // 4 SPIRAL
    // ==========================
    public void spiralPrint() {
        System.out.println("\nSpiral Traversal:");
        int top = 0;
        int bottom = this.matrix.length - 1;
        int left = 0;
        int right = this.matrix[0].length - 1;

        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++)
                System.out.print(this.matrix[top][i] + " ");
            top++;

            for (int i = top; i <= bottom; i++)
                System.out.print(this.matrix[i][right] + " ");
            right--;

            if (top <= bottom) {
                for (int i = right; i >= left; i--)
                    System.out.print(this.matrix[bottom][i] + " ");
                bottom--;
            }

            if (left <= right) {
                for (int i = bottom; i >= top; i--)
                    System.out.print(this.matrix[i][left] + " ");
                left++;
            }
        }
        System.out.println("\n");
    }

    // ==========================
    // 5 TRANSPOSE
    // ==========================
    public void transpose() {
        int rows = this.matrix.length;
        int cols = this.matrix[0].length;
        int[][] newMatrix = new int[cols][rows];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                newMatrix[c][r] = this.matrix[r][c];
            }
        }

        this.matrix = newMatrix;
        System.out.println("\nTranspose Matrix:");
        printMatrix();
    }

    // =====================================
    // MAIN PROGRAM
    // =====================================
    public static void main(String[] args) {
        MatrixProgram program = new MatrixProgram();
        program.inputMatrix();
        Scanner sc = new Scanner(System.in);
        String choice;

        while (true) {
            System.out.println("============== MENU ==============");
            System.out.println("0. Tampilkan Matrix");
            System.out.println("1-a. Sort the matrix row-wise");
            System.out.println("1-b. Sort the matrix column-wise");
            System.out.println("2-a. Rotate Matrix Clockwise by 1");
            System.out.println("2-b. Rotate Matrix Counter-Clockwise by 1");
            System.out.println("2-c. Rotate a matrix by 90");
            System.out.println("2-d. Rotate a matrix by 180");
            System.out.println("3-a. Row-wise traversal of matrix");
            System.out.println("3-b. Column-wise traversal of matrix");
            System.out.println("4. Print matrix in spiral form");
            System.out.println("5. Transpose matrix");
            System.out.println("6. Input Matrix Baru");
            System.out.println("7. Quit");
            System.out.println("==================================");
            System.out.print("Pilih menu: ");

            choice = sc.nextLine().trim().toLowerCase();

            switch (choice) {
                case "0":
                    program.printMatrix();
                    break;
                case "1-a":
                    program.sortRowWise();
                    break;
                case "1-b":
                    program.sortColumnWise();
                    break;
                case "2-a":
                    program.rotateClockwiseOne();
                    break;
                case "2-b":
                    program.rotateCounterClockwiseOne();
                    break;
                case "2-c":
                    program.rotate90();
                    break;
                case "2-d":
                    program.rotate180();
                    break;
                case "3-a":
                    program.rowTraversal();
                    break;
                case "3-b":
                    program.columnTraversal();
                    break;
                case "4":
                    program.spiralPrint();
                    break;
                case "5":
                    program.transpose();
                    break;
                case "6":
                    program.inputMatrix();
                    break;
                case "7":
                    System.out.println("Program selesai.");
                    sc.close();
                    return; // Menghentikan eksekusi main method
                default:
                    System.out.println("Menu tidak valid!");
            }
        }
    }
}