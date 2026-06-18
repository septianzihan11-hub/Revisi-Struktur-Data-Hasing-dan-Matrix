class MatrixProgram:

    def __init__(self):
        self.matrix = []

    # ==========================
    # INPUT MATRIX
    # ==========================
    def input_matrix(self):

        while True:
            try:
                rows = int(input("Masukkan jumlah baris : "))
                cols = int(input("Masukkan jumlah kolom : "))
                break
            except ValueError:
                print("Input harus angka!")

        self.matrix = []

        print("\nMasukkan elemen matrix:")

        for i in range(rows):

            while True:

                try:
                    row = list(map(int, input(f"Baris {i+1}: ").split()))

                    if len(row) != cols:
                        print(f"Harus {cols} angka!")
                        continue

                    self.matrix.append(row)
                    break

                except ValueError:
                    print("Masukkan angka saja!")

    # ==========================
    # TAMPILKAN MATRIX
    # ==========================
    def print_matrix(self):
        print("\nMatrix:")
        for row in self.matrix:
            print(*row)
        print()

    # ==========================
    # 1-a SORT ROW WISE
    # ==========================
    def sort_row_wise(self):
        for row in self.matrix:
            row.sort()

        print("\nMatrix setelah sort row-wise:")
        self.print_matrix()

    # ==========================
    # 1-b SORT COLUMN WISE
    # ==========================
    def sort_column_wise(self):
        rows = len(self.matrix)
        cols = len(self.matrix[0])

        for c in range(cols):
            column = []

            for r in range(rows):
                column.append(self.matrix[r][c])

            column.sort()

            for r in range(rows):
                self.matrix[r][c] = column[r]

        print("\nMatrix setelah sort column-wise:")
        self.print_matrix()

    # ==========================
    # 2-a CLOCKWISE BY 1
    # ==========================
    def rotate_clockwise_one(self):

        rows = len(self.matrix)
        cols = len(self.matrix[0])

        if rows < 2 or cols < 2:
            return

        elements = []

        # atas
        for j in range(cols):
            elements.append(self.matrix[0][j])

        # kanan
        for i in range(1, rows):
            elements.append(self.matrix[i][cols-1])

        # bawah
        for j in range(cols-2, -1, -1):
            elements.append(self.matrix[rows-1][j])

        # kiri
        for i in range(rows-2, 0, -1):
            elements.append(self.matrix[i][0])

        elements = [elements[-1]] + elements[:-1]

        k = 0

        for j in range(cols):
            self.matrix[0][j] = elements[k]
            k += 1

        for i in range(1, rows):
            self.matrix[i][cols-1] = elements[k]
            k += 1

        for j in range(cols-2, -1, -1):
            self.matrix[rows-1][j] = elements[k]
            k += 1

        for i in range(rows-2, 0, -1):
            self.matrix[i][0] = elements[k]
            k += 1

        print("\nMatrix setelah rotate clockwise 1 langkah:")
        self.print_matrix()

    # ==========================
    # 2-b COUNTER CLOCKWISE BY 1
    # ==========================
    def rotate_counter_clockwise_one(self):

        rows = len(self.matrix)
        cols = len(self.matrix[0])

        if rows < 2 or cols < 2:
            return

        elements = []

        # atas
        for j in range(cols):
            elements.append(self.matrix[0][j])

        # kanan
        for i in range(1, rows):
            elements.append(self.matrix[i][cols-1])

        # bawah
        for j in range(cols-2, -1, -1):
            elements.append(self.matrix[rows-1][j])

        # kiri
        for i in range(rows-2, 0, -1):
            elements.append(self.matrix[i][0])

        elements = elements[1:] + [elements[0]]

        k = 0

        for j in range(cols):
            self.matrix[0][j] = elements[k]
            k += 1

        for i in range(1, rows):
            self.matrix[i][cols-1] = elements[k]
            k += 1

        for j in range(cols-2, -1, -1):
            self.matrix[rows-1][j] = elements[k]
            k += 1

        for i in range(rows-2, 0, -1):
            self.matrix[i][0] = elements[k]
            k += 1

        print("\nMatrix setelah rotate counter-clockwise 1 langkah:")
        self.print_matrix()

    # ==========================
    # 2-c ROTATE 90
    # ==========================
    def rotate_90(self):

        self.matrix = [list(row) for row in zip(*self.matrix[::-1])]

        print("\nMatrix setelah rotate 90 derajat:")
        self.print_matrix()

    # ==========================
    # 2-d ROTATE 180
    # ==========================
    def rotate_180(self):

        self.matrix = [row[::-1] for row in self.matrix[::-1]]

        print("\nMatrix setelah rotate 180 derajat:")
        self.print_matrix()

    # ==========================
    # 3-a ROW TRAVERSAL
    # ==========================
    def row_traversal(self):

        print("\nRow-wise Traversal:")

        for row in self.matrix:
            for val in row:
                print(val, end=" ")

        print("\n")

    # ==========================
    # 3-b COLUMN TRAVERSAL
    # ==========================
    def column_traversal(self):

        print("\nColumn-wise Traversal:")

        rows = len(self.matrix)
        cols = len(self.matrix[0])

        for c in range(cols):
            for r in range(rows):
                print(self.matrix[r][c], end=" ")

        print("\n")

    # ==========================
    # 4 SPIRAL
    # ==========================
    def spiral_print(self):

        print("\nSpiral Traversal:")

        top = 0
        bottom = len(self.matrix) - 1
        left = 0
        right = len(self.matrix[0]) - 1

        while top <= bottom and left <= right:

            for i in range(left, right + 1):
                print(self.matrix[top][i], end=" ")
            top += 1

            for i in range(top, bottom + 1):
                print(self.matrix[i][right], end=" ")
            right -= 1

            if top <= bottom:
                for i in range(right, left - 1, -1):
                    print(self.matrix[bottom][i], end=" ")
                bottom -= 1

            if left <= right:
                for i in range(bottom, top - 1, -1):
                    print(self.matrix[i][left], end=" ")
                left += 1

        print("\n")

    # ==========================
    # 5 TRANSPOSE
    # ==========================
    def transpose(self):

        self.matrix = [list(row) for row in zip(*self.matrix)]

        print("\nTranspose Matrix:")
        self.print_matrix()


# =====================================
# MAIN PROGRAM
# =====================================

program = MatrixProgram()

program.input_matrix()

while True:

    print("============== MENU ==============")
    print("0. Tampilkan Matrix")
    print("1-a. Sort the matrix row-wise")
    print("1-b. Sort the matrix column-wise")
    print("2-a. Rotate Matrix Clockwise by 1")
    print("2-b. Rotate Matrix Counter-Clockwise by 1")
    print("2-c. Rotate a matrix by 90")
    print("2-d. Rotate a matrix by 180")
    print("3-a. Row-wise traversal of matrix")
    print("3-b. Column-wise traversal of matrix")
    print("4. Print matrix in spiral form")
    print("5. Transpose matrix")
    print("6. Input Matrix Baru")
    print("7. Quit")
    print("==================================")

    choice = input("Pilih menu: ").lower()

    if choice == "0":
        program.print_matrix()

    elif choice == "1-a":
        program.sort_row_wise()

    elif choice == "1-b":
        program.sort_column_wise()

    elif choice == "2-a":
        program.rotate_clockwise_one()

    elif choice == "2-b":
        program.rotate_counter_clockwise_one()

    elif choice == "2-c":
        program.rotate_90()

    elif choice == "2-d":
        program.rotate_180()

    elif choice == "3-a":
        program.row_traversal()

    elif choice == "3-b":
        program.column_traversal()

    elif choice == "4":
        program.spiral_print()

    elif choice == "5":
        program.transpose()
    
    elif choice == "6":
        program.input_matrix()

    elif choice == "7":
        print("Program selesai.")
        break

    else:
        print("Menu tidak valid!")