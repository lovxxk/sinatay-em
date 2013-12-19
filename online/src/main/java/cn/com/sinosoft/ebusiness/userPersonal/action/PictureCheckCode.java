package cn.com.sinosoft.ebusiness.userPersonal.action;

import java.awt.Font;  
import java.awt.Graphics2D;  
import java.awt.image.BufferedImage;  
import java.io.IOException;  
import java.awt.Color;  
import java.awt.geom.AffineTransform;  
import java.util.Random; 
import javax.imageio.ImageIO;  
import javax.servlet.ServletException;  
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

public class PictureCheckCode extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public PictureCheckCode() {
		super();
	}

	public void init() throws ServletException {
		super.init();
	}

	public void destroy() {
		super.destroy();
	}
	
    private Random random = new Random(); 

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���ò�����ͼƬ
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		// ָ�����ɵ���ӦͼƬ
		response.setContentType("image/jpeg");
		BufferedImage image = new BufferedImage(80,	25, BufferedImage.TYPE_INT_BGR);
		Graphics2D g = image.createGraphics();
		// ����������ʽ
		Font myFont = new Font("����", Font.BOLD, 16);
		// ��������
		g.setFont(myFont);

		g.setColor(getRandomColor(200, 250));
		// ���Ʊ���
		g.fillRect(0, 0, 80, 25);

		g.setColor(getRandomColor(180, 200));
		drawRandomLines(g, 160);
		String img = drawRandomString(4, g);
		request.getSession().setAttribute("registerImageCode", img);
		g.dispose();
		ImageIO.write(image, "JPEG", response.getOutputStream());
	}
	
    public Color getRandomColor(int fc, int bc) {  
        if (fc > 255)  
            fc = 200;  
        if (bc > 255)  
            bc = 255;  
        int r = fc + random.nextInt(bc - fc);  
        int g = fc + random.nextInt(bc - fc);  
        int b = fc + random.nextInt(bc - fc);  
        return new Color(r, g, b);  
    }  
      
    /** 
     * ���Ƹ����� 
     * @param g Graphics2D������������ͼ�� 
     * @param nums  �����ߵ����� 
     */  
    public void drawRandomLines(Graphics2D g ,int nums ){  
        g.setColor(this.getRandomColor(160, 200)) ;  
        for(int i=0 ; i<nums ; i++){  
            int x1 = random.nextInt(80) ;  
            int y1 = random.nextInt(25);  
            int x2 = random.nextInt(12) ;  
            int y2 = random.nextInt(12) ;  
            g.drawLine(x1, y1, x2, y2) ;  
        }  
    }  
      
    /** 
     * ��ȡ����ַ����� 
     *      �˺������Բ����ɴ�Сд��ĸ�����֣�������ɵ��ַ��� 
     * @param length    ����ַ����ĳ��� 
     * @return  ����ַ��� 
     */  
    public String drawRandomString(int length , Graphics2D g){  
        StringBuffer strbuf = new StringBuffer() ;  
        String temp = "" ;  
        int itmp = 0 ;  
        for(int i=0 ; i<length ; i++){  
            switch(random.nextInt(5)){  
            case 1:     //����A��Z����ĸ  
                itmp = random.nextInt(26) + 65 ;  
                temp = String.valueOf((char)itmp);  
                break;  
            case 2:  
                itmp = random.nextInt(26) + 97 ;  
                temp = String.valueOf((char)itmp);  
//            case 3:     //���ɺ���  
//                String[] rBase = {"0" , "1" , "2" , "3" , "4" , "5" , "6" , "7" ,   
//                        "8" , "9" , "a" , "b" , "c" , "d" , "e" , "f" };  
//                int r1 = random.nextInt(3)+11 ;     //���ɵ�1λ������  
//                String strR1 = rBase[r1] ;      //����11��14�������  
//                int r2 ;        //���ɵ�2λ������  
//                if(r1 == 13)  
//                    r2 = random.nextInt(7) ;    //����0��7�������  
//                else  
//                    r2 = random.nextInt(16) ;   //����0��16�������  
//                String strR2 = rBase[r2] ;  
//                int r3 = random.nextInt(6) + 10 ;   //���ɵ�1λ��λ��  
//                String strR3 = rBase[r3] ;  
//                int r4 ;        //���ɵ�2λ��λ��  
//                if(r3 == 10)  
//                    r4 = random.nextInt(15) + 1;    //����1��16�������  
//                else if(r3 == 15)  
//                    r4 = random.nextInt(15) ;       //����0��15�������  
//                else  
//                    r4 = random.nextInt(16) ;       //����0��16�������  
//                String strR4 = rBase[r4] ;  
//                //�����ɵĻ�����ת��������  
//                byte[] bytes = new byte[2]   ;        
//                String strR12 = strR1 + strR2 ;     //�����ɵ����뱣�浽�ֽ�����ĵ�1��Ԫ����  
//                int tempLow = Integer.parseInt(strR12, 16) ;  
//                bytes[0] = (byte)tempLow;  
//                String strR34 = strR3 + strR4 ;     //�����ɵ����뱣�浽�ֽ�����ĵ�2��Ԫ����  
//                int tempHigh = Integer.parseInt(strR34, 16) ;  
//                bytes[1] = (byte)tempHigh;  
//                temp = new String(bytes);           //�����ֽ��������ɺ���  
//                break;  
            default:  
                itmp = random.nextInt(10) + 48 ;  
                temp = String.valueOf((char)itmp) ;  
                break;  
            }  
            Color color = new Color(20+random.nextInt(20) , 20+random.nextInt(20) ,20+random.nextInt(20) );  
            g.setColor(color) ;  
            //��������תһ���ĽǶ�  
            AffineTransform trans = new AffineTransform();  
            trans.rotate(random.nextInt(45)*3.14/180, 15*i+8, 7) ;  
//            trans.rotate(random.nextInt(45)*3.14/180, 15*i+8, 7) ;  
            //��������  
            float scaleSize = random.nextFloat() + 1f ;  
            if(scaleSize>1f)  
                scaleSize = 1f ;  
            trans.scale(scaleSize, scaleSize) ;  
            g.setTransform(trans) ;  
            g.drawString(temp, 15*i+18, 14) ;
              
            strbuf.append(temp) ;  
        }  
        System.out.println(">>>>" + strbuf);
        g.dispose() ;  
        return strbuf.toString() ;  
    } 
    
}
