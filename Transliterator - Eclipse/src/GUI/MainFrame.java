package GUI;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Transliteration.Transliterator;
import Utility.TokenizedResult;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = -5766323791092320305L;
	private JPanel contentPane;
	private JTextField finglishTextField;
	private JLabel lblPersian;
	private JTextField persianTextField;
	private JLabel lblPossibleValues;
	private JPanel panel;
	private JButton addToCorpusButton;
	private JButton buttonOK;
	private JTable resultsTable;
	private JScrollPane scrollPane;

	private Transliterator tl = new Transliterator();
	private JPanel panel_1;
	private JButton btnNewButton;
	private JButton btnNewButton_1;

	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 673, 685);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(308dlu;default):grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(27dlu;default):grow"),}));

		JLabel lblFinglish = new JLabel("Finglish:");
		contentPane.add(lblFinglish, "2, 2, left, default");

		finglishTextField = new JTextField();
		contentPane.add(finglishTextField, "4, 2, fill, default");
		finglishTextField.setColumns(10);

		lblPersian = new JLabel("Persian:");
		contentPane.add(lblPersian, "2, 4, left, default");

		persianTextField = new JTextField();
		contentPane.add(persianTextField, "4, 4, fill, default");
		persianTextField.setColumns(10);

		lblPossibleValues = new JLabel("Possible Values:");
		contentPane.add(lblPossibleValues, "2, 6, left, default");

		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "4, 8, fill, fill");

		resultsTable = new JTable();
		scrollPane.setViewportView(resultsTable);
		
		panel_1 = new JPanel();
		contentPane.add(panel_1, "2, 10, center, fill");
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		btnNewButton = new JButton("Reset All and Exit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetAndExit();
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		panel_1.add(btnNewButton, gbc_btnNewButton);
		
		btnNewButton_1 = new JButton("Don't Save and Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dontSaveExit();
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 1;
		panel_1.add(btnNewButton_1, gbc_btnNewButton_1);

		panel = new JPanel();
		contentPane.add(panel, "4, 10, fill, fill");
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		addToCorpusButton = new JButton("Add to Corpus");
		addToCorpusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addToCorpus();
			}
		});
		panel.add(addToCorpusButton);

		buttonOK = new JButton("OK");
		buttonOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onOK();
			}
		});
		panel.add(buttonOK);

		customCodeInConstructor();
	}
	
	private void resetAndExit() {
		TokenizedResult.cleanFiles();
		System.exit(0);
	}
	
	private void dontSaveExit() {
		System.exit(0);
	}

	private void customCodeInConstructor(){
		persianTextField.setEditable(false);
		finglishTextField.setDocument(new MyFilter(MyFilter.ALPHA));
		addToCorpusButton.setEnabled(false);

		finglishTextField.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent focusEvent) {
				super.focusGained(focusEvent);
				finglishTextField.setSelectionStart(0);
				finglishTextField.setSelectionEnd(1000);
			}
		});

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				TokenizedResult.cleanFiles();
				TokenizedResult.writeToFile();
			}
		});
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void addToCorpus(){
		int trueAnswerRow = resultsTable.getSelectedRow();

		if (trueAnswerRow == -1) {
			JOptionPane.showMessageDialog(null, "Please Select The True Answer Then Hit The Button");
			return;
		}

		String input = finglishTextField.getText().toLowerCase();

		persianTextField.setText((String) resultsTable.getValueAt(trueAnswerRow, 0));

		tl.updateCorpus(input, trueAnswerRow);
	}

	@SuppressWarnings("rawtypes")
	private void onOK(){
		addToCorpusButton.setEnabled(true);

		String input = finglishTextField.getText().toLowerCase();
		Vector<String> output = tl.genTransliteration(input);

		persianTextField.setText(output.elementAt(0));

		Object[][] obj = new Object[output.size()][1];
		resultsTable.setModel(new javax.swing.table.DefaultTableModel(
				obj,
				new String[]{"Results"}
				) {
					private static final long serialVersionUID = 551317842147510106L;
			Class[] types = new Class[]{
					java.lang.String.class
			};
			boolean[] canEdit = new boolean[]{
					false
			};

			@SuppressWarnings("unchecked")
			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});

		for (int i = 0; i < output.size(); i++) {
			resultsTable.setValueAt(output.elementAt(i), i, 0);
		}
	}

}
