import random

# ================= NODE =================
class Node:
    def __init__(self, data):
        self.data = data
        self.next = None

# ================= SEARCH RESULT =================
class SearchResult:
    def __init__(self, found, index, position):
        self.found = found
        self.index = index
        self.position = position

# ================= HASH TABLE =================
class HashTable:
    def __init__(self, size):
        self.size = size
        self.table = [None] * size

    # Fungsi hash menggunakan operasi modulo
    def _hash(self, key):
        return abs(key) % self.size

    # INSERT
    def insert(self, key):
        index = self._hash(key)
        current = self.table[index]
        
        # Traversal untuk cek duplikasi data
        while current is not None:
            if current.data == key:
                print("Data sudah ada!")
                return
            current = current.next
        
        # Menyisipkan node baru di awal (Head)
        new_node = Node(key)
        new_node.next = self.table[index]
        self.table[index] = new_node
        print("Data berhasil ditambahkan.")

    # SEARCH (Pencarian Data)
    def search(self, key):
        index = self._hash(key)
        current = self.table[index]
        position = 1
        
        # Traversal list pada indeks hasil komputasi hash
        while current is not None:
            if current.data == key:
                return SearchResult(True, index, position)
            current = current.next
            position += 1
            
        return SearchResult(False, -1, -1)

    #display bucket
    def display_bucket(self, index):
        current = self.table[index]

        print(f"\nBucket {index} : ", end="")

        while current:
            print(f"{current.data} -> ", end="")
            current = current.next
        print("None")

    #Statistik hash tabel
    def statistics(self):

        bucket_used = 0
        collision_bucket = 0
        longest_chain = 0

        for node in self.table:

            count = 0
            current = node

            while current:
                count += 1
                current = current.next

            if count > 0:
                bucket_used += 1

            if count > 1:
                collision_bucket += 1

            longest_chain = max(longest_chain, count)

        print("\n=== STATISTIK HASH TABLE ===")
        print(f"Ukuran Tabel      : {self.size}")
        print(f"Jumlah Data       : {self.count_data()}")
        print(f"Bucket Terpakai   : {bucket_used}")
        print(f"Bucket Collision  : {collision_bucket}")
        print(f"Chain Terpanjang  : {longest_chain}")

    # DELETE
    def delete(self, key):
        index = self._hash(key)
        current = self.table[index]
        prev = None
        
        while current is not None:
            if current.data == key:
                # Jika node yang dihapus adalah head
                if prev is None:
                    self.table[index] = current.next
                # Jika node berada di tengah atau akhir list
                else:
                    prev.next = current.next
                return True
            # Update pointer
            prev = current
            current = current.next
            
        return False

    # DISPLAY HASH TABLE
    def display(self):
        print("\n========== HASH TABLE ==========")
        for i in range(self.size):
            print(f"Index {i} : ", end="")
            current = self.table[i]
            while current is not None:
                print(f"{current.data} -> ", end="")
                current = current.next
            print("None")

    #collusion
    def display_collision(self):
        print("\n=== DATA COLLISION ===")

        found = False

        for i in range(self.size):

            count = 0
            current = self.table[i]

            while current:
                count += 1
                current = current.next

            if count > 1:
                found = True
                print(f"Index {i} memiliki {count} data")

        if not found:
            print("Tidak ada collision")

    # Hitung total populasi data
    def count_data(self):
        count = 0
        for i in range(self.size):
            current = self.table[i]
            while current is not None:
                count += 1
                current = current.next
        return count

# ================= MAIN =================
def hash_table():
    hash_table = HashTable(100)
    
    # Generate 100 data acak unik menggunakan struktur data Set built-in Python
    random_numbers = set()
    while len(random_numbers) < 100:
        random_numbers.add(random.randint(0, 999))
        
    for value in random_numbers:
        hash_table.insert(value)
        
    print("\n100 data random berhasil dimasukkan ke Hash Table.")
    
    # MENU
    while True:
        print("\n================================")
        print("       MENU HASH TABLE")
        print("================================")
        print("1. Input Data")
        print("2. Hapus Data")
        print("3. Cari Data")
        print("4. Tampilkan Hash Table")
        print("5. Jumlah Data")
        print("6. Statistik Hash Table")
        print("7. Tampilkan Collision")
        print("0. Keluar")
        
        # Error handling untuk input pengguna
        try:
            pilihan = int(input("Pilih Menu : "))
        except ValueError:
            print("Harap masukkan input berupa angka valid.")
            continue
            
        if pilihan == 1:
            tambah = int(input("Masukkan data : "))
            hash_table.insert(tambah)
        elif pilihan == 2:
            hapus = int(input("Masukkan data yang akan dihapus : "))
            if hash_table.delete(hapus):
                print("Data berhasil dihapus.")
            else:
                print("Data tidak ditemukan.")
        elif pilihan == 3:
            cari = int(input("Masukkan data yang dicari : "))
            hasil = hash_table.search(cari)
            if hasil.found:
                print("\n=== HASIL PENCARIAN ===")
                print("Data ditemukan")
                print(f"Nilai  : {cari}")
                print(f"Index  : {hasil.index}")
                print(f"Posisi : {hasil.position} (dalam rantai Linked List)")
                hash_table.display_bucket(hasil.index)
            else:
                print("Data tidak ditemukan")
        elif pilihan == 4:
            hash_table.display()
        elif pilihan == 5:
            print(f"Jumlah data dalam Hash Table = {hash_table.count_data()}")
        elif pilihan == 6:
            hash_table.statistics()
        elif pilihan == 7:
            hash_table.display_collision()
        elif pilihan == 0:
            print("Program selesai. Memory dibebaskan.")
            break
        else:
            print("Pilihan tidak valid.")

# Eksekusi blok utama
if __name__ == "__main__":
    hash_table()