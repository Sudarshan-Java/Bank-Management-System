package Asimulator;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
import java.util.*;

public class Signup extends JFrame implements ActionListener {
    JLabel l1, l2;
    JTextField t1, t2, t3, t4, t5, t6, t7;
    JRadioButton r1, r2, r3, r4, r5;
    JButton b;
    JDateChooser dateChooser;
    String formno;

    Signup() {
        setTitle("NEW ACCOUNT APPLICATION FORM");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Generate form number
        Random ran = new Random();
        long first4 = (ran.nextLong() % 9000L) + 1000L;
        formno = "" + Math.abs(first4);

        // Header
        l1 = new JLabel("APPLICATION FORM NO. " + formno);
        l1.setFont(new Font("Raleway", Font.BOLD, 24));
        l1.setBounds(180, 20, 600, 30);
        add(l1);

        l2 = new JLabel("Page 1: Personal Details");
        l2.setFont(new Font("Raleway", Font.BOLD, 18));
        l2.setBounds(250, 60, 400, 30);
        add(l2);

        // Labels and fields
        int y = 110;
        int spacing = 40;

        addLabel("Name:", 100, y);             t1 = addTextField(250, y);
        y += spacing;

        addLabel("Father's Name:", 100, y);    t2 = addTextField(250, y);
        y += spacing;

        addLabel("Date of Birth:", 100, y);    
        dateChooser = new JDateChooser();
        dateChooser.setBounds(250, y, 200, 25);
        add(dateChooser);
        y += spacing;

        addLabel("Gender:", 100, y);
        r1 = new JRadioButton("Male");
        r2 = new JRadioButton("Female");
        r1.setBounds(250, y, 80, 25);
        r2.setBounds(340, y, 100, 25);
        r1.setBackground(Color.WHITE);
        r2.setBackground(Color.WHITE);
        add(r1); add(r2);
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(r1); genderGroup.add(r2);
        y += spacing;

        addLabel("Email:", 100, y);            t3 = addTextField(250, y);
        y += spacing;

        addLabel("Marital Status:", 100, y);
        r3 = new JRadioButton("Married");
        r4 = new JRadioButton("Unmarried");
        r5 = new JRadioButton("Other");
        r3.setBounds(250, y, 100, 25);
        r4.setBounds(360, y, 100, 25);
        r5.setBounds(470, y, 100, 25);
        r3.setBackground(Color.WHITE);
        r4.setBackground(Color.WHITE);
        r5.setBackground(Color.WHITE);
        add(r3); add(r4); add(r5);
        ButtonGroup statusGroup = new ButtonGroup();
        statusGroup.add(r3); statusGroup.add(r4); statusGroup.add(r5);
        y += spacing;

        addLabel("Address:", 100, y);          t4 = addTextField(250, y);
        y += spacing;

        addLabel("City:", 100, y);             t5 = addTextField(250, y);
        y += spacing;

        addLabel("Pin Code:", 100, y);         t6 = addTextField(250, y);
        y += spacing;

        addLabel("State:", 100, y);            t7 = addTextField(250, y);
        y += spacing;

        // Next Button
        b = new JButton("Next");
        b.setBounds(480, y + 20, 100, 30);
        b.setBackground(Color.BLACK);
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Raleway", Font.BOLD, 14));
        b.addActionListener(this);
        add(b);

        setSize(750, y + 120);
        setLocation(450, 100);
        setVisible(true);
    }

    private void addLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Raleway", Font.BOLD, 16));
        label.setBounds(x, y, 150, 25);
        add(label);
    }

    private JTextField addTextField(int x, int y) {
        JTextField tf = new JTextField();
        tf.setFont(new Font("Raleway", Font.PLAIN, 14));
        tf.setBounds(x, y, 300, 25);
        add(tf);
        return tf;
    }

    public void actionPerformed(ActionEvent ae) {
        String name = t1.getText();
        String fname = t2.getText();
        java.util.Date selectedDate = dateChooser.getDate();
        if (selectedDate == null) {
            JOptionPane.showMessageDialog(null, "Please select your Date of Birth");
            return;
        }
        String dob = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);
        String gender = r1.isSelected() ? "Male" : r2.isSelected() ? "Female" : null;
        String email = t3.getText();
        String marital = r3.isSelected() ? "Married" : r4.isSelected() ? "Unmarried" : r5.isSelected() ? "Other" : null;
        String address = t4.getText();
        String city = t5.getText();
        String pincode = t6.getText();
        String state = t7.getText();

        if (name.isEmpty() || fname.isEmpty() || gender == null || email.isEmpty() || marital == null || address.isEmpty() || city.isEmpty() || pincode.isEmpty() || state.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all fields");
            return;
        }

        try {
            Conn c1 = new Conn();
            String q1 = "insert into signup values('" + formno + "','" + name + "','" + fname + "','" + dob + "','" + gender + "','" + email + "','" + marital + "','" + address + "','" + city + "','" + pincode + "','" + state + "')";
            c1.s.executeUpdate(q1);

            new Signup2(formno).setVisible(true);
            setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Signup();
    }
}
