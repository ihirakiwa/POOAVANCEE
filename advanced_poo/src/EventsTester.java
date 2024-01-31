
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class EventsTester {
	private final int val = 7;
	private final JFrame mainFrame;
	private  JFrame testFrame;
	private final JButton btnNewTestFrame;
	private final JButton razButton;
	private JTextArea textArea[];
	private int compteur = 1;
	private int compteurRaz = 1;
	private final String[] names = {"MouseListener","WindowFocusListener","WindowListener","KeyListener","WindowStateListener","MouseWheelListener","MouseMotionListner" };
	
	
	
	public EventsTester(){
		mainFrame = createFrame();
		testFrame = createNewTestFrame();
		btnNewTestFrame = new JButton("Nouvelle fenêtre");
		razButton = new JButton("RAZ Compteur");
		textArea = new JTextArea[val];
		for(int i = 0; i <val ; i ++) {
			textArea[i] = new JTextArea();
		}
		placeComponents();
		//CONTROLLERS
		addActionListeners();

	}
	
	//COMMANDES
	
	public JFrame createFrame() {
		JFrame f = new JFrame("Tests sur les événements");
		f.setPreferredSize(new Dimension(900, 900));
		return f;
	}
	
	public JFrame createNewTestFrame() {
		JFrame f = new JFrame("Zone de test");
		f.setPreferredSize(new Dimension(200, 100));
		return f;
	}
	
	public void display() {
		refreshView();
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}
	
	//TOOLS 
	
	private void refreshView() {
		
	}
	
	private void placeComponents() {
		JPanel p = new JPanel();
		{//--
			p.add(btnNewTestFrame);
			p.add(razButton);
		}
		mainFrame.add(p,BorderLayout.NORTH);
		p = new JPanel(new GridLayout(0,3));
		{//--
			for(int i = 0;i < val ; i++) {
				JScrollPane jp = new JScrollPane(textArea[i]);
				Border border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 1),names[i]);
				jp.setBorder(border);
				p.add(jp);
				
			}
		}//--
		mainFrame.add(p,BorderLayout.CENTER);
	}
	
	
	
	private void addActionListeners() {
		mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mainFrame.dispose();
                testFrame.dispose();
            }
        });
		
		btnNewTestFrame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				testFrame.dispose();
				testFrame = createNewTestFrame();
				testFrame.pack();
				testFrame.setLocationRelativeTo(null);
				testFrame.setVisible(true);
				testFrameListeners(testFrame);
			}
		});
		
				
		razButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 0 ; i < val; i++) {
					textArea[i].append("---" + compteurRaz + "---\n");
				}
				compteur = 1;
				compteurRaz++;
				
			}
		});
	}

	private void testFrameListeners(JFrame f) {
		f.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				String mot = e.paramString().split(",")[0];
				textArea[0].append( compteur+ " " + mot + "\n");
				compteur++;
			}

			@Override
			public void mousePressed(MouseEvent e) {
				String mot = e.paramString().split(",")[0];
				textArea[0].append( compteur+ " " + mot + "\n");
				compteur++;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				String mot = e.paramString().split(",")[0];
				textArea[0].append( compteur+ " " + mot + "\n");
				compteur++;
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				String mot = e.paramString().split(",")[0];
				textArea[0].append( compteur+ " " + mot + "\n");
				compteur++;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				String mot = e.paramString().split(",")[0];
				textArea[0].append( compteur+ " " + mot + "\n");
				compteur++;
			}
		});
		
		f.addWindowFocusListener(new WindowFocusListener() {
			
			@Override
			public void windowLostFocus(WindowEvent e) {
				String mot = e.paramString().split(",")[0];
				textArea[1].append( compteur+ " " + mot + "\n");
				compteur++;				
			}
			
			@Override
			public void windowGainedFocus(WindowEvent e) {
				String mot = e.paramString().split(",")[0];
				textArea[1].append( compteur+ " " + mot + "\n");
				compteur++;				
			}
		});
		
		f.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				String mot = e.paramString().split(",")[0];
				textArea[2].append( compteur+ " " + mot + "\n");
				compteur++;
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				String mot = e.paramString().split(",")[0];
				textArea[2].append( compteur+ " " + mot + "\n");
				compteur++;				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				String mot = e.paramString().split(",")[0];
				textArea[2].append( compteur+ " " + mot + "\n");
				compteur++;				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				String mot = e.paramString().split(",")[0];
				textArea[2].append( compteur+ " " + mot + "\n");
				compteur++;				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				String mot = e.paramString().split(",")[0];
				textArea[2].append( compteur+ " " + mot + "\n");
				compteur++;				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				String mot = e.paramString().split(",")[0];
				textArea[2].append( compteur+ " " + mot + "\n");
				compteur++;				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				String mot = e.paramString().split(",")[0];
				textArea[2].append( compteur+ " " + mot + "\n");
				compteur++;				
			}
		});

		f.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				String mot = e.paramString().split(",")[0];
				textArea[3].append( compteur+ " " + mot + "\n");
				compteur++;				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				String mot = e.paramString().split(",")[0];
				textArea[3].append( compteur+ " " + mot + "\n");
				compteur++;				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				String mot = e.paramString().split(",")[0];
				textArea[3].append( compteur+ " " + mot + "\n");
				compteur++;				
			}
		});
		
		f.addWindowStateListener(new WindowStateListener() {
			
			@Override
			public void windowStateChanged(WindowEvent e) {
				String mot = e.paramString().split(",")[0];
				textArea[4].append( compteur+ " " + mot + "\n");
				compteur++;				
			}
		});
		
		f.addMouseWheelListener(new MouseWheelListener() {
			
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				String mot = e.paramString().split(",")[0];
				textArea[5].append( compteur+ " " + mot + "\n");
				compteur++;				
			}
		});
		
		f.addMouseMotionListener(new  MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				String mot = e.paramString().split(",")[0];
				textArea[6].append( compteur+ " " + mot + "\n");
				compteur++;				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				String mot = e.paramString().split(",")[0];
				textArea[6].append( compteur+ " " + mot + "\n");
				compteur++;				
			}
		});
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new EventsTester().display();
			}
		});
	}
	
}
