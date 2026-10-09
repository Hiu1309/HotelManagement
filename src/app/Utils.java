package app;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Utils {
    public static Scanner scanner = new Scanner(System.in);

    public static int nhapSo(String thongBao) {
        while (true) {
            try {
                System.out.print(thongBao);
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Vui long nhap so nguyen.");
            }
        }
    }

    public static double nhapSoThuc(String thongBao) {
        while (true) {
            try {
                System.out.print(thongBao);
                return Double.parseDouble(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Vui long nhap so thuc.");
            }
        }
    }

    public static String nhapChuoi(String thongBao) {
        System.out.print(thongBao);
        return scanner.nextLine().trim();
    }

    public static String dinhDangTien(double tien) {
        DecimalFormat df = new DecimalFormat("#,##0");
        return df.format(tien) + " VND";
    }

    public static boolean laSoDuong(int so) {
        return so > 0;
    }
}
