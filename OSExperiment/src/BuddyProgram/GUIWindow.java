package BuddyProgram;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class GUIWindow extends JFrame{

	private int using_memory=0;
	private MemoryView mypanel;
	private JLabel memory_infoLabel;
	private JPanel process_list_panel;
	private int [][]m;
	private int [][]v;
	private MyMemoryViewJPanel memory_view_panel;
	private JLabel SystemInfoLabel ;
	private JTextArea process_info_text;
//	public JTextArea getProcess_info_text() {
//		return process_info_text;
//	}

	public void setProcess_info_text(String process_info_text) {
		this.process_info_text.setText(process_info_text);
	}
	private JLabel newLabelProcess[]=new JLabel[200];
	public int getUsing_memory() {
		return using_memory;
	}

	public void setUsing_memory(int using_memory) {
		this.using_memory = using_memory;
		mypanel.setUsing_memory(using_memory);
		memory_infoLabel.setText("<html><body>总内存：    16MB<br/>   已用   ：       "+getUsing_memory()+"KB <br/>可用   ："
		+(int)(Math.pow(2, 14)-getUsing_memory())+"KB</body></html>");
	}

	public void addProcessList(String process_name,int process_time,int process_size,int x,int y){
		newLabelProcess[x*10+y]=new JLabel("进程名称："+process_name+" 进程实际大小："+process_size+"KB   实际占用内存："
	+(int)Math.pow(2,x)+"KB  占用内存时间："+process_time+"ms");
		newLabelProcess[x*10+y].setForeground(Color.RED);
		newLabelProcess[x*10+y].setFont(new Font("微软雅黑", Font.BOLD, 14));
		newLabelProcess[x*10+y].setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		process_list_panel.add(newLabelProcess[x*10+y]);
		
		
	}
	public void removeProcessList(int x,int y){
		process_list_panel.remove(newLabelProcess[x*10+y]);
		process_list_panel.setVisible(false);
		process_list_panel.setVisible(true);
	}
	public void changedMemoryState(int i,int j,int color_mark,int [][]m,int [][]v){
		this.m=m;
		this.v=v;
		memory_view_panel.setI(i);
		memory_view_panel.setJ(j);
		memory_view_panel.setM(m);
		memory_view_panel.setV(v);
		memory_view_panel.setRemark_color(color_mark);
	}
	public void changedNumberOfProcess(int number){
		SystemInfoLabel.setText("进程数："+number+"  物理内存："+(int)(100*getUsing_memory()/Math.pow(2, 14))+"%");
	}
	public GUIWindow(ActionListener newThreadListener){
		java.net.URL imgURL = GUIWindow.class.getResource("/image/panelset.png");
		ImageIcon image = new ImageIcon(imgURL);
		//ImageIcon image = new ImageIcon(getClass().getResource("image/panelset.png"));
		setIconImage(image.getImage());
		getContentPane().setFont(new Font("微软雅黑", Font.PLAIN, 12));
		setFont(new Font("微软雅黑", Font.BOLD, 14));
		this.setSize(900,600);
		this.setTitle("\u6A21\u62DF\u4F19\u4F34\u7CFB\u7EDF\u5185\u5B58\u7BA1\u7406");
		getContentPane().setLayout(null);
		mypanel=new MemoryView(using_memory);
		mypanel.setBounds(33, 22, 119, 415);
		mypanel.setPreferredSize(new Dimension(120,440));
		mypanel.setBackground(Color.black);
		getContentPane().add(mypanel);

		JButton newThreadButton=new JButton("新建进程");
		newThreadButton.setFont(new Font("微软雅黑", Font.BOLD, 12));
		newThreadButton.setToolTipText("生成随机大小及时长的进程");
		newThreadButton.setBounds(450, 398, 86, 28);
		JButton killThreadButton=new JButton("终止进程");
		killThreadButton.setFont(new Font("微软雅黑", Font.BOLD, 12));
		killThreadButton.setToolTipText("强制终止进程并释放内存空间");
		killThreadButton.setBounds(587, 398, 86, 28);
		getContentPane().add(newThreadButton);
		getContentPane().add(killThreadButton);
		killThreadButton.setEnabled(false);
		newThreadButton.addActionListener(newThreadListener);
		
		memory_infoLabel = new JLabel("<html><body>总内存：    16MB<br/>   已用   ：       "+
		getUsing_memory()+"KB <br/>可用   ："+(int)(Math.pow(2, 14)-getUsing_memory())+"KB</body></html>");
		memory_infoLabel.setForeground(Color.BLACK);
		memory_infoLabel.setFont(new Font("微软雅黑", Font.BOLD, 12));
		memory_infoLabel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "\u7269\u7406\u5185\u5B58(KB)", TitledBorder.LEADING, TitledBorder.TOP, new Font("微软雅黑", Font.BOLD, 14), Color.black));
		memory_infoLabel.setBackground(Color.LIGHT_GRAY);
		memory_infoLabel.setBounds(33, 449, 119, 86);
		getContentPane().add(memory_infoLabel);
		
		memory_view_panel = new MyMemoryViewJPanel(14,0,1,m,v);
		memory_view_panel.setBackground(SystemColor.inactiveCaption);
		memory_view_panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		memory_view_panel.setBounds(183, 39, 661, 41);
		getContentPane().add(memory_view_panel);
		
		process_list_panel = new JPanel();
		process_list_panel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		process_list_panel.setForeground(Color.GREEN);
		process_list_panel.setBackground(Color.WHITE);
		process_list_panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "\u8FDB\u7A0B\u5217\u8868", TitledBorder.LEADING, TitledBorder.TOP, new Font("微软雅黑", Font.BOLD, 14), Color.black));
		process_list_panel.setBounds(183, 129, 661, 237);
		
//		JLabel testLabel=new JLabel("进程名：ID123"+"   进程占用内存大小："
//				+"1234"+"  进程占用内存时间："+"1111");
//		testLabel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
//		testLabel.setForeground(Color.RED);
//		testLabel.setFont(new Font("微软雅黑", Font.BOLD, 14));
//		process_list_panel.add(testLabel);
		
		getContentPane().add(process_list_panel);
		
		JScrollPane scrollPane = new JScrollPane();
		process_list_panel.add(scrollPane);
		
		JList list = new JList();
		process_list_panel.add(list);
		
		process_info_text = new JTextArea();
		process_info_text.setBorder(new TitledBorder(null, "\u8FDB\u7A0B\u72B6\u6001\u680F", TitledBorder.LEADING, TitledBorder.TOP, new Font("微软雅黑", Font.BOLD, 14), Color.black));
		process_info_text.setEditable(false);
		process_info_text.setFont(new Font("微软雅黑", Font.BOLD, 16));
		process_info_text.setForeground(Color.RED);
		process_info_text.setText("\u8FDB\u7A0B\u4FE1\u606F\u8F93\u51FA");
		process_info_text.setBounds(183, 449, 661, 86);
		getContentPane().add(process_info_text);
		
		JLabel lblBuddy = new JLabel("buddy\u7B97\u6CD5\u5185\u5B58\u5757\u4F7F\u7528\u72B6\u6001");
		lblBuddy.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblBuddy.setBounds(183, 12, 170, 18);
		getContentPane().add(lblBuddy);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(499, 92, 25, 18);
		getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GREEN);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(648, 92, 25, 18);
		getContentPane().add(panel_1);
		
		JLabel label = new JLabel("\u7A7A\u95F2");
		label.setFont(new Font("微软雅黑", Font.BOLD, 12));
		label.setBounds(544, 92, 55, 18);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u5360\u7528");
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 12));
		label_1.setBounds(691, 92, 55, 18);
		getContentPane().add(label_1);
		
		JButton helpButton = new JButton("帮助");
		helpButton.setFont(new Font("微软雅黑", Font.BOLD, 12));
		helpButton.setBounds(723, 398, 86, 28);
		getContentPane().add(helpButton);
		
		SystemInfoLabel = new JLabel("\u8FDB\u7A0B\u6570\uFF1A0  \u7269\u7406\u5185\u5B58\uFF1A0%");
		SystemInfoLabel.setFont(new Font("微软雅黑", Font.BOLD, 12));
		SystemInfoLabel.setBounds(314, 537, 257, 28);
		getContentPane().add(SystemInfoLabel);
		helpButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				MyHelpFrame frame=new MyHelpFrame();
				frame.setVisible(true);
			}
			
		});
	}
}

class MemoryView extends JPanel{
	private int using_memory;
	public int getUsing_memory() {
		return using_memory;
	}
	public void setUsing_memory(int using_memory) {
		this.using_memory = using_memory;
		repaint();
	}
	public MemoryView(int using_memory){
		this.using_memory=using_memory;
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(10, 10, 100, 400);
		g.setColor(new Color(0,255,0));
		g.fillRect(15, 400-(int) (380*(1.0*using_memory/Math.pow(2,14))), 90, (int) (380*(1.0*using_memory/Math.pow(2,14))));
		
	}
}