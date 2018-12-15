package dynamic_beat;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.LayoutPath;
import java.util.List;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;

public class HelpMain extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton backbtn,nextbtn;
	private JTabbedPane imgLayout;
	private static Image Step1 = new ImageIcon("../img/step1Screen.jpg").getImage();
	private static Image Step2 = new ImageIcon("../img/step2Screen.jpg").getImage();
	private static Image Step3 = new ImageIcon("../img/step3Screen.jpg").getImage();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HelpMain frame = new HelpMain();
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
	public HelpMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		backbtn = new JButton("이 전");
		backbtn.setForeground(Color.BLACK);
		backbtn.setFont(new Font("굴림", Font.PLAIN, 18));
		backbtn.setBackground(Color.WHITE);
		backbtn.setBounds(177, 500, 100, 50);
		contentPane.add(backbtn);
		
		nextbtn = new JButton("다 음");
		nextbtn.setForeground(Color.BLACK);
		nextbtn.setFont(new Font("굴림", Font.PLAIN, 18));
		nextbtn.setBackground(Color.WHITE);
		nextbtn.setBounds(540, 500, 100, 50);
		contentPane.add(nextbtn);
		
		imgLayout = new JTabbedPane(JTabbedPane.CENTER);
		imgLayout.setBounds(12, 10, 760, 480);
		contentPane.add(imgLayout);
		
		
		backbtn.addActionListener(this);
		nextbtn.addActionListener(this);
	}
	//화면전환

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
