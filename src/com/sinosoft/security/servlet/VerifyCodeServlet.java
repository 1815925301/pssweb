package com.sinosoft.security.servlet;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.patchca.color.ColorFactory;
import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.WobbleRippleFilterFactory;
import org.patchca.font.RandomFontFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.text.renderer.BestFitTextRenderer;
import org.patchca.text.renderer.TextRenderer;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.RandomWordFactory;
import org.springframework.ui.ModelMap;

import com.sinosoft.common.web.ActivityModelMap;

public class VerifyCodeServlet {  
    private static final long serialVersionUID = 3796351198097771007L;   
    private static ConfigurableCaptchaService ccs = null;  
    private static ColorFactory cf = null;  
    private static TextRenderer tr = null;  
    private static RandomFontFactory ff = null;  
    private static RandomWordFactory rwf = null;  
    private static Random r = new Random();  
    //private static CurvesRippleFilterFactory crff = null;  //干扰线波纹  
    //private static MarbleRippleFilterFactory mrff = null;  //大理石波纹  
    //private static DoubleRippleFilterFactory drff = null;  //双波纹  
    private static WobbleRippleFilterFactory wrff = null;   //摆波纹  
    //private static DiffuseRippleFilterFactory dirff = null;  //漫波纹  
  
    /** 
     * Constructor of the object. 
     */  
    public VerifyCodeServlet() {  
    	 ccs = new ConfigurableCaptchaService();  
         cf = new SingleColorFactory(new Color(25, 60, 170));  
         ff = new RandomFontFactory();  
         rwf = new RandomWordFactory();  
         tr = new BestFitTextRenderer();  
         //crff = new CurvesRippleFilterFactory(ccs.getColorFactory());  
         //drff = new DoubleRippleFilterFactory();  
         wrff = new WobbleRippleFilterFactory();  
         //dirff = new DiffuseRippleFilterFactory();  
         //mrff = new MarbleRippleFilterFactory();  
         rwf.setCharacters("123456789");  //abcdefghigkmnpqrstuvwxyzABCDEFGHIGKLMNPQRSTUVWXYZ
         ff.setRandomStyle(false);  
         ff.setMaxSize(16);  
         ff.setMinSize(12);  
         ccs.setTextRenderer(tr);  
         ccs.setFontFactory(ff);  
         ccs.setWordFactory(rwf);  
         ccs.setColorFactory(cf);  
         ccs.setWidth(50);  
         ccs.setHeight(20);  
    }  
  
      
    /** 
     * Destruction of the servlet. <br> 
     */  
    public void destroy() {  
        rwf = null;  
        cf = null;  
        ccs = null;  
        ff = null;  
        //super.destroy(); // Just puts "destroy" string in log  
        // Put your code here  
    }  
    
	public void verifyCode(HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		response.setContentType("image/png");  
	    response.setHeader("cache", "no-cache");  
        rwf.setMaxLength(4);  
        rwf.setMinLength(4);  
        ccs.setFilterFactory(wrff);  
       /*  switch (r.nextInt(5)) {
	        case 0:
	        	ccs.setFilterFactory(new CurvesRippleFilterFactory(ccs.getColorFactory()));
	            break;
	        case 1:
	        	ccs.setFilterFactory(new MarbleRippleFilterFactory());
	            break;
	        case 2:
	        	ccs.setFilterFactory(new DoubleRippleFilterFactory());
	            break;
	        case 3:
	        	ccs.setFilterFactory(new WobbleRippleFilterFactory());
	            break;
	        case 4:
	        	ccs.setFilterFactory(new DiffuseRippleFilterFactory());
	            break;
	    }**/
        HttpSession session = request.getSession(true);  
        OutputStream os = null;
		try {
			os = response.getOutputStream();
		    String captcha = EncoderHelper.getChallangeAndWriteImage(ccs, "png", os);  
	        session.setAttribute("validateCode", captcha);  
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			 try {
				 if(os!=null){
					 os.flush();
					 os.close();  
				 }
				 if (os == null)
						os = response.getOutputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}  
		}  
	}
}  