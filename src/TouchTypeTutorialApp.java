import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.knowm.xchart.DialChart;
import org.knowm.xchart.DialChartBuilder;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.LegendPosition;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class TouchTypeTutorialApp {
	
	User user;
	GUI gui;
	WordGenerator wordGenerator;
	
	
public TouchTypeTutorialApp () {
	
	wordGenerator = new WordGenerator();
	user = new User();
	user.loadUserProfile();
	gui = new GUI(user);
	
}

}
