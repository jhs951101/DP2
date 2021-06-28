/**
 * Created on 2016. 3. 22.
 * @author cskim -- hufs.ac.kr, Dept of CSE
 * Copy Right -- Free for Educational Purpose
 */
package decorator;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

public class DecoratorImageFrameView extends JFrame {

	private static final long serialVersionUID = 1L;
	static final double DEFAULT_RATIO = 4d/3d;
	static final double HD_RATIO = 16d/9d;
	
	static final int DEFAULT_WIDTH = 1200;
	static final int DEFAULT_HEIGHT = 900;
	static final int HD_WIDTH = 1600;
	static final int HD_HEIGHT = 900;
	static final int SIDE_WIDTH =200;
	
	private DecoratorImageController controller = null;
	private DecoratorImageModel model = null;
	private DecoratorImageFrameView thisClass = this;
	private DrawPanelView drawPanelView = null;

	private JFileChooser jFileChooser1 = null;
	private JFileChooser jFileChooser2 = null;
	private String defaultDir = "C:/home/cskim/temp/";

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem jmiOpen;
	private JMenuItem jmiSave;
	private JMenuItem jmiSaveAs;
	private JMenuItem jmiExit;
	private JMenu mnHsb;
	private JMenuItem jmiHuePlus;
	private JMenuItem jmiHueMinus;
	private JMenuItem jmiSatPlus;
	private JMenuItem jmiSatMinus;
	private JMenuItem jmiBriPlus;
	private JMenuItem jmiBriMinus;
	private JMenu mnEffect;
	private JMenuItem jmiBlur;
	private JMenuItem jmiSharpen;
	private JMenuItem jmiEdge;
	private JPanel statusPanel;
	private JMenuItem jmiEmboss;

	/**
	 * Create the frame.
	 */
	public DecoratorImageFrameView(DecoratorImageController controller, DecoratorImageModel model) {
		super();
		this.controller = controller;
		this.model = model;
		this.model.setFrameView(this);
		
		initialize();
	}
	
	private void initialize() {
		
		this.setTitle("Image View Decoration");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 50, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("File ");
		menuBar.add(mnFile);
		
		jmiOpen = new JMenuItem("Open ");
		jmiOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpenAction();
			}
		});
		mnFile.add(jmiOpen);
		
		jmiSave = new JMenuItem("Save ");
		jmiSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveAction();
			}
		});
		mnFile.add(jmiSave);
		
		jmiSaveAs = new JMenuItem("Save As...");
		jmiSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveAsAction();
			}
		});
		mnFile.add(jmiSaveAs);
		
		mnFile.addSeparator();
		jmiExit = new JMenuItem("Exit ");
		jmiExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnFile.add(jmiExit);
		
		mnHsb = new JMenu("HSB ");
		menuBar.add(mnHsb);
		
		jmiHuePlus = new JMenuItem("Hue +");
		jmiHuePlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model = new HueUp(model);
				model.setMainImage(model.changeImage());
				drawPanelView.setModel(model);
				drawPanelView.repaint();
			}
		});
		mnHsb.add(jmiHuePlus);
		
		jmiHueMinus = new JMenuItem("Hue -");
		jmiHueMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model = new HueDown(model);
				model.setMainImage(model.changeImage());
				drawPanelView.setModel(model);
				drawPanelView.repaint();
			}
		});
		mnHsb.add(jmiHueMinus);
		
		jmiSatPlus = new JMenuItem("Saturation +");
		jmiSatPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				model = new SaturationUp(model);
				model.setMainImage(model.changeImage());
				drawPanelView.setModel(model);
				drawPanelView.repaint();
			}
		});
		mnHsb.add(jmiSatPlus);
		
		jmiSatMinus = new JMenuItem("Saturation -");
		jmiSatMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model = new SaturationDown(model);
				model.setMainImage(model.changeImage());
				drawPanelView.setModel(model);
				drawPanelView.repaint();
			}
		});
		mnHsb.add(jmiSatMinus);
		
		jmiBriPlus = new JMenuItem("Brightness +");
		jmiBriPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model = new BrightnessUp(model);
				model.setMainImage(model.changeImage());
				drawPanelView.setModel(model);
				drawPanelView.repaint();
			}
		});
		mnHsb.add(jmiBriPlus);
		
		jmiBriMinus = new JMenuItem("Brightness -");
		jmiBriMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model = new BrightnessDown(model);
				model.setMainImage(model.changeImage());
				drawPanelView.setModel(model);
				drawPanelView.repaint();
			}
		});
		mnHsb.add(jmiBriMinus);
		
		mnEffect = new JMenu("Effect ");
		menuBar.add(mnEffect);
		
		jmiBlur = new JMenuItem("Blur  ");
		jmiBlur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model = new Blur(model);
				model.setMainImage(model.changeImage());
				drawPanelView.setModel(model);
				drawPanelView.repaint();
			}
		});
		mnEffect.add(jmiBlur);
		
		jmiSharpen = new JMenuItem("Sharpen");
		jmiSharpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model = new Sharpen(model);
				model.setMainImage(model.changeImage());
				drawPanelView.setModel(model);
				drawPanelView.repaint();
			}
		});
		mnEffect.add(jmiSharpen);
		
		jmiEdge = new JMenuItem("Edge Detect");
		jmiEdge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model = new EdgeDetect(model);
				model.setMainImage(model.changeImage());
				drawPanelView.setModel(model);
				drawPanelView.repaint();
			}
		});
		mnEffect.add(jmiEdge);
		
		jmiEmboss = new JMenuItem("Emboss ");
		jmiEmboss.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model = new Emboss(model);
				model.setMainImage(model.changeImage());
				drawPanelView.setModel(model);
				drawPanelView.repaint();
			}
		});
		mnEffect.add(jmiEmboss);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		jFileChooser1 = new JFileChooser(defaultDir);
		jFileChooser1.setDialogTitle("Open Image File");
		jFileChooser1.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "JPG", "jpeg", "png", "PNG"));

		jFileChooser2 = new JFileChooser(defaultDir);
		jFileChooser2.setDialogType(JFileChooser.SAVE_DIALOG);
		jFileChooser2.setDialogTitle("Save As ...");
		
		drawPanelView = new DrawPanelView(model);
		contentPane.add(drawPanelView, BorderLayout.CENTER);
		controller.setDrawPanelView(drawPanelView);
		
		statusPanel = new JPanel();
		statusPanel.setPreferredSize(new Dimension(200, 500));
		contentPane.add(statusPanel, BorderLayout.EAST);
		statusPanel.setLayout(new BorderLayout(0, 0));
	
	}

	/**
	 * 
	 */
	protected void SaveAsAction() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 */
	protected void SaveAction() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 */
	protected void OpenAction() {
		if (jFileChooser1.showOpenDialog(this) ==
				JFileChooser.APPROVE_OPTION) {
				File selFile = jFileChooser1.getSelectedFile();
				model.setSaveFile(selFile);
				controller.loadNDrawImageFile();
				int imgWidth = model.getMainImage().getWidth();
				int imgHeight = model.getMainImage().getHeight();
				double imgRatio = (double)imgWidth/imgHeight;
				if (Math.abs(imgRatio-DEFAULT_RATIO) <Math.abs(imgRatio-HD_RATIO)){
					this.setBounds(100, 50, DEFAULT_WIDTH+SIDE_WIDTH, DEFAULT_HEIGHT);
				}
				else {
					this.setBounds(100, 50, HD_WIDTH+SIDE_WIDTH, HD_HEIGHT);
				}
				drawPanelView.repaint();
				
				/*
				float[] hsb = Utils.maxHSB(model.getMainImage());
				System.out.println("maxHSB=("+hsb[0]+", "+hsb[1]+", "+hsb[2]+")");
				hsb = Utils.minHSB(model.getMainImage());
				System.out.println("minHSB=("+hsb[0]+", "+hsb[1]+", "+hsb[2]+")");
				*/
			}
		
	}

}
