/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.luoteng.captcha.utils;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import net.luoteng.captcha.constant.CaptchaConstant;
import static net.luoteng.captcha.constant.CaptchaConstant.CAPTCHA_HEIGHT;
import static net.luoteng.captcha.constant.CaptchaConstant.CAPTCHA_WIDTH;
import nl.captcha.Captcha;
import nl.captcha.text.renderer.DefaultWordRenderer;
import nl.captcha.text.renderer.WordRenderer;

/**
 * 
 * @author suetming <suetming.ma at gmail.com>
 */
@Slf4j
public class CaptchaUtils implements CaptchaConstant {

    private static final List<Color> COLORS = new ArrayList<>();

    private static final List<Font> FONTS = new ArrayList<>();

    static {
        COLORS.add(Color.CYAN);
        COLORS.add(Color.magenta);
        COLORS.add(Color.DARK_GRAY);
        COLORS.add(Color.PINK);

        FONTS.add(new Font("Geneva", Font.PLAIN, 48));
        FONTS.add(new Font("Courier", Font.ITALIC, 48));
        FONTS.add(new Font("Arial", Font.ITALIC, 48));
    }

    private static final WordRenderer WORD_RENDERER = new DefaultWordRenderer(COLORS, FONTS);

    /**
     * 生成默认尺寸的captcha
     * 
     * @return 
     */
    public static Captcha getCaptcha() {
        return getCaptcha(CAPTCHA_WIDTH, CAPTCHA_HEIGHT);
    }

    /**
     * 生成captcha
     *
     * @param width
     * @param height
     * @return
     */
    public static Captcha getCaptcha(int width, int height) {
        long startTime = System.currentTimeMillis();
        Captcha captcha = new Captcha.Builder(width, height).addText(WORD_RENDERER).gimp().addNoise().build();
        log.debug("captcha generated.[elapsedTime={}]", System.currentTimeMillis() - startTime);
        return captcha;
    }
}
