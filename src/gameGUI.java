import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BoundedRangeModel;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class gameGUI {

	// The basic JFrame
	private JFrame frmLiveInOntario;

	// play sound
	Clip clip;

	// basic textfields
	private JTextField health_text;
	private JTextField debt_text;
	private JTextField cash_text;
	private JTextField reputation_text;
	private JTextField deposit_text;
	private JLabel txtYourStatus;
	private JLabel txtPlaces;
	private JLabel txtCityMap;
	private JLabel txtBlackMarket;
	private JLabel txtYourStorehouse;

	// map panel
	private JPanel map_panel = new JPanel();

	// some basic data
	private static int open_time = 0;
	private int move_status = 0;
	private int sound_status = 1;
	private int hacker_status = 0;
	private int sort_status = 0;
	private int storehouse_expand = 0;
	private int current_storehouse_size = 0;
	private int MAX_STOREHOUSE_SIZE = 100;
	private int current_day = 0;
	private final int TOTAL_DAY = 50;
	private int news_slider = -1;
	private int slide_mode = 1;

	// record price up
	private int Water_price_increase = 0;
	private int IED_price_increase = 0;
	private int weapon_price_increase = 0;

	// record reputation increase caused by event B
	private int current_B_reputation = 0;
	private final int MAX_B_REPUTATION = 5;

	// some basic string
	private String version = null;
	private String copyright = "Copyright(C) WLU CP317 Software Engineering 3D development team, 2019 Spring";

	// basic labels
	private JLabel store_size_label = new JLabel();
	private JLabel lblDay = new JLabel("Day: " + current_day + "/" + TOTAL_DAY);

	// player status
	private int cash = 2000;
	private int deposit = 0;
	private int health = 100;
	private int debt = 5500;
	private int reputation = 50;

	// places Jbutton
	private JToggleButton btn1 = new JToggleButton("Finch");
	private JToggleButton btn2 = new JToggleButton("Eglinton");
	private JToggleButton btn3 = new JToggleButton("St. Clair");
	private JToggleButton btn4 = new JToggleButton("Dundas");
	private JToggleButton btn5 = new JToggleButton("Queen");
	private JToggleButton btn6 = new JToggleButton("Osgoode");
	private JToggleButton btn7 = new JToggleButton("King");
	private JToggleButton btn8 = new JToggleButton("Dupont");
	private JToggleButton btn09 = new JToggleButton("Old Mill");
	private JToggleButton btn10 = new JToggleButton("Jane");
	private JToggleButton btn11 = new JToggleButton("Keele");
	private JToggleButton btn12 = new JToggleButton("Bay");
	private JToggleButton btn13 = new JToggleButton("Pape");
	private JToggleButton btn14 = new JToggleButton("Coxwell");
	private JToggleButton btn15 = new JToggleButton("Mildland");
	private JToggleButton btn16 = new JToggleButton("McCowan");

	// switch sub-area
	private JButton btn_switch = new JButton("Switch");

	// record last clicked button
	private JToggleButton btn_record = new JToggleButton();

	// the models of the commodity list
	private DefaultListModel<String> model1 = new DefaultListModel<>();
	private DefaultListModel<Integer> model2 = new DefaultListModel<>();
	private DefaultListModel<String> model3 = new DefaultListModel<>();
	private DefaultListModel<Integer> model4 = new DefaultListModel<>();
	private DefaultListModel<Integer> model5 = new DefaultListModel<>();

	// JList
	private JList<String> List1 = new JList<>(model1);
	private JList<Integer> List2 = new JList<>(model2);
	private JList<String> List3 = new JList<>(model3);
	private JList<Integer> List4 = new JList<>(model4);
	private JList<Integer> List5 = new JList<>(model5);

	// commodities
	private Commodity commodity1;
	private Commodity commodity2;
	private Commodity commodity3;
	private Commodity commodity4;
	private Commodity commodity5;
	private Commodity commodity6;
	private Commodity commodity7;
	private Commodity commodity8;
	private Commodity commodity9;
	private Commodity commodity10;

	// Map gridbag constraints
	private GridBagConstraints gbc_btn_1 = new GridBagConstraints();
	private GridBagConstraints gbc_btn_2 = new GridBagConstraints();
	private GridBagConstraints gbc_btn_3 = new GridBagConstraints();
	private GridBagConstraints gbc_btn_4 = new GridBagConstraints();
	private GridBagConstraints gbc_btn_5 = new GridBagConstraints();
	private GridBagConstraints gbc_btn_6 = new GridBagConstraints();
	private GridBagConstraints gbc_btn_7 = new GridBagConstraints();
	private GridBagConstraints gbc_btn_8 = new GridBagConstraints();
	private GridBagConstraints gbc_btn_9 = new GridBagConstraints();

	// some commodity lists
	private ArrayList<Commodity> market_commodity_list = new ArrayList<Commodity>();
	private ArrayList<Commodity> storehouse_commodity_list = new ArrayList<Commodity>();
	private ArrayList<Commodity> total_commodity_list = new ArrayList<Commodity>();

	// temp commodity lists
	private ArrayList<Commodity> buy_list = new ArrayList<Commodity>();
	private ArrayList<Commodity> sell_list = new ArrayList<Commodity>();

	private static Random random = new Random();

	// generate players name
	private static String rand_name1 = generateName();
	private static String rand_name2 = generateName();
	private static String rand_name3 = generateName();
	private static String rand_name4 = generateName();
	private static String rand_name5 = generateName();
	private static String rand_name6 = generateName();
	private static String rand_name7 = generateName();
	private static String rand_name8 = generateName();
	private static String rand_name9 = generateName();
	private static String rand_name10 = generateName();

	// generate random money
	private static int rand_money1 = 500000 + random.nextInt(100000); // 50-60W
	private static int rand_money2 = 1000000 + random.nextInt(200000); // 100-120W
	private static int rand_money3 = 4000000 + random.nextInt(1000000); // 400-500W
	private static int rand_money4 = 8000000 + random.nextInt(2000000); // 800-1000W
	private static int rand_money5 = 10000000 + random.nextInt(2000000); // 1-1.2M
	private static int rand_money6 = 40000000 + random.nextInt(10000000); // 4-5M
	private static int rand_money7 = 50000000 + random.nextInt(10000000); // 5-6M
	private static int rand_money8 = 80000000 + random.nextInt(10000000); // 8-9M
	private static int rand_money9 = 90000000 + random.nextInt(10000000); // 9-10M
	private static int rand_money10 = 100000000 + random.nextInt(10000000); // 1-1.1Y

	// generate players health
	private static int rand_health1 = 5 + random.nextInt(95);
	private static int rand_health2 = 5 + random.nextInt(95);
	private static int rand_health3 = 5 + random.nextInt(95);
	private static int rand_health4 = 5 + random.nextInt(95);
	private static int rand_health5 = 5 + random.nextInt(95);
	private static int rand_health6 = 5 + random.nextInt(95);
	private static int rand_health7 = 5 + random.nextInt(95);
	private static int rand_health8 = 5 + random.nextInt(95);
	private static int rand_health9 = 5 + random.nextInt(95);
	private static int rand_health10 = 5 + random.nextInt(95);

	// generate players reputation
	private static int temp_reputation1 = 1 + random.nextInt(99);
	private static int temp_reputation2 = 1 + random.nextInt(99);
	private static int temp_reputation3 = 1 + random.nextInt(99);
	private static int temp_reputation4 = 1 + random.nextInt(99);
	private static int temp_reputation5 = 1 + random.nextInt(99);
	private static int temp_reputation6 = 1 + random.nextInt(99);
	private static int temp_reputation7 = 1 + random.nextInt(99);
	private static int temp_reputation8 = 1 + random.nextInt(99);
	private static int temp_reputation9 = 1 + random.nextInt(99);
	private static int temp_reputation10 = 1 + random.nextInt(99);

	// backdround msg
	private String msg = "<html>The continuous and highlighted international tensions eventually has reached an<br>irremediable stage, a nuclear war was broken out. Brilliant human civilization was<br>destroyed by the gigantic mushroom cloud, the survivor migrated to the<br>underground world to avoid the lethal nuclear radiation, and transport through<br>subway tunnels. However, people won't give up, they still wish to rebuild a<br>homeland in the future. You are one of the survivors, local gang found you in the<br>ruins of war and saved your life from severe comatose. The cost is, however, you<br>realized that you owe them a huge usury. As a smart businessman in the pre-war<br>society, you decided to use your intellect to pay off the debt, and further, earn<br>more money to help you survive in the cruel, strange world. Are you going to stay<br>living a hard life or trying your best to get rich? Would you like to implement your<br>chivalry fully? Or go along with the evil force? The choice is all yours!</html>";

	/**
	 * Launch application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("unused")
			public void run() {
				try {
					gameGUI window = new gameGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * constructor
	 */
	private gameGUI() {
		set_map_gbc();
		generate_total_commodity();
		generate_market_commodity();
		setup_contents();
		if (open_time == 0)
			initial_game_background();
		open_time++;
	}

	/**
	 * Initialize JFrame contents.
	 */
	private void setup_contents() {
		setup_mainframe();
		setup_status_panel();
		setup_places_panel();
		setup_map_panel();
		refresh_map_panel();
		setup_market_jlist();
		coordinate_jlist();
		setup_day_label();
		setup_buy_in();
		setup_sell_out();
		setup_discard();
		setup_storehouse();
		setup_top_menu();
		setup_news();
	}

	private void generate_total_commodity() {
		Random random = new Random();
		int rand_price1 = 10 + random.nextInt(30);
		int rand_price2 = 40 + random.nextInt(120);
		int rand_price3 = 75 + random.nextInt(225);
		int rand_price4 = 200 + random.nextInt(600);
		int rand_price5 = 750 + random.nextInt(2250);
		int rand_price6 = 500 + random.nextInt(1500);
		int rand_price7 = 3750 + random.nextInt(11250);
		int rand_price8 = 10000 + random.nextInt(30000);
		int rand_price9 = 20000 + random.nextInt(60000);
		int rand_price10 = 50000 + random.nextInt(150000);

		commodity1 = new Commodity("Water", rand_price1);
		commodity2 = new Commodity("Grains", rand_price2);
		commodity3 = new Commodity("Clothes", rand_price3);
		commodity4 = new Commodity("Meats", rand_price4);
		commodity5 = new Commodity("Drugs", rand_price5);
		commodity6 = new Commodity("Diesel", rand_price6);
		commodity7 = new Commodity("Explosives <IED>", rand_price7);
		commodity8 = new Commodity("Pre-war automobiles", rand_price8);
		commodity9 = new Commodity("Weapons", rand_price9);
		commodity10 = new Commodity("Computers", rand_price10);

		total_commodity_list.add(commodity1);
		total_commodity_list.add(commodity2);
		total_commodity_list.add(commodity3);
		total_commodity_list.add(commodity4);
		total_commodity_list.add(commodity5);
		total_commodity_list.add(commodity6);
		total_commodity_list.add(commodity7);
		total_commodity_list.add(commodity8);
		total_commodity_list.add(commodity9);
		total_commodity_list.add(commodity10);
	}

	private void refresh_total_commodity() {
		Random random = new Random();
		int rand_price1 = 10 + random.nextInt(30);
		int rand_price2 = 40 + random.nextInt(120);
		int rand_price3 = 75 + random.nextInt(225);
		int rand_price4 = 200 + random.nextInt(600);
		int rand_price5 = 750 + random.nextInt(2250);
		int rand_price6 = 500 + random.nextInt(1500);
		int rand_price7 = 3750 + random.nextInt(11250);
		int rand_price8 = 10000 + random.nextInt(30000);
		int rand_price9 = 20000 + random.nextInt(60000);
		int rand_price10 = 50000 + random.nextInt(150000);

		commodity1.setPrice(rand_price1);
		commodity2.setPrice(rand_price2);
		commodity3.setPrice(rand_price3);
		commodity4.setPrice(rand_price4);
		commodity5.setPrice(rand_price5);
		commodity6.setPrice(rand_price6);
		commodity7.setPrice(rand_price7);
		commodity8.setPrice(rand_price8);
		commodity9.setPrice(rand_price9);
		commodity10.setPrice(rand_price10);
	}

	private void set_map_gbc() {
		gbc_btn_1.insets = new Insets(0, 0, 5, 5);
		gbc_btn_1.gridx = 0;
		gbc_btn_1.gridy = 0;
		gbc_btn_2.insets = new Insets(0, 0, 5, 5);
		gbc_btn_2.gridx = 1;
		gbc_btn_2.gridy = 0;
		gbc_btn_3.insets = new Insets(0, 0, 5, 5);
		gbc_btn_3.gridx = 2;
		gbc_btn_3.gridy = 0;
		gbc_btn_4.insets = new Insets(0, 0, 5, 5);
		gbc_btn_4.gridx = 0;
		gbc_btn_4.gridy = 1;
		gbc_btn_5.fill = GridBagConstraints.BOTH;
		gbc_btn_5.insets = new Insets(0, 0, 5, 5);
		gbc_btn_5.gridx = 1;
		gbc_btn_5.gridy = 1;
		gbc_btn_6.insets = new Insets(0, 0, 5, 5);
		gbc_btn_6.gridx = 2;
		gbc_btn_6.gridy = 1;
		gbc_btn_7.insets = new Insets(0, 0, 5, 5);
		gbc_btn_7.gridx = 0;
		gbc_btn_7.gridy = 2;
		gbc_btn_8.insets = new Insets(0, 0, 5, 5);
		gbc_btn_8.gridx = 1;
		gbc_btn_8.gridy = 2;
		gbc_btn_9.insets = new Insets(0, 0, 5, 5);
		gbc_btn_9.gridx = 2;
		gbc_btn_9.gridy = 2;
	}

	// operation when switching places
	private void place_switch() {
		playSound("sound/place.wav");
		generate_market_commodity();
		randomEvent();
		day_pass();

		// resume price up goods
		if (Water_price_increase == 1)
			Water_price_increase = 0;
		if (IED_price_increase == 1)
			IED_price_increase = 0;
		if (weapon_price_increase == 1)
			weapon_price_increase = 0;

		// resume B-increased reputation
		current_B_reputation = 0;

		// clean buy_list & sell_list
		buy_list.clear();
		sell_list.clear();
	}

	// operation when day pass
	private void day_pass() {

		// debt & deposit increase
		debt *= 1.1;
		debt_text.setText(Integer.toString(debt));
		deposit *= 1.01;
		deposit_text.setText(Integer.toString(deposit));

		// 1-50 day
		if (current_day < TOTAL_DAY) {
			lblDay.setText("Day: " + Integer.toString(++current_day) + "/" + TOTAL_DAY);
			if (current_day > (TOTAL_DAY - 5))
				time_limit_mention();
			if (current_day % 5 == 0)
				fixedEvent();
			if (current_day % 10 == 0 && debt == 0)
				add_debt();
			frmLiveInOntario.setTitle("The Underground Survivor " + "(" + current_day + "/" + TOTAL_DAY + " days)");
		}

		// over 50 day
		else {
			playSound("sound/50day.wav");
			JOptionPane.showMessageDialog(new JFrame(), "It's been " + TOTAL_DAY + " days! I have to leave Ontario!");
			game_ranking();
		}
	}

	private void add_debt() {
		playSound("sound/hack.wav");

		if (current_storehouse_size <= 150) {
			JOptionPane.showMessageDialog(new JFrame(), "The gang said I owe them 1000 storage fee :(");
			debt += 1000;
		} else if ((current_storehouse_size > 150) && (current_storehouse_size <= 300)) {
			JOptionPane.showMessageDialog(new JFrame(), "The gang said I owe them 5000 storage fee :(");
			debt += 5000;
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "The gang said I owe them 20000 storage fee :(");
			debt += 20000;
		}
		debt_text.setText(Integer.toString(debt));
	}

	private void time_limit_mention() {

		// exactly 46 day, play sound
		if (current_day == (TOTAL_DAY - 5) + 1)
			playSound("sound/unhappy.wav");

		// mention player
		lblDay.setForeground(Color.RED);
		JOptionPane.showMessageDialog(new JFrame(),
				"My Ontario life has only " + (TOTAL_DAY - current_day + 1) + " days left.");
	}

	private void restart_game() {
		playSound("sound/restart.wav");
		frmLiveInOntario.setVisible(false);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
		}

		// end old GUI
		frmLiveInOntario.dispose();

		// start new GUI
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gameGUI window = new gameGUI();
					window.frmLiveInOntario.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static String generateName() {
		String[] Beginning = { "Kr", "Ca", "Ra", "Mrok", "Cru", "Ray", "Bre", "Zed", "Drak", "Mor", "Jag", "Mer", "Jar",
				"Mjol", "Zork", "Mad", "Cry", "Zur", "Creo", "Azak", "Azur", "Rei", "Cro", "Mar", "Luk" };
		String[] Middle = { "air", "ir", "mi", "sor", "mee", "clo", "red", "cra", "ark", "arc", "miri", "lori", "cres",
				"mur", "zer", "marac", "zoir", "slamar", "salmar", "urak" };
		String[] End = { "d", "ed", "ark", "arc", "es", "er", "der", "tron", "med", "ure", "zur", "cred", "mur" };
		Random rand = new Random();
		return Beginning[rand.nextInt(Beginning.length)] + Middle[rand.nextInt(Middle.length)]
				+ End[rand.nextInt(End.length)];
	}

	@SuppressWarnings("serial")
	private class DisabledItemSelectionModel extends DefaultListSelectionModel {
		@Override
		public void setSelectionInterval(int index0, int index1) {
			super.setSelectionInterval(-1, -1);
		}
	}

	private static void delete(File file) throws IOException {

		// if it is a folder
		if (file.isDirectory()) {

			// directory is empty, then delete it
			if (file.list().length == 0) {

				file.delete();
				System.out.println("Directory is deleted : " + file.getAbsolutePath());

			}

			else {

				// list all the directory contents
				String files[] = file.list();

				for (String temp : files) {
					// construct the file structure
					File fileDelete = new File(file, temp);

					// recursive delete
					delete(fileDelete);
				}

				// check the directory again, if empty then delete it
				if (file.list().length == 0) {
					file.delete();
					System.out.println("Directory is deleted : " + file.getAbsolutePath());
				}
			}

		}

		else {
			// if file, delete it
			file.delete();
			System.out.println("File is deleted : " + file.getAbsolutePath());
		}
	}

	private void game_ranking() {
		JFrame frame = new JFrame();

		// title
		JLabel label = new JLabel("Game Ranking (Top 10)");
		label.setBounds(29, 35, 300, 25);
		label.setFont(new Font("SimSun", Font.BOLD, 16));
		frame.getContentPane().add(label);

		// the models of the rank list
		DefaultListModel<Integer> model1 = new DefaultListModel<>();
		DefaultListModel<String> model2 = new DefaultListModel<>();
		DefaultListModel<Integer> model3 = new DefaultListModel<>();
		DefaultListModel<Integer> model4 = new DefaultListModel<>();
		DefaultListModel<String> model5 = new DefaultListModel<>();

		// JList of models
		JList<Integer> rank_List1 = new JList<>(model1);
		JList<String> rank_List2 = new JList<>(model2);
		JList<Integer> rank_List3 = new JList<>(model3);
		JList<Integer> rank_List4 = new JList<>(model4);
		JList<String> rank_List5 = new JList<>(model5);

		// disable JList selection
		rank_List1.setSelectionModel(new DisabledItemSelectionModel());
		rank_List2.setSelectionModel(new DisabledItemSelectionModel());
		rank_List3.setSelectionModel(new DisabledItemSelectionModel());
		rank_List4.setSelectionModel(new DisabledItemSelectionModel());
		rank_List5.setSelectionModel(new DisabledItemSelectionModel());

		// rank scroller
		JScrollPane Scroller1 = new JScrollPane(rank_List1);
		Scroller1.setBounds(29, 90, 78, 210);
		rank_List1.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		frame.getContentPane().add(Scroller1);

		// name scroller
		JScrollPane Scroller2 = new JScrollPane(rank_List2);
		Scroller2.setBounds(106, 90, 118, 210);
		rank_List2.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		frame.getContentPane().add(Scroller2);

		// money scroller
		JScrollPane Scroller3 = new JScrollPane(rank_List3);
		Scroller3.setBounds(223, 90, 98, 210);
		rank_List3.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		frame.getContentPane().add(Scroller3);

		// health scroller
		JScrollPane Scroller4 = new JScrollPane(rank_List4);
		Scroller4.setBounds(320, 90, 98, 210);
		rank_List4.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		frame.getContentPane().add(Scroller4);

		// reputation scroller
		JScrollPane Scroller5 = new JScrollPane(rank_List5);
		Scroller5.setBounds(417, 90, 110, 210);
		rank_List5.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		frame.getContentPane().add(Scroller5);

		// rank button
		JButton btn_rank = new JButton("Rank");
		btn_rank.setBounds(29, 68, 77, 23);
		btn_rank.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(btn_rank);

		// name button
		JButton btn_name = new JButton("Name");
		btn_name.setBounds(106, 68, 117, 23);
		btn_name.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(btn_name);

		// money button
		JButton btn_money = new JButton("Property");
		btn_money.setBounds(223, 68, 97, 23);
		btn_money.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(btn_money);

		// health button
		JButton btn_health = new JButton("Health");
		btn_health.setBounds(320, 68, 97, 23);
		btn_health.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(btn_health);

		// reputation button
		JButton btn_reputation = new JButton("Reputation");
		btn_reputation.setBounds(417, 68, 109, 23);
		btn_reputation.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(btn_reputation);

		// get min money on ranking
		int money_array[] = { rand_money1, rand_money2, rand_money3, rand_money4, rand_money5, rand_money6, rand_money7,
				rand_money8, rand_money9, rand_money10 };
		Arrays.sort(money_array);
		int min_money = money_array[0];

		// compare user money to min of ranking
		String player_name = null;
		int player_money = cash + deposit;
		if (player_money > min_money)
			player_name = JOptionPane.showInputDialog("You are Wealthy! Your name is?");
		else
			JOptionPane.showMessageDialog(new JFrame(),
					"You are not rich enough to enter the ranking (The lowest of current ranking is $" + min_money
							+ ")",
					"Keep fighting!", JOptionPane.INFORMATION_MESSAGE);

		playSound("sound/happy.wav");

		// generate players
		Player player1 = new Player(rand_name1, rand_money1, rand_health1, Integer.toString(temp_reputation1));
		Player player2 = new Player(rand_name2, rand_money2, rand_health2, Integer.toString(temp_reputation2));
		Player player3 = new Player(rand_name3, rand_money3, rand_health3, Integer.toString(temp_reputation3));
		Player player4 = new Player(rand_name4, rand_money4, rand_health4, Integer.toString(temp_reputation4));
		Player player5 = new Player(rand_name5, rand_money5, rand_health5, Integer.toString(temp_reputation5));
		Player player6 = new Player(rand_name6, rand_money6, rand_health6, Integer.toString(temp_reputation6));
		Player player7 = new Player(rand_name7, rand_money7, rand_health7, Integer.toString(temp_reputation7));
		Player player8 = new Player(rand_name8, rand_money8, rand_health8, Integer.toString(temp_reputation8));
		Player player9 = new Player(rand_name9, rand_money9, rand_health9, Integer.toString(temp_reputation9));
		Player player10 = new Player(rand_name10, rand_money10, rand_health10, Integer.toString(temp_reputation10));
		Player myself = new Player(player_name, player_money, health, Integer.toString(reputation));

		// add players to list
		ArrayList<Player> player_list = new ArrayList<Player>();
		player_list.add(player1);
		player_list.add(player2);
		player_list.add(player3);
		player_list.add(player4);
		player_list.add(player5);
		player_list.add(player6);
		player_list.add(player7);
		player_list.add(player8);
		player_list.add(player9);
		player_list.add(player10);
		player_list.add(myself);

		// sort player
		Comparator<Player> compareByMoney = (Player o1, Player o2) -> o1.getMoney().compareTo(o2.getMoney());
		Collections.sort(player_list, compareByMoney.reversed());

		// show players on JList
		int total_number = 10;
		for (int i = 0; i < total_number; i++) {
			model1.addElement(i + 1);
			model2.addElement(player_list.get(i).getName());
			model3.addElement(player_list.get(i).getMoney());
			model4.addElement(player_list.get(i).getHealth());
			int temp_reputation = Integer.parseInt(player_list.get(i).getReputation());
			if (temp_reputation >= 0 && temp_reputation < 10)
				player_list.get(i).setReputation("Worst" + "(" + player_list.get(i).getReputation() + ")");
			else if (temp_reputation >= 10 && temp_reputation < 20)
				player_list.get(i).setReputation("Terrible" + "(" + player_list.get(i).getReputation() + ")");
			else if (temp_reputation >= 20 && temp_reputation < 30)
				player_list.get(i).setReputation("Notorious" + "(" + player_list.get(i).getReputation() + ")");
			else if (temp_reputation >= 30 && temp_reputation < 40)
				player_list.get(i).setReputation("Insidious" + "(" + player_list.get(i).getReputation() + ")");
			else if (temp_reputation >= 40 && temp_reputation < 50)
				player_list.get(i).setReputation("Sinister" + "(" + player_list.get(i).getReputation() + ")");
			else if (temp_reputation >= 50 && temp_reputation < 60)
				player_list.get(i).setReputation("So so" + "(" + player_list.get(i).getReputation() + ")");
			else if (temp_reputation >= 60 && temp_reputation < 70)
				player_list.get(i).setReputation("Fine" + "(" + player_list.get(i).getReputation() + ")");
			else if (temp_reputation >= 70 && temp_reputation < 80)
				player_list.get(i).setReputation("Kindhearted" + "(" + player_list.get(i).getReputation() + ")");
			else if (temp_reputation >= 80 && temp_reputation < 90)
				player_list.get(i).setReputation("Virtuous" + "(" + player_list.get(i).getReputation() + ")");
			else if (temp_reputation >= 90 && temp_reputation <= 100)
				player_list.get(i).setReputation("Perfect" + "(" + player_list.get(i).getReputation() + ")");
			model5.addElement(player_list.get(i).getReputation());
		}

		// OK button
		JButton btn_OK = new JButton("OK");
		btn_OK.setBounds(236, 320, 70, 30);
		btn_OK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				if (health == 0 || current_day >= 50)
					restart_game();
			}
		});
		frame.getContentPane().add(btn_OK);

		// setup frame
		ImageIcon img = new ImageIcon("icon/ontario.jpg");
		frame.setIconImage(img.getImage());
		frame.setResizable(false);
		frame.getContentPane().setBackground(UIManager.getColor("Button.background"));
		frame.setTitle("Game Ranking");
		frame.setBounds(300, 200, 550, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
	}

	private void player_die() {
		// prevent health become negative
		health = 0;
		health_text.setText(Integer.toString(health));

		playSound("sound/unhappy.wav");

		// show dialog
		JOptionPane.showMessageDialog(new JFrame(), "Ooops, my HP is 0, bye bye bealtiful world :(");

		// rank & restart
		game_ranking();
	}

	// randomly generate market commodity list
	private void generate_market_commodity() {

		refresh_total_commodity();

		market_commodity_list.clear();
		model1.clear();
		model2.clear();

		// generate a random number between 5-7
		Random random = new Random();
		int base = 5;
		int commodity_num1 = base + random.nextInt(3);

		// fill in the market list
		for (int i = 0; i < commodity_num1; i++) {
			int index = random.nextInt(total_commodity_list.size());
			Commodity temp_commodity = total_commodity_list.get(index);
			while (market_commodity_list.contains(temp_commodity)) {
				index = random.nextInt(total_commodity_list.size());
				temp_commodity = total_commodity_list.get(index);
			}
			market_commodity_list.add(temp_commodity);
		}

		// update the ListModel of market list
		for (int i = 0; i < market_commodity_list.size(); i++) {
			model1.addElement((i + 1) + ")  " + market_commodity_list.get(i).getName());
			model2.addElement(market_commodity_list.get(i).getPrice());
		}
	}

	private void playSound(String soundName) {
		if (sound_status == 1) {
			try {
				AudioInputStream audioInputStream = AudioSystem
						.getAudioInputStream(new File(soundName).getAbsoluteFile());
				clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
			} catch (Exception ex) {
				System.out.println("Err playing sound.");
				ex.printStackTrace();
			}
		}
	}

	private static void download(String url, String fileName) throws Exception {
		try (InputStream in = URI.create(url).toURL().openStream()) {
			Files.copy(in, Paths.get(fileName));
		}
	}

	public static void unzip(File source, String out) throws IOException {
		try (ZipInputStream zis = new ZipInputStream(new FileInputStream(source))) {
			ZipEntry entry = zis.getNextEntry();
			while (entry != null) {
				File file = new File(out, entry.getName());
				if (entry.isDirectory())
					file.mkdirs();

				else {
					File parent = file.getParentFile();
					if (!parent.exists())
						parent.mkdirs();
					try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {
						byte[] buffer = new byte[Math.toIntExact(entry.getSize())];
						int location;
						while ((location = zis.read(buffer)) != -1) {
							bos.write(buffer, 0, location);
						}
					}
				}
				entry = zis.getNextEntry();
			}
		}
	}

	// the switch button function
	private void refresh_map_panel() {

		// remove old items
		map_panel.removeAll();

		// switch to 2nd area
		if (move_status == 0) {
			move_status = 1;
			txtCityMap.setText("Underground Map 1");
			map_panel.setBackground(new Color(250, 250, 210));
			map_panel.add(btn1, gbc_btn_1);
			map_panel.add(btn2, gbc_btn_2);
			map_panel.add(btn3, gbc_btn_3);
			map_panel.add(btn4, gbc_btn_4);
			map_panel.add(btn_switch, gbc_btn_5);
			btn_switch.setBackground(Color.YELLOW);
			map_panel.add(btn5, gbc_btn_6);
			map_panel.add(btn6, gbc_btn_7);
			map_panel.add(btn7, gbc_btn_8);
			map_panel.add(btn8, gbc_btn_9);
		}

		// switch to 1st area
		else {
			move_status = 0;
			txtCityMap.setText("Underground Map 2");
			map_panel.setBackground(new Color(224, 255, 255));
			map_panel.add(btn09, gbc_btn_1);
			map_panel.add(btn10, gbc_btn_2);
			map_panel.add(btn11, gbc_btn_3);
			map_panel.add(btn12, gbc_btn_4);
			map_panel.add(btn_switch, gbc_btn_5);
			btn_switch.setBackground(Color.CYAN);
			map_panel.add(btn13, gbc_btn_6);
			map_panel.add(btn14, gbc_btn_7);
			map_panel.add(btn15, gbc_btn_8);
			map_panel.add(btn16, gbc_btn_9);
		}

		// refresh the JFrame
		frmLiveInOntario.validate();
		frmLiveInOntario.invalidate();
		frmLiveInOntario.revalidate();
		frmLiveInOntario.repaint();
	}

	private void initial_game_background() {
		JFrame frame = new JFrame();

		// add background picture to JFrame
		try {
			frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("picture/nuclear.jpg")))));
		} catch (IOException e1) {
		}

		// create game background text
		JLabel label = new JLabel(msg);
		label.setBounds(35, 10, 700, 218);
		label.setFont(new Font("SimSun", Font.PLAIN, 15));
		label.setLocation(35, 10);
		frame.getContentPane().add(label);

		// create progress bar
		JProgressBar bar = new JProgressBar();
		bar.setValue(0);
		bar.setBounds(270, 255, 200, 15);
		bar.setStringPainted(true);
		frame.getContentPane().add(bar);

		// setup labels
		JLabel label1 = new JLabel("Initializing Main Frame....", SwingConstants.CENTER);
		JLabel label2 = new JLabel("Initializing Status Panel....", SwingConstants.CENTER);
		JLabel label3 = new JLabel("Initializing Places Panel....", SwingConstants.CENTER);
		JLabel label4 = new JLabel("Initializing Map Panel....", SwingConstants.CENTER);
		JLabel label5 = new JLabel("Initializing Market List....", SwingConstants.CENTER);
		JLabel label6 = new JLabel("Initializing Storehouse List....", SwingConstants.CENTER);
		JLabel label7 = new JLabel("Initializing Top Menu....", SwingConstants.CENTER);
		JLabel label8 = new JLabel("Initializing finished, let's get to the Ontario....", SwingConstants.CENTER);

		// setup label location
		label1.setBounds(230, 230, 300, 30);
		label2.setBounds(230, 230, 300, 30);
		label3.setBounds(230, 230, 300, 30);
		label4.setBounds(230, 230, 300, 30);
		label5.setBounds(230, 230, 300, 30);
		label6.setBounds(230, 230, 300, 30);
		label7.setBounds(230, 230, 300, 30);
		label8.setBounds(130, 230, 500, 30);

		// set label font
		label1.setFont(new Font("SimSun", Font.BOLD, 15));
		label2.setFont(new Font("SimSun", Font.BOLD, 15));
		label3.setFont(new Font("SimSun", Font.BOLD, 15));
		label4.setFont(new Font("SimSun", Font.BOLD, 15));
		label5.setFont(new Font("SimSun", Font.BOLD, 15));
		label6.setFont(new Font("SimSun", Font.BOLD, 15));
		label7.setFont(new Font("SimSun", Font.BOLD, 15));
		label8.setFont(new Font("SimSun", Font.BOLD, 15));

		// add label to frame
		frame.getContentPane().add(label1);
		frame.getContentPane().add(label2);
		frame.getContentPane().add(label3);
		frame.getContentPane().add(label4);
		frame.getContentPane().add(label5);
		frame.getContentPane().add(label6);
		frame.getContentPane().add(label7);
		frame.getContentPane().add(label8);

		// OK button
		JButton btn_OK = new JButton("Let's Start Game  >>>");
		btn_OK.setBounds(270, 280, 200, 30);
		btn_OK.setBackground(Color.LIGHT_GRAY);
		btn_OK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("sound/place.wav");
				frame.dispose();
				frmLiveInOntario.setVisible(true);
			}
		});
		frame.getContentPane().add(btn_OK);

		// hide panels and disable button
		label1.setVisible(false);
		label2.setVisible(false);
		label3.setVisible(false);
		label4.setVisible(false);
		label5.setVisible(false);
		label6.setVisible(false);
		label7.setVisible(false);
		label8.setVisible(false);
		btn_OK.setEnabled(false);

		// setup frame
		ImageIcon img = new ImageIcon("icon/ontario.jpg");
		frame.setIconImage(img.getImage());
		frame.setResizable(false);
		frame.getContentPane().setBackground(UIManager.getColor("Button.background"));
		frame.setTitle("Story of 'The Underground Survivor'");
		frame.setBounds(300, 200, 740, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		// simulate initializing
		SwingWorker<Void, Void> w = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {

				// Initializing Main Frame....
				try {
					Thread.sleep(100 + random.nextInt(200));
				} catch (InterruptedException e) {
				}
				label1.setVisible(true);
				bar.setValue(13);

				// Initializing Status Panel....
				try {
					Thread.sleep(100 + random.nextInt(200));
				} catch (InterruptedException e) {
				}
				label1.setVisible(false);
				label2.setVisible(true);
				bar.setValue(25);

				// Initializing Places Panel....
				try {
					Thread.sleep(100 + random.nextInt(200));
				} catch (InterruptedException e) {
				}
				label2.setVisible(false);
				label3.setVisible(true);
				bar.setValue(37);

				// Initializing Map Panel....
				try {
					Thread.sleep(100 + random.nextInt(200));
				} catch (InterruptedException e) {
				}
				label3.setVisible(false);
				label4.setVisible(true);
				bar.setValue(50);

				// Initializing Market List....
				try {
					Thread.sleep(100 + random.nextInt(200));
				} catch (InterruptedException e) {
				}
				label4.setVisible(false);
				label5.setVisible(true);
				bar.setValue(63);

				// Initializing Storehouse List....
				try {
					Thread.sleep(100 + random.nextInt(300));
				} catch (InterruptedException e) {
				}
				label5.setVisible(false);
				label6.setVisible(true);
				bar.setValue(75);

				// Initializing Top Menu....
				try {
					Thread.sleep(100 + random.nextInt(200));
				} catch (InterruptedException e) {
				}
				label6.setVisible(false);
				label7.setVisible(true);
				bar.setValue(88);

				// Initializing finished, let's get to the Ontario....
				try {
					Thread.sleep(100 + random.nextInt(200));
				} catch (InterruptedException e) {
				}
				label7.setVisible(false);
				label8.setVisible(true);
				bar.setValue(100);

				// enable button
				btn_OK.setEnabled(true);

				return null;
			}
		};
		w.execute();
	}

	// intro to game background
	private void game_background() {
		JOptionPane.showMessageDialog(new JFrame(), msg, "Game background", JOptionPane.PLAIN_MESSAGE);
	}

	// all buttons "setSelected" false
	private void releaseButton() {
		btn1.setSelected(false);
		btn2.setSelected(false);
		btn3.setSelected(false);
		btn4.setSelected(false);
		btn5.setSelected(false);
		btn6.setSelected(false);
		btn7.setSelected(false);
		btn8.setSelected(false);
		btn09.setSelected(false);
		btn10.setSelected(false);
		btn11.setSelected(false);
		btn12.setSelected(false);
		btn13.setSelected(false);
		btn14.setSelected(false);
		btn15.setSelected(false);
		btn16.setSelected(false);
	}

	private void fixedEvent() {

		// low health, send hospital, recover 20HP, increase debt 5000
		if (health < 40 && current_day % 10 == 0) {
			playSound("sound/unhappy.wav");
			int HP_point = 20;
			health += HP_point;
			health_text.setText(Integer.toString(health));
			int debt_increase = 5000;
			debt += debt_increase;
			JOptionPane.showMessageDialog(new JFrame(),
					"My HP is too low, I fainted, someone sent me to hospital, recovered " + HP_point
							+ " HP, but then my debt increased " + debt_increase + ":(");
			debt_text.setText(Integer.toString(debt));
		}

		// huge debt
		if (debt > 10000) {
			playSound("sound/unhappy.wav");
			Random random = new Random();
			int HP_decrease = 8 + random.nextInt(5);
			health -= HP_decrease;
			JOptionPane.showMessageDialog(new JFrame(),
					"I ve got too much debt, I was hitted by beater, I lost " + HP_decrease + " health :(");
			health_text.setText(Integer.toString(health));
			if (health <= 0)
				player_die();
		}

		// low reputation
		if (reputation <= 10) {
			playSound("sound/unhappy.wav");
			Random random = new Random();
			int HP_decrease = 18 + random.nextInt(5);
			health -= HP_decrease;
			JOptionPane.showMessageDialog(new JFrame(),
					"I ve sold too much inferior goods, I was hitted by citizens, I lost " + HP_decrease
							+ " health :(");
			health_text.setText(Integer.toString(health));
			if (health <= 0)
				player_die();
		}
	}

	private void event_A() {

		// random int to determine the event interval
		int rand_int = random.nextInt(126);

		// event 1: health -1
		if (rand_int >= 0 && rand_int <= 4) {
			playSound("sound/hack.wav");
			JOptionPane.showMessageDialog(new JFrame(),
					"The cold wave arrived!\n I ve been frozen like an iced sardine during my sleep in the shelter. (health -1)");
			health -= 1;
			health_text.setText(Integer.toString(health));
			if (health <= 0)
				player_die();
		}

		// event 2: health -1
		else if (rand_int >= 5 && rand_int <= 9) {
			playSound("sound/hack.wav");
			JOptionPane.showMessageDialog(new JFrame(),
					"Some strange wildlife prey on me that runs my gut out. (health -1)");
			health -= 1;
			health_text.setText(Integer.toString(health));
			if (health <= 0)
				player_die();
		}

		// event 3: health -1
		else if (rand_int >= 10 && rand_int <= 14) {
			playSound("sound/hack.wav");
			JOptionPane.showMessageDialog(new JFrame(),
					"Several drunkards knock me out for no reason. \nWhat a Crazy world... (health -1)");
			health -= 1;
			health_text.setText(Integer.toString(health));
			if (health <= 0)
				player_die();
		}

		// event 4: health -1
		else if (rand_int >= 15 && rand_int <= 19) {
			playSound("sound/hack.wav");
			JOptionPane.showMessageDialog(new JFrame(), "The radiation dust blows silently. (health -1)");
			health -= 1;
			health_text.setText(Integer.toString(health));
			if (health <= 0)
				player_die();
		}

		// event 5: health -1
		else if (rand_int >= 20 && rand_int <= 24) {
			playSound("sound/hack.wav");
			JOptionPane.showMessageDialog(new JFrame(),
					"I was scratched by wire mesh when running, it really hurts! (health -1)");
			health -= 1;
			health_text.setText(Integer.toString(health));
			if (health <= 0)
				player_die();
		}

		// event 6: health -1, reputation +1
		else if (rand_int >= 25 && rand_int <= 31) {
			playSound("sound/hack.wav");
			JOptionPane.showMessageDialog(new JFrame(),
					"I gave my foods to some poor children and starving for all night. \n(health -1, reputation +1)");
			health -= 1;
			health_text.setText(Integer.toString(health));
			if ((reputation + 1) <= 100)
				reputation += 1;
			else
				reputation = 100;
			reputation_text.setText(Integer.toString(reputation));
			if (health <= 0)
				player_die();
		}

		// event 7: health -3
		else if (rand_int >= 32 && rand_int <= 38) {
			playSound("sound/hack.wav");
			JOptionPane.showMessageDialog(new JFrame(),
					"Do not mess with goose (especially radioactive goose). (health -3)");
			health -= 3;
			health_text.setText(Integer.toString(health));
			if (health <= 0)
				player_die();
		}

		// event 8: health -3
		else if (rand_int >= 39 && rand_int <= 44) {
			playSound("sound/hack.wav");
			JOptionPane.showMessageDialog(new JFrame(),
					"I've got lost while scavenging, the wasteland makes me nauseous. (health -3)");
			health -= 3;
			health_text.setText(Integer.toString(health));
			if (health <= 0)
				player_die();
		}

		// event 9: health -3
		else if (rand_int >= 45 && rand_int <= 50) {
			playSound("sound/hack.wav");
			JOptionPane.showMessageDialog(new JFrame(),
					"Holy crap! I was nearly crushed to death by a collapsing building. (health -3)");
			health -= 3;
			health_text.setText(Integer.toString(health));
			if (health <= 0)
				player_die();
		}

		// event 10: health -5
		else if (rand_int >= 51 && rand_int <= 55) {
			playSound("sound/hack.wav");
			JOptionPane.showMessageDialog(new JFrame(),
					"I accidentally picked a bright metallic gravel and immediately threw it away. \nI can saw some red patches in my hand. (health -5)");
			health -= 5;
			health_text.setText(Integer.toString(health));
			if (health <= 0)
				player_die();
		}

		// event 11: (cash+deposit) +5%
		else if (rand_int >= 91 && rand_int <= 93) {
			playSound("sound/happy.wav");
			JOptionPane.showMessageDialog(new JFrame(),
					"You and your friends made some money by selling iodine tablets (cash+deposit +5%)");
			cash = (int) ((cash + deposit) * 1.05);
			cash_text.setText(Integer.toString(cash));
		}

		// event 12: cash +1000
		else if (rand_int >= 78 && rand_int <= 85) {
			playSound("sound/happy.wav");
			JOptionPane.showMessageDialog(new JFrame(),
					"I found a box of prewar coins in the ventilation duct of the underground corridor. (cash +1000)");
			cash += 1000;
			cash_text.setText(Integer.toString(cash));
		}

		// event 13: (cash+deposit) +10%
		else if (rand_int >= 94 && rand_int <= 95) {
			playSound("sound/happy.wav");
			JOptionPane.showMessageDialog(new JFrame(),
					"Your friend's investment got huge succeed. \n(I start thinking about why not invest more at the beginning...) \n(total money +10%)");
			cash = (int) ((cash + deposit) * 1.1);
			cash_text.setText(Integer.toString(cash));
		}

		// event 14: cash +10000
		else if (rand_int >= 86 && rand_int <= 90) {
			playSound("sound/happy.wav");
			JOptionPane.showMessageDialog(new JFrame(),
					"I made tremendous efforts to crack out a deformed safe. (cash +10000)");
			cash += 10000;
			cash_text.setText(Integer.toString(cash));
		}

		// event 15: cash -5%, reputation +3
		else if (rand_int >= 74 && rand_int <= 77) {
			playSound("sound/hack.wav");
			JOptionPane.showMessageDialog(new JFrame(),
					"I ve just donated some money to a weak old man. (cash -5%, reputation +3)");
			cash *= 0.95;
			cash_text.setText(Integer.toString(cash));
			if ((reputation + 3) <= 100)
				reputation += 3;
			else
				reputation = 100;
			reputation_text.setText(Integer.toString(reputation));
		}

		// event 16: cash -10%
		else if (rand_int >= 70 && rand_int <= 73) {
			playSound("sound/hack.wav");
			JOptionPane.showMessageDialog(new JFrame(),
					"Local gangsters kick the door in and rush into my space shout: \n'utilities, heating, living fee, breathing fee! you gotta pay what you own!' (cash -10%)");
			cash *= 0.9;
			cash_text.setText(Integer.toString(cash));
		}

		// event 17: cash -20%
		else if (rand_int >= 67 && rand_int <= 69) {
			playSound("sound/hack.wav");
			JOptionPane.showMessageDialog(new JFrame(),
					"a savage man knock into you yelled 'some tips for passing pal' (cash -20%)");
			cash *= 0.8;
			cash_text.setText(Integer.toString(cash));
		}

		// event 18: deposit -10%
		else if (rand_int >= 56 && rand_int <= 58) {
			playSound("sound/hack.wav");
			JOptionPane.showMessageDialog(new JFrame(),
					"The local tyrant impose a large sum of money from bank, \nin the name of 'Taxation' (deposit -10%)");
			deposit *= 0.9;
			deposit_text.setText(Integer.toString(deposit));
		}

		// event 19: deposit -5%
		else if (rand_int >= 59 && rand_int <= 62) {
			playSound("sound/hack.wav");
			JOptionPane.showMessageDialog(new JFrame(),
					"Your friend borrowed you some money for investment, \nthen he disappeared into thin air. (deposit -5%)");
			deposit *= 0.95;
			deposit_text.setText(Integer.toString(deposit));
		}

		// event 20: deposit -5%
		else if (rand_int >= 63 && rand_int <= 66) {
			playSound("sound/hack.wav");
			JOptionPane.showMessageDialog(new JFrame(),
					"The thievish-looking accountant said: \n'Hey fellow! there is some extra cost needed.' (deposit -5%)");
			deposit *= 0.95;
			deposit_text.setText(Integer.toString(deposit));
		}

		// event 21: get 10 Grains
		else if (rand_int >= 104 && rand_int <= 111) {
			String name = "Grains";
			int amount = 10;
			playSound("sound/happy.wav");
			JOptionPane.showMessageDialog(new JFrame(),
					"You dug out a box of flour in the ruins of a supermarket (get 10 Grains)");
			get_commodity(name, amount);
		}

		// event 22: get 10 Clothes, debt +4000
		else if (rand_int >= 96 && rand_int <= 99) {
			String name = "Clothes";
			int amount = 10;
			playSound("sound/hack.wav");
			JOptionPane.showMessageDialog(new JFrame(),
					"The local bully forced you to sell you the backlog of clothes at a high price \n(get 10 Cloths, debt +4000)");
			get_commodity(name, amount);
			debt += 4000;
			debt_text.setText(Integer.toString(debt));
		}

		// event 23: get 5 meats, debt +5000
		else if (rand_int >= 100 && rand_int <= 103) {
			String name = "Meats";
			int amount = 5;
			playSound("sound/hack.wav");
			JOptionPane.showMessageDialog(new JFrame(),
					"A bunch of outlaws maliciously 'invites' me to dinner and \nsells me a large box of prey in return of not becoming one of them. (get 5 meats, debt +5000)");
			get_commodity(name, amount);
			debt += 5000;
			debt_text.setText(Integer.toString(debt));
		}

		// event 24: get 5 drug
		else if (rand_int >= 112 && rand_int <= 117) {
			String name = "Drugs";
			int amount = 5;
			playSound("sound/hack.wav");
			JOptionPane.showMessageDialog(new JFrame(),
					"I ve found a couple of people pouring into a dark basement corner \nwith a few packs of white powder beside them. (get 5 drugs)");
			get_commodity(name, amount);
		}

		// event 25: get 2 pre-war car
		else if (rand_int >= 118 && rand_int <= 121) {
			String name = "Pre-war automobiles";
			int amount = 2;
			playSound("sound/happy.wav");
			JOptionPane.showMessageDialog(new JFrame(),
					"I found two excellent pre-war cars. Bravo! (get 2 pre-war cars)");
			get_commodity(name, amount);
		}

		// event 26: get 1 Computer
		else if (rand_int >= 122 && rand_int <= 125) {
			String name = "Computers";
			int amount = 1;
			playSound("sound/happy.wav");
			JOptionPane.showMessageDialog(new JFrame(),
					"Marvelous! I found a brand new laptop in the garbage \n(maybe someone else hid the it in). (get 1 computer)");
			get_commodity(name, amount);
		}
	}

	private void get_commodity(String name, int amount) {

		// if storehouse lack space, throw some items
		if ((current_storehouse_size + amount) > MAX_STOREHOUSE_SIZE) {
			int throwed = (current_storehouse_size + amount) - MAX_STOREHOUSE_SIZE;
			amount = MAX_STOREHOUSE_SIZE - current_storehouse_size;
			JOptionPane.showMessageDialog(new JFrame(), "Oooos! My storehouse lack space, so I have to throw " + throwed
					+ " of them, finally I ve got " + amount + " " + name);
		}

		// find corresponding commodity
		Commodity temp_commodity = null;
		for (int i = 0; i < total_commodity_list.size(); i++) {
			if (total_commodity_list.get(i).getName().equals(name)) {
				temp_commodity = total_commodity_list.get(i);
				break;
			}
		}
		String temp_name = temp_commodity.getName();
		int temp_price = temp_commodity.getPrice();

		// expand storehouse size
		current_storehouse_size += amount;
		store_size_label
				.setText(Integer.toString(current_storehouse_size) + "/" + Integer.toString(MAX_STOREHOUSE_SIZE));

		// if item not in storehouse
		if (!storehouse_commodity_list.contains(temp_commodity)) {
			if (amount > 0) {
				model3.addElement((model3.size() + 1) + ")  " + temp_name);
				model4.addElement(temp_price);
				model5.addElement(amount);
				storehouse_commodity_list.add(temp_commodity);
			}
		}

		// if item already in storehouse
		else {
			int index = storehouse_commodity_list.indexOf(temp_commodity);
			int old_amount = model5.getElementAt(index);
			int new_amount = old_amount + amount;
			model5.setElementAt(new_amount, index);
		}
	}

	private void event_B() {
		String item1 = "Water";
		String item2 = "Grains";
		String item3 = "Clothes";
		String item4 = "Meats";
		String item5 = "Drugs";
		String item6 = "Diesel";
		String item7 = "Explosives <IED>";
		String item8 = "Pre-war automobiles";
		String item9 = "Weapons";
		String item10 = "Computers";

		// randomly select 1 item from market list
		int index = random.nextInt(market_commodity_list.size());
		Commodity commodity = market_commodity_list.get(index);
		String item_name = commodity.getName();
		int item_price = commodity.getPrice();

		// select Water
		if (item_name.equals(item1)) {
			int rand = 1 + random.nextInt(2);
			switch (rand) {

			// price increase 4 times
			case (1):
				playSound("sound/happy.wav");
				JOptionPane.showMessageDialog(new JFrame(),
						"The locality is facing severe water shortage (Water price * 4), \nsell 50 Water you can get 1 reputation");
				int new_price1 = item_price * 4;
				commodity.setPrice(new_price1);
				Water_price_increase = 1;
				break;

			// price decrease 8 times
			case (2):
				JOptionPane.showMessageDialog(new JFrame(), "A new water source has been found! (Water price / 8)");
				int new_price2 = item_price / 8;
				commodity.setPrice(new_price2);
				break;
			}
		}

		// select Grains
		if (item_name.equals(item2)) {

			// price decrease 7 times
			JOptionPane.showMessageDialog(new JFrame(),
					"A new underground farm has been cultivated. (Grains price / 7)");
			int new_price1 = item_price / 7;
			commodity.setPrice(new_price1);
		}

		// select Clothes
		if (item_name.equals(item3)) {
			int rand = 1 + random.nextInt(4);
			switch (rand) {

			// price increase 4 times
			case (1):
				JOptionPane.showMessageDialog(new JFrame(),
						"An expedition is purchasing lots of warm clothes nearby. (Clothes price * 4)");
				int new_price1 = item_price * 4;
				commodity.setPrice(new_price1);
				break;

			// price increase 7 times
			case (2):
				JOptionPane.showMessageDialog(new JFrame(),
						"The Weavers who can't stand ruthless exploitation are on strike locally \n(Clothes price * 7)");
				int new_price2 = item_price * 7;
				commodity.setPrice(new_price2);
				break;

			// price decrease 7 times
			case (3):
				JOptionPane.showMessageDialog(new JFrame(),
						"The heat wave makes the underground extremely hot, in result nobody buys clothes at all. \n(Clothes price / 7)");
				int new_price3 = item_price / 7;
				commodity.setPrice(new_price3);
				break;
			}
		}

		// select Meats
		if (item_name.equals(item4)) {
			// price increase 3 times
			JOptionPane.showMessageDialog(new JFrame(),
					"With safe return of the expeditions, A grand banquet is being held in local. (Meats price * 3)");
			int new_price1 = item_price * 3;
			commodity.setPrice(new_price1);
		}

		// select Drugs
		if (item_name.equals(item5)) {
			int rand = 1 + random.nextInt(3);
			switch (rand) {

			// price increase 3 times
			case (1):
				JOptionPane.showMessageDialog(new JFrame(),
						"Depression pervades every part of local community. (Drugs price * 3)");
				int new_price1 = item_price * 3;
				commodity.setPrice(new_price1);
				break;

			// price increase 7 times
			case (2):
				JOptionPane.showMessageDialog(new JFrame(),
						"A strange bunch of guys are purchasing for 'ceremonial items' in the area. (Drugs price * 7)");
				int new_price2 = item_price * 7;
				commodity.setPrice(new_price2);
				break;
			}
		}

		// select Diesel
		if (item_name.equals(item6)) {

			// price increase 7 times
			JOptionPane.showMessageDialog(new JFrame(),
					"The cold wave is coming!\nDiesel is urgently needed for power generation and heating.\n(Diesel price * 7)");
			int new_price = item_price * 7;
			commodity.setPrice(new_price);
		}

		// select Explosives <IED>
		if (item_name.equals(item7)) {
			int rand = 1 + random.nextInt(2);
			switch (rand) {

			// price increase 4 times
			case (1):
				playSound("sound/happy.wav");
				JOptionPane.showMessageDialog(new JFrame(),
						"A revolution is in the making! The mass was hoarding IED. (Explosives <IED> price * 4), \nsell 5 IED you can get 1 reputation");
				int new_price1 = item_price * 4;
				commodity.setPrice(new_price1);
				IED_price_increase = 1;
				break;

			// price increase 8 times
			case (2):
				JOptionPane.showMessageDialog(new JFrame(),
						"Local gangs hint that you’d better mess something up recently right before the revolution. \n(Explosives <IED> price * 8)");
				int new_price2 = item_price * 8;
				commodity.setPrice(new_price2);
				break;
			}
		}

		// select Pre-war automobiles
		if (item_name.equals(item8)) {
			int rand = 1 + random.nextInt(3);
			switch (rand) {

			// price increase 3 times
			case (1):
				JOptionPane.showMessageDialog(new JFrame(),
						"'There must be people who have survived in other regions!'\nExpeditions have been buying large quantities of vehicles locally for exploration. (Pre-war automobiles price * 3)");
				int new_price1 = item_price * 6;
				commodity.setPrice(new_price1);
				break;
			}
		}

		// select Weapons
		if (item_name.equals(item9)) {
			int rand = 1 + random.nextInt(2);
			switch (rand) {

			// price increase 5 times
			case (1):
				playSound("sound/happy.wav");
				JOptionPane.showMessageDialog(new JFrame(),
						"Weapons are badly needed! \nRevolution army need more weapons. (Weapons price * 5), \nsell 1 weapon you can get 1 reputation");
				int new_price1 = item_price * 5;
				commodity.setPrice(new_price1);
				weapon_price_increase = 1;
				break;

			// price increase 9 times
			case (2):
				JOptionPane.showMessageDialog(new JFrame(),
						"Tyrant are buying weapons at high prices to suppress revolution. (Weapons price * 9)");
				int new_price2 = item_price * 9;
				commodity.setPrice(new_price2);
				break;
			}
		}

		// select Computers
		if (item_name.equals(item10)) {
			int rand = 1 + random.nextInt(3);
			switch (rand) {

			// price increaes 5 times
			case (1):
				JOptionPane.showMessageDialog(new JFrame(),
						"We are recovering from nuclear war. \nlocal scientific researchers are in urgent need of computers. \n(Computers price * 3)");
				int new_price1 = item_price * 5;
				commodity.setPrice(new_price1);
				break;
			}
		}
		// update market commodity list
		model1.clear();
		model2.clear();
		for (int i = 0; i < market_commodity_list.size(); i++) {
			model1.addElement((i + 1) + ")  " + market_commodity_list.get(i).getName());
			model2.addElement(market_commodity_list.get(i).getPrice());
		}
	}

	private void event_B_increase_reputation(int amount, String temp_name, String event) {
		playSound("sound/happy.wav");

		// increase reputation according to event
		int reputation_increase = 0;
		if (event.equals("water shortage"))
			reputation_increase = amount % 50;
		else if (event.equals("revolution"))
			reputation_increase = amount % 5;
		else if (event.equals("riot"))
			reputation_increase = amount;

		// record increased reputation caused by event B
		current_B_reputation += reputation_increase;

		JOptionPane.showMessageDialog(new JFrame(), "Due to " + event + ", I sold " + amount + " " + temp_name
				+ " and got " + reputation_increase + " reputation!");

		// if over today limit, reduce it
		if (current_B_reputation >= MAX_B_REPUTATION) {
			JOptionPane.showMessageDialog(new JFrame(), "I ve got enought reputation in this area!");
			reputation_increase -= (current_B_reputation - MAX_B_REPUTATION);
		}

		// update reputation
		reputation += reputation_increase;
		reputation_text.setText(Integer.toString(reputation));
	}

	private void sold_inferior_goods(int min, int plus, String name) {
		playSound("sound/unhappy.wav");
		int reduce = min + random.nextInt(plus);
		JOptionPane.showMessageDialog(new JFrame(), "I just sold " + name + ", so I lost " + reduce + " reputation");
		reputation -= reduce;
		reputation_text.setText(Integer.toString(reputation));
	}

	private void randomEvent() {
		double prob_A = 0.5;
		double prob_B = 0.3;

		// event A happen
		while (prob_A > 0) {
			if (new Random().nextDouble() <= prob_A)
				event_A();
			prob_A -= 0.2;
		}

		// event B happen
		if (new Random().nextDouble() <= prob_B)
			event_B();
	}

	// Coordinate JList
	private void coordinate_jlist() {
		List1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				List2.setSelectedIndex(List1.getSelectedIndex());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				List2.setSelectedIndex(List1.getSelectedIndex());
			}

			@Override
			public void mousePressed(MouseEvent e) {
				List2.setSelectedIndex(List1.getSelectedIndex());
			}
		});

		// coordinate JList1 & JList2
		List2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				List1.setSelectedIndex(List2.getSelectedIndex());
			}

			@Override
			public void mousePressed(MouseEvent e) {
				List1.setSelectedIndex(List2.getSelectedIndex());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				List1.setSelectedIndex(List2.getSelectedIndex());
			}
		});

		// coordinate JList3 & JList4 & JList5
		List3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				List4.setSelectedIndex(List3.getSelectedIndex());
				List5.setSelectedIndex(List3.getSelectedIndex());
			}

			@Override
			public void mousePressed(MouseEvent e) {
				List4.setSelectedIndex(List3.getSelectedIndex());
				List5.setSelectedIndex(List3.getSelectedIndex());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				List4.setSelectedIndex(List3.getSelectedIndex());
				List5.setSelectedIndex(List3.getSelectedIndex());
			}
		});

		// coordinate JList3 & JList4 & JList5
		List4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				List3.setSelectedIndex(List4.getSelectedIndex());
				List5.setSelectedIndex(List4.getSelectedIndex());
			}

			@Override
			public void mousePressed(MouseEvent e) {
				List3.setSelectedIndex(List4.getSelectedIndex());
				List5.setSelectedIndex(List4.getSelectedIndex());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				List3.setSelectedIndex(List4.getSelectedIndex());
				List5.setSelectedIndex(List4.getSelectedIndex());
			}
		});

		// coordinate JList3 & JList4 & JList5
		List5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				List3.setSelectedIndex(List5.getSelectedIndex());
				List4.setSelectedIndex(List5.getSelectedIndex());
			}

			@Override
			public void mousePressed(MouseEvent e) {
				List3.setSelectedIndex(List5.getSelectedIndex());
				List4.setSelectedIndex(List5.getSelectedIndex());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				List3.setSelectedIndex(List5.getSelectedIndex());
				List4.setSelectedIndex(List5.getSelectedIndex());
			}
		});
	}

	private void setup_mainframe() {
		frmLiveInOntario = new JFrame();
		ImageIcon img = new ImageIcon("icon/ontario.jpg");
		frmLiveInOntario.setIconImage(img.getImage());
		frmLiveInOntario.setResizable(false);
		frmLiveInOntario.getContentPane().setBackground(UIManager.getColor("Button.background"));
		frmLiveInOntario.setTitle("The Underground Survivor " + "(" + current_day + "/" + TOTAL_DAY + " days)");
		frmLiveInOntario.setBounds(300, 200, 771, 577);
		frmLiveInOntario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLiveInOntario.getContentPane().setLayout(null);
	}

	private void setup_status_panel() {
		// Status panel
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(192, 192, 192)));
		panel_2.setBackground(UIManager.getColor("Button.background"));
		panel_2.setBounds(49, 313, 200, 147);
		frmLiveInOntario.getContentPane().add(panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 72, 72, 0 };
		gbl_panel_2.rowHeights = new int[] { 27, 27, 27, 27, 0, 0 };
		gbl_panel_2.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		// cash label
		JLabel lblDebt = new JLabel("Cash :");
		lblDebt.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDebt.setFont(new Font("Times New Roman", Font.BOLD, 12));
		GridBagConstraints gbc_lblDebt = new GridBagConstraints();
		gbc_lblDebt.fill = GridBagConstraints.BOTH;
		gbc_lblDebt.insets = new Insets(0, 0, 5, 5);
		gbc_lblDebt.gridx = 0;
		gbc_lblDebt.gridy = 0;
		panel_2.add(lblDebt, gbc_lblDebt);

		// cash textfield
		cash_text = new JTextField();
		cash_text.setText(Integer.toString(cash));
		cash_text.setHighlighter(null);
		cash_text.setHorizontalAlignment(SwingConstants.CENTER);
		cash_text.setForeground(new Color(0, 255, 0));
		cash_text.setFont(new Font("SimSun", Font.BOLD, 18));
		cash_text.setEditable(false);
		cash_text.setColumns(10);
		cash_text.setBackground(Color.BLACK);

		// wrap cash textfield to status panel
		GridBagConstraints gbc_cash_text = new GridBagConstraints();
		gbc_cash_text.insets = new Insets(0, 0, 5, 0);
		gbc_cash_text.fill = GridBagConstraints.BOTH;
		gbc_cash_text.gridx = 1;
		gbc_cash_text.gridy = 0;
		panel_2.add(cash_text, gbc_cash_text);

		// deposit label
		JLabel lblDeposit = new JLabel("Deposit :");
		lblDeposit.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDeposit.setFont(new Font("Times New Roman", Font.BOLD, 12));
		GridBagConstraints gbc_lblDeposit = new GridBagConstraints();
		gbc_lblDeposit.insets = new Insets(0, 0, 5, 5);
		gbc_lblDeposit.anchor = GridBagConstraints.EAST;
		gbc_lblDeposit.gridx = 0;
		gbc_lblDeposit.gridy = 1;
		panel_2.add(lblDeposit, gbc_lblDeposit);

		// deposit textfield
		deposit_text = new JTextField();
		deposit_text.setText(Integer.toString(deposit));
		deposit_text.setHorizontalAlignment(SwingConstants.CENTER);
		deposit_text.setForeground(Color.GREEN);
		deposit_text.setFont(new Font("SimSun", Font.BOLD, 18));
		deposit_text.setEditable(false);
		deposit_text.setHighlighter(null);
		deposit_text.setColumns(10);
		deposit_text.setBackground(Color.BLACK);

		// wrap deposit textfield to status panel
		GridBagConstraints gbc_deposit_text = new GridBagConstraints();
		gbc_deposit_text.insets = new Insets(0, 0, 5, 0);
		gbc_deposit_text.fill = GridBagConstraints.HORIZONTAL;
		gbc_deposit_text.gridx = 1;
		gbc_deposit_text.gridy = 1;
		panel_2.add(deposit_text, gbc_deposit_text);

		// debt label
		JLabel lblNewLabel_6 = new JLabel("Debt :");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 12));
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 2;
		panel_2.add(lblNewLabel_6, gbc_lblNewLabel_6);

		// debt textfield
		debt_text = new JTextField();
		debt_text.setEditable(false);
		debt_text.setFont(new Font("SimSun", Font.BOLD, 18));
		debt_text.setHorizontalAlignment(SwingConstants.CENTER);
		debt_text.setText(Integer.toString(debt));
		debt_text.setHighlighter(null);
		debt_text.setForeground(new Color(255, 0, 0));
		debt_text.setColumns(10);
		debt_text.setBackground(Color.BLACK);

		// wrap debt textfield to status panel
		GridBagConstraints gbc_debt_text = new GridBagConstraints();
		gbc_debt_text.insets = new Insets(0, 0, 5, 0);
		gbc_debt_text.fill = GridBagConstraints.BOTH;
		gbc_debt_text.gridx = 1;
		gbc_debt_text.gridy = 2;
		panel_2.add(debt_text, gbc_debt_text);

		// health label
		JLabel lblNewLabel_5 = new JLabel("Health :");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 12));
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 3;
		panel_2.add(lblNewLabel_5, gbc_lblNewLabel_5);

		// health textfield
		health_text = new JTextField();
		health_text.setEditable(false);
		health_text.setFont(new Font("SimSun", Font.BOLD, 18));
		health_text.setHorizontalAlignment(SwingConstants.CENTER);
		health_text.setText(Integer.toString(health));
		health_text.setHighlighter(null);
		health_text.setForeground(Color.GREEN);
		health_text.setColumns(10);
		health_text.setBackground(Color.BLACK);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 3;
		panel_2.add(health_text, gbc_textField);

		// reputation label
		JLabel lblReputation = new JLabel("Reputation :");
		lblReputation.setHorizontalAlignment(SwingConstants.RIGHT);
		lblReputation.setFont(new Font("Times New Roman", Font.BOLD, 12));
		GridBagConstraints gbc_lblReputation = new GridBagConstraints();
		gbc_lblReputation.fill = GridBagConstraints.BOTH;
		gbc_lblReputation.insets = new Insets(0, 0, 0, 5);
		gbc_lblReputation.gridx = 0;
		gbc_lblReputation.gridy = 4;
		panel_2.add(lblReputation, gbc_lblReputation);

		// reputation textfield
		reputation_text = new JTextField();
		reputation_text.setEditable(false);
		reputation_text.setFont(new Font("SimSun", Font.BOLD, 18));
		reputation_text.setHorizontalAlignment(SwingConstants.CENTER);
		reputation_text.setText(Integer.toString(reputation));
		reputation_text.setHighlighter(null);
		reputation_text.setForeground(Color.GREEN);
		reputation_text.setColumns(10);
		reputation_text.setBackground(Color.BLACK);

		// wrap reputation textfield to the status panel
		GridBagConstraints gbc_reputation_text = new GridBagConstraints();
		gbc_reputation_text.fill = GridBagConstraints.BOTH;
		gbc_reputation_text.gridx = 1;
		gbc_reputation_text.gridy = 4;
		panel_2.add(reputation_text, gbc_reputation_text);

		// status label
		txtYourStatus = new JLabel();
		txtYourStatus.setFont(new Font("SimSun", Font.BOLD, 16));
		txtYourStatus.setText("Your status");
		txtYourStatus.setBounds(90, 285, 105, 23);
		frmLiveInOntario.getContentPane().add(txtYourStatus);
	}

	private void setup_places_panel() {
		// the places panel
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(248, 248, 255));
		panel_3.setBounds(317, 316, 100, 144);
		frmLiveInOntario.getContentPane().add(panel_3);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));

		// bank button
		JButton btnNewButton = new JButton("Bank");
		btnNewButton.addActionListener(new bank_listener());

		// add bank button to places panel
		panel_3.add(btnNewButton);
		btnNewButton.setIcon(new ImageIcon("icon/bank.jpg"));
		btnNewButton.setMargin(new Insets(0, 0, 0, 0));
		btnNewButton.setBackground(null);

		// hospital button
		JButton btnHospital = new JButton("Hospital");

		// hospital button listener
		btnHospital.addActionListener(new hospital_listener());

		// add hospital to places panel
		panel_3.add(btnHospital);
		btnHospital.setIcon(new ImageIcon("icon/hospital.jpg"));
		btnHospital.setMargin(new Insets(0, 0, 0, 0));
		btnHospital.setBackground(null);

		// post office button
		JButton btnPostOffice = new JButton("Post Office");

		// post office button listener
		btnPostOffice.addActionListener(new postoffice_listener());

		// add post office button to places panel
		panel_3.add(btnPostOffice);
		btnPostOffice.setIcon(new ImageIcon("icon/post_office.jpg"));
		btnPostOffice.setBackground(null);

		// house button
		JButton btnHouse = new JButton("House");

		// house button listener
		btnHouse.addActionListener(new house_listener());

		// add house button to the places pabel
		panel_3.add(btnHouse);
		btnHouse.setIcon(new ImageIcon("icon/house.jpg"));
		btnHouse.setBackground(null);

		// bank button color change when mouse enter/exit
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton.setIcon(null);
				btnNewButton.setBackground(Color.orange);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton.setBackground(null);
				btnNewButton.setIcon(new ImageIcon("icon/bank.jpg"));
				btnNewButton.setMargin(new Insets(0, 0, 0, 0));
			}
		});

		// post office color change when mouse enter/exit button
		btnPostOffice.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnPostOffice.setIcon(null);
				btnPostOffice.setBackground(Color.GREEN);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnPostOffice.setBackground(null);
				btnPostOffice.setIcon(new ImageIcon("icon/post_office.jpg"));
			}
		});

		// house button color change when mouse enter/exit
		btnHouse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnHouse.setIcon(null);
				btnHouse.setBackground(Color.MAGENTA);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnHouse.setBackground(null);
				btnHouse.setIcon(new ImageIcon("icon/house.jpg"));
			}
		});

		// hospital button color change when mouse enter/exit
		btnHospital.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnHospital.setIcon(null);
				btnHospital.setBackground(Color.CYAN);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnHospital.setBackground(null);
				btnHospital.setIcon(new ImageIcon("icon/hospital.jpg"));
			}
		});

		// places label
		txtPlaces = new JLabel();
		txtPlaces.setFont(new Font("SimSun", Font.BOLD, 16));
		txtPlaces.setText("Places");
		txtPlaces.setBounds(337, 285, 62, 23);
		frmLiveInOntario.getContentPane().add(txtPlaces);
	}

	private void setup_map_panel() {
		// map label
		txtCityMap = new JLabel();
		txtCityMap.setFont(new Font("SimSun", Font.BOLD, 16));
		txtCityMap.setBounds(524, 272, 160, 23);
		frmLiveInOntario.getContentPane().add(txtCityMap);

		// map panel
		map_panel.setBounds(448, 296, 294, 199);
		frmLiveInOntario.getContentPane().add(map_panel);
		GridBagLayout gbl_map_panel = new GridBagLayout();
		gbl_map_panel.columnWidths = new int[] { 98, 98, 98, 0 };
		gbl_map_panel.rowHeights = new int[] { 66, 66, 66, 0 };
		gbl_map_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_map_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		map_panel.setLayout(gbl_map_panel);

		// Switch button and its listener
		btn_switch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("sound/switch.wav");
				refresh_map_panel();
			}
		});

		// place 1-16 button listener
		btn1.addActionListener(new place_listener());
		btn1.setBackground(Color.ORANGE);
		btn2.addActionListener(new place_listener());
		btn2.setBackground(Color.ORANGE);
		btn3.addActionListener(new place_listener());
		btn3.setBackground(Color.ORANGE);
		btn4.addActionListener(new place_listener());
		btn4.setBackground(Color.ORANGE);
		btn5.addActionListener(new place_listener());
		btn5.setBackground(Color.ORANGE);
		btn6.addActionListener(new place_listener());
		btn6.setBackground(Color.ORANGE);
		btn7.addActionListener(new place_listener());
		btn7.setBackground(Color.ORANGE);
		btn8.addActionListener(new place_listener());
		btn8.setBackground(Color.ORANGE);
		btn09.addActionListener(new place_listener());
		btn09.setBackground(new Color(100, 240, 240));
		btn10.addActionListener(new place_listener());
		btn10.setBackground(new Color(100, 240, 240));
		btn11.addActionListener(new place_listener());
		btn11.setBackground(new Color(100, 240, 240));
		btn12.addActionListener(new place_listener());
		btn12.setBackground(new Color(100, 240, 240));
		btn13.addActionListener(new place_listener());
		btn13.setBackground(new Color(100, 240, 240));
		btn14.addActionListener(new place_listener());
		btn14.setBackground(new Color(100, 240, 240));
		btn15.addActionListener(new place_listener());
		btn15.setBackground(new Color(100, 240, 240));
		btn16.addActionListener(new place_listener());
		btn16.setBackground(new Color(100, 240, 240));
	}

	private void setup_market_jlist() {
		// market commodity name scroller
		JScrollPane Scroller1 = new JScrollPane(List1);
		Scroller1.setBounds(29, 90, 154, 170);
		List1.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		frmLiveInOntario.getContentPane().add(Scroller1);

		// market commodity price scroller
		JScrollPane Scroller2 = new JScrollPane(List2);
		Scroller2.setBounds(182, 90, 98, 170);
		List2.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		frmLiveInOntario.getContentPane().add(Scroller2);

		// coordinate market scroller
		BoundedRangeModel model = Scroller1.getVerticalScrollBar().getModel();
		Scroller2.getVerticalScrollBar().setModel(model);

		// market commodity button
		JButton btnCommodity = new JButton("Commodity");
		btnCommodity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Comparator<Commodity> compareByName = (Commodity o1, Commodity o2) -> o1.getName()
						.compareTo(o2.getName());

				if (sort_status == 0) {
					sort_status = 1;
					Collections.sort(market_commodity_list, compareByName);
				}

				else {
					sort_status = 0;
					Collections.sort(market_commodity_list, compareByName.reversed());
				}

				// update the ListModel of market list
				model1.clear();
				model2.clear();
				for (int i = 0; i < market_commodity_list.size(); i++) {
					model1.addElement((i + 1) + ")  " + market_commodity_list.get(i).getName());
					model2.addElement(market_commodity_list.get(i).getPrice());
				}
			}
		});
		btnCommodity.setBounds(29, 68, 153, 23);
		btnCommodity.setBackground(Color.LIGHT_GRAY);
		frmLiveInOntario.getContentPane().add(btnCommodity);

		// market price button
		JButton button_3 = new JButton("Price");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Comparator<Commodity> compareByPrice = (Commodity o1, Commodity o2) -> o1.getPrice()
						.compareTo(o2.getPrice());

				if (sort_status == 0) {
					sort_status = 1;
					Collections.sort(market_commodity_list, compareByPrice);
				}

				else {
					sort_status = 0;
					Collections.sort(market_commodity_list, compareByPrice.reversed());
				}

				// update the ListModel of market list
				model1.clear();
				model2.clear();
				for (int i = 0; i < market_commodity_list.size(); i++) {
					model1.addElement((i + 1) + ")  " + market_commodity_list.get(i).getName());
					model2.addElement(market_commodity_list.get(i).getPrice());
				}
			}
		});
		button_3.setBounds(181, 68, 98, 23);
		button_3.setBackground(Color.LIGHT_GRAY);
		frmLiveInOntario.getContentPane().add(button_3);

		// market label
		txtBlackMarket = new JLabel();
		txtBlackMarket.setFont(new Font("SimSun", Font.BOLD, 16));
		btn1.setSelected(true);
		btn_record = btn1;
		txtBlackMarket.setText("Weird Market (" + btn1.getText() + ")");
		txtBlackMarket.setBounds(49, 35, 220, 23);
		frmLiveInOntario.getContentPane().add(txtBlackMarket);
	}

	private void setup_day_label() {
		// Day label
		lblDay.setForeground(new Color(0, 245, 0));
		lblDay.setFont(new Font("SimSun", Font.BOLD, 22));
		lblDay.setHorizontalAlignment(SwingConstants.CENTER);
		lblDay.setBounds(297, 28, 132, 33);
		frmLiveInOntario.getContentPane().add(lblDay);
	}

	private void setup_buy_in() {
		// buy in label
		JLabel lblNewLabel_1 = new JLabel("Buy in");
		lblNewLabel_1.setFont(new Font("SimSun", Font.BOLD, 13));
		lblNewLabel_1.setBounds(334, 92, 54, 15);
		frmLiveInOntario.getContentPane().add(lblNewLabel_1);

		// buy in button
		JButton btnNewButton_6 = new JButton("=>");

		// buy in button listener
		btnNewButton_6.addActionListener(new buy_in_listener());

		// add buy in button to the basic GUI JFrame
		btnNewButton_6.setBounds(317, 111, 93, 23);
		frmLiveInOntario.getContentPane().add(btnNewButton_6);
	}

	private void setup_sell_out() {
		// sell out label
		JLabel lblNewLabel_2 = new JLabel("Sell out");
		lblNewLabel_2.setFont(new Font("SimSun", Font.BOLD, 13));
		lblNewLabel_2.setBounds(334, 144, 65, 15);
		frmLiveInOntario.getContentPane().add(lblNewLabel_2);

		// sell out button
		JButton btnNewButton_7 = new JButton("<=");
		btnNewButton_7.addActionListener(new sell_out_listener());
		btnNewButton_7.setBounds(317, 163, 93, 23);
		frmLiveInOntario.getContentPane().add(btnNewButton_7);
	}

	private void setup_discard() {
		// discard label
		JLabel lblDiscard = new JLabel("Discard");
		lblDiscard.setFont(new Font("SimSun", Font.BOLD, 13));
		lblDiscard.setBounds(334, 196, 65, 15);
		frmLiveInOntario.getContentPane().add(lblDiscard);

		// discard button
		JButton button_2 = new JButton("<<<<<<<<");
		button_2.addActionListener(new discard_listener());
		button_2.setBounds(317, 215, 93, 23);
		frmLiveInOntario.getContentPane().add(button_2);
	}

	private void setup_storehouse() {
		// storehouse capacity label
		store_size_label.setFont(new Font("SimSun", Font.BOLD, 22));
		store_size_label
				.setText(Integer.toString(current_storehouse_size) + "/" + Integer.toString(MAX_STOREHOUSE_SIZE));
		store_size_label.setBounds(649, 31, 110, 27);
		frmLiveInOntario.getContentPane().add(store_size_label);

		// storehouse label
		txtYourStorehouse = new JLabel();
		txtYourStorehouse.setFont(new Font("SimSun", Font.BOLD, 16));
		txtYourStorehouse.setText("My Storehouse");
		txtYourStorehouse.setBounds(514, 35, 124, 23);
		frmLiveInOntario.getContentPane().add(txtYourStorehouse);

		// storehouse commodity name scroller
		JScrollPane Scroller3 = new JScrollPane(List3);
		Scroller3.setBounds(448, 90, 132, 170);
		List3.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		frmLiveInOntario.getContentPane().add(Scroller3);

		// storehouse commodity price scroller
		JScrollPane Scroller4 = new JScrollPane(List4);
		Scroller4.setBounds(579, 90, 71, 170);
		List4.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		frmLiveInOntario.getContentPane().add(Scroller4);

		// storehouse commodity amount scroller
		JScrollPane Scroller5 = new JScrollPane(List5);
		Scroller5.setBounds(649, 90, 85, 170);
		List5.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		frmLiveInOntario.getContentPane().add(Scroller5);

		// coordinate storehouse scroller
		BoundedRangeModel model1 = Scroller3.getVerticalScrollBar().getModel();
		Scroller4.getVerticalScrollBar().setModel(model1);
		BoundedRangeModel model2 = Scroller4.getVerticalScrollBar().getModel();
		Scroller5.getVerticalScrollBar().setModel(model2);

		// storehouse commodity button
		JButton button = new JButton("Commodity");
		button.setBounds(448, 68, 131, 23);
		button.setBackground(Color.LIGHT_GRAY);
		frmLiveInOntario.getContentPane().add(button);

		// storehouse price button
		JButton button_1 = new JButton("Value");
		button_1.setBounds(579, 68, 70, 23);
		button_1.setBackground(Color.LIGHT_GRAY);
		frmLiveInOntario.getContentPane().add(button_1);

		// storehouse amount button
		JButton btnAmount = new JButton("Amount");
		btnAmount.setBounds(649, 68, 84, 23);
		btnAmount.setBackground(Color.LIGHT_GRAY);
		frmLiveInOntario.getContentPane().add(btnAmount);
	}

	private void setup_news() {
		// setup news contents
		String space = "          ";
		String news0 = "                                      ";
		String news1 = "Nuclear radiation in Northwest Ontario has resulted in several deaths and cancer 06:35:42";
		String news2 = "Like Canada, the civilizations of many countries have been severely damaged and the whole human civilization has retreated for hundreds of years 08:02:56";
		String news3 = "Traffic has been severely damaged by the nuclear war, and communication between cities has been greatly hampered 08:31:44";
		String news4 = "Many urban volunteers are actively participating in urban redesign and construction 10:58:33";
		String news5 = "Ontario has entered a foreign force, the local gangs clash with foreign gangs 11:49:05";
		String news6 = "Nuclear radiation has led to the emergence of newly evolved biochemical viruses in some regions of Ontario 12:30:31";
		String news7 = "Toronto has recently emerged as a new generation of outstanding businessmen 23:33:35";
		String news8 = "The government has sent some staff to give aid and care to the newly-reborn victims who have just recovered from the ruins 17:23:54";

		// news text
		JTextArea news = new JTextArea();
		news.setFont(new Font("Monospaced", Font.PLAIN, 14));
		news.setText(news0 + news0 + news0 + news1 + space + news2 + space + news3 + space + news4);
		news.setBounds(-1, 502, 10000, 22);
		news.setEditable(false);
		news.setHighlighter(null);
		news.setBackground(new Color(240, 255, 255));
		frmLiveInOntario.getContentPane().add(news);

		// news slider
		Timer timer = new Timer(16, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				news.setBounds(--news_slider, 502, 5000, 22);
				if (news_slider < -4200) {
					news_slider = -1;
					if (slide_mode == 1) {
						slide_mode = 2;
						news.setText(news0 + news0 + space + space + space + news5 + space + news6 + space + news7
								+ space + news8);
					} else if (slide_mode == 2) {
						slide_mode = 1;
						news.setText(news0 + news0 + news0 + news1 + space + news2 + space + news3 + space + news4);
					}
				}
			}
		});
		timer.start();

		// stop/restart sliding & sound when mouse enter/exit
		news.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				int rand = random.nextInt(20);
				if (rand == 0)
					playSound("news/news1.wav");
				else if (rand == 1)
					playSound("news/news2.wav");
				else if (rand == 2)
					playSound("news/news3.wav");
				else if (rand == 4)
					playSound("news/news5.wav");
				else if (rand == 5)
					playSound("news/news6.wav");
				else if (rand == 6)
					playSound("news/news7.wav");
				else if (rand == 7)
					playSound("news/news8.wav");
				else if (rand == 8)
					playSound("news/news9.wav");
				timer.stop();
			}

			public void mouseExited(MouseEvent e) {
				timer.start();
				clip.stop();
			}
		});
	}

	private void setup_top_menu() {
		// the top menu bar
		JMenuBar menuBar = new JMenuBar();
		frmLiveInOntario.setJMenuBar(menuBar);

		// system menu
		JMenu mnSystem = new JMenu("System");
		menuBar.add(mnSystem);

		// new game menu item
		JMenuItem mntmNewMenuItem = new JMenuItem("New Game");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int ORIGIN_STATUS = 999;
				int option = ORIGIN_STATUS;

				// if user is on the process of game
				if ((cash != 2000) || (deposit != 0) || (debt != 5500) || (health != 100) || (reputation != 50)) {
					option = JOptionPane.showConfirmDialog(new JFrame(),
							"You are on the process of the game, are you sure to restart?", "Wanna try again?",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				}

				// ready to restart
				if (option == JOptionPane.YES_OPTION || option == ORIGIN_STATUS)
					restart_game();
			}
		});
		mnSystem.add(mntmNewMenuItem);

		// exit menu item
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int ORIGIN_STATUS = 999;
				int option = ORIGIN_STATUS;

				// if user is on the process of game
				if ((cash != 2000) || (deposit != 0) || (debt != 5500) || (health != 100) || (reputation != 50)) {
					option = JOptionPane.showConfirmDialog(new JFrame(),
							"You are on the process of the game, are you sure to exit?", "Wanna leave?",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				}

				// ready to exit
				if (option == JOptionPane.YES_OPTION || option == ORIGIN_STATUS) {
					playSound("sound/50day.wav");
					JOptionPane.showMessageDialog(new JFrame(), "I am busy working! Bye bye funny game!");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
					}
					frmLiveInOntario.dispose();
					try {
						Thread.sleep(2700);
					} catch (InterruptedException e1) {
					}
					System.exit(0);
				}
			}
		});

		JMenuItem mntmRanking = new JMenuItem("Ranking");
		mntmRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game_ranking();
			}
		});
		mnSystem.add(mntmRanking);
		// setting menu
		JMenu mnSetting = new JMenu("Setting");
		mnSystem.add(mnSetting);

		// sound selection button
		JRadioButton rdbtnSound = new JRadioButton("Sound");
		rdbtnSound.setSelected(true);
		rdbtnSound.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
					sound_status = 1;
				else if (e.getStateChange() == ItemEvent.DESELECTED)
					sound_status = 0;
			}
		});

		// hacker selection button
		JRadioButton rdbtnBankHacker = new JRadioButton("Bank Hacker");
		rdbtnBankHacker.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					hacker_status = 1;
					playSound("sound/hack.wav");
				}

				else if (e.getStateChange() == ItemEvent.DESELECTED)
					hacker_status = 0;
			}
		});
		mnSetting.add(rdbtnBankHacker);
		mnSetting.add(rdbtnSound);
		mnSystem.add(mntmExit);

		// places menu
		JMenu mnMainPlace = new JMenu("Places");
		menuBar.add(mnMainPlace);

		// Bank menu item
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Bank");
		mntmNewMenuItem_1.addActionListener(new bank_listener());
		mnMainPlace.add(mntmNewMenuItem_1);

		// Hospital menu item
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Hospital");
		mntmNewMenuItem_2.addActionListener(new hospital_listener());
		mnMainPlace.add(mntmNewMenuItem_2);

		// Post Office menu item
		JMenuItem mntmPostOffice = new JMenuItem("Post Office");
		mntmPostOffice.addActionListener(new postoffice_listener());
		mnMainPlace.add(mntmPostOffice);

		// House menu item
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("House");
		mntmNewMenuItem_3.addActionListener(new house_listener());
		mnMainPlace.add(mntmNewMenuItem_3);

		// cheat menu
		JMenu mnCheat = new JMenu("Cheat");
		menuBar.add(mnCheat);

		// add 1000 cash
		JMenuItem mntmAddCash = new JMenuItem("Cash +1000");
		mntmAddCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cash += 1000;
				cash_text.setText(Integer.toString(cash));
				playSound("sound/money.wav");
			}
		});
		mnCheat.add(mntmAddCash);

		// Add 1000 deposit
		JMenuItem mntmAddDeposit = new JMenuItem("Deposit +1000");
		mntmAddDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deposit += 1000;
				deposit_text.setText(Integer.toString(deposit));
			}
		});
		mnCheat.add(mntmAddDeposit);

		// Pay 1000 debt
		JMenuItem mntmPayDebt = new JMenuItem("Debt -1000");
		mntmPayDebt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((debt - 1000) >= 0)
					debt -= 1000;
				else {
					debt = 0;
					debt_text.setText(Integer.toString(debt));
					playSound("sound/happy.wav");
					JOptionPane.showMessageDialog(new JFrame(), "Honey, you are out of debt! Now you are free!");
				}
				debt_text.setText(Integer.toString(debt));
			}
		});

		// Add 10000 cash
		JMenuItem mntmAddCash_1 = new JMenuItem("Cash +10000");
		mntmAddCash_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cash += 10000;
				cash_text.setText(Integer.toString(cash));
				playSound("sound/money.wav");
			}
		});
		mnCheat.add(mntmAddCash_1);

		// Add 10000 deposit
		JMenuItem mntmAddDeposit_1 = new JMenuItem("Deposit +10000");
		mntmAddDeposit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deposit += 10000;
				deposit_text.setText(Integer.toString(deposit));
			}
		});
		mnCheat.add(mntmAddDeposit_1);
		mnCheat.add(mntmPayDebt);

		// Recovor health
		JMenuItem mntmRecovorHealth = new JMenuItem("Health -> 100");
		mntmRecovorHealth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				health = 100;
				health_text.setText(Integer.toString(health));
			}
		});
		mnCheat.add(mntmRecovorHealth);

		// Recover reputation
		JMenuItem mntmRecoverReputation = new JMenuItem("Reputation -> 100");
		mntmRecoverReputation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reputation = 100;
				reputation_text.setText(Integer.toString(reputation));
			}
		});
		mnCheat.add(mntmRecoverReputation);

		JMenuItem mntmDay = new JMenuItem("Day -10");
		mntmDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (current_day - 10 >= 0)
					current_day -= 10;
				else
					current_day = 0;
				lblDay.setText("Day: " + current_day + "/" + TOTAL_DAY);
				if (current_day < (TOTAL_DAY - 5))
					lblDay.setForeground(new Color(0, 250, 0));
			}
		});
		mnCheat.add(mntmDay);

		// Test menu
		JMenu mnTest = new JMenu("Test");
		menuBar.add(mnTest);

		// Loss 1000 cash
		JMenuItem mntmLossCash = new JMenuItem("Cash -1000");
		mntmLossCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((cash - 1000) >= 0)
					cash -= 1000;
				else
					cash = 0;
				cash_text.setText(Integer.toString(cash));
			}
		});
		mnTest.add(mntmLossCash);

		// Loss 1000 deposit
		JMenuItem mntmLossDeposit = new JMenuItem("Deposit -1000");
		mntmLossDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((deposit - 1000) >= 0)
					deposit -= 1000;
				else
					deposit = 0;
				deposit_text.setText(Integer.toString(deposit));
			}
		});
		mnTest.add(mntmLossDeposit);

		// Add 1000 debt
		JMenuItem mntmAdd = new JMenuItem("Debt +1000");
		mntmAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				debt += 1000;
				debt_text.setText(Integer.toString(debt));
			}
		});

		// Loss 10000 cash
		JMenuItem mntmLossCash_1 = new JMenuItem("Cash -10000");
		mntmLossCash_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((cash - 10000) >= 0)
					cash -= 10000;
				else
					cash = 0;
				cash_text.setText(Integer.toString(cash));
			}
		});
		mnTest.add(mntmLossCash_1);

		// Loss 10000 deposit
		JMenuItem mntmLossDeposit_1 = new JMenuItem("Deposit -10000");
		mntmLossDeposit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((deposit - 10000) >= 0)
					deposit -= 10000;
				else
					deposit = 0;
				deposit_text.setText(Integer.toString(deposit));
			}
		});
		mnTest.add(mntmLossDeposit_1);
		mnTest.add(mntmAdd);

		// Loss 5 health
		JMenuItem mntmLossHealth = new JMenuItem("Health -5");
		mntmLossHealth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((health - 5) >= 0)
					health -= 5;
				else
					health = 0;
				health_text.setText(Integer.toString(health));
			}
		});
		mnTest.add(mntmLossHealth);

		// Loss 5 reputation
		JMenuItem mntmLossReputation = new JMenuItem("Reputation -5");
		mntmLossReputation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((reputation - 5) >= 0)
					reputation -= 5;
				else
					reputation = 0;
				reputation_text.setText(Integer.toString(reputation));
			}
		});
		mnTest.add(mntmLossReputation);

		// add 10 days
		JMenuItem mntmDay_1 = new JMenuItem("Day +10");
		mntmDay_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				current_day += 10;
				lblDay.setText("Day: " + current_day + "/" + TOTAL_DAY);
				if (current_day > (TOTAL_DAY - 5) && current_day <= TOTAL_DAY) {
					lblDay.setForeground(Color.RED);
					JOptionPane.showMessageDialog(new JFrame(),
							"My Ontario life has only " + (TOTAL_DAY - current_day + 1) + " days left.");
				}
				if (current_day > TOTAL_DAY) {
					lblDay.setText("Day: " + TOTAL_DAY + "/" + TOTAL_DAY);
					playSound("sound/50day.wav");
					JOptionPane.showMessageDialog(new JFrame(),
							"It's been " + TOTAL_DAY + " days! I have to leave Ontario!");
					game_ranking();
				}
			}
		});
		mnTest.add(mntmDay_1);

		// help menu
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		// ontario introduction
		JMenuItem mntmGameIntroduction = new JMenuItem("Ontario Introduction");
		mntmGameIntroduction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("sound/happy.wav");
				String msg = "Ontario is one of the 13 provinces and territories of Canada.\nLocated in Central Canada, it is Canada's most populous \nprovince accounting for 38.3 percent of the country's population,\n and is the second-largest province in total area. Ontario is \nfourth-largest jurisdiction in total area when the territories of the \nNorthwest Territories and Nunavut are included. It is home to the \n nation's capital city, Ottawa, and the nation's most populous city, \nToronto, which is also Ontario's provincial capital.";
				JOptionPane.showMessageDialog(new JFrame(), msg, "Ontario introduction", JOptionPane.PLAIN_MESSAGE);
			}
		});
		mnHelp.add(mntmGameIntroduction);

		// Game Background
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Game Background");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("sound/happy.wav");
				game_background();
			}
		});
		mnHelp.add(mntmNewMenuItem_6);

		// Game document
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Game Document");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().browse(new URL(
							"https://docs.google.com/document/d/1lJJgdDaa3tdGvRhBxwVFVZLKcJw6c835X8lChNEtlwE/edit?usp=sharing")
									.toURI());
				} catch (Exception e1) {
				}
			}
		});
		mnHelp.add(mntmNewMenuItem_7);

		// About This game
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("About This game");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("sound/happy.wav");

				JDialog dialog = new JDialog(new JFrame(), "About this game");
				ImageIcon img = new ImageIcon("icon/ontario.jpg");
				dialog.setIconImage(img.getImage());

				JPanel p = new JPanel();
				p.setLayout(new GridLayout(8, 1));

				JPanel p1 = new JPanel();
				JPanel p2 = new JPanel();
				JPanel p3 = new JPanel();
				JPanel p4 = new JPanel();
				JPanel p5 = new JPanel();
				JPanel p6 = new JPanel();
				JPanel p7 = new JPanel();
				JPanel p8 = new JPanel();

				// get current version
				InputStream input1 = null;
				try {
					input1 = new FileInputStream("version.txt");
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				BufferedReader buffer = new BufferedReader(new InputStreamReader(input1));
				try {
					version = buffer.readLine();
				} catch (IOException e2) {
					e2.printStackTrace();
				}

				JLabel text1 = new JLabel("The Underground Survivor");
				JLabel text2 = new JLabel(version);
				JLabel text3 = new JLabel(copyright);
				JLabel text4 = new JLabel("If you want to give suggestions, or report bugs, please E-mail:");
				JLabel text5 = new JLabel("123456789@hotmail.com");
				text5.setForeground(new Color(0, 0, 255));
				text5.setFont(new Font("SimSun", Font.ITALIC, 11));
				text5.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						try {
							Desktop.getDesktop().browse(new URL("http://www.google.com").toURI());
						} catch (Exception e1) {
						}
					}
				});
				JLabel text6 = new JLabel("If you want to check the latest update, please access: ");
				JLabel text7 = new JLabel("check for update");
				text7.setForeground(new Color(0, 0, 255));
				text7.setFont(new Font("SimSun", Font.ITALIC, 13));
				text7.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {

						// get current version
						InputStream input2 = null;
						String current_version = null;
						try {
							input2 = new FileInputStream("version.txt");
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
						BufferedReader buffer = new BufferedReader(new InputStreamReader(input2));
						try {
							current_version = buffer.readLine();
						} catch (IOException e2) {
							e2.printStackTrace();
						}

						// download file
						String url = "https://github.com/ZZAACCC/The-underground-servivor/archive/master.zip";
						String file_name = "file.zip";
						try {
							download(url, file_name);
						} catch (Exception e1) {
						}

						// unzip file
						File source = new File("file.zip");
						String dest = "file";
						try {
							unzip(source, dest);
						} catch (IOException e1) {
							e1.printStackTrace();
						}

						// delete zip file
						File file = new File("file.zip");
						file.delete();

						// unzip file
						source = new File("file/The-underground-servivor-master/game.zip");
						dest = "temp";
						try {
							unzip(source, dest);
						} catch (IOException e1) {
							e1.printStackTrace();
						}

						// get latest version
						InputStream input3 = null;
						String latest_version = null;
						try {
							input3 = new FileInputStream("temp\\game\\version.txt");
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
						buffer = new BufferedReader(new InputStreamReader(input3));
						try {
							latest_version = buffer.readLine();
						} catch (IOException e2) {
							e2.printStackTrace();
						}

						// compare version
						JOptionPane.showMessageDialog(new JFrame(),
								"Current version: " + current_version + "\nLatest version: " + latest_version);

						// close input stream
						try {
							input2.close();
						} catch (IOException e2) {
							e2.printStackTrace();
						}
						try {
							input3.close();
						} catch (IOException e2) {
							e2.printStackTrace();
						}

						// if same version number
						if (current_version.equals(latest_version)) {
							JOptionPane.showMessageDialog(new JFrame(), "Current version is already the latest.");
							String SRC_FOLDER1 = "file";
							String SRC_FOLDER2 = "temp";
							File directory1 = new File(SRC_FOLDER1);
							File directory2 = new File(SRC_FOLDER2);
							try {
								delete(directory1);
								delete(directory2);
							} catch (IOException e1) {
							}
						}

						// different version number
						else {
							// updating new exe
							JOptionPane.showMessageDialog(new JFrame(), "Downloading the new version soon.");

							// open updater
							File updater = new File("updater.exe");
							try {
								Desktop.getDesktop().open(updater);
							} catch (IOException e1) {
							}

							// close myself
							System.exit(0);
						}
					}
				});
				JButton button8 = new JButton("OK");
				button8.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dialog.dispose();
					}
				});

				p1.add(text1);
				p2.add(text2);
				p3.add(text3);
				p4.add(text4);
				p5.add(text5);
				p6.add(text6);
				p7.add(text7);
				p8.add(button8);

				p.add(p1);
				p.add(p2);
				p.add(p3);
				p.add(p4);
				p.add(p5);
				p.add(p6);
				p.add(p7);
				p.add(p8);

				dialog.getContentPane().add(p);
				dialog.pack();
				dialog.setLocation(300, 200);
				dialog.setVisible(true);
				dialog.setResizable(false);
			}
		});

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Game Guide");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// open guide
				File guide = new File("guide.html");
				try {
					Desktop.getDesktop().open(guide);
				} catch (IOException e1) {
				}
			}
		});
		mnHelp.add(mntmNewMenuItem_4);
		mnHelp.add(mntmNewMenuItem_8);
	}

	// place button listener
	private class place_listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String buttonText = ((JToggleButton) e.getSource()).getText();
			if ((JToggleButton) e.getSource() != btn_record) {
				releaseButton();
				((JToggleButton) e.getSource()).setSelected(true);
				txtBlackMarket.setText("Weird Market (" + buttonText + ")");
				place_switch();
			} else
				((JToggleButton) e.getSource()).setSelected(true);

			btn_record = (JToggleButton) e.getSource();
		}
	}

	// buy in button listener
	private class buy_in_listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// if player selected an item in market
			if (List1.getSelectedIndex() != -1) {

				// get the corresponding commodity
				Commodity temp_commodity = market_commodity_list.get(List1.getSelectedIndex());
				String temp_name = temp_commodity.getName();
				int temp_price = temp_commodity.getPrice();

				// only can buy item if not sold before
				if (!sell_list.contains(temp_commodity)) {

					// set up the basic container of buy in interface
					JFrame askframe = new JFrame();
					JDialog askdialog = new JDialog(askframe, "How much do I want to buy?");
					JPanel p = new JPanel();
					p.setLayout(new GridLayout(3, 1));

					// first part of buy in interface
					JPanel p1 = new JPanel();
					int max1 = cash / temp_price;
					int max2 = MAX_STOREHOUSE_SIZE - current_storehouse_size;
					int max = (max1 < max2) ? (max1) : (max2);
					JLabel label_valid = new JLabel("I have " + cash + " dollars, I can buy " + max1 + " " + temp_name
							+ ", I have " + max2 + " storehouse space left.");
					p1.add(label_valid);

					// second part of buy in interface
					JPanel p2 = new JPanel();
					JLabel label = new JLabel("How much do I want to buy? ");
					JSpinner spinner = new JSpinner();
					spinner.setValue(max);
					Component mySpinnerEditor = spinner.getEditor();
					JFormattedTextField jftf = ((JSpinner.DefaultEditor) mySpinnerEditor).getTextField();
					jftf.setColumns(4);
					p2.add(label);
					p2.add(spinner);

					// third part of buy in interface
					JPanel p3 = new JPanel();
					JButton ok_button = new JButton("OK");
					JButton cancel_button = new JButton("Cancel");
					p3.add(ok_button);
					p3.add(cancel_button);

					// OK button listener
					ok_button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {

							// get amount & cost of item to buy
							int amount = (int) spinner.getValue();
							int cost = amount * temp_price;

							// if reputation >= 80, commodity 20% discount
							if (reputation >= 80) {
								playSound("sound/happy.wav");
								int rand_int = 8 + random.nextInt(8);
								double percent = (double) rand_int / (double) 100;
								int old_cost = cost;
								cost -= (int) (percent * cost);
								JOptionPane.showMessageDialog(new JFrame(),
										"Those " + temp_name + " originally cost $" + old_cost
												+ ", but due to my good reputation, I have " + rand_int
												+ "% discount, now they costs $" + cost);
							}

							// valid input - positive amount
							if (amount >= 0) {

								// enough money
								if (cost <= cash) {
									int space_later = MAX_STOREHOUSE_SIZE - (current_storehouse_size + amount);
									int space_left = MAX_STOREHOUSE_SIZE - current_storehouse_size;

									// storehouse enough space
									if (space_later >= 0) {

										if (amount > 0)
											playSound("sound/buy.wav");

										// pay cash
										cash -= cost;
										cash_text.setText(Integer.toString(cash));

										// occupy storehouse
										current_storehouse_size += amount;
										store_size_label.setText(Integer.toString(current_storehouse_size) + "/"
												+ Integer.toString(MAX_STOREHOUSE_SIZE));

										// if item not in storehouse
										if (!storehouse_commodity_list.contains(temp_commodity)) {
											if (amount > 0) {
												model3.addElement((model3.size() + 1) + ")  " + temp_name);
												model4.addElement(temp_price);
												model5.addElement(amount);
												storehouse_commodity_list.add(temp_commodity);
											}
										}

										// if item already in storehouse
										else {
											int index = storehouse_commodity_list.indexOf(temp_commodity);
											int old_amount = model5.getElementAt(index);
											int new_amount = old_amount + amount;
											model5.setElementAt(new_amount, index);
										}

									}

									// storehouse lack space
									else {
										playSound("sound/err.wav");
										Component frame = null;
										JOptionPane.showMessageDialog(frame, "My storehouse lack space (I wanna buy "
												+ amount + " items, but " + space_left + " space left) :(.");
									}
								}

								// lack money
								else {
									playSound("sound/err.wav");
									Component frame = null;
									JOptionPane.showMessageDialog(frame, "Oops, I don't have enough money.");
								}
							}

							// invalid input - negative amount
							else {
								playSound("sound/err.wav");
								Component frame = null;
								JOptionPane.showMessageDialog(frame, "Don't try to fool the game!");
							}

							// close dialog
							askdialog.dispose();
						}
					});

					// calcel button listener
					cancel_button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							askdialog.dispose();
						}
					});

					// set up buy in interface
					p.add(p1);
					p.add(p2);
					p.add(p3);
					askdialog.getContentPane().add(p);
					askdialog.pack();
					askdialog.setLocationRelativeTo(null);
					askdialog.setVisible(true);

					// record that you have bought this item in this area
					buy_list.add(temp_commodity);
				}

				// if sold before, cannot buy this item
				else {
					playSound("sound/err.wav");
					JOptionPane.showMessageDialog(new JFrame(),
							"You ve just sold " + temp_name + " in this area, why do you buy it?");
				}
			}

			// if player did not select market item
			else {
				playSound("sound/err.wav");
				Component frame = null;
				JOptionPane.showMessageDialog(frame, "Oops, I haven't choose an item to buy yet :(");
			}
		}
	}

	// sell out button listener
	private class sell_out_listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// if player selected an item to sell
			if (List3.getSelectedIndex() != -1) {

				// get corresponding item
				Commodity temp_commodity = storehouse_commodity_list.get(List3.getSelectedIndex());
				String temp_name = temp_commodity.getName();

				// if the area sells the selected item
				if (market_commodity_list.indexOf(temp_commodity) != -1) {

					// only can sell if not bought this item before in same area
					if (!buy_list.contains(temp_commodity)) {
						int temp_price = market_commodity_list.get(market_commodity_list.indexOf(temp_commodity))
								.getPrice();

						// set up basic container for sell out interface
						JFrame askframe = new JFrame();
						JDialog askdialog = new JDialog(askframe, "How much do I want to sell?");
						JPanel p = new JPanel();
						p.setLayout(new GridLayout(3, 1));

						// first part of sell out interface
						JPanel p1 = new JPanel();
						int index = storehouse_commodity_list.indexOf(temp_commodity);
						int own_amount = model5.getElementAt(index);
						JLabel label_valid = new JLabel("I currently have " + own_amount + " " + temp_name
								+ ", (each values $" + temp_price + ").");
						p1.add(label_valid);

						// second part of sell out interface
						JPanel p2 = new JPanel();
						JLabel label = new JLabel("How much do I want to sell? ");
						JSpinner spinner = new JSpinner();
						spinner.setValue(own_amount);
						Component mySpinnerEditor = spinner.getEditor();
						JFormattedTextField jftf = ((JSpinner.DefaultEditor) mySpinnerEditor).getTextField();
						jftf.setColumns(4);
						p2.add(label);
						p2.add(spinner);

						// third part of sell out interface
						JPanel p3 = new JPanel();
						JButton ok_button = new JButton("OK");
						JButton cancel_button = new JButton("Cancel");
						p3.add(ok_button);
						p3.add(cancel_button);

						// OK button listener
						ok_button.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {

								// get amount to sell
								int amount = (int) spinner.getValue();

								// valid input - positive input
								if (amount >= 0) {

									// correct amount
									if (amount <= own_amount) {

										// earned money
										int earn = amount * temp_price;

										// if reputation >= 80, earn more money
										if (reputation >= 80) {
											playSound("sound/happy.wav");
											int rand_int = 8 + random.nextInt(8);
											double percent = (double) rand_int / (double) 100;
											int old_earn = earn;
											earn += (int) (percent * earn);
											JOptionPane.showMessageDialog(new JFrame(),
													"Initially I can earn $" + old_earn
															+ ", but due to my good reputation, now I can earn "
															+ rand_int + "% more, so I earned $" + earn);
										}

										cash += earn;
										cash_text.setText(Integer.toString(cash));

										if (amount > 0)
											playSound("sound/money.wav");

										// update storehouse amount JList
										int index = storehouse_commodity_list.indexOf(temp_commodity);
										int old_amount = model5.getElementAt(index);
										int new_amount = old_amount - amount;
										model5.setElementAt(new_amount, index);

										// if this item sell out, remove it from storehouse amount JList
										if (new_amount == 0) {
											storehouse_commodity_list.remove(index);

											model3.removeAllElements();
											for (int i = 0; i < storehouse_commodity_list.size(); i++)
												model3.addElement((model3.size() + 1) + ")  "
														+ storehouse_commodity_list.get(i).getName());

											model4.remove(index);
											model5.remove(index);
										}

										// update storehouse item amount
										current_storehouse_size -= amount;
										store_size_label.setText(Integer.toString(current_storehouse_size) + "/"
												+ Integer.toString(MAX_STOREHOUSE_SIZE));

										askdialog.dispose();

										// sold water
										if (temp_name.equals("Water")) {
											// when water shortage, increase reputation
											if (Water_price_increase == 1)
												event_B_increase_reputation(amount, temp_name, "water shortage");
										}

										// some commodities reduce reputation
										String inferior_commodity_1 = "Drugs";
										String inferior_commodity_2 = "Explosives <IED>";
										String inferior_commodity_3 = "Weapons";

										// you sold drugs, reduce reputation
										if (temp_name.equals(inferior_commodity_1))
											sold_inferior_goods(1, 10, inferior_commodity_1);

										// sold explosives
										else if (temp_name.equals(inferior_commodity_2)) {

											// if IED price normal, reduce reputation
											if (IED_price_increase == 0)
												sold_inferior_goods(2, 14, inferior_commodity_2);

											// if IED price up, increase reputaition
											else
												event_B_increase_reputation(amount, temp_name, "revolution");
										}

										// sold weapons
										else if (temp_name.equals(inferior_commodity_3)) {

											// if weapon price normal, reduce reputation
											if (weapon_price_increase == 0)
												sold_inferior_goods(5, 26, inferior_commodity_3);

											// if weapon price up, increase reputation
											else
												event_B_increase_reputation(amount, temp_name, "riot");
										}
									}

									// lack money
									else {
										playSound("sound/err.wav");
										Component frame = null;
										JOptionPane.showMessageDialog(frame, "I don't have enough " + temp_name + ".");
									}
								}

								// invalid input - negative input
								else {
									playSound("sound/err.wav");
									Component frame = null;
									JOptionPane.showMessageDialog(frame, "Don't try to fool the game!");
								}

								// close dialog
								askdialog.dispose();
							}
						});

						// cancel button listener
						cancel_button.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								askdialog.dispose();
							}
						});

						// set up sell out interface
						p.add(p1);
						p.add(p2);
						p.add(p3);
						askdialog.getContentPane().add(p);
						askdialog.pack();
						askdialog.setLocationRelativeTo(null);
						askdialog.setVisible(true);

						// record that you have sold this item in this area
						sell_list.add(temp_commodity);
					}

					else {
						playSound("sound/err.wav");
						JOptionPane.showMessageDialog(new JFrame(),
								"You ve just bought " + temp_name + " in this area, why do you sell it?");
					}
				}

				// this area don't sell this item
				else {
					playSound("sound/err.wav");
					JOptionPane.showMessageDialog(new JFrame(),
							"Ooops, it seems that this area don't do the business of " + temp_commodity.getName());
				}
			}

			// if player did not select an item to sell
			else {
				playSound("sound/err.wav");
				Component frame = null;
				JOptionPane.showMessageDialog(frame, "Oops, I haven't choose an item to sell yet :(");
			}
		}
	}

	private class discard_listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			// if player selected an item to discard
			if (List3.getSelectedIndex() != -1) {

				// get corresponding item
				Commodity temp_commodity = storehouse_commodity_list.get(List3.getSelectedIndex());
				String temp_name = temp_commodity.getName();

				// set up basic container for discard interface
				JFrame askframe = new JFrame();
				JDialog askdialog = new JDialog(askframe, "How much do I want to discard?");
				JPanel p = new JPanel();
				p.setLayout(new GridLayout(3, 1));

				// first part of discard interface
				JPanel p1 = new JPanel();
				int index = storehouse_commodity_list.indexOf(temp_commodity);
				int own_amount = model5.getElementAt(index);
				JLabel label_valid = new JLabel(
						"My storehouse lack space, I currently have " + own_amount + " " + temp_name);
				p1.add(label_valid);

				// second part of discard interface
				JPanel p2 = new JPanel();
				JLabel label = new JLabel("How much " + temp_name + " do I want to discard? ");
				JSpinner spinner = new JSpinner();
				spinner.setValue(own_amount);
				Component mySpinnerEditor = spinner.getEditor();
				JFormattedTextField jftf = ((JSpinner.DefaultEditor) mySpinnerEditor).getTextField();
				jftf.setColumns(4);
				p2.add(label);
				p2.add(spinner);

				// third part of discard interface
				JPanel p3 = new JPanel();
				JButton ok_button = new JButton("OK");
				JButton cancel_button = new JButton("Cancel");
				p3.add(ok_button);
				p3.add(cancel_button);

				// OK button listener
				ok_button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						// get amount to discard
						int amount = (int) spinner.getValue();

						// valid input - positive input
						if (amount >= 0) {

							// correct amount
							if (amount <= own_amount) {
								playSound("sound/discard.wav");

								// update storehouse amount JList
								int index = storehouse_commodity_list.indexOf(temp_commodity);
								int old_amount = model5.getElementAt(index);
								int new_amount = old_amount - amount;
								model5.setElementAt(new_amount, index);

								// if no this item anymore, remove it from storehouse amount JList
								if (new_amount == 0) {
									storehouse_commodity_list.remove(index);

									model3.removeAllElements();
									for (int i = 0; i < storehouse_commodity_list.size(); i++)
										model3.addElement((model3.size() + 1) + ")  "
												+ storehouse_commodity_list.get(i).getName());

									model4.remove(index);
									model5.remove(index);
								}

								// update storehouse item amount
								current_storehouse_size -= amount;
								store_size_label.setText(Integer.toString(current_storehouse_size) + "/"
										+ Integer.toString(MAX_STOREHOUSE_SIZE));

								// close dialog
								askdialog.dispose();

								// mention discard sucess
								JOptionPane.showMessageDialog(new JFrame(),
										"I just discarded " + amount + " " + temp_name);
							}

							// over amount
							else {
								playSound("sound/err.wav");
								Component frame = null;
								JOptionPane.showMessageDialog(frame,
										"I don't have so much " + temp_name + " to discard.");
							}
						}

						// invalid input - negative input
						else {
							playSound("sound/err.wav");
							Component frame = null;
							JOptionPane.showMessageDialog(frame, "Don't try to fool the game!");
						}

						// close dialog
						askdialog.dispose();
					}
				});

				// cancel button listener
				cancel_button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						askdialog.dispose();
					}
				});

				// set up discard interface
				p.add(p1);
				p.add(p2);
				p.add(p3);
				askdialog.getContentPane().add(p);
				askdialog.pack();
				askdialog.setLocationRelativeTo(null);
				askdialog.setVisible(true);
			}

			// if player did not select an item to discard
			else {
				playSound("sound/err.wav");
				Component frame = null;
				JOptionPane.showMessageDialog(frame, "Oops, I haven't choose an item to discard yet :(");
			}
		}
	}

	// bank button listener
	private class bank_listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// set up the basic container of the bank interface
			JFrame askframe = new JFrame();
			JDialog askdialog = new JDialog(askframe, "Bank");
			JPanel p = new JPanel();
			p.setLayout(new GridLayout(2, 1));

			// first part of bank interface
			JPanel p1 = new JPanel();
			JLabel label_valid = new JLabel(
					"Welcome to Bank! You have cash " + cash + ", your deposit is " + deposit + ", you want to?");
			p1.add(label_valid);

			// second part of bank interface
			JPanel p2 = new JPanel();
			JButton deposit_button = new JButton("Deposit");
			JButton withdraw_button = new JButton("Withdraw");
			JButton Leave_button = new JButton("Leave");

			// listener of deposit button
			deposit_button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					// set up basic container for deposit interface
					JFrame askframe = new JFrame();
					JDialog askdialog_deposit = new JDialog(askframe, "Cash transaction");
					JPanel p = new JPanel();
					p.setLayout(new GridLayout(2, 1));

					// first part of deposit interface
					JPanel p1 = new JPanel();
					JLabel label_valid = new JLabel("How much do you want to deposit?");
					JSpinner spinner = new JSpinner();
					spinner.setValue(cash);
					Component mySpinnerEditor = spinner.getEditor();
					JFormattedTextField jftf = ((JSpinner.DefaultEditor) mySpinnerEditor).getTextField();
					jftf.setColumns(4);
					p1.add(label_valid);
					p1.add(spinner);

					// second part of deposit interface
					JPanel p2 = new JPanel();
					JButton Confirm_button = new JButton("Confirm transaction");
					JButton Cancel_button = new JButton("Cancel transaction");

					// confirm button listener
					Confirm_button.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {

							// get deposit amount
							int amount = (int) spinner.getValue();

							// normal input - positive amount
							if (amount >= 0) {

								// player has enought monry
								if (amount <= cash) {
									playSound("sound/money.wav");
									// cash become deposit
									cash -= amount;
									deposit += amount;

									// hacker mode (1/3 possibility hack)
									if (hacker_status == 1) {
										Random random = new Random();
										int rand_num = random.nextInt(3);
										if (rand_num == 0) {
											deposit -= (amount / 10);
											playSound("sound/hack.wav");
											JOptionPane.showMessageDialog(new JFrame(),
													"1/10 of my deposit was stolen by hacker!");
										}
									}

									// no hacker mode
									else {
										if (amount > 0)
											playSound("sound/money.wav");
									}

									// update textfield
									cash_text.setText(Integer.toString(cash));
									deposit_text.setText(Integer.toString(deposit));
								}

								// player lack money
								else {
									playSound("sound/err.wav");
									Component frame = null;
									JOptionPane.showMessageDialog(frame, "I don't have enought cash!");
								}
							}

							// invalid input - negative amount
							else {
								playSound("sound/err.wav");
								Component frame = null;
								JOptionPane.showMessageDialog(frame, "Don't try to fool the game!");
							}

							// close dialog
							askdialog.dispose();
							askdialog_deposit.dispose();
						}
					});

					// cancel button listener
					Cancel_button.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							askdialog_deposit.dispose();
						}
					});

					// set up deposit panel
					p2.add(Confirm_button);
					p2.add(Cancel_button);
					p.add(p1);
					p.add(p2);
					askdialog_deposit.getContentPane().add(p);
					askdialog_deposit.pack();
					askdialog_deposit.setLocationRelativeTo(null);
					askdialog_deposit.setVisible(true);
				}
			});

			// withdraw button listener
			withdraw_button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					// set up container of withdraw interface
					JFrame askframe = new JFrame();
					JDialog askdialog_withdraw = new JDialog(askframe, "Cash transaction");
					JPanel p = new JPanel();
					p.setLayout(new GridLayout(2, 1));

					// first part of withdraw interface
					JPanel p1 = new JPanel();
					JLabel label_valid = new JLabel("How much do you want to withdraw?");
					JSpinner spinner = new JSpinner();
					spinner.setValue(deposit);
					Component mySpinnerEditor = spinner.getEditor();
					JFormattedTextField jftf = ((JSpinner.DefaultEditor) mySpinnerEditor).getTextField();
					jftf.setColumns(4);
					p1.add(label_valid);
					p1.add(spinner);

					// second part of withdraw interface
					JPanel p2 = new JPanel();
					JButton Confirm_button = new JButton("Confirm transaction");
					JButton Cancel_button = new JButton("Cancel transaction");

					// confirm button listener
					Confirm_button.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {

							// get withdraw amount
							int amount = (int) spinner.getValue();

							// valid input - pisitive withdraw
							if (amount >= 0) {

								// has enought deposit
								if (amount <= deposit) {
									playSound("sound/money.wav");
									deposit -= amount;
									cash += amount;

									// hacker mode
									if (hacker_status == 1) {

										// 1/3 possibility hack
										Random random = new Random();
										int rand_num = random.nextInt(3);
										if (rand_num == 0) {
											cash -= (amount / 10);
											playSound("sound/hack.wav");
											JOptionPane.showMessageDialog(new JFrame(),
													"1/10 of my withdraw was stolen by hacker!");
										}
									}

									// no hacker mode
									else {
										if (amount > 0)
											playSound("sound/money.wav");
									}

									cash_text.setText(Integer.toString(cash));
									deposit_text.setText(Integer.toString(deposit));
								}

								// lack deposit
								else {
									playSound("sound/err.wav");
									Component frame = null;
									JOptionPane.showMessageDialog(frame, "I don't have enought deposit!");
								}
							}

							// invalid input - negative withdraw
							else {
								playSound("sound/err.wav");
								Component frame = null;
								JOptionPane.showMessageDialog(frame, "Don't try to fool the game!");
							}

							// close dialogs
							askdialog.dispose();
							askdialog_withdraw.dispose();
						}
					});

					// cancel button listener
					Cancel_button.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							askdialog_withdraw.dispose();
						}
					});

					// set up withdraw interface
					p2.add(Confirm_button);
					p2.add(Cancel_button);
					p.add(p1);
					p.add(p2);
					askdialog_withdraw.getContentPane().add(p);
					askdialog_withdraw.pack();
					askdialog_withdraw.setLocationRelativeTo(null);
					askdialog_withdraw.setVisible(true);
				}
			});

			// leave button listener
			Leave_button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					askdialog.dispose();
				}
			});

			// set up bank interface
			p2.add(deposit_button);
			p2.add(withdraw_button);
			p2.add(Leave_button);
			p.add(p1);
			p.add(p2);
			askdialog.getContentPane().add(p);
			askdialog.pack();
			askdialog.setLocationRelativeTo(null);
			askdialog.setVisible(true);
		}
	}

	// hospital button listener
	private class hospital_listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// need to recover health
			if (health < 100) {

				// set up hospital interface
				JFrame validframe = new JFrame();
				JDialog hospital_dialog = new JDialog(validframe, "Hospotal");
				JPanel p = new JPanel();
				p.setLayout(new GridLayout(3, 1));

				// first part of hospital interface
				JPanel p1 = new JPanel();
				int require_point = 100 - health;
				int unit_charge = 2500;
				JLabel label_talk = new JLabel(
						"The doctor says: Oops, you are injured, your HP is " + health + ", you need " + require_point
								+ " to fully recover, we only charge " + unit_charge + " per HP point.");
				p1.add(label_talk);

				// second part of hospital interface
				JPanel p2 = new JPanel();
				JLabel point = new JLabel("You want to heal: ");
				JSpinner spinner = new JSpinner();
				spinner.setValue(require_point);
				Component mySpinnerEditor = spinner.getEditor();
				JFormattedTextField jftf = ((JSpinner.DefaultEditor) mySpinnerEditor).getTextField();
				jftf.setColumns(4);
				p2.add(point);
				p2.add(spinner);

				// third part of hospital interface
				JPanel p3 = new JPanel();
				JButton Confirm = new JButton("Confirm");
				JButton Expensive = new JButton("Too expensive!");

				// confirm button listener
				Confirm.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						int point = (int) spinner.getValue();
						int cost = unit_charge * point;

						// valid input - positive input
						if (point >= 0) {

							// normal range of recover
							if (point <= require_point) {

								// enough money
								if (cost <= cash) {
									cash -= cost;
									health += point;
									cash_text.setText(Integer.toString(cash));
									health_text.setText(Integer.toString(health));
									playSound("sound/happy.wav");
									Component frame = null;
									JOptionPane.showMessageDialog(frame, "Wow, I am healthy now!");
								}

								// lack money
								else {
									playSound("sound/err.wav");
									Component frame = null;
									JOptionPane.showMessageDialog(frame, "Go get some money first, pls!");
								}
							}

							// exceed 100 value
							else {
								playSound("sound/err.wav");
								Component frame = null;
								JOptionPane.showMessageDialog(frame, "Are you intending to evolve?");
							}
						}

						// invalid input - negative input
						else {
							playSound("sound/err.wav");
							Component frame = null;
							JOptionPane.showMessageDialog(frame, "What kind of spanking do you deserve?");
						}

						// close dialog
						hospital_dialog.dispose();
					}
				});

				// expensive button listener
				Expensive.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						hospital_dialog.dispose();
					}
				});

				// set up hospital interface
				p3.add(Confirm);
				p3.add(Expensive);
				p.add(p1);
				p.add(p2);
				p.add(p3);
				hospital_dialog.getContentPane().add(p);
				hospital_dialog.pack();
				hospital_dialog.setLocationRelativeTo(null);
				hospital_dialog.setVisible(true);
			}

			// full health value, no need to recover
			else {
				playSound("sound/err.wav");
				Component frame = null;
				JOptionPane.showMessageDialog(frame, "You are healthy, why you here?");
			}
		}
	}

	// post office button listener
	private class postoffice_listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			// debt not clear
			if (debt > 0) {

				// set up casic container of post office interface
				JFrame frame = new JFrame();
				JDialog post_office_dialog = new JDialog(frame, "Post Office (pay debt)");
				JPanel p = new JPanel();
				p.setLayout(new GridLayout(3, 1));

				// first part of post office interface
				JPanel p1 = new JPanel();
				JLabel label_talk = new JLabel("The creditor phoned me 'Hi honey, you still owe me " + debt + " :)'");
				p1.add(label_talk);

				// second part of post office interface
				JPanel p2 = new JPanel();
				JLabel pay = new JLabel("I want to pay? ");
				JSpinner spinner = new JSpinner();
				int smaller = (cash < debt) ? (cash) : (debt);
				spinner.setValue(smaller);
				Component mySpinnerEditor = spinner.getEditor();
				JFormattedTextField jftf = ((JSpinner.DefaultEditor) mySpinnerEditor).getTextField();
				jftf.setColumns(4);
				p2.add(pay);
				p2.add(spinner);

				// third part of post office interface
				JPanel p3 = new JPanel();
				JButton Pay = new JButton("Pay debt");
				JButton Escape = new JButton("Escape");

				// pay button listener
				Pay.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						// get the amount to pay debt
						int amount = (int) spinner.getValue();

						// valid input - positive input
						if (amount >= 0) {

							// enough money
							if (amount <= cash) {

								// debt not clear yet
								if ((debt - amount) > 0) {
									cash -= amount;
									debt -= amount;

									// only operate when amount > 0
									if (amount > 0) {
										cash_text.setText(Integer.toString(cash));
										debt_text.setText(Integer.toString(debt));
										playSound("sound/happy.wav");
										JOptionPane.showMessageDialog(new JFrame(),
												"Wow, $" + debt + " debt left, keep going honey :)");
									}
								}

								// debt clear
								else {
									cash -= debt;
									debt = 0;
									cash_text.setText(Integer.toString(cash));
									debt_text.setText(Integer.toString(debt));
									playSound("sound/money.wav");
									playSound("sound/happy.wav");
									JOptionPane.showMessageDialog(frame,
											"Honey, you are out of debt! Now you are free!");
								}
							}

							// lack money
							else {
								playSound("sound/err.wav");
								Component frame = null;
								JOptionPane.showMessageDialog(frame, "I just can't pay the debt now :(");
							}
						}

						// invalid input - negative input
						else {
							playSound("sound/err.wav");
							Component frame = null;
							JOptionPane.showMessageDialog(frame, "So honey, you mean I owe you?");
						}

						// close dialog
						post_office_dialog.dispose();
					}
				});

				// escape button listener
				Escape.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						post_office_dialog.dispose();
					}
				});

				// set up post opffice dialog
				p3.add(Pay);
				p3.add(Escape);
				p.add(p1);
				p.add(p2);
				p.add(p3);
				post_office_dialog.getContentPane().add(p);
				post_office_dialog.pack();
				post_office_dialog.setLocationRelativeTo(null);
				post_office_dialog.setVisible(true);
			}

			// debt clear
			else {
				playSound("sound/happy.wav");
				JOptionPane.showMessageDialog(new JFrame(),
						"Honey, you rich, you don't owe me, do you love my daughter? :)");
			}
		}
	}

	// house button listener
	private class house_listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			int money = cash + deposit;

			// if carrying cash more than 30,000
			if (money >= 30000) {

				// set up basic container of house interface
				JFrame frame = new JFrame();
				JDialog house_dialog = new JDialog(frame, "Buy house (expand storehouse)");
				JPanel p = new JPanel();
				p.setLayout(new GridLayout(2, 1));

				// first part of house interface
				JPanel p1 = new JPanel();
				double rand_percent = (double) (15 + random.nextInt(15)) / 100;
				int cost = (int) ((double) money * rand_percent);
				if (cost >= 4500 && cost < 20000)
					storehouse_expand = 20;
				else if (cost >= 20000 && cost < 70000)
					storehouse_expand = 60;
				else if (cost >= 70000 && cost < 150000)
					storehouse_expand = 100;
				else if (cost >= 150000)
					storehouse_expand = 150;

				JLabel label_talk = new JLabel("Hi, welcome to the real estate agency, you can expand "
						+ storehouse_expand + " storehouse space by only $" + cost + " :)");
				p1.add(label_talk);

				// second part of house interface
				JPanel p2 = new JPanel();
				JButton Purchase = new JButton("Purchase");
				JButton Leave = new JButton("No! Thanks");

				// purchase button listener
				Purchase.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						// first spend money from deposit, then the cash
						deposit -= cost;
						if (deposit < 0) {
							cash += deposit;
							deposit = 0;
						}
						playSound("sound/money.wav");
						cash_text.setText(Integer.toString(cash));
						deposit_text.setText(Integer.toString(deposit));

						// expand storehouse
						MAX_STOREHOUSE_SIZE += storehouse_expand;
						store_size_label.setText(Integer.toString(current_storehouse_size) + "/"
								+ Integer.toString(MAX_STOREHOUSE_SIZE));

						// mention player that the store house size is expanded
						playSound("sound/happy.wav");
						JOptionPane.showMessageDialog(new JFrame(),
								"Hahaha, I have expanded " + storehouse_expand + " space in my store house!");

						// close dialog
						house_dialog.dispose();
					}
				});

				// leave button listener
				Leave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						house_dialog.dispose();
					}
				});

				// set up house interface
				p2.add(Purchase);
				p2.add(Leave);
				p.add(p1);
				p.add(p2);
				house_dialog.getContentPane().add(p);
				house_dialog.pack();
				house_dialog.setLocationRelativeTo(null);
				house_dialog.setVisible(true);
			}

			// if cash less than 30,000
			else {
				playSound("sound/err.wav");
				Component frame = null;
				JOptionPane.showMessageDialog(frame,
						"Honey, you are perfectly welcome when you are the rich with $30,000+.");
			}
		}
	}

	private class Commodity {
		private String name;
		private Integer price;

		public Commodity(String name, int price) {
			this.name = name;
			this.price = price;
		}

		public String getName() {
			return name;
		}

		public Integer getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}
	}

	private class Player {
		private String name;
		private int money;
		private int health;
		private String reputation;

		public Player(String name, int money, int health, String reputation) {
			this.name = name;
			this.money = money;
			this.health = health;
			this.reputation = reputation;
		}

		public String getName() {
			return name;
		}

		public Integer getMoney() {
			return money;
		}

		public Integer getHealth() {
			return health;
		}

		public String getReputation() {
			return reputation;
		}

		public void setReputation(String reputation) {
			this.reputation = reputation;
		}
	}
}