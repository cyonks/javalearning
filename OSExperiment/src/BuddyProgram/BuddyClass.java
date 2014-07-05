package BuddyProgram;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.Timer;
/**
 * buddy算法
 * @author rong
 *
 */

public class BuddyClass {

	private int Memory_Or_Not[][]=new int[20][300];
	public int[][] getMemory_Or_Not() {
		return Memory_Or_Not;
	}
	public int[][] getValuable_Or_Not() {
		return Valuable_Or_Not;
	}
	private int Valuable_Or_Not[][]=new int[20][300];
	private int remain_memory=(int) Math.pow(2, 14);
	private int xMemlocate;
	private int yMemlocate;
	private int process_time;
	private GUIWindow myframe;
	private int number_of_thread=0;
	private Timer timer;// = new Timer(6000,new TimerAction());
	public int getNumber_of_thread() {
		return number_of_thread;
	}
	public void setNumber_of_thread(int number_of_thread) {
		this.number_of_thread = number_of_thread;
		myframe.changedNumberOfProcess(number_of_thread);
	}
	private ActionListener newThreadListener=new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) { 
			// TODO �Զ���ɵķ������
			int newProSize=(int) (Math.random()*6384+1);
			int thread_life_time=(int)(Math.random()*7000+1000);
			process_time=thread_life_time;
			Storage_Allocation(newProSize);
            
		}
	};
	public int getRemain_memory() {
		return remain_memory;
	}
	public void setRemain_memory(int remain_memory) {
		this.remain_memory = remain_memory;
	}
	public void InitData() {
		for (int i = 6; i <=14; i++) {
			for(int j=0;j<(int)(Math.pow(2, 14)/Math.pow(2, i));j++){
				Memory_Or_Not[i][j]=0;
				Valuable_Or_Not[i][j]=0;//�����û�ǿ���
				//myframe.changedMemoryState(i, j, 2);
				if(i==14&&j==0){
					Memory_Or_Not[i][j]=1;//�����ڴ��
					Valuable_Or_Not[i][j]=1;//����
					myframe.changedMemoryState(i, j, 1,getMemory_Or_Not(),getValuable_Or_Not());
				}
			}
			
			
		}
	}
	public void Storage_Allocation(int pro_size){
		int location = 0;
		int i=6;
		for (i = 6; i <=14; i++) {
			if(pro_size<=Math.pow(2, i)){
				location=i;
				break;
			}
			
		}
		if(i>14){
			System.out.println("out of memory!");
			return;
		}
		for(i=location;i<=14;i++){
			int flag=1;
			for(int j=0;j<Math.pow(2, 14)/Math.pow(2, i);j++){
				if(Memory_Or_Not[i][j]==1&&Valuable_Or_Not[i][j]==1){
					flag=0;
					if(i!=location){
						Memory_Or_Not[i][j]=0;
						Valuable_Or_Not[i][j]=0;
						//myframe.changedMemoryState(i, j, 2);
						for(i--,j=2*j;i>location;i--,j=j*2){
							
							Memory_Or_Not[i][j]=0;
							Valuable_Or_Not[i][j]=0;
							//myframe.changedMemoryState(i, j, 2);
							Memory_Or_Not[i][j+1]=1;
							Valuable_Or_Not[i][j+1]=1;
							myframe.changedMemoryState(i, j, 1,getMemory_Or_Not(),getValuable_Or_Not());
						}
						Memory_Or_Not[i][j]=1;
						Valuable_Or_Not[i][j]=0;
						myframe.changedMemoryState(i, j, 3,getMemory_Or_Not(),getValuable_Or_Not());
						Memory_Or_Not[i][j+1]=1;
						Valuable_Or_Not[i][j+1]=1;
						myframe.changedMemoryState(i, j, 1,getMemory_Or_Not(),getValuable_Or_Not());
					}
					else {
						Memory_Or_Not[i][j]=1;
						Valuable_Or_Not[i][j]=0;
						myframe.changedMemoryState(i, j, 3,getMemory_Or_Not(),getValuable_Or_Not());
					}
					System.out.println("i: "+i+"  j: "+j+"  m[i][j]: "+Memory_Or_Not[i][j]+"  v[i][j]"+Valuable_Or_Not[i][j]);
					System.out.println("location: "+location);
					xMemlocate=i;
					yMemlocate=j;
					remain_memory=(int) (remain_memory-Math.pow(2, location));
					
					myframe.setUsing_memory((int)Math.pow(2, 14)-remain_memory);
					setNumber_of_thread(getNumber_of_thread()+1);
					myframe.addProcessList("ID"+xMemlocate+yMemlocate, process_time, pro_size,xMemlocate,yMemlocate);
					myframe.setProcess_info_text("��̴����ɹ��� �����"+"ID"+xMemlocate+yMemlocate
							+" ��С��"+pro_size+"KB"+"  ռ���ڴ棺"+(int)Math.pow(2, i)+"KB "+" ռ��ʱ�䣺"+process_time+"ms");
					timer = new Timer(process_time,new TimerAction(xMemlocate,yMemlocate));
					timer.start();
					System.out.println(remain_memory);
					
					break;
				}
			}
			if(flag==0){
				break;
			}
		}
		if(i>14){
			System.out.println("cann't find the proper memory!");
			myframe.setProcess_info_text("�޷������½�̣��Ҳ����ʺ�"+pro_size+"KB��С���ڴ�飬��ȴ��ڴ��ͷţ�");
		}
//		else{
//			for(i=6;i<=14;i++){
//				for(int j=0;j<Math.pow(2, 14)/Math.pow(2, i);j++){
//					if(Memory_Or_Not[i][j]==1&&Valuable_Or_Not[i][j]==0){
//						myframe.changedMemoryState(i, j, 3,getMemory_Or_Not(),getValuable_Or_Not());
//					}
//					else if(Memory_Or_Not[i][j]==1&&Valuable_Or_Not[i][j]==1){
//						myframe.changedMemoryState(i, j, 1,getMemory_Or_Not(),getValuable_Or_Not());
//					}
//				}
//			}
//		}

	}
	public void Recover_Memery(int xlocate,int ylocate){
//		for(int i=6;i<=14;i++){
//			for(int j=0;j<Math.pow(2, 14)/Math.pow(2, i);j++){
//				System.out.print(" "+Memory_Or_Not[i][j]+Valuable_Or_Not[i][j]);
//			}
//			System.out.println();
//		}
//		System.out.println("recover the memory!");
		
		if(Memory_Or_Not[xlocate][ylocate]!=1){
			System.out.println("the district is not a memory!");
			
		}
		if(Memory_Or_Not[xlocate][ylocate]==1&&Valuable_Or_Not[xlocate][ylocate]==0){
			Valuable_Or_Not[xlocate][ylocate]=1;
			remain_memory=(int) (remain_memory+Math.pow(2, xlocate));
			myframe.setUsing_memory((int)Math.pow(2, 14)-remain_memory);
			setNumber_of_thread(getNumber_of_thread()-1);
			for(int i=xlocate,j=ylocate;i<14;i++,j=j/2){
				int flag=1;
				if(j%2!=0){
					if(Memory_Or_Not[i][j-1]==1&&Valuable_Or_Not[i][j-1]==1){
						flag=0;
						Memory_Or_Not[i][j-1]=0;
						Valuable_Or_Not[i][j-1]=0;
						//myframe.changedMemoryState(i, j-1, 2);
						Memory_Or_Not[i][j]=0;
						Valuable_Or_Not[i][j]=0;
						//myframe.changedMemoryState(i, j, 2);
						Memory_Or_Not[i+1][j/2]=1;
						Valuable_Or_Not[i+1][j/2]=1;
						myframe.changedMemoryState(i+1, j/2, 1,getMemory_Or_Not(),getValuable_Or_Not());
					}
				}
				else {
					if(Memory_Or_Not[i][j+1]==1&&Valuable_Or_Not[i][j+1]==1){
						flag=0;
						Memory_Or_Not[i][j+1]=0;
						Valuable_Or_Not[i][j+1]=0;
						//myframe.changedMemoryState(i, j+1, 2);
						Memory_Or_Not[i][j]=0;
						Valuable_Or_Not[i][j]=0;
						//myframe.changedMemoryState(i, j, 2);
						Memory_Or_Not[i+1][j/2]=1;
						Valuable_Or_Not[i+1][j/2]=1;
						myframe.changedMemoryState(i+1, j/2, 1,getMemory_Or_Not(),getValuable_Or_Not());
					}
				}
				
				if(flag==1){
				     
				    // System.out.println("remain_me "+remain_memory);
				     break;
				}

				
			}
		}
//		for(int i=6;i<=14;i++){
//			for(int j=0;j<Math.pow(2, 14)/Math.pow(2, i);j++){
//				System.out.print(" "+Memory_Or_Not[i][j]+Valuable_Or_Not[i][j]);
//			}
//			System.out.println();
//		}
	}
	public BuddyClass(){
		myframe=new GUIWindow(newThreadListener);
		myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myframe.setVisible(true);
		myframe.setLocationRelativeTo(null);
		InitData();
		//System.out.println("remain_me "+remain_memory);
	}
	
	
	
    private class TimerAction extends AbstractAction {
    	private int x;
    	private int y;
    	public TimerAction(int x,int y){
    		this.x=x;
    		this.y=y;
    	}
        public void actionPerformed(ActionEvent e) {
               Recover_Memery(x, y);
               myframe.removeProcessList(x,y);
               //System.out.println("rcovkkkk!");
               if(Memory_Or_Not[14][0]==1&&Valuable_Or_Not[14][0]==1){
            	   timer.stop();
               }
               
        }
    }
    
    
	public static void main(String[]args) {
		new BuddyClass();
	}
}
