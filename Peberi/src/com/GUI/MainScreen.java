package com.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MainScreen extends JFrame{
    private JPanel panelMain;
    private JList listDataMahasiswa;
    private JButton filterButton;
    private JTextField textFieldFilter;
    private JTextField textFieldNama;
    private JTextField textFieldNim;
    private JTextField textFieldIpk;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton clearButton;

    private List<Mahasiswa> arrayData = new ArrayList<>();

    private DefaultListModel defaultListModel = new DefaultListModel<>();

    class Mahasiswa{
        private String nama;

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getNim() {
            return nim;
        }

        public void setNim(String nim) {
            this.nim = nim;
        }

        public Float getIpk() {
            return ipk;
        }

        public void setIpk(Float ipk) {
            this.ipk = ipk;
        }

        private String nim;
        private Float ipk;
    }

    public MainScreen(){
        super("Data Mahasiswa");
        this.setContentPane(panelMain);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nama = textFieldNama.getText();
                String nim = textFieldNim.getText();
                Float ipk = Float.valueOf(textFieldIpk.getText());

                Mahasiswa mahasiswa = new Mahasiswa();

                mahasiswa.setNama(nama);
                mahasiswa.setNim(nim);
                mahasiswa.setIpk(ipk);

                arrayData.add(mahasiswa);
                setClearButton();
                fromMahasiswaToListModel();
            }
        });
        listDataMahasiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int index = listDataMahasiswa.getSelectedIndex();

                if (index < 0 )
                    return;

                String nama = listDataMahasiswa.getSelectedValue().toString();

                for (int i = 0; i < arrayData.size(); i++) {
                    if (arrayData.get(i).getNama().equals(nama)){
                        Mahasiswa mahasiswa = arrayData.get(i);
                        textFieldNim.setText(mahasiswa.getNim());
                        textFieldNama.setText(mahasiswa.getNama());
                        textFieldIpk.setText(String.valueOf(mahasiswa.getIpk()));
                        break;
                    }
                }
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setClearButton();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = listDataMahasiswa.getSelectedIndex();

                if (index<0)
                    return;
                String nama = listDataMahasiswa.getSelectedValue().toString();
                for (int i = 0; i < arrayData.size() ; i++){
                    if (arrayData.get(i).getNama().equals(nama)){
                        arrayData.remove(i);
                        break;
                    }
                }
                setClearButton();
                fromMahasiswaToListModel();
            }
        });
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = textFieldFilter.getText();

                List<String>filtered = new ArrayList<>();

                for (int i =0; i < arrayData.size();i++) {
                     if (arrayData.get(i).getNama().contains(keyword)) {
                        filtered.add(arrayData.get(i).getNama());
                     }
                }
                refreshListModel(filtered);
            }
        });
    }

    private void fromMahasiswaToListModel(){
        List<String> listNamaMahasiswa = new ArrayList<>();

        for (int i = 0; i < arrayData.size(); i++) {
            listNamaMahasiswa.add(arrayData.get(i).getNama());
        }
        refreshListModel(listNamaMahasiswa);
    }

    private void setClearButton(){
        textFieldIpk.setText("");
        textFieldNim.setText("");
        textFieldNama.setText("");
    }

    private void refreshListModel(List<String>list){
        defaultListModel.clear();
        defaultListModel.addAll(list);
        listDataMahasiswa.setModel(defaultListModel);
    }

    public static void main(String[] args) {
        MainScreen mainScreen = new MainScreen();
        mainScreen.setVisible(true);
    }
}
