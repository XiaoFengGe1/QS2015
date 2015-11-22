package lxf.qs.com;

import java.io.*;

public class FileTool {

	public FileTool() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void copyFile(String src,String dest) throws IOException{
		FileInputStream in = new FileInputStream(src);
		File file = new File(dest);
		if(!file.exists()){
			file.createNewFile();
		}
		FileOutputStream out = new FileOutputStream(file);
		int c;
		byte buffer[] = new byte[1024];
		while((c=in.read(buffer))!=-1){
			for(int i=0;i<c;i++){
				out.write(buffer[i]);
			}
		}
		in.close();
		out.close();
	}
	
	public void createFolder(String str){
		File file =new File(str);    
		//如果文件夹不存在则创建    
		if  (!file .exists()  && !file .isDirectory())      
		{       
		    file .mkdir(); 
		} 
	}
	
	public Boolean judgeFolder(String str){
		File file =new File(str);    
		//如果文件夹不存在则创建    
		if  (!file .exists()  && !file .isDirectory())      
		{       
		    file .mkdir(); 
		    return false;
		} else {
			return true;
		}
	}
	
	public Boolean judgeFile(String str) throws IOException{
		File file =new File(str);    
		//如果文件夹不存在则创建    
		if  (!file .exists()  && !file .isDirectory())      
		{       
		    file .createNewFile();    
		    return false;
		} else {
			return true;
		} 
	}
	
	public void writeFile(String scr,String contetn) throws IOException{
		File file = new File(scr);
		if(!file.exists()){
			file.createNewFile();
		}
		FileOutputStream out = new FileOutputStream(file,false);
		StringBuffer sb = new StringBuffer();
		sb.append(contetn);
		out.write(sb.toString().getBytes("utf-8"));
		out.close();
	}
	
	public String readFile(String scr) throws IOException{
		File file = new File(scr);
		if(!file.exists()||file.isDirectory()){
			throw new FileNotFoundException();
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(scr),"utf-8"));
		String temp = null;
		StringBuffer sb = new StringBuffer();
		temp = br.readLine();
		while(temp!=null){
			sb.append(temp+"");
			temp=br.readLine();
		}
		br.close();
		return sb.toString();
	}
}
