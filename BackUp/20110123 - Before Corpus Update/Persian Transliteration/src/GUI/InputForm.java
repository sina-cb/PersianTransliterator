package GUI;

import Transliteration.Transliterator;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Vector;

public class InputForm extends JFrame {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextField finglishTextField;
    private JTextField persianTextField;
    private JTable resultsTable;
    private JButton addToCorpusButton;

    public InputForm() {
        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonOK);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setTitle("Persian Transliteration");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        persianTextField.setEditable(false);
        finglishTextField.setDocument(new MyFilter(MyFilter.ALPHA));

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        finglishTextField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent focusEvent) {
                super.focusGained(focusEvent);
                finglishTextField.setSelectionStart(0);
                finglishTextField.setSelectionEnd(1000);
            }
        });

        addToCorpusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                addToCorpus();
            }
        });
    }

    private void addToCorpus() {
        int trueAnswerRow = resultsTable.getSelectedRow();
        String input = finglishTextField.getText().toLowerCase();

        persianTextField.setText((String) resultsTable.getValueAt(trueAnswerRow, 0));

        Transliterator.updateCorpus(input, trueAnswerRow);
    }

    private void onOK() {
        String input = finglishTextField.getText().toLowerCase();
        Vector<String> output = Transliterator.genTransliteration(input);

        persianTextField.setText(output.elementAt(0));

        Object[][] obj = new Object[output.size()][1];
        resultsTable.setModel(new javax.swing.table.DefaultTableModel(
                obj,
                new String[]{"Results"}
        ) {
            Class[] types = new Class[]{
                    java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                    false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        for (int i = 0; i < output.size() ; i++) {
            resultsTable.setValueAt(output.elementAt(i), i, 0);
        }
    }
}
