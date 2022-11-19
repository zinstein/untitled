package ch1;
import java.io.*;
import java.util.*;

public class Inventory {
    public static void main(String[] args) {
        try {
            fun();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("完成！");
    }

    static void fun() throws IOException {
        BufferedReader input1 = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\Inventory.txt"), "GBK"));
        BufferedReader input2 = new BufferedReader(new InputStreamReader(new FileInputStream( "D:\\Transactions.txt"), "GBK"));
        BufferedWriter output1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:/Shipping.txt")));
        BufferedWriter output2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:/Errors.txt")));
        BufferedWriter output3 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:/newInventory.txt")));

        //读取货物数据
        String line;
        Goods good;
        ArrayList<Goods> arr = new ArrayList<>();
        while ((line = input1.readLine()) != null) {
            String[] str = line.split(" ");
            good = new Goods(str[0], Integer.parseInt(str[1]), str[2], str[3]);
            arr.add(good);
        }

        ArrayList<Goods> arr2 = new ArrayList<>();//到货
        ArrayList<Goods> arr3 = new ArrayList<>();//发货
        ArrayList<Goods> arr4 = new ArrayList<>();//删货
        while ((line = input2.readLine()) != null) {
            String[] str = line.split(" ");
            if (str[0].equals("O")) {
                good = new Goods(str[1], Integer.parseInt(str[2]), str[3], " ");
                arr3.add(good);
            }
            if (str[0].equals("R")) {
                good = new Goods(str[1], Integer.parseInt(str[2]), " ", " ");
                arr2.add(good);
            }
            if (str[0].equals("A")) {
                good = new Goods(str[1], 0, str[2], str[3]);
                arr.add(good);
            }
            if (str[0].equals("D")) {
                good = new Goods(str[1], 0, " ", " ");
                arr4.add(good);
            }
        }

        //到货
        for (Goods a2 : arr2) {
            for (Goods a : arr) {
                if (a2.ItemNumber.equals(a.ItemNumber)) a.Quantity += a2.Quantity;
            }
        }

        //发货
        ArrayList<Goods> ship = new ArrayList<>();
        arr3.sort(new SortHelper());
        for (Goods a3 : arr3) {
            for (Goods a : arr) {
                if (a3.ItemNumber.equals(a.ItemNumber)) {
                    if (a3.Quantity <= a.Quantity) {
                        a.Quantity -= a3.Quantity;
                        int i = 0;
                        for (Goods s : ship) {
                            if (a3.ItemNumber.equals(s.ItemNumber)&&a3.Supplier.equals(s.Supplier)) {
                                s.Quantity += a3.Quantity;
                                i = 1;
                                break;
                            }
                        }
                        if (i == 0) {
                            ship.add(a3);
                        }
                    } else output2.write(a3.Supplier+"\t"+a3.ItemNumber +"\t"+a3.Quantity+"\n");
                }
            }
        }

        //删货
        for (Goods a4 : arr4) {
            for (Goods a : new ArrayList<>(arr)) {
                if (a4.ItemNumber.equals(a.ItemNumber)) {
                    if (a.Quantity == 0) arr.remove(a);
                    else output2.write("\n0\t"+a4.ItemNumber +"\t"+a.Quantity);
                }
            }
        }

        //写入
        for (Goods a : ship) {
            output1.write(a.Supplier+ "\t" +a.ItemNumber + "\t" + a.Quantity );
            output1.newLine();
        }
        for (Goods a : arr) {
            output3.write(a.ItemNumber + "\t" + a.Quantity + "\t" + a.Supplier + "\t" + a.Description);
            output3.newLine();
        }

        input1.close();
        input2.close();
        output1.flush();
        output2.flush();
        output3.flush();
        output1.close();
        output2.close();
        output3.close();
    }
}

class Goods {
    String ItemNumber;//货物编号
    int Quantity;//货物数量
    String Supplier;//供应商编号
    String Description;//货物描述
    Goods(String ItemNumber, int Quantity, String Supplier, String Description) {
        this.ItemNumber = ItemNumber;
        this.Quantity = Quantity;
        this.Supplier = Supplier;
        this.Description = Description;
    }
}

class SortHelper implements Comparator<Goods> {
    public int compare(Goods o1, Goods o2) {
        return o1.Quantity - o2.Quantity;
    }
}