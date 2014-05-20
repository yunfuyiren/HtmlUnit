package com.yunfuyiren.htmlunittest2;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlHiddenInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlRadioButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;



public class baidupage {
	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException
	{
		WebClient webclient=new WebClient(BrowserVersion.CHROME);
		final HtmlPage htmlpage=webclient
				.getPage("http://news.baidu.com/advanced_news.html");
		webclient.getOptions().setCssEnabled(false);
		webclient.getOptions().setJavaScriptEnabled(false);
		final HtmlForm form=htmlpage.getFormByName("f");
		final HtmlSubmitInput button=form.getInputByValue("百度一下");
		final HtmlTextInput textFiled=form.getInputByName("q1");
		textFiled.setValueAttribute("阿里巴巴");
		final List<HtmlRadioButtonInput> radioButtons=form
				.getRadioButtonsByName("s");
		radioButtons.get(0).setChecked(false);
		radioButtons.get(1).setChecked(true); //the radion button of choosing the limmtied time area 
		final List<HtmlRadioButtonInput> titleButtons=form
				.getRadioButtonsByName("tn");
		titleButtons.get(0).setChecked(false);
		titleButtons.get(1).setChecked(true);  //选中“仅在新闻的标题中”的radion button 
		HtmlHiddenInput bt=form.getInputByName("bt");
		bt.setValueAttribute("1400428800");
		HtmlHiddenInput et = form.getInputByName("et");
		et.setValueAttribute("1400515200");
		final HtmlPage page2 = button.click();  
		String result=page2.asText();
		System.out.println(result);
		Pattern pattern=Pattern.compile("找到相关新闻 约(.*) 篇");
		Matcher matcher = pattern.matcher(result); 
		webclient.closeAllWindows();  
        if (matcher.find())  
        	System.out.print(matcher.group(1));
	}
}
