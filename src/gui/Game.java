package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;

import controller.FieldController;
import exception.IllegalNumberException;

public class Game extends JFrame {

	private final JPanel contentPanel = new JPanel();
	
	private FieldController fieldCtrl;
	
	private ArrayList<JButton> buttons;
	private ImageFactory imgFactory;
	
	private int width;
	private int height;
	
	private int turn;

	/**
	 * Create the dialog.
	 */
	public Game(FieldController fieldCtrl) {
		
		this.fieldCtrl = fieldCtrl;
		
		width = 300;
		height = 300;
		
		buttons = new ArrayList<>();
		
		turn = 0;
		
		UIManager.put("Button.disabledText", new ColorUIResource(ColorScheme.DESELECT));
		
		
		
		initGui();
		
		int imgSize = width / fieldCtrl.getNumberOfPlayers();
		imgFactory = new ImageFactory(imgSize, imgSize,
				fieldCtrl.getNumberOfPlayers());
		
	}
	
	private void initGui() {
		setPanelName();
		setIconImage(Images.getLogo());
		setBounds(100, 100, width, height);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(ColorScheme.BACKGROUND);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();

		contentPanel.setLayout(gbl_contentPanel);
		
		
		for (int y = 0; y < fieldCtrl.getSize(); y++) {
		
			for (int x = 0; x < fieldCtrl.getSize(); x++) {
				JButton btnNewButton_1 = new JButton("");
				
				JPanel pane = new JPanel();
				pane.setLayout(new BorderLayout(0, 0));
				
				btnNewButton_1.setForeground(ColorScheme.TEXT_FIELD);
				
				btnNewButton_1.setBackground(ColorScheme.BACKGROUND);
				btnNewButton_1.setFocusPainted(false);
				
				contentPanel.addComponentListener(new ComponentListener() {

		            @Override
		            public void componentHidden(ComponentEvent arg0) {
		            }

		            @Override
		            public void componentMoved(ComponentEvent arg0) {
		            }

		            @Override
		            public void componentResized(ComponentEvent arg0) {
		                int size = getImageSize();
		                
		                try {
		                	imgFactory.setDimensions(size, size);
		                	
		                	btnNewButton_1.setIcon(new ImageIcon(imgFactory.getImage(
		                			fieldCtrl.getField().getSquareValue(buttons.indexOf(btnNewButton_1)))));
							
						} catch (IllegalNumberException e) {
							e.printStackTrace();
						}

		                
		                
		                contentPanel.revalidate();
		            }

		            @Override
		            public void componentShown(ComponentEvent e) {

		            }
		        });
				
				btnNewButton_1.setBorder(new LineBorder(ColorScheme.BUTTON, 1));
				
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buttonPress(btnNewButton_1);
					}
				});
				
				buttons.add(btnNewButton_1);
				
				pane.add(btnNewButton_1);
				
				int border = 9 / fieldCtrl.getSize();
				
				GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
				gbc_btnNewButton_1.gridx = x;
				gbc_btnNewButton_1.gridy = y;
				gbc_btnNewButton_1.weightx = 1;
				gbc_btnNewButton_1.weighty = 1;
				gbc_btnNewButton_1.insets = new Insets(border, border, border, border);
				gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
				contentPanel.add(pane, gbc_btnNewButton_1);
			}
		
		}
	}
	
	private void setPanelName() {
		setTitle("Game - players turn: " + (turn % fieldCtrl.getNumberOfPlayers() + 1));
		turn++;
	}
	
	private void buttonPress(JButton button) {
		int index = buttons.indexOf(button);
		
		try {
			int result = fieldCtrl.place(index);
			
			switch (result) {
				case -2:
					break;
				case -1:
					setImageIcon(index);
					draw();
					break;
				case 0:
					setImageIcon(index);
					setPanelName();
					break;
				default:
					setImageIcon(index);
					win(result, index);
					
			}
			
		} catch (IllegalNumberException e) {
			e.printStackTrace();
		}
		
	}
	
	private void setImageIcon(int index) throws IllegalNumberException {
		buttons.get(index).setIcon(new ImageIcon(imgFactory.getImage(fieldCtrl.getField().getSquareValue(index))));
	}
	
	private int getImageSize() {
		int width = contentPanel.getWidth();
        int height = contentPanel.getHeight();
        
        
        if (width > height) {
        	width = height;
        }
        
        width = (width / fieldCtrl.getSize()) / 2;
        
        return width;
	}
	
	private void win(int result, int index) {
		
		
		try {
			int[] winningSquares = fieldCtrl.getField().getLongestConnectingSquares(index);
			
			Color color = ColorScheme.BUTTON_HIGHTLIGHT;
			
			buttons.get(index).setBackground(color);
			
			for (int i = 0; i < winningSquares.length; i++) {
				buttons.get(winningSquares[i]).setBackground(color);
			}
			
			setTitle("Player number " + (result + 1) + " won");
			
			end();
		} catch (IllegalNumberException e) {
			e.printStackTrace();
		}

		
		
	}
	
	private void draw() {
		setTitle("Draw");
		end();
	}
	
	private void end() {
		buttons.forEach(button -> button.setEnabled(false));
	}

}
