package TestObjectStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
 
public class ObjectStream {
    public static void main(String[] args) throws Exception{
        ArrayList<UserClass> playlist = new ArrayList<UserClass>();
        int select = 10; 
        String name="";
        Scanner input = new Scanner(System.in);
        File f = new File("music.dat");
        int c;
        ObjectInputStream objin = null;
        ObjectOutputStream objout = null;
        //������ ������ ������ �о���� �κ�
        if (f.isFile()) {
            objin = new ObjectInputStream(new FileInputStream("music.dat"));
            try {
                while((c=objin.read())==-1) {
                    playlist.add((UserClass)objin.readObject()); 
                }
            } catch (Exception e) {
                // TODO: handle exception
            }            
            
        }
        else if(f.isFile()==false) {
             FileOutputStream fsout = new FileOutputStream("music.dat");
             objout = new ObjectOutputStream(fsout);
             for (int i = 0; i < playlist.size(); i++) {
                 objout.writeObject(playlist.get(i));
                 
             }
             objout.close();
        }
        while (select != 0) {
            System.out.print("��ü �߰� 1 , ��� 2 ���� 0");
            select = input.nextInt();
            switch (select) {            
            case 1:
                playlist.add(new UserClass());
                System.out.print("���� �̸�");
                name = input.next();
                playlist.get(playlist.size()-1).setSong(name);
                
                break;
            case 2:
                for (int i = 0; i <playlist.size(); i++) {
                    System.out.println(playlist.get(i).getSong());
                }
                break;
            
            default:
                break;
            }
        }
        //��ü ����ȭ�� ���� �κ�
        FileOutputStream fsout = new FileOutputStream("music.dat");
        objout = new ObjectOutputStream(fsout);
        for (int i = 0; i < playlist.size(); i++) {
            objout.writeObject(playlist.get(i));
            
        }
        objout.close();
        
        if (objin!=null) {
            objin.close();
        }
        if (objout!=null) {
            objout.close();
        }       
    }
}


