package PEBRIYA;

import java.util. ArrayList;
import java.util.Scanner;
// PEBRIANSYAH
// 22116028

public class DATAMAHASISWI {
    public static void main(String[] args) {

        ArrayList<String> nama = new ArrayList<>();
        ArrayList<String> Nim = new ArrayList<>();
        ArrayList<String> alamat = new ArrayList<>();
        Scanner input = new Scanner(System.in);


        int pilihan;
        do {

            System.out.println("\n program data mahasiswi");
            System.out.println("=========================");
            System.out.println("1.Tambahkan");
            System.out.println("2.Tampilkan");
            System.out.println("3.cari");
            System.out.println("4.Hapus");
            System.out.println("5.Keluar");
            System.out.print("\n pilih menuh :");
            pilihan = input.nextInt();

            if (pilihan == 1) {
                System.out.print("masukan nama :");
                String nm = input.next();
                nama.add(nm);

                System.out.print("masukan nim :");
                String nim = input.next();
                Nim.add(nim);

                System.out.print("masukan alamat : ");
                String alm = input.next();
                alamat.add(alm);

            } else if (pilihan == 2) {

                System.out.println("Data mahasiswi");
                System.out.println("===========");

                for (int i = 0; i < Nim.size(); i++) {
                    System.out.println(i + 1 + "." + Nim.get(i));
                }

                System.out.println("untuk melihat data lengkap,cari data (3) ");
            } else if (pilihan == 3) {
                System.out.print("masukan data nomor berapa yang ingin di cari :");
                int cari = input.nextInt();
                if (cari == 1) {
                    System.out.println("nama : " + nama.get(0));
                    System.out.println("nim : " + Nim.get(0));
                    System.out.println("alamat :" + alamat.get(0));
                }
                System.out.println("================");
                if (cari == 2) {
                    System.out.println("nama : " + nama.get(1));
                    System.out.println("nim : " + Nim.get(1));
                    System.out.println("alamat : " + alamat.get(1));

                }
            }else if (pilihan == 4) {
                System.out.println("data mahasiswi");
                System.out.println("===============");

                for (int i = 0; i < Nim.size(); i++) {
                    System.out.println(i + 1 + "." + Nim.get(i));
                }
                System.out.print("masukan nim yang akan di hapus : ");
                String nim = input.next();
                Nim.remove(nim);
            } else if (pilihan == 5) {
                System.out.println("selesai");

            } else {
                System.out.println("menu tidak tersedia");

            }

        } while (pilihan != 5);
    }
}

















