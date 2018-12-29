/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.util.*;
/**
 *
 * @author 
 */
public class Evaluate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        FileReader fr=new FileReader("test1.txt");
        BufferedReader br=new BufferedReader(fr);
        int i1=Integer.parseInt(br.readLine());
        LinkedList<String> alpha=new LinkedList<String>();
        LinkedList value=new LinkedList();
        LinkedList<String> exp=new LinkedList<String>();
        LinkedList<String> post=new LinkedList<String>();
        LinkedList post2=new LinkedList();
        for(int i=0;i<i1;i++){
            String s=br.readLine();
            StringTokenizer st=new StringTokenizer(s,"=");
            alpha.addLast(st.nextToken());
            value.addLast(st.nextToken());
        }
        //System.out.println("alpha "+alpha);
        //System.out.println("value "+value);
        
        i1=Integer.parseInt(br.readLine());
        for(int i=0;i<i1;i++){
          exp.addLast(br.readLine());
        }
        //System.out.println("exp "+exp);
        
        Stack sp=new Stack();
        
        String s="";
        //postfix operation
        for (int j= 0; j<i1; j++) {
            s=exp.get(j);
            String po="";
            for(int i=0;i<s.length();i++){//iterating the String
                
                if(s.codePointAt(i)>=97&&s.codePointAt(i)<=122){//checking alphabets
                    po=po+s.charAt(i);
                }
                else{
                    if((s.charAt(i)+"").equals("(")){//checking ( bracket
                        sp.push("(");
                    }
                    else if((s.charAt(i)+"").equals(")")){
                        while(!((sp.peek()+"").equals("("))){
                                po=po+sp.pop();
                            }
                        sp.pop();
                    }
                    
                    else if((s.charAt(i)+"").equals("+")||(s.charAt(i)+"").equals("-")){//checking + & -
                        if(sp.empty()||(sp.peek()+"").equals("(")){
                            sp.push((s.charAt(i)+""));
                        }
                        else{
                            while(!sp.empty()&&!((sp.peek()+"").equals("("))){
                                po=po+sp.pop();
                            }
                            sp.push(s.charAt(i)+"");
                        }

                    }
                    
                    
                    else if((s.charAt(i)+"").equals("*")||(s.charAt(i)+"").equals("/")){//checking * & /
                        if(sp.empty()||(sp.peek()+"").equals("(")){
                            sp.push((s.charAt(i)+""));
                        }
                        else if((sp.peek()+"").equals("+")||(sp.peek()+"").equals("-")){
                          
                            sp.push((s.charAt(i)+""));
                        }
                        else{
                            while(((sp.peek()+"").equals("*")||(sp.peek()+"").equals("/"))&&!((sp.peek()+"").equals("+")||(sp.peek()+"").equals("-"))&&!((sp.peek()+"").equals("("))){
                                po=po+sp.pop();
                            }
                            sp.push((s.charAt(i)+""));
                        }
                    }
                
                    
                }
                       if(i==(s.length()-1)&&!sp.empty()){
                           while(!sp.empty()){
                               po=po+sp.pop();
                         }
                    }
            }
            post.addLast(po);
        }
        System.out.println("Exp: "+exp);
        System.out.println("Post: "+post);
        
        
        //checking the expression        
        for(int i=0;i<post.size();i++){
            s=post.get(i);
            for(int j=0;j<s.length();j++){
                if(s.codePointAt(j)>=97&&s.codePointAt(j)<=122){
                      if(alpha.contains(s.charAt(j)+"")){
                          
                      }   
                      else{
                          post.remove(i);
                          post.add(i, "compilation error");
                          break;
                      }
                }
                
            }
        }
        
        
        
        //evaluating
        for(int i=0;i<post.size();i++){
            s=post.get(i);
            sp=new Stack();
            if(s.equals("compilation error")){
                post2.addLast("compilation error");
          }
            else{
                 double a=0;
                 double b=0;
            
                  for(int j=0;j<s.length();j++){
                      if(s.codePointAt(j)>=97&&s.codePointAt(j)<=122){
                           sp.push(value.get(alpha.indexOf(s.charAt(j)+"")));
                          
                    }
                      else{
                          if((s.charAt(j)+"").equals("+")){
                              b=Double.parseDouble(sp.pop()+"");
                              a=Double.parseDouble(sp.pop()+"");
                              sp.push(a+b);
                          }
                          else if((s.charAt(j)+"").equals("-")){
                              b=Double.parseDouble(sp.pop()+"");
                              a=Double.parseDouble(sp.pop()+"");
                              sp.push(a-b);
                          }
                          else if((s.charAt(j)+"").equals("*")){
                              b=Double.parseDouble(sp.pop()+"");
                              a=Double.parseDouble(sp.pop()+"");
                              sp.push(a*b);
                          }
                          else if((s.charAt(j)+"").equals("/")){
                              b=Double.parseDouble(sp.pop()+"");
                              a=Double.parseDouble(sp.pop()+"");
                              sp.push(a/b);
                          }
                          
                      }
                }
                  post2.addLast(sp.pop());
                    
                }
        }
        System.out.println("Output :"+post2);
    }
    
}
