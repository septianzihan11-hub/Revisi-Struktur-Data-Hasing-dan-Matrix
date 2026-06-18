import java.util.*;

public class HashTableAngka {

    private static final int TABLE_SIZE = 100; // 100 bucket (0-99)
    private final LinkedList<Integer>[] table;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public HashTableAngka() {
        table = new LinkedList[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++)
            table[i] = new LinkedList<>();
    }

    int hash(int n) {
        return n % TABLE_SIZE;
    }

    public boolean insert(int angka) {
        int idx = hash(angka);
        for (int n : table[idx]) {
            if (n == angka) {
                System.out.println("  x " + angka + " sudah ada (duplikat ditolak).");
                return false;
            }
        }
        table[idx].add(angka);
        size++;
        return true;
    }

    public boolean delete(int angka) {
        int idx = hash(angka);
        Iterator<Integer> it = table[idx].iterator();
        while (it.hasNext()) {
            int n = it.next();
            if (n == angka) {
                it.remove();
                size--;
                System.out.println("  v " + angka + " berhasil dihapus dari bucket[" + idx + "].");
                return true;
            }
        }
        System.out.println("  x " + angka + " tidak ditemukan.");
        return false;
    }

    public boolean search(int angka) {
        int idx = hash(angka);
        int pos = 1;
        for (int n : table[idx]) {
            if (n == angka) {
                System.out.printf("  v %d DITEMUKAN di bucket[%d], posisi ke-%d dalam chain.%n",
                        angka, idx, pos);
                return true;
            }
            pos++;
        }
        System.out.printf("  x %d TIDAK DITEMUKAN (dicari di bucket[%d]).%n", angka, idx);
        return false;
    }

    public void printTable() {
        System.out.println("\n==========================================================");
        System.out.println("         STRUKTUR HASH TABLE (Bucket Berisi Data)");
        System.out.println("==========================================================");
        int empty = 0;
        for (int i = 0; i < TABLE_SIZE; i++) {
            if (!table[i].isEmpty()) {
                System.out.printf("  bucket[%d] (%d) --> %s%n", i, table[i].size(), table[i]);
            } else {
                System.out.printf("  bucket[%d] --> (kosong)%n", i);
                empty++;
            }
        }
        System.out.println("  (Bucket kosong: " + empty + " dari " + TABLE_SIZE + ")");
    }

    public void printStats() {
        int col = 0, maxChain = 0;
        for (LinkedList<Integer> b : table) {
            if (b.size() > 1)
                col += b.size() - 1;
            if (b.size() > maxChain)
                maxChain = b.size();
        }
        System.out.println("\n--------------------- Statistik Hash Table ---------------------");
        System.out.printf("  Ukuran table      : %d%n", TABLE_SIZE);
        System.out.printf("  Total elemen      : %d%n", size);
        System.out.printf("  Elemen collision  : %d%n", col);
        System.out.printf("  Chain terpanjang  : %d%n", maxChain);
        System.out.printf("  Load factor       : %.2f%n", (double) size / TABLE_SIZE);
    }

    public void generateRandom() {
        System.out.println("\n[*] Membuat 100 angka random unik (1 - 1000)...");
        System.out.printf("    %-10s %-12s %-6s%n", "Angka", "Bucket", "Chain");

        Random rand = new Random();
        Set<Integer> generated = new LinkedHashSet<>();

        while (generated.size() < 100) {
            generated.add(rand.nextInt(1000) + 1);
        }

        for (int angka : generated) {
            insert(angka);
            int idx = hash(angka);
            System.out.printf("    %-10d bucket[%d]   %d%n", angka, idx, table[idx].size());
        }
        System.out.println("    Total dimuat: " + size + " angka");
    }

    public static void main(String[] args) {
        HashTableAngka ht = new HashTableAngka();

        System.out.println("==========================================================");
        System.out.println("  HASH TABLE -- Separate Chaining  (data numerik)");
        System.out.println("  Hash Function: h(n) = n mod " + TABLE_SIZE);
        System.out.println("==========================================================");

        ht.generateRandom();
        ht.printStats();

        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("==========MENU UTAMA==========");
            System.out.println("1. Input Data Baru");
            System.out.println("2. Hapus Data");
            System.out.println("3. Cari Data");
            System.out.println("4. Tampilkan Seluruh Hash Table");
            System.out.println("5. Tampilkan Statistik");
            System.out.println("0. Keluar");
            System.out.print("Pilihan: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  Masukkan angka menu.");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    System.out.print("Masukkan angka baru: ");
                    try {
                        int angka = Integer.parseInt(sc.nextLine().trim());
                        if (ht.insert(angka))
                            System.out.printf("  v %d dimasukkan ke bucket[%d].%n", angka, ht.hash(angka));
                    } catch (NumberFormatException e) {
                        System.out.println("  Masukkan angka yang valid.");
                    }
                }
                case 2 -> {
                    System.out.print("Masukkan angka yang dihapus: ");
                    try {
                        ht.delete(Integer.parseInt(sc.nextLine().trim()));
                    } catch (NumberFormatException e) {
                        System.out.println("  Masukkan angka yang valid.");
                    }
                }
                case 3 -> {
                    System.out.print("Masukkan angka yang dicari: ");
                    try {
                        ht.search(Integer.parseInt(sc.nextLine().trim()));
                    } catch (NumberFormatException e) {
                        System.out.println("Masukkan angka yang valid.");
                    }
                }
                case 4 -> ht.printTable();
                case 5 -> ht.printStats();
                case 0 -> {
                    System.out.println("\n[*] Program selesai.");
                    running = false;
                }
                default -> System.out.println("  Pilihan tidak valid.");
            }
        }
        sc.close();
    }
}