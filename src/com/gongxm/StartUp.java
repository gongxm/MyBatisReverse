package com.gongxm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;

import com.gongxm.domain.TypeItem;
import com.gongxm.domain.XmlParam;
import com.gongxm.utils.GeneratorSqlmapTool;
import com.gongxm.utils.TextUtils;
import com.gongxm.utils.XmlWriter;

public class StartUp {
	private JFrame frmMybatis;
	private JPasswordField pf_db_pwd;
	private JTextField tf_db_user;
	private JTextField tf_db_url;
	private JTextField tf_pojo_package;
	private JTextField tf_mapper_package;
	private JTextField tf_table;
	private JTextField tf_field;
	private JTextField tf_java_type;
	private JTextField tf_db_name;

	private List<TypeItem> typeList = new ArrayList<TypeItem>();
	private List<JCheckBox> checkBoxList = new LinkedList<JCheckBox>();
	private List<String> tableList = new ArrayList<String>();
	private JTextField tf_dir;
	private int pwidth;
	private int pheight;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartUp window = new StartUp();
					window.frmMybatis.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StartUp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screensize.getWidth();
		int left = width / 2 - 300;
		frmMybatis = new JFrame();
		frmMybatis.setTitle(
				"MyBatis\u9006\u5411\u5DE5\u5177 (made by \u9F9A\u51BC\u654F) \u6682\u53EA\u652F\u6301MySQL\u548COracle");
		frmMybatis.setBounds(left, 100, 755, 485);
		frmMybatis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMybatis.getContentPane().setLayout(null);
		frmMybatis.setResizable(false);

		JLabel label_5 = new JLabel("\u9700\u8981\u9006\u5411\u7684\u8868:");
		label_5.setForeground(new Color(148, 0, 211));
		label_5.setBounds(21, 165, 100, 15);
		frmMybatis.getContentPane().add(label_5);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(21, 194, 321, 182);
		frmMybatis.getContentPane().add(scrollPane);

		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		pwidth = panel.getPreferredSize().width;
		pheight = panel.getPreferredSize().height;

		JButton bt_connet_db = new JButton("\u8FDE\u63A5\u6570\u636E\u5E93");
		bt_connet_db.setBounds(242, 161, 100, 23);

		frmMybatis.getContentPane().add(bt_connet_db);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(21, 10, 321, 145);
		frmMybatis.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel label = new JLabel("\u6570\u636E\u5E93\u8FDE\u63A5\u5730\u5740:");
		label.setBounds(10, 32, 100, 15);
		panel_1.add(label);

		tf_db_url = new JTextField();
		tf_db_url.setText("mysql://localhost:3306");
		tf_db_url.setToolTipText("");
		tf_db_url.setBounds(111, 29, 200, 21);
		panel_1.add(tf_db_url);
		tf_db_url.setColumns(10);

		JLabel label_7 = new JLabel("\u6570\u636E\u5E93\u540D\u79F0:");
		label_7.setBounds(10, 60, 90, 15);
		panel_1.add(label_7);

		tf_db_name = new JTextField();
		tf_db_name.setToolTipText("");
		tf_db_name.setBounds(111, 57, 200, 21);
		panel_1.add(tf_db_name);
		tf_db_name.setColumns(10);

		JLabel label_1 = new JLabel("\u6570\u636E\u5E93\u8D26\u53F7:");
		label_1.setBounds(10, 85, 90, 15);
		panel_1.add(label_1);

		tf_db_user = new JTextField();
		tf_db_user.setBounds(111, 82, 200, 21);
		panel_1.add(tf_db_user);
		tf_db_user.setColumns(10);

		JLabel label_2 = new JLabel("\u6570\u636E\u5E93\u5BC6\u7801:");
		label_2.setBounds(10, 114, 90, 15);
		panel_1.add(label_2);

		pf_db_pwd = new JPasswordField();
		pf_db_pwd.setBounds(111, 111, 200, 21);
		panel_1.add(pf_db_pwd);

		JLabel label_8 = new JLabel("\u6570\u636E\u5E93\u76F8\u5173\u8BBE\u7F6E:");
		label_8.setForeground(Color.RED);
		label_8.setBounds(10, 7, 100, 15);
		panel_1.add(label_8);

		JButton button = new JButton("\u5BFC\u5165\u914D\u7F6E...");
		button.setBounds(205, 3, 106, 23);
		panel_1.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser ch = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				ch.setFileSelectionMode(JFileChooser.FILES_ONLY);
				ch.setFileFilter(new FileFilter() {
					@Override
					public String getDescription() {
						return "*.properties";
					}

					@Override
					public boolean accept(File f) {
						String name = f.getName();
						return name.endsWith(".properties");
					}
				});
				ch.showOpenDialog(null);
				File file = ch.getSelectedFile();
				if (file != null) {
					Properties prop = new Properties();
					try {
						prop.load(new FileInputStream(file));
						String url = prop.getProperty("url");
						String database = prop.getProperty("database");
						String username = prop.getProperty("username");
						String password = prop.getProperty("password");
						String pojo = prop.getProperty("pojo");
						String mapper = prop.getProperty("mapper");
						String dir = prop.getProperty("dir");

						tf_db_url.setText(url);
						tf_db_name.setText(database);
						tf_db_user.setText(username);
						pf_db_pwd.setText(password);
						tf_pojo_package.setText(pojo);
						tf_mapper_package.setText(mapper);
						tf_dir.setText(dir);
					} catch (IOException e1) {
						showMsg("配置文件加载失败!");
					}

				}
			}
		});

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(352, 10, 372, 145);
		frmMybatis.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JLabel label_4 = new JLabel("\u662F\u5426\u53BB\u9664\u81EA\u52A8\u751F\u6210\u7684\u6CE8\u91CA");
		label_4.setBounds(20, 32, 190, 15);
		panel_2.add(label_4);

		JLabel label_9 = new JLabel("\u9006\u5411\u76F8\u5173\u8BBE\u7F6E:");
		label_9.setFont(new Font("宋体", Font.BOLD, 12));
		label_9.setForeground(Color.BLUE);
		label_9.setBounds(10, 7, 123, 21);
		panel_2.add(label_9);

		JComboBox cb_comment = new JComboBox();
		cb_comment.setBounds(279, 27, 79, 21);
		panel_2.add(cb_comment);
		cb_comment.setModel(new DefaultComboBoxModel(new String[] { "\u662F", "\u5426" }));

		JLabel lbljdbcDecimal = new JLabel("\u628AJDBC DECIMAL\u548CNUMERIC\u7C7B\u578B\u89E3\u6790\u4E3A");
		lbljdbcDecimal.setBounds(20, 58, 281, 15);
		panel_2.add(lbljdbcDecimal);

		JComboBox cb_decimal_type = new JComboBox();
		cb_decimal_type.setBounds(279, 55, 79, 21);
		panel_2.add(cb_decimal_type);
		cb_decimal_type.setModel(new DefaultComboBoxModel(new String[] { "Integer", "Decimal" }));

		JLabel lblPojo = new JLabel("POJO\u7C7B\u7684\u5305\u540D:");
		lblPojo.setBounds(20, 86, 154, 15);
		panel_2.add(lblPojo);

		tf_pojo_package = new JTextField();
		tf_pojo_package.setBounds(152, 83, 206, 21);
		panel_2.add(tf_pojo_package);
		tf_pojo_package.setColumns(10);

		JLabel lblMapper = new JLabel("mapper\u6587\u4EF6\u7684\u5305\u540D:");
		lblMapper.setBounds(20, 114, 116, 15);
		panel_2.add(lblMapper);

		tf_mapper_package = new JTextField();
		tf_mapper_package.setBounds(152, 111, 206, 21);
		panel_2.add(tf_mapper_package);
		tf_mapper_package.setColumns(10);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(352, 194, 372, 182);
		frmMybatis.getContentPane().add(panel_3);
		panel_3.setLayout(null);

		JLabel label_6 = new JLabel(
				"\u7279\u6B8A\u5B57\u6BB5\u6620\u5C04(\u6CA1\u6709\u53EF\u4EE5\u4E0D\u8BBE\u7F6E):");
		label_6.setForeground(new Color(255, 140, 0));
		label_6.setBounds(10, 10, 204, 15);
		panel_3.add(label_6);

		JLabel lblTable = new JLabel("table:");
		lblTable.setBounds(10, 40, 54, 15);
		panel_3.add(lblTable);

		tf_table = new JTextField();
		tf_table.setBounds(46, 37, 66, 21);
		panel_3.add(tf_table);
		tf_table.setColumns(10);

		JLabel lblField = new JLabel("field:");
		lblField.setBounds(122, 40, 54, 15);
		panel_3.add(lblField);

		tf_field = new JTextField();
		tf_field.setBounds(160, 37, 66, 21);
		panel_3.add(tf_field);
		tf_field.setColumns(10);

		JLabel lblJavatype = new JLabel("javaType:");
		lblJavatype.setBounds(238, 40, 54, 15);
		panel_3.add(lblJavatype);

		tf_java_type = new JTextField();
		tf_java_type.setBounds(296, 37, 66, 21);
		panel_3.add(tf_java_type);
		tf_java_type.setColumns(10);

		JScrollPane scrollPane_type = new JScrollPane();
		scrollPane_type.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_type.setBounds(10, 63, 249, 109);
		panel_3.add(scrollPane_type);

		JPanel panel_4 = new JPanel();
		scrollPane_type.setViewportView(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JButton bt_add = new JButton("\u6DFB\u52A0");

		bt_add.setBounds(269, 65, 93, 23);
		panel_3.add(bt_add);

		JLabel lbln = new JLabel(
				"<html><body>\u70B9\u51FB\u5DE6\u4FA7\u6761\u76EE<br/>\u53EF\u5220\u9664\u6620\u5C04!<body></html>");
		lbln.setForeground(new Color(128, 0, 0));
		lbln.setFont(new Font("宋体", Font.PLAIN, 13));
		lbln.setHorizontalAlignment(SwingConstants.LEFT);
		lbln.setBounds(269, 131, 93, 41);
		panel_3.add(lbln);

		JButton bt_clear = new JButton("\u6E05\u7A7A");

		bt_clear.setBounds(269, 98, 93, 23);
		panel_3.add(bt_clear);

		JButton bt_create = new JButton("\u751F\u6210\u7ED3\u679C");

		bt_create.setFont(new Font("宋体", Font.BOLD, 14));
		bt_create.setBounds(618, 386, 106, 56);
		frmMybatis.getContentPane().add(bt_create);
		bt_create.setForeground(new Color(255, 0, 0));

		JLabel lab_tips = new JLabel("");
		lab_tips.setForeground(new Color(255, 0, 0));
		lab_tips.setBounds(352, 165, 153, 15);
		frmMybatis.getContentPane().add(lab_tips);

		JLabel label_3 = new JLabel("\u9009\u62E9\u6587\u4EF6\u5B58\u653E\u76EE\u5F55:");
		label_3.setBounds(21, 407, 132, 15);
		frmMybatis.getContentPane().add(label_3);

		tf_dir = new JTextField();
		tf_dir.setBackground(new Color(245, 255, 250));
		tf_dir.setEditable(false);
		tf_dir.setBounds(135, 404, 207, 21);
		frmMybatis.getContentPane().add(tf_dir);
		tf_dir.setColumns(10);

		JButton bt_select = new JButton("\u6D4F\u89C8");
		bt_select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser ch = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				ch.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				ch.showOpenDialog(null);
				File file = ch.getSelectedFile();
				if (file != null) {
					tf_dir.setText(file.getAbsolutePath());
				}
			}
		});
		bt_select.setBounds(352, 403, 93, 23);
		frmMybatis.getContentPane().add(bt_select);

		JCheckBox checkBox_all = new JCheckBox("\u5168\u9009");
		checkBox_all.setBounds(133, 161, 103, 23);
		frmMybatis.getContentPane().add(checkBox_all);

		checkBox_all.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean selected = checkBox_all.isSelected();
				for (JCheckBox cb : checkBoxList) {
					cb.setSelected(selected);
				}
			}
		});
		int itemWidth = scrollPane_type.getWidth();

		bt_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String table = tf_table.getText();
				String field = tf_field.getText();
				String javaType = tf_java_type.getText();
				if (TextUtils.isEmpty(table)) {
					showMsg("请填写表名!");
					return;
				}
				if (TextUtils.isEmpty(field)) {
					showMsg("请填写表字段!");
					return;
				}
				if (TextUtils.isEmpty(javaType)) {
					showMsg("请填写对应的java类型!");
					return;
				}

				TypeItem typeItem = new TypeItem(table, field, javaType);

				if (typeList.contains(typeItem)) {
					showMsg("该字段已经映射过了!");
					return;
				}

				typeList.add(typeItem);

				JLabel item = new JLabel(table + " : " + field + " => " + javaType);

				Dimension size = item.getPreferredSize();
				item.setBorder(new LineBorder(Color.GRAY));
				int height = size.height;
				size.setSize(itemWidth - 20, height);
				item.setPreferredSize(size);

				item.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int confirm = JOptionPane.showConfirmDialog(null, "是否删除该映射?", "提示",
								JOptionPane.OK_CANCEL_OPTION);
						// 0:确定 2:取消
						if (confirm == 0) {
							typeList.remove(typeItem);
							Dimension size = panel_4.getPreferredSize();
							panel_4.remove(item);
							panel_4.setPreferredSize(new Dimension(size.width, size.height - height - 5));
							panel_4.revalidate();
							panel_4.repaint();
						}
					}
				});
				tf_table.setText("");
				tf_field.setText("");
				tf_java_type.setText("");
				Dimension panelSize = panel_4.getPreferredSize();
				int panelWidth = panelSize.width;
				int panelHeight = panelSize.height;
				panel_4.add(item);
				panel_4.setPreferredSize(new Dimension(panelWidth, panelHeight + height + 5));
				panel_4.revalidate();
				panel_4.repaint();
			}
		});

		bt_connet_db.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				lab_tips.setText("");
				// 获取文本框内容
				String url = tf_db_url.getText();
				String dbName = tf_db_name.getText();
				String user = tf_db_user.getText();
				String pwd = new String(pf_db_pwd.getPassword());

				if (TextUtils.isEmpty(url)) {
					showMsg("请输入数据库连接地址!");
					return;
				} else if (TextUtils.isEmpty(dbName)) {
					showMsg("请输入数据库名称!");
					return;
				} else if (TextUtils.isEmpty(user)) {
					showMsg("请输入数据库用户名!");
					return;
				} else if (TextUtils.isEmpty(pwd)) {
					showMsg("请输入数据库密码!");
					return;
				}

				if (!url.endsWith("/")) {
					url += "/";
				}

				String con_url = "jdbc:" + url + dbName + "?user=" + user + "&password=" + pwd;

				if (url.startsWith("jdbc:")) {
					con_url = url + dbName + "?user=" + user + "&password=" + pwd;
				}

				String lowerCase = url.toLowerCase();
				if (!lowerCase.contains("mysql") && !lowerCase.contains("oracle")) {
					showMsg("不支持的数据库类型!");
					return;
				}

				int line = 0;
				panel.removeAll();
				tableList.clear();// 清空表集合
				checkBoxList.clear();
				checkBox_all.setSelected(false);

				panel.setPreferredSize(new Dimension(pwidth, pheight));// 恢复初始大小

				lab_tips.setText("正在连接数据库...");
				try {
					Connection con = DriverManager.getConnection(con_url);
					DatabaseMetaData dm = con.getMetaData();
					ResultSet rs = dm.getTables(dbName, dbName, null, new String[] { "TABLE" });
					int spWidth = scrollPane.getWidth();

					while (rs.next()) {
						String name = rs.getString("TABLE_NAME");
						JCheckBox cb = new JCheckBox(name);
						int width = cb.getPreferredSize().width;
						line += width;
						// 如果行宽大于滚动框宽度时, 另起一行
						if (line > spWidth - 5) {
							int panelHeight = panel.getPreferredSize().height;
							int panelWidth = panel.getPreferredSize().width;
							int height = cb.getPreferredSize().height;
							panel.setPreferredSize(new Dimension(panelWidth, panelHeight + height + 20));
							line = width; // 新的一行的宽度
						}

						cb.addItemListener(new ItemListener() {

							@Override
							public void itemStateChanged(ItemEvent e) {
								int state = e.getStateChange();// 1选中, 2去除选中
								String tbName = cb.getText();
								if (state == 1) {
									tableList.add(tbName);
								} else if (state == 2) {
									tableList.remove(tbName);
								}
							}
						});

						checkBoxList.add(cb);

						panel.add(cb);
						panel.revalidate();
						panel.repaint();
					}
					lab_tips.setText("连接数据库成功!");
				} catch (SQLException e1) {
					showMsg("获取表信息失败!异常信息:" + e1.getMessage());
					lab_tips.setText("连接数据库失败!");
				}

			}
		});

		// 清空按钮
		bt_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null, "是否清空所有映射?", "提示", JOptionPane.OK_CANCEL_OPTION);
				// 0:确定 2:取消
				if (confirm == 0) {
					typeList.clear();
					panel_4.removeAll();
					panel_4.revalidate();
					panel_4.repaint();
				}
			}
		});

		// 生成结果按钮
		bt_create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 获取文本框内容
				String url = tf_db_url.getText();
				String dbName = tf_db_name.getText();
				String user = tf_db_user.getText();
				String password = new String(pf_db_pwd.getPassword());

				String dirPath = tf_dir.getText();

				if (TextUtils.isEmpty(url)) {
					showMsg("请输入数据库连接地址!");
					return;
				} else if (TextUtils.isEmpty(dbName)) {
					showMsg("请输入数据库名称!");
					return;
				} else if (TextUtils.isEmpty(user)) {
					showMsg("请输入数据库用户名!");
					return;
				} else if (TextUtils.isEmpty(password)) {
					showMsg("请输入数据库密码!");
					return;
				}

				if (!url.endsWith("/")) {
					url += "/";
				}

				String conUrl = "jdbc:" + url + dbName;

				if (url.startsWith("jdbc:")) {
					conUrl = url + dbName;
				}

				if (tableList == null || tableList.size() == 0) {
					showMsg("请选择需要逆向的表!");
					return;
				}

				String lowerCase = url.toLowerCase();
				String dbType = null;
				if (lowerCase.contains("mysql")) {
					dbType = "MySQL";
				} else if (lowerCase.contains("oracle")) {
					dbType = "Oracle";
				} else {
					showMsg("不支持的数据库类型!");
					return;
				}

				String pojoPackage = tf_pojo_package.getText();
				String mapperPackage = tf_mapper_package.getText();

				if (TextUtils.isEmpty(pojoPackage)) {
					showMsg("请填写pojo包名!");
					return;
				}
				if (TextUtils.isEmpty(mapperPackage)) {
					showMsg("请填写mapper包名!");
					return;
				}

				if (TextUtils.isEmpty(dirPath)) {
					showMsg("请选择文件保存位置!");
					return;
				}

				int comIndex = cb_comment.getSelectedIndex();// 0:是 1:否
				int decIndex = cb_decimal_type.getSelectedIndex();// 0:Integer 1:Decimal

				// 1.生成xml文件
				XmlParam param = new XmlParam(comIndex, decIndex, dbType, conUrl, user, password, pojoPackage,
						mapperPackage, dirPath, tableList, typeList);
				try {
					InputStream inputStream = XmlWriter.createXml(param);
					// 2.根据xml文件进行逆向
					File destDir = new File(dirPath);
					if (!destDir.exists()) {
						destDir.mkdirs();
					}
					GeneratorSqlmapTool.generator(inputStream);
				} catch (IOException e1) {
					showMsg("写xml文件出现异常,请检查!异常信息:" + e1.getMessage());
					return;

				} catch (Exception e1) {
					e1.printStackTrace();
					showMsg("逆向过程出现异常,请检查!异常信息:" + e1.getMessage());
					return;
				}
				showMsg("逆向完成!");
			}
		});

	}

	private void showMsg(String tips) {
		JOptionPane.showMessageDialog(null, tips, "警告!", JOptionPane.WARNING_MESSAGE);
	}
}
