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
        //파일이 있으면 파일을 읽어오는 부분
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
            System.out.print("객체 추가 1 , 출력 2 종료 0");
            select = input.nextInt();
            switch (select) {            
            case 1:
                playlist.add(new UserClass());
                System.out.print("음악 이름");
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
        //객체 직렬화에 대한 부분
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


