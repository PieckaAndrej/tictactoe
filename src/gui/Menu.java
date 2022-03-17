package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.FieldController;
import exception.IllegalNumberException;
import exception.SmallFieldException;

public class Menu extends JFrame {

	private JPanel contentPane;
	
	private FieldController fieldCtrl;
	private JTextField fieldSize;
	private JTextField winningLength;
	private JTextField playerNumber;
	private JLabel errorLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.setProperty("sun.java2d.uiScale", "1.0");
					Menu frame = new Menu();
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		
		
		initGui();
		
	}
	
	private void initGui() {
		Font font1 = new Font("SansSerif", Font.BOLD, 20);
		
		setIconImage(Images.getLogo());
		
		setTitle("TicTacToe");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(ColorScheme.BACKGROUND);
		contentPane.setBorder(new EmptyBorder(0, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel p = new JPanel(new GridBagLayout());
		p.setBackground(ColorScheme.BACKGROUND);
		
		
		
		
		contentPane.add(p, BorderLayout.NORTH);
		
		Box verticalBox_2 = Box.createVerticalBox();
		
		contentPane.add(verticalBox_2, BorderLayout.CENTER);
		

		
		
		Box horizontalBox = Box.createHorizontalBox();
		verticalBox_2.add(horizontalBox);
		
		Box verticalBox = Box.createVerticalBox();
		horizontalBox.add(verticalBox);
		
		JPanel panel_4 = new JPanel();
		panel_4.setOpaque(false);
		verticalBox.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Field size");
		lblNewLabel_1.setForeground(ColorScheme.BUTTON_HIGHTLIGHT);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(font1);
		panel_4.add(lblNewLabel_1, BorderLayout.CENTER);
		lblNewLabel_1.setAlignmentX(0.5f);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel_4.add(horizontalStrut, BorderLayout.EAST);
		
		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		verticalBox.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Winning length");
		lblNewLabel_1_1_1.setForeground(ColorScheme.BUTTON_HIGHTLIGHT);
		lblNewLabel_1_1_1.setBackground(ColorScheme.BACKGROUND);
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1.setFont(font1);
		panel_3.add(lblNewLabel_1_1_1, BorderLayout.CENTER);
		lblNewLabel_1_1_1.setAlignmentX(0.5f);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panel_3.add(horizontalStrut_1, BorderLayout.EAST);
		
		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		verticalBox.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1_1 = new JLabel("Player number");
		lblNewLabel_1_1.setForeground(ColorScheme.BUTTON_HIGHTLIGHT);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setFont(font1);
		panel_2.add(lblNewLabel_1_1, BorderLayout.CENTER);
		lblNewLabel_1_1.setAlignmentX(0.5f);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		panel_2.add(horizontalStrut_2, BorderLayout.EAST);
		
		Box verticalBox_1 = Box.createVerticalBox();
		verticalBox_1.setAlignmentX(1.0f);
		horizontalBox.add(verticalBox_1);
		
		fieldSize = new JTextField();
		fieldSize.setBackground(ColorScheme.TEXT_FIELD);
		fieldSize.setHorizontalAlignment(SwingConstants.CENTER);
		fieldSize.setBorder(new LineBorder(ColorScheme.BACKGROUND, 1));
		fieldSize.setText("3");
		fieldSize.setFont(font1);
		fieldSize.setColumns(10);
		verticalBox_1.add(fieldSize);
		
		winningLength = new JTextField();
		winningLength.setBackground(ColorScheme.TEXT_FIELD);
		winningLength.setHorizontalAlignment(SwingConstants.CENTER);
		winningLength.setBorder(new LineBorder(ColorScheme.BACKGROUND, 1));
		winningLength.setText("3");
		winningLength.setFont(font1);
		winningLength.setColumns(10);
		verticalBox_1.add(winningLength);
		
		playerNumber = new JTextField();
		playerNumber.setBackground(ColorScheme.TEXT_FIELD);
		playerNumber.setBorder(new LineBorder(ColorScheme.BACKGROUND, 1));
		playerNumber.setHorizontalAlignment(SwingConstants.CENTER);
		playerNumber.setText("2");
		playerNumber.setFont(font1);
		playerNumber.setColumns(10);
		verticalBox_1.add(playerNumber);
		
		errorLabel = new JLabel(" ");
		errorLabel.setAlignmentY(0.0f);
		errorLabel.setAlignmentX(0.5f);
		errorLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		errorLabel.setForeground(ColorScheme.BUTTON_HIGHTLIGHT);
		verticalBox_2.add(errorLabel);
		
		JLabel lblNewLabel = new JLabel("TicTacToe");
		lblNewLabel.setForeground(ColorScheme.BUTTON);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(font1);
		
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.gridheight = 0;
		gbc_label_2.gridwidth = 2;
		gbc_label_2.gridy = 0;
		gbc_label_2.gridx = 0;
		p.add(lblNewLabel, gbc_label_2);
		
		JLabel picLabel = new JLabel(new ImageIcon(Images.getBG()));
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1.0;
		gbc.gridwidth = 2;
		gbc.gridheight = 0;
		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		
		p.add(picLabel, gbc);
		
		
		JButton btnNewButton = new JButton("Play");
		btnNewButton.setForeground(ColorScheme.BACKGROUND);
		btnNewButton.setBackground(ColorScheme.BUTTON);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setFont(font1);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				play();
			}
		});
		contentPane.add(btnNewButton, BorderLayout.SOUTH);
	}
	
	private void play() {
		
		try {
			try {
				fieldCtrl = new FieldController(Integer.parseInt(winningLength.getText()));
				
				try {
					int players = Integer.parseInt(playerNumber.getText());
					
					if (players > Images.getMaxNumberOfPlayers()) {
						errorLabel.setText("Max player number is " + Images.getMaxNumberOfPlayers());
						playerNumber.setBackground(ColorScheme.DESELECT);
					}
					else {
						fieldCtrl.setNumberOfPlayers(Integer.parseInt(playerNumber.getText()));
						
						try {
							if (Integer.parseInt(fieldSize.getText()) > 15) {
								errorLabel.setText("No");
								fieldSize.setBackground(ColorScheme.DESELECT);
							}
							else {
								fieldCtrl.initField(Integer.parseInt(fieldSize.getText()));
								
								resetErrorMessage();
								
								Game dialog = new Game(fieldCtrl);
								dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								dialog.setVisible(true);
							}
							

						} catch (SmallFieldException e) {
							errorLabel.setText("Field size cannot be smaller than winning size");
							fieldSize.setBackground(ColorScheme.DESELECT);
							winningLength.setBackground(ColorScheme.DESELECT);
						}
					}
					
				} catch (IllegalNumberException e1) {
					errorLabel.setText("Number of player cannot be smaller than 2");
					playerNumber.setBackground(ColorScheme.DESELECT);
				}

			} catch (IllegalNumberException e) {
				errorLabel.setText("Winning size cannot be smaller than 3");
				winningLength.setBackground(ColorScheme.DESELECT);
			}
			
		} catch (NumberFormatException e) {
			errorLabel.setText("Enter a valid number");
		}
			
	}
	
	private void resetErrorMessage() {
		errorLabel.setText(" ");
		
		winningLength.setBackground(ColorScheme.TEXT_FIELD);
		playerNumber.setBackground(ColorScheme.TEXT_FIELD);
		fieldSize.setBackground(ColorScheme.TEXT_FIELD);
	}

}
