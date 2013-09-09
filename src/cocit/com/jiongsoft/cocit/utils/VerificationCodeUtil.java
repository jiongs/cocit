package com.jiongsoft.cocit.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 验证码工具类
 * 
 * @author jiongsoft
 * 
 */
public abstract class VerificationCodeUtil {
	/**
	 * 
	 * @param fc
	 * @param bc
	 * @return
	 */
	private static Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	/**
	 * 生成随机验证码，验证码被保存在session(key=cocit_verification_code)中，可以通过{@link #checkVerificationCode(String)进行验证 。
	 */
	public static String makeVerificationCode(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();

		char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

		Random r = new Random();
		int index, len = ch.length;
		for (int i = 0; i < 4; i++) {
			index = r.nextInt(len);
			sb.append(ch[index]);
		}

		HttpSession session = request.getSession(true);
		session.setAttribute("cocit_verification_code", sb.toString());

		return sb.toString();
	}

	/**
	 * 检查客户端提交的验证码是否与session(key=cocit_verification_code)中保存的验证码一致？如果不一致，将抛出异常。
	 * <p>
	 * 检查时将忽略大小写。
	 * 
	 * @param code
	 * @param exceptionMessage
	 */
	public static void checkVerificationCode(HttpServletRequest request, String code, String exceptionMessage) {

		if (StringUtil.isNil(exceptionMessage))
			exceptionMessage = "验证码不正确！";

		HttpSession session = request.getSession(true);
		String validCode = (String) session.getAttribute("cocit_verification_code");

		if (StringUtil.isNil(code) || StringUtil.isNil(validCode))
			throw new CocException(exceptionMessage);

		if (!code.toLowerCase().equals(validCode.toLowerCase()))
			throw new CocException(exceptionMessage);

	}

	/**
	 * 生成随机图片验证码，并将图片对象输出到response中
	 * <p>
	 * 验证码文本被保存在session(key=cocit_verification_code)中，可以通过{@link #checkVerificationCode(String)}进行验证 。
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public static String makeImageVerificationCode(HttpServletRequest request, HttpServletResponse response) {
		int width = 60, height = 20;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		Graphics g = image.getGraphics();

		Random random = new Random();

		// 生成彩色背景
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 100; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

		g.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD | Font.PLAIN, 18));

		StringBuffer sb = new StringBuffer();
		int index, len = ch.length;
		for (int i = 0; i < 4; i++) {
			g.setColor(new Color(random.nextInt(88), random.nextInt(188), random.nextInt(255)));

			// 写什么数字，在图片 的什么位置画
			index = random.nextInt(len);
			g.drawString("" + ch[index], (i * 14) + 2, 16);

			sb.append(ch[index]);
		}

		HttpSession session = request.getSession(true);
		session.setAttribute("cocit_verification_code", sb.toString());

		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		OutputStream out = null;
		try {
			out = response.getOutputStream();

			ImageIO.write(image, "JPEG", out);
		} catch (IOException ex) {
			Log.error("VerificationCodeUtil.writeVarificationCodeImage: error! ", ex);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException ex) {
					Log.error("VerificationCodeUtil.writeVarificationCodeImage: error! ", ex);
				}
			}
		}

		return sb.toString();
	}

}
