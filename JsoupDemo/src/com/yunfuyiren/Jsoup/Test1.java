package com.yunfuyiren.Jsoup;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test1 {
	static String url="http://www.cnblogs.com/zyw-205520/archive/2012/12/20/2826402.html";
    public static void main( String[] args ) throws IOException
    {
        BlogBody();
//    	article();    	
//    	ustclink();
//    	Blog();
    	
    }
    
    //���ַ����Ľ�������ȡhtml������
	private static void BlogBody() throws IOException {
		// TODO Auto-generated method stub
		 String html =  "<p>An <a href='http://example.com/'><b>example</b></a> link.</p>";
		 Document doc = Jsoup.parse(html);
		 Element link=doc.select("a").first();//���ҵ�һ��aԪ��
		 
		 // "An example link"//ȡ���ַ����е��ı�
		 System.out.println(doc.body().text());
		 // "http://example.com/"//ȡ�����ӵ�ַ  �����Ե�ֵ
		 String s=link.attr("href");
		 System.out.println(s);
		// "example""//ȡ�����ӵ�ַ�е��ı�
		 s=link.text();
		 System.out.println(s);
		// "<b>example</b>"//ȡ�������ڵ�html����
		 s=link.outerHtml();
		 System.out.println(s);
		 s=link.html();
		 System.out.println(s);
	}
	
    /**
     * ��ȡ�����ϵ����±��������
     */
	public static void article(){
		Document doc;
		try{
			doc=Jsoup.connect("http://www.cnblogs.com/zyw-205520/").get();
			Elements ListDiv=doc.getElementsByAttributeValue("class", "postTitle");
			for(Element element:ListDiv){
				Elements links=element.getElementsByTag("a");
                for (Element link : links) {
                    String linkHref = link.attr("href");
                    String linkText = link.text().trim();
                    System.out.println(linkHref);
                    System.out.println(linkText);
                }
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/*��ȡBBSʮ�����ַ*/
	public static void ustclink()   //��ȡʮ�����ַ
	{
		Document doc;
		try{
			doc=Jsoup.connect("http://bbs.ustc.edu.cn/cgi/bbstop10").get();
			Elements trs=doc.body().select("tr");
			for(Element tr:trs){
				Elements tds=tr.select("td");
				if(tds.size()<=0)
					continue;
				Elements links=tds.get(2).getElementsByTag("a");
				for(Element link:links){
					String linkHref=link.attr("href");
					System.out.println("http://bbs.ustc.edu.cn/cgi/"+linkHref);
				}
			}
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/*��ȡ����ĳһƪ������*/
	 public static void Blog() {
	        Document doc;
	        try {
	            doc = Jsoup.connect("http://www.cnblogs.com/zyw-205520/archive/2012/12/20/2826402.html").get();
	            Elements ListDiv = doc.getElementsByAttributeValue("class","postBody");
	            for (Element element :ListDiv) {
	                System.out.println(element.text());
	            }
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }    
	  }
}

