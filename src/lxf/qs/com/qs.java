package lxf.qs.com;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
public class qs {
	
	public static void main(String[] args) {
		Domain damain = new Domain();
		damain.doThing();
	}
}

class Domain{
	 FileTool myFile;
	 JsonTool myJson;
	 GUITool myGUI;
	 String judgeTitle="";
	 String myfileFloderQS="D:\\QS2015";
	 String myfileFloder="D:\\QS2015\\data";
	 String myfileAdr="D:\\QS2015\\data\\lixiaofeng.txt";
	public Domain() {
		super();
		myFile = new FileTool();
		myJson = new JsonTool();
		myGUI= new GUITool();
		myGUI.GUIInit();
		myFile.createFolder(myfileFloderQS);
		myFile.createFolder(myfileFloder);
	}
	
	public void doThing(){
		myGUI.butAdd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				myGUI.myFrame.remove(myGUI.panel21);
				myGUI.myFrame.add(myGUI.panel1,BorderLayout.NORTH);
				myGUI.myFrame.add(myGUI.panel11,BorderLayout.CENTER);
				myGUI.myFrame.setSize(800,601);//加上下面的设置大小代码，是为了解决窗体下面两个按钮显示问题。
				myGUI.myFrame.setVisible(true);
				System.err.println("ADD");
			}
		});
		
		myGUI.butLook.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				myGUI.myFrame.remove(myGUI.panel11);
				myGUI.myFrame.add(myGUI.panel1,BorderLayout.NORTH);
				myGUI.myFrame.add(myGUI.panel21,BorderLayout.CENTER);
				myGUI.myFrame.setSize(800,600);
				myGUI.myFrame.setVisible(true);
				System.err.println("LOOK");
			}
		});
		
		myGUI.butClear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				myGUI.textTitle.append("");
				myGUI.textTitle.setText("");
				myGUI.textAnswer.append("");
				myGUI.textAnswer.setText("");
			}
		});
		
		myGUI.butConfirm.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String question = myGUI.textTitle.getText().toString();
				String jsonContent="";
				if(question.isEmpty()){
					JOptionPane.showMessageDialog(null,"请输入问题");
					return;
				}
				if(judgeTitle.equals(question)){
					JOptionPane.showMessageDialog(null,"已添加");
					return;
				}
				if(!myFile.judgeFolder(myfileFloder)){   
					
				} else
					try {
						if(!myFile.judgeFile(myfileAdr)){
							
							}else{
								try {
								jsonContent = myFile.readFile(myfileAdr);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							HashMap<String,String> hashData = new HashMap<String,String>();
							myJson.getJsonArray(hashData,jsonContent);
							if(hashData.containsKey(question)){
								JOptionPane.showMessageDialog(null,"已存在该问题");
								return;
							} 
						}
					} catch (HeadlessException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				
				Calendar calendar = Calendar.getInstance();
				String classify = myGUI.myChoice.getSelectedItem().toString();
				String answer = myGUI.textAnswer.getText().toString();
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(calendar.MONTH)+1;
				int day = calendar.get(calendar.DATE);
				JSONArray jsonArray = new JSONArray();
				JSONObject json = new JSONObject();
				try {
					jsonContent = myFile.readFile(myfileAdr);
					if(jsonContent.length()==0){
						
					}else{
					//jsonContentPut = jsonContent.substring(1,jsonContent.length());
					jsonArray = JSONArray.fromObject(jsonContent);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				json.put("classify",classify);
				json.put("year",year);
				json.put("month",month);
				json.put("day",day);
				json.put("question",question);
				json.put("answer",answer);
				jsonArray.add(json);
				try {
					myFile.writeFile(myfileAdr,jsonArray.toString());
				} catch (IOException e1) {
					
				}
				try {
					jsonContent = myFile.readFile(myfileAdr);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				judgeTitle=myGUI.textTitle.getText().toString();
				System.out.println(jsonContent);
				myJson.readJosn(jsonContent);
			}
		});
		
		myGUI.butSearch.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String jsonContent="";
				String mes = "";
				mes = myGUI.textWord.getText();
				try {
					jsonContent = myFile.readFile(myfileAdr);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				HashMap<String,String> hashData = new HashMap<String,String>();
				myJson.getJsonArray(hashData,jsonContent);
				String data1[][] = new String[hashData.size()][2];
				Object data2[] = hashData.keySet().toArray();
				String temp = "";
				String[] columnName = {"问题","答案"};
				int len=0;
				for(int i=0;i<hashData.size();i++){
					temp = data2[i].toString();
					if(temp.contains(mes)){
						data1[len][0] = temp;
						data1[len][1] = hashData.get(temp);
						len++;
					}
				}
				String[][] data3 = new String[len][2];
				for(int i=0;i<len;i++){
					data3[i][0]=data1[i][0];
					data3[i][1]=data1[i][1];
				}
				System.out.println("hashData.size()"+hashData.size());
				JTable table = new JTable(data3,columnName);
				
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//关闭自动变宽度
				table.getColumnModel().getColumn(0).setPreferredWidth(150);
				table.getColumnModel().getColumn(1).setPreferredWidth(630);
				JScrollPane pane = new JScrollPane(table);
				
				myGUI.panel23.removeAll();
				myGUI.panel23.setLayout(new BorderLayout());
				myGUI.panel23.add(pane,BorderLayout.CENTER);
				myGUI.panel21.add(myGUI.panel22,BorderLayout.NORTH);
				myGUI.panel21.add(myGUI.panel23,BorderLayout.CENTER);

				myGUI.myFrame.remove(myGUI.panel21);
				myGUI.myFrame.add(myGUI.panel1,BorderLayout.NORTH);
				myGUI.myFrame.add(myGUI.panel21,BorderLayout.CENTER);
				myGUI.myFrame.setVisible(true);
			}
		});
		myGUI.butShowAll.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String jsonContent="";
				String mes = "";
				mes = myGUI.textWord.getText();
				try {
					jsonContent = myFile.readFile(myfileAdr);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				HashMap<String,String> hashData = new HashMap<String,String>();
				myJson.getJsonArray(hashData,jsonContent);
				String data1[][] = new String[hashData.size()][2];
				Object data2[] = hashData.keySet().toArray();
				String temp = "";
				String[] columnName = {"问题","答案"};
				int len=0;
				for(int i=0;i<hashData.size();i++){
					temp = data2[i].toString();
					if(temp.contains(mes)){
						data1[len][0] = temp;
						data1[len][1] = hashData.get(temp);
						len++;
					}
				}
				String[][] data3 = new String[len][2];
				for(int i=0;i<len;i++){
					data3[i][0]=data1[i][0];
					data3[i][1]=data1[i][1];
				}
				System.out.println("hashData.size()"+hashData.size());
				JTable table = new JTable(data3,columnName);
				table.setDefaultRenderer(Object.class, new TableCellTextAreaRenderer()); //自动换行。
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//关闭自动变宽度
				table.getColumnModel().getColumn(0).setPreferredWidth(150);
				table.getColumnModel().getColumn(1).setPreferredWidth(630);
				JScrollPane pane = new JScrollPane(table);
				
				myGUI.panel23.removeAll();
				myGUI.panel23.setLayout(new BorderLayout());
				myGUI.panel23.add(pane,BorderLayout.CENTER);
				myGUI.panel21.add(myGUI.panel22,BorderLayout.NORTH);
				myGUI.panel21.add(myGUI.panel23,BorderLayout.CENTER);

				myGUI.myFrame.remove(myGUI.panel21);
				myGUI.myFrame.add(myGUI.panel1,BorderLayout.NORTH);
				myGUI.myFrame.add(myGUI.panel21,BorderLayout.CENTER);
				myGUI.myFrame.setVisible(true);
			}
		});
	}
}