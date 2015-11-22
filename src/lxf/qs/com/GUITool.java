package lxf.qs.com;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;

public class GUITool {
	
	public GUITool() {
		super();
		// TODO Auto-generated constructor stub
	}
	JFrame myFrame=null;
	JButton butConfirm=null;
	JButton butAdd=null;
	JButton butLook=null;
	JButton butSearch=null;
	JButton butClear=null;
	JButton butShowAll=null;
	JButton butSend=null;
	JTextField textWord=null;
	TextArea textTitle=null;
	TextArea textAnswer=null;
	JComboBox myChoice=null;
	//JScrollPane textTitleS=null;
	//JScrollPane textAnswerS=null;
	JTable mytable=null;
	JPanel panel1=null;
	JPanel panel11=null;
	JPanel panel121=null;
	JPanel panel122=null;
	JPanel panel13=null;
	JPanel panel14=null;
	JPanel panel15=null;
	JPanel panel21=null;
	JPanel panel22=null;
	JPanel panel23=null;
	JPanel panel24=null;
	public void GUIInit() {
		myFrame = new JFrame("QS");
		butConfirm = new JButton("确定");
		butAdd = new JButton("添加");
		butLook = new JButton("查看");
		butSearch = new JButton("搜索");
		butClear = new JButton("清空");
		butShowAll = new JButton("搜索(换行)");
		butSend = new JButton("上传");
		textWord = new JTextField("",42);
		textTitle = new TextArea("",20,20,TextArea.SCROLLBARS_VERTICAL_ONLY );
		textAnswer = new TextArea("",20,100,TextArea.SCROLLBARS_VERTICAL_ONLY );
		myChoice = new JComboBox();
		myChoice.addItem("Java");
		myChoice.addItem("Android");
		myChoice.addItem("SQL");
		myChoice.addItem("Web");
		myChoice.addItem("生活");
		myChoice.addItem("其他");
		panel1 = new JPanel();
		panel11 = new JPanel();
		panel121 = new JPanel();
		panel122 = new JPanel();
		panel13 = new JPanel();
		panel14 = new JPanel();
		panel15 = new JPanel();
		panel21 = new JPanel();
		panel22 = new JPanel();
		panel23 = new JPanel();
		panel24 = new JPanel();
		myFrame.setLocation(300,200);
		myFrame.setSize(800,600);
		butConfirm.setSize(15,5);
		butLook.setSize(15,5);
		butSearch.setSize(15,5);
		butClear.setSize(15,5);
		butShowAll.setSize(15,5);
		butAdd.setSize(15,5);
		
		myChoice.setSize(50, 20);
		textWord.setSize(100, 20);

		
		myFrame.setLayout(new BorderLayout());
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel11.setLayout(new BorderLayout());
	
		panel121.setLayout(new GridLayout(2,1));
		panel122.setLayout(new GridLayout(2,1));
		panel13.setLayout(new  BorderLayout());
		panel14.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel15.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel21.setLayout(new BorderLayout());
		panel22.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel23.setLayout(new BorderLayout());
		
		panel1.add(butAdd);
		panel1.add(butLook);
		panel121.add(new Label("标题"));
		panel121.add(new Label("答案"));
		panel122.add(textTitle);
		panel122.add(textAnswer);
		panel13.add(panel121,BorderLayout.WEST);
		panel13.add(panel122,BorderLayout.CENTER);
		panel14.add(butConfirm);
		panel14.add(butClear);
		panel15.add(new Label("分类:"));
		panel15.add(myChoice);
		panel11.add(panel13,BorderLayout.CENTER);
		panel11.add(panel14,BorderLayout.SOUTH);
		panel11.add(panel15,BorderLayout.NORTH);
		panel22.add(textWord);
		panel22.add(butSearch);
		panel22.add(butShowAll);
		panel22.add(butSend);
		panel21.add(panel22,BorderLayout.NORTH);
		panel21.add(panel23,BorderLayout.CENTER);
		
		myFrame.add(panel1,"North");
		myFrame.add(panel11,"Center");
		//myFrame.add(panel21,"Center");
		
		myFrame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		myFrame.setVisible(true);
	}
}


//Jtable 自动换行处理
class TableCellTextAreaRenderer extends JTextArea implements TableCellRenderer { 
  public TableCellTextAreaRenderer() { 
      setLineWrap(true);   
      setWrapStyleWord(true); 
  } 

  public Component getTableCellRendererComponent(JTable table, Object value, 
          boolean isSelected, boolean hasFocus, int row, int column) { 
      // 计算当下行的最佳高度 
      int maxPreferredHeight = 0; 
      for (int i = 0; i < table.getColumnCount(); i++) { 
          setText("" + table.getValueAt(row, i)); 
          setSize(table.getColumnModel().getColumn(column).getWidth(), 0); 
          maxPreferredHeight = Math.max(maxPreferredHeight, getPreferredSize().height); 
      } 

      if (table.getRowHeight(row) != maxPreferredHeight)  // 少了这行则处理器瞎忙 
          table.setRowHeight(row, maxPreferredHeight); 

      setText(value == null ? "" : value.toString()); 
      return this; 
  } 
} 