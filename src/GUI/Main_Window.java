package GUI;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class Main_Window extends JFrame implements ActionListener, ChangeListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JPanel globalPanel = new JPanel(new GridBagLayout());
	JButton scanButton = new JButton("Scan");
	JButton connectionButton = new JButton("Connect");
	JComboBox portBox = new JComboBox();
	JLabel connectionStatus = new JLabel("Status: Disconnected");
	
	JPanel topbottomLedPanel = new JPanel(new GridBagLayout());
	SpinnerModel topLedsSM = new SpinnerNumberModel(0, 0, 64, 1);
	SpinnerModel bottomLedsSM = new SpinnerNumberModel(0, 0, 64, 1);
	JCheckBox topLedsActivated = new JCheckBox("Top LEDs");
	JSpinner topLedsSpinner = new JSpinner(topLedsSM);
	JCheckBox bottomLedsActivated = new JCheckBox("Bottom LEDs");
	JSpinner bottomLedsSpinner = new JSpinner(bottomLedsSM);
	
	JPanel lateralLedPanel = new JPanel(new GridBagLayout());
	SpinnerModel lateralLedsSM = new SpinnerNumberModel(4, 1, 64, 1);
	JLabel lateralLedsText = new JLabel("Lateral LEDs");
	JSpinner lateralLedsSpinner = new JSpinner(lateralLedsSM);
	
	JPanel statusPanel = new JPanel();
	JTextArea statusConsole = new JTextArea("Test !\n");
	JScrollPane statusScroll = new JScrollPane(statusConsole);

	
	public Main_Window(){
		super("My Window");
		setResizable(false);
		setBounds(500, 500, 500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GridBagConstraints globalPanelGridBagConstraints = new GridBagConstraints();
		globalPanelGridBagConstraints.insets = new Insets(10, 10, 10, 10);
		
		Container con = this.getContentPane();
		con.add(globalPanel);
	
		/*
		 * Connection Panel
		 */
		//scanButton.setMnemonic('S');
		scanButton.requestFocus();
		scanButton.addActionListener(this);
		globalPanelGridBagConstraints.gridx = 0; globalPanelGridBagConstraints.gridy = 0;
		globalPanelGridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
		globalPanelGridBagConstraints.fill = GridBagConstraints.NONE;
		globalPanel.add(scanButton, globalPanelGridBagConstraints);
		
		//connectionButton.setMnemonic('C');
		connectionButton.requestFocus();
		connectionButton.addActionListener(this);
		globalPanelGridBagConstraints.gridx = 1; globalPanelGridBagConstraints.gridy = 0;
		globalPanel.add(connectionButton, globalPanelGridBagConstraints);
		
		portBox.requestFocus();
		portBox.setPreferredSize(new Dimension(75, 25));
		globalPanelGridBagConstraints.gridx = 2; globalPanelGridBagConstraints.gridy = 0;
		globalPanel.add(portBox, globalPanelGridBagConstraints);
		
		connectionStatus.setForeground(Color.RED);
		globalPanelGridBagConstraints.gridx = 3; globalPanelGridBagConstraints.gridy = 0;
		globalPanelGridBagConstraints.anchor = GridBagConstraints.CENTER;
		globalPanel.add(connectionStatus, globalPanelGridBagConstraints);
		
		/*
		 * Top and Bottom LEDs Panel
		 */
		GridBagConstraints ledPanelGridBagConstraints = new GridBagConstraints();
		ledPanelGridBagConstraints.insets = new Insets(0, 10, 10, 10);
		ledPanelGridBagConstraints.anchor = GridBagConstraints.LINE_START;
		
		topLedsActivated.addActionListener(this);
		ledPanelGridBagConstraints.gridx = 0; ledPanelGridBagConstraints.gridy = 0;
		topbottomLedPanel.add(topLedsActivated, ledPanelGridBagConstraints);	
		
		topLedsSpinner.setEnabled(false);
		topLedsSpinner.setPreferredSize(new Dimension(35,20));
		ledPanelGridBagConstraints.gridx = 1; ledPanelGridBagConstraints.gridy = 0;
		topbottomLedPanel.add(topLedsSpinner, ledPanelGridBagConstraints);
		
		ledPanelGridBagConstraints.gridx = 0; ledPanelGridBagConstraints.gridy = 1;
		topbottomLedPanel.add(bottomLedsActivated, ledPanelGridBagConstraints);
		
		bottomLedsSpinner.setEnabled(false);
		bottomLedsSpinner.setPreferredSize(new Dimension(35,20));
		bottomLedsSpinner.addChangeListener(this);
		ledPanelGridBagConstraints.gridx = 1; ledPanelGridBagConstraints.gridy = 1;
		topbottomLedPanel.add(bottomLedsSpinner, ledPanelGridBagConstraints);
		
		topbottomLedPanel.setBorder(new TitledBorder("Top & Bottom LEDs"));
		globalPanelGridBagConstraints.gridx = 0; globalPanelGridBagConstraints.gridy = 2;
		globalPanelGridBagConstraints.gridwidth = 2;
		globalPanelGridBagConstraints.fill = GridBagConstraints.BOTH;
		globalPanel.add(topbottomLedPanel, globalPanelGridBagConstraints);
		
		/*
		 * Lateral LEDs Panel
		 */
		
		
		/* 
		 * Status Panel
		 */
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
		statusPanel.setBorder(new TitledBorder("Status"));
		statusConsole.setEditable(false);
		statusConsole.setLineWrap(true);
		statusConsole.setWrapStyleWord(true);
		statusConsole.setCaretPosition(statusConsole.getDocument().getLength());	
		statusScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		statusScroll.setPreferredSize(new Dimension(100,100));
		statusPanel.add(statusScroll);
		globalPanelGridBagConstraints.gridx = 0; globalPanelGridBagConstraints.gridy = 3;
		globalPanelGridBagConstraints.gridwidth = 4;
		globalPanelGridBagConstraints.fill = GridBagConstraints.BOTH;
		globalPanel.add(statusPanel, globalPanelGridBagConstraints);
		
		pack();
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent event){
		Object source = event.getSource();
		if(source == scanButton){
			statusConsole.append("Button pressed !\n");
		}
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

