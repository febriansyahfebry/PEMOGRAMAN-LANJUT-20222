package febry;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MainScreen extends JFrame{
    private static final String URL = "jdbc:mysql://localhost:3306/datamahasiswa4";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private JPanel PanelMain;
    private JTable JTabelMahasiswa;
    private JTextField txtnim;
    private JTextField txtnama;
    private JTextField txtipk;
    private JButton buttonAdd;
    private JButton buttonUpdate;
    private JButton buttonDelete;
    private JButton buttonClear;
    private JTextField txtfilter;
    private JButton filter;

    private DefaultTableModel defaultTableModel = new DefaultTableModel();
    public MainScreen(){
        super("Data Mahasiswa");
        this.setContentPane(PanelMain);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();

        refreshTable(getMahasiswa());

        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = txtnama.getText();
                String nim = txtnim.getText();
                double ipk = Double.parseDouble(txtipk.getText());


                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setNama(nama);
                mahasiswa.setNim(nim);
                mahasiswa.setIpk(ipk);

                clearForm();
                insertMahasiswa(mahasiswa);
                refreshTable(getMahasiswa());
            }
        });

        buttonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = txtnama.getText();
                String nim = txtnim.getText();
                double  ipk = Double.parseDouble(txtipk.getText());

                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setNama(nama);
                mahasiswa.setNim(nim);
                mahasiswa.setIpk(ipk);

                clearForm();
                updateMahasiswa(mahasiswa);
                refreshTable(getMahasiswa());

            }
        });
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nim = txtnim.getText();

                clearForm();
                deleteMahasiswa(nim);
                refreshTable(getMahasiswa());
            }
        });
        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
        filter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = txtfilter.getText();
                refreshTable(filterMahasiswa(nama));
            }
        });


        JTabelMahasiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int row = JTabelMahasiswa.getSelectedRow();
                System.out.println(row);

                String nim = JTabelMahasiswa.getValueAt(row,0).toString();
                String nama = JTabelMahasiswa.getValueAt(row,1).toString();
                String ipk = JTabelMahasiswa.getValueAt(row,2).toString();

                txtnama.setText(nama);
                txtnim.setText(nim);
                txtipk.setText(ipk);
            }
        });
    }



    public void refreshTable(List<Mahasiswa> arrayListMahasiswa){
      Object [][] data = new Object[arrayListMahasiswa.size()][3];
        for (int i = 0; i < arrayListMahasiswa.size(); i++) {
            data[i] =new Object[]{
                    arrayListMahasiswa.get(i).getNim(),
                    arrayListMahasiswa.get(i).getNama(),
                    arrayListMahasiswa.get(i).getIpk()
            };
        }
        defaultTableModel =new DefaultTableModel(
                data, new String[] {"nim", "nama", "ipk"});

        JTabelMahasiswa.setModel(defaultTableModel);
    }

    private static ResultSet executeQuery(String query){

        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            Statement statement = connection.createStatement();

            return statement.executeQuery(query);
        }
        catch (Exception e){
            return null;
        }
    }

    private static List<Mahasiswa> getMahasiswa(){
        List<Mahasiswa> arrayListMahasiswa = new ArrayList<>();

        ResultSet resultSet = executeQuery("select * from mahasiswa");
        try {
            while (resultSet.next()){
                String nama = resultSet.getString("nama");
                String nim = resultSet.getString("nim");
                double ipk = Double.parseDouble(resultSet.getString("ipk")) ;

                System.out.println(nim);
                System.out.println(nama);
                System.out.println(ipk);
                System.out.println();

                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setNim(nim);
                mahasiswa.setNama(nama);
                mahasiswa.setIpk(ipk);
                arrayListMahasiswa.add(mahasiswa);
            }
        }
        catch (Exception e){
            return null;
        }
        return arrayListMahasiswa;
    }
    private static  List<Mahasiswa> filterMahasiswa(String filternama){
        List<Mahasiswa> arrayListMahasiswa = new ArrayList<>();

        ResultSet resultSet =executeQuery("select * from mahasiswa where nama like '%" + filternama + "%'");

        try {
            while (resultSet.next()){
                String nim = resultSet.getString("nim");
                String nama = resultSet.getString("nama");
                double ipk = Double.parseDouble(resultSet.getString("ipk"));

                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setNim(nim);
                mahasiswa.setNama(nama);
                mahasiswa.setIpk(ipk);

                arrayListMahasiswa.add(mahasiswa);

            }
        }
        catch (Exception e){
            return null;
        }
        return arrayListMahasiswa;
    }

    private static void executesql(String sql){
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);
        }
        catch (Exception e){

        }
    }

    private static  void insertMahasiswa( Mahasiswa mahasiswa){
        String sql = "insert into mahasiswa values (" +
                "'" + mahasiswa.getNim() + "', " +
                "'" + mahasiswa.getNama() + "', " +
                "'" + mahasiswa.getIpk() + "')";
        executesql(sql);
    }
    private static  void updateMahasiswa( Mahasiswa mahasiswa){
        String sql = "update mahasiswa set " +
                "nama = '" + mahasiswa.getNama() + "', " +
                "ipk = '" + mahasiswa.getIpk() + "' " +
                "where nim = '" + mahasiswa.getNim() +"'";
        executesql(sql);
    }
    private static  void deleteMahasiswa(String nim){
        String sql = "delete from mahasiswa " +
                "where nim = '"+ nim +"'";
        executesql(sql);
    }
    private void clearForm(){
        txtnim.setText("");
        txtnama.setText("");
        txtipk.setText("");
    }

    public static void main(String[] args) {
        MainScreen mainScreen = new MainScreen();
        mainScreen.setVisible(true);
    }
}
