import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.applet.*;
import java.net.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {

  public static void main(String[] args) {

    try {
      Person p1 = new Person(1, 'W', 'C', 'S', 'X', 'D', 'Q', 'Z', 'E');
      Person p2 = new Person(1, 'K', 'M', 'O', 'L', 'P', 'I', '9', '0');

      // write object to file
      FileOutputStream fos = new FileOutputStream("myPerson.ser");
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(p1);
      oos.writeObject(p2);
      oos.close();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    Menu M = new Menu();
  }
}
