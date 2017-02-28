package com.lovapaper.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts2.ServletActionContext;

import web.ssh.bean.CheckProjectBean;
import web.ssh.bean.CkPoint;
import web.ssh.bean.CkTarget;
import web.ssh.bean.DeptProjectBean;
import web.ssh.bean.JobProjectBean;
import web.ssh.bean.UserProject;
import web.ssh.service.CheckProjectService;
import web.ssh.service.DeptProjectService;
import web.ssh.service.JobProjectService;
import web.ssh.service.UserProjectService;

public class ReadWriteExcelUtil {
	/**
	 * 检查项从文件中读取内容   导入
	 */
	public static Object[] checkProReadExcl(File xlsPath,CheckProjectService checkProjectService){
		//正确数据
		List<CheckProjectBean> temp = new ArrayList<CheckProjectBean>();
		//错误数据
		List<List<String>> errlist = new ArrayList<List<String>>();
		//错误信息
		List<List<String>> errmsglist = new ArrayList<List<String>>();
		//标记
		Boolean bj = true;
		//返回数组
		Object[] obj = new Object[3];
		// 构造Workbook（工作薄）对象  
		Workbook wk = null;
		try {
			FileInputStream filepath = new FileInputStream(xlsPath);
			wk = Workbook.getWorkbook(filepath);
			filepath.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		if(wk==null){return null;};//读取文件失败
		//获取第一张Sheet表 
        Sheet sheet=wk.getSheet(0);
        //获取总行数
        int rowNum=sheet.getRows();
		//迭代每一行
        for(int i=1;i<rowNum;i++){
    		List<String> errmsg = new ArrayList<String>(19);
    		List<String> errdata = new ArrayList<String>(19);
    	//getCell(column,row)，表示取得指定列指定行的单元格（Cell）
    	//getContents()获取单元格的内容，返回字符串数据。适用于字符型数据的单元格        	
        //创建对象实例  自己的表比如   
        String setChId = sheet.getCell(0, i).getContents();						errdata.add(setChId);
        String setChName = sheet.getCell(1, i).getContents();					errdata.add(setChName);
        String setChDesigner = sheet.getCell(2, i).getContents();				errdata.add(setChDesigner);
        String setChResdep = sheet.getCell(3, i).getContents();					errdata.add(setChResdep);
        String setChAppro = sheet.getCell(4, i).getContents();					errdata.add(setChAppro);
        String setChSuitobj = sheet.getCell(5, i).getContents();				errdata.add(setChSuitobj);
        String setChContent = sheet.getCell(6, i).getContents();				errdata.add(setChContent);
        String setChMeans = sheet.getCell(7, i).getContents();					errdata.add(setChMeans);
        String setExplaination = sheet.getCell(8, i).getContents();				errdata.add(setExplaination);
        String setIsSubjective = sheet.getCell(9, i).getContents();				errdata.add(setIsSubjective);
        String setChSubstandard = sheet.getCell(10, i).getContents();			errdata.add(setChSubstandard);
        String setThresholdSta = sheet.getCell(11, i).getContents();			errdata.add(setThresholdSta);
        String setFrequencyDatesta = sheet.getCell(12, i).getContents();		errdata.add(setFrequencyDatesta);
        String setFrequencyCount = sheet.getCell(13, i).getContents();			errdata.add(setFrequencyCount);
        String setFrequencyTimelong = sheet.getCell(14, i).getContents();		errdata.add(setFrequencyTimelong);
        String setImplementDate = sheet.getCell(15, i).getContents();			errdata.add(setImplementDate);
        String setChSignificance = sheet.getCell(16, i).getContents();			errdata.add(setChSignificance);
        String setChGleader = sheet.getCell(17, i).getContents();				errdata.add(setChGleader);
        String setIsable = sheet.getCell(18, i).getContents(); 					errdata.add(setIsable);
      
        
        CheckProjectBean check = new CheckProjectBean();
	    if(setChId==null||setChId.equals("")){
	    	//不能为空
	    	errmsg.add(0, "检查项编号不能为空");
	    	bj = false;
	    }else{
	     	String flag = checkProjectService.whetherToRepeat(setChId, "");
	    	if(flag.equals("0")){
	    		//没重复
	    		check.setChId(setChId);
	    		errmsg.add(0, "");
	    	}else if(flag.equals("1")){
	    		//有重复编号
	    		check.setChId(setChId);
	    		errmsg.add(0, "检查项编号重复");
	    		bj = false;
	    	}
	    }
	    
	    if(setChName==null||setChName.equals("")){
	    	//不能为空
	    	errmsg.add(1, "检查项名称不能为空");
	    }else{
	     	String flag = checkProjectService.whetherToRepeat("", setChName);
	    	if(flag.equals("0")){
	    		//没重复
	    		 check.setChName(setChName);
	    		 errmsg.add(1, "");
	    	}else if(flag.equals("2")){
	    		//有重复编号
	    		check.setChName(setChName);
	    		errmsg.add(1, "检查项名称重复");
	    		bj = false;
	    	}
	    }
	    
	    if(setChDesigner==null||setChDesigner.equals("")){
	    	errmsg.add(2, "设计人不能为空");
	    	bj = false;
	    }else{
	    	check.setChDesigner(setChDesigner);
	    	errmsg.add(2, "");
	    }
	    
	    if(setChResdep==null||setChResdep.equals("")){
	    	errmsg.add(3, "责任部门不能为空");
	    	bj = false;
	    }else{
	    	 check.setChResdep(setChResdep);   
	    	 errmsg.add(3, "");
	    }
	    
	    if(setChAppro==null||setChAppro.equals("")){
	    	errmsg.add(4, "批准人不能为空");
	    	bj = false;
	    }else{
	    	 check.setChAppro(setChAppro);  
	    	 errmsg.add(4, "");
	    }
	    
	    if(setChSuitobj==null||setChSuitobj.equals("")){
	    	errmsg.add(5, "适用对象不能为空");
	    	bj = false;
	    }else{
	    	 check.setChSuitobj(setChSuitobj); 
	    	 errmsg.add(5, "");
	    }
	    check.setChContent(setChContent);    
	    errmsg.add(6, "");
	    check.setChMeans(setChMeans);
	    errmsg.add(7, "");
	    check.setExplaination(setExplaination);   
	    errmsg.add(8, "");
	    if(setIsSubjective==null||setIsSubjective.equals("")){
	    	errmsg.add(9, "是否主管判断不能为空");
	    	bj = false;
	    }else{
	    	if(setIsSubjective.equals("是")){
	    		 errmsg.add(9, "");
	    		check.setIsSubjective(Short.parseShort("1")); 
	    	}else if(setIsSubjective.equals("否")){
	    		 errmsg.add(9, "");
	    		check.setIsSubjective(Short.parseShort("0"));
	    	}else{
	    		errmsg.add(9, "是否主管判断格式不正确(是/否)");
	    		bj = false;
	    	}
	    }
	    check.setChSubstandard(setChSubstandard);
	    errmsg.add(10, "");
	    check.setThresholdSta(setThresholdSta);   
	    errmsg.add(11, "");
	    
	    if(setFrequencyDatesta==null||setFrequencyDatesta.equals("")){
	    	check.setFrequencyDatesta(null);
	    	 errmsg.add(12, "");
	    }else{
	    	 if(setFrequencyDatesta.equals("年")){
			    	check.setFrequencyDatesta(Integer.parseInt("0")); 
			    	 errmsg.add(12, "");
			    }else if(setFrequencyDatesta.equals("月")){
			    	check.setFrequencyDatesta(Integer.parseInt("1"));
			    	 errmsg.add(12, "");
			    }else if(setFrequencyDatesta.equals("日")){
			    	check.setFrequencyDatesta(Integer.parseInt("2"));
			    	 errmsg.add(12, "");
			    }else{
			    	errmsg.add(12, "检查周期格式不正确(年/月/日)");
			    	bj = false;
			    }
	    }
	    
	    
	    if(setFrequencyCount==null||setFrequencyCount.equals("")){
	    	check.setFrequencyCount(null); 
	    	 errmsg.add(13, "");
	    }else{
	    	boolean flag = setFrequencyCount.matches("\\d");
	    	if(flag){
	    		check.setFrequencyCount(Integer.parseInt(setFrequencyCount)); 
	    		 errmsg.add(13, "");
	    	}else{
	    		errmsg.add(13, "检查周期次数格式不正确(数字)");
	    		bj = false;
	    	}
	    }
	    
	    if(setFrequencyTimelong==null||setFrequencyTimelong.equals("")){
	    	check.setFrequencyTimelong(null); 
	    	 errmsg.add(14, "");
	    }else{
	    	boolean flag = setFrequencyTimelong.matches("\\d");
	    	if(flag){
	    		check.setFrequencyTimelong(Integer.parseInt(setFrequencyTimelong)); 
	    		 errmsg.add(14, "");
	    	}else{
	    		errmsg.add(14, "检查周期时长格式不正确(数字)");
	    		bj = false;
	    	}
	    }
	    
	    if(setImplementDate==null||setImplementDate.equals("")){
	    	check.setImplementDate(null); 
	    	errmsg.add(15, "");
	    }else{
	    	SimpleDateFormat  s = new SimpleDateFormat("yyyy-MM-dd");
	   	    Date d = null;
	   		try {
	   			d = s.parse(setImplementDate);
	   			check.setImplementDate(d);
	   			errmsg.add(15, "");
	   		} catch (ParseException e) {
	   			errmsg.add(15, "执行开始日期格式不正确");
	   			bj = false;
	   		}
	    }
	    check.setChSignificance(setChSignificance);   
	    errmsg.add(16, "");
	    check.setChGleader(setChGleader); 
	    errmsg.add(17, "");
	    
	    if(setIsable==null||setIsable.equals("")){
	    	errmsg.add(18, "是否启用不能为空");
	    	bj = false;
	    }else{
	    	if(setIsable.equals("是")){
	    		errmsg.add(18, "");
	    		check.setIsable(Short.parseShort("1")); 
	    	}else if(setIsable.equals("否")){
	    		errmsg.add(18, "");
	    		check.setIsable(Short.parseShort("0"));
	    	}else{
	    		errmsg.add(18, "是否启用格式不正确(是/否)");
	    		bj = false;
	    	}
	    }
	    if(bj){
	    	temp.add(check);
	    }else{
	    	errlist.add(errdata);
	    	errmsglist.add(errmsg);
	    }
        }
        if(!errlist.isEmpty()){
        	String flag = checkReadError(errlist,errmsglist);
        }
	        obj[0] = temp;      // 正确数据
	        obj[1] = errlist;   // 错误数据
	        obj[2] = errmsglist;// 错误信息
        wk.close();
        return obj;
	}
		
	/**
	 * 检查项向服务器导出错误的
	 */
	public static String checkReadError(List<List<String>> errlist,List<List<String>> errmsglist){
		String root = ServletActionContext.getServletContext().getRealPath("/upload");
		WritableWorkbook wk = null;
		File b = null;
		try {
			b = new File(root+"//检查项导入错误.xls"); 
			wk = Workbook.createWorkbook(b);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		  try {
			  //创建可写入的Excel工作表
			  WritableSheet sheet=wk.createSheet("检查项", 0);
			  Label lab_01=new Label(0,0,"检查项编号（包括最后的流水号）");
			  Label lab_02=new Label(1,0,"检查项名称");
			  Label lab_03=new Label(2,0,"设计人");
			  Label lab_04=new Label(3,0,"责任部门");
			  Label lab_05=new Label(4,0,"批准人");
			  Label lab_06=new Label(5,0,"适用对象");
			  Label lab_07=new Label(6,0,"检查内容");
			  Label lab_08=new Label(7,0,"检查手段");
			  Label lab_09=new Label(8,0,"说明");
			  Label lab_10=new Label(9,0,"是否主观判断（是/否）");
			  Label lab_11=new Label(10,0,"主观检查标准");
			  Label lab_12=new Label(11,0,"阈值标准");
			  Label lab_13=new Label(12,0,"检查周期（年/月/日）");
			  Label lab_14=new Label(13,0,"次数");
			  Label lab_15=new Label(14,0,"时长（分钟）");
			  Label lab_16=new Label(15,0,"开始执行日期（年/月/日）");
			  Label lab_17=new Label(16,0,"检查项意义");
			  Label lab_18=new Label(17,0,"职能检查负责人");
			  Label lab_19=new Label(18,0,"是否启用（是/否）");
			  sheet.addCell(lab_01);
			  sheet.addCell(lab_02);
			  sheet.addCell(lab_03);
			  sheet.addCell(lab_04);
			  sheet.addCell(lab_05);
			  sheet.addCell(lab_06);
			  sheet.addCell(lab_07);
			  sheet.addCell(lab_08);
			  sheet.addCell(lab_09);
			  sheet.addCell(lab_10);
			  sheet.addCell(lab_11);
			  sheet.addCell(lab_12);
			  sheet.addCell(lab_13);
			  sheet.addCell(lab_14);
			  sheet.addCell(lab_15);
			  sheet.addCell(lab_16);
			  sheet.addCell(lab_17);
			  sheet.addCell(lab_18);
			  sheet.addCell(lab_19);
			  for(int i=0;i<errlist.size();i++){
				  String chid ="";
				  if(errmsglist.get(i).get(0).equals("")){
					   chid = errlist.get(i).get(0);
				  }else{
					   chid = errlist.get(i).get(0)+"("+errmsglist.get(i).get(0)+")";
				  }
				  String chname = "";
				  if(errmsglist.get(i).get(1).equals("")){
					   chname = errlist.get(i).get(1);
				  }else{
					   chname = errlist.get(i).get(1)+"("+errmsglist.get(i).get(1)+")";
				  }
				  String chdesigner = "";
				  if(errmsglist.get(i).get(2).equals("")){
					   chdesigner = errlist.get(i).get(2);
				  }else{
					   chdesigner = errlist.get(i).get(2)+"("+errmsglist.get(i).get(2)+")";
				  }
				  String chresdep = "";
				  if(errmsglist.get(i).get(3).equals("")){
					   chresdep = errlist.get(i).get(3);
				  }else{
					   chresdep = errlist.get(i).get(3)+"("+errmsglist.get(i).get(3)+")";
				  }
				  String chappro = "";
				  if(errmsglist.get(i).get(4).equals("")){
					   chappro = errlist.get(i).get(4);
				  }else{
					   chappro = errlist.get(i).get(4)+"("+errmsglist.get(i).get(4)+")";
				  }
				  String chsuitobj = "";
				  if(errmsglist.get(i).get(5).equals("")){
					   chsuitobj = errlist.get(i).get(5);
				  }else{
					   chsuitobj = errlist.get(i).get(5)+"("+errmsglist.get(i).get(5)+")";
				  }
				  String chcontent = "";
				  if(errmsglist.get(i).get(6).equals("")){
					   chcontent = errlist.get(i).get(6);
				  }else{
					   chcontent = errlist.get(i).get(6)+"("+errmsglist.get(i).get(6)+")";
				  }
				  String chmeans = "";
				  if(errmsglist.get(i).get(7).equals("")){
					   chmeans = errlist.get(i).get(7);
				  }else{
					   chmeans = errlist.get(i).get(7)+"("+errmsglist.get(i).get(7)+")";
				  }
				  String explaination = "";
				  if(errmsglist.get(i).get(8).equals("")){
					   explaination = errlist.get(i).get(8);
				  }else{
					   explaination = errlist.get(i).get(8)+"("+errmsglist.get(i).get(8)+")";
				  }
				  String issubjective = "";
				  if(errmsglist.get(i).get(9).equals("")){
					   issubjective = errlist.get(i).get(9);
				  }else{
					   issubjective = errlist.get(i).get(9)+"("+errmsglist.get(i).get(9)+")";
				  }
				  String chsubstandard = "";
				  if(errmsglist.get(i).get(10).equals("")){
					   chsubstandard = errlist.get(i).get(10);
				  }else{
					   chsubstandard = errlist.get(i).get(10)+"("+errmsglist.get(i).get(10)+")";
				  }
				  String thresholdsta = "";
				  if(errmsglist.get(i).get(11).equals("")){
					   thresholdsta = errlist.get(i).get(11);
				  }else{
					   thresholdsta = errlist.get(i).get(11)+"("+errmsglist.get(i).get(11)+")";
				  }
				  String frequencydatesta = "";
				  if(errmsglist.get(i).get(12).equals("")){
					   frequencydatesta = errlist.get(i).get(12);
				  }else{
					   frequencydatesta = errlist.get(i).get(12)+"("+errmsglist.get(i).get(12)+")";
				  }
				  String frequencycount = "";
				  if(errmsglist.get(i).get(13).equals("")){
					   frequencycount = errlist.get(i).get(13);
				  }else{
					   frequencycount = errlist.get(i).get(13)+"("+errmsglist.get(i).get(13)+")";
				  }
				  String FrequencyTimelong = "";
				  if(errmsglist.get(i).get(14).equals("")){
					   FrequencyTimelong = errlist.get(i).get(14);
				  }else{
					   FrequencyTimelong = errlist.get(i).get(14)+"("+errmsglist.get(i).get(14)+")";
				  }
				  String implementdate = "";
				  if(errmsglist.get(i).get(15).equals("")){
					   implementdate = errlist.get(i).get(15);
				  }else{
					   implementdate = errlist.get(i).get(15)+"("+errmsglist.get(i).get(15)+")";
				  }
				  String chsignificance = "";
				  if(errmsglist.get(i).get(16).equals("")){
					   chsignificance = errlist.get(i).get(16);
				  }else{
					   chsignificance = errlist.get(i).get(16)+"("+errmsglist.get(i).get(16)+")";
				  }
				  String chgleader = "";
				  if(errmsglist.get(i).get(17).equals("")){
					   chgleader = errlist.get(i).get(17);
				  }else{
					   chgleader = errlist.get(i).get(17)+"("+errmsglist.get(i).get(17)+")";
				  }
				  String isable = "";
				  if(errmsglist.get(i).get(18).equals("")){
					   isable = errlist.get(i).get(18);
				  }else{
					   isable = errlist.get(i).get(18)+"("+errmsglist.get(i).get(18)+")";
				  }
				  sheet.addCell(new Label(0,i+1,chid));
				  sheet.addCell(new Label(1,i+1,chname));
				  sheet.addCell(new Label(2,i+1,chdesigner));
				  sheet.addCell(new Label(3,i+1,chresdep));
				  sheet.addCell(new Label(4,i+1,chappro));
				  sheet.addCell(new Label(5,i+1,chsuitobj));
				  sheet.addCell(new Label(6,i+1,chcontent));
				  sheet.addCell(new Label(7,i+1,chmeans));
				  sheet.addCell(new Label(8,i+1,explaination));
				  sheet.addCell(new Label(9,i+1,issubjective));
				  sheet.addCell(new Label(10,i+1,chsubstandard));
				  sheet.addCell(new Label(11,i+1,thresholdsta));
				  sheet.addCell(new Label(12,i+1,frequencydatesta));
				  sheet.addCell(new Label(13,i+1,frequencycount));
				  sheet.addCell(new Label(14,i+1,FrequencyTimelong));
				  sheet.addCell(new Label(15,i+1,implementdate));
				  sheet.addCell(new Label(16,i+1,chsignificance));
				  sheet.addCell(new Label(17,i+1,chgleader));
				  sheet.addCell(new Label(18,i+1,isable));
				
			  }
			  return "ok";
		  } catch (Exception e) {
				e.printStackTrace();
		}finally{
			//将定义的工作表输出到之前指定的介质中（这里是客户端浏览器）
			  try {
				wk.write();
				wk.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		  return "no";
	}
	
	/**
	 * 检查项向文件中写内容   导出
	 */
	public static ByteArrayOutputStream checkProWriteExcl(List<CheckProjectBean> list){
		WritableWorkbook wk = null;
		ByteArrayOutputStream b = null;
		try {
			b = new ByteArrayOutputStream();
			wk = Workbook.createWorkbook(b);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		  try {
			  //创建可写入的Excel工作表
			  WritableSheet sheet=wk.createSheet("检查项", 0);
			  Label lab_00=new Label(0,0,"主键");
			  Label lab_01=new Label(1,0,"检查项编号（包括最后的流水号）");
			  Label lab_02=new Label(2,0,"检查项名称");
			  Label lab_03=new Label(3,0,"设计人");
			  Label lab_04=new Label(4,0,"责任部门");
			  Label lab_05=new Label(5,0,"批准人");
			  Label lab_06=new Label(6,0,"适用对象");
			  Label lab_07=new Label(7,0,"检查内容");
			  Label lab_08=new Label(8,0,"检查手段");
			  Label lab_09=new Label(9,0,"说明");
			  Label lab_10=new Label(10,0,"是否主观判断（是/否）");
			  Label lab_11=new Label(11,0,"主观检查标准");
			  Label lab_12=new Label(12,0,"阈值标准");
			  Label lab_13=new Label(13,0,"检查周期（年/月/日）");
			  Label lab_14=new Label(14,0,"次数");
			  Label lab_15=new Label(15,0,"时长（分钟）");
			  Label lab_16=new Label(16,0,"开始执行日期（年/月/日）");
			  Label lab_17=new Label(17,0,"检查项意义");
			  Label lab_18=new Label(18,0,"职能检查负责人");
			  Label lab_19=new Label(19,0,"是否启用");
			  Label lab_20=new Label(20,0,"附件名");
			  sheet.addCell(lab_00);
			  sheet.addCell(lab_01);
			  sheet.addCell(lab_02);
			  sheet.addCell(lab_03);
			  sheet.addCell(lab_04);
			  sheet.addCell(lab_05);
			  sheet.addCell(lab_06);
			  sheet.addCell(lab_07);
			  sheet.addCell(lab_08);
			  sheet.addCell(lab_09);
			  sheet.addCell(lab_10);
			  sheet.addCell(lab_11);
			  sheet.addCell(lab_12);
			  sheet.addCell(lab_13);
			  sheet.addCell(lab_14);
			  sheet.addCell(lab_15);
			  sheet.addCell(lab_16);
			  sheet.addCell(lab_17);
			  sheet.addCell(lab_18);
			  sheet.addCell(lab_19);
			  sheet.addCell(lab_20);
			  for(int i=0;i<list.size();i++){
				  String id = list.get(i).getId()==null?null:list.get(i).getId().toString();
				  String chid = list.get(i).getChId()==null?null:list.get(i).getChId().toString();
				  String chname = list.get(i).getChName()==null?null:list.get(i).getChName().toString();
				  String chdesigner = list.get(i).getChDesigner()==null?null:list.get(i).getChDesigner().toString();
				  String chresdep = list.get(i).getChResdep()==null?null:list.get(i).getChResdep().toString();
				  String chappro = list.get(i).getChAppro()==null?null:list.get(i).getChAppro().toString();
				  String chsuitobj = list.get(i).getChSuitobj()==null?null:list.get(i).getChSuitobj().toString();
				  String chcontent = list.get(i).getChContent()==null?null:list.get(i).getChContent().toString();
				  String chmeans = list.get(i).getChMeans()==null?null:list.get(i).getChMeans().toString();
				  String explaination = list.get(i).getExplaination()==null?null:list.get(i).getExplaination().toString();
				  String issubjective = list.get(i).getIsSubjective()==null?null:list.get(i).getIsSubjective().toString();
				  String chsubstandard = list.get(i).getChSubstandard()==null?null:list.get(i).getChSubstandard().toString();
				  String thresholdsta = list.get(i).getThresholdSta()==null?null:list.get(i).getThresholdSta().toString();
				  String frequencydatesta = list.get(i).getFrequencyDatesta()==null?null:list.get(i).getFrequencyDatesta().toString();
				  String frequencycount = list.get(i).getFrequencyCount()==null?null:list.get(i).getFrequencyCount().toString();
				  String FrequencyTimelong = list.get(i).getFrequencyTimelong()==null?null:list.get(i).getFrequencyTimelong().toString();
				  String implementdate = list.get(i).getImplementDate()==null?null:list.get(i).getImplementDate().toString();
				  String chsignificance = list.get(i).getChSignificance()==null?null:list.get(i).getChSignificance().toString();
				  String chgleader = list.get(i).getChGleader()==null?null:list.get(i).getChGleader().toString();
				  String isable = list.get(i).getIsable()==null?null:list.get(i).getIsable().toString();
				  String fileid = list.get(i).getFileId()==null?null:list.get(i).getFileId().toString();
				  sheet.addCell(new Label(0,i+1,id));
				  sheet.addCell(new Label(1,i+1,chid));
				  sheet.addCell(new Label(2,i+1,chname));
				  sheet.addCell(new Label(3,i+1,chdesigner));
				  sheet.addCell(new Label(4,i+1,chresdep));
				  sheet.addCell(new Label(5,i+1,chappro));
				  sheet.addCell(new Label(6,i+1,chsuitobj));
				  sheet.addCell(new Label(7,i+1,chcontent));
				  sheet.addCell(new Label(8,i+1,chmeans));
				  sheet.addCell(new Label(9,i+1,explaination));
				  if(issubjective!=null){
					  if(issubjective.equals("1")){
						  sheet.addCell(new Label(10,i+1,"是"));
					  }else{
						  sheet.addCell(new Label(10,i+1,"否"));
					  }
				  }
				  sheet.addCell(new Label(11,i+1,chsubstandard));
				  sheet.addCell(new Label(12,i+1,thresholdsta));
				  if(frequencydatesta!=null){
					  if(frequencydatesta.equals("0")){
						  sheet.addCell(new Label(13,i+1,"年"));
					  }else  if(frequencydatesta.equals("1")){
						  sheet.addCell(new Label(13,i+1,"月"));
					  }else  if(frequencydatesta.equals("2")){
						  sheet.addCell(new Label(13,i+1,"日"));
					  }
				  }else{
					  sheet.addCell(new Label(13,i+1,null));
				  }
				  sheet.addCell(new Label(14,i+1,frequencycount));
				  sheet.addCell(new Label(15,i+1,FrequencyTimelong));
				  sheet.addCell(new Label(16,i+1,implementdate));
				  sheet.addCell(new Label(17,i+1,chsignificance));
				  sheet.addCell(new Label(18,i+1,chgleader));
				  if(isable!=null){
					  if(isable.equals("1")){
						  sheet.addCell(new Label(19,i+1,"是"));
					  }else{
						  sheet.addCell(new Label(19,i+1,"否"));
					  }
				  }
				  sheet.addCell(new Label(20,i+1,fileid));
			  }
			  return b;
		  } catch (Exception e) {
				e.printStackTrace();
		}finally{
			//将定义的工作表输出到之前指定的介质中（这里是客户端浏览器）
			  try {
				wk.write();
				wk.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return b;
	}
	
	/**
	 * user从文件中读取内容   导入
	 */
	public static Object[] userProReadExcl(File xlsPath,UserProjectService userProjectService){
		//所有数据
		List<UserProject> temp = new ArrayList<UserProject>();
		//错误数据以及错误信息
		List<String[][]> err = new ArrayList<String[][]>();
		//返回错误数据
		Object[] obj = new Object[2];
		// 构造Workbook（工作薄）对象  
		Workbook wk = null;
		try {
			FileInputStream filepath = new FileInputStream(xlsPath);
			wk = Workbook.getWorkbook(filepath);
			filepath.close();
		} catch(Exception e){
			e.printStackTrace();
		}
		if(wk==null){return null;};
		//获取第一张Sheet表 
        Sheet sheet=wk.getSheet(0);
        //获取总行数
        int rowNum=sheet.getRows();
		//迭代每一行
        for(int i=1;i<rowNum;i++){
        	String[][] strarr = new String[8][2];
        	Boolean flag = true;
        	Boolean flag2 = true;
    	//getCell(column,row)，表示取得指定列指定行的单元格（Cell）
    	//getContents()获取单元格的内容，返回字符串数据。适用于字符型数据的单元格        	
        //创建对象实例  自己的表比如   
        	String setUsername = sheet.getCell(0, i).getContents();   	strarr[0][0] = setUsername;
        	String setGender = sheet.getCell(1, i).getContents();		strarr[1][0] = setGender;
        	String setRybh = sheet.getCell(2, i).getContents();			strarr[2][0] = setRybh;
        	String setDeptId = sheet.getCell(3, i).getContents();		strarr[3][0] = setDeptId;
        	String setDeptname = sheet.getCell(4, i).getContents();		strarr[4][0] = setDeptname;
        	String setJobId = sheet.getCell(5, i).getContents();		strarr[5][0] = setJobId;
        	String setJobname = sheet.getCell(6, i).getContents();		strarr[6][0] = setJobname;
        	
	    	UserProject user = new UserProject();
	    	if(setUsername==null||setUsername.equals("")){
	    		strarr[0][1] = "用户名不能为空";
	    		flag = false;
	    	}else{
	    		user.setUsername(setUsername);    
	    	}
	    	
	    	if(setGender==null||setGender.equals("")){
	    		strarr[1][1] = "性别不能为空";
	    		flag = false;
	    	}else if(setGender.matches("[男|女]")){
	    		 user.setGender(setGender);   
	    	}else{
	    		strarr[1][1] = "性别格式不正确(男/女)";
	    		flag = false;
	    	}
	    	
	    	if(setRybh==null||setRybh.equals("")){
	    		strarr[2][1] = "人员编号不能为空";
	    		flag = false;
	    	}else if(setRybh.matches("[A-Za-z\\d]+")){
	    		user.setRybh(setRybh);    
	    	}else{
	    		strarr[2][1] = "人员编号格式不正确(不能为中文和特殊字符)";
	    		flag = false;
	    	}
	    	
	    	if(setDeptId.matches("\\d+")){
	    		user.setDeptId(Long.parseLong(setDeptId));    
	    	}else{
	    		strarr[3][1] = "部门主键格式不正确(数字)";
	    		flag = false;
	    		flag2 = false;
	    	}
	    	
		    user.setDeptname(setDeptname); 
			
		    if(setJobId.matches("\\d+")){
	    		user.setJobId(setJobId);    
	    	}else{
	    		strarr[5][1] = "岗位主键格式不正确(数字)";
	    		flag = false;
	    		flag2 = false;
	    	}
		    
		    user.setJobname(setJobname);
		    
		    if(flag){
		    	if(flag2){
		    		Boolean b = userProjectService.queryuser(setDeptId, setJobId, setRybh);
		    		if(b){
		    			user.setPassword("123456");
		    			temp.add(user);
		    		}else{
		    			strarr[7][1] = "该部门该岗位已有该人员编号";
		    			err.add(strarr);
		    		}
		    	}
		    }else{
		    	err.add(strarr);
		    }
	        }
	        obj[0] = temp;
	        obj[1] = err;
	        if(!err.isEmpty()){
	        	boolean b = userWriteErrExcl(err);
	        }
	        wk.close();
	        return obj;
	}
	
	/**
	 * user向服务器写内容   导出
	 */
	public static boolean userWriteErrExcl(List<String[][]> list){
		String root = ServletActionContext.getServletContext().getRealPath("/upload");
		WritableWorkbook wk = null;
		File b = null;
		try {
			b = new File(root+"//职工导入错误.xls"); 
			wk = Workbook.createWorkbook(b);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		  try {
			  //创建可写入的Excel工作表
			  WritableSheet sheet=wk.createSheet("职工", 0);
			  Label lab_01=new Label(0,0,"职工姓名");
			  Label lab_02=new Label(1,0,"性别");
			  Label lab_03=new Label(2,0,"职工编号");
			  Label lab_04=new Label(3,0,"部门编号");
			  Label lab_05=new Label(4,0,"部门名称");
			  Label lab_06=new Label(5,0,"岗位编号");
			  Label lab_07=new Label(6,0,"岗位名称");
			  sheet.addCell(lab_01);
			  sheet.addCell(lab_02);
			  sheet.addCell(lab_03);
			  sheet.addCell(lab_04);
			  sheet.addCell(lab_05);
			  sheet.addCell(lab_06);
			  sheet.addCell(lab_07);
			  for(int i=0;i<list.size();i++){
				  String username = "";
				  if(list.get(i)[0][1]==null){
					  username = list.get(i)[0][0];
				  }else{
					  username = list.get(i)[0][0]+"("+list.get(i)[0][1]+")";
				  }
				  String gender = "";
				  if(list.get(i)[1][1]==null){
					  gender = list.get(i)[1][0];
				  }else{
					  gender = list.get(i)[1][0]+"("+list.get(i)[1][1]+")";
				  }
				  String rybh = "";
				  if(list.get(i)[2][1]==null){
					  rybh = list.get(i)[2][0];
				  }else{
					  rybh = list.get(i)[2][0]+"("+list.get(i)[2][1]+")";
				  }
				  String deptid = "";
				  if(list.get(i)[3][1]==null){
					  deptid = list.get(i)[3][0];
				  }else{
					  deptid = list.get(i)[3][0]+"("+list.get(i)[3][1]+")";
				  }
				  String deptname = "";
				  if(list.get(i)[4][1]==null){
					  deptname = list.get(i)[4][0];
				  }else{
					  deptname = list.get(i)[4][0]+"("+list.get(i)[4][1]+")";
				  }
				  String jobid = "";
				  if(list.get(i)[5][1]==null){
					  jobid = list.get(i)[5][0];
				  }else{
					  jobid = list.get(i)[5][0]+"("+list.get(i)[5][1]+")";
				  }
				  String jobname = "";
				  if(list.get(i)[6][1]==null){
					  jobname = list.get(i)[6][0];
				  }else{
					  jobname = list.get(i)[6][0]+"("+list.get(i)[6][1]+")";
				  }
				  String msg = "";
				  if(list.get(i)[7][1]==null){
					  msg = "";
				  }else{
					  msg = "("+list.get(i)[7][1]+")";
				  }
				  sheet.addCell(new Label(0,i+1,username));
				  sheet.addCell(new Label(1,i+1,gender));
				  sheet.addCell(new Label(2,i+1,rybh));
				  sheet.addCell(new Label(3,i+1,deptid));
				  sheet.addCell(new Label(4,i+1,deptname));
				  sheet.addCell(new Label(5,i+1,jobid));
				  sheet.addCell(new Label(6,i+1,jobname));
				  sheet.addCell(new Label(7,i+1,msg));
			  }
			  return true;
		  } catch (Exception e) {
				return false;
		}finally{
			//将定义的工作表输出到之前指定的介质中（这里是客户端浏览器）
			  try {
				wk.write();
				wk.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * user向文件中写内容   导出
	 */
	public static ByteArrayOutputStream userProWriteExcl(List<UserProject> list){
		WritableWorkbook wk = null;
		ByteArrayOutputStream b = null;
		try {
			b = new ByteArrayOutputStream();
			wk = Workbook.createWorkbook(b);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		  try {
			  //创建可写入的Excel工作表
			  WritableSheet sheet=wk.createSheet("职工", 0);
			  Label lab_00=new Label(0,0,"主键");
			  Label lab_01=new Label(1,0,"职工姓名");
			  Label lab_02=new Label(2,0,"性别");
			  Label lab_03=new Label(3,0,"职工编号");
			  Label lab_04=new Label(4,0,"部门编号");
			  Label lab_05=new Label(5,0,"部门名称");
			  Label lab_06=new Label(6,0,"岗位编号");
			  Label lab_07=new Label(7,0,"岗位名称");
			  sheet.addCell(lab_00);
			  sheet.addCell(lab_01);
			  sheet.addCell(lab_02);
			  sheet.addCell(lab_03);
			  sheet.addCell(lab_04);
			  sheet.addCell(lab_05);
			  sheet.addCell(lab_06);
			  sheet.addCell(lab_07);
			  for(int i=0;i<list.size();i++){
				  String id = list.get(i).getUserId()==null?null:list.get(i).getUserId().toString();
				  String username = list.get(i).getUsername()==null?null:list.get(i).getUsername().toString();
				  String gender = list.get(i).getGender()==null?null:list.get(i).getGender().toString();
				  String rybh = list.get(i).getRybh()==null?null:list.get(i).getRybh().toString();
				  String deptid = list.get(i).getDeptId()==null?null:list.get(i).getDeptId().toString();
				  String deptname = list.get(i).getDeptname()==null?null:list.get(i).getDeptname().toString();
				  String jobid = list.get(i).getJobId()==null?null:list.get(i).getJobId().toString();
				  String jobname = list.get(i).getJobname()==null?null:list.get(i).getJobname().toString();
				  sheet.addCell(new Label(0,i+1,id));
				  sheet.addCell(new Label(1,i+1,username));
				  sheet.addCell(new Label(2,i+1,gender));
				  sheet.addCell(new Label(3,i+1,rybh));
				  sheet.addCell(new Label(4,i+1,deptid));
				  sheet.addCell(new Label(5,i+1,deptname));
				  sheet.addCell(new Label(6,i+1,jobid));
				  sheet.addCell(new Label(7,i+1,jobname));
			  }
			  return b;
		  } catch (Exception e) {
				e.printStackTrace();
		}finally{
			//将定义的工作表输出到之前指定的介质中（这里是客户端浏览器）
			  try {
				wk.write();
				wk.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return b;
	}
	
	/**
	 * 岗位从文件中读取内容   导入
	 */
	public static Object[] usergwProReadExcl(File xlsPath,JobProjectService jobProjectService){
		//所有数据
		List<JobProjectBean> temp = new ArrayList<JobProjectBean>();
		//错误数据以及错误信息
		List<String[][]> err = new ArrayList<String[][]>();
		//返回错误数据
		Object[] obj = new Object[2];
		// 构造Workbook（工作薄）对象  
		Workbook wk = null;
		try {
			FileInputStream filepath = new FileInputStream(xlsPath);
			wk = Workbook.getWorkbook(filepath);
			filepath.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		if(wk==null){return null;};
		//获取第一张Sheet表 
		Sheet sheet=wk.getSheet(0);
		//获取总行数
		int rowNum=sheet.getRows();
		//迭代每一行
		for(int i=1;i<rowNum;i++){
			String[][] strarr = new String[4][2];
			//getCell(column,row)，表示取得指定列指定行的单元格（Cell）
			//getContents()获取单元格的内容，返回字符串数据。适用于字符型数据的单元格        	
			//创建对象实例  自己的表比如   
			String setJobId = sheet.getCell(0, i).getContents();    strarr[0][0] = setJobId;
			String setJobName = sheet.getCell(1, i).getContents();  strarr[1][0] = setJobName;
			String setDeptId = sheet.getCell(2, i).getContents();   strarr[2][0] = setDeptId;
			JobProjectBean job = new JobProjectBean();
			boolean b = true;
			if(setJobId==null||setJobId.equals("")){
				strarr[0][1] = "岗位主键不能为空";
				b = false;
			}else{
				if(setJobId.matches("\\d+")){
					job.setJobId(Integer.parseInt(setJobId));				
				}else{
					strarr[0][1] = "岗位主键格式不正确(数字)";
				}
			}
			if(setJobName==null||setJobName.equals("")){
				strarr[1][1] = "岗位名称不能为空";
				b = false;
			}else{
				job.setJobName(setJobName);
			}
			if(setDeptId==null||setDeptId.equals("")){
				strarr[2][1] = "部门主键不能为空";
				b = false;
			}else{
				if(setDeptId.matches("\\d+")){
					job.setDeptId(Integer.parseInt(setDeptId));				
				}else{
					strarr[2][1] = "部门主键格式不正确(数字)";
				}
			}
			
			if(b){
//				boolean flag = jobProjectService.queryjob(setJobId, setDeptId);
//				if(flag){
					temp.add(job);
//				}else{
//					strarr[3][1] = "该部门已有该岗位";
//					err.add(strarr);
//				}
			}else{
				err.add(strarr);
			}
			
		}
	    obj[0] = temp;
        obj[1] = err;
        if(!err.isEmpty()){
        	boolean b = JobErrWriteExcl(err);
        }
		wk.close();
		return obj;
	}
	/**
	 * 岗位向服务器写内容   导出
	 */
	public static boolean JobErrWriteExcl(List<String[][]> list){
		String root = ServletActionContext.getServletContext().getRealPath("/upload");
		WritableWorkbook wk = null;
		File b = null;
		try {
			b = new File(root+"//岗位导入错误.xls"); 
			wk = Workbook.createWorkbook(b);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			//创建可写入的Excel工作表
			WritableSheet sheet=wk.createSheet("岗位", 0);
			Label lab_00=new Label(0,0,"主键");
			Label lab_01=new Label(1,0,"岗位名称");
			Label lab_02=new Label(2,0,"部门主键");
			sheet.addCell(lab_00);
			sheet.addCell(lab_01);
			sheet.addCell(lab_02);
			for(int i=0;i<list.size();i++){
				String id = "";
				if(list.get(i)[0][1]==null){
					id = list.get(i)[0][0];
				}else{
					id = list.get(i)[0][0]+"("+list.get(i)[0][1]+")";
				}
				
				String name = "";
				if(list.get(i)[1][1]==null){
					name = list.get(i)[1][0];
				}else{
					name = list.get(i)[1][0]+"("+list.get(i)[1][1]+")";
				}
				
				String did = "";
				if(list.get(i)[2][1]==null){
					did = list.get(i)[2][0];
				}else{
					did = list.get(i)[2][0]+"("+list.get(i)[2][1]+")";
				}
				
				String msg = "";
				if(list.get(i)[3][1]==null){
			        msg = "";
				}else{
				    msg = "("+list.get(i)[3][1]+")";
				}
				
				sheet.addCell(new Label(0,i+1,id));
				sheet.addCell(new Label(1,i+1,name));
				sheet.addCell(new Label(2,i+1,did));
				sheet.addCell(new Label(3,i+1,msg));
			}
			return true;
		} catch (Exception e) {
			return false;
		}finally{
			try {
				wk.write();
				wk.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	/**
	 * 岗位向文件中写内容   导出
	 */
	public static ByteArrayOutputStream JobWriteExcl(List<JobProjectBean> list){
		WritableWorkbook wk = null;
		ByteArrayOutputStream b = null;
		try {
			b = new ByteArrayOutputStream();
			wk = Workbook.createWorkbook(b);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			//创建可写入的Excel工作表
			WritableSheet sheet=wk.createSheet("岗位", 0);
			Label lab_00=new Label(0,0,"主键");
			Label lab_01=new Label(1,0,"岗位名称");
			Label lab_02=new Label(2,0,"部门主键");
			sheet.addCell(lab_00);
			sheet.addCell(lab_01);
			sheet.addCell(lab_02);
			for(int i=0;i<list.size();i++){
				String id = list.get(i).getJobId()==null?null:list.get(i).getJobId().toString();
				String name = list.get(i).getJobName()==null?null:list.get(i).getJobName().toString();
				String did = list.get(i).getDeptId()==null?null:list.get(i).getDeptId().toString();
				sheet.addCell(new Label(0,i+1,id));
				sheet.addCell(new Label(1,i+1,name));
				sheet.addCell(new Label(2,i+1,did));
			}
			return b;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//将定义的工作表输出到之前指定的介质中（这里是客户端浏览器）
			try {
				wk.write();
				wk.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return b;
	}
	
	/**
	 * 部门向文件中写内容   导出
	 */
	public static ByteArrayOutputStream DeptWriteExcl(List<DeptProjectBean> list){
		WritableWorkbook wk = null;
		ByteArrayOutputStream b = null;
		try {
			b = new ByteArrayOutputStream();
			wk = Workbook.createWorkbook(b);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			//创建可写入的Excel工作表
			WritableSheet sheet=wk.createSheet("部门", 0);
			Label lab_00=new Label(0,0,"部门id");
			Label lab_01=new Label(1,0,"部门名称");
			Label lab_02=new Label(2,0,"部门地址");
			Label lab_03=new Label(3,0,"部门分类");
			Label lab_04=new Label(4,0,"部门属性");
			Label lab_05=new Label(5,0,"上级部门id");
			
			sheet.addCell(lab_00);
			sheet.addCell(lab_01);
			sheet.addCell(lab_02);
			sheet.addCell(lab_03);
			sheet.addCell(lab_04);
			sheet.addCell(lab_05);
			for(int i=0;i<list.size();i++){
				String id = list.get(i).getDeptId()==null?null:list.get(i).getDeptId().toString();
				String name = list.get(i).getDeptName()==null?null:list.get(i).getDeptName().toString();
				String address = list.get(i).getDeptAddress()==null?null:list.get(i).getDeptAddress().toString();
				String fenlei = list.get(i).getDeptNumber()==null?null:list.get(i).getDeptNumber().toString();
				String dclass = list.get(i).getDeptClass()==null?null:list.get(i).getDeptClass().toString();
				String parid = list.get(i).getDeptParent()==null?null:list.get(i).getDeptParent().toString();
				sheet.addCell(new Label(0,i+1,id));
				sheet.addCell(new Label(1,i+1,name));
				sheet.addCell(new Label(2,i+1,address));
				sheet.addCell(new Label(3,i+1,fenlei));
				sheet.addCell(new Label(4,i+1,dclass));
				sheet.addCell(new Label(5,i+1,parid));
			}
			return b;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//将定义的工作表输出到之前指定的介质中（这里是客户端浏览器）
			try {
				wk.write();
				wk.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return b;
	}

	/**
	 * dept从文件中读取内容   导入
	 */
	public static Object[] deptProReadExcl(File xlsPath,DeptProjectService deptProjectService){
		//所有数据
		List<DeptProjectBean> temp = new ArrayList<DeptProjectBean>();
		//错误数据以及错误信息
		List<String[][]> err = new ArrayList<String[][]>();
		//返回错误数据
		Object[] obj = new Object[2];
		// 构造Workbook（工作薄）对象  
		Workbook wk = null;
		try {
			FileInputStream filepath = new FileInputStream(xlsPath);
			wk = Workbook.getWorkbook(filepath);
			filepath.close();
		} catch(Exception e){
			e.printStackTrace();
		}
		if(wk==null){return null;};
		//获取第一张Sheet表 
        Sheet sheet=wk.getSheet(0);
        //获取总行数
        int rowNum=sheet.getRows();
		//迭代每一行
        for(int i=1;i<rowNum;i++){
        	String[][] strarr = new String[6][2];
        	boolean flag = true;
    	//getCell(column,row)，表示取得指定列指定行的单元格（Cell）
    	//getContents()获取单元格的内容，返回字符串数据。适用于字符型数据的单元格        	
        //创建对象实例  自己的表比如   
        	String setDeptId = sheet.getCell(0, i).getContents();			strarr[0][0] = setDeptId;
        	String setDeptName = sheet.getCell(1, i).getContents();			strarr[1][0] = setDeptName;
        	String setDeptAddress = sheet.getCell(2, i).getContents();		strarr[2][0] = setDeptAddress;
        	String setDeptNumber = sheet.getCell(3, i).getContents();		strarr[3][0] = setDeptNumber;
        	String setDeptClass = sheet.getCell(4, i).getContents();		strarr[4][0] = setDeptClass;
        	String setDeptParent = sheet.getCell(5, i).getContents();		strarr[5][0] = setDeptParent;
        	DeptProjectBean dept = new DeptProjectBean();
        	
        	if(setDeptId==null||setDeptId.equals("")){
        		strarr[0][1] = "部门主键不能为空";
        		flag = false;
        	}else{
        		if(setDeptId.matches("\\d+")){
        			dept.setDeptId(Integer.parseInt(setDeptId));				
				}else{
					strarr[0][1] = "部门主键格式不正确(数字)";
					flag = false;
				}
        	}
        	
          	if(setDeptName==null||setDeptName.equals("")){
        		strarr[1][1] = "部门名称不能为空";
        		flag = false;
        	}else{
        		dept.setDeptName(setDeptName);				
        	}
        	
          	dept.setDeptAddress(setDeptAddress);
        	
          	dept.setDeptNumber(setDeptNumber);
        	
        	dept.setDeptClass(setDeptClass);
        	
        	if(setDeptParent==null||setDeptParent.equals("")){
        		dept.setDeptParent(0L);
        	}else{
        		if(setDeptParent.matches("\\d+")){
        			dept.setDeptParent(Long.parseLong(setDeptParent));	
        		}else{
        			strarr[5][1] = "上级部门id格式错误（数字）";
        			flag = false;
        		}
        	}
        	
        	if(flag){
				temp.add(dept);
			}else{
				err.add(strarr);
			}
			
		}
	    obj[0] = temp;
        obj[1] = err;
        if(!err.isEmpty()){
        	boolean b = DeptErrWriteExcl(err);
        }
        wk.close();
        return obj;
	}
	/**
	 * 部门向服务器写内容   导出
	 */
	public static boolean DeptErrWriteExcl(List<String[][]> list){
		String root = ServletActionContext.getServletContext().getRealPath("/upload");
		WritableWorkbook wk = null;
		File b = null;
		try {
			b = new File(root+"//部门导入错误.xls"); 
			wk = Workbook.createWorkbook(b);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			//创建可写入的Excel工作表
			WritableSheet sheet=wk.createSheet("部门", 0);
			Label lab_00=new Label(0,0,"部门id");
			Label lab_01=new Label(1,0,"部门名称");
			Label lab_02=new Label(2,0,"部门地址");
			Label lab_03=new Label(3,0,"部门分类");
			Label lab_04=new Label(4,0,"部门属性");
			Label lab_05=new Label(5,0,"上级部门id");
			
			sheet.addCell(lab_00);
			sheet.addCell(lab_01);
			sheet.addCell(lab_02);
			sheet.addCell(lab_03);
			sheet.addCell(lab_04);
			sheet.addCell(lab_05);
			for(int i=0;i<list.size();i++){
				String id = "";
				if(list.get(i)[0][1]==null){
					id = list.get(i)[0][0];
				}else{
					id = list.get(i)[0][0]+"("+list.get(i)[0][1]+")";
				}
				String name = "";
				if(list.get(i)[1][1]==null){
					name = list.get(i)[1][0];
				}else{
					name = list.get(i)[1][0]+"("+list.get(i)[1][1]+")";
				}
				String address = "";
				if(list.get(i)[2][1]==null){
					address = list.get(i)[2][0];
				}else{
					address = list.get(i)[2][0]+"("+list.get(i)[2][1]+")";
				}
				String fenlei = "";
				if(list.get(i)[3][1]==null){
					fenlei = list.get(i)[3][0];
				}else{
					fenlei = list.get(i)[3][0]+"("+list.get(i)[3][1]+")";
				}
				String dclass = "";
				if(list.get(i)[4][1]==null){
					dclass = list.get(i)[4][0];
				}else{
					dclass = list.get(i)[4][0]+"("+list.get(i)[4][1]+")";
				}
				String parid = "";
				if(list.get(i)[5][1]==null){
					parid = list.get(i)[5][0];
				}else{
					parid = list.get(i)[5][0]+"("+list.get(i)[5][1]+")";
				}
				sheet.addCell(new Label(0,i+1,id));
				sheet.addCell(new Label(1,i+1,name));
				sheet.addCell(new Label(2,i+1,address));
				sheet.addCell(new Label(3,i+1,fenlei));
				sheet.addCell(new Label(4,i+1,dclass));
				sheet.addCell(new Label(5,i+1,parid));
			}
			return true;
		} catch (Exception e) {
			return false;
		}finally{
			try {
				wk.write();
				wk.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
	
	/**
	 * 检查对象导入
	 */
	public static List<CkTarget> checkTargetReadExcl(File xlsPath){
		//所有数据
		List<CkTarget> temp = new ArrayList<CkTarget>();
		// 构造Workbook（工作薄）对象  
		Workbook wk = null;
		try {
			//xlsPath = xlsPath.replaceAll("\\\\", "\\\\\\\\");
			FileInputStream filepath = new FileInputStream(xlsPath);
			wk = Workbook.getWorkbook(filepath);
			filepath.close();
		} catch (BiffException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		if(wk==null){return null;};
		//获取第一张Sheet表 
        Sheet sheet=wk.getSheet(0);
        //获取总行数
        int rowNum=sheet.getRows();
		//迭代每一行
        for(int i=1;i<rowNum;i++){
    	//getCell(column,row)，表示取得指定列指定行的单元格（Cell）
    	//getContents()获取单元格的内容，返回字符串数据。适用于字符型数据的单元格        	
        //创建对象实例  自己的表比如   
        CkTarget check = new CkTarget();
	    check.setCkTargetId(sheet.getCell(0, i).getContents());    
	    check.setCkTargetName(sheet.getCell(1, i).getContents());    
	    check.setCkTargetHeadres(sheet.getCell(2, i).getContents());    
	    check.setCkTargetResposition(sheet.getCell(3, i).getContents());    
	    check.setCkTargetResperson(sheet.getCell(4, i).getContents()); 
	    check.setCheckprojct(sheet.getCell(5, i).getContents()==null?"":sheet.getCell(5, i).getContents());
	    check.setCkTargetCheckman(sheet.getCell(6, i).getContents());    
	    check.setFenlei(sheet.getCell(7, i).getContents()==null?"":sheet.getCell(7, i).getContents());
	    check.setFlag(Short.parseShort(sheet.getCell(8, i).getContents()));
	    String s = sheet.getCell(9, i).getContents();
	    check.setCkTargetKeywords(s==null?"":s);
	    temp.add(check);
        }
        wk.close();
        return temp;
	}
	
	/**
	 * 检查对象向文件中写内容   导出
	 */
	public static ByteArrayOutputStream checkTargetWriteExcl(List<CkTarget> list){
		WritableWorkbook wk = null;
		ByteArrayOutputStream b = null;
			try {
				b = new ByteArrayOutputStream();
				wk = Workbook.createWorkbook(b);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
			  //创建可写入的Excel工作表
			  WritableSheet sheet=wk.createSheet("检查对象", 0);
			  Label lab_00=new Label(0,0,"主键");
			  Label lab_01=new Label(1,0,"检查对象编号（包括最后的流水号）");
			  Label lab_02=new Label(2,0,"检查对象名称");
			  Label lab_03=new Label(3,0,"责任部门");
			  Label lab_04=new Label(4,0,"责任岗位");
			  Label lab_05=new Label(5,0,"责任人");
			  Label lab_06=new Label(6,0,"所有计划检查岗位");
			  Label lab_07=new Label(7,0,"是否启用");
			  Label lab_08=new Label(8,0,"检查对象关键字");
			  sheet.addCell(lab_00);
			  sheet.addCell(lab_01);
			  sheet.addCell(lab_02);
			  sheet.addCell(lab_03);
			  sheet.addCell(lab_04);
			  sheet.addCell(lab_05);
			  sheet.addCell(lab_06);
			  sheet.addCell(lab_07);
			  sheet.addCell(lab_08);
			  for(int i=0;i<list.size();i++){
				  String id = list.get(i).getId()==null?null:list.get(i).getId().toString();
				  String targetid = list.get(i).getCkTargetId()==null?null:list.get(i).getCkTargetId().toString();
				  String cktargetname = list.get(i).getCkTargetName()==null?null:list.get(i).getCkTargetName().toString();
				  String cktargetheadres = list.get(i).getCkTargetHeadres()==null?null:list.get(i).getCkTargetHeadres().toString();
				  String cktargetresposition = list.get(i).getCkTargetResposition()==null?null:list.get(i).getCkTargetResposition().toString();
				  String cktargetresperson = list.get(i).getCkTargetResperson()==null?null:list.get(i).getCkTargetResperson().toString();
				  String cktargetcheckman = list.get(i).getCkTargetCheckman()==null?null:list.get(i).getCkTargetCheckman().toString();
				  String flag = list.get(i).getFlag()==null?null:list.get(i).getFlag().toString();
				  String cktargetkeywords = list.get(i).getCkTargetKeywords()==null?null:list.get(i).getCkTargetKeywords().toString();
				  sheet.addCell(new Label(0,i+1,id));
				  sheet.addCell(new Label(1,i+1,targetid));
				  sheet.addCell(new Label(2,i+1,cktargetname));
				  sheet.addCell(new Label(3,i+1,cktargetheadres));
				  sheet.addCell(new Label(4,i+1,cktargetresposition));
				  sheet.addCell(new Label(5,i+1,cktargetresperson));
				  sheet.addCell(new Label(6,i+1,cktargetcheckman));
				  if(flag!=null){
					  if(flag.equals("1")){
						  sheet.addCell(new Label(7,i+1,"是"));
					  }else{
						  sheet.addCell(new Label(7,i+1,"否"));
					  }
				  }
				  sheet.addCell(new Label(8,i+1,cktargetkeywords));
			  }
			  return b;
		  } catch (Exception e) {
				e.printStackTrace();
		}finally{
			//将定义的工作表输出到之前指定的介质中（这里是客户端浏览器）
			  try {
				wk.write();
				wk.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return b;

	}
	/**
	 * 检查点向文件中写内容   导出
	 */
	public static ByteArrayOutputStream checkPointWriteExcl(List<CkPoint> list){
		WritableWorkbook wk = null;
		ByteArrayOutputStream b = null;
		try {
			b = new ByteArrayOutputStream();
			wk = Workbook.createWorkbook(b);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  try {
			  //创建可写入的Excel工作表
			  WritableSheet sheet=wk.createSheet("检查点", 0);
			  Label lab_00=new Label(0,0,"主键");
			  Label lab_01=new Label(1,0,"检查点名称");
			  Label lab_02=new Label(2,0,"检查项名称");
			  Label lab_03=new Label(3,0,"检查对象名称");
			  Label lab_04=new Label(4,0,"责任部门");
			  Label lab_05=new Label(5,0,"职能检查部门");
			  Label lab_06=new Label(6,0,"职能检查责任岗位");
			  Label lab_07=new Label(7,0,"责任人");
			  Label lab_08=new Label(8,0,"职能检查负责人");
			  Label lab_09=new Label(9,0,"阈值标准");
			  Label lab_10=new Label(10,0,"检查周期");
			  Label lab_11=new Label(11,0,"执行日期");
			  Label lab_12=new Label(12,0,"是否主观判断");
			  Label lab_13=new Label(13,0,"责任岗位");
			  Label lab_14=new Label(14,0,"次数");
			  Label lab_15=new Label(15,0,"时长（分钟）");
			  Label lab_16=new Label(16,0,"主观检查标准");
			  Label lab_17=new Label(17,0,"检查点停用");
			  Label lab_18=new Label(18,0,"检查对象关键字");
			  sheet.addCell(lab_00);
			  sheet.addCell(lab_01);
			  sheet.addCell(lab_02);
			  sheet.addCell(lab_03);
			  sheet.addCell(lab_04);
			  sheet.addCell(lab_05);
			  sheet.addCell(lab_06);
			  sheet.addCell(lab_07);
			  sheet.addCell(lab_08);
			  sheet.addCell(lab_09);
			  sheet.addCell(lab_10);
			  sheet.addCell(lab_11);
			  sheet.addCell(lab_12);
			  sheet.addCell(lab_13);
			  sheet.addCell(lab_14);
			  sheet.addCell(lab_15);
			  sheet.addCell(lab_16);
			  sheet.addCell(lab_17);
			  sheet.addCell(lab_18);
			  for(int i=0;i<list.size();i++){
				  String ck_point_id = list.get(i).getCkPointId()==null?null:list.get(i).getCkPointId().toString();
				  String ck_point_name = list.get(i).getCkPointName()==null?null:list.get(i).getCkPointName().toString();
				  String ch_name = list.get(i).getChName()==null?null:list.get(i).getChName().toString();
				  String ck_target_name = list.get(i).getCkTargetName()==null?null:list.get(i).getCkTargetName().toString();
				  String ck_target_adminresdep = list.get(i).getCkTargetAdminresdep()==null?null:list.get(i).getCkTargetAdminresdep().toString();
				  String ck_target_funckdep = list.get(i).getCkTargetFunckdep()==null?null:list.get(i).getCkTargetFunckdep().toString();
				  String ck_target_adminrespos = list.get(i).getCkTargetAdminrespos()==null?null:list.get(i).getCkTargetAdminrespos().toString();
				  String ck_target_adminresper = list.get(i).getCkTargetAdminresper()==null?null:list.get(i).getCkTargetAdminresper().toString();
				  String ck_target_adminleader = list.get(i).getCkTargetAdminleader()==null?null:list.get(i).getCkTargetAdminleader().toString();
				  String threshold_sta = list.get(i).getThresholdSta()==null?null:list.get(i).getThresholdSta().toString();
				  String frequency_standard = list.get(i).getFrequencyStandard()==null?null:list.get(i).getFrequencyStandard().toString();
				  String implement_date_stand = list.get(i).getImplementDateStand()==null?null:list.get(i).getImplementDateStand().toString();
				  String is_subjective = list.get(i).getIsSubjective()==null?null:list.get(i).getIsSubjective().toString();
				  String ck_target_dutypost = list.get(i).getCkTargetDutypost()==null?null:list.get(i).getCkTargetDutypost().toString();
				  String frequency = list.get(i).getFrequency()==null?null:list.get(i).getFrequency().toString();
				  String duration = list.get(i).getDuration()==null?null:list.get(i).getDuration().toString();
				  String subjectivestandard = list.get(i).getSubjectivestandard()==null?null:list.get(i).getSubjectivestandard().toString();
				  String flag = list.get(i).getFlag()==null?null:list.get(i).getFlag().toString();
				  String ck_target_keywords = list.get(i).getCkTargetKeywords()==null?null:list.get(i).getCkTargetKeywords().toString();
				  sheet.addCell(new Label(0,i+1,ck_point_id));
				  sheet.addCell(new Label(1,i+1,ck_point_name));
				  sheet.addCell(new Label(2,i+1,ch_name));
				  sheet.addCell(new Label(3,i+1,ck_target_name));
				  sheet.addCell(new Label(4,i+1,ck_target_adminresdep));
				  sheet.addCell(new Label(5,i+1,ck_target_funckdep));
				  sheet.addCell(new Label(6,i+1,ck_target_adminrespos));
				  sheet.addCell(new Label(7,i+1,ck_target_adminresper));
				  sheet.addCell(new Label(8,i+1,ck_target_adminleader));
				  sheet.addCell(new Label(9,i+1,threshold_sta));
				  if(frequency_standard!=null){
					  if(frequency_standard.equals("0")){
						  sheet.addCell(new Label(10,i+1,"年"));
					  }else  if(frequency_standard.equals("1")){
						  sheet.addCell(new Label(10,i+1,"月"));
					  }else  if(frequency_standard.equals("2")){
						  sheet.addCell(new Label(10,i+1,"日"));
					  }
				  }else{
					  sheet.addCell(new Label(10,i+1,null));
				  }
				  sheet.addCell(new Label(11,i+1,implement_date_stand));
				  if(is_subjective!=null){
					  if(is_subjective.equals("1")){
						  sheet.addCell(new Label(12,i+1,"是"));
					  }else{
						  sheet.addCell(new Label(12,i+1,"否"));
					  }
				  }
				  sheet.addCell(new Label(13,i+1,ck_target_dutypost));
				  sheet.addCell(new Label(14,i+1,frequency));
				  sheet.addCell(new Label(15,i+1,duration));
				  sheet.addCell(new Label(16,i+1,subjectivestandard));
				  if(flag!=null){
					  if(flag.equals("1")){
						  sheet.addCell(new Label(17,i+1,"启用"));
					  }else{
						  sheet.addCell(new Label(17,i+1,"停用"));
					  }
				  }
				  sheet.addCell(new Label(18,i+1,ck_target_keywords));
			  }
			  return b;
		  } catch (Exception e) {
				e.printStackTrace();
		}finally{
			//将定义的工作表输出到之前指定的介质中（这里是客户端浏览器）
			  try {
				wk.write();
				wk.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return b;
	}
}
