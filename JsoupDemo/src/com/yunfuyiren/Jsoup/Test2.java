package com.yunfuyiren.Jsoup;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test2 {
	static String url="http://www.cnblogs.com/zyw-205520/archive/2012/12/20/2826402.html";
	 public static void main(String[] args) throws IOException
	 {
//		 GrapPage();
//		 SetAttr();
		 BBSHome();
	 }
	 
	 //����bodyƬ��
	 public static void bodyfragment()  
	 {
		 String html =  "<div><p>Lorem ipsum.</p>";
		 Document doc = Jsoup.parseBodyFragment(html);
		 Element body=doc.body();
		 System.out.println(body.toString());
	 }
	 
	 //���ʾ������չʾ��δ�һ��URL���һ��ҳ�档
	 //Ȼ����ȡҳ���е��������ӡ�ͼƬ�������������ݡ������URLs���ı���Ϣ��
	 public static void GrapPage() throws IOException
	 {
		 String url="http://bbs.ustc.edu.cn/cgi/bbstop10";
		 Document doc=Jsoup.connect(url).get();
		 Elements links=doc.select("a[href]");
		 Elements media=doc.select("[src]");
		 Elements imports=doc.select("link[href]");
		 print("\nMedia: (%d)", media.size());
	        for (Element src : media) {
	            if (src.tagName().equals("img"))
	                print(" * %s: <%s> %sx%s (%s)",
	                        src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
	                        trim(src.attr("alt"), 20));
	            else
	                print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
	        }
	        print("\nImports: (%d)", imports.size());
	        for (Element link : imports) {
	            print(" * %s <%s> (%s)", link.tagName(),link.attr("abs:href"), link.attr("rel"));
	        }
	        print("\nLinks: (%d)", links.size());
	        for (Element link : links) {
	            print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
	        }
	    }
	    private static void print(String msg, Object... args) {
	        System.out.println(String.format(msg, args));
	    }
	    private static String trim(String s, int width) {
	        if (s.length() > width)
	            return s.substring(0, width-1) + ".";
	        else
	            return s;
	    }
	    
	   //�������Ե�ֵ
	    public static void SetAttr()
	    {
	    	 String html ="<html><head><title> ��Դ�й����� </title></head>"
					 +"<body><p> ������ jsoup ��Ŀ��������� </p><div></div><span>One</span></body></html>";
	    	 Document doc=Jsoup.parse(html);
	    	 System.out.println(doc.body());
	    	 
	    	 //Ϊdiv�������
	    	 Element div=doc.select("div").first();
	    	 div.html("<p>haha</p>");
	    	 div.prepend("<p>preAdd</p>");
	    	 div.append("<p>lastAdd</p>");
	    	 System.out.println(doc.body());
	    	 
	    	 //��Ӱ�������
	    	 Element span=doc.select("span").first();
	    	 span.wrap("<li><a href='http://example.com'></a></li>");
	    	 System.out.println(doc.body());
	    }
	    
	    //BBS������ҳ��ȡ
	    public static void BBSHome() throws IOException
	    {
	    	String url="http://bbs.ustc.edu.cn/cgi/bbsqry?userid=frostforest";
	    	Document doc=Jsoup.connect(url).get();
	    	Elements elems=doc.getElementsByAttributeValue("class", "face");
	    	Element elem=elems.first();
	    	
	    	//����ͷ���ȡ
	    	String pic=elem.select("img").first().attr("abs:src");
	    	//��Ծ�̶Ȼ�ȡ
	    	String act_t=elem.select("span").get(2).attr("style");
	    	int t=act_t.indexOf(":");
	    	String act=act_t.substring(t+1,act_t.length()-1);
	    	//�û����
	    	String ident_t=elem.select("span").get(4).attr("class");  
	    	String ident;
	    	switch(ident_t)
	    	{
	    	case "identity ALU":
	    		ident="�ƴ�У��";
	    		break;
	    	case "identity STA":
	    		ident="��У�̹�";
	    		break;
	    	default:
	    		ident="��Уѧ��";
	    	}	    	
	    	//�û������ǳ�
	    	elems=doc.getElementsByAttributeValue("class", "detailinfo");  
	    	elem=elems.first();
	    	String id=elem.getElementsByAttributeValue("class", "id").first().text();  //�û�id
	    	String nickname=elem.getElementsByAttributeValue("class", "nickname").first().text();  //�ǳ�
	    	String vitality=elem.getElementsByAttributeValue("class", "vitality").first().text();  	//������
	    	String post_count=elem.getElementsByAttributeValue("class", "post_count").first().text(); //������
	    	String login_count=elem.getElementsByAttributeValue("class", "login_count").first().text(); //��վ����
	    	String age=elem.getElementsByAttributeValue("class", "age").first().text();  //����
	    	String mail=elem.getElementsByAttributeValue("class", "mail").first().text();   //����
	    	String smd=elem.getElementsByAttributeValue("class", "smd").first().text();    //����˵��
	    	System.out.println(smd);
	    }
}
