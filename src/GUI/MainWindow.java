package GUI;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame implements ActionListener, ChangeListener{
	
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
	
	ScreenAndZonesPanel screeAndZones = new ScreenAndZonesPanel();
	
	JPanel statusPanel = new JPanel(new GridBagLayout());
	JTextArea statusConsole = new JTextArea("Test !\n");
	JScrollPane statusScroll = new JScrollPane(statusConsole);

	
	public MainWindow(){
		super("My Window");
		setBounds(300, 300, 500, 500);
		setResizable(false);
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
		globalPanel.add(connectionStatus, globalPanelGridBagConstraints);
		
		/*
		 * Top and Bottom LEDs Panel
		 */
		GridBagConstraints topBottomLedPanelGridBagConstraints = new GridBagConstraints();
		topBottomLedPanelGridBagConstraints.insets = new Insets(0, 10, 10, 10);
		topBottomLedPanelGridBagConstraints.anchor = GridBagConstraints.LINE_START;
		
		topLedsActivated.addActionListener(this);
		topBottomLedPanelGridBagConstraints.gridx = 0; topBottomLedPanelGridBagConstraints.gridy = 0;
		topbottomLedPanel.add(topLedsActivated, topBottomLedPanelGridBagConstraints);	
		
		topLedsSpinner.setEnabled(false);
		topLedsSpinner.setPreferredSize(new Dimension(35,20));
		topBottomLedPanelGridBagConstraints.gridx = 1; topBottomLedPanelGridBagConstraints.gridy = 0;
		topbottomLedPanel.add(topLedsSpinner, topBottomLedPanelGridBagConstraints);
		
		topBottomLedPanelGridBagConstraints.gridx = 0; topBottomLedPanelGridBagConstraints.gridy = 1;
		topbottomLedPanel.add(bottomLedsActivated, topBottomLedPanelGridBagConstraints);
		
		bottomLedsSpinner.setEnabled(false);
		bottomLedsSpinner.setPreferredSize(new Dimension(35,20));
		bottomLedsSpinner.addChangeListener(this);
		topBottomLedPanelGridBagConstraints.gridx = 1; topBottomLedPanelGridBagConstraints.gridy = 1;
		topbottomLedPanel.add(bottomLedsSpinner, topBottomLedPanelGridBagConstraints);
		
		topbottomLedPanel.setBorder(new TitledBorder("Top & Bottom LEDs"));
		globalPanelGridBagConstraints.gridx = 0; globalPanelGridBagConstraints.gridy = 2;
		globalPanelGridBagConstraints.gridwidth = 3;
		globalPanelGridBagConstraints.fill = GridBagConstraints.BOTH;
		globalPanel.add(topbottomLedPanel, globalPanelGridBagConstraints);
		
		/*
		 * Lateral LEDs Panel
		 */
		GridBagConstraints lateralLedPanelGridBagConstraints = new GridBagConstraints();
		lateralLedPanel.setBorder(new TitledBorder("Lateral LEDs"));
		
		lateralLedPanelGridBagConstraints.gridx = 0; lateralLedPanelGridBagConstraints.gridy = 0;
		lateralLedPanelGridBagConstraints.insets = new Insets(0, 10, 10, 10);
		lateralLedPanel.add(lateralLedsText,lateralLedPanelGridBagConstraints);
		
		lateralLedsSpinner.setPreferredSize(new Dimension(35, 20));
		lateralLedPanelGridBagConstraints.gridx = 1; lateralLedPanelGridBagConstraints.gridy = 0;
		lateralLedPanel.add(lateralLedsSpinner, lateralLedPanelGridBagConstraints);
		globalPanelGridBagConstraints.gridx = 3; globalPanelGridBagConstraints.gridy = 2;
		globalPanelGridBagConstraints.gridwidth = 1;
		globalPanelGridBagConstraints.fill = GridBagConstraints.VERTICAL;
		globalPanel.add(lateralLedPanel, globalPanelGridBagConstraints);
		
		/* 
		 * Screen and Zones Panel
		 */
		globalPanelGridBagConstraints.gridx = 0; globalPanelGridBagConstraints.gridy = 3;
		lateralLedPanelGridBagConstraints.insets = new Insets(0, 0, 0, 0);
		globalPanelGridBagConstraints.gridwidth = 4;
		globalPanelGridBagConstraints.fill = GridBagConstraints.BOTH;
		globalPanel.add(screeAndZones,globalPanelGridBagConstraints);
		
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
		globalPanelGridBagConstraints.gridx = 0; globalPanelGridBagConstraints.gridy = 4;
		globalPanelGridBagConstraints.gridwidth = 4;
		globalPanelGridBagConstraints.fill = GridBagConstraints.BOTH;
		globalPanel.add(statusPanel, globalPanelGridBagConstraints);
		
		pack();
		setVisible(true);
		
//		screeAndZones.baseRectDrawing(screeAndZones.getGraphics(), this.getWidth(), Toolkit.getDefaultToolkit().getScreenSize());
		
		pack();
		
		
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

