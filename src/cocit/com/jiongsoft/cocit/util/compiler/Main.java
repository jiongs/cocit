package com.jiongsoft.cocit.util.compiler;  
import java.lang.reflect.Method;
public class Main {  
      
    private static String java_file_path = "E:/cocit/test/dcompiler/Test.java";  
      
    private static String java_basic_path = "E:/cocit/test/dcompiler";  
      
    private static String ecode = "utf8";  
      
    public void execute() throws Exception{  
        DCompile dc = new DCompile();  
        dc.initialize(java_file_path, java_basic_path, ecode);  
        Class clazz = dc.compile();  
        Object obj = clazz.newInstance(); 
        Method method = clazz.getMethod("getName", new Class[]{String.class});  
        String name = (String)method.invoke(obj, "dbdxj");  
        System.out.println(name);  
    }  
      
    public static void main(String[] args) {  
        Main m = new Main();  
        try{  
            m.execute();  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
    }  
}   