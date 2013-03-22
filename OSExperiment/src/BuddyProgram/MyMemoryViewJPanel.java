package BuddyProgram;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;


public class MyMemoryViewJPanel extends JPanel {

	private int i;
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
		//repaint();
	}
	public int getJ() {
		return j;
	}
	public void setJ(int j) {
		this.j = j;
		//repaint();
	}
	private int j;
	private int remark_color;
	private int [][]m=new int[20][300];
	public void setM(int[][] m) {
		this.m = m;
	}
	public void setV(int[][] v) {
		this.v = v;
	}
	private int [][]v=new int[20][300];
	public void setRemark_color(int remark_color) {
		this.remark_color = remark_color;
		Graphics g =this.getGraphics();
		//this.paintComponent(g);
		this.paint(g);
	}
	public MyMemoryViewJPanel(int i,int j,int remark_color,int m[][],int v[][]){
		this.i=i;
		this.j=j;
		this.remark_color=remark_color;
		this.m=m;
		this.v=v;
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		for(int i=6;i<=14;i++){
			for(int j=0;j<Math.pow(2, 14)/Math.pow(2, i);j++){
				if(m[i][j]==1&&v[i][j]==0){
					g.setColor(Color.green);
					g.fillRect((int)(661*Math.pow(2, i)/Math.pow(2, 14)*j), 0, (int)(661*Math.pow(2, i)/Math.pow(2, 14)), 41);					
				}
				if(m[i][j]==1&&v[i][j]==1){
					g.setColor(Color.DARK_GRAY);
					g.fillRect((int)(661*Math.pow(2, i)/Math.pow(2, 14)*j), 0, (int)(661*Math.pow(2, i)/Math.pow(2, 14)), 41);
				}
			}
		}
	}
}
